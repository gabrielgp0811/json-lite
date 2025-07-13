/**
 * 
 */
package io.github.gabrielgp0811.jsonlite.annotation;

import static java.lang.annotation.ElementType.FIELD;
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
@Target({ FIELD, TYPE })
public @interface JsonField {

	/**
	 * @return The attribute's name.
	 */
	String value() default "";

	/**
	 * @return The attribute's custom name for serialization/deserialization.
	 */
	String customName() default "";

	/**
	 * @return The attribute's custom name for serialization.
	 */
	String customNameSerialization() default "";

	/**
	 * @return The attribute's custom name for deserialization.
	 */
	String customNameDeserialization() default "";

	/**
	 * @return The attribute's custom names for deserialization.
	 */
	String[] customNamesDeserialization() default {};

	/**
	 * @return The serializer's name from child object.
	 */
	String serializerName() default "";

	/**
	 * The date/time pattern, in case attribute is a <code>java.util.Date</code>,
	 * <code>java.time.LocalDate</code>, <code>java.time.LocalTime</code> or
	 * <code>java.time.LocalDateTime</code>.
	 * 
	 * @return The date/time pattern.
	 */
	JsonPattern pattern() default @JsonPattern;

}