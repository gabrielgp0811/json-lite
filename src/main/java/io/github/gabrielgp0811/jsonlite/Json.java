/**
 * 
 */
package io.github.gabrielgp0811.jsonlite;

import java.io.Serializable;
import java.lang.reflect.Array;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;

import io.github.gabrielgp0811.jsonlite.constants.JsonStrings;
import io.github.gabrielgp0811.jsonlite.converter.Converter;
import io.github.gabrielgp0811.jsonlite.converter.impl.JsonDeserializerImpl;
import io.github.gabrielgp0811.jsonlite.exception.JsonException;
import io.github.gabrielgp0811.jsonlite.impl.JsonBoolean;
import io.github.gabrielgp0811.jsonlite.impl.JsonCollection;
import io.github.gabrielgp0811.jsonlite.impl.JsonDate;
import io.github.gabrielgp0811.jsonlite.impl.JsonEnum;
import io.github.gabrielgp0811.jsonlite.impl.JsonLocalDate;
import io.github.gabrielgp0811.jsonlite.impl.JsonLocalDateTime;
import io.github.gabrielgp0811.jsonlite.impl.JsonLocalTime;
import io.github.gabrielgp0811.jsonlite.impl.JsonNull;
import io.github.gabrielgp0811.jsonlite.impl.JsonNumber;
import io.github.gabrielgp0811.jsonlite.impl.JsonObject;
import io.github.gabrielgp0811.jsonlite.impl.JsonString;
import io.github.gabrielgp0811.jsonlite.serializer.Serializer;
import io.github.gabrielgp0811.jsonlite.serializer.impl.JsonSerializerImpl;
import io.github.gabrielgp0811.jsonlite.util.JsonPatternInfo;
import io.github.gabrielgp0811.jsonlite.util.Util;

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
		Converter<String, JsonEntry<?>> deserializer = new JsonDeserializerImpl();

		try {
			return deserializer.convert(json);
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
	 * @see #toJson(String, Object, JsonPatternInfo)
	 */
	public static JsonEntry<?> toJson(String name, Object obj, String pattern, String locale, String timezone) {
		return toJson(name, obj, new JsonPatternInfo(pattern, Util.toLocale(locale), Util.toTimeZone(timezone)));
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
	public static JsonEntry<?> toJson(String name, Object obj, JsonPatternInfo info) {
		JsonEntry<?> json = null;

		if (obj == null) {
			json = new JsonNull(name == null || name.trim().isEmpty() ? JsonStrings.NULL_NAME : name);
		} else if (Util.isBoolean(obj)) {
			json = new JsonBoolean(name == null || name.trim().isEmpty() ? JsonStrings.BOOLEAN_NAME : name,
					(boolean) obj);
		} else if (Util.isArray(obj)) {
			Object[] array = new Object[Array.getLength(obj)];

			for (int i = 0; i < array.length; i++) {
				array[i] = Array.get(obj, i);
			}

			json = new JsonCollection(name == null || name.trim().isEmpty() ? JsonStrings.COLLECTION_NAME : name,
					new ArrayList<>(Arrays.asList(array)), info);
		} else if (Util.isCollection(obj)) {
			json = new JsonCollection(name == null || name.trim().isEmpty() ? JsonStrings.COLLECTION_NAME : name,
					(Collection<?>) obj, info);
		} else if (Util.isDate(obj)) {
			json = new JsonDate(name == null || name.trim().isEmpty() ? JsonStrings.DATE_NAME : name, (Date) obj, info);
		} else if (Util.isEnum(obj)) {
			json = new JsonEnum(name == null || name.trim().isEmpty() ? JsonStrings.ENUM_NAME : name, (Enum<?>) obj);
		} else if (Util.isLocalDate(obj)) {
			json = new JsonLocalDate(name == null || name.trim().isEmpty() ? JsonStrings.LOCALDATE_NAME : name,
					(LocalDate) obj, info);
		} else if (Util.isLocalDateTime(obj)) {
			json = new JsonLocalDateTime(name == null || name.trim().isEmpty() ? JsonStrings.LOCALDATETIME_NAME : name,
					(LocalDateTime) obj, info);
		} else if (Util.isLocalTime(obj)) {
			json = new JsonLocalTime(name == null || name.trim().isEmpty() ? JsonStrings.LOCALTIME_NAME : name,
					(LocalTime) obj, info);
		} else if (Util.isNumber(obj)) {
			json = new JsonNumber(name == null || name.trim().isEmpty() ? JsonStrings.NUMBER_NAME : name, (Number) obj);
		} else if (Util.isCharacter(obj) || Util.isString(obj)) {
			json = new JsonString(name == null || name.trim().isEmpty() ? JsonStrings.STRING_NAME : name,
					obj.toString());
		} else {
			if (name == null || name.trim().isEmpty()) {
				if (Util.isMap(obj)) {
					name = JsonStrings.OBJECT_NAME;
				} else {
					name = obj.getClass().getSimpleName();
	
					name = name.substring(0, 1).toLowerCase().concat(name.substring(1));
				}
			}

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
	 * @see #toJavaObject(String, Class, JsonPatternInfo)
	 */
	public static <T> T toJavaObject(String json, Class<T> clazz, String pattern, String locale, String timezone) {
		return toJavaObject(json, clazz,
				new JsonPatternInfo(pattern, Util.toLocale(locale), Util.toTimeZone(timezone)));
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
	 * @see #toJavaObject(JsonEntry, Class, JsonPatternInfo)
	 */
	public static <T> T toJavaObject(String json, Class<T> clazz, JsonPatternInfo info) {
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
	 * @see #toJavaObject(String, Object, JsonPatternInfo)
	 */
	public static <T> T toJavaObject(String json, T obj, String pattern, String locale, String timezone) {
		return toJavaObject(json, obj, new JsonPatternInfo(pattern, Util.toLocale(locale), Util.toTimeZone(timezone)));
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
	 * @see #toJavaObject(JsonEntry, Object, JsonPatternInfo)
	 */
	public static <T> T toJavaObject(String json, T obj, JsonPatternInfo info) {
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
	 * @see #toJavaObject(JsonEntry, Class, JsonPatternInfo)
	 */
	public static <T> T toJavaObject(JsonEntry<?> json, Class<T> clazz, String pattern, String locale,
			String timezone) {
		return toJavaObject(json, clazz,
				new JsonPatternInfo(pattern, Util.toLocale(locale), Util.toTimeZone(timezone)));
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
	public static <T> T toJavaObject(JsonEntry<?> json, Class<T> clazz, JsonPatternInfo info) {
		if (json == null || clazz == null) {
			return null;
		}

		return json.toJavaObject(clazz, info);
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
	 * @see #toJavaObject(JsonEntry, Object, JsonPatternInfo)
	 */
	public static <T> T toJavaObject(JsonEntry<?> json, T obj, String pattern, String locale, String timezone) {
		return toJavaObject(json, obj, new JsonPatternInfo(pattern, Util.toLocale(locale), Util.toTimeZone(timezone)));
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
	 * @see #toJavaObject(JsonEntry, Class, JsonPatternInfo)
	 */
	@SuppressWarnings("unchecked")
	public static <T> T toJavaObject(JsonEntry<?> json, T obj, JsonPatternInfo info) {
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
	 * @see #toString(String, Object, JsonPatternInfo)
	 */
	public static String toString(String name, Object obj, String pattern, String locale, String timezone) {
		return toString(name, obj, new JsonPatternInfo(pattern, Util.toLocale(locale), Util.toTimeZone(timezone)));
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
	 * @see #toJson(String, Object, JsonPatternInfo)
	 */
	public static String toString(String name, Object obj, JsonPatternInfo info) {
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
		return toPrettyString(name, obj, (String) null);
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
	public static String toPrettyString(String name, Object obj, String pattern, String locale) {
		return toPrettyString(name, obj, pattern, locale, null);
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
	 * @see #toString(String, Object, JsonPatternInfo)
	 */
	public static String toPrettyString(String name, Object obj, String pattern, String locale, String timezone) {
		return toPrettyString(name, obj,
				new JsonPatternInfo(pattern, Util.toLocale(locale), Util.toTimeZone(timezone)));
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
	 * @param tab      The string representing tabulation.
	 * @return The JSON string.
	 * @see Util#toLocale(String)
	 * @see Util#toTimeZone(String)
	 * @see #toString(String, Object, JsonPatternInfo)
	 */
	public static String toPrettyString(String name, Object obj, String pattern, String locale, String timezone,
			String tab) {
		return toPrettyString(name, obj,
				new JsonPatternInfo(pattern, Util.toLocale(locale), Util.toTimeZone(timezone)), tab);
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
	 * @see #toJson(String, Object, JsonPatternInfo)
	 */
	public static String toPrettyString(String name, Object obj, JsonPatternInfo info) {
		return toJson(name, obj, info).toPrettyString();
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
	 * @param tab  The string representing tabulation.
	 * @return The JSON string.
	 * @see #toJson(String, Object, JsonPatternInfo)
	 */
	public static String toPrettyString(String name, Object obj, JsonPatternInfo info, String tab) {
		return toJson(name, obj, info).toPrettyString(tab);
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
		return prettySerialize(name, obj, JsonStrings.TAB);
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