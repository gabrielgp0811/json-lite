/**
 * 
 */
package com.github.gabrielgp0811.jsonlite;

import java.io.Serializable;
import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;

import com.github.gabrielgp0811.jsonlite.annotation.JsonPattern;
import com.github.gabrielgp0811.jsonlite.constants.JsonStrings;
import com.github.gabrielgp0811.jsonlite.deserializer.Deserializer;
import com.github.gabrielgp0811.jsonlite.deserializer.impl.JsonDeserializerImpl;
import com.github.gabrielgp0811.jsonlite.exception.JsonException;
import com.github.gabrielgp0811.jsonlite.impl.JsonBoolean;
import com.github.gabrielgp0811.jsonlite.impl.JsonCollection;
import com.github.gabrielgp0811.jsonlite.impl.JsonDate;
import com.github.gabrielgp0811.jsonlite.impl.JsonEnum;
import com.github.gabrielgp0811.jsonlite.impl.JsonLocalDate;
import com.github.gabrielgp0811.jsonlite.impl.JsonLocalDateTime;
import com.github.gabrielgp0811.jsonlite.impl.JsonLocalTime;
import com.github.gabrielgp0811.jsonlite.impl.JsonNull;
import com.github.gabrielgp0811.jsonlite.impl.JsonNumber;
import com.github.gabrielgp0811.jsonlite.impl.JsonObject;
import com.github.gabrielgp0811.jsonlite.impl.JsonString;
import com.github.gabrielgp0811.jsonlite.serializer.Serializer;
import com.github.gabrielgp0811.jsonlite.serializer.impl.JsonSerializerImpl;
import com.github.gabrielgp0811.jsonlite.util.JsonFormatInfo;
import com.github.gabrielgp0811.jsonlite.util.Util;

/**
 * Main class responsible for converting Java objects (Array, Boolean, Date,
 * Enum, LocalDate, LocalDateTime, LocalTime, Number, Object, String, etc) into
 * JSON objects.
 * 
 * @author gabrielgp0811
 */
