/**
 * 
 */
package com.github.gabrielgp0811.jsonlite.serializer.impl;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.github.gabrielgp0811.jsonlite.Json;
import com.github.gabrielgp0811.jsonlite.JsonEntry;
import com.github.gabrielgp0811.jsonlite.annotation.JsonPattern;
import com.github.gabrielgp0811.jsonlite.annotation.JsonSerializer;
import com.github.gabrielgp0811.jsonlite.annotation.JsonSerializers;
import com.github.gabrielgp0811.jsonlite.exception.JsonException;
import com.github.gabrielgp0811.jsonlite.impl.JsonObject;
import com.github.gabrielgp0811.jsonlite.serializer.Serializer;

/**
 * Main implementation responsible for serializing Java objects.
 * 
 * @author gabrielgp0811
 */
public final class JsonSerializerImpl implements Serializer {

	/**
	 * 
	 */
	public JsonSerializerImpl() {

	}

	@Override
	public JsonEntry<?> serialize(String name, Object obj) throws JsonException {
		if (name == null || name.trim().isEmpty() || obj == null) {
			return null;
		}

		if (obj.getClass().isAnnotationPresent(JsonSerializers.class)) {
			return Arrays.stream(obj.getClass().getDeclaredAnnotation(JsonSerializers.class).value())
					.filter(serializer -> serializer.name().equals(name)).map(serializer -> serialize(serializer, obj))
					.findFirst().get();
		}

		JsonSerializer serializer = obj.getClass().getDeclaredAnnotation(JsonSerializer.class);
		if (serializer != null && serializer.name().equals(name)) {
			return serialize(serializer, obj);
		}

		return null;
	}

	/**
	 * @param serializer The serializer.
	 * @param obj        The Java object.
	 * @return The JSON object.
	 */
	private JsonEntry<?> serialize(JsonSerializer serializer, Object obj) {
		JsonObject json = new JsonObject();

		Arrays.stream(serializer.fields())
				.map(field -> toJson(new ArrayList<>(Arrays.asList(field.value().split("[.]"))), obj, field.pattern()))
				.forEach(json::addChild);

		return json;
	}

	/**
	 * @param names A collection of attribute's names.
	 * @param obj   The Java object.
	 * @param jp    The annotation containing serialization pattern.
	 * @return The JSON object.
	 */
	private JsonEntry<?> toJson(List<String> names, Object obj, JsonPattern jp) {
		if (names == null || names.isEmpty()) {
			return null;
		}

		String name = names.remove(0);

		if (!names.isEmpty()) {
			return new JsonObject(name).addChild(toJson(names, getValue(name, obj), jp));
		}

		String pattern = null;

		if (!jp.serializePattern().trim().isEmpty()) {
			pattern = jp.serializePattern();
		} else if (!jp.value().trim().isEmpty()) {
			pattern = jp.value();
		}

		return toJson(name, obj, pattern, jp.locale(), jp.timezone());
	}

	/**
	 * @param name
	 * @param obj
	 * @param pattern
	 * @param locale
	 * @param timezone
	 * @return
	 */
	private JsonEntry<?> toJson(String name, Object obj, String pattern, String locale, String timezone) {
		Object value = getValue(name, obj);

		if (value != null) {
			try {
				return Json.toJson(name, value, pattern, locale, timezone);
			} catch (SecurityException | IllegalArgumentException e) {
			}
		}

		return null;
	}

	/**
	 * @param name The attribute's name.
	 * @param obj The Java object.
	 * @return The attribute's value.
	 */
	private Object getValue(String name, Object obj) {
		try {
			Field field = obj.getClass().getDeclaredField(name);

			return obj.getClass().getDeclaredMethod(getterName(field)).invoke(obj);
		} catch (NoSuchFieldException | SecurityException | NoSuchMethodException | IllegalAccessException
				| IllegalArgumentException | InvocationTargetException e) {
		}

		return null;
	}

	/**
	 * @param field The field.
	 * @return The getter method's name.
	 */
	private String getterName(Field field) {
		return "get".concat(field.getName().substring(0, 1).toUpperCase()).concat(field.getName().substring(1));
	}

}