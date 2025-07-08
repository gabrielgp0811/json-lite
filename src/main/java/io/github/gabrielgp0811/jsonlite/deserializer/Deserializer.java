/**
 * 
 */
package io.github.gabrielgp0811.jsonlite.deserializer;

import io.github.gabrielgp0811.jsonlite.JsonEntry;
import io.github.gabrielgp0811.jsonlite.exception.JsonException;

/**
 * Interface for deserialization.
 * 
 * @author gabrielgp0811
 */
public interface Deserializer {

	/**
	 * Deserializes a JSON string specified in <code>json</code> into a
	 * {@link JsonEntry} object.
	 * 
	 * @param json JSON string.
	 * @return The JSON object.
	 * @throws JsonException Error on deserialization.
	 */
	JsonEntry<?> deserialize(String json) throws JsonException;

}