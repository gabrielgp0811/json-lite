/**
 * 
 */
package io.github.gabrielgp0811.jsonlite.impl;

import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Map;
import java.util.stream.Collectors;

import io.github.gabrielgp0811.jsonlite.Json;
import io.github.gabrielgp0811.jsonlite.JsonEntry;
import io.github.gabrielgp0811.jsonlite.annotation.JsonIgnore;
import io.github.gabrielgp0811.jsonlite.annotation.JsonPattern;
import io.github.gabrielgp0811.jsonlite.constants.JsonStrings;
import io.github.gabrielgp0811.jsonlite.util.JsonFormatInfo;
import io.github.gabrielgp0811.jsonlite.util.Util;

/**
 * JSON class for Java objects.
 * 
 * @author gabrielgp0811
 */
public class JsonObject extends JsonEntry<Object> {

	/**
	 * The children JSON objects.
	 */
	private Collection<JsonEntry<?>> children = null;

	/**
	 * 
	 */
	public JsonObject() {

	}

	/**
	 * @param name The name to set
	 */
	public JsonObject(String name) {
		super(name, null);
	}

	/**
	 * @param value The value to set
	 */
	public JsonObject(Object value) {
		this(null, value);
	}

	/**
	 * @param value   The value to set
	 * @param pattern The pattern to set
	 */
	public JsonObject(Object value, String pattern) {
		this(null, value, pattern);
	}

	/**
	 * @param value   The value to set
	 * @param pattern The pattern to set
	 * @param locale  The locale to set
	 */
	public JsonObject(Object value, String pattern, String locale) {
		this(null, value, pattern, locale, null);
	}

	/**
	 * @param value    The value to set
	 * @param pattern  The pattern to set
	 * @param locale   The locale to set
	 * @param timezone The timezone to set
	 */
	public JsonObject(Object value, String pattern, String locale, String timezone) {
		this(null, value, new JsonFormatInfo(pattern, Util.toLocale(locale), Util.toTimeZone(timezone)));
	}

	/**
	 * @param value The value to set
	 * @param info  As informa&ccedil;&otilde;es da formata&ccedil;&atilde;o
	 *              utilizada.
	 */
	public JsonObject(Object value, JsonFormatInfo info) {
		this(null, value, info);
	}

	/**
	 * @param name  The name to set
	 * @param value The value to set
	 */
	public JsonObject(String name, Object value) {
		this(name, value, (JsonFormatInfo) null);
	}

	/**
	 * @param name    The name to set
	 * @param value   The value to set
	 * @param pattern The pattern to set
	 */
	public JsonObject(String name, Object value, String pattern) {
		this(name, value, pattern, null);
	}

	/**
	 * @param name    The name to set
	 * @param value   The value to set
	 * @param pattern The pattern to set
	 * @param locale  The locale to set
	 */
	public JsonObject(String name, Object value, String pattern, String locale) {
		this(name, value, pattern, locale, null);
	}

	/**
	 * @param name     The name to set
	 * @param value    The value to set
	 * @param pattern  The pattern to set
	 * @param locale   The locale to set
	 * @param timezone The timezone to set
	 */
	public JsonObject(String name, Object value, String pattern, String locale, String timezone) {
		this(name, value, new JsonFormatInfo(pattern, Util.toLocale(locale), Util.toTimeZone(timezone)));
	}

	/**
	 * @param name  The name to set
	 * @param value The value to set
	 * @param info  The inf oto set
	 */
	public JsonObject(String name, Object value, JsonFormatInfo info) {
		super(name, value, info);

		init();
	}

	/**
	 * Init children JSON objects.
	 */
	private void init() {
		if (Util.isMap(getValue())) {
			Map<?, ?> map = (Map<?, ?>) getValue();

			map.entrySet().stream().filter(entry -> Util.isString(entry.getKey()))
					.forEach(entry -> addObject((String) entry.getKey(), entry.getValue(), info));

			return;
		}

		Field[] fields = getValue().getClass().getDeclaredFields();

		for (Field field : fields) {
			if (Modifier.isStatic(field.getModifiers()) || field.getType().isAnnotationPresent(JsonIgnore.class)
					|| field.isAnnotationPresent(JsonIgnore.class)) {
				continue;
			}

			String name = field.getName();

			Method method = null;

			try {
				method = getValue().getClass()
						.getDeclaredMethod("get".concat(name.substring(0, 1).toUpperCase()).concat(name.substring(1)));
			} catch (NoSuchMethodException e) {
				try {
					method = getValue().getClass().getDeclaredMethod(
							"is".concat(name.substring(0, 1).toUpperCase()).concat(name.substring(1)));
				} catch (NoSuchMethodException e1) {
					continue;
				}
			}

			Object value = null;
			try {
				value = method.invoke(getValue());
			} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
			}

			JsonFormatInfo info = this.info;

			if (field.isAnnotationPresent(JsonPattern.class)) {
				if (info == null)
					info = new JsonFormatInfo();

				JsonPattern pattern = field.getDeclaredAnnotation(JsonPattern.class);

				if (info.getPattern().trim().isEmpty())
					if (!pattern.deserializePattern().trim().isEmpty())
						info.setPattern(pattern.deserializePattern().trim());
					else if (!pattern.value().trim().isEmpty())
						info.setPattern(pattern.value().trim());

				if (info.getLocale() == null)
					info.setLocale(Util.toLocale(pattern.locale()));

				if (info.getTimezone() == null)
					info.setTimezone(Util.toTimeZone(pattern.timezone()));
			}

			addObject(name, value, info);
		}
	}

	@Override
	public JsonEntry<Object> addObject(String name, Object obj, JsonFormatInfo info) {
		return addChild(Json.toJson(name, obj, info));
	}

	@Override
	public JsonEntry<Object> addChild(JsonEntry<?> json) {
		if (json != null) {
			if (isChildPresent(json.getKey())) {
				JsonEntry<?> child = getChild(json.getKey());
				if (Util.isObject(child)) {
					if (Util.isObject(json)) {
						child.addChildren(json.getChildren());
					} else {
						child.addChild(json);
					}
				}
			} else {
				json.setObjectChild(true);

				getChildren().add(json);
			}
		}

		return this;
	}

	@Override
	public JsonEntry<Object> addChildren(JsonEntry<?>... jsons) {
		if (jsons != null)
			addChildren(Arrays.asList(jsons));

		return this;
	}

	@Override
	public JsonEntry<Object> addChildren(Collection<JsonEntry<?>> jsons) {
		if (jsons != null)
			for (JsonEntry<?> json : jsons) {
				addChild(json);
			}

		return this;
	}

	@Override
	public JsonEntry<?> getChild(int index) {
		if (index < 0 || index > childrenSize()) {
			return null;
		}

		int i = 0;
		for (JsonEntry<?> json : children) {
			if (i == index) {
				return json;
			}

			i++;
		}

		return null;
	}

	@Override
	public JsonEntry<?> getChild(String name) {
		return getChildren().stream().filter(json -> json.getKey().equals(name)).findFirst().orElse(null);
	}

	@Override
	public JsonEntry<?> removeChild(int index) {
		if (index > 0 || index < getChildren().size()) {
			int i = 0;
			for (JsonEntry<?> json : children) {
				if (i == index) {
					children.remove(json);
					break;
				}

				i++;
			}
		}

		return this;
	}

	@Override
	public JsonEntry<?> removeChild(String name) {
		getChildren().removeIf(child -> child.getKey().equals(name));

		return this;
	}

	@Override
	public Collection<JsonEntry<?>> getChildren() {
		if (children == null) {
			children = new ArrayList<>();
		}
		return children;
	}

	@Override
	public Object toJavaObject() {
		return getValue();
	}

	@Override
	public <T> T toJavaObject(Class<T> clazz, JsonFormatInfo info) {
		if (clazz == null) {
			return null;
		}

		if (Util.isArray(clazz) || Util.isBoolean(clazz) || Util.isCharacter(clazz) || Util.isCollection(clazz)
				|| Util.isDate(clazz) || Util.isEnum(clazz) || Util.isLocalDate(clazz) || Util.isLocalDateTime(clazz)
				|| Util.isLocalTime(clazz) || Util.isMap(clazz) || Util.isNumber(clazz) || Util.isString(clazz)) {
			return null;
		}

		Field[] fields = clazz.getDeclaredFields();

		T result = null;
		try {
			result = clazz.newInstance();
		} catch (InstantiationException | IllegalAccessException e) {
			return null;
		}

		for (Field field : fields) {
			JsonEntry<?> child = getChild(field.getName());

			if (child == null) {
				continue;
			}

			String fieldName = field.getName();
			Class<?> fieldType = field.getType();

			JsonFormatInfo info0 = info;

			if (field.isAnnotationPresent(JsonPattern.class)) {
				if (info0 == null)
					info0 = new JsonFormatInfo();

				JsonPattern pattern = field.getDeclaredAnnotation(JsonPattern.class);

				if (info0.getPattern().trim().isEmpty())
					if (!pattern.deserializePattern().trim().isEmpty())
						info0.setPattern(pattern.deserializePattern().trim());
					else if (!pattern.value().trim().isEmpty())
						info0.setPattern(pattern.value().trim());

				if (info0.getLocale() == null)
					info0.setLocale(Util.toLocale(pattern.locale()));

				if (info0.getTimezone() == null)
					info0.setTimezone(Util.toTimeZone(pattern.timezone()));
			}

			Object value = null;

			if (Util.isArray(fieldType)) {
				if (Util.isCollection(child)) {
					fieldType = fieldType.getComponentType();

					value = Array.newInstance(fieldType.getComponentType(), child.childrenSize());

					for (int i = 0; i < child.childrenSize(); i++) {
						Array.set(value, i, child.getChild(i).getValue());
					}
				}
			} else if (Util.isCollection(fieldType)) {
				if (Util.isCollection(child)) {
					value = child.getValue();
				}
			} else if (Util.isEnum(fieldType)) {
				if (Util.isEnum(child)) {
					value = child.getValue();
				}
			} else {
				value = child.toJavaObject(fieldType, info0);
			}

			Util.setValue(result, fieldName, fieldType, value);
		}

		return result;
	}

	@Override
	public Collection<Object> toJavaCollection() {
		return new ArrayList<>(Arrays.asList(toJavaObject()));
	}

	@Override
	public <T> Collection<T> toJavaCollection(Class<T> clazz, JsonFormatInfo info) {
		if (clazz == null) {
			return null;
		}

		return new ArrayList<>(Arrays.asList(toJavaObject(clazz, info)));
	}

	@Override
	public String toPrettyString() {
		StringBuilder builder = new StringBuilder();

		if (isObjectChild()) {
			builder.append(Util.getIndentTab(indentLevel, tab));
			builder.append(JsonStrings.QUOTATION);
			builder.append(getKey());
			builder.append(JsonStrings.QUOTATION);
			builder.append(JsonStrings.COLON);
			builder.append(JsonStrings.WHITESPACE);
		}

		if (isArrayChild()) {
			builder.append(Util.getIndentTab(indentLevel, tab));
		}

		builder.append(JsonStrings.START);
		builder.append(
			getChildren().stream()
				.map(child -> {
					child.setIndentLevel(getIndentLevel() + 1);
					
					return JsonStrings.LINE_SEPARATOR.concat(child.toPrettyString(tab));
				})
				.collect(Collectors.joining(JsonStrings.COMMA))
		);
		builder.append(JsonStrings.LINE_SEPARATOR);
		
		if (isObjectChild() || isArrayChild()) {
			builder.append(Util.getIndentTab(indentLevel, tab));
		}

		builder.append(JsonStrings.END);

		return builder.toString();
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();

		if (isObjectChild()) {
			builder.append(JsonStrings.QUOTATION);
			builder.append(getKey());
			builder.append(JsonStrings.QUOTATION);
			builder.append(JsonStrings.COLON);
		}

		builder.append(JsonStrings.START);
		builder.append(
			getChildren().stream()
				.map(child -> child.toString())
				.collect(Collectors.joining(JsonStrings.COMMA))
		);
		builder.append(JsonStrings.END);

		return builder.toString();
	}

}