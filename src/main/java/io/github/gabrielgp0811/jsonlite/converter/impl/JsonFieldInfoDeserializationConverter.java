/**
 * 
 */
package io.github.gabrielgp0811.jsonlite.converter.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import io.github.gabrielgp0811.jsonlite.annotation.JsonField;
import io.github.gabrielgp0811.jsonlite.converter.Converter;
import io.github.gabrielgp0811.jsonlite.exception.JsonException;
import io.github.gabrielgp0811.jsonlite.util.JsonFieldInfo;
import io.github.gabrielgp0811.jsonlite.util.JsonPatternInfo;

/**
 * Class responsible for converting {@link JsonField} annotation into a
 * {@link JsonFieldInfo} object.
 * 
 * @author gabrielgp0811
 */
public class JsonFieldInfoDeserializationConverter implements Converter<JsonField, JsonFieldInfo> {

	/**
	 * 
	 */
	public JsonFieldInfoDeserializationConverter() {

	}

	@Override
	public JsonFieldInfo convert(JsonField input) throws JsonException {
		if (input == null) {
			throw new JsonException("input is null");
		}

		List<String> customNames = new ArrayList<>(Arrays.asList(input.customNamesDeserialization()));

		if (!input.customName().trim().isEmpty()) {
			customNames.add(input.customName());
		}

		if (!input.value().trim().isEmpty()) {
			customNames.add(input.value());
		}

		JsonPatternInfo patternInfo = null;

		try {
			patternInfo = new JsonPatternInfoDeserializationConverter().convert(input.pattern());
		} catch (JsonException e) {
		}

		return new JsonFieldInfo(input.value(), customNames.toArray(new String[customNames.size()]), patternInfo);
	}

}