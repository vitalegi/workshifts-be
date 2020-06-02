package it.vitalegi.workshifts.service.validation;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import it.vitalegi.workshifts.WorkShiftContext;
import it.vitalegi.workshifts.model.Action;
import it.vitalegi.workshifts.model.ActionTracker;
import it.vitalegi.workshifts.model.Employee;
import it.vitalegi.workshifts.model.ErrorMessage;
import it.vitalegi.workshifts.model.Group;
import it.vitalegi.workshifts.service.EmployeeService;
import it.vitalegi.workshifts.util.BeanUtil;
import it.vitalegi.workshifts.util.DateUtil;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public abstract class GroupMinPresenceValidator extends AbstractValidator {

	protected Action targetAction;

	public List<ErrorMessage> getErrors(WorkShiftContext context, Employee employee, LocalDate date) {

		List<ErrorMessage> errors = initErrors();
		if (employee == null) {
			return errors;
		}

		Group referenceGroup = getGroup(employee);
		List<Employee> employees = getEmployees(context, referenceGroup);

		ActionTracker actionsTracker = context.getActionTracker();
		int numberOfWorkers = 0;
		for (Employee currEmployee : employees) {
			Action action = getAction(actionsTracker, currEmployee, date);
			if (action == targetAction) {
				numberOfWorkers++;
			}
		}

		int minWorkers = getConstraint(referenceGroup, date);
		if (numberOfWorkers < minWorkers) {
			return error(referenceGroup, minWorkers, numberOfWorkers);
		}
		return errors;
	}

	protected Group getGroup(Employee employee) {
		return employee.getSubgroup().getParent();
	}

	protected List<Employee> getEmployees(WorkShiftContext context, Group group) {
		EmployeeService employeeService = BeanUtil.getBean(EmployeeService.class);
		return employeeService.getEmployeeInGroup(context.getEmployees(), group);
	}

	protected List<ErrorMessage> error(Group group, int expectedMinWorkers, int actualWorkers) {

		if (targetAction == Action.MORNING) {
			return Arrays.asList(super.error("Necessarie almeno " + expectedMinWorkers + " persone per "
					+ group.getName() + " la mattina. Trovate " + actualWorkers));
		}
		if (targetAction == Action.AFTERNOON) {
			return Arrays.asList(super.error("Necessarie almeno " + expectedMinWorkers + " persone per "
					+ group.getName() + " il pomeriggio. Trovate " + actualWorkers));
		}
		return Arrays.asList();
	}

	protected int getConstraint(Group group, LocalDate date) {

		if (DateUtil.isWeekDay(date)) {
			if (targetAction == Action.MORNING) {
				return group.getConstraints().getMinWorkersMorningWeekDays();
			}
			if (targetAction == Action.AFTERNOON) {
				return group.getConstraints().getMinWorkersAfternoonWeekDays();
			}
		}
		if (DateUtil.isWeekDay(date)) {
			if (targetAction == Action.MORNING) {
				return group.getConstraints().getMinWorkersMorningWeekends();
			}
			if (targetAction == Action.AFTERNOON) {
				return group.getConstraints().getMinWorkersAfternoonWeekends();
			}
		}
		return 0;
	}
}
