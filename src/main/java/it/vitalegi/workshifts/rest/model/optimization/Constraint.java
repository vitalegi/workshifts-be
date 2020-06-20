package it.vitalegi.workshifts.rest.model.optimization;

import java.util.List;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class Constraint {
	String name;
	int max;
	int min;
	List<Coefficient> coefficients;
}
