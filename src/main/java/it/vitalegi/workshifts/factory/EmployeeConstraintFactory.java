package it.vitalegi.workshifts.factory;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import it.vitalegi.workshifts.model.EmployeeConstraint;
import it.vitalegi.workshifts.util.DateUtil;
import it.vitalegi.workshifts.util.ListUtil;

public class EmployeeConstraintFactory {

	EmployeeConstraint instance;

	public static EmployeeConstraintFactory newInstance() {
		EmployeeConstraintFactory factory = new EmployeeConstraintFactory();
		factory.instance = new EmployeeConstraint();
		return factory;
	}

	public static EmployeeConstraintFactory newInstance(int totWorkShift, int maxMornings, int maxAfternoons) {
		EmployeeConstraintFactory factory = new EmployeeConstraintFactory();
		factory.instance = new EmployeeConstraint();
		factory.maxMornings(maxMornings).maxAfternoons(maxAfternoons).totWorkShift(totWorkShift);
		return factory;
	}

	public EmployeeConstraintFactory clone() {
		return newInstance(instance.getTotWorkShift(), instance.getMaxMornings(), instance.getMaxAfternoons());
	}

	public EmployeeConstraint build() {
		return instance;
	}

	public List<EmployeeConstraintFactory> replicateByWeek(LocalDate from, LocalDate to) {
		List<List<LocalDate>> ranges = DateUtil.splitRangeSunday(from, to);
		List<EmployeeConstraintFactory> constraints = new ArrayList<>();
		for (List<LocalDate> range : ranges) {
			EmployeeConstraintFactory clone = clone();
			clone.startDay(ListUtil.first(range));
			clone.endDay(ListUtil.last(range));
			constraints.add(clone);
		}
		return constraints;
	}

	public EmployeeConstraintFactory startDay(LocalDate startDay) {
		instance.setStartDay(startDay);
		return this;
	}

	public EmployeeConstraintFactory endDay(LocalDate endDay) {
		instance.setEndDay(endDay);
		return this;
	}

	public EmployeeConstraintFactory maxMornings(int maxMornings) {
		instance.setMaxMornings(maxMornings);
		return this;
	}

	public EmployeeConstraintFactory maxAfternoons(int maxAfternoons) {
		instance.setMaxAfternoons(maxAfternoons);
		return this;
	}

	public EmployeeConstraintFactory totWorkShift(int totWorkShift) {
		instance.setTotWorkShift(totWorkShift);
		return this;
	}
}
