package it.vitalegi.workshifts.optimizer;

import java.util.HashMap;
import java.util.Map;

import com.google.ortools.linearsolver.MPConstraint;
import com.google.ortools.linearsolver.MPObjective;
import com.google.ortools.linearsolver.MPSolver;
import com.google.ortools.linearsolver.MPVariable;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class MPSolverImpl {

	final double INFINITY = java.lang.Double.POSITIVE_INFINITY;

	MPSolver solver;
	Map<String, MPVariable> variables;
	Map<String, MPConstraint> constraints;
	MPObjective objective;

	public MPSolverImpl() {
		init();
	}

	public void init() {
		solver = new MPSolver("WorkShiftsMip", MPSolver.OptimizationProblemType.CBC_MIXED_INTEGER_PROGRAMMING);
		variables = new HashMap<>();
		constraints = new HashMap<>();
		objective = solver.objective();
	}

	public MPVariable addIntVar(String name, int from, int to) {
		if (getVar(name) != null) {
			throw new IllegalArgumentException("Variable " + name + " alraedy defined");
		} 
		log.debug("Add Var: {}", name);
		MPVariable variable = solver.makeIntVar(from, to, name);
		variables.put(name, variable);
		return variable;
	}

	public MPVariable addIntVar(String name, double from, double to) {
		if (getVar(name) != null) {
			throw new IllegalArgumentException("Variable " + name + " alraedy defined");
		}
		log.debug("Add Var: {}", name);
		MPVariable variable = solver.makeIntVar(from, to, name);
		variables.put(name, variable);
		return variable;
	}

	public MPVariable getVar(String name) {
		return variables.get(name);
	}

	public void addConstraint(String name, double from, double to) {
		if (getConstraint(name) != null) {
			throw new IllegalArgumentException("Constraint " + name + " alraedy defined");
		}
		log.debug("Add Constraint: {}", name); 
		MPConstraint constraint = solver.makeConstraint(from, to, name);
		constraints.put(name, constraint);
	}

	private MPConstraint getConstraint(String name) {
		return constraints.get(name);
	}

	public void addConstraintCoefficient(String constraint, String variable, double coefficient) {
		log.debug("Add Constraint Coefficient: {}. Variable: {}", constraint, variable);
		if (getConstraint(constraint) == null) {
			throw new IllegalArgumentException("Constraint " + constraint + " doesn't exist");
		}
		if (getVar(variable) == null) {
			throw new IllegalArgumentException("Variable " + variable + " doesn't exist");
		}
		getConstraint(constraint).setCoefficient(getVar(variable), coefficient);
	}

	public MPObjective getObjective() {
		return objective;
	}

	public void addObjectiveCoefficient(String variable, double coefficient) {
		log.debug("Add Objective Coefficient: {}, {}", variable, coefficient);
		if (getVar(variable) == null) {
			throw new IllegalArgumentException("Variable " + variable + " doesn't exist");
		}
		objective.setCoefficient(getVar(variable), coefficient);
	}

	public void setMaximization() {
		objective.setMaximization();
	}

	public void setMinimization() {
		objective.setMinimization();
	}

	public MPSolver.ResultStatus solve() {
		return solver.solve();
	}

	public int getNumVariables() {
		return solver.numVariables();
	}

	public int getNumConstraints() {
		return solver.numConstraints();
	}

}
