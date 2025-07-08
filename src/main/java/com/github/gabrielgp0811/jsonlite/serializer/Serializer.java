/**
 * 
 */
package com.github.gabrielgp0811.jsonlite.serializer;

import com.github.gabrielgp0811.jsonlite.JsonEntry;
import com.github.gabrielgp0811.jsonlite.exception.JsonException;

/**
 * Interface for serialization.
 * 
 * @author gabrielgp0811
 */
public interface Serializer {

	/**
	 * Serializes a Java object specified in <code>obj</code> using serializer with
	 * name specified in <code>name</code>.
	 * 
	 * @param name The serializer's name.
	 * @param obj  The object to be serialized.
	 * @return The serialized object.
	 * @throws JsonException Error serializing object.
	 */
	JsonEntry<?> serialize(String name, Object obj) throws JsonException;

}