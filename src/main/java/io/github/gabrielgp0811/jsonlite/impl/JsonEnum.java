/**
 * 
 */
package io.github.gabrielgp0811.jsonlite.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;

import io.github.gabrielgp0811.jsonlite.JsonEntry;
import io.github.gabrielgp0811.jsonlite.constants.JsonStrings;
import io.github.gabrielgp0811.jsonlite.util.JsonPatternInfo;
import io.github.gabrielgp0811.jsonlite.util.Util;

/**
 * Class for <code>java.lang.Enum</code>.
 * 
 * @author gabrielgp0811
 */
public class JsonEnum extends JsonEntry<Enum<?>> {

	/**
	 * 
	 */
	public JsonEnum() {
		this.name = JsonStrings.ENUM_NAME;
	}

	/**
	 * @param value The value to set
	 */
	public JsonEnum(Enum<?> value) {
		super(JsonStrings.ENUM_NAME, value);
	}

	/**
	 * @param name  The name to set
	 * @param value The value to set
	 */
	public JsonEnum(String name, Enum<?> value) {
		super(name, value);
	}

	@Override
	public JsonEntry<Enum<?>> addChild(String name, Object obj, JsonPatternInfo info) {
		return this;
	}

	@Override
	public JsonEntry<Enum<?>> addChild(JsonEntry<?> json) {
		return this;
	}

	@Override
	public JsonEntry<Enum<?>> addChildren(JsonEntry<?>... jsons) {
		return this;
	}

	@Override
	public JsonEntry<Enum<?>> addChildren(Collection<JsonEntry<?>> jsons) {
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
	public Object toJavaObject() {
		return getValue();
	}

	@Override
	@SuppressWarnings("unchecked")
	public <T> T toJavaObject(Class<T> clazz, JsonPatternInfo info) {
		if (clazz == null) {
			return null;
		}

		if (Util.isInteger(clazz)) {
			return (T) Integer.valueOf(((Enum<?>) getValue()).ordinal());
		}

		if (Util.isString(clazz)) {
			return (T) ((Enum<?>) getValue()).name();
		}

		if (((Enum<?>) getValue()).getDeclaringClass().equals(clazz)) {
			return (T) getValue();
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
	public JsonEntry<Enum<?>> clone() {
		return new JsonEnum(name, value);
	}

}