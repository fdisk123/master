package project.master.fw.sh.common;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.ConcurrentHashMap;

public class ObjectFill {

	private final static Map<Class<?>, Map<String, Field>> FIELDS = new ConcurrentHashMap<Class<?>, Map<String, Field>>();

	public void scanClass(Class<?> c) {
		FIELDS.put(c, fieldsConvertMap(c.getDeclaredFields()));
	}

	public Map<String, Field> fieldsConvertMap(Field... o) {
		Map<String, Field> map = new HashMap<String, Field>();
		for (Field f : o) {
			f.setAccessible(true);
			map.put(f.getName(), f);
		}
		return map;
	}

	public final <T> T fillObject(Class<T> t, Map<String, ?> data) throws IllegalArgumentException, IllegalAccessException, InstantiationException {
		return fillObject(t.newInstance(), data);
	}

	public final Map<String, Object> objectToMap(Object o) throws IllegalArgumentException, IllegalAccessException, InstantiationException {
		if (!FIELDS.containsKey(o.getClass()))
			scanClass(o.getClass());
		Map<String, Field> fields = FIELDS.get(o.getClass());
		Map<String, Object> result = new HashMap<String, Object>();
		for (Entry<String, Field> en : fields.entrySet()) {
			result.put(en.getKey(), en.getValue().get(o));
		}
		return result;
	}

	public final <T> T fillObject(T t, Map<String, ?> data) throws IllegalArgumentException, IllegalAccessException {
		System.out.println(t.getClass());
		Class<?> c = t.getClass();
		if (!FIELDS.containsKey(c))
			scanClass(c);
		Map<String, Field> fields = FIELDS.get(c);
		Object value;
		Field field;
		for (Entry<String, ?> en : data.entrySet()) {
			value = en.getValue();
			if (null == value || null == (field = fields.get(en.getKey())))
				continue;
			field.set(t, getValue(field.getType(), value));
		}
		return t;
	}

	private Object getValue(Class<?> c, Object data) {
		String simpleName = c.getSimpleName();
		boolean isArray = data.getClass().isArray();
		if (c.isArray()) {
			System.err.println("数组末实现");
			return null;
		} else if (simpleName.equalsIgnoreCase("String"))
			return getFirstValue(isArray, data);
		else if (simpleName.equalsIgnoreCase("boolean") || simpleName.equalsIgnoreCase("Boolean"))
			return Boolean.valueOf(getFirstValue(isArray, data));
		else if (simpleName.equalsIgnoreCase("int") || simpleName.equalsIgnoreCase("Integer"))
			try {
				return Integer.valueOf(getFirstValue(isArray, data));
			} catch (Exception e5) {
				return 0;
			}
		else if (simpleName.equalsIgnoreCase("byte"))
			return Byte.valueOf(getFirstValue(isArray, data));
		else if (simpleName.equalsIgnoreCase("char") || simpleName.equalsIgnoreCase("Character"))
			try {
				return Character.valueOf(getFirstValue(isArray, data).charAt(0));
			} catch (Exception e4) {
				return 0;
			}
		else if (simpleName.equalsIgnoreCase("double"))
			try {
				return Double.valueOf(getFirstValue(isArray, data));
			} catch (Exception e3) {
				return 0.0;
			}
		else if (simpleName.equalsIgnoreCase("long"))
			try {
				return Long.valueOf(getFirstValue(isArray, data));
			} catch (Exception e2) {
				return 0L;
			}
		else if (simpleName.equalsIgnoreCase("short"))
			try {
				return Short.valueOf(getFirstValue(isArray, data));
			} catch (Exception e1) {
			}
		else if (simpleName.equalsIgnoreCase("float"))
			try {
				return Float.valueOf(getFirstValue(isArray, data));
			} catch (Exception e) {
				return 0;
			}
		return data;
	}

	private String getFirstValue(boolean isArray, Object data) {
		return isArray ? ((String[]) data)[0] : data.toString();
	}
}
