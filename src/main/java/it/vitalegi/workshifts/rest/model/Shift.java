package it.vitalegi.workshifts.rest.model;

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
