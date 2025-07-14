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
public class JsonPatternInfo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3228260671489453201L;

	private String pattern = null;

	private Locale locale = null;

	private TimeZone timezone = null;

	public JsonPatternInfo() {
	}

	public JsonPatternInfo(String pattern, Locale locale, TimeZone timezone) {
		this.pattern = pattern;
		this.locale = locale;
		this.timezone = timezone;
	}

	public String getPattern() {
		return pattern;
	}

	public void setPattern(String pattern) {
		this.pattern = pattern;
	}

	public Locale getLocale() {
		return locale;
	}

	public void setLocale(Locale locale) {
		this.locale = locale;
	}

	public TimeZone getTimezone() {
		return timezone;
	}

	public void setTimezone(TimeZone timezone) {
		this.timezone = timezone;
	}

	@Override
	public String toString() {
		return "JsonPatternInfo [pattern=" + pattern + ", locale=" + locale + ", timezone=" + timezone + "]";
	}

}