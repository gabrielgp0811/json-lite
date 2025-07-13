/**
 * 
 */
package io.github.gabrielgp0811.jsonlite.impl;

import java.time.DateTimeException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;

import io.github.gabrielgp0811.jsonlite.JsonEntry;
import io.github.gabrielgp0811.jsonlite.constants.JsonStrings;
import io.github.gabrielgp0811.jsonlite.util.JsonPatternInfo;
import io.github.gabrielgp0811.jsonlite.util.Util;

/**
 * Class for <code>java.time.LocalDateTime</code>.
 * 
 * @author gabrielgp0811
 */
public class JsonLocalDateTime extends JsonEntry<LocalDateTime> {

	/**
	 * 
	 */
	public JsonLocalDateTime() {
		this.name = JsonStrings.LOCALDATETIME_NAME;
	}

	/**
	 * @param value The value to set
	 */
	public JsonLocalDateTime(LocalDateTime value) {
		super(JsonStrings.LOCALDATETIME_NAME, value);
	}

	/**
	 * @param value   The value to set
	 * @param pattern The pattern to set
	 */
	public JsonLocalDateTime(LocalDateTime value, String pattern) {
		super(JsonStrings.LOCALDATETIME_NAME, value, pattern);
	}

	/**
	 * @param value   The value to set
	 * @param pattern The pattern to set
	 * @param locale  The locale to set
	 */
	public JsonLocalDateTime(LocalDateTime value, String pattern, String locale) {
		super(JsonStrings.LOCALDATETIME_NAME, value, pattern, locale);
	}

	/**
	 * @param value    The value to set
	 * @param pattern  The pattern to set
	 * @param locale   The locale to set
	 * @param timezone The timezone to set
	 */
	public JsonLocalDateTime(LocalDateTime value, String pattern, String locale, String timezone) {
		super(JsonStrings.LOCALDATETIME_NAME, value, pattern, locale, timezone);
	}

	/**
	 * @param value The value to set
	 * @param info  The info to set
	 */
	public JsonLocalDateTime(LocalDateTime value, JsonPatternInfo info) {
		this(JsonStrings.LOCALDATETIME_NAME, value, info);
	}

	/**
	 * @param name  The name to set
	 * @param value The value to set
	 */
	public JsonLocalDateTime(String name, LocalDateTime value) {
		super(name, value);
	}

	/**
	 * @param name    The name to set
	 * @param value   The value to set
	 * @param pattern The pattern to set
	 */
	public JsonLocalDateTime(String name, LocalDateTime value, String pattern) {
		super(name, value, pattern);
	}

	/**
	 * @param name    The name to set
	 * @param value   The value to set
	 * @param pattern The pattern to set
	 * @param locale  The locale to set
	 */
	public JsonLocalDateTime(String name, LocalDateTime value, String pattern, String locale) {
		super(name, value, pattern, locale);
	}

	/**
	 * @param name     The name to set
	 * @param value    The value to set
	 * @param pattern  The pattern to set
	 * @param locale   The locale to set
	 * @param timezone The timezone to set
	 */
	public JsonLocalDateTime(String name, LocalDateTime value, String pattern, String locale, String timezone) {
		super(name, value, pattern, locale, timezone);
	}

	/**
	 * @param name  The name to set
	 * @param value The value to set
	 * @param info  The info to set
	 */
	public JsonLocalDateTime(String name, LocalDateTime value, JsonPatternInfo info) {
		super(name, value, info);
	}

	@Override
	public JsonEntry<LocalDateTime> addChild(String name, Object obj, JsonPatternInfo info) {
		return this;
	}

	@Override
	public JsonEntry<LocalDateTime> addChild(JsonEntry<?> json) {
		return this;
	}

	@Override
	public JsonEntry<LocalDateTime> addChildren(JsonEntry<?>... jsons) {
		return this;
	}

	@Override
	public JsonEntry<LocalDateTime> addChildren(Collection<JsonEntry<?>> jsons) {
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
	public LocalDateTime toJavaObject() {
		return getValue();
	}

	@Override
	@SuppressWarnings("unchecked")
	public <T> T toJavaObject(Class<T> clazz, JsonPatternInfo info) {
		if (clazz == null) {
			return null;
		}

		if (Util.isLocalDate(clazz)) {
			try {
				return (T) toJavaObject().atZone(info.getTimezone().toZoneId()).toLocalDate();
			} catch (NullPointerException e) {
			}

			return (T) toJavaObject().atZone(ZoneId.systemDefault()).toLocalDate();
		}

		if (Util.isLocalTime(clazz)) {
			try {
				return (T) toJavaObject().atZone(info.getTimezone().toZoneId()).toLocalTime();
			} catch (NullPointerException e) {
			}

			return (T) toJavaObject().atZone(ZoneId.systemDefault()).toLocalTime();
		}

		if (Util.isLocalDateTime(clazz)) {
			return (T) getValue();
		}

		if (Util.isDate(clazz)) {
			try {
				return (T) Date.from(toJavaObject().atZone(info.getTimezone().toZoneId()).toInstant());
			} catch (NullPointerException e) {
			}

			return (T) Date.from(toJavaObject().atZone(ZoneId.systemDefault()).toInstant());
		}

		if (Util.isString(clazz)) {
			try {
				if (info.getLocale() != null) {
					if (info.getPattern() == null || info.getPattern().trim().isEmpty()) {
						return (T) toJavaObject()
								.format(DateTimeFormatter.ofPattern("uuuu-MM-dd'T'HH:mm:ss", info.getLocale()));
					}

					return (T) toJavaObject().format(DateTimeFormatter.ofPattern(info.getPattern(), info.getLocale()));
				}

				if (info.getPattern() != null && !info.getPattern().trim().isEmpty()) {
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
	public <T> Collection<T> toJavaCollection(Class<T> clazz, JsonPatternInfo info) {
		if (clazz == null) {
			return null;
		}

		return new ArrayList<>(Arrays.asList(toJavaObject(clazz, info)));
	}

	@Override
	public LocalDateTime getValue() {
		return (LocalDateTime) super.getValue();
	}

	@Override
	public LocalDateTime setValue(LocalDateTime value) {
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
	public JsonEntry<LocalDateTime> clone() {
		return new JsonLocalDateTime(name, value, info);
	}

}