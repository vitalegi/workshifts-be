package it.vitalegi.workshifts.model;

import java.util.List;
import java.util.Objects;
import java.util.function.Function;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@EqualsAndHashCode(of = "id")
public class Employee {

	int id;
	String fullName;
	SubGroup subgroup;
	List<EmployeeConstraint> constraints;

	public <E> boolean equalsField(Employee other, Function<Employee, E> function) {
		if (other == null) {
			return false;
		}
		E value1 = function.apply(this);
		E value2 = function.apply(other);
		return Objects.equals(value1, value2);
	}
}
