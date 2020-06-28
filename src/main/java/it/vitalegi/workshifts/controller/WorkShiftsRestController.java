package it.vitalegi.workshifts.controller;

import java.io.ByteArrayOutputStream;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import it.vitalegi.workshifts.model.WorkContext;
import it.vitalegi.workshifts.optimization.model.OptimizationContext;
import it.vitalegi.workshifts.optimization.model.VariableSolution;
import it.vitalegi.workshifts.service.ExcelExportServiceFactory;
import it.vitalegi.workshifts.service.OptimizeWorkShiftService;
import it.vitalegi.workshifts.util.logging.LogExecutionTime;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("workshifts-rest")
@Slf4j
public class WorkShiftsRestController {

	@Autowired
	ExcelExportServiceFactory exportExcelServiceFactory;

	@Autowired
	OptimizeWorkShiftService optimizeWorkShiftService;

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

		List<VariableSolution> solutions = optimizeWorkShiftService.optimize(context);
		return new ResponseEntity<List<VariableSolution>>(solutions, HttpStatus.OK);
	}
}
