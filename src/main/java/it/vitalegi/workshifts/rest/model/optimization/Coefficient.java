package it.vitalegi.workshifts.rest.model.optimization;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString(callSuper = true)
public class Coefficient extends Variable {
	int coefficient;
}
