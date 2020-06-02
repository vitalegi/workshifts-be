package it.vitalegi.workshifts.factory;

import it.vitalegi.workshifts.model.CarsConstraint;

public class CarsConstraintFactory {

	CarsConstraint instance;

	public static CarsConstraintFactory newInstance() {
		CarsConstraintFactory factory = new CarsConstraintFactory();
		factory.instance = new CarsConstraint();
		return factory;
	}

	public CarsConstraintFactory available(int available) {
		instance.setAvailable(available);
		return this;
	}

	public CarsConstraint build() {
		return instance;
	}
}
