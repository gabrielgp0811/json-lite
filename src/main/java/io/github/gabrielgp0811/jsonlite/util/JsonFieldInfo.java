/**
 * 
 */
package io.github.gabrielgp0811.jsonlite.util;

import java.io.Serializable;

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
public class JsonFieldInfo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3228260671489453201L;

	private String name = null;

	private String[] customNames = null;

	private String serializerName = null;

	private JsonPatternInfo patternInfo = null;

	/**
	 * @param name        the name to set
	 * @param patternInfo the patternInfo to set
	 */
	public JsonFieldInfo(String name, JsonPatternInfo patternInfo) {
		this(name, new String[0], patternInfo);
	}

	/**
	 * @param name        the name to set
	 * @param customNames the custom names to set
	 * @param patternInfo the patternInfo to set
	 */
	public JsonFieldInfo(String name, String[] customNames, JsonPatternInfo patternInfo) {
		this.name = name;
		this.customNames = customNames;
		this.serializerName = "";
		this.patternInfo = patternInfo;
	}

}