/**
 * 
 */
package io.github.gabrielgp0811.jsonlite.impl;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.DateTimeException;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;

import io.github.gabrielgp0811.jsonlite.JsonEntry;
import io.github.gabrielgp0811.jsonlite.constants.JsonStrings;
import io.github.gabrielgp0811.jsonlite.util.JsonFormatInfo;
import io.github.gabrielgp0811.jsonlite.util.Util;

/**
 * JSON class for <code>java.util.Date</code> and <code>java.sql.Date</code>.
 * 
 * @author gabrielgp0811
 */
public class JsonDate extends JsonEntry<Date> {

	/**
	 * 
	 */
	public JsonDate() {

	}

	/**
	 * @param value The value to set
	 */
	public JsonDate(Date value) {
		super(value);
	}

	/**
	 * @param value   The value to set
	 * @param pattern The pattern to set
	 */
	public JsonDate(Date value, String pattern) {
		super(null, value, pattern);
	}

	/**
	 * @param value   The value to set
	 * @param pattern The pattern to set
	 * @param locale  The locale to set
	 */
	public JsonDate(Date value, String pattern, String locale) {
		super(null, value, pattern, locale);
	}

	/**
	 * @param value    The value to set
	 * @param pattern  The pattern to set
	 * @param locale   The locale to set
	 * @param timezone The timezone to set
	 */
	public JsonDate(Date value, String pattern, String locale, String timezone) {
		super(null, value, pattern, locale, timezone);
	}

	/**
	 * @param value The value to set
	 * @param info  The info to set
	 */
	public JsonDate(Date value, JsonFormatInfo info) {
		super(null, value, info);
	}

	/**
	 * @param name  The name to set
	 * @param value The value to set
	 */
	public JsonDate(String name, Date value) {
		super(name, value);
	}

	/**
	 * @param name    The name to set
	 * @param value   The value to set
	 * @param pattern The pattern to set
	 */
	public JsonDate(String name, Date value, String pattern) {
		super(name, value, pattern);
	}

	/**
	 * @param name    The name to set
	 * @param value   The value to set
	 * @param pattern The pattern to set
	 * @param locale  The locale to set
	 */
	public JsonDate(String name, Date value, String pattern, String locale) {
		super(name, value, pattern, locale);
	}

	/**
	 * @param name     The name to set
	 * @param value    The value to set
	 * @param pattern  The pattern to set
	 * @param locale   The locale to set
	 * @param timezone The timezone to set
	 */
	public JsonDate(String name, Date value, String pattern, String locale, String timezone) {
		super(name, value, pattern, locale, timezone);
	}

	/**
	 * @param name  The name to set
	 * @param value The value to set
	 * @param info  The info to set
	 */
	public JsonDate(String name, Date value, JsonFormatInfo info) {
		super(name, value, info);
	}

	@Override
	public JsonEntry<Date> addObject(String name, Object obj, JsonFormatInfo info) {
		return this;
	}

	@Override
	public JsonEntry<Date> addChild(JsonEntry<?> json) {
		return this;
	}

	@Override
	public JsonEntry<Date> addChildren(JsonEntry<?>... jsons) {
		return this;
	}

	@Override
	public JsonEntry<Date> addChildren(Collection<JsonEntry<?>> jsons) {
		return this;
	}

	@Override
	public JsonEntry<?> getChild(int index) {
		return null;
	}

	@Override
	public JsonEntry<?> getChild(String name) {
		return null;
	}

	@Override
	public JsonEntry<?> removeChild(int index) {
		return null;
	}

	@Override
	public JsonEntry<?> removeChild(String name) {
		return null;
	}

	@Override
	public Collection<JsonEntry<?>> getChildren() {
		return Collections.emptyList();
	}

	@Override
	public Date toJavaObject() {
		return (Date) getValue();
	}

	@Override
	@SuppressWarnings("unchecked")
	public <T> T toJavaObject(Class<T> clazz, JsonFormatInfo info) {
		if (clazz == null) {
			return null;
		}

		if (Util.isLocalDate(clazz)) {
			try {
				return (T) toJavaObject().toInstant().atZone(info.getTimezone().toZoneId()).toLocalDate();
			} catch (NullPointerException | DateTimeException e) {
			}

			return (T) toJavaObject().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
		}

		if (Util.isLocalTime(clazz)) {
			try {
				return (T) toJavaObject().toInstant().atZone(info.getTimezone().toZoneId()).toLocalTime();
			} catch (NullPointerException | DateTimeException e) {
			}

			return (T) toJavaObject().toInstant().atZone(ZoneId.systemDefault()).toLocalTime();
		}

		if (Util.isLocalDateTime(clazz)) {
			try {
				return (T) toJavaObject().toInstant().atZone(info.getTimezone().toZoneId()).toLocalDateTime();
			} catch (NullPointerException | DateTimeException e) {
			}

			return (T) toJavaObject().toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
		}

		if (Util.isDate(clazz)) {
			return (T) getValue();
		}

		if (Util.isString(clazz)) {
			try {
				DateFormat formatter = null;

				String pattern = info.getPattern();
				if (pattern.trim().isEmpty()) {
					pattern = "EEE MMM dd HH:mm:ss zzz yyyy";
				}

				if (info.getLocale() != null) {
					formatter = new SimpleDateFormat(pattern, info.getLocale());
				} else {
					formatter = new SimpleDateFormat(pattern);
				}

				if (info.getTimezone() != null) {
					formatter.setTimeZone(info.getTimezone());
				}

				return (T) formatter.format(toJavaObject());
			} catch (NullPointerException | ClassCastException | IllegalArgumentException e) {
			}

			return (T) toJavaObject().toString();
		}

		return null;
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
	public Date getValue() {
		return (Date) super.getValue();
	}

	@Override
	public Date setValue(Date value) {
		super.setValue(value);

		return getValue();
	}

	@Override
	public String toPrettyString() {
		StringBuilder builder = new StringBuilder();

		builder.append(Util.getIndentTab(indentLevel, tab));

		if (!isObjectChild() && !isArrayChild()) {
			builder.append(JsonStrings.START);
			builder.append(JsonStrings.WHITESPACE);
		}

		if (!isArrayChild()) {
			builder.append(JsonStrings.QUOTATION);
			builder.append(getKey());
			builder.append(JsonStrings.QUOTATION);
			builder.append(JsonStrings.COLON);
			builder.append(JsonStrings.WHITESPACE);
		}

		builder.append(JsonStrings.QUOTATION);
		builder.append(toJavaObject(String.class));
		builder.append(JsonStrings.QUOTATION);

		if (!isObjectChild() && !isArrayChild()) {
			builder.append(JsonStrings.WHITESPACE);
			builder.append(JsonStrings.END);
		}

		return builder.toString();
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();

		if (!isObjectChild() && !isArrayChild()) {
			builder.append(JsonStrings.START);
		}

		if (!isArrayChild()) {
			builder.append(JsonStrings.QUOTATION);
			builder.append(getKey());
			builder.append(JsonStrings.QUOTATION);
			builder.append(JsonStrings.COLON);
		}

		builder.append(JsonStrings.QUOTATION);
		builder.append(toJavaObject(String.class));
		builder.append(JsonStrings.QUOTATION);

		if (!isObjectChild() && !isArrayChild()) {
			builder.append(JsonStrings.END);
		}

		return builder.toString();
	}

}
