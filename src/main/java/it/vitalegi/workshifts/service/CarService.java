package it.vitalegi.workshifts.service;

import java.time.LocalDate;

import org.springframework.stereotype.Service;

import it.vitalegi.workshifts.WorkShiftContext;
import it.vitalegi.workshifts.model.Action;
import it.vitalegi.workshifts.model.ActionTracker;
import it.vitalegi.workshifts.model.Employee;
import it.vitalegi.workshifts.util.ActionUtil;

@Service
public class CarService {

	public int getAvailableCars(WorkShiftContext context, LocalDate date) {
		return context.getCarsConstraint().getAvailable();
	}

	public int getUsedCars(WorkShiftContext context, LocalDate date, Action action) {
		ActionTracker actionsTracker = context.getActionTracker();
		int count = 0;
		for (Employee curr : context.getEmployees()) {
			Action currAction = getAction(actionsTracker, curr, date);
			if (action == currAction) {
				count++;
			}
		}
		return count;
	}

	protected Action getAction(ActionTracker actionsTracker, Employee employee, LocalDate date) {
		String action = actionsTracker.getAction(employee, date);
		return ActionUtil.getInstance().getAction(action);
	}

}
