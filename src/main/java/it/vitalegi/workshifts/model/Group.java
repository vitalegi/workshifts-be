package it.vitalegi.workshifts.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@EqualsAndHashCode(of = "id")
public class Group {

	int id;
	String name;
	GroupConstraint constraints;
}
