package it.vitalegi.workshifts.controller;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

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
import it.vitalegi.workshifts.service.DummyWorkShiftContextService;
import it.vitalegi.workshifts.service.HttpRequest2WorkShiftContextService;
import it.vitalegi.workshifts.util.DateUtil;
import it.vitalegi.workshifts.util.logging.LogExecutionTime;
import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class WorkShiftsController {

	@Autowired
	HttpRequest2WorkShiftContextService converter;

	@Autowired
	DummyWorkShiftContextService dummyContext;

	@GetMapping("/workshifts")
	@LogExecutionTime
	public String getWorkShifts(Model model) {
		WorkShiftContext context = dummyContext.initContext();
		model.addAttribute("ws", context);
		model.addAttribute("range", DateUtil.range(context.getFrom(), context.getTo()));

		return "workshifts";
	}

	@PostMapping("/workshifts")
	@LogExecutionTime
	public String updateWorkshifts(Model model, HttpServletRequest request) {

		WorkShiftContext context = converter.convert(request);
		model.addAttribute("ws", context);
		model.addAttribute("range", DateUtil.range(context.getFrom(), context.getTo()));

		return "workshifts";
	}

	@PostMapping("/workshifts/add")
	@LogExecutionTime
	public ModelAndView addStock(Model model, String stockId, String date, BigDecimal price, int quantity) {

		return new ModelAndView("redirect:/workshifts");
	}
	
	@GetMapping("/workshifts2")
	@LogExecutionTime
	public ModelAndView getWorkShifts2(Model model) {
		
		return new ModelAndView("redirect:/static/ws/index.html");
	}
}
