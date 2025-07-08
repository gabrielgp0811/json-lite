/**
 * 
 */
package com.github.gabrielgp0811.jsonlite.annotation;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

/**
 * Annotation responsible for informations of all serializers.
 * 
 * @author gabrielgp0811
 */
@Documented
@Retention(RUNTIME)
@Target(TYPE)
public @interface JsonSerializers {

	/**
	 * @return The serializers.
	 */
	JsonSerializer[] value();

}