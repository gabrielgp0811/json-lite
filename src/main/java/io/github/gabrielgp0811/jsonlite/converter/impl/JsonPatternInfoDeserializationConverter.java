/**
 * 
 */
package io.github.gabrielgp0811.jsonlite.converter.impl;

import java.util.Locale;
import java.util.TimeZone;

import io.github.gabrielgp0811.jsonlite.annotation.JsonPattern;
import io.github.gabrielgp0811.jsonlite.converter.Converter;
import io.github.gabrielgp0811.jsonlite.exception.JsonException;
import io.github.gabrielgp0811.jsonlite.util.JsonPatternInfo;
import io.github.gabrielgp0811.jsonlite.util.Util;

/**
 * Class responsible for converting {@link JsonPattern} annotation into a
 * {@link JsonPatternInfo} object.
 * 
 * @author gabrielgp0811
 */
public class JsonPatternInfoDeserializationConverter implements Converter<JsonPattern, JsonPatternInfo> {

	/**
	 * 
	 */
	public JsonPatternInfoDeserializationConverter() {

	}

	@Override
	public JsonPatternInfo convert(JsonPattern input) throws JsonException {
		if (input == null) {
			throw new JsonException("input is null");
		}

		String pattern = input.deserializePattern();

		if (pattern.trim().isEmpty()) {
			pattern = input.value();
		}

		Locale locale = Util.toLocale(input.locale());
		TimeZone timezone = Util.toTimeZone(input.timezone());

		return new JsonPatternInfo(pattern, locale, timezone);
	}

}