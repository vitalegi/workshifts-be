package it.vitalegi.workshifts.optimization;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import com.google.ortools.linearsolver.MPConstraint;
import com.google.ortools.linearsolver.MPObjective;
import com.google.ortools.linearsolver.MPSolver;
import com.google.ortools.linearsolver.MPVariable;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@Profile("discovery")
public class DummyRunner implements ApplicationRunner {

	@Override
	public void run(ApplicationArguments args) throws Exception {
		mpSolver();
		mixedIntegerProgramming();
	}

	protected void mpSolver() {
		// Create the linear solver with the GLOP backend.
		MPSolver solver = new MPSolver("SimpleLpProgram", MPSolver.OptimizationProblemType.GLOP_LINEAR_PROGRAMMING);

		// Create the variables x and y.
		MPVariable x = solver.makeNumVar(0.0, 1.0, "x");
		MPVariable y = solver.makeNumVar(0.0, 2.0, "y");

		System.out.println("Number of variables = " + solver.numVariables());

		// Create a linear constraint, 0 <= x + y <= 2.
		MPConstraint ct = solver.makeConstraint(0.0, 2.0, "ct");
		ct.setCoefficient(x, 1);
		ct.setCoefficient(y, 1);

		System.out.println("Number of constraints = " + solver.numConstraints());

		// Create the objective function, 3 * x + y.
		MPObjective objective = solver.objective();
		objective.setCoefficient(x, 3);
		objective.setCoefficient(y, 1);
		objective.setMaximization();

		solver.solve();

		System.out.println("Solution:");
		System.out.println("Objective value = " + objective.value());
		System.out.println("x = " + x.solutionValue());
		System.out.println("y = " + y.solutionValue());
	}

	protected void mixedIntegerProgramming() {
		System.out.println("mixedIntegerProgramming");

		MPSolver solver = new MPSolver("SimpleMipProgram",
				MPSolver.OptimizationProblemType.CBC_MIXED_INTEGER_PROGRAMMING);

		double infinity = java.lang.Double.POSITIVE_INFINITY;
		// x and y are integer non-negative variables.
		MPVariable x = solver.makeIntVar(0.0, infinity, "x");
		MPVariable y = solver.makeIntVar(0.0, infinity, "y");

		System.out.println("Number of variables = " + solver.numVariables());

		// x + 7 * y <= 17.5.
		MPConstraint c0 = solver.makeConstraint(-infinity, 17.5, "c0");
		c0.setCoefficient(x, 1);
		c0.setCoefficient(y, 7);

		// x <= 3.5.
		MPConstraint c1 = solver.makeConstraint(-infinity, 3.5, "c1");
		c1.setCoefficient(x, 1);
		c1.setCoefficient(y, 0);

		System.out.println("Number of constraints = " + solver.numConstraints());

		// Maximize x + 10 * y.
		MPObjective objective = solver.objective();
		objective.setCoefficient(x, 1);
		objective.setCoefficient(y, 10);
		objective.setMaximization();

		final MPSolver.ResultStatus resultStatus = solver.solve();

		if (resultStatus == MPSolver.ResultStatus.OPTIMAL) {
			System.out.println("Solution:");
			System.out.println("Objective value = " + objective.value());
			System.out.println("x = " + x.solutionValue());
			System.out.println("y = " + y.solutionValue());
		}
	}
}
