package it.vitalegi.workshifts.service;

import org.springframework.stereotype.Service;

import it.vitalegi.workshifts.WorkShiftContext;
import it.vitalegi.workshifts.model.Group;
import it.vitalegi.workshifts.model.SubGroup;

@Service
public class GroupService {

	public SubGroup getSubGroup(WorkShiftContext context, int id) {
		return context.getSubgroups().stream().filter(a -> a.getId() == id).findFirst().orElse(null);
	}

	public Group getGroup(WorkShiftContext context, int id) {
		return context.getGroups().stream().filter(a -> a.getId() == id).findFirst().orElse(null);
	}

}
