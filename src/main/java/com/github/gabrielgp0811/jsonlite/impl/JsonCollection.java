/**
 * 
 */
package com.github.gabrielgp0811.jsonlite.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.stream.Collectors;

import com.github.gabrielgp0811.jsonlite.Json;
import com.github.gabrielgp0811.jsonlite.JsonEntry;
import com.github.gabrielgp0811.jsonlite.constants.JsonStrings;
import com.github.gabrielgp0811.jsonlite.util.JsonFormatInfo;
import com.github.gabrielgp0811.jsonlite.util.Util;

/**
 * JSON class for <code>java.util.Collection</code>.
 * 
 * @author gabrielgp0811
 */
public class JsonCollection extends JsonEntry<Collection<?>> {

	/**
	 * The children JSON objects.
	 */
	private Collection<JsonEntry<?>> children = null;

	/**
	 * 
	 */
	public JsonCollection() {
		this(new ArrayList<>());
	}

	/**
	 * @param name The name to set
	 */
	public JsonCollection(String name) {
		this(name, new ArrayList<Object>());
	}

	/**
	 * @param value The value to set
	 */
	public JsonCollection(Collection<?> value) {
		this(null, value);
	}

	/**
	 * @param name  The name to set
	 * @param value The value to set
	 */
	public JsonCollection(String name, Collection<?> value) {
		this(name, value, (String) null);
	}

	/**
	 * @param value   The value to set
	 * @param pattern The pattern to set
	 */
	public JsonCollection(Collection<?> value, String pattern) {
		this(null, value, pattern);
	}

	/**
	 * @param name    The name to set
	 * @param value   The value to set
	 * @param pattern The pattern to set
	 */
	public JsonCollection(String name, Collection<?> value, String pattern) {
		this(name, value, pattern, null);
	}

	/**
	 * @param name    The name to set
	 * @param value   The value to set
	 * @param pattern The pattern to set
	 * @param locale  The locale to set
	 */
	public JsonCollection(String name, Collection<?> value, String pattern, String locale) {
		this(name, value, pattern, locale, null);
	}

	/**
	 * @param name     The name to set
	 * @param value    The value to set
	 * @param pattern  The pattern to set
	 * @param locale   The locale to set
	 * @param timezone The timezone to set
	 */
	public JsonCollection(String name, Collection<?> value, String pattern, String locale, String timezone) {
		this(name, value, new JsonFormatInfo(pattern, Util.toLocale(locale), Util.toTimeZone(timezone)));
	}

	/**
	 * @param name  The name to set
	 * @param value The value to set
	 * @param info  The info to set
	 */
	public JsonCollection(String name, Collection<?> value, JsonFormatInfo info) {
		super(name, value, info);

		init();
	}

	/**
	 * Init children JSON objects.
	 */
	@SuppressWarnings("unchecked")
	private void init() {
		Collection<Object> list = null;

		try {
			list = (Collection<Object>) getValue();
		} catch (ClassCastException e1) {
			return;
		}

		for (Object obj : list) {
			addObject(null, obj, info);
		}
	}

	@Override
	public JsonEntry<Collection<?>> addObject(String name, Object obj, JsonFormatInfo info) {
		return addChild(Json.toJson(name, obj, info));
	}

	@Override
	public JsonEntry<Collection<?>> addChild(JsonEntry<?> json) {
		json.setArrayChild(true);

		getChildren().add(json);

		return this;
	}

	@Override
	public JsonEntry<Collection<?>> addChildren(JsonEntry<?>... jsons) {
		if (jsons != null)
			addChildren(Arrays.asList(jsons));

		return this;
	}

	@Override
	public JsonEntry<Collection<?>> addChildren(Collection<JsonEntry<?>> jsons) {
		if (jsons != null)
			jsons.forEach(this::addChild);

		return this;
	}

	@Override
	public JsonEntry<?> getChild(int index) {
		if (index < 0 || index >= childrenSize()) {
			return null;
		}

		JsonEntry<?> result = null;
		int i = 0;
		for (JsonEntry<?> child : children) {
			if (i == index) {
				result = child;
				break;
			}

			i++;
		}

		return result;
	}

	@Override
	public JsonEntry<?> getChild(String name) {
		return this;
	}

	@Override
	public JsonEntry<?> removeChild(int index) {
		if (index > 0 || index < childrenSize()) {
			for (int i = 0; i < childrenSize(); i++) {
				if (i == index) {
					getChildren().remove(getChild(index));
				}
			}
		}

		return this;
	}

	@Override
	public JsonEntry<?> removeChild(String name) {
		return this;
	}

	@Override
	public Collection<JsonEntry<?>> getChildren() {
		if (children == null) {
			children = new ArrayList<>();
		}
		return children;
	}

	@Override
	public Collection<Object> toJavaObject() {
		return getChildren().stream().map(child -> child.toJavaObject()).collect(Collectors.toList());
	}

	@Override
	public Collection<Object> toJavaCollection() {
		return getChildren().stream().map(child -> child.toJavaObject()).collect(Collectors.toList());
	}

	@Override
	public <T> T toJavaObject(Class<T> clazz, JsonFormatInfo info) {
		return null;
	}

	@Override
	public <T> Collection<T> toJavaCollection(Class<T> clazz, JsonFormatInfo info) {
		if (clazz == null) {
			return null;
		}

		return (Collection<T>) getChildren().stream().map(child -> child.toJavaObject(clazz, info))
				.collect(Collectors.toList());
	}

	@Override
	public String toPrettyString() {
		StringBuilder builder = new StringBuilder();

		// if (isArrayChild()) {
		// 	builder.append(JsonStrings.START);
		// 	builder.append(JsonStrings.WHITESPACE);
		// }

		builder.append(Util.getIndentTab(indentLevel, tab));

		if (isObjectChild()) {
			builder.append(JsonStrings.QUOTATION);
			builder.append(getKey());
			builder.append(JsonStrings.QUOTATION);
			builder.append(JsonStrings.COLON);
			builder.append(JsonStrings.WHITESPACE);
		}

		builder.append(JsonStrings.START_ARRAY);
		builder.append(getChildren().stream()
				.map(child -> {
					child.setIndentLevel(getIndentLevel() + 1);
					
					return JsonStrings.LINE_SEPARATOR.concat(child.toPrettyString(tab));
				})
				.collect(Collectors.joining(JsonStrings.COMMA)));
		builder.append(JsonStrings.LINE_SEPARATOR);
		builder.append(Util.getIndentTab(indentLevel, tab));

		builder.append(JsonStrings.END_ARRAY);

		// if (isArrayChild()) {
		// 	builder.append(JsonStrings.END);
		// }

		return builder.toString();
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();

		// if (!isObjectChild() && !isArrayChild()) {
		// 	builder.append(JsonStrings.START);
		// }

		if (isObjectChild()) {
			builder.append(JsonStrings.QUOTATION);
			builder.append(getKey());
			builder.append(JsonStrings.QUOTATION);
			builder.append(JsonStrings.COLON);
		}

		builder.append(JsonStrings.START_ARRAY);
		builder.append(getChildren().stream().map(child -> child.toString())
				.collect(Collectors.joining(JsonStrings.COMMA)));

		builder.append(JsonStrings.END_ARRAY);

		// if (!isObjectChild() && !isArrayChild()) {
		// 	builder.append(JsonStrings.END);
		// }

		return builder.toString();
	}

}