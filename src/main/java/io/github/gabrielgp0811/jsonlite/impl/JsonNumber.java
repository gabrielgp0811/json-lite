/**
 * 
 */
package io.github.gabrielgp0811.jsonlite.impl;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;

import io.github.gabrielgp0811.jsonlite.JsonEntry;
import io.github.gabrielgp0811.jsonlite.constants.JsonStrings;
import io.github.gabrielgp0811.jsonlite.util.JsonFormatInfo;
import io.github.gabrielgp0811.jsonlite.util.Util;

/**
 * Class for numbers:
 * <ul>
 * <li><code>java.lang.Byte</code></li>
 * <li><code>java.lang.Short</code></li>
 * <li><code>java.lang.Integer</code></li>
 * <li><code>java.lang.Long</code></li>
 * <li><code>java.lang.Float</code></li>
 * <li><code>java.lang.Double</code></li>
 * <li><code>java.math.BigDecimal</code></li>
 * <li><code>java.math.BigInteger</code></li>
 * </ul>
 * 
 * @author gabrielgp0811
 */
public class JsonNumber extends JsonEntry<Number> {

	/**
	 * 
	 */
	public JsonNumber() {

	}

	/**
	 * @param value The value to set
	 */
	public JsonNumber(Number value) {
		super(value);
	}

	/**
	 * @param name  The name to set
	 * @param value The value to set
	 */
	public JsonNumber(String name, Number value) {
		super(name, value);
	}

	@Override
	public JsonEntry<Number> addObject(String name, Object obj, JsonFormatInfo info) {
		return this;
	}

	@Override
	public JsonEntry<Number> addChild(JsonEntry<?> json) {
		return this;
	}

	@Override
	public JsonEntry<Number> addChildren(JsonEntry<?>... jsons) {
		return this;
	}

