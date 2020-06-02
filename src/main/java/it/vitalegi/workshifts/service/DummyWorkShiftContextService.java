package it.vitalegi.workshifts.service;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;

import org.springframework.stereotype.Service;

import it.vitalegi.workshifts.WorkShiftContext;
import it.vitalegi.workshifts.accessor.WorkShiftAccessor;
import it.vitalegi.workshifts.factory.CarsConstraintFactory;
import it.vitalegi.workshifts.factory.EmployeeConstraintFactory;
import it.vitalegi.workshifts.factory.EmployeeFactory;
import it.vitalegi.workshifts.factory.GroupConstraintFactory;
import it.vitalegi.workshifts.factory.GroupFactory;
import it.vitalegi.workshifts.factory.SubGroupFactory;
import it.vitalegi.workshifts.model.ActionTracker;
import it.vitalegi.workshifts.model.Employee;

@Service
public class DummyWorkShiftContextService {

	public WorkShiftContext initContext() {
		WorkShiftAccessor context = new WorkShiftAccessor();
		context.setFrom(LocalDate.of(2020, 04, 27));
		context.setTo(LocalDate.of(2020, 05, 31));

		context.setCarsConstraint(CarsConstraintFactory.newInstance().available(3).build());

		context.setGroups(Arrays.asList(//
				GroupFactory.newInstance().id(1).name("Macro Area 1")
						.constraints(GroupConstraintFactory.newInstance(1, 1, 0, 1))//
						.build(), //
				GroupFactory.newInstance().id(2).name("Macro Area 2")
						.constraints(GroupConstraintFactory.newInstance(1, 1, 0, 1))//
						.build() //
		));

		context.setSubgroups(Arrays.asList(//
				SubGroupFactory.newInstance().id(1).name("Area 1").parent(context.getGroup(1))
						.constraints(GroupConstraintFactory.newInstance().minWorkersMorningWeekDays(1))//
						.build(), //
				SubGroupFactory.newInstance().id(2).name("Area 2").parent(context.getGroup(1))
						.constraints(GroupConstraintFactory.newInstance().minWorkersMorningWeekDays(1))//
						.build(), //
				SubGroupFactory.newInstance().id(3).name("Area 3").parent(context.getGroup(2))
						.constraints(GroupConstraintFactory.newInstance().minWorkersMorningWeekDays(1))//
						.build(), ////
				SubGroupFactory.newInstance().id(4).name("Area 4").parent(context.getGroup(2))
						.constraints(GroupConstraintFactory.newInstance().minWorkersMorningWeekDays(1))//
						.build() //
		));

		context.setEmployees(Arrays.asList(//
				EmployeeFactory.newInstance().id(1).fullName("Name 1").subgroup(context.getSubGroup(1))
						.constraints(EmployeeConstraintFactory.newInstance(5, 5, 2).replicateByWeek(context.getFrom(),
								context.getTo())//
						).build(), //
				EmployeeFactory.newInstance().id(2).fullName("Name 2").subgroup(context.getSubGroup(1))
						.constraints(EmployeeConstraintFactory.newInstance(4, 4, 2).replicateByWeek(context.getFrom(),
								context.getTo())//
						).build(), //
				EmployeeFactory.newInstance().id(3).fullName("Name 3").subgroup(context.getSubGroup(1))
						.constraints(EmployeeConstraintFactory.newInstance(5, 5, 2).replicateByWeek(context.getFrom(),
								context.getTo())//
						).build(), //
				EmployeeFactory.newInstance().id(4).fullName("Name 4").subgroup(context.getSubGroup(2))
						.constraints(EmployeeConstraintFactory.newInstance(5, 5, 2).replicateByWeek(context.getFrom(),
								context.getTo())//
						).build(), //
				EmployeeFactory.newInstance().id(5).fullName("Name 5").subgroup(context.getSubGroup(3))
						.constraints(EmployeeConstraintFactory.newInstance(5, 5, 2).replicateByWeek(context.getFrom(),
								context.getTo())//
						).build(), //
				EmployeeFactory.newInstance().id(6).fullName("Name 6").subgroup(context.getSubGroup(4))
						.constraints(EmployeeConstraintFactory.newInstance(5, 5, 2).replicateByWeek(context.getFrom(),
								context.getTo())//
						).build() //
		));

		Comparator<Employee> employeeComparator = Comparator //
				.comparing((Employee e) -> e.getSubgroup().getParent().getId())//
				.thenComparing(e -> e.getSubgroup().getId()) //
				.thenComparing(Employee::getId);

		Collections.sort(context.getEmployees(), employeeComparator);

		context.setActionTracker(new ActionTracker());

		return context;
	}
}
