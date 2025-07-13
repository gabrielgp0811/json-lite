/**
 * 
 */
package io.github.gabrielgp0811.jsonlite.converter.impl;

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
public class JsonFieldInfoSerializationConverter implements Converter<JsonField, JsonFieldInfo> {

	/**
	 * 
	 */
	public JsonFieldInfoSerializationConverter() {

	}

	@Override
	public JsonFieldInfo convert(JsonField input) throws JsonException {
		if (input == null) {
			throw new JsonException("input is null");
		}

		String customName = input.customNameSerialization();

		if (customName.trim().isEmpty()) {
			customName = input.customName();

			if (customName.trim().isEmpty()) {
				customName = input.value();

				if (customName.trim().isEmpty()) {
					throw new JsonException(new IllegalArgumentException("Must provide name."));
				}
			}
		}

		JsonPatternInfo patternInfo = null;

		try {
			patternInfo = new JsonPatternInfoSerializationConverter().convert(input.pattern());
		} catch (JsonException e) {
		}

		return new JsonFieldInfo(input.value(), new String[] { customName }, input.serializerName(), patternInfo);
	}

}