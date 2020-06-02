package it.vitalegi.workshifts.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import it.vitalegi.workshifts.WorkShiftContext;
import it.vitalegi.workshifts.model.Action;
import it.vitalegi.workshifts.model.Employee;
import it.vitalegi.workshifts.util.ActionUtil;
import it.vitalegi.workshifts.util.ListUtil;

@Service
public class ActionService {

	public List<String> getActions() {
		List<String> actions = new ArrayList<>();
		actions.addAll(ActionUtil.getInstance().getValues(Action.IDLE));
		actions.addAll(ActionUtil.getInstance().getValues(Action.MORNING));
		actions.addAll(ActionUtil.getInstance().getValues(Action.AFTERNOON));
		actions.addAll(ActionUtil.getInstance().getValues(Action.ABSENCE));
		return actions;
	}

	public String getSelectedAction(WorkShiftContext context, Employee employee, LocalDate day) {

		String selectedAction = context.getActionTracker().getAction(employee, day);
		if (selectedAction != null) {
			return selectedAction;
		}
		return ListUtil.first(ActionUtil.getInstance().getValues(Action.IDLE));
	}

}
