package it.vitalegi.workshifts.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class SubGroup extends Group {

	Group parent;
}
