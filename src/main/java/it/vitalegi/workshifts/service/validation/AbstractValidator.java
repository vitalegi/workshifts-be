package it.vitalegi.workshifts.service.validation;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import it.vitalegi.workshifts.WorkShiftContext;
import it.vitalegi.workshifts.model.Action;
import it.vitalegi.workshifts.model.ActionTracker;
import it.vitalegi.workshifts.model.Employee;
import it.vitalegi.workshifts.model.EmployeeConstraint;
import it.vitalegi.workshifts.model.ErrorMessage;
import it.vitalegi.workshifts.util.ActionUtil;
import it.vitalegi.workshifts.util.DateUtil;
import it.vitalegi.workshifts.util.ListUtil;

public abstract class AbstractValidator {

	public abstract List<ErrorMessage> getErrors(WorkShiftContext context, Employee employee, LocalDate date);

	public boolean hasErrors(WorkShiftContext context, Employee employee, LocalDate date) {
		List<ErrorMessage> errors = getErrors(context, employee, date);
		if (ListUtil.isNullOrEmpty(errors)) {
			return false;
		}
		return true;
	}

	protected List<ErrorMessage> initErrors() {
		return new ArrayList<>();
	}

	protected ErrorMessage error(String message) {
		ErrorMessage msg = new ErrorMessage();
		msg.setMessage(message);
		return msg;
	}

	protected LocalDate getFirstDayOfWeek(LocalDate date) {
		return DateUtil.getFirstDayOfWeek(date);
	}

	protected LocalDate getLastDayOfWeek(LocalDate date) {
		return DateUtil.getLastDayOfWeek(date);
	}

	protected List<LocalDate> getWeek(LocalDate date) {
		return DateUtil.range(getFirstDayOfWeek(date), getLastDayOfWeek(date));
	}

	protected Action getAction(ActionTracker actionsTracker, Employee employee, LocalDate date) {
		String action = actionsTracker.getAction(employee, date);
		return ActionUtil.getInstance().getAction(action);
	}

	protected EmployeeConstraint getEmployeeConstraint(Employee employee, LocalDate date) {
		LocalDate startOfWeek = getFirstDayOfWeek(date);
		LocalDate endOfWeek = getLastDayOfWeek(date);

		List<EmployeeConstraint> constraints = employee.getConstraints().stream()//
				.filter(c -> c.getStartDay().compareTo(startOfWeek) >= 0) //
				.filter(c -> c.getEndDay().compareTo(endOfWeek) <= 0)//
				.collect(Collectors.toList());

		if (ListUtil.isNullOrEmpty(constraints)) {
			return null;
		}

		if (constraints.size() > 1) {
			throw new IllegalArgumentException("Wrong configuration, " + constraints.size()
					+ " constraints available, 1 expected. " + employee + " " + date);
		}
		return constraints.get(0);
	}
}
