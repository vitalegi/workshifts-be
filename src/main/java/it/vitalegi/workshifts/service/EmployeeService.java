package it.vitalegi.workshifts.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import it.vitalegi.workshifts.WorkShiftContext;
import it.vitalegi.workshifts.model.Employee;
import it.vitalegi.workshifts.model.Group;
import it.vitalegi.workshifts.model.SubGroup;

@Service
public class EmployeeService {

	public Employee getEmployee(WorkShiftContext context, int id) {
		return context.getEmployees().stream().filter(a -> a.getId() == id).findFirst().orElse(null);
	}

	public long getGroupSize(WorkShiftContext context, Group group) {
		return context.getEmployees().stream() //
				.map(Employee::getSubgroup) //
				.map(SubGroup::getParent) //
				.map(Group::getId) //
				.filter(id -> id == group.getId()) //
				.count();
	}

	public long getSubGroupSize(WorkShiftContext context, Group group) {
		return context.getEmployees().stream() //
				.map(Employee::getSubgroup) //
				.map(Group::getId) //
				.filter(id -> id == group.getId()) //
				.count();
	}

	public int getEmployeeIndex(WorkShiftContext context, Employee employee) {
		for (int i = 0; i < context.getEmployees().size(); i++) {
			if (context.getEmployees().get(i).equals(employee)) {
				return i;
			}
		}
		return -1;
	}

	public Employee getEmployeePredecessor(WorkShiftContext context, Employee employee) {
		int index = getEmployeeIndex(context, employee);
		if (index == -1) {
			return null;
		}
		if (index == 0) {
			return null;
		}
		return context.getEmployees().get(index - 1);
	}

	public Employee getEmployeeSuccessor(WorkShiftContext context, Employee employee) {
		int index = getEmployeeIndex(context, employee);
		if (index == -1) {
			return null;
		}
		if (index == context.getEmployees().size() - 1) {
			return null;
		}
		return context.getEmployees().get(index + 1);
	}

	public List<Employee> getEmployeeInGroup(List<Employee> employees, Group targetGroup) {
		return employees.stream() //
				.filter(e -> e.getSubgroup().getParent().equals(targetGroup)) //
				.collect(Collectors.toList());
	}

	public List<Employee> getEmployeeInSubGroup(List<Employee> employees, SubGroup targetGroup) {
		return employees.stream() //
				.filter(e -> e.getSubgroup().equals(targetGroup)) //
				.collect(Collectors.toList());
	}
}
