package it.vitalegi.workshifts.util;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class DateUtilTest {

	@Test
	void testRange() {
		List<LocalDate> range = DateUtil.range(LocalDate.of(2020, 04, 25), LocalDate.of(2020, 04, 28));
		assertEquals(4, range.size());
		assertEquals(date(2020, 04, 25), range.get(0));
		assertEquals(date(2020, 04, 26), range.get(1));
		assertEquals(date(2020, 04, 27), range.get(2));
		assertEquals(date(2020, 04, 28), range.get(3));
	}

	@Test
	void testSplitRange() {
		List<List<LocalDate>> ranges = DateUtil.splitRangeSunday(date(2020, 04, 29), date(2020, 05, 13));

		assertEquals(3, ranges.size());
		assertEquals(DateUtil.range(date(2020, 04, 29), date(2020, 05, 03)), ranges.get(0));
		assertEquals(DateUtil.range(date(2020, 05, 04), date(2020, 05, 10)), ranges.get(1));
		assertEquals(DateUtil.range(date(2020, 05, 11), date(2020, 05, 13)), ranges.get(2));
	}

	@Test
	void testGetRangeWeekDays() {
		List<List<LocalDate>> ranges = DateUtil.getRangeWeekDays(date(2020, 04, 29), date(2020, 05, 13));

		assertEquals(3, ranges.size());
		assertEquals(DateUtil.range(date(2020, 04, 29), date(2020, 05, 1)), ranges.get(0));
		assertEquals(DateUtil.range(date(2020, 05, 04), date(2020, 05, 8)), ranges.get(1));
		assertEquals(DateUtil.range(date(2020, 05, 11), date(2020, 05, 13)), ranges.get(2));
	}

	@Test
	void testGetRangeWeekendDays() {
		List<List<LocalDate>> ranges = DateUtil.getRangeWeekendDays(date(2020, 04, 29), date(2020, 05, 13));

		assertEquals(2, ranges.size());
		assertEquals(DateUtil.range(date(2020, 5, 2), date(2020, 5, 3)), ranges.get(0));
		assertEquals(DateUtil.range(date(2020, 5, 9), date(2020, 5, 10)), ranges.get(1));
	}
	

	@Test
	void testGetFirstDayOfWeek() {
		
		LocalDate monday = date(2020,05,11);
		assertEquals(monday, DateUtil.getFirstDayOfWeek(date(2020,05,11)));
		assertEquals(monday, DateUtil.getFirstDayOfWeek(date(2020,05,12)));
		assertEquals(monday, DateUtil.getFirstDayOfWeek(date(2020,05,13)));
		assertEquals(monday, DateUtil.getFirstDayOfWeek(date(2020,05,14)));
		assertEquals(monday, DateUtil.getFirstDayOfWeek(date(2020,05,15)));
		assertEquals(monday, DateUtil.getFirstDayOfWeek(date(2020,05,16)));
		assertEquals(monday, DateUtil.getFirstDayOfWeek(date(2020,05,17)));
	}

	@Test
	void testGetLastDayOfWeek() {
		
		LocalDate sunday = date(2020,05,17);
		assertEquals(sunday, DateUtil.getLastDayOfWeek(date(2020,05,11)));
		assertEquals(sunday, DateUtil.getLastDayOfWeek(date(2020,05,12)));
		assertEquals(sunday, DateUtil.getLastDayOfWeek(date(2020,05,13)));
		assertEquals(sunday, DateUtil.getLastDayOfWeek(date(2020,05,14)));
		assertEquals(sunday, DateUtil.getLastDayOfWeek(date(2020,05,15)));
		assertEquals(sunday, DateUtil.getLastDayOfWeek(date(2020,05,16)));
		assertEquals(sunday, DateUtil.getLastDayOfWeek(date(2020,05,17)));
	}

	private static LocalDate date(int year, int month, int dayOfMonth) {
		return LocalDate.of(year, month, dayOfMonth);
	}
}
