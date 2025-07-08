/**
 * 
 */
package com.github.gabrielgp0811.jsonlite.annotation;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

/**
 * Annotation responsible for making the attribute be ignored in conversion.
 * 
 * @author gabrielgp0811
 */
@Retention(RUNTIME)
@Target(FIELD)
public @interface JsonIgnore {
}