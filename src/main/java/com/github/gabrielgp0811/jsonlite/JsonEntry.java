/**
 * 
 */
package com.github.gabrielgp0811.jsonlite;

import java.util.Collection;
import java.util.Map.Entry;

import com.github.gabrielgp0811.jsonlite.constants.JsonStrings;
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
import com.github.gabrielgp0811.jsonlite.util.JsonFormatInfo;
import com.github.gabrielgp0811.jsonlite.util.Util;

/**
 * Base class for main implementations of JSON objects (Array, Boolean, Date,
 * Enum, LocalDate, LocalDateTime, LocalTime, Number, Object, String, etc).
 * 
 * @author gabrielgp0811
 * @see JsonBoolean
 * @see JsonCollection
 * @see JsonDate
 * @see JsonEnum
 * @see JsonLocalDate
 * @see JsonLocalDateTime
 * @see JsonLocalTime
 * @see JsonNumber
 * @see JsonObject
 * @see JsonString
 * @see JsonNull
 */
public abstract class JsonEntry<V> implements Entry<String, V> {

	/**
	 * The name of JSON attribute.
	 */
	private String name = null;

	/**
	 * The value of JSON attribute.
	 */
	private V value = null;

	/**
	 * The format info (pattern, locale and timezone).
	 */
	protected JsonFormatInfo info = null;

	/**
	 * Flag responsible for checking if this JSON object is a child of other JSON
	 * object.
	 */
	private boolean objectChild = false;

	/**
	 * Flag responsible for checking if this JSON object is an array child.
	 */
	private boolean arrayChild = false;

	/**
	 * The indent level. Used to print this JSON object in a way for better visualization.
	 * @see #toPrettyString()
	 * @see #toPrettyString(String)
	 */
	protected int indentLevel = 0;

	/**
	 * The tabulation used for indentation. Default is <strong>\t</strong>.
	 */
	protected String tab = JsonStrings.TAB;

	/**
	 * 
	 */
	public JsonEntry() {

	}

	/**
	 * @param value The value of JSON attribute.
	 */
	public JsonEntry(V value) {
		this(null, value);
	}

	/**
	 * @param name  The name of JSON attribute.
	 * @param value The value of JSON attribute.
	 */
	public JsonEntry(String name, V value) {
		this(name, value, (String) null);
	}

	/**
	 * @param name    The name of JSON attribute.
	 * @param value   The value of JSON attribute.
	 * @param pattern The pattern.
	 */
	public JsonEntry(String name, V value, String pattern) {
		this(name, value, pattern, null);
	}

	/**
	 * @param name    The name of JSON attribute.
	 * @param value   The value of JSON attribute.
	 * @param pattern The pattern.
	 * @param locale  The locale.
	 */
	public JsonEntry(String name, V value, String pattern, String locale) {
		this(name, value, pattern, locale, null);
	}

	/**
	 * @param name     The name of JSON attribute.
	 * @param value    The value of JSON attribute.
	 * @param pattern  The pattern.
	 * @param locale   The locale.
	 * @param timezone The timezone.
	 */
	public JsonEntry(String name, V value, String pattern, String locale, String timezone) {
		this(name, value, new JsonFormatInfo(pattern, Util.toLocale(locale), Util.toTimeZone(timezone)));
	}

	/**
	 * @param name  The name of JSON attribute.
	 * @param value The value of JSON attribute.
	 * @param info  The format info (pattern, locale and timezone).
	 */
	public JsonEntry(String name, V value, JsonFormatInfo info) {
		this.name = name;
		this.value = value;
		this.info = info;
	}

	/**
	 * @return The name of JSON attribute.
	 */
	public String getName() {
		return name;
	}

	@Override
	public String getKey() {
		return name;
	}

	@Override
	public V getValue() {
		return value;
	}

	@Override
	public V setValue(V value) {
		this.value = value;

		return this.value;
	}

	/**
	 * @return The objectChild.
	 */
	public boolean isObjectChild() {
		return objectChild;
	}

	/**
	 * @param objectChild The objectChild to set.
	 */
	public void setObjectChild(boolean objectChild) {
		this.objectChild = objectChild;
	}

	/**
	 * @return The arrayChild.
	 */
	public boolean isArrayChild() {
		return arrayChild;
	}

	/**
	 * @param arrayChild The arrayChild to set.
	 */
	public void setArrayChild(boolean arrayChild) {
		this.arrayChild = arrayChild;
	}

	/**
	 * @return The indentLevel.
	 */
	public int getIndentLevel() {
		return indentLevel;
	}

	/**
	 * @param indentLevel The indentLevel to set.
	 */
	public void setIndentLevel(int indentLevel) {
		this.indentLevel = indentLevel;
	}

	/**
	 * Add a JSON child to this JSON object.
	 * 
	 * @param obj The JSON child object.
	 * @return This JSON object.
	 */
	public JsonEntry<V> addObject(V obj) {
		return addObject(null, obj);
	}

	/**
	 * Add a JSON child to this JSON object.
	 * 
	 * @param name The name of child JSON object.
	 * @param obj  The JSON child object.
	 * @return This JSON object.
	 */
	public JsonEntry<V> addObject(String name, V obj) {
		return addObject(name, obj, (String) null);
	}

	/**
	 * Add a JSON child to this JSON object.
	 * 
	 * @param name    The name of child JSON object.
	 * @param obj     The JSON child object.
	 * @param pattern The pattern.
	 * @return This JSON object.
	 */
	public JsonEntry<V> addObject(String name, V obj, String pattern) {
		return addObject(name, obj, pattern, null);
	}

	/**
	 * Add a JSON child to this JSON object.
	 * 
	 * @param name    The name of child JSON object.
	 * @param obj     The JSON child object.
	 * @param pattern The pattern.
	 * @param locale  The locale.
	 * @return This JSON object.
	 */
	public JsonEntry<V> addObject(String name, V obj, String pattern, String locale) {
		return addObject(name, obj, pattern, locale, null);
	}

	/**
	 * Add a JSON child to this JSON object.
	 * 
	 * @param name     The name of child JSON object.
	 * @param obj      The JSON child object.
	 * @param pattern  The pattern.
	 * @param locale   The locale.
	 * @param timezone The timezone.
	 * @return This JSON object.
	 */
	public JsonEntry<V> addObject(String name, V obj, String pattern, String locale, String timezone) {
		return addObject(name, obj, new JsonFormatInfo(pattern, Util.toLocale(locale), Util.toTimeZone(timezone)));
	}

	/**
	 * Add a JSON child to this JSON object.
	 * 
	 * @param name The name of child JSON object.
	 * @param obj  The JSON child object.
	 * @param info The format info (pattern, locale and timezone).
	 * @return This JSON object.
	 */
	public abstract JsonEntry<V> addObject(String name, Object obj, JsonFormatInfo info);

	/**
	 * Add a JSON child to this JSON object.
	 * 
	 * @param json The JSON child object.
	 * @return This JSON object.
	 */
	public abstract JsonEntry<V> addChild(JsonEntry<?> json);

	/**
	 * Add JSON children to this JSON object.
	 * 
	 * @param jsons The JSON children objects.
	 * @return This JSON object.
	 */
	public abstract JsonEntry<V> addChildren(JsonEntry<?>... jsons);

	/**
	 * Add JSON children to this JSON object.
	 * 
	 * @param jsons The JSON children objects.
	 * @return This JSON object.
	 */
	public abstract JsonEntry<V> addChildren(Collection<JsonEntry<?>> jsons);

	/**
	 * Get child JSON object by <code>index</code>.
	 * 
	 * @param index The index.
	 * @return The child JSON object.
	 */
	public abstract JsonEntry<?> getChild(int index);

	/**
	 * Get child JSON object by <code>name</code>.
	 * 
	 * @param name The name.
	 * @return The child JSON object.
	 */
	public abstract JsonEntry<?> getChild(String name);

	/**
	 * Removes a child JSON object by <code>index</code>.
	 * 
	 * @param index The index.
	 * @return The child JSON object.
	 */
	public abstract JsonEntry<?> removeChild(int index);

	/**
	 * Removes a child JSON object by <code>name</code>.
	 * 
	 * @param name The name.
	 * @return The child JSON object.
	 */
	public abstract JsonEntry<?> removeChild(String name);

	/**
	 * @return The children JSON objects of this JSON object.
	 */
	public abstract Collection<JsonEntry<?>> getChildren();

	/**
	 * @return The amount of children JSON objects of this JSON object.
	 */
	public int childrenSize() {
		Collection<JsonEntry<?>> children = getChildren();

		if (children != null) {
			return children.size();
		}

		return 0;
	}

	/**
	 * Checks if this JSON object contains a child JSON object with
	 * <code>name</code>.
	 * 
	 * @param name The name.
	 * @return <strong><span style="color:#7f0055">true</span></strong> if exists;
	 *         <strong><span style="color:#7f0055">false</span></strong> otherwise.
	 */
	public boolean isChildPresent(String name) {
		if (name == null || name.trim().isEmpty()) {
			return false;
		}

		Collection<JsonEntry<?>> children = getChildren();

		if (children != null) {
			return children.stream().filter(child -> child.getKey().equals(name)).findAny().isPresent();
		}

		return false;
	}

	/**
	 * Checks if this JSON object contains a child JSON object retrieved by
	 * <code>index</code>.
	 * 
	 * @param index The index.
	 * @return <strong><span style="color:#7f0055">true</span></strong> if exists;
	 *         <strong><span style="color:#7f0055">false</span></strong> otherwise.
	 */
	public boolean isChildPresent(int index) {
		if (index < 0) {
			return false;
		}

		Collection<JsonEntry<?>> children = getChildren();

		return children != null && index <= children.size() - 1;
	}

	/**
	 * Convert this JSON object into a Java object.
	 * 
	 * @return The Java object.
	 */
	public abstract Object toJavaObject();

	/**
	 * Convert this JSON object into a Java object of class <code>clazz</code> using
	 * format informations (pattern, locale and timezone).
	 * 
	 * @param <T>   The <code>clazz</code> generic type.
	 * @param clazz Class for conversion.
	 * @param info  The format info (pattern, locale and timezone).
	 * @return The Java object.
	 */
	public abstract <T> T toJavaObject(Class<T> clazz, JsonFormatInfo info);

	/**
	 * Convert this JSON object into a Java collection.
	 * 
	 * @return The Java collection.
	 */
	public abstract Collection<Object> toJavaCollection();

	/**
	 * Convert this JSON object into a Java collection of class <code>clazz</code>
	 * using format informations (pattern, locale and timezone).
	 * 
	 * @param <T>   The <code>clazz</code> generic type.
	 * @param clazz Class for conversion.
	 * @param info  The format info (pattern, locale and timezone).
	 * @return The Java collection.
	 */
	public abstract <T> Collection<T> toJavaCollection(Class<T> clazz, JsonFormatInfo info);

	/**
	 * Convert this JSON object into a Java object of class <code>clazz</code>.
	 * <p>
	 * Invoke this method is the same as <code>toJavaObject(clazz, info)</code>.
	 * </p>
	 * 
	 * @param <T>   The <code>clazz</code> generic type.
	 * @param clazz Class for conversion.
	 * @return The Java object.
	 * @see #info
	 * @see #toJavaCollection(Class, JsonFormatInfo)
	 */
	public <T> T toJavaObject(Class<T> clazz) {
		return toJavaObject(clazz, info);
	}

	/**
	 * Convert this JSON object into a Java object of class <code>clazz</code> using
	 * <code>pattern</code>.
	 * <p>
	 * Invoke this method is the same as
	 * <code>toJavaObject(clazz, pattern, <strong><span style=
	 * "color:#7f0055">null</span></strong>)</code>.
	 * </p>
	 * 
	 * @param <T>     The <code>clazz</code> generic type.
	 * @param clazz   Class for conversion.
	 * @param pattern The pattern.
	 * @return The Java object.
	 * @see #toJavaObject(Class, String, String)
	 */
	public <T> T toJavaObject(Class<T> clazz, String pattern) {
		return toJavaObject(clazz, pattern, null);
	}

	/**
	 * Convert this JSON object into a Java object of class <code>clazz</code> using
	 * <code>pattern</code> and <code>locale</code>.
	 * <p>
	 * Invoke this method is the same as
	 * <code>toJavaObject(clazz, pattern, locale, <strong><span style=
	 * "color:#7f0055">null</span></strong>)</code>.
	 * </p>
	 * 
	 * @param <T>     The <code>clazz</code> generic type.
	 * @param clazz   Class for conversion.
	 * @param pattern The pattern.
	 * @param locale  The locale.
	 * @return The Java object.
	 * @see #toJavaObject(Class, String, String, String)
	 */
	public <T> T toJavaObject(Class<T> clazz, String pattern, String locale) {
		return toJavaObject(clazz, pattern, locale, null);
	}

	/**
	 * Convert this JSON object into a Java object of class <code>clazz</code> using
	 * <code>pattern</code>, <code>locale</code> and <code>timezone</code>.
	 * <p>
	 * Invoke this method is the same as
	 * <code>toJavaObject(clazz, <strong><span style=
	 * "color:#7f0055">new</span></strong> JsonFormatInfo(pattern, Util.toLocale(locale), Util.toTimeZone(timezone)))
	 * </code>.
	 * </p>
	 * 
	 * @param <T>      The <code>clazz</code> generic type.
	 * @param clazz    Class for conversion.
	 * @param pattern  The pattern.
	 * @param locale   The locale.
	 * @param timezone The timezone.
	 * @return The Java object.
	 * @see JsonFormatInfo
	 * @see Util#toLocale(String)
	 * @see Util#toTimeZone(String)
	 * @see #toJavaObject(Class, JsonFormatInfo)
	 */
	public <T> T toJavaObject(Class<T> clazz, String pattern, String locale, String timezone) {
		return toJavaObject(clazz, new JsonFormatInfo(pattern, Util.toLocale(locale), Util.toTimeZone(timezone)));
	}

	/**
	 * Convert this JSON object into a Java collection of class <code>clazz</code>.
	 * <p>
	 * Invoke this method is the same as <code>toJavaCollection(clazz, info)</code>.
	 * </p>
	 * 
	 * @param <T>   The <code>clazz</code> generic type.
	 * @param clazz Class for conversion.
	 * @return The Java collection.
	 * @see #info
	 * @see #toJavaCollection(Class, JsonFormatInfo)
	 */
	public <T> Collection<T> toJavaCollection(Class<T> clazz) {
		return toJavaCollection(clazz, info);
	}

	/**
	 * Convert this JSON object in a way for better visualization.
	 * 
	 * @param tab The string representing tabulation.
	 * @return The JSON string.
	 */
	public String toPrettyString(String tab) {
		if (tab != null)
			this.tab = tab;

		return this.toPrettyString();
	}

	/**
	 * Convert this JSON object in a way for better visualization.
	 * 
	 * @return The JSON string.
	 */
	public String toPrettyString() {
		return this.toString();
	}

}