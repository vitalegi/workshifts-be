package it.vitalegi.workshifts.model;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

public class ActionTracker {

	Map<String, String> selectedValues;

	public ActionTracker() {
		selectedValues = new HashMap<>();
	}

	public void setAction(Employee employee, LocalDate day, String value) {
		selectedValues.put(getKey(employee, day), value);
	}

	public String getAction(Employee employee, LocalDate day, String defaultValue) {
		String value = getAction(employee, day);
		if (value == null) {
			return defaultValue;
		}
		return value;
	}

	public String getAction(Employee employee, LocalDate day) {
		return selectedValues.get(getKey(employee, day));
	}

	protected String getKey(Employee employee, LocalDate day) {
		return employee.getId() + "_" + day;
	}
}
