/**
 * 
 */
package io.github.gabrielgp0811.jsonlite.annotation;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

/**
 * Annotation that represents a class attribute.
 * 
 * @author gabrielgp0811
 */
@Retention(RUNTIME)
@Target(TYPE)
public @interface JsonField {

	/**
	 * @return The attribute's name.
	 */
	String value();

	/**
	 * The date/time pattern, in case attribute is a <code>java.util.Date</code>,
	 * <code>java.time.LocalDate</code>, <code>java.time.LocalTime</code> or
	 * <code>java.time.LocalDateTime</code>.
	 * 
	 * @return The date/time pattern.
	 */
	JsonPattern pattern() default @JsonPattern;

}