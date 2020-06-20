package it.vitalegi.workshifts.rest.model.optimization;

import java.util.List;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class OptimizationContext {

	List<Constraint> constraints;
	List<Variable> variables;
	List<Objective> objective;
}
