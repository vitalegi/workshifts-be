package it.vitalegi.workshifts.service.validation;

import java.time.LocalDate;
import java.util.List;

import it.vitalegi.workshifts.WorkShiftContext;
import it.vitalegi.workshifts.model.Action;
import it.vitalegi.workshifts.model.ActionTracker;
import it.vitalegi.workshifts.model.Employee;
import it.vitalegi.workshifts.model.EmployeeConstraint;
import it.vitalegi.workshifts.model.ErrorMessage;

public class EmployeeMaxShiftsPerWeekValidator extends AbstractValidator {

	public List<ErrorMessage> getErrors(WorkShiftContext context, Employee employee, LocalDate date) {

		List<ErrorMessage> errors = initErrors();
		if (employee == null) {
			return errors;
		}
		List<LocalDate> range = getWeek(date);

		ActionTracker actionsTracker = context.getActionTracker();
		int count = 0;
		for (LocalDate day : range) {
			Action action = getAction(actionsTracker, employee, day);
			if (action == Action.MORNING || action == Action.AFTERNOON || action == Action.ABSENCE) {
				count++;
			}
		}
		EmployeeConstraint constraint = getEmployeeConstraint(employee, date);
		if (constraint != null && count != constraint.getTotWorkShift()) {
			errors.add(error("Expected " + constraint.getTotWorkShift() + " turni, actual " + count));
		}
		return errors;
	}
}
