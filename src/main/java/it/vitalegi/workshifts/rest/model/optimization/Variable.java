package it.vitalegi.workshifts.rest.model.optimization;

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
