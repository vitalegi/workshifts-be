package it.vitalegi.workshifts.model;

import java.time.LocalDate;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class EmployeeConstraint {

	LocalDate startDay;
	LocalDate endDay;
	int totWorkShift;
	int maxMornings;
	int maxAfternoons;
}
