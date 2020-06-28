package it.vitalegi.workshifts.service;

import org.springframework.stereotype.Service;

import it.vitalegi.workshifts.model.WorkContext;

@Service
public class ExcelExportServiceFactory {

	public ExcelExportService getService(WorkContext context) {
		ExcelExportService service = new ExcelExportService();
		service.setContext(context);
		return service;
	}
}
