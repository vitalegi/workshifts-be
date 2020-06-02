package it.vitalegi.workshifts.factory;

import it.vitalegi.workshifts.model.GroupConstraint;
import it.vitalegi.workshifts.model.Group;

public class GroupFactory {

	Group instance;

	public static GroupFactory newInstance() {
		GroupFactory factory = new GroupFactory();
		factory.instance = new Group();
		return factory;
	}

	public GroupFactory id(int id) {
		instance.setId(id);
		return this;
	}

	public GroupFactory name(String name) {
		instance.setName(name);
		return this;
	}

	public GroupFactory constraints(GroupConstraint constraints) {
		instance.setConstraints(constraints);
		return this;
	}

	public GroupFactory constraints(GroupConstraintFactory constraints) {
		instance.setConstraints(constraints.build());
		return this;
	}

	public Group build() {
		return instance;
	}

}
