/**
 * 
 */
package io.github.gabrielgp0811.jsonlite.annotation;

import static java.lang.annotation.ElementType.FIELD;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Annotation responsible for patterns.
 * 
 * @author gabrielgp0811
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(FIELD)
public @interface JsonPattern {

	/**
	 * @return The format responsible for value's serialization/deserialization.
	 */
	String value() default "";

	/**
	 * @return The format responsible for value's serialization.
	 */
	String serializePattern() default "";

	/**
	 * @return The format responsible for value's deserialization.
	 */
	String deserializePattern() default "";

	/**
	 * @return The {@link java.util.Locale Locale}.
	 */
	String locale() default "";

	/**
	 * @return The {@link java.util.TimeZone TimeZone}.
	 */
	String timezone() default "";

}