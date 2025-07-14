/**
 * 
 */
package io.github.gabrielgp0811.jsonlite.util;

import java.io.Serializable;
import java.util.Arrays;

/**
 * @author gabrielgp0811
 */
public class JsonFieldInfo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3228260671489453201L;

	private String name = null;

	private String[] customNames = null;

	private String serializerName = null;

	private JsonPatternInfo patternInfo = null;

	public JsonFieldInfo() {
	}

	public JsonFieldInfo(String name, JsonPatternInfo patternInfo) {
		this(name, new String[0], patternInfo);
	}

	public JsonFieldInfo(String name, String[] customNames, JsonPatternInfo patternInfo) {
		this(name, customNames, "", patternInfo);
	}

	public JsonFieldInfo(String name, String[] customNames, String serializerName, JsonPatternInfo patternInfo) {
		this.name = name;
		this.customNames = customNames;
		this.serializerName = serializerName;
		this.patternInfo = patternInfo;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String[] getCustomNames() {
		return customNames;
	}

	public void setCustomNames(String[] customNames) {
		this.customNames = customNames;
	}

	public String getSerializerName() {
		return serializerName;
	}

	public void setSerializerName(String serializerName) {
		this.serializerName = serializerName;
	}

	public JsonPatternInfo getPatternInfo() {
		return patternInfo;
	}

	public void setPatternInfo(JsonPatternInfo patternInfo) {
		this.patternInfo = patternInfo;
	}

	@Override
	public String toString() {
		return "JsonFieldInfo [name=" + name + ", customNames=" + Arrays.toString(customNames) + ", serializerName="
				+ serializerName + ", patternInfo=" + patternInfo + "]";
	}

}