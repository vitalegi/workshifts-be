package it.vitalegi.workshifts;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

import it.vitalegi.workshifts.model.Action;
import it.vitalegi.workshifts.model.ActionTracker;
import it.vitalegi.workshifts.model.CarsConstraint;
import it.vitalegi.workshifts.model.Employee;
import it.vitalegi.workshifts.model.Group;
import it.vitalegi.workshifts.model.SubGroup;
import it.vitalegi.workshifts.util.ActionUtil;
import it.vitalegi.workshifts.util.DateUtil;
import it.vitalegi.workshifts.util.ListUtil;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class WorkShiftContext {

	LocalDate from;
	LocalDate to;
	List<Group> groups;
	List<SubGroup> subgroups;
	List<Employee> employees;
	ActionTracker actionTracker;
	CarsConstraint carsConstraint;

	public WorkShiftContext() {
		groups = new ArrayList<>();
		subgroups = new ArrayList<>();
		employees = new ArrayList<>();
	}
}
