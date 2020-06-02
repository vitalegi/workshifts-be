package it.vitalegi.workshifts.factory;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import it.vitalegi.workshifts.model.Employee;
import it.vitalegi.workshifts.model.EmployeeConstraint;
import it.vitalegi.workshifts.model.SubGroup;

public class EmployeeFactory {

	Employee instance;

	public static EmployeeFactory newInstance() {
		EmployeeFactory factory = new EmployeeFactory();
		factory.instance = new Employee();
		return factory;
	}

	public EmployeeFactory id(int id) {
		instance.setId(id);
		return this;
	}

	public EmployeeFactory fullName(String fullName) {
		instance.setFullName(fullName);
		return this;
	}

	public EmployeeFactory subgroup(SubGroup subgroup) {
		instance.setSubgroup(subgroup);
		return this;
	}

	public EmployeeFactory constraints(EmployeeConstraint... constraints) {
		instance.setConstraints(Arrays.asList(constraints));
		return this;
	}

	public EmployeeFactory constraints(EmployeeConstraintFactory... constraints) {

		return constraints(Arrays.asList(constraints));
	}

	public EmployeeFactory constraints(List<EmployeeConstraintFactory> constraints) {

		instance.setConstraints(
				constraints.stream().map(EmployeeConstraintFactory::build).collect(Collectors.toList()));
		return this;
	}

	public Employee build() {
		return instance;
	}
}
