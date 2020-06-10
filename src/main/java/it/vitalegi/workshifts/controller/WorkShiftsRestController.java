package it.vitalegi.workshifts.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import it.vitalegi.workshifts.factory.EmployeeFactory;
import it.vitalegi.workshifts.rest.model.WorkContext;
import it.vitalegi.workshifts.service.ExcelExportService;
import it.vitalegi.workshifts.service.ExcelExportServiceFactory;
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
		exportExcelServiceFactory.getService(context).export(null);
		byte[] payload = EmployeeFactory.newInstance().fullName("AA").id(1).build().toString().getBytes();
		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-disposition", "attachment; filename=hello.xlsx");
		headers.add("Content-Type", "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
		return new ResponseEntity<byte[]>(payload, headers, HttpStatus.OK);
	}
}
