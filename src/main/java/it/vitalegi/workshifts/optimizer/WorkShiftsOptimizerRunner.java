package it.vitalegi.workshifts.optimizer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import com.google.ortools.linearsolver.MPSolver;
import com.google.ortools.linearsolver.MPSolver.ResultStatus;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@Profile("discovery")
public class WorkShiftsOptimizerRunner implements ApplicationRunner {

	final double INFINITY = java.lang.Double.POSITIVE_INFINITY;

	@Override
	public void run(ApplicationArguments args) throws Exception {
		MPSolverImpl optimizer = new MPSolverImpl();

		int DAYS = 14;
		int RESOURCES = 4;
		int CARS = 8;

		newResource(optimizer, 0, DAYS);
		for (int day = 0; day < DAYS; day += 7) {
			addConstraintResourceMaxMorningsPerWeek(optimizer, 0, day, day + 7, 5);
			addConstraintResourceMaxAfternoonsPerWeek(optimizer, 0, day, day + 7, 2);
			addConstraintResourceShiftsPerWeek(optimizer, 0, day, day + 7, 5);
		}
		newResource(optimizer, 1, DAYS);
		for (int day = 0; day < DAYS; day += 7) {
			addConstraintResourceMaxMorningsPerWeek(optimizer, 1, day, day + 7, 4);
			addConstraintResourceMaxAfternoonsPerWeek(optimizer, 1, day, day + 7, 2);
			addConstraintResourceShiftsPerWeek(optimizer, 1, day, day + 7, 4);
		}
		newResource(optimizer, 2, DAYS);
		for (int day = 0; day < DAYS; day += 7) {
			addConstraintResourceMaxMorningsPerWeek(optimizer, 2, day, day + 7, 5);
			addConstraintResourceMaxAfternoonsPerWeek(optimizer, 2, day, day + 7, 2);
			addConstraintResourceShiftsPerWeek(optimizer, 2, day, day + 7, 5);
		}

		for (int resource = 3; resource < RESOURCES; resource++) {
			newResource(optimizer, resource, DAYS);
			for (int day = 0; day < DAYS; day += 7) {
				addConstraintResourceMaxMorningsPerWeek(optimizer, resource, day, day + 7, 5);
				addConstraintResourceMaxAfternoonsPerWeek(optimizer, resource, day, day + 7, 2);
				addConstraintResourceShiftsPerWeek(optimizer, resource, day, day + 7, 5);
			}
		}

		List<int[]> areaResources = new ArrayList<>();
		for (int resource = 0; resource < RESOURCES; resource += 4) {
			List<Integer> list = new ArrayList<>();
			for (int areaRes = 0; (areaRes < 4) && (areaRes + resource < RESOURCES); areaRes++) {
				list.add(areaRes + resource);
			}
			areaResources.add(list.stream().mapToInt(n -> n).toArray());
		}
		log.info("Areas: {}", areaResources.stream().map(Arrays::toString).collect(Collectors.joining(",")));

		List<int[]> macroAreaResources = new ArrayList<>();
		for (int resource = 0; resource < RESOURCES; resource += 8) {
			List<Integer> list = new ArrayList<>();
			for (int areaRes = 0; (areaRes < 8) && (areaRes + resource < RESOURCES); areaRes++) {
				list.add(areaRes + resource);
			}
			macroAreaResources.add(list.stream().mapToInt(n -> n).toArray());
		}

		// area constraints
		for (int day = 0; day < DAYS; day += 7) {
			for (int dayOfWeek = 0; dayOfWeek < 5; dayOfWeek++) {
				for (int[] area : areaResources) {
					addConstraintMinAreaShiftsPerDay(optimizer, area[0], area, day + dayOfWeek, 1, Shift.M);
					addConstraintMinAreaShiftsPerDay(optimizer, area[0], area, day + dayOfWeek, 0, Shift.P);
				}
			}
			for (int dayOfWeek = 5; dayOfWeek < 7; dayOfWeek++) {
				for (int[] area : areaResources) {
					addConstraintMinAreaShiftsPerDay(optimizer, area[0], area, day + dayOfWeek, 0, Shift.M);
					addConstraintMinAreaShiftsPerDay(optimizer, area[0], area, day + dayOfWeek, 0, Shift.P);
				}
			}
		}

		for (int day = 0; day < DAYS; day += 7) {
			for (int dayOfWeek = 0; dayOfWeek < 5; dayOfWeek++) {
				for (int[] area : macroAreaResources) {
					addConstraintMinMacroAreaShiftsPerDay(optimizer, area[0], area, day + dayOfWeek, 0, Shift.M);
					addConstraintMinMacroAreaShiftsPerDay(optimizer, area[0], area, day + dayOfWeek, 1, Shift.P);
				}
			}
			for (int dayOfWeek = 5; dayOfWeek < 7; dayOfWeek++) {
				for (int[] area : macroAreaResources) {
					addConstraintMinMacroAreaShiftsPerDay(optimizer, area[0], area, day + dayOfWeek, 1, Shift.M);
					addConstraintMinMacroAreaShiftsPerDay(optimizer, area[0], area, day + dayOfWeek, 1, Shift.P);
				}
			}
		}

		// max number of cars per shift
		for (int day = 0; day < DAYS; day++) {
			addConstraintMaxResourcesPerShift(optimizer, RESOURCES, day, Shift.M, CARS);
			addConstraintMaxResourcesPerShift(optimizer, RESOURCES, day, Shift.P, CARS);
		}

		// maximize number of working days
		for (int day = 0; day < DAYS; day++) {
			for (int resource = 0; resource < RESOURCES; resource++) {
				optimizer.addObjectiveCoefficient(name(resource, day, Shift.M), 1);
				optimizer.addObjectiveCoefficient(name(resource, day, Shift.P), 1);
			}
		}
		optimizer.setMaximization();

		System.out.println("Number of variables = " + optimizer.getNumVariables());
		System.out.println("Number of constraints = " + optimizer.getNumConstraints());

		ResultStatus resultStatus = optimizer.solve();

		System.out.println("ResultStatus:" + resultStatus);

		if (resultStatus == MPSolver.ResultStatus.OPTIMAL) {
			System.out.println("Solution:");
			System.out.println("Objective value = " + optimizer.getObjective().value());

			System.out.println();
			for (int day = 0; day < DAYS; day++) {
				if (day % 7 == 0) {
					System.out.print(" |");
				}
				System.out.print(day < 10 ? "   " + day : "  " + day);
			}
			System.out.println();
			for (int resource = 0; resource < RESOURCES; resource++) {
				for (int day = 0; day < DAYS; day++) {
					if (day % 7 == 0) {
						System.out.print(" |");
					}
					System.out.print(" " + resourceDayValue(optimizer, resource, day));
				}
				System.out.println();
			}

			for (int day = 0; day < DAYS; day++) {
				if (day % 7 == 0) {
					System.out.print(" |");
				}
				long m = 0;
				for (int resource = 0; resource < RESOURCES; resource++) {
					m += getMorning(optimizer, resource, day);
				}
				System.out.print(m < 10 ? "   " + m : "  " + m);
			}
			System.out.println(" <== M");

			for (int day = 0; day < DAYS; day++) {
				if (day % 7 == 0) {
					System.out.print(" |");
				}
				long p = 0;
				for (int resource = 0; resource < RESOURCES; resource++) {
					p += getAfternoon(optimizer, resource, day);
				}
				System.out.print(p < 10 ? "   " + p : "  " + p);
			}
			System.out.println(" <== P");

			for (int day = 0; day < DAYS; day++) {
				if (day % 7 == 0) {
					System.out.print(" |");
				}
				long nothing = 0;
				for (int resource = 0; resource < RESOURCES; resource++) {
					nothing += getNothing(optimizer, resource, day);
				}
				System.out.print(nothing < 10 ? "   " + nothing : "  " + nothing);
			}
			System.out.println(" <== NOTHING");
		}
	}

	protected String resourceDayValue(MPSolverImpl optimizer, int resource, int day) {

		StringBuilder sb = new StringBuilder();
		long m = getMorning(optimizer, resource, day);
		long p = getAfternoon(optimizer, resource, day);
		long nothing = getNothing(optimizer, resource, day);
		if (m + p + nothing > 1) {
			sb.append("ERR_");
		}
		if (m == 1) {
			sb.append("M");
		}
		if (p == 1) {
			sb.append("P");
		}
		if (nothing == 1) {
			sb.append("_");
		}
		String str = sb.toString();
		while (str.length() < 3) {
			str = " " + str;
		}
		return str;
	}

	protected long getMorning(MPSolverImpl optimizer, int resource, int day) {
		return Math.round(optimizer.getVar(name(resource, day, Shift.M)).solutionValue());
	}

	protected long getAfternoon(MPSolverImpl optimizer, int resource, int day) {
		return Math.round(optimizer.getVar(name(resource, day, Shift.P)).solutionValue());
	}

	protected long getNothing(MPSolverImpl optimizer, int resource, int day) {
		return Math.round(optimizer.getVar(name(resource, day, Shift.NOTHING)).solutionValue());
	}

	protected void newResource(MPSolverImpl optimizer, int resource, int days) {

		for (int day = 0; day < days; day++) {
			optimizer.addIntVar(name(resource, day, Shift.M), 0, 1);
			optimizer.addIntVar(name(resource, day, Shift.P), 0, 1);
			// 0 - 0, it's a mandatory field
			optimizer.addIntVar(name(resource, day, Shift.ABSENCE), 0, 0);
			optimizer.addIntVar(name(resource, day, Shift.NOTHING), 0, 1);

			// max 1 activity per day
			String constraintName = String.format("res_%d_day_%d", resource, day);
			optimizer.addConstraint(constraintName, 0, 1);
			optimizer.addConstraintCoefficient(constraintName, name(resource, day, Shift.M), 1);
			optimizer.addConstraintCoefficient(constraintName, name(resource, day, Shift.P), 1);
			optimizer.addConstraintCoefficient(constraintName, name(resource, day, Shift.ABSENCE), 1);
			optimizer.addConstraintCoefficient(constraintName, name(resource, day, Shift.NOTHING), 1);
		}
	}

	protected void addConstraintResourceMaxMorningsPerWeek(MPSolverImpl optimizer, int resource, int fromDay, int toDay,
			int maxM) {

		String constraintName = name(resource, fromDay, toDay, "M");
		log.debug("addConstraintResourceMaxMorningsPerWeek {}", constraintName);
		optimizer.addConstraint(constraintName, 0, maxM);
		for (int day = fromDay; day < toDay; day++) {
			optimizer.addConstraintCoefficient(constraintName, name(resource, day, Shift.M), 1);
		}
	}

	protected void addConstraintResourceMaxAfternoonsPerWeek(MPSolverImpl optimizer, int resource, int fromDay,
			int toDay, int maxP) {

		String constraintName = name(resource, fromDay, toDay, "P");
		log.debug("addConstraintResourceMaxAfternoonsPerWeek {}", constraintName);
		optimizer.addConstraint(constraintName, 0, maxP);
		for (int day = fromDay; day < toDay; day++) {
			optimizer.addConstraintCoefficient(constraintName, name(resource, day, Shift.P), 1);
		}
	}

	protected void addConstraintResourceShiftsPerWeek(MPSolverImpl optimizer, int resource, int fromDay, int toDay,
			int total) {

		String constraintName = name(resource, fromDay, toDay, "TOT");
		log.debug("addConstraintResourceShiftsPerWeek {}", constraintName);
		optimizer.addConstraint(constraintName, 0, total);
		for (int day = fromDay; day < toDay; day++) {
			optimizer.addConstraintCoefficient(constraintName, name(resource, day, Shift.M), 1);
			optimizer.addConstraintCoefficient(constraintName, name(resource, day, Shift.P), 1);
			optimizer.addConstraintCoefficient(constraintName, name(resource, day, Shift.ABSENCE), 1);
			optimizer.addConstraintCoefficient(constraintName, name(resource, day, Shift.NOTHING), 1);
		}
	}

	protected void addConstraintMinAreaShiftsPerDay(MPSolverImpl optimizer, int area, int[] resources, int day, int min,
			Shift shift) {
		String constraintName = String.format("area_r_%s_day_%d_%s", area, day, shift.name());
		log.debug("addConstraintMinAreaShiftsPerDay {}", constraintName);
		optimizer.addConstraint(constraintName, min, Integer.MAX_VALUE);
		for (int resource : resources) {
			optimizer.addConstraintCoefficient(constraintName, name(resource, day, shift), 1);
		}
	}

	protected void addConstraintMinMacroAreaShiftsPerDay(MPSolverImpl optimizer, int macroarea, int[] resources,
			int day, int min, Shift shift) {
		String constraintName = String.format("macroarea_r_%s_day_%d_%s", macroarea, day, shift.name());
		log.debug("addConstraintMinMacroAreaShiftsPerDay {}", constraintName);
		optimizer.addConstraint(constraintName, min, Integer.MAX_VALUE);
		for (int resource : resources) {
			optimizer.addConstraintCoefficient(constraintName, name(resource, day, shift), 1);
		}
	}

	protected void addConstraintMaxResourcesPerShift(MPSolverImpl optimizer, int resources, int day, Shift shift,
			int cars) {

		String constraintName = String.format("cars_day_%d_%s", day, shift.name());
		log.debug("addConstraintMaxResourcesPerShift {}", constraintName);
		optimizer.addConstraint(constraintName, 0, cars);

		for (int resource = 0; resource < resources; resource++) {
			optimizer.addConstraintCoefficient(constraintName, name(resource, day, shift), 1);
		}
	}

	protected String name(int resource, int day, Shift shift) {
		return String.format("res_%d_day_%d_%s", resource, day, shift.name());
	}

	protected String name(int resource, int fromDay, int toDay, String subgroup) {
		return String.format("res_%d_days_%d_%d_%s", resource, fromDay, toDay, subgroup);
	}

	protected static enum Shift {
		M, P, ABSENCE, NOTHING
	}
}
