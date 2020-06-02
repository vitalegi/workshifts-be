package it.vitalegi.workshifts.util;

import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import it.vitalegi.workshifts.exception.ConfigurationException;
import it.vitalegi.workshifts.model.Action;

@Service
public class ActionUtil {

	@Autowired
	Environment properties;

	protected static ActionUtil _instance;

	@PostConstruct
	void init() {
		_instance = this;
	}

	public static ActionUtil getInstance() {
		return _instance;
	}

	public List<String> getValues(Action action) {
		String key = getName(action);

		@SuppressWarnings("unchecked")
		List<String> values = properties.getProperty(key, List.class);
		if (ListUtil.isNullOrEmpty(values)) {
			throw new ConfigurationException("Property " + key + " not set correctly");
		}
		return values;
	}

	public Action getAction(String value) {
		for (Action action : Action.values()) {
			if (getValues(action).stream().anyMatch(s -> s.equals(value))) {
				return action;
			}
		}
		return null;
	}

	protected String getName(Action action) {
		return "actions." + action.name();
	}
}
