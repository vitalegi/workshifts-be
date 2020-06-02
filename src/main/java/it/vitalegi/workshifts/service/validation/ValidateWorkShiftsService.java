package it.vitalegi.workshifts.service.validation;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import it.vitalegi.workshifts.WorkShiftContext;
import it.vitalegi.workshifts.model.Employee;
import it.vitalegi.workshifts.model.ErrorMessage;
import it.vitalegi.workshifts.util.ListUtil;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class ValidateWorkShiftsService {

	public List<ErrorMessage> getErrors(WorkShiftContext context, Employee employee, LocalDate date) {

		List<AbstractValidator> validators = Arrays.asList(//
				new EmployeeMaxShiftsPerWeekValidator(), //
				new EmployeeMaxMorningsPerWeekValidator(), //
				new EmployeeMaxAfternoonsPerWeekValidator(), //
				new CarsAvailabilityMorningValidator(), //
				new CarsAvailabilityAfternoonValidator());

		return validators.stream()//
				.flatMap(v -> v.getErrors(context, employee, date).stream())//
				.collect(Collectors.toList());
	}

	public String getErrorsHtml(WorkShiftContext context, Employee employee, LocalDate date) {

		List<ErrorMessage> errors = getErrors(context, employee, date);
		if (ListUtil.isNullOrEmpty(errors)) {
			return "";
		}
		return errors.stream().map(ErrorMessage::getMessage).collect(Collectors.joining("</li><li>", "<li>", "</li>"));
	}

	public boolean hasErrors(WorkShiftContext context, Employee employee, LocalDate date) {

		List<ErrorMessage> errors = getErrors(context, employee, date);
		if (ListUtil.isNullOrEmpty(errors)) {
			return false;
		}
		return true;
	}

}
