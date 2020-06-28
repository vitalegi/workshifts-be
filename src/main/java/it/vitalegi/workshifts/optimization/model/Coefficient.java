package it.vitalegi.workshifts.optimization.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString(callSuper = true)
public class Coefficient extends Variable {
	int coefficient;
}
