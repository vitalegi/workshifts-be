package it.vitalegi.workshifts.rest.model.optimization;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Objective {

	String name;
	int coefficient;
}