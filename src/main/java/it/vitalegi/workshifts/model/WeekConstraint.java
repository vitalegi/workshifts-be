package it.vitalegi.workshifts.model;

import java.time.DayOfWeek;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class WeekConstraint {

	DayOfWeek dayOfWeek;
	Action action;
	String type;
	int value = 0;
}
