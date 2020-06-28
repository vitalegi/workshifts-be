package it.vitalegi.workshifts.model;

import java.time.LocalDate;
import java.util.List;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class WorkContext {

	private LocalDate date;
	private List<Employee> employees;
	private List<Group> groups;
	private List<Subgroup> subgroups;
	private int availableCars;
	private List<Shift> shifts;
}
