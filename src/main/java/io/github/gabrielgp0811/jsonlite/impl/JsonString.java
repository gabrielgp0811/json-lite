/**
 * 
 */
package io.github.gabrielgp0811.jsonlite.impl;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;

import io.github.gabrielgp0811.jsonlite.JsonEntry;
import io.github.gabrielgp0811.jsonlite.constants.JsonStrings;
import io.github.gabrielgp0811.jsonlite.util.JsonPatternInfo;
import io.github.gabrielgp0811.jsonlite.util.Util;

/**
 * Class for <code>java.lang.String</code>.
 * 
 * @author gabrielgp0811
 */
public class JsonString extends JsonEntry<String> {

	/**
	 * 
	 */
	public JsonString() {
		this(null);
	}

	/**
	 * @param value The value to set
	 */
	public JsonString(String value) {
		super(JsonStrings.STRING_NAME, value);
	}

	/**
	 * @param name  The name to set
	 * @param value The value to set
	 */
	public JsonString(String name, String value) {
		super(name, value);
	}

	@Override
	public JsonEntry<String> addChild(String name, Object obj, JsonPatternInfo info) {
		return this;
	}

	@Override
	public JsonEntry<String> addChild(JsonEntry<?> json) {
		return this;
	}

	@Override
	public JsonEntry<String> addChildren(JsonEntry<?>... jsons) {
		return this;
	}

	@Override
	public JsonEntry<String> addChildren(Collection<JsonEntry<?>> jsons) {
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
	public String toJavaObject() {
		return getValue();
	}

	@Override
	@SuppressWarnings("unchecked")
	public <T> T toJavaObject(Class<T> clazz, JsonPatternInfo info) {
		if (clazz == null) {
			return null;
		}

		if (Util.isString(clazz)) {
			return (T) getValue();
		}

		if (Util.isCharacter(clazz) && !toJavaObject().trim().isEmpty()) {
			return (T) Character.valueOf(toJavaObject().charAt(0));
		}

		if (Util.isNumber(clazz)) {
			try {
				if (Util.isByte(clazz)) {
					return (T) Byte.valueOf(toJavaObject());
				}

				if (Util.isShort(clazz)) {
					return (T) Short.valueOf(toJavaObject());
				}

				if (Util.isInteger(clazz)) {
					return (T) Integer.valueOf(toJavaObject());
				}

				if (Util.isLong(clazz)) {
					return (T) Long.valueOf(toJavaObject());
				}

				if (Util.isBigInteger(clazz)) {
					return (T) new BigInteger(toJavaObject());
				}

				if (Util.isFloat(clazz)) {
					return (T) Float.valueOf(toJavaObject());
				}

				if (Util.isDouble(clazz)) {
					return (T) Double.valueOf(toJavaObject());
				}

				if (Util.isBigDecimal(clazz)) {
					return (T) new BigDecimal(toJavaObject());
				}
			} catch (NumberFormatException e) {
			}
		}

		if (Util.isDate(clazz)) {
			try {
				DateFormat formatter = null;

				String pattern = info.getPattern();
				if (pattern == null || pattern.trim().isEmpty()) {
					pattern = "EEE, MMM dd HH:mm:ss zzz yyyy";
				}

				if (info.getLocale() != null) {
					formatter = new SimpleDateFormat(pattern, info.getLocale());
				} else {
					formatter = new SimpleDateFormat(pattern);
				}

				if (info.getTimezone() != null) {
					formatter.setTimeZone(info.getTimezone());
				}

				return (T) formatter.parse(toJavaObject());
			} catch (NullPointerException | ParseException | IllegalArgumentException e) {
			}

			try {
				return (T) new SimpleDateFormat("EEE, MMM dd HH:mm:ss zzz yyyy").parse(toJavaObject());
			} catch (ParseException e) {
			}
		}

		if (Util.isLocalDate(clazz)) {
			try {
				DateTimeFormatter formatter = null;

				String pattern = info.getPattern();
				if (pattern.trim().isEmpty()) {
					pattern = "yyyy-MM-dd";
				}

				if (info.getLocale() != null) {
					formatter = DateTimeFormatter.ofPattern(pattern, info.getLocale());
				} else {
					formatter = DateTimeFormatter.ofPattern(pattern);
				}

				return (T) LocalDate.parse(toJavaObject(), formatter);
			} catch (NullPointerException | DateTimeParseException e) {
			}

			try {
				return (T) LocalDate.parse(toJavaObject());
			} catch (DateTimeParseException e) {
			}
		}

		if (Util.isLocalDateTime(clazz)) {
			try {
				DateTimeFormatter formatter = null;

				String pattern = info.getPattern();
				if (pattern.trim().isEmpty()) {
					pattern = "yyyy-MM-dd'T'HH:mm:ss";
				}

				if (info.getLocale() != null) {
					formatter = DateTimeFormatter.ofPattern(pattern, info.getLocale());
				} else {
					formatter = DateTimeFormatter.ofPattern(pattern);
				}

				return (T) LocalDateTime.parse(toJavaObject(), formatter);
			} catch (NullPointerException | IllegalArgumentException | DateTimeParseException e) {
			}

			try {
				return (T) LocalDateTime.parse(toJavaObject());
			} catch (DateTimeParseException e) {
			}
		}

		if (Util.isLocalTime(clazz)) {
			try {
				DateTimeFormatter formatter = null;

				String pattern = info.getPattern();
				if (pattern.trim().isEmpty()) {
					pattern = "HH:mm:ss";
				}

				if (info.getLocale() != null) {
					formatter = DateTimeFormatter.ofPattern(pattern, info.getLocale());
				} else {
					formatter = DateTimeFormatter.ofPattern(pattern);
				}

				return (T) LocalTime.parse(toJavaObject(), formatter);
			} catch (NullPointerException | IllegalArgumentException | DateTimeParseException e) {
			}

			try {
				return (T) LocalTime.parse(toJavaObject());
			} catch (DateTimeParseException e) {
			}
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
	public String getValue() {
		return (String) super.getValue();
	}

	@Override
	public String setValue(String value) {
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
		builder.append(getValue());
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
		builder.append(getValue());
		builder.append(JsonStrings.QUOTATION);

		if (!isObjectChild() && !isArrayChild()) {
			builder.append(JsonStrings.END);
		}

		return builder.toString();
	}

	@Override
	public JsonEntry<String> clone() {
		return new JsonString(name, value);
	}

}