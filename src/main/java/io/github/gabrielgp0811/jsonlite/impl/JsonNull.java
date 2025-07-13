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
 * Class for <strong><span style= "color:#7f0055">null</span></strong> values.
 * 
 * @author gabrielgp0811
 */
public class JsonNull extends JsonEntry<Void> {

	/**
	 * 
	 */
	public JsonNull() {
		this(JsonStrings.NULL_NAME);
	}

	/**
	 * @param name The name to set
	 */
	public JsonNull(String name) {
		super(name, null);
	}

	@Override
	public Void getValue() {
		return null;
	}

	@Override
	public Void setValue(Void value) {
		return null;
	}

	@Override
	public JsonEntry<Void> addChild(String name, Object obj, JsonPatternInfo info) {
		return this;
	}

	@Override
	public JsonEntry<Void> addChild(JsonEntry<?> json) {
		return this;
	}

	@Override
	public JsonEntry<Void> addChildren(JsonEntry<?>... jsons) {
		return this;
	}

	@Override
	public JsonEntry<Void> addChildren(Collection<JsonEntry<?>> jsons) {
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
		return null;
	}

	@Override
	public <T> T toJavaObject(Class<T> clazz, JsonPatternInfo info) {
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

		builder.append("null");

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

		builder.append("null");

		if (!isObjectChild() && !isArrayChild()) {
			builder.append(JsonStrings.END);
		}

		return builder.toString();
	}

	@Override
	public JsonEntry<Void> clone() {
		return new JsonNull(name);
	}

}