/**
 * 
 */
package io.github.gabrielgp0811.jsonlite.util;

import java.io.Serializable;
import java.util.Locale;
import java.util.TimeZone;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * @author gabrielgp0811
 */
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class JsonPatternInfo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3228260671489453201L;

	private String pattern = null;

	private Locale locale = null;

	private TimeZone timezone = null;

}