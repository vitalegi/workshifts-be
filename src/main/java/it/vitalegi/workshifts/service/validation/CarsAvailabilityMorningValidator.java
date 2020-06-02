package it.vitalegi.workshifts.service.validation;

import java.time.LocalDate;
import java.util.List;

import it.vitalegi.workshifts.WorkShiftContext;
import it.vitalegi.workshifts.model.Action;
import it.vitalegi.workshifts.model.ActionTracker;
import it.vitalegi.workshifts.model.Employee;
import it.vitalegi.workshifts.model.ErrorMessage;

public class CarsAvailabilityMorningValidator extends AbstractValidator {

	public List<ErrorMessage> getErrors(WorkShiftContext context, Employee employee, LocalDate date) {

		List<ErrorMessage> errors = initErrors();

		ActionTracker actionsTracker = context.getActionTracker();
		int count = 0;
		for (Employee curr : context.getEmployees()) {
			Action action = getAction(actionsTracker, curr, date);
			if (action == Action.MORNING) {
				count++;
			}
		}
		int max = context.getCarsConstraint().getAvailable();
		if (count > max) {
			errors.add(error("Max " + max + " auto al mattino, selezionate " + count));
		}
		return errors;
	}
}
