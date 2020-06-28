package it.vitalegi.workshifts.controller;

import java.io.ByteArrayOutputStream;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.google.ortools.linearsolver.MPSolver.ResultStatus;

import it.vitalegi.workshifts.optimizer.MPSolverImpl;
import it.vitalegi.workshifts.rest.model.WorkContext;
import it.vitalegi.workshifts.rest.model.optimization.OptimizationContext;
import it.vitalegi.workshifts.rest.model.optimization.VariableSolution;
import it.vitalegi.workshifts.service.ExcelExportServiceFactory;
import it.vitalegi.workshifts.util.logging.LogExecutionTime;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("workshifts-rest")
@Slf4j
public class WorkShiftsRestController {

	@Autowired
	ExcelExportServiceFactory exportExcelServiceFactory;
	
	@PostMapping(value = "/export", produces = MediaType.APPLICATION_OCTET_STREAM_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<byte[]> exportExcel(@RequestBody WorkContext context) {
		log.info("Request: " + context);
		ByteArrayOutputStream os = new ByteArrayOutputStream();
		exportExcelServiceFactory.getService(context).export(os);
		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-disposition", "attachment; filename=hello.xlsx");
		headers.add("Content-Type", "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
		return new ResponseEntity<byte[]>(os.toByteArray(), headers, HttpStatus.OK);
	}

	@PostMapping(value = "/optimize", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	@LogExecutionTime
	public ResponseEntity<?> optimize(@RequestBody OptimizationContext context) {
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
			return new ResponseEntity<List<VariableSolution>>(solutions, HttpStatus.OK);
		}
		log.info("No solution found");
		return new ResponseEntity<Object>("No solution found", HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
