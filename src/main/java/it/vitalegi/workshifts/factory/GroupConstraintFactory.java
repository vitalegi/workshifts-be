package it.vitalegi.workshifts.factory;

import it.vitalegi.workshifts.model.GroupConstraint;

public class GroupConstraintFactory {

	GroupConstraint instance;

	public static GroupConstraintFactory newInstance() {
		GroupConstraintFactory factory = new GroupConstraintFactory();
		factory.instance = new GroupConstraint();
		return factory;
	}

	public static GroupConstraintFactory newInstance(int minWorkersAfternoonWeekDays, int minWorkersAfternoonWeekends,
			int minWorkersMorningWeekDays, int minWorkersMorningWeekends) {
		GroupConstraintFactory factory = new GroupConstraintFactory();
		factory.instance = new GroupConstraint();
		factory.minWorkersAfternoonWeekDays(minWorkersAfternoonWeekDays)
				.minWorkersAfternoonWeekends(minWorkersAfternoonWeekends)
				.minWorkersMorningWeekDays(minWorkersMorningWeekDays)
				.minWorkersMorningWeekends(minWorkersMorningWeekends);
		return factory;
	}

	public GroupConstraint build() {
		return instance;
	}

	public GroupConstraintFactory minWorkersAfternoonWeekDays(int minWorkersAfternoonWeekDays) {
		instance.setMinWorkersAfternoonWeekDays(minWorkersAfternoonWeekDays);
		return this;
	}

	public GroupConstraintFactory minWorkersAfternoonWeekends(int minWorkersAfternoonWeekends) {
		instance.setMinWorkersAfternoonWeekends(minWorkersAfternoonWeekends);
		return this;
	}

	public GroupConstraintFactory minWorkersMorningWeekDays(int minWorkersMorningWeekDays) {
		instance.setMinWorkersMorningWeekDays(minWorkersMorningWeekDays);
		return this;
	}

	public GroupConstraintFactory minWorkersMorningWeekends(int minWorkersMorningWeekends) {
		instance.setMinWorkersMorningWeekends(minWorkersMorningWeekends);
		return this;
	}
}
