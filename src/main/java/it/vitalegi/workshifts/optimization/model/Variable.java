package it.vitalegi.workshifts.optimization.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class Variable {
	String name;
	int max;
	int min;
}
