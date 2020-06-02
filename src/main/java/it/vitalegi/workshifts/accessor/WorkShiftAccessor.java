package it.vitalegi.workshifts.accessor;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

import it.vitalegi.workshifts.WorkShiftContext;
import it.vitalegi.workshifts.model.Action;
import it.vitalegi.workshifts.model.Employee;
import it.vitalegi.workshifts.model.Group;
import it.vitalegi.workshifts.model.SubGroup;
import it.vitalegi.workshifts.service.ActionService;
import it.vitalegi.workshifts.service.CarService;
import it.vitalegi.workshifts.service.EmployeeService;
import it.vitalegi.workshifts.service.GroupService;
import it.vitalegi.workshifts.service.validation.ValidateWorkShiftsService;
import it.vitalegi.workshifts.util.BeanUtil;
import it.vitalegi.workshifts.util.DateUtil;

public class WorkShiftAccessor extends WorkShiftContext {

	protected EmployeeService getEmployeeService() {
		return BeanUtil.getBean(EmployeeService.class);
	}

	protected GroupService getGroupService() {
		return BeanUtil.getBean(GroupService.class);
	}

	protected CarService getCarService() {
		return BeanUtil.getBean(CarService.class);
	}

	protected ActionService getActionService() {
		return BeanUtil.getBean(ActionService.class);
	}

	public SubGroup getSubGroup(int id) {
		return getGroupService().getSubGroup(this, id);
	}

	public Group getGroup(int id) {
		return getGroupService().getGroup(this, id);
	}

	public Employee getEmployee(int id) {
		return getEmployeeService().getEmployee(this, id);
	}

	public long getGroupSize(Group group) {
		return getEmployeeService().getGroupSize(this, group);
	}

	public long getSubGroupSize(Group group) {
		return getEmployeeService().getSubGroupSize(this, group);
	}

	public List<String> getActions() {
		return getActionService().getActions();
	}

	public String getSelectedAction(Employee employee, LocalDate day) {
		return getActionService().getSelectedAction(this, employee, day);
	}

	public int getAvailableCars(LocalDate day) {
		return getCarService().getAvailableCars(this, day);
	}

	public String getDayOfWeekCode(LocalDate date) {
		switch (date.getDayOfWeek()) {
		case MONDAY:
			return "L";
		case TUESDAY:
			return "M";
		case WEDNESDAY:
			return "M";
		case THURSDAY:
			return "G";
		case FRIDAY:
			return "V";
		case SATURDAY:
			return "S";
		case SUNDAY:
			return "D";
		default:
			return "-";
		}
	}

	public String getTdCssClass(LocalDate date) {
		return getTdCssClass(null, date);
	}

	public String getTdCssClass(Employee employee, LocalDate date) {
		List<String> classSelectors = new ArrayList<>();
		if (DateUtil.getFirstDayOfWeek(date).equals(date)) {
			classSelectors.add("solid-left-border");
		}
		if (DateUtil.getLastDayOfWeek(date).equals(date)) {
			classSelectors.add("solid-right-border");
		}
		if (employee != null) {
			Employee predecessor = getEmployeeService().getEmployeePredecessor(this, employee);
			Employee successor = getEmployeeService().getEmployeeSuccessor(this, employee);

			Function<Employee, Object> getGroup = (e) -> e.getSubgroup().getParent();
			Function<Employee, Object> getSubGroup = (e) -> e.getSubgroup();

			boolean changedPredecessorGroup = !employee.equalsField(predecessor, getGroup);
			boolean changedPredecessorSubGroup = !employee.equalsField(predecessor, getSubGroup);
			boolean changedSuccessorGroup = !employee.equalsField(successor, getGroup);
			boolean changedSuccessorSubGroup = !employee.equalsField(successor, getSubGroup);

			if (changedPredecessorGroup) {
				classSelectors.add("group-top-border");
			} else if (predecessor != null && changedPredecessorSubGroup) {
				classSelectors.add("subgroup-top-border");
			}
			if (changedSuccessorGroup) {
				classSelectors.add("group-bottom-border");
			} else if (successor != null && changedSuccessorSubGroup) {
				classSelectors.add("subgroup-bottom-border");
			}
		}
		return classSelectors.stream().collect(Collectors.joining(" "));
	}

	public String getCarsTdCssClass(LocalDate date) {
		StringBuilder sb = new StringBuilder();
		sb.append(getTdCssClass(date));
		sb.append(" ");
		if (BeanUtil.getBean(ValidateWorkShiftsService.class).hasErrors(this, null, date)) {
			sb.append("warning");
		}
		return sb.toString();
	}

	public int getUsedCars(LocalDate date, Action action) {
		return getCarService().getUsedCars(this, date, action);
	}
}