	@Override
	public JsonEntry<Number> addChildren(Collection<JsonEntry<?>> jsons) {
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
	public Number toJavaObject() {
		return getValue();
	}

	@Override
	@SuppressWarnings("unchecked")
	public <T> T toJavaObject(Class<T> clazz, JsonFormatInfo info) {
		if (clazz == null) {
			return null;
		}

		if (Util.isString(clazz)) {
			return (T) String.valueOf(getValue());
		}

		if (Util.isByte(getValue())) {
			if (Util.isByte(clazz)) {
				return (T) getValue();
			} else if (Util.isShort(clazz)) {
				return (T) Short.valueOf(((Byte) getValue()).shortValue());
			} else if (Util.isInteger(clazz)) {
				return (T) Integer.valueOf(((Byte) getValue()).intValue());
			} else if (Util.isLong(clazz)) {
				return (T) Long.valueOf(((Byte) getValue()).longValue());
			} else if (Util.isBigInteger(clazz)) {
				return (T) BigInteger.valueOf(((Byte) getValue()).longValue());
			} else if (Util.isFloat(clazz)) {
				return (T) Float.valueOf(((Byte) getValue()).floatValue());
			} else if (Util.isDouble(clazz)) {
				return (T) Double.valueOf(((Byte) getValue()).doubleValue());
			} else if (Util.isBigDecimal(clazz)) {
				return (T) new BigDecimal(((Byte) getValue()).intValue());
			}
		}

		if (Util.isShort(getValue())) {
			if (Util.isByte(clazz)) {
				return (T) Byte.valueOf(((Short) getValue()).byteValue());
			} else if (Util.isShort(clazz)) {
				return (T) getValue();
			} else if (Util.isInteger(clazz)) {
				return (T) Integer.valueOf(((Short) getValue()).intValue());
			} else if (Util.isLong(clazz)) {
				return (T) Long.valueOf(((Short) getValue()).longValue());
			} else if (Util.isBigInteger(clazz)) {
				return (T) BigInteger.valueOf(((Short) getValue()).longValue());
			} else if (Util.isFloat(clazz)) {
				return (T) Float.valueOf(((Short) getValue()).floatValue());
			} else if (Util.isDouble(clazz)) {
				return (T) Double.valueOf(((Short) getValue()).doubleValue());
			} else if (Util.isBigDecimal(clazz)) {
				return (T) new BigDecimal(((Short) getValue()).intValue());
			}
		}

		if (Util.isInteger(getValue())) {
			if (Util.isByte(clazz)) {
				return (T) Byte.valueOf(((Integer) getValue()).byteValue());
			} else if (Util.isShort(clazz)) {
				return (T) Short.valueOf(((Integer) getValue()).shortValue());
			} else if (Util.isInteger(clazz)) {
				return (T) getValue();
			} else if (Util.isLong(clazz)) {
				return (T) Long.valueOf(((Integer) getValue()).longValue());
			} else if (Util.isBigInteger(clazz)) {
				return (T) BigInteger.valueOf(((Integer) getValue()).longValue());
			} else if (Util.isFloat(clazz)) {
				return (T) Float.valueOf(((Integer) getValue()).floatValue());
			} else if (Util.isDouble(clazz)) {
				return (T) Double.valueOf(((Integer) getValue()).doubleValue());
			} else if (Util.isBigDecimal(clazz)) {
				return (T) new BigDecimal(((Integer) getValue()).intValue());
			}
		}

		if (Util.isLong(getValue())) {
			if (Util.isByte(clazz)) {
				return (T) Byte.valueOf(((Long) getValue()).byteValue());
			} else if (Util.isShort(clazz)) {
				return (T) Short.valueOf(((Long) getValue()).shortValue());
			} else if (Util.isInteger(clazz)) {
				return (T) Integer.valueOf(((Long) getValue()).intValue());
			} else if (Util.isLong(clazz)) {
				return (T) getValue();
			} else if (Util.isBigInteger(clazz)) {
				return (T) BigInteger.valueOf(((Long) getValue()).longValue());
			} else if (Util.isFloat(clazz)) {
				return (T) Float.valueOf(((Long) getValue()).floatValue());
			} else if (Util.isDouble(clazz)) {
				return (T) Double.valueOf(((Long) getValue()).doubleValue());
			} else if (Util.isBigDecimal(clazz)) {
				return (T) new BigDecimal(((Long) getValue()).intValue());
			}
		}

		if (Util.isBigInteger(getValue())) {
			if (Util.isByte(clazz)) {
				return (T) Byte.valueOf(((BigInteger) getValue()).byteValue());
			} else if (Util.isShort(clazz)) {
				return (T) Short.valueOf(((BigInteger) getValue()).shortValue());
			} else if (Util.isInteger(clazz)) {
				return (T) Integer.valueOf(((BigInteger) getValue()).intValue());
			} else if (Util.isLong(clazz)) {
				return (T) Long.valueOf(((BigInteger) getValue()).longValue());
			} else if (Util.isBigInteger(clazz)) {
				return (T) getValue();
			} else if (Util.isFloat(clazz)) {
				return (T) Float.valueOf(((BigInteger) getValue()).floatValue());
			} else if (Util.isDouble(clazz)) {
				return (T) Double.valueOf(((BigInteger) getValue()).doubleValue());
			} else if (Util.isBigDecimal(clazz)) {
				return (T) new BigDecimal(((BigInteger) getValue()));
			}
		}

		if (Util.isFloat(getValue())) {
			if (Util.isByte(clazz)) {
				return (T) Byte.valueOf(((Float) getValue()).byteValue());
			} else if (Util.isShort(clazz)) {
				return (T) Short.valueOf(((Float) getValue()).shortValue());
			} else if (Util.isInteger(clazz)) {
				return (T) Integer.valueOf(((Float) getValue()).intValue());
			} else if (Util.isLong(clazz)) {
				return (T) Long.valueOf(((Long) getValue()).longValue());
			} else if (Util.isBigInteger(clazz)) {
				return (T) BigInteger.valueOf(((Float) getValue()).longValue());
			} else if (Util.isFloat(clazz)) {
				return (T) getValue();
			} else if (Util.isDouble(clazz)) {
				return (T) Double.valueOf(((Float) getValue()).doubleValue());
			} else if (Util.isBigDecimal(clazz)) {
				return (T) new BigDecimal(((Float) getValue()).intValue());
			}
		}

		if (Util.isDouble(getValue())) {
			if (Util.isByte(clazz)) {
				return (T) Byte.valueOf(((Double) getValue()).byteValue());
			} else if (Util.isShort(clazz)) {
				return (T) Short.valueOf(((Double) getValue()).shortValue());
			} else if (Util.isInteger(clazz)) {
				return (T) Integer.valueOf(((Double) getValue()).intValue());
			} else if (Util.isLong(clazz)) {
				return (T) Long.valueOf(((Double) getValue()).longValue());
			} else if (Util.isBigInteger(clazz)) {
				return (T) BigInteger.valueOf(((Double) getValue()).longValue());
			} else if (Util.isFloat(clazz)) {
				return (T) Float.valueOf(((Double) getValue()).floatValue());
			} else if (Util.isDouble(clazz)) {
				return (T) getValue();
			} else if (Util.isBigDecimal(clazz)) {
				return (T) new BigDecimal(((Double) getValue()).intValue());
			}
		}

		if (Util.isBigDecimal(getValue())) {
			if (Util.isByte(clazz)) {
				return (T) Byte.valueOf(((BigDecimal) getValue()).byteValue());
			} else if (Util.isShort(clazz)) {
				return (T) Short.valueOf(((BigDecimal) getValue()).shortValue());
			} else if (Util.isInteger(clazz)) {
				return (T) Integer.valueOf(((BigDecimal) getValue()).intValue());
			} else if (Util.isLong(clazz)) {
				return (T) Long.valueOf(((BigDecimal) getValue()).longValue());
			} else if (Util.isBigInteger(clazz)) {
				return (T) ((BigDecimal) getValue()).toBigInteger();
			} else if (Util.isFloat(clazz)) {
				return (T) Float.valueOf(((BigDecimal) getValue()).floatValue());
			} else if (Util.isDouble(clazz)) {
				return (T) Double.valueOf(((BigDecimal) getValue()).doubleValue());
			} else if (Util.isBigDecimal(clazz)) {
				return (T) getValue();
			}
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

		builder.append(getValue());

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

		builder.append(getValue());

		if (!isObjectChild() && !isArrayChild()) {
			builder.append(JsonStrings.END);
		}

		return builder.toString();
	}

}