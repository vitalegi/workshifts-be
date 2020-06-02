package it.vitalegi.workshifts.optimizer;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import it.vitalegi.workshifts.WorkShiftContext;
import it.vitalegi.workshifts.model.Action;
import it.vitalegi.workshifts.model.Employee;
import it.vitalegi.workshifts.model.EmployeeConstraint;
import it.vitalegi.workshifts.model.Group;
import it.vitalegi.workshifts.util.ActionUtil;
import it.vitalegi.workshifts.util.DateUtil;
import it.vitalegi.workshifts.util.ListUtil;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class WorkShiftOptimizer {

	@Autowired
	GetWorkShiftIdentifierService getIdentifier;

	WorkShiftContext context;
	MPSolverImpl optimizer;

	public void optimize(WorkShiftContext context) {
		optimizer = new MPSolverImpl();
		this.context = context;

		List<List<LocalDate>> weeks = DateUtil.splitRangeSunday(context.getFrom(), context.getTo());
		for (Employee employee : context.getEmployees()) {
			addEmployee(employee);
			for (List<LocalDate> week : weeks) {
				List<EmployeeConstraint> constraints = employee.getConstraints();

				EmployeeConstraint constraint = constraints.stream()//
						.filter(c -> c.getStartDay().compareTo(ListUtil.first(week)) >= 0) //
						.filter(c -> c.getEndDay().compareTo(ListUtil.last(week)) <= 0) //
						.findFirst().orElse(null);

				if (constraint != null) {
					addConstraintEmployeeMaxActionsInRange(employee, week, Action.MORNING,
							constraint.getMaxMornings());
					addConstraintEmployeeMaxActionsInRange(employee, week, Action.AFTERNOON,
							constraint.getMaxAfternoons());
					addConstraintEmployeeMaxShiftsInRange(employee, week, constraint.getTotWorkShift());
				}
			}
		}

		for (Group group : context.getGroups()) {
			for (List<LocalDate> week : weeks) {
				addConstraintDailyMinGroupWorkShifts(group, week, Action.MORNING,
						group.getConstraints().getMinWorkersMorningWeekDays());
			}
		}

		// // area constraints
//		for (int day = 0; day < DAYS; day += 7) {
//			for (int dayOfWeek = 0; dayOfWeek < 5; dayOfWeek++) {
//				for (int[] area : areaResources) {
//					addConstraintMinAreaShiftsPerDay(optimizer, area[0], area, day + dayOfWeek, 1, Shift.M);
//					addConstraintMinAreaShiftsPerDay(optimizer, area[0], area, day + dayOfWeek, 0, Shift.P);
//				}
//			}
//			for (int dayOfWeek = 5; dayOfWeek < 7; dayOfWeek++) {
//				for (int[] area : areaResources) {
//					addConstraintMinAreaShiftsPerDay(optimizer, area[0], area, day + dayOfWeek, 0, Shift.M);
//					addConstraintMinAreaShiftsPerDay(optimizer, area[0], area, day + dayOfWeek, 0, Shift.P);
//				}
//			}
//		}
//
//		for (int day = 0; day < DAYS; day += 7) {
//			for (int dayOfWeek = 0; dayOfWeek < 5; dayOfWeek++) {
//				for (int[] area : macroAreaResources) {
//					addConstraintMinMacroAreaShiftsPerDay(optimizer, area[0], area, day + dayOfWeek, 0, Shift.M);
//					addConstraintMinMacroAreaShiftsPerDay(optimizer, area[0], area, day + dayOfWeek, 1, Shift.P);
//				}
//			}
//			for (int dayOfWeek = 5; dayOfWeek < 7; dayOfWeek++) {
//				for (int[] area : macroAreaResources) {
//					addConstraintMinMacroAreaShiftsPerDay(optimizer, area[0], area, day + dayOfWeek, 1, Shift.M);
//					addConstraintMinMacroAreaShiftsPerDay(optimizer, area[0], area, day + dayOfWeek, 1, Shift.P);
//				}
//			}
//		}
//
//		// max number of cars per shift
//		for (int day = 0; day < DAYS; day++) {
//			addConstraintMaxResourcesPerShift(optimizer, RESOURCES, day, Shift.M, CARS);
//			addConstraintMaxResourcesPerShift(optimizer, RESOURCES, day, Shift.P, CARS);
//		}
//
//		// maximize number of working days
//		for (int day = 0; day < DAYS; day++) {
//			for (int resource = 0; resource < RESOURCES; resource++) {
//				optimizer.addObjectiveCoefficient(name(resource, day, Shift.M), 1);
//				optimizer.addObjectiveCoefficient(name(resource, day, Shift.P), 1);
//			}
//		}
//		optimizer.setMaximization();
//
//		System.out.println("Number of variables = " + optimizer.getNumVariables());
//		System.out.println("Number of constraints = " + optimizer.getNumConstraints());
//
//		ResultStatus resultStatus = optimizer.solve();
//
//		System.out.println("ResultStatus:" + resultStatus);
//
//		if (resultStatus == MPSolver.ResultStatus.OPTIMAL) {
//			System.out.println("Solution:");
//			System.out.println("Objective value = " + optimizer.getObjective().value());
//
//			System.out.println();
//			for (int day = 0; day < DAYS; day++) {
//				if (day % 7 == 0) {
//					System.out.print(" |");
//				}
//				System.out.print(day < 10 ? "   " + day : "  " + day);
//			}
//			System.out.println();
//			for (int resource = 0; resource < RESOURCES; resource++) {
//				for (int day = 0; day < DAYS; day++) {
//					if (day % 7 == 0) {
//						System.out.print(" |");
//					}
//					System.out.print(" " + resourceDayValue(optimizer, resource, day));
//				}
//				System.out.println();
//			}
//
//			for (int day = 0; day < DAYS; day++) {
//				if (day % 7 == 0) {
//					System.out.print(" |");
//				}
//				long m = 0;
//				for (int resource = 0; resource < RESOURCES; resource++) {
//					m += getMorning(optimizer, resource, day);
//				}
//				System.out.print(m < 10 ? "   " + m : "  " + m);
//			}
//			System.out.println(" <== M");
//
//			for (int day = 0; day < DAYS; day++) {
//				if (day % 7 == 0) {
//					System.out.print(" |");
//				}
//				long p = 0;
//				for (int resource = 0; resource < RESOURCES; resource++) {
//					p += getAfternoon(optimizer, resource, day);
//				}
//				System.out.print(p < 10 ? "   " + p : "  " + p);
//			}
//			System.out.println(" <== P");
//
//			for (int day = 0; day < DAYS; day++) {
//				if (day % 7 == 0) {
//					System.out.print(" |");
//				}
//				long nothing = 0;
//				for (int resource = 0; resource < RESOURCES; resource++) {
//					nothing += getNothing(optimizer, resource, day);
//				}
//				System.out.print(nothing < 10 ? "   " + nothing : "  " + nothing);
//			}
//			System.out.println(" <== NOTHING");
//		}
	}

	protected void addEmployee(Employee employee) {

		for (LocalDate date : dates()) {
			// create values, per resource, per day
			optimizer.addIntVar(getIdentifier.varEmployeeDailyAction(employee, date, Action.MORNING), 0, 1);
			optimizer.addIntVar(getIdentifier.varEmployeeDailyAction(employee, date, Action.AFTERNOON), 0, 1);
			optimizer.addIntVar(getIdentifier.varEmployeeDailyAction(employee, date, Action.ABSENCE), 0, 1);
			optimizer.addIntVar(getIdentifier.varEmployeeDailyAction(employee, date, Action.IDLE), 0, 1);

			Action unchangeable = ActionUtil.getInstance()
					.getAction(context.getActionTracker().getAction(employee, date));
			if (unchangeable != null) {
				// if value is fixed, set constraint
				String constraintName = getIdentifier.employeeConstraintDailyFixedAction(employee, date, unchangeable);
				optimizer.addConstraint(constraintName, 1, 1);
				String employeeAction = getIdentifier.varEmployeeDailyAction(employee, date, unchangeable);
				optimizer.addConstraintCoefficient(constraintName, employeeAction, 1);
			}
			if (Action.ABSENCE != unchangeable) {
				// absence can be obtained only as input
				String constraintName = getIdentifier.employeeConstraintDailyFixedAction(employee, date,
						Action.ABSENCE);
				optimizer.addConstraint(constraintName, 0, 0);
				String employeeAction = getIdentifier.varEmployeeDailyAction(employee, date, Action.ABSENCE);
				optimizer.addConstraintCoefficient(constraintName, employeeAction, 1);
			}
			// max 1 activity per day
			String constraintName = getIdentifier.employeeDailyConstraint(employee, date);
			optimizer.addConstraint(constraintName, 0, 1);
			for (Action action : Action.values()) {
				String employeeAction = getIdentifier.varEmployeeDailyAction(employee, date, Action.MORNING);
				optimizer.addConstraintCoefficient(constraintName, employeeAction, 1);
			}
		}

	}

	protected void addConstraintEmployeeMaxActionsInRange(Employee employee, List<LocalDate> range, Action action,
			int max) {

		String constraintName = getIdentifier.employeeConstraintMaxActionsInRangeByType(employee, ListUtil.first(range),
				ListUtil.last(range), action);

		log.debug("addConstraintEmployeeMaxActionsInRange {}", constraintName);

		optimizer.addConstraint(constraintName, 0, max);

		for (LocalDate date : range) {
			optimizer.addConstraintCoefficient(constraintName,
					getIdentifier.varEmployeeDailyAction(employee, date, action), 1);
		}
	}

	protected void addConstraintEmployeeMaxShiftsInRange(Employee employee, List<LocalDate> range, int max) {

		String constraintName = getIdentifier.employeeConstraintMaxShiftsInRange(employee, ListUtil.first(range),
				ListUtil.last(range));

		log.debug("addConstraintEmployeeMaxShiftsInRange {}", constraintName);

		optimizer.addConstraint(constraintName, 0, max);

		for (LocalDate date : range) {
			for (Action action : Action.values()) {
				optimizer.addConstraintCoefficient(constraintName,
						getIdentifier.varEmployeeDailyAction(employee, date, action), 1);
			}
		}
	}

	protected List<LocalDate> dates() {
		return DateUtil.range(context.getFrom(), context.getTo());
	}

	private long getValue(MPSolverImpl optimizer, Employee employee, LocalDate date, Action action) {
		return Math
				.round(optimizer.getVar(getIdentifier.varEmployeeDailyAction(employee, date, action)).solutionValue());
	}

	private void addConstraintDailyMinGroupWorkShifts(Group group, List<LocalDate> range, Action action, int min) {
		String constraintName = getIdentifier.minGroupWorkShiftsInRange(group, ListUtil.first(range),
				ListUtil.last(range), action);
		log.debug("addConstraintDailyMinGroupWorkShifts {}", constraintName);
		optimizer.addConstraint(constraintName, min, Integer.MAX_VALUE);

		context.getEmployees().stream().filter(employee -> {
			if (employee.getSubgroup().equals(group)) {
				return true;
			}
			if (employee.getSubgroup().getParent().equals(group)) {
				return true;
			}
			return false;
		}).forEach(employee -> {
			for (LocalDate date : range) {
				optimizer.addConstraintCoefficient(constraintName,
						getIdentifier.varEmployeeDailyAction(employee, date, action), 1);
			}
		});
	}

//
//	private void addConstraintResourceMaxAfternoonsPerWeek(MPSolverImpl optimizer, int resource, int fromDay, int toDay,
//			int maxP) {
//
//		String constraintName = name(resource, fromDay, toDay, "P");
//		log.debug("addConstraintResourceMaxAfternoonsPerWeek {}", constraintName);
//		optimizer.addConstraint(constraintName, 0, maxP);
//		for (int day = fromDay; day < toDay; day++) {
//			optimizer.addConstraintCoefficient(constraintName, name(resource, day, Shift.P), 1);
//		}
//	}
//
//
//
//	private void addConstraintMaxResourcesPerShift(MPSolverImpl optimizer, int resources, int day, Shift shift,
//			int cars) {
//
//		String constraintName = String.format("cars_day_%d_%s", day, shift.name());
//		log.debug("addConstraintMaxResourcesPerShift {}", constraintName);
//		optimizer.addConstraint(constraintName, 0, cars);
//
//		for (int resource = 0; resource < resources; resource++) {
//			optimizer.addConstraintCoefficient(constraintName, name(resource, day, shift), 1);
//		}
//	}

}