public final class Json implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7419803458091368828L;

	/**
	 * 
	 */
	private Json() {

	}

	/**
	 * Converts the JSON string <code>json</code> into a JSON object.
	 * 
	 * @param json The JSON string.
	 * @return The JSON object.
	 */
	public static JsonEntry<?> fromJson(String json) {
		Deserializer deserializer = new JsonDeserializerImpl();

		try {
			return deserializer.deserialize(json);
		} catch (JsonException e) {
		}

		return null;
	}

	/**
	 * Converts Java object <code>obj</code> into a JSON object.
	 * <p>
	 * Invoke this method is the same as <code>toJson(<strong><span style=
	 * "color:#7f0055">null</span></strong>, obj)</code>.
	 * </p>
	 * 
	 * @param obj O objeto.
	 * @return Um objeto da classe Json.
	 * @see #toJson(String, Object)
	 */
	public static JsonEntry<?> toJson(Object obj) {
		return toJson(null, obj);
	}

	/**
	 * Converts Java object <code>obj</code> into a JSON object with name specified
	 * in <code>name</code>.
	 * <p>
	 * Invoke this method is the same as
	 * <code>toJson(name, obj, (String) <strong><span style=
	 * "color:#7f0055">null</span></strong>)</code>.
	 * </p>
	 * 
	 * @param name The JSON object's name.
	 * @param obj  The Java object.
	 * @return The JSON object.
	 * @see #toJson(String, Object, String)
	 */
	public static JsonEntry<?> toJson(String name, Object obj) {
		return toJson(name, obj, (String) null);
	}

	/**
	 * Converts Java object <code>obj</code> into a JSON object with name specified
	 * in <code>name</code> using <code>pattern</code>.
	 * <p>
	 * Invoke this method is the same as
	 * <code>toJson(name, obj, pattern, <strong><span style=
	 * "color:#7f0055">null</span></strong>)</code>.
	 * </p>
	 * 
	 * @param name    The JSON object's name.
	 * @param obj     The Java object.
	 * @param pattern The pattern.
	 * @return The JSON object.
	 * @see #toJson(String, Object, String, String)
	 */
	public static JsonEntry<?> toJson(String name, Object obj, String pattern) {
		return toJson(name, obj, pattern, null);
	}

	/**
	 * Converts Java object <code>obj</code> into a JSON object with name specified
	 * in <code>name</code> using <code>pattern</code> and <code>locale</code>.
	 * <p>
	 * Invoke this method is the same as
	 * <code>toJson(name, obj, pattern, locale, <strong><span style=
	 * "color:#7f0055">null</span></strong>)</code>.
	 * </p>
	 * 
	 * @param name    The JSON object's name.
	 * @param obj     The Java object.
	 * @param pattern The pattern.
	 * @param locale  The locale.
	 * @return The JSON object.
	 * @see #toJson(String, Object, String, String, String)
	 */
	public static JsonEntry<?> toJson(String name, Object obj, String pattern, String locale) {
		return toJson(name, obj, pattern, locale, null);
	}

	/**
	 * Converts Java object <code>obj</code> into a JSON object with name specified
	 * in <code>name</code> using <code>pattern</code>, <code>locale</code> and
	 * <code>timezone</code>.
	 * <p>
	 * Invoke this method is the same as
	 * <code>toJson(name, obj, <strong><span style=
	 * "color:#7f0055">new</span></strong> JsonFormatInfo(pattern, Util.toLocale(locale), Util.toTimeZone(timezone)))</code>.
	 * </p>
	 * 
	 * @param name     The JSON object's name.
	 * @param obj      The Java object.
	 * @param pattern  The pattern.
	 * @param locale   The locale.
	 * @param timezone The timezone.
	 * @return The JSON object.
	 * @see Util#toLocale(String)
	 * @see Util#toTimeZone(String)
	 * @see #toJson(String, Object, JsonFormatInfo)
	 */
	public static JsonEntry<?> toJson(String name, Object obj, String pattern, String locale, String timezone) {
		return toJson(name, obj, new JsonFormatInfo(pattern, Util.toLocale(locale), Util.toTimeZone(timezone)));
	}

	/**
	 * Converts Java object <code>obj</code> into a JSON object with name specified
	 * in <code>name</code> using <code>info</code> (<code>pattern</code>,
	 * <code>locale</code> and <code>timezone</code>).
	 * 
	 * @param name The JSON object's name.
	 * @param obj  The Java object.
	 * @param info The info (pattern, locale and timezone).
	 * @return The JSON object.
	 */
	public static JsonEntry<?> toJson(String name, Object obj, JsonFormatInfo info) {
		JsonEntry<?> json = null;

		if (name == null || name.trim().isEmpty()) {
			name = JsonStrings.VALUE_NAME;
		}

		if (obj == null) {
			json = new JsonNull(name);
		} else if (Util.isBoolean(obj)) {
			json = new JsonBoolean(name, (boolean) obj);
		} else if (Util.isArray(obj)) {
			Object[] array = new Object[Array.getLength(obj)];

			for (int i = 0; i < array.length; i++) {
				array[i] = Array.get(obj, i);
			}

			json = new JsonCollection(name, new ArrayList<>(Arrays.asList(array)), info);
		} else if (Util.isCollection(obj)) {
			json = new JsonCollection(name, (Collection<?>) obj, info);
		} else if (Util.isDate(obj)) {
			json = new JsonDate(name, (Date) obj, info);
		} else if (Util.isEnum(obj)) {
			json = new JsonEnum(name, (Enum<?>) obj);
		} else if (Util.isLocalDate(obj)) {
			json = new JsonLocalDate(name, (LocalDate) obj, info);
		} else if (Util.isLocalDateTime(obj)) {
			json = new JsonLocalDateTime(name, (LocalDateTime) obj, info);
		} else if (Util.isLocalTime(obj)) {
			json = new JsonLocalTime(name, (LocalTime) obj, info);
		} else if (Util.isNumber(obj)) {
			json = new JsonNumber(name, (Number) obj);
		} else if (Util.isCharacter(obj) || Util.isString(obj)) {
			json = new JsonString(name, obj.toString());
		} else {
			json = new JsonObject(name, obj, info);
		}

		return json;
	}

	/**
	 * Converts JSON string <code>json</code> into a
	 * <code>java.util.Collection</code> of class specified in <code>clazz</code>.
	 * <p>
	 * Invoke this method is the same as
	 * <code>toJavaCollection(fromJson(json), clazz)</code>.
	 * </p>
	 * 
	 * @param <T>   The generic class.
	 * @param json  The JSON string.
	 * @param clazz The class.
	 * @return The <code>java.util.Collection</code> of <code>clazz</code>.
	 * @see #fromJson(String)
	 * @see #toJavaCollection(JsonEntry, Class)
	 */
	public static <T> Collection<T> toJavaCollection(String json, Class<T> clazz) {
		if (json == null || json.trim().isEmpty() || clazz == null) {
			return null;
		}

		return toJavaCollection(fromJson(json), clazz);
	}

	/**
	 * Converts JSON string <code>json</code> into a
	 * <code>java.util.Collection</code> of <code>obj</code>'s class.
	 * <p>
	 * Invoke this method is the same as
	 * <code>toJavaCollection(fromJson(json), obj)</code>.
	 * </p>
	 * 
	 * @param <T>  The generic class of <code>obj</code>.
	 * @param json The JSON string.
	 * @param obj  The Java object.
	 * @return The <code>java.util.Collection</code> of <code>obj</code>'s class.
	 * @see #fromJson(String)
	 * @see #toJavaCollection(JsonEntry, Object)
	 */
	public static <T> Collection<T> toJavaCollection(String json, T obj) {
		return toJavaCollection(fromJson(json), obj);
	}

	/**
	 * Converts JSON object <code>json</code> into a
	 * <code>java.util.Collection</code> of class specified in <code>clazz</code>.
	 * 
	 * @param <T>   The generic class.
	 * @param json  The JSON object.
	 * @param clazz The class.
	 * @return The <code>java.util.Collection</code> of <code>clazz</code>.
	 */
	public static <T> Collection<T> toJavaCollection(JsonEntry<?> json, Class<T> clazz) {
		if (json == null || clazz == null) {
			return null;
		}

		Collection<T> result = new ArrayList<>();

		if (Util.isCollection(json)) {
			json.getChildren().stream().map(child -> toJavaObject(child, clazz)).forEach(result::add);
		} else {
			result.add(toJavaObject(json, clazz));
		}

		return result;
	}

	/**
	 * Converts JSON object <code>json</code> into a
	 * <code>java.util.Collection</code> of <code>obj</code>'s class.
	 * <p>
	 * Invoke this method is the same as
	 * <code>(Collection&lt;T&gt;) toJavaCollection(json, obj.getClass())</code>.
	 * </p>
	 * 
	 * @param <T>  The generic class of <code>obj</code>.
	 * @param json The JSON object.
	 * @param obj  The Java object.
	 * @return The <code>java.util.Collection</code> of <code>obj</code>'s class.
	 */
	@SuppressWarnings("unchecked")
	public static <T> Collection<T> toJavaCollection(JsonEntry<?> json, T obj) {
		if (json == null || obj == null) {
			return null;
		}

		return (Collection<T>) toJavaCollection(json, obj.getClass());
	}

	/**
	 * Converts JSON string <code>json</code> into a Java object of class specified
	 * in <code>clazz</code>.
	 * <p>
	 * Invoke this method is the same as
	 * <code>toJavaObject(json, clazz, (String) <strong><span style=
	 * "color:#7f0055">null</span></strong>)</code>.
	 * </p>
	 * 
	 * @param <T>   The generic class.
	 * @param json  The JSON string.
	 * @param clazz The class.
	 * @return The Java object.
	 * @see #toJavaObject(String, Class, String)
	 */
	public static <T> T toJavaObject(String json, Class<T> clazz) {
		return toJavaObject(json, clazz, (String) null);
	}

	/**
	 * Converts JSON string <code>json</code> into a Java object of class specified
	 * in <code>clazz</code> using <code>pattern</code>.
	 * <p>
	 * Invoke this method is the same as
	 * <code>toJavaObject(json, clazz, pattern, <strong><span style=
	 * "color:#7f0055">null</span></strong>)</code>.
	 * </p>
	 * 
	 * @param <T>     The generic class.
	 * @param json    The JSON string.
	 * @param clazz   The class.
	 * @param pattern The pattern.
	 * @return The Java object.
	 * @see #toJavaObject(String, Class, String, String)
	 */
	public static <T> T toJavaObject(String json, Class<T> clazz, String pattern) {
		return toJavaObject(json, clazz, pattern, null);
	}

	/**
	 * Converts JSON string <code>json</code> into a Java object of class specified
	 * in <code>clazz</code> using <code>pattern</code> and <code>locale</code>.
	 * <p>
	 * Invoke this method is the same as
	 * <code>toJavaObject(json, clazz, pattern, locale, <strong><span style=
	 * "color:#7f0055">null</span></strong>)</code>.
	 * </p>
	 * 
	 * @param <T>     The generic class.
	 * @param json    The JSON string.
	 * @param clazz   The class.
	 * @param pattern The pattern.
	 * @param locale  The locale.
	 * @return The Java object.
	 * @see #toJavaObject(String, Class, String, String, String)
	 */
	public static <T> T toJavaObject(String json, Class<T> clazz, String pattern, String locale) {
		return toJavaObject(json, clazz, pattern, locale, null);
	}

	/**
	 * Converts JSON string <code>json</code> into a Java object of class specified
	 * in <code>clazz</code> using <code>pattern</code>, <code>locale</code> and
	 * <code>timezone</code>.
	 * <p>
	 * Invoke this method is the same as
	 * <code>toJavaObject(json, clazz, <strong><span style=
	 * "color:#7f0055">new</span></strong> JsonFormatInfo(pattern, Util.toLocale(locale), Util.toTimeZone(timezone)))</code>.
	 * </p>
	 * 
	 * @param <T>      The generic class.
	 * @param json     The JSON string.
	 * @param clazz    The class.
	 * @param pattern  The pattern.
	 * @param locale   The locale.
	 * @param timezone The timezone.
	 * @return The Java object.
	 * @see Util#toLocale(String)
	 * @see Util#toTimeZone(String)
	 * @see #toJavaObject(String, Class, JsonFormatInfo)
	 */
	public static <T> T toJavaObject(String json, Class<T> clazz, String pattern, String locale, String timezone) {
		return toJavaObject(json, clazz, new JsonFormatInfo(pattern, Util.toLocale(locale), Util.toTimeZone(timezone)));
	}

	/**
	 * Converts JSON string <code>json</code> into a Java object of class specified
	 * in <code>clazz</code> using <code>info</code> (<code>pattern</code>,
	 * <code>locale</code> and <code>timezone</code>).
	 * <p>
	 * Invoke this method is the same as
	 * <code>(T) toJavaObject(fromJson(json), clazz, info)</code>.
	 * </p>
	 * 
	 * @param <T>   The generic class.
	 * @param json  The JSON string.
	 * @param clazz The class.
	 * @param info  The info (pattern, locale and timezone).
	 * @return The Java object.
	 * @see #fromJson(String)
	 * @see #toJavaObject(JsonEntry, Class, JsonFormatInfo)
	 */
	public static <T> T toJavaObject(String json, Class<T> clazz, JsonFormatInfo info) {
		return (T) toJavaObject(fromJson(json), clazz, info);
	}

	/**
	 * Converts JSON string <code>json</code> into a Java object of
	 * <code>obj</code>'s class.
	 * <p>
	 * Invoke this method is the same as
	 * <code>toJavaObject(json, obj, (String) <strong><span style=
	 * "color:#7f0055">null</span></strong>)</code>.
	 * </p>
	 * 
	 * @param <T>  The generic class of <code>obj</code>.
	 * @param json The JSON string.
	 * @param obj  The Java object.
	 * @return The Java object.
	 * @see #toJavaObject(String, Object, String)
	 */
	public static <T> T toJavaObject(String json, T obj) {
		return toJavaObject(json, obj, (String) null);
	}

	/**
	 * Converts JSON string <code>json</code> into a Java object of
	 * <code>obj</code>'s class using <code>pattern</code>.
	 * <p>
	 * Invoke this method is the same as
	 * <code>toJavaObject(json, obj, pattern, <strong><span style=
	 * "color:#7f0055">null</span></strong>)</code>.
	 * </p>
	 * 
	 * @param <T>     The generic class of <code>obj</code>.
	 * @param json    The JSON string.
	 * @param obj     The Java object.
	 * @param pattern The pattern.
	 * @return The Java object.
	 * @see #toJavaObject(String, Class, String, String)
	 */
	public static <T> T toJavaObject(String json, T obj, String pattern) {
		return toJavaObject(json, obj, pattern, null);
	}

	/**
	 * Converts JSON string <code>json</code> into a Java object of
	 * <code>obj</code>'s class using <code>pattern</code> and <code>locale</code>.
	 * <p>
	 * Invoke this method is the same as
	 * <code>toJavaObject(json, obj, pattern, locale, <strong><span style=
	 * "color:#7f0055">null</span></strong>)</code>.
	 * </p>
	 * 
	 * @param <T>     The generic class of <code>obj</code>.
	 * @param json    The JSON string.
	 * @param obj     The Java object.
	 * @param pattern The pattern.
	 * @param locale  The locale.
	 * @return The Java object.
	 * @see #toJavaObject(String, Class, String, String, String)
	 */
	public static <T> T toJavaObject(String json, T obj, String pattern, String locale) {
		return toJavaObject(json, obj, pattern, locale, null);
	}

	/**
	 * Converts JSON string <code>json</code> into a Java object of
	 * <code>obj</code>'s class using <code>pattern</code>, <code>locale</code> and
	 * <code>timezone</code>.
	 * <p>
	 * Invoke this method is the same as
	 * <code>toJavaObject(json, obj, <strong><span style=
	 * "color:#7f0055">new</span></strong> JsonFormatInfo(pattern, Util.toLocale(locale), Util.toTimeZone(timezone)))</code>.
	 * </p>
	 * 
	 * @param <T>      The generic class of <code>obj</code>.
	 * @param json     The JSON string.
	 * @param obj      The Java object.
	 * @param pattern  The pattern.
	 * @param locale   The locale.
	 * @param timezone The timezone.
	 * @return The Java object.
	 * @see Util#toLocale(String)
	 * @see Util#toTimeZone(String)
	 * @see #toJavaObject(String, Object, JsonFormatInfo)
	 */
	public static <T> T toJavaObject(String json, T obj, String pattern, String locale, String timezone) {
		return toJavaObject(json, obj, new JsonFormatInfo(pattern, Util.toLocale(locale), Util.toTimeZone(timezone)));
	}

	/**
	 * Converts JSON string <code>json</code> into a Java object of
	 * <code>obj</code>'s class using <code>info</code> (<code>pattern</code>,
	 * <code>locale</code> and <code>timezone</code>).
	 * <p>
	 * Invoke this method is the same as
	 * <code>toJavaObject(fromJson(json), obj, info)</code>.
	 * </p>
	 * 
	 * @param <T>  The generic class of <code>obj</code>.
	 * @param json The JSON string.
	 * @param obj  The Java object.
	 * @param info The info (pattern, locale and timezone).
	 * @return The Java object.
	 * @see #fromJson(String)
	 * @see #toJavaObject(JsonEntry, Object, JsonFormatInfo)
	 */
	public static <T> T toJavaObject(String json, T obj, JsonFormatInfo info) {
		return toJavaObject(fromJson(json), obj, info);
	}

	/**
	 * Converts JSON object <code>json</code> into a Java object of class specified
	 * in <code>clazz</code>.
	 * <p>
	 * Invoke this method is the same as
	 * <code>toJavaObject(json, clazz, (String) <strong><span style=
	 * "color:#7f0055">null</span></strong>)</code>.
	 * </p>
	 * 
	 * @param <T>   The generic class.
	 * @param json  The JSON object.
	 * @param clazz The class.
	 * @return The Java object.
	 * @see #toJavaObject(JsonEntry, Class, String)
	 */
	public static <T> T toJavaObject(JsonEntry<?> json, Class<T> clazz) {
		return toJavaObject(json, clazz, (String) null);
	}

	/**
	 * Converts JSON object <code>json</code> into a Java object of class specified
	 * in <code>clazz</code> using <code>pattern</code>.
	 * <p>
	 * Invoke this method is the same as
	 * <code>toJavaObject(json, clazz, pattern, <strong><span style=
	 * "color:#7f0055">null</span></strong>)</code>.
	 * </p>
	 * 
	 * @param <T>     The generic class.
	 * @param json    The JSON object.
	 * @param clazz   The class.
	 * @param pattern The pattern.
	 * @return The Java object.
	 * @see #toJavaObject(JsonEntry, Class, String, String)
	 */
	public static <T> T toJavaObject(JsonEntry<?> json, Class<T> clazz, String pattern) {
		return toJavaObject(json, clazz, pattern, null);
	}

	/**
	 * Converts JSON object <code>json</code> into a Java object of class specified
	 * in <code>clazz</code> using <code>pattern</code> and <code>locale</code>.
	 * <p>
	 * Invoke this method is the same as
	 * <code>toJavaObject(json, clazz, pattern, locale, <strong><span style=
	 * "color:#7f0055">null</span></strong>)</code>.
	 * </p>
	 * 
	 * @param <T>     The generic class.
	 * @param json    The JSON object.
	 * @param clazz   The class.
	 * @param pattern The pattern.
	 * @param locale  The locale.
	 * @return The Java object.
	 * @see #toJavaObject(JsonEntry, Class, String, String, String)
	 */
	public static <T> T toJavaObject(JsonEntry<?> json, Class<T> clazz, String pattern, String locale) {
		return toJavaObject(json, clazz, pattern, locale, null);
	}

	/**
	 * Converts JSON object <code>json</code> into a Java object of class specified
	 * in <code>clazz</code> using <code>pattern</code>, <code>locale</code> and
	 * <code>timezone</code>.
	 * <p>
	 * Invoke this method is the same as
	 * <code>toJavaObject(json, clazz, <strong><span style=
	 * "color:#7f0055">new</span></strong> JsonFormatInfo(pattern, Util.toLocale(locale), Util.toTimeZone(timezone)))</code>.
	 * </p>
	 * 
	 * @param <T>      The generic class.
	 * @param json     The JSON object.
	 * @param clazz    The class.
	 * @param pattern  The pattern.
	 * @param locale   The locale.
	 * @param timezone The timezone.
	 * @return The Java object.
	 * @see Util#toLocale(String)
	 * @see Util#toTimeZone(String)
	 * @see #toJavaObject(JsonEntry, Class, JsonFormatInfo)
	 */
	public static <T> T toJavaObject(JsonEntry<?> json, Class<T> clazz, String pattern, String locale,
			String timezone) {
		return toJavaObject(json, clazz, new JsonFormatInfo(pattern, Util.toLocale(locale), Util.toTimeZone(timezone)));
	}

	/**
	 * Converts JSON object <code>json</code> into a Java object of class specified
	 * in <code>clazz</code> using <code>info</code> (<code>pattern</code>,
	 * <code>locale</code> and <code>timezone</code>).
	 * 
	 * @param <T>   The generic class.
	 * @param json  The JSON object.
	 * @param clazz The class.
	 * @param info  The info (pattern, locale and timezone).
	 * @return The Java object.
	 */
	public static <T> T toJavaObject(JsonEntry<?> json, Class<T> clazz, JsonFormatInfo info) {
		if (json == null || clazz == null) {
			return null;
		}

		T result = null;
		try {
			result = clazz.newInstance();
		} catch (InstantiationException | IllegalAccessException e) {
			return null;
		}

		Field[] fields = clazz.getDeclaredFields();

		for (Field field : fields) {
			JsonEntry<?> child = json.getChild(field.getName());

			if (child == null) {
				continue;
			}

			Object value = null;

			String fieldName = field.getName();
			Class<?> fieldType = field.getType();

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

				value = child.toJavaObject(fieldType, info0);
			}

			Util.setValue(result, fieldName, fieldType, value);
		}

		return result;
	}

	/**
	 * Converts JSON object <code>json</code> into a Java object of
	 * <code>obj</code>'s class.
	 * <p>
	 * Invoke this method is the same as
	 * <code>toJavaObject(json, obj, (String) <strong><span style=
	 * "color:#7f0055">null</span></strong>)</code>.
	 * </p>
	 * 
	 * @param <T>  The generic class.
	 * @param json The JSON object.
	 * @param obj  The Java object.
	 * @return The Java object.
	 * @see #toJavaObject(JsonEntry, Object, String)
	 */
	public static <T> T toJavaObject(JsonEntry<?> json, T obj) {
		return toJavaObject(json, obj, (String) null);
	}

	/**
	 * Converts JSON object <code>json</code> into a Java object of
	 * <code>obj</code>'s class using <code>pattern</code>.
	 * <p>
	 * Invoke this method is the same as
	 * <code>toJavaObject(json, obj, pattern, <strong><span style=
	 * "color:#7f0055">null</span></strong>)</code>.
	 * </p>
	 * 
	 * @param <T>     The generic class.
	 * @param json    The JSON object.
	 * @param obj     The Java object.
	 * @param pattern The pattern.
	 * @return The Java object.
	 * @see #toJavaObject(JsonEntry, Object, String, String)
	 */
	public static <T> T toJavaObject(JsonEntry<?> json, T obj, String pattern) {
		return toJavaObject(json, obj, pattern, null);
	}

	/**
	 * Converts JSON object <code>json</code> into a Java object of
	 * <code>obj</code>'s class using <code>pattern</code> and <code>locale</code>.
	 * <p>
	 * Invoke this method is the same as
	 * <code>toJavaObject(json, obj, pattern, locale, <strong><span style=
	 * "color:#7f0055">null</span></strong>))</code>.
	 * </p>
	 * 
	 * @param <T>     The generic class.
	 * @param json    The JSON object.
	 * @param obj     The Java object.
	 * @param pattern The pattern.
	 * @param locale  The locale.
	 * @return The Java object.
	 * @see #toJavaObject(JsonEntry, Object, String, String, String)
	 */
	public static <T> T toJavaObject(JsonEntry<?> json, T obj, String pattern, String locale) {
		return toJavaObject(json, obj, pattern, locale, null);
	}

	/**
	 * Converts JSON object <code>json</code> into a Java object of
	 * <code>obj</code>'s class using <code>pattern</code>, <code>locale</code> and
	 * <code>timezone</code>.
	 * <p>
	 * Invoke this method is the same as
	 * <code>toJavaObject(json, obj, <strong><span style=
	 * "color:#7f0055">new</span></strong>) JsonFormatInfo(pattern, Util.toLocale(locale), Util.toTimeZone(timezone)))</code>.
	 * </p>
	 * 
	 * @param <T>      The generic class.
	 * @param json     The JSON object.
	 * @param obj      The Java object.
	 * @param pattern  The pattern.
	 * @param locale   The locale.
	 * @param timezone The timezone.
	 * @return The Java object.
	 * @see Util#toLocale(String)
	 * @see Util#toTimeZone(String)
	 * @see #toJavaObject(JsonEntry, Object, JsonFormatInfo)
	 */
	public static <T> T toJavaObject(JsonEntry<?> json, T obj, String pattern, String locale, String timezone) {
		return toJavaObject(json, obj, new JsonFormatInfo(pattern, Util.toLocale(locale), Util.toTimeZone(timezone)));
	}

	/**
	 * Converts JSON object <code>json</code> into a Java object of
	 * <code>obj</code>'s class using <code>info</code> (<code>pattern</code>,
	 * <code>locale</code> and <code>timezone</code>).
	 * <p>
	 * Invoke this method is the same as
	 * <code>(T) toJavaObject(json, obj.getClass(), info)</code>.
	 * </p>
	 * 
	 * @param <T>  The generic class of <code>obj</code>.
	 * @param json The JSON object.
	 * @param obj  The Java object.
	 * @param info The info (pattern, locale and timezone).
	 * @return The Java object.
	 * @see #toJavaObject(JsonEntry, Class, JsonFormatInfo)
	 */
	@SuppressWarnings("unchecked")
	public static <T> T toJavaObject(JsonEntry<?> json, T obj, JsonFormatInfo info) {
		if (json == null || obj == null || Util.isCollection(json)) {
			return null;
		}

		return (T) toJavaObject(json, obj.getClass(), info);
	}

	/**
	 * Converts Java object <code>obj</code> into a JSON string.
	 * <p>
	 * Invoke this method is the same as <code>toString(<strong><span style=
	 * "color:#7f0055">null</span></strong>, obj)</code>.
	 * </p>
	 * 
	 * @param obj The Java object.
	 * @return The JSON string.
	 * @see #toString(String, Object)
	 */
	public static String toString(Object obj) {
		return toString(null, obj);
	}

	/**
	 * Converts Java object <code>obj</code> into a JSON string with name specified
	 * in <code>name</code>.
	 * <p>
	 * Invoke this method is the same as
	 * <code>toString(name, obj, (String) <strong><span style=
	 * "color:#7f0055">null</span></strong>)</code>.
	 * </p>
	 * 
	 * @param name The JSON object's name.
	 * @param obj  The Java object.
	 * @return The JSON string.
	 * @see #toString(String, Object, String)
	 */
	public static String toString(String name, Object obj) {
		return toString(name, obj, (String) null);
	}

	/**
	 * Converts Java object <code>obj</code> into a JSON string with name specified
	 * in <code>name</code> using <code>pattern</code>.
	 * <p>
	 * Invoke this method is the same as
	 * <code>toString(name, obj, pattern, <strong><span style=
	 * "color:#7f0055">null</span></strong>)</code>.
	 * </p>
	 * 
	 * @param name    The JSON object's name.
	 * @param obj     The Java object.
	 * @param pattern The pattern.
	 * @return The JSON string.
	 * @see #toString(String, Object, String, String)
	 */
	public static String toString(String name, Object obj, String pattern) {
		return toString(name, obj, pattern, null);
	}

	/**
	 * Converts Java object <code>obj</code> into a JSON string with name specified
	 * in <code>name</code> using <code>pattern</code> and <code>locale</code>.
	 * <p>
	 * Invoke this method is the same as
	 * <code>toString(name, obj, pattern, locale, <strong><span style=
	 * "color:#7f0055">null</span></strong>)</code>.
	 * </p>
	 * 
	 * @param name    The JSON object's name.
	 * @param obj     The Java object.
	 * @param pattern The pattern.
	 * @param locale  The locale.
	 * @return The JSON string.
	 * @see #toString(String, Object, String, String, String)
	 */
	public static String toString(String name, Object obj, String pattern, String locale) {
		return toString(name, obj, pattern, locale, null);
	}

	/**
	 * Converts Java object <code>obj</code> into a JSON string with name specified
	 * in <code>name</code> using <code>pattern</code>, <code>locale</code> and
	 * <code>timezone</code>.
	 * <p>
	 * Invoke this method is the same as
	 * <code>toString(name, obj, <strong><span style=
	 * "color:#7f0055">new</span></strong> JsonFormatInfo(pattern, Util.toLocale(locale), Util.toTimeZone(timezone)))</code>.
	 * </p>
	 * 
	 * @param name     The JSON object's name.
	 * @param obj      The Java object.
	 * @param pattern  The pattern.
	 * @param locale   The locale.
	 * @param timezone The timezone.
	 * @return The JSON string.
	 * @see Util#toLocale(String)
	 * @see Util#toTimeZone(String)
	 * @see #toString(String, Object, JsonFormatInfo)
	 */
	public static String toString(String name, Object obj, String pattern, String locale, String timezone) {
		return toString(name, obj, new JsonFormatInfo(pattern, Util.toLocale(locale), Util.toTimeZone(timezone)));
	}

	/**
	 * Converts Java object <code>obj</code> into a JSON string with name specified
	 * in <code>name</code> using <code>info</code> (<code>pattern</code>,
	 * <code>locale</code> and <code>timezone</code>).
	 * <p>
	 * Invoke this method is the same as
	 * <code>toJson(name, obj, info).toString()</code>.
	 * </p>
	 * 
	 * @param name The JSON object's name.
	 * @param obj  The Java object.
	 * @param info The info (pattern, locale and timezone).
	 * @return The JSON string.
	 * @see #toJson(String, Object, JsonFormatInfo)
	 */
	public static String toString(String name, Object obj, JsonFormatInfo info) {
		return toJson(name, obj, info).toString();
	}

	/**
	 * Converts Java object <code>obj</code> into a JSON pretty string.
	 * <p>
	 * Invoke this method is the same as <code>toPrettyString(<strong><span style=
	 * "color:#7f0055">null</span></strong>, obj)</code>.
	 * </p>
	 * 
	 * @param obj The Java object.
	 * @return The JSON pretty string.
	 * @see #toPrettyString(String, Object)
	 */
	public static String toPrettyString(Object obj) {
		return toPrettyString(null, obj);
	}

	/**
	 * Converts Java object <code>obj</code> into a JSON pretty string with name
	 * specified in <code>name</code>.
	 * <p>
	 * Invoke this method is the same as
	 * <code>toPrettyString(name, obj, <strong><span style=
	 * "color:#7f0055">null</span></strong>)</code>.
	 * </p>
	 * 
	 * @param name The JSON object's name.
	 * @param obj  The Java object.
	 * @return The JSON pretty string.
	 * @see #toPrettyString(String, Object, String)
	 */
	public static String toPrettyString(String name, Object obj) {
		return toPrettyString(name, obj, null);
	}

	/**
	 * Converts Java object <code>obj</code> into a JSON pretty string with name
	 * specified in <code>name</code> using <code>pattern</code>.
	 * <p>
	 * Invoke this method is the same as
	 * <code>toJson(name, obj, pattern).toPrettyString()</code>.
	 * </p>
	 * 
	 * @param name    The JSON object's name.
	 * @param obj     The Java object.
	 * @param pattern The pattern.
	 * @return The JSON pretty string.
	 */
	public static String toPrettyString(String name, Object obj, String pattern) {
		return toJson(name, obj, pattern).toPrettyString();
	}

	/**
	 * Converts Java object <code>obj</code> into a JSON pretty string with name
	 * specified in <code>name</code> using <code>pattern</code>.
	 * <p>
	 * Invoke this method is the same as
	 * <code>toJson(name, obj, pattern).toPrettyString(tab)</code>.
	 * </p>
	 * 
	 * @param name    The JSON object's name.
	 * @param obj     The Java object.
	 * @param pattern The pattern.
	 * @param tab     The string representing tabulation.
	 * @return The JSON pretty string.
	 */
	public static String toPrettyString(String name, Object obj, String pattern, String tab) {
		return toJson(name, obj, pattern).toPrettyString(tab);
	}

	/**
	 * Converts Java object <code>obj</code> into a JSON string using serializer of
	 * name specified in <code>name</code>.
	 * 
	 * @param name The serializer's name.
	 * @param obj  The Java object, which contains the serializer.
	 * @return The JSON string.
	 */
	public static String serialize(String name, Object obj) {
		try {
			return fromSerializer(name, obj).toString();
		} catch (NullPointerException e) {
		}

		return null;
	}

	/**
	 * Converts Java object <code>obj</code> into a JSON pretty string using
	 * serializer of name specified in <code>name</code>.
	 * 
	 * @param name The serializer's name.
	 * @param obj  The Java object, which contains the serializer.
	 * @return The JSON pretty string.
	 */
	public static String prettySerialize(String name, Object obj) {
		try {
			return fromSerializer(name, obj).toPrettyString();
		} catch (NullPointerException e) {
		}

		return null;
	}

	/**
	 * Converts Java object <code>obj</code> into a JSON pretty string using
	 * serializer of name specified in <code>name</code>.
	 * 
	 * @param name The serializer's name.
	 * @param obj  The Java object, which contains the serializer.
	 * @param tab  The string representing tabulation.
	 * @return The JSON pretty string.
	 */
	public static String prettySerialize(String name, Object obj, String tab) {
		try {
			return fromSerializer(name, obj).toPrettyString(tab);
		} catch (NullPointerException e) {
		}

		return null;
	}

	/**
	 * Converts Java object <code>obj</code> into a JSON object using serializer of
	 * name specified in <code>name</code>.
	 * 
	 * @param name The serializer's name.
	 * @param obj  The Java object, which contains the serializer.
	 * @return The JSON object.
	 */
	public static JsonEntry<?> fromSerializer(String name, Object obj) {
		try {
			Serializer serializer = new JsonSerializerImpl();

			return serializer.serialize(name, obj);
		} catch (JsonException e) {
		}

		return null;
	}

}