package it.vitalegi.workshifts.service;

import java.io.IOException;
import java.io.OutputStream;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalField;
import java.time.temporal.WeekFields;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;
import java.util.function.Consumer;
import java.util.stream.Collectors;

import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import it.vitalegi.workshifts.rest.model.Employee;
import it.vitalegi.workshifts.rest.model.Group;
import it.vitalegi.workshifts.rest.model.Shift;
import it.vitalegi.workshifts.rest.model.Subgroup;
import it.vitalegi.workshifts.rest.model.WorkContext;
import it.vitalegi.workshifts.util.DateUtil;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ExcelExportService {
	private static final String NONE = "NONE";
	private static final String THIN = "THIN";
	private static final String MEDIUM = "MEDIUM";

	WorkContext context;
	XSSFWorkbook workbook;
	XSSFSheet sheet;

	public void setContext(WorkContext context) {
		this.context = context;
	}

	public void export(OutputStream os) {

		workbook = new XSSFWorkbook();
		sheet = workbook.createSheet("Template");

		exportSheet();
		try {
			workbook.write(os);
			workbook.close();
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	protected void exportSheet() {

		createHeaderTitle();
		createHeaderNumberDays();
		createHeaderNameDays();
		createEmployees();

		sheet.setColumnWidth(0, 2000);
		sheet.autoSizeColumn(1);

		List<LocalDate> range = range();
		for (int i = 0; i < range.size(); i++) {
			sheet.setColumnWidth(2 + i, 900);
		}
	}

	protected void createHeaderTitle() {
		XSSFRow row = sheet.createRow(0);
		row.setHeightInPoints(37.75f);

		int columns = 1 + range().size();
		sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, columns));

		XSSFCell cell = row.createCell(0);
		CellStyle style = workbook.createCellStyle();
		style.setFont(fontNormal(true));
		style.setAlignment(HorizontalAlignment.CENTER);
		style.setVerticalAlignment(VerticalAlignment.CENTER);
		setStringCell(cell, "TURNO INFERMIERI ADI DISTRETTO SUD " + context.getDate().getYear(), style);

	}

	protected void createHeaderNumberDays() {
		XSSFRow row = sheet.createRow(1);
		row.setHeightInPoints(18.0f);

		CellStyle style = new StyleFactory().top(THIN).bottom(MEDIUM).left(THIN).right(THIN).build();
		style.setFont(fontSmall());
		style.setAlignment(HorizontalAlignment.CENTER);
		style.setVerticalAlignment(VerticalAlignment.CENTER);
		setStringCell(row.createCell(0), "", style);
		setStringCell(row.createCell(1), "", style);
		sheet.addMergedRegion(new CellRangeAddress(1, 1, 0, 1));

		List<LocalDate> range = range();
		for (int i = 0; i < range.size(); i++) {
			XSSFCell cell = row.createCell(2 + i);
			style = new StyleFactory().top(THIN).bottom(MEDIUM).left(THIN).right(THIN).build();
			style.setFont(fontSmall());
			style.setAlignment(HorizontalAlignment.CENTER);
			style.setVerticalAlignment(VerticalAlignment.CENTER);
			cell.setCellType(CellType.NUMERIC);
			cell.setCellStyle(style);
			cell.setCellValue(range.get(i).getDayOfMonth());
		}
	}

	protected void createHeaderNameDays() {
		List<LocalDate> range = range();
		XSSFRow row = sheet.createRow(2);
		row.setHeightInPoints(18.0f);

		CellStyle style = new StyleFactory().top(MEDIUM).bottom(THIN).left(THIN).right(THIN).build();
		style.setAlignment(HorizontalAlignment.CENTER);
		style.setVerticalAlignment(VerticalAlignment.BOTTOM);
		style.setFont(fontNormal(true));
		DateTimeFormatter pattern = DateTimeFormatter.ofPattern("MMMM", Locale.ITALIAN);
		setStringCell(row.createCell(0), context.getDate().format(pattern).toUpperCase(), style);
		setStringCell(row.createCell(1), "", style);

		sheet.addMergedRegion(new CellRangeAddress(2, 2, 0, 1));

		for (int i = 0; i < range.size(); i++) {
			XSSFCell cell = row.createCell(2 + i);
			style = new StyleFactory().top(MEDIUM).bottom(THIN).left(THIN).right(THIN).build();
			style.setAlignment(HorizontalAlignment.CENTER);
			style.setVerticalAlignment(VerticalAlignment.CENTER);
			style.setFont(fontNormal(true));
			setStringCell(cell, getDayOfWeekShortName(range.get(i)), style);
		}
	}

	protected void createEmployees() {
		List<LocalDate> range = range();
		List<Employee> employees = sortedEmployees();
		int rowIndex = 3;
		for (Employee employee : employees) {
			log.info("Add employee on line {}", rowIndex);
			XSSFRow row = sheet.createRow(rowIndex);
			row.setHeightInPoints(13);

			Subgroup subgroup = getSubgroup(employee.getSubgroupId());

			if (isStartOfSubgroup(employee.getId())) {
				XSSFCell cell = row.createCell(0);
				CellStyle style = getEmployeeStyle(employee.getId(), null).build();
				style.setRotation((short) 90);
				style.setFont(fontSmall());
				style.setAlignment(HorizontalAlignment.CENTER);
				style.setVerticalAlignment(VerticalAlignment.CENTER);
				style.setWrapText(true);
				setStringCell(cell, subgroup.getName(), style);
				int subgroupSize = getEmployeeInSubgroup(subgroup.getId()).size();
				sheet.addMergedRegion(new CellRangeAddress(rowIndex, rowIndex + subgroupSize - 1, 0, 0));
			}
			if (isEndOfSubgroup(employee.getId())) {
				XSSFCell cell = row.createCell(0);
				CellStyle style = getEmployeeStyle(employee.getId(), null).build();
				cell.setCellStyle(style);
			}

			XSSFCell cell = row.createCell(1);
			CellStyle style = getEmployeeStyle(employee.getId(), null).build();
			style.setFont(fontNormal(false));
			setStringCell(cell, employee.getName(), style);

			for (int dayIndex = 0; dayIndex < range.size(); dayIndex++) {
				LocalDate date = range.get(dayIndex);
				XSSFCell dateCell = row.createCell(2 + dayIndex);
				String value = context.getShifts().stream()//
						.filter(shift -> shift.getEmployeeId() == employee.getId())//
						.filter(shift -> shift.getDate().equals(date))//
						.map(Shift::getValue).findFirst().orElse("");

				style = getEmployeeStyle(employee.getId(), date).build();
				style.setFont(fontNormal(false));
				setStringCell(dateCell, value, style);
			}
			rowIndex++;

			log.info("{}, isEndOfGroup? {} isEndOfSubgroup? {}", employee.getName(), isEndOfGroup(employee.getId()),
					isEndOfSubgroup(employee.getId()));
			if (isEndOfGroup(employee.getId())) {
				createGroupLine(rowIndex);
				rowIndex++;
			} else if (isEndOfSubgroup(employee.getId())) {
				createSubgroupLine(rowIndex);
				rowIndex++;
			}
		}
	}

	protected void createGroupLine(int rowIndex) {
		XSSFRow row = sheet.createRow(rowIndex);
		row.setHeightInPoints(6);
	}

	protected void createSubgroupLine(int rowIndex) {
		XSSFRow row = sheet.createRow(rowIndex);
		row.setHeightInPoints(6);
		int columns = 2 + range().size();
		for (int column = 0; column < columns; column++) {
			XSSFCell cell = row.createCell(column);
			CellStyle style = new StyleFactory().right(THIN).left(THIN).build();
			cell.setCellStyle(style);
		}
	}

	protected StyleFactory getEmployeeStyle(int employeeId, LocalDate date) {
		String top = THIN;
		if (isStartOfSubgroup(employeeId)) {
			top = MEDIUM;
		}
		String bottom = THIN;
		if (isEndOfSubgroup(employeeId)) {
			bottom = MEDIUM;
		}
		String left = THIN;
		if (date != null && isStartOfWeek(date)) {
			left = MEDIUM;
		}
		String right = THIN;
		if (date != null && isEndOfWeek(date)) {
			right = MEDIUM;
		}
		return new StyleFactory().top(top).right(right).bottom(bottom).left(left);
	}

	protected void sortGroups(List<Group> groups) {
		groups.sort(Comparator.comparing(Group::getId));
	}

	protected void sortSubgroups(List<Subgroup> subgroups) {
		subgroups.sort(Comparator.comparing(Subgroup::getGroupId).thenComparing(Subgroup::getId));
	}

	protected List<Employee> sortedEmployees() {
		return context.getEmployees().stream().sorted((e1, e2) -> {
			Subgroup subgroup1 = getSubgroup(e1.getSubgroupId());
			Subgroup subgroup2 = getSubgroup(e2.getSubgroupId());
			if (subgroup1.getGroupId() != subgroup2.getGroupId()) {
				return subgroup1.getGroupId() - subgroup2.getGroupId();
			}
			if (subgroup1.getId() != subgroup2.getId()) {
				return subgroup1.getId() - subgroup2.getId();
			}
			return e1.getId() - e2.getId();
		}).collect(Collectors.toList());
	}

	protected Group getGroup(int id) {
		return context.getGroups().stream().filter((g) -> g.getId() == id).findFirst().orElse(null);
	}

	protected List<Employee> getEmployeeInSubgroup(int subgroupId) {
		return sortedEmployees().stream() //
				.filter(e -> e.getSubgroupId() == subgroupId) //
				.collect(Collectors.toList());
	}

	protected Subgroup getSubgroup(int id) {
		return context.getSubgroups().stream().filter((g) -> g.getId() == id).findFirst().orElse(null);
	}

	protected LocalDate getRangeStart(LocalDate date) {
		LocalDate startOfMonth = date.withDayOfMonth(1);

		TemporalField fieldISO = WeekFields.of(Locale.ITALY).dayOfWeek();
		return startOfMonth.with(fieldISO, 1);
	}

	protected LocalDate getRangeEnd(LocalDate date) {
		LocalDate startOfMonth = date.withDayOfMonth(date.lengthOfMonth());
		TemporalField fieldISO = WeekFields.of(Locale.ITALY).dayOfWeek();
		return startOfMonth.with(fieldISO, 7);
	}

	protected boolean isStartOfGroup(int employeeId) {
		List<Employee> employees = sortedEmployees();
		int indexOfEmployee = indexOf(employees, employeeId);
		if (indexOfEmployee == 0) {
			return true;
		}
		Employee previous = employees.get(indexOfEmployee - 1);
		Employee target = employees.get(indexOfEmployee);
		Subgroup previousSubgroup = getSubgroup(previous.getSubgroupId());
		Subgroup targetSubgroup = getSubgroup(target.getSubgroupId());
		return previousSubgroup.getGroupId() != targetSubgroup.getGroupId();
	}

	protected boolean isEndOfGroup(int employeeId) {
		List<Employee> employees = sortedEmployees();
		int indexOfEmployee = indexOf(employees, employeeId);
		if (indexOfEmployee == employees.size() - 1) {
			return true;
		}
		Employee next = employees.get(indexOfEmployee + 1);
		Employee target = employees.get(indexOfEmployee);
		Subgroup nextSubgroup = getSubgroup(next.getSubgroupId());
		Subgroup targetSubgroup = getSubgroup(target.getSubgroupId());
		return nextSubgroup.getGroupId() != targetSubgroup.getGroupId();
	}

	protected boolean isStartOfSubgroup(int employeeId) {
		List<Employee> employees = sortedEmployees();
		int indexOfEmployee = indexOf(employees, employeeId);
		if (indexOfEmployee == 0) {
			return true;
		}
		Employee previous = employees.get(indexOfEmployee - 1);
		Employee target = employees.get(indexOfEmployee);
		return previous.getSubgroupId() != target.getSubgroupId();
	}

	protected boolean isEndOfSubgroup(int employeeId) {
		List<Employee> employees = sortedEmployees();
		int indexOfEmployee = indexOf(employees, employeeId);
		if (indexOfEmployee == employees.size() - 1) {
			return true;
		}
		Employee next = employees.get(indexOfEmployee + 1);
		Employee target = employees.get(indexOfEmployee);
		return next.getSubgroupId() != target.getSubgroupId();
	}

	protected boolean isStartOfWeek(LocalDate date) {
		return date.getDayOfWeek() == DayOfWeek.MONDAY;
	}

	protected boolean isEndOfWeek(LocalDate date) {
		return date.getDayOfWeek() == DayOfWeek.SUNDAY;
	}

	protected int indexOf(List<Employee> employees, int employeeId) {
		for (int i = 0; i < employees.size(); i++) {
			if (employees.get(i).getId() == employeeId) {
				return i;
			}
		}
		return -1;
	}

	protected String getDayOfWeekShortName(LocalDate date) {
		switch (date.getDayOfWeek()) {
		case MONDAY:
			return "L";
		case TUESDAY:
			return "M";
		case WEDNESDAY:
			return "M";
		case THURSDAY:
			return "G";
		case FRIDAY:
			return "V";
		case SATURDAY:
			return "S";
		case SUNDAY:
			return "D";
		default:
			return "-";
		}
	}

	protected void setStringCell(XSSFCell cell, String value, StyleFactory styleFactory) {
		setStringCell(cell, value, styleFactory.build());
	}

	protected void setStringCell(XSSFCell cell, String value, CellStyle style) {
		cell.setCellType(CellType.STRING);
		cell.setCellValue(value);
		cell.setCellStyle(style);
	}

	protected List<LocalDate> range() {
		LocalDate referenceDate = context.getDate();
		LocalDate from = getRangeStart(referenceDate);
		LocalDate to = getRangeEnd(referenceDate);

		return DateUtil.range(from, to);

	}

	protected Font fontSmall() {
		Font font = workbook.createFont();
		font.setFontHeightInPoints((short) 8);
		font.setFontName("Arial");
		font.setColor(IndexedColors.BLACK.getIndex());
		return font;
	}

	protected Font fontNormal(boolean bold) {
		Font font = workbook.createFont();
		font.setFontHeightInPoints((short) 10);
		font.setFontName("Arial");
		font.setColor(IndexedColors.BLACK.getIndex());
		font.setBold(bold);
		return font;
	}

	protected Font fontBig() {
		Font font = workbook.createFont();
		font.setFontHeightInPoints((short) 12);
		font.setFontName("Arial");
		font.setBold(true);
		font.setColor(IndexedColors.BLACK.getIndex());
		return font;
	}

	protected class StyleFactory {
		public CellStyle style = workbook.createCellStyle();

		public CellStyle build() {
			return style;
		}

		public StyleFactory top(String type) {
			borderStyle(type, style::setBorderTop);
			borderColor(type, style::setTopBorderColor);
			return this;
		}

		public StyleFactory right(String type) {
			borderStyle(type, style::setBorderRight);
			borderColor(type, style::setRightBorderColor);
			return this;
		}

		public StyleFactory bottom(String type) {
			borderStyle(type, style::setBorderBottom);
			borderColor(type, style::setBottomBorderColor);
			return this;
		}

		public StyleFactory left(String type) {
			borderStyle(type, style::setBorderLeft);
			borderColor(type, style::setLeftBorderColor);
			return this;
		}

		protected StyleFactory borderStyle(String type, Consumer<BorderStyle> borderAccessor) {
			if (type == null || type.equals(NONE)) {
				return this;
			}
			if (type.equals(THIN)) {
				borderAccessor.accept(BorderStyle.THIN);
			}
			if (type.equals(MEDIUM)) {
				borderAccessor.accept(BorderStyle.MEDIUM);
			}
			return this;
		}

		protected StyleFactory borderColor(String type, Consumer<Short> borderAccessor) {
			if (type == null || type.equals(NONE)) {
				return this;
			}
			borderAccessor.accept(IndexedColors.BLACK.getIndex());
			return this;
		}
	}

}
