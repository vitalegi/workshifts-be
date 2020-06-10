package it.vitalegi.workshifts.rest.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@EqualsAndHashCode(of = "id")
public class Employee {

	int id;
	String name;
	int subgroupId;
	int totWeekShifts;
	int maxWeekMornings;
	int maxWeekAfternoons;
}
