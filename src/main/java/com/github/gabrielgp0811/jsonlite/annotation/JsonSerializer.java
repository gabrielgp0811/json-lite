/**
 * 
 */
package com.github.gabrielgp0811.jsonlite.annotation;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Repeatable;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

/**
 * Annotation responsible for informations of serialization.
 * 
 * @author gabrielgp0811
 */
@Documented
@Retention(RUNTIME)
@Target(TYPE)
@Repeatable(JsonSerializers.class)
public @interface JsonSerializer {

	/**
	 * The serializer's name.
	 * 
	 * @return The name.
	 */
	String name();

	/**
	 * Fields for serialization.
	 * 
	 * @return The fields.
	 */
	JsonField[] fields();

}