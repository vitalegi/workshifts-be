package it.vitalegi.workshifts.util;

import java.util.List;

public class ListUtil {

	public static boolean isNullOrEmpty(List<?> list) {
		if (list == null || list.isEmpty()) {
			return true;
		}
		return false;
	}

	public static <E> E first(List<E> list) {
		if (isNullOrEmpty(list)) {
			throw new IllegalArgumentException("List is empty" + list);
		}
		return list.get(0);
	}

	public static <E> E last(List<E> list) {
		if (isNullOrEmpty(list)) {
			throw new IllegalArgumentException("List is empty" + list);
		}
		return list.get(list.size() - 1);
	}
}
