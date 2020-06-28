package it.vitalegi.workshifts.model;

import java.time.LocalDate;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Shift {

	int employeeId;
	LocalDate date;
	String value;
}
