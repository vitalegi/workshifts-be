package it.vitalegi.workshifts.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.google.ortools.linearsolver.MPSolver.ResultStatus;

import it.vitalegi.workshifts.optimization.MPSolverImpl;
import it.vitalegi.workshifts.optimization.model.OptimizationContext;
import it.vitalegi.workshifts.optimization.model.VariableSolution;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class OptimizeWorkShiftService {

	public List<VariableSolution> optimize(OptimizationContext context) {
		
		MPSolverImpl optimizer = new MPSolverImpl();
		
		context.getVariables().forEach(variable -> {
			optimizer.addIntVar(variable.getName(), variable.getMin(), variable.getMax());
		});
		
		context.getConstraints().forEach(constraint -> {
			optimizer.addConstraint(constraint.getName(), constraint.getMin(), constraint.getMax());
			constraint.getCoefficients().forEach(coefficient -> {
				optimizer.addConstraintCoefficient(constraint.getName(), coefficient.getName(),
						coefficient.getCoefficient());
			});
		});

		optimizer.setMaximization();
		context.getObjective().forEach(objective -> {
			optimizer.addObjectiveCoefficient(objective.getName(), objective.getCoefficient());
		});
		
		log.info("Optimize {} variables and {} constraints", optimizer.getNumVariables(),
				optimizer.getNumConstraints());

		ResultStatus resultStatus = optimizer.solve();
		if (resultStatus == ResultStatus.OPTIMAL) {
			log.info("Score: {}", optimizer.getObjective().value());

			List<VariableSolution> solutions = context.getVariables().stream().map(variable -> {
				long value = Math.round(optimizer.getVar(variable.getName()).solutionValue());
				VariableSolution solution = new VariableSolution();
				solution.setName(variable.getName());
				solution.setValue(value);
				return solution;
			}).collect(Collectors.toList());
			return solutions;
		}
		throw new RuntimeException("No solution found");
	}
}