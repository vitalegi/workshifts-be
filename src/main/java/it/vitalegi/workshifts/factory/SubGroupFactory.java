package it.vitalegi.workshifts.factory;

import it.vitalegi.workshifts.model.SubGroup;
import it.vitalegi.workshifts.model.GroupConstraint;
import it.vitalegi.workshifts.model.Group;

public class SubGroupFactory {

	SubGroup instance;

	public static SubGroupFactory newInstance() {
		SubGroupFactory factory = new SubGroupFactory();
		factory.instance = new SubGroup();
		return factory;
	}

	public SubGroupFactory id(int id) {
		instance.setId(id);
		return this;
	}

	public SubGroupFactory name(String name) {
		instance.setName(name);
		return this;
	}

	public SubGroupFactory parent(Group parent) {
		instance.setParent(parent);
		return this;
	}

	public SubGroupFactory constraints(GroupConstraint constraints) {
		instance.setConstraints(constraints);
		return this;
	}

	public SubGroupFactory constraints(GroupConstraintFactory constraints) {
		instance.setConstraints(constraints.build());
		return this;
	}

	public SubGroup build() {
		return instance;
	}
}
