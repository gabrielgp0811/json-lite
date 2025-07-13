/**
 * 
 */
package io.github.gabrielgp0811.jsonlite.serializer.impl;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import io.github.gabrielgp0811.jsonlite.Json;
import io.github.gabrielgp0811.jsonlite.JsonEntry;
import io.github.gabrielgp0811.jsonlite.annotation.JsonField;
import io.github.gabrielgp0811.jsonlite.annotation.JsonSerializer;
import io.github.gabrielgp0811.jsonlite.annotation.JsonSerializers;
import io.github.gabrielgp0811.jsonlite.converter.Converter;
import io.github.gabrielgp0811.jsonlite.converter.impl.JsonFieldInfoSerializationConverter;
import io.github.gabrielgp0811.jsonlite.exception.JsonException;
import io.github.gabrielgp0811.jsonlite.impl.JsonObject;
import io.github.gabrielgp0811.jsonlite.serializer.Serializer;
import io.github.gabrielgp0811.jsonlite.util.JsonFieldInfo;

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
					.filter(serializer -> serializer.name().equals(name))
					.findFirst()
					.map(serializer -> serialize(serializer, obj))
					.orElseThrow(() -> new JsonException("No serializer for name: '" + name + "'."));
		}

		if (obj.getClass().isAnnotationPresent(JsonSerializer.class)) {
			JsonSerializer serializer = obj.getClass().getDeclaredAnnotation(JsonSerializer.class);
			if (serializer.name().equals(name)) {
				return serialize(serializer, obj);
			}
		}

		return null;
	}

	/**
	 * @param serializer The serializer.
	 * @param obj        The Java object.
	 * @return The JSON object.
	 */
	private JsonEntry<?> serialize(JsonSerializer serializer, Object obj) {
		final JsonObject json = new JsonObject();

		final Converter<JsonField, JsonFieldInfo> converter = new JsonFieldInfoSerializationConverter();

		Arrays.stream(serializer.fields())
				.filter(field -> !field.value().trim().isEmpty())
				.map(field -> {
					try {
						return converter.convert(field);
					} catch (JsonException e) {
					}
					return null;
				})
				.filter(Objects::nonNull)
				.map(fieldInfo -> toJson(obj, fieldInfo))
				.forEach(json::addChild);

		return json;
	}

	/**
	 * @param obj       The Java object.
	 * @param fieldInfo The field info (name, custom name, pattern, locale e
	 *                  timezone).
	 * @return The JSON object.
	 */
	private JsonEntry<?> toJson(Object obj, JsonFieldInfo fieldInfo) {
		List<String> names = new ArrayList<>(Arrays.asList(fieldInfo.getName().split("[.]")));
		List<String> customNames = new ArrayList<>(Arrays.asList(fieldInfo.getCustomNames()[0].split("[.]")));

		int size = names.size();
		int customSize = customNames.size();

		if (size > customSize) {
			for (int i = 0; i < size - customSize; i++) {
				customNames.add(i, names.get(i));
			}
		}

		for (int i = 0; i < customNames.size(); i++) {
			String customName = customNames.get(i);

			if (customName.trim().isEmpty()) {
				customName = names.get(i);

				customNames.set(i, customName);
			}
		}

		return toJson(names, customNames, obj, fieldInfo);
	}

	/**
	 * @param names       A collection of attribute's names.
	 * @param customNames A collection of attribute's custom names.
	 * @param obj         The Java object.
	 * @param fieldInfo   The field info (name, custom name, pattern, locale e
	 *                    timezone).
	 * @return The JSON object.
	 */
	private JsonEntry<?> toJson(List<String> names, List<String> customNames, Object obj, JsonFieldInfo fieldInfo) {
		String name = names.remove(0);
		String customName = customNames.remove(0);

		if (!names.isEmpty()) {
			JsonEntry<?> jsonEntry = new JsonObject(customName);

			if (!fieldInfo.getSerializerName().trim().isEmpty()) {
				try {
					JsonEntry<?> json = serialize(fieldInfo.getSerializerName(), getValue(name, obj));

					jsonEntry.addChildren(json.getChildren());
				} catch (JsonException e) {
				}

				return jsonEntry;
			}

			return jsonEntry.addChild(toJson(names, customNames, getValue(name, obj), fieldInfo));
		}

		Object value = getValue(name, obj);

		JsonEntry<?> jsonEntry = new JsonObject(customName);

		if (!fieldInfo.getSerializerName().trim().isEmpty()) {
			try {
				JsonEntry<?> json = serialize(fieldInfo.getSerializerName(), value);

				jsonEntry.addChildren(json.getChildren());
			} catch (JsonException e) {
			}

			return jsonEntry;
		}

		if (value != null) {
			try {
				return Json.toJson(customName, value, fieldInfo.getPatternInfo());
			} catch (SecurityException | IllegalArgumentException e) {
			}
		}

		return null;
	}

	/**
	 * @param name The attribute's name.
	 * @param obj  The Java object.
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