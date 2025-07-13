/**
 * 
 */
package io.github.gabrielgp0811.jsonlite.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.stream.Collectors;

import io.github.gabrielgp0811.jsonlite.Json;
import io.github.gabrielgp0811.jsonlite.JsonEntry;
import io.github.gabrielgp0811.jsonlite.constants.JsonStrings;
import io.github.gabrielgp0811.jsonlite.util.JsonPatternInfo;
import io.github.gabrielgp0811.jsonlite.util.Util;

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
		this(name, new ArrayList<>());
	}

	/**
	 * @param value The value to set
	 */
	public JsonCollection(Collection<?> value) {
		this(JsonStrings.COLLECTION_NAME, value);
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
		this(JsonStrings.COLLECTION_NAME, value, pattern);
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
		this(name, value, new JsonPatternInfo(pattern, Util.toLocale(locale), Util.toTimeZone(timezone)));
	}

	/**
	 * @param name  The name to set
	 * @param value The value to set
	 * @param info  The info to set
	 */
	public JsonCollection(String name, Collection<?> value, JsonPatternInfo info) {
		super(name, value, info);

		init();
	}

	/**
	 * Init children JSON objects.
	 */
	@SuppressWarnings("unchecked")
	private void init() {
		if (value == null) {
			return;
		}

		Collection<Object> list = null;

		try {
			list = (Collection<Object>) value;
		} catch (ClassCastException e1) {
			return;
		}

		for (Object obj : list) {
			addChild(null, obj, info);
		}
	}

	@Override
	public JsonEntry<Collection<?>> addChild(String name, Object obj, JsonPatternInfo info) {
		return addChild(Json.toJson(name, obj, info));
	}

	@Override
	public JsonEntry<Collection<?>> addChild(JsonEntry<?> json) {
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
	public <T> T toJavaObject(Class<T> clazz, JsonPatternInfo info) {
		Collection<T> collection = toJavaCollection(clazz, info);

		return collection.stream().findFirst().orElse(null);
	}

	@Override
	public <T> Collection<T> toJavaCollection(Class<T> clazz, JsonPatternInfo info) {
		if (clazz == null) {
			return null;
		}

		return (Collection<T>) getChildren().stream().map(child -> child.toJavaObject(clazz, info))
				.collect(Collectors.toList());
	}

	@Override
	public String toPrettyString(String tab) {
		StringBuilder builder = new StringBuilder();

		builder.append(Util.getIndentTab(indentLevel, tab));

		if (isObjectChild()) {
			builder.append(JsonStrings.QUOTATION);
			builder.append(getName());
			builder.append(JsonStrings.QUOTATION);
			builder.append(JsonStrings.COLON);
			builder.append(JsonStrings.WHITESPACE);
		}

		builder.append(JsonStrings.START_ARRAY);
		builder.append(getChildren().stream()
				.map(child -> child.clone())
				.map(child -> {
					child.setArrayChild(true);
					child.setIndentLevel(getIndentLevel() + 1);
					
					return JsonStrings.LINE_SEPARATOR.concat(child.toPrettyString(tab));
				})
				.collect(Collectors.joining(JsonStrings.COMMA)));
		builder.append(JsonStrings.LINE_SEPARATOR);
		builder.append(Util.getIndentTab(indentLevel, tab));

		builder.append(JsonStrings.END_ARRAY);

		return builder.toString();
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();

		if (isObjectChild()) {
			builder.append(JsonStrings.QUOTATION);
			builder.append(getName());
			builder.append(JsonStrings.QUOTATION);
			builder.append(JsonStrings.COLON);
		}

		builder.append(JsonStrings.START_ARRAY);
		builder.append(getChildren().stream()
				.map(child -> child.clone())
				.map(child -> {
					child.setArrayChild(true);

					return child;
				})
				.map(child -> child.toString())
				.collect(Collectors.joining(JsonStrings.COMMA)));

		builder.append(JsonStrings.END_ARRAY);

		return builder.toString();
	}

	@Override
	public JsonEntry<Collection<?>> clone() {
		return new JsonCollection(name, null, info).addChildren(children);
	}

}