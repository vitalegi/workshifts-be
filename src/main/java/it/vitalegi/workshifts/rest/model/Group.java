package it.vitalegi.workshifts.rest.model;

import java.util.List;

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
	List<WeekConstraint> constraints;
}
