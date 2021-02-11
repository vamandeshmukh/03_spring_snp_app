package com.snp.app.utility;

public class Utility {

	@SuppressWarnings("unchecked")
	public static <T> T mergeObjects(T first, T second) throws IllegalAccessException, InstantiationException {
		Class<?> clazz = first.getClass();
		java.lang.reflect.Field[] fields = clazz.getDeclaredFields();
		Object returnValue = clazz.newInstance();
		for (java.lang.reflect.Field field : fields) {
			field.setAccessible(true);
			Object value1 = field.get(second);
			Object value2 = field.get(first);
			Object value = (value1 != null) ? value1 : value2;
			field.set(returnValue, value);
		}
		return (T) returnValue;
	}
}
