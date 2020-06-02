package it.vitalegi.workshifts.service;

import java.time.LocalDate;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.type.TypeReference;

import it.vitalegi.workshifts.accessor.WorkShiftAccessor;
import it.vitalegi.workshifts.exception.InputValidationException;
import it.vitalegi.workshifts.model.ActionTracker;
import it.vitalegi.workshifts.model.CarsConstraint;
import it.vitalegi.workshifts.model.Employee;
import it.vitalegi.workshifts.model.Group;
import it.vitalegi.workshifts.model.SubGroup;
import it.vitalegi.workshifts.util.DateUtil;
import it.vitalegi.workshifts.util.JsonUtil;

@Service
public class HttpRequest2WorkShiftContextService {

	public WorkShiftAccessor convert(HttpServletRequest request) {

		WorkShiftAccessor context = new WorkShiftAccessor();

		context.setFrom(getMandatoryLocalDate(request, "from"));
		context.setTo(getMandatoryLocalDate(request, "to"));
		context.setCarsConstraint(getMandatoryObjectFromJson(request, "cars", CarsConstraint.class));
		context.setGroups(getMandatoryObjectFromJson(request, "groups", new TypeReference<List<Group>>() {
		}));
		context.setSubgroups(getMandatoryObjectFromJson(request, "subgroups", new TypeReference<List<SubGroup>>() {
		}));
		context.setEmployees(getMandatoryObjectFromJson(request, "employees", new TypeReference<List<Employee>>() {
		}));
		Comparator<Employee> employeeComparator = Comparator //
				.comparing((Employee e) -> e.getSubgroup().getParent().getId())//
				.thenComparing(e -> e.getSubgroup().getId()) //
				.thenComparing(Employee::getId);

		Collections.sort(context.getEmployees(), employeeComparator);

		context.setActionTracker(new ActionTracker());

		List<LocalDate> range = DateUtil.range(context.getFrom(), context.getTo());
		for (LocalDate date : range) {
			for (Employee employee : context.getEmployees()) {
				String value = getString(request, "employee_" + employee.getId() + '_' + DateUtil.format(date));
				context.getActionTracker().setAction(employee, date, value);
			}
		}

		return context;
	}

	protected LocalDate getMandatoryLocalDate(HttpServletRequest request, String key) {
		try {
			String value = request.getParameter(key);
			return DateUtil.parse(value);
		} catch (Exception e) {
			throw new InputValidationException("Error on " + key + ": " + e.getMessage(), e);
		}
	}

	protected <E> E getMandatoryObjectFromJson(HttpServletRequest request, String key, Class<E> clazz) {
		try {
			return JsonUtil.getInstance().deserialize(getString(request, key), clazz);
		} catch (Exception e) {
			throw new InputValidationException("Error on " + key + ": " + e.getMessage(), e);
		}
	}

	protected <E> E getMandatoryObjectFromJson(HttpServletRequest request, String key, TypeReference<E> typeReference) {
		try {
			return JsonUtil.getInstance().deserialize(getString(request, key), typeReference);
		} catch (Exception e) {
			throw new InputValidationException("Error on " + key + ": " + e.getMessage(), e);
		}
	}

	protected String getString(HttpServletRequest request, String key) {
		try {
			return request.getParameter(key);
		} catch (Exception e) {
			throw new InputValidationException("Error on " + key + ": " + e.getMessage(), e);
		}
	}
}
