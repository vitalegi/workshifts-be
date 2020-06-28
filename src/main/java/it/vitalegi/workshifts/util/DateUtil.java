package it.vitalegi.workshifts.util;

import static java.time.DayOfWeek.FRIDAY;
import static java.time.DayOfWeek.MONDAY;
import static java.time.DayOfWeek.SATURDAY;
import static java.time.DayOfWeek.SUNDAY;
import static java.time.DayOfWeek.THURSDAY;
import static java.time.DayOfWeek.TUESDAY;
import static java.time.DayOfWeek.WEDNESDAY;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalField;
import java.time.temporal.WeekFields;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Service;

@Service
public class DateUtil {

	protected static DateUtil _instance;

	@PostConstruct
	void init() {
		_instance = this;
	}

	public static DateUtil getInstance() {
		return _instance;
	}

	public static LocalDate parse(String str) {
		return LocalDate.parse(str, getPattern());
	}

	public static String format(LocalDate date) {
		return date.format(getPattern());
	}

	public static DateTimeFormatter getPattern() {
		return DateTimeFormatter.ofPattern("yyyy-MM-dd");
	}

	public static List<LocalDate> range(LocalDate from, LocalDate to) {
		List<LocalDate> dates = new ArrayList<>();
		LocalDate date = from;
		while (!date.isAfter(to)) {
			dates.add(date);
			date = date.plusDays(1);
		}
		return dates;
	}

	public static List<List<LocalDate>> splitRangeSunday(LocalDate from, LocalDate to) {
		return splitRange(from, to, date -> SUNDAY == date.getDayOfWeek());
	}

	public static List<List<LocalDate>> getRangeWeekDays(LocalDate from, LocalDate to) {
		List<LocalDate> dates = range(from, to).stream()//
				.filter(date -> filter(date, MONDAY, TUESDAY, WEDNESDAY, THURSDAY, FRIDAY))
				.collect(Collectors.toList());

		return splitRange(dates, date -> FRIDAY == date.getDayOfWeek());
	}

	public static List<List<LocalDate>> getRangeWeekendDays(LocalDate from, LocalDate to) {
		List<LocalDate> dates = range(from, to).stream()//
				.filter(date -> filter(date, SATURDAY, SUNDAY)).collect(Collectors.toList());

		return splitRange(dates, date -> SUNDAY == date.getDayOfWeek());
	}

	public static List<List<LocalDate>> splitRange(LocalDate from, LocalDate to, Predicate<LocalDate> isLastElement) {
		List<LocalDate> dates = range(from, to);
		return splitRange(dates, isLastElement);
	}

	public static List<List<LocalDate>> splitRange(List<LocalDate> dates, Predicate<LocalDate> isLastElement) {
		List<List<LocalDate>> ranges = new ArrayList<>();

		List<LocalDate> currRange = new ArrayList<>();
		for (int i = 0; i < dates.size(); i++) {
			LocalDate next = dates.get(i);
			currRange.add(next);
			if (isLastElement.test(next)) {
				ranges.add(currRange);
				currRange = new ArrayList<>();
			}
		}
		if (!currRange.isEmpty()) {
			ranges.add(currRange);
		}
		return ranges;
	}

	public static LocalDate getFirstDayOfWeek(LocalDate date) {
		TemporalField fieldISO = WeekFields.of(Locale.ITALY).dayOfWeek();
		return date.with(fieldISO, 1);
	}

	public static LocalDate getLastDayOfWeek(LocalDate date) {
		TemporalField fieldISO = WeekFields.of(Locale.ITALY).dayOfWeek();
		return date.with(fieldISO, 7);
	}

	protected static boolean filter(LocalDate date, DayOfWeek... days) {
		DayOfWeek dayOfWeek = date.getDayOfWeek();
		for (DayOfWeek day : days) {
			if (dayOfWeek == day) {
				return true;
			}
		}
		return false;
	}

	public static boolean isWeekDay(LocalDate date) {
		return !isWeekendDay(date);
	}

	public static boolean isWeekendDay(LocalDate date) {
		DayOfWeek day = date.getDayOfWeek();
		if (day == DayOfWeek.SATURDAY || day == DayOfWeek.SUNDAY) {
			return true;
		}
		return false;
	}
}
