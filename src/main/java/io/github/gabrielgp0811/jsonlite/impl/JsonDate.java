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
import java.util.Locale;
import java.util.TimeZone;

import io.github.gabrielgp0811.jsonlite.JsonEntry;
import io.github.gabrielgp0811.jsonlite.constants.JsonStrings;
import io.github.gabrielgp0811.jsonlite.util.JsonPatternInfo;
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
		this.name = JsonStrings.DATE_NAME;
	}

	/**
	 * @param value The value to set
	 */
	public JsonDate(Date value) {
		super(JsonStrings.DATE_NAME, value);
	}

	/**
	 * @param value   The value to set
	 * @param pattern The pattern to set
	 */
	public JsonDate(Date value, String pattern) {
		super(JsonStrings.DATE_NAME, value, pattern);
	}

	/**
	 * @param value   The value to set
	 * @param pattern The pattern to set
	 * @param locale  The locale to set
	 */
	public JsonDate(Date value, String pattern, String locale) {
		super(JsonStrings.DATE_NAME, value, pattern, locale);
	}

	/**
	 * @param value    The value to set
	 * @param pattern  The pattern to set
	 * @param locale   The locale to set
	 * @param timezone The timezone to set
	 */
	public JsonDate(Date value, String pattern, String locale, String timezone) {
		super(JsonStrings.DATE_NAME, value, pattern, locale, timezone);
	}

	/**
	 * @param value The value to set
	 * @param info  The info to set
	 */
	public JsonDate(Date value, JsonPatternInfo info) {
		super(JsonStrings.DATE_NAME, value, info);
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
	public JsonDate(String name, Date value, JsonPatternInfo info) {
		super(name, value, info);
	}

	@Override
	public JsonEntry<Date> addChild(String name, Object obj, JsonPatternInfo info) {
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
	public <T> T toJavaObject(Class<T> clazz, JsonPatternInfo info) {
		if (clazz == null) {
			return null;
		}

		JsonPatternInfo info0 = info;

		if (info0 == null) {
			info0 = new JsonPatternInfo();
		}

		if (Util.isLocalDate(clazz)) {
			try {
				return (T) toJavaObject().toInstant().atZone(info0.getTimezone().toZoneId()).toLocalDate();
			} catch (NullPointerException | DateTimeException e) {
			}

			return (T) toJavaObject().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
		}

		if (Util.isLocalTime(clazz)) {
			try {
				return (T) toJavaObject().toInstant().atZone(info0.getTimezone().toZoneId()).toLocalTime();
			} catch (NullPointerException | DateTimeException e) {
			}

			return (T) toJavaObject().toInstant().atZone(ZoneId.systemDefault()).toLocalTime();
		}

		if (Util.isLocalDateTime(clazz)) {
			try {
				return (T) toJavaObject().toInstant().atZone(info0.getTimezone().toZoneId()).toLocalDateTime();
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

				String pattern = info0.getPattern();
				Locale locale = info0.getLocale();
				TimeZone timezone = info0.getTimezone();

				if (pattern == null || pattern.trim().isEmpty()) {
					pattern = "EEE MMM dd HH:mm:ss zzz yyyy";
				}

				if (locale != null) {
					formatter = new SimpleDateFormat(pattern, locale);
				} else {
					formatter = new SimpleDateFormat(pattern);
				}

				if (timezone != null) {
					formatter.setTimeZone(timezone);
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
	public <T> Collection<T> toJavaCollection(Class<T> clazz, JsonPatternInfo info) {
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
	public String toPrettyString(String tab) {
		StringBuilder builder = new StringBuilder();

		builder.append(Util.getIndentTab(indentLevel, tab));

		if (!isObjectChild() && !isArrayChild()) {
			builder.append(JsonStrings.START);
			builder.append(JsonStrings.WHITESPACE);
		}

		if (!isArrayChild()) {
			builder.append(JsonStrings.QUOTATION);
			builder.append(getName());
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
			builder.append(getName());
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

	@Override
	public JsonEntry<Date> clone() {
		return new JsonDate(name, value, info);
	}

}