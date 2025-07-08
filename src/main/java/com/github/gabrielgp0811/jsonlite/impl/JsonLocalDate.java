/**
 * 
 */
package com.github.gabrielgp0811.jsonlite.impl;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;

import com.github.gabrielgp0811.jsonlite.JsonEntry;
import com.github.gabrielgp0811.jsonlite.constants.JsonStrings;
import com.github.gabrielgp0811.jsonlite.util.JsonFormatInfo;
import com.github.gabrielgp0811.jsonlite.util.Util;

/**
 * Class for <code>java.time.LocalDate</code>.
 * 
 * @author gabrielgp0811
 */
public class JsonLocalDate extends JsonEntry<LocalDate> {

	/**
	 * 
	 */
	public JsonLocalDate() {

	}

	/**
	 * @param value The value to set
	 */
	public JsonLocalDate(LocalDate value) {
		super(value);
	}

	/**
	 * @param value   The value to set
	 * @param pattern The pattern to set
	 */
	public JsonLocalDate(LocalDate value, String pattern) {
		this(null, value, pattern);
	}

	/**
	 * @param value   The value to set
	 * @param pattern The pattern to set
	 * @param locale  The locale to set
	 */
	public JsonLocalDate(LocalDate value, String pattern, String locale) {
		super(null, value, pattern, locale);
	}

	/**
	 * @param value    The value to set
	 * @param pattern  The pattern to set
	 * @param locale   The locale to set
	 * @param timezone The timezone to set
	 */
	public JsonLocalDate(LocalDate value, String pattern, String locale, String timezone) {
		super(null, value, pattern, locale, timezone);
	}

	/**
	 * @param value The value to set
	 * @param info  The info to set
	 */
	public JsonLocalDate(LocalDate value, JsonFormatInfo info) {
		super(null, value, info);
	}

	/**
	 * @param name  The name to set
	 * @param value The value to set
	 */
	public JsonLocalDate(String name, LocalDate value) {
		super(name, value);
	}

	/**
	 * @param name    The name to set
	 * @param value   The value to set
	 * @param pattern The pattern to set
	 */
	public JsonLocalDate(String name, LocalDate value, String pattern) {
		super(name, value, pattern);
	}

	/**
	 * @param name    The name to set
	 * @param value   The value to set
	 * @param pattern The pattern to set
	 * @param locale  The locale to set
	 */
	public JsonLocalDate(String name, LocalDate value, String pattern, String locale) {
		super(name, value, pattern, locale);
	}

	/**
	 * @param name     The name to set
	 * @param value    The value to set
	 * @param pattern  The pattern to set
	 * @param locale   The locale to set
	 * @param timezone The timezone to set
	 */
	public JsonLocalDate(String name, LocalDate value, String pattern, String locale, String timezone) {
		super(name, value, pattern, locale, timezone);
	}

	/**
	 * @param name  The name to set
	 * @param value The value to set
	 * @param info  The info to set
	 */
	public JsonLocalDate(String name, LocalDate value, JsonFormatInfo info) {
		super(name, value, info);
	}

	@Override
	public JsonEntry<LocalDate> addObject(String name, Object obj, JsonFormatInfo info) {
		return this;
	}

	@Override
	public JsonEntry<LocalDate> addChild(JsonEntry<?> json) {
		return this;
	}

	@Override
	public JsonEntry<LocalDate> addChildren(JsonEntry<?>... jsons) {
		return this;
	}

	@Override
	public JsonEntry<LocalDate> addChildren(Collection<JsonEntry<?>> jsons) {
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
	public LocalDate toJavaObject() {
		return getValue();
	}

	@Override
	@SuppressWarnings("unchecked")
	public <T> T toJavaObject(Class<T> clazz, JsonFormatInfo info) {
		if (clazz == null) {
			return null;
		}

		if (Util.isLocalDate(clazz)) {
			return (T) getValue();
		}

		if (Util.isLocalTime(clazz)) {
			try {
				return (T) toJavaObject().atStartOfDay(info.getTimezone().toZoneId()).toLocalTime();
			} catch (NullPointerException e) {
			}

			return (T) toJavaObject().atStartOfDay(ZoneId.systemDefault()).toLocalTime();
		}

		if (Util.isLocalDateTime(clazz)) {
			try {
				return (T) toJavaObject().atStartOfDay(info.getTimezone().toZoneId()).toLocalDateTime();
			} catch (NullPointerException e) {
			}

			return (T) toJavaObject().atStartOfDay(ZoneId.systemDefault()).toLocalDateTime();
		}

		if (Util.isDate(clazz)) {
			try {
				return (T) Date.from(toJavaObject().atStartOfDay(info.getTimezone().toZoneId()).toInstant());
			} catch (NullPointerException e) {
			}

			return (T) Date.from(toJavaObject().atStartOfDay(ZoneId.systemDefault()).toInstant());
		}

		if (Util.isString(clazz)) {
			try {
				if (info.getLocale() != null) {
					if (info.getPattern().trim().isEmpty()) {
						return (T) toJavaObject().format(DateTimeFormatter.ofPattern("uuuu-MM-dd", info.getLocale()));
					}

					return (T) toJavaObject().format(DateTimeFormatter.ofPattern(info.getPattern(), info.getLocale()));
				}

				if (!info.getPattern().trim().isEmpty()) {
					return (T) toJavaObject().format(DateTimeFormatter.ofPattern(info.getPattern()));
				}
			} catch (NullPointerException | DateTimeException | IllegalArgumentException e) {
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
	public LocalDate getValue() {
		return (LocalDate) super.getValue();
	}

	@Override
	public LocalDate setValue(LocalDate value) {
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
