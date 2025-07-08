/**
 * 
 */
package io.github.gabrielgp0811.jsonlite.util;

import java.io.Serializable;
import java.util.Locale;
import java.util.TimeZone;

/**
 * @author gabrielgp0811
 */
public class JsonFormatInfo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3228260671489453201L;

	private String pattern = null;

	private Locale locale = null;

	private TimeZone timezone = null;

	/**
	 * 
	 */
	public JsonFormatInfo() {

	}

	/**
	 * @param pattern  the pattern to set
	 * @param locale   the locale to set
	 * @param timezone the timezone to set
	 */
	public JsonFormatInfo(String pattern, Locale locale, TimeZone timezone) {
		this.pattern = pattern;
		this.locale = locale;
		this.timezone = timezone;
	}

	/**
	 * @return the pattern
	 */
	public String getPattern() {
		if (pattern == null) {
			pattern = "";
		}
		return pattern;
	}

	/**
	 * @param pattern the pattern to set
	 */
	public void setPattern(String pattern) {
		this.pattern = pattern;
	}

	/**
	 * @return the locale
	 */
	public Locale getLocale() {
		return locale;
	}

	/**
	 * @param locale the locale to set
	 */
	public void setLocale(Locale locale) {
		this.locale = locale;
	}

	/**
	 * @return the timezone
	 */
	public TimeZone getTimezone() {
		return timezone;
	}

	/**
	 * @param timezone the timezone to set
	 */
	public void setTimezone(TimeZone timezone) {
		this.timezone = timezone;
	}

	@Override
	public String toString() {
		return "JsonFormatInfo [pattern=" + pattern + ", locale=" + locale + ", timezone=" + timezone + "]";
	}

}