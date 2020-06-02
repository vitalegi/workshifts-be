package it.vitalegi.workshifts.optimizer;

import java.time.LocalDate;

import org.springframework.stereotype.Service;

import it.vitalegi.workshifts.model.Action;
import it.vitalegi.workshifts.model.Employee;
import it.vitalegi.workshifts.model.Group;

@Service
public class GetWorkShiftIdentifierService {

	public String varEmployeeDailyAction(Employee employee, LocalDate day, Action action) {
		return String.format("v_res_%d_day_%d_%s", employee.getId(), day.toString(), action.name());
	}

	public String employeeDailyConstraint(Employee employee, LocalDate day) {
		return String.format("c_res_%d_day_%d", employee.getId(), day.toString());
	}

	public String employeeConstraintDailyFixedAction(Employee employee, LocalDate day, Action action) {
		return String.format("c_res_%d_day_%d_action_%s", employee.getId(), day.toString(), action.name());
	}

	public String employeeConstraintMaxActionsInRangeByType(Employee employee, LocalDate fromDay, LocalDate toDay,
			Action action) {
		return String.format("c_res_%d_days_%d_%d_action_%s", employee, fromDay, toDay, action.name());
	}

	public String employeeConstraintMaxShiftsInRange(Employee employee, LocalDate fromDay, LocalDate toDay) {
		return String.format("c_res_%d_days_%d_%d_max_actions", employee.getId(), fromDay, toDay);
	}

	public String minGroupWorkShiftsInRange(Group group, LocalDate fromDay, LocalDate toDay, Action action) {
		return String.format("c_group_%d_days_%d_%d_min_%s", group.getId(), fromDay, toDay, action.name());
	}
}
