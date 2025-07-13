/**
 * 
 */
package io.github.gabrielgp0811.jsonlite.constants;

/**
 * Class containing all characters for <strong>JSON</strong> syntax.
 * 
 * @author gabrielgp0811
 */
public final class JsonStrings {

	/**
	 * 
	 */
	private JsonStrings() {

	}

	/**
	 * Start object.
	 */
	public static final String START = "{";

	/**
	 * End object.
	 */
	public static final String END = "}";

	/**
	 * Start array.
	 */
	public static final String START_ARRAY = "[";

	/**
	 * End array.
	 */
	public static final String END_ARRAY = "]";

	/**
	 * Colon.
	 */
	public static final String COLON = ":";

	/**
	 * Comma.
	 */
	public static final String COMMA = ",";

	/**
	 * Quotation.
	 */
	public static final String QUOTATION = "\"";

	/**
	 * Whitespace.
	 */
	public static final String WHITESPACE = " ";

	/**
	 * Tabulation.
	 */
	public static final String TAB = "\t";

	/**
	 * LIne separator.
	 */
	public static final String LINE_SEPARATOR = System.getProperty("line.separator", "\n");

	/**
	 * The field's name.
	 */
	public static final String FIELD_NAME = "name";

	/**
	 * The field's name for {@link io.github.gabrielgp0811.jsonlite.impl.JsonBoolean JsonBoolean}.
	 */
	public static final String BOOLEAN_NAME = "boolean";

	/**
	 * The field's name for {@link io.github.gabrielgp0811.jsonlite.impl.JsonCollection JsonCollection}.
	 */
	public static final String COLLECTION_NAME = "collection";

	/**
	 * The field's name for {@link io.github.gabrielgp0811.jsonlite.impl.JsonDate JsonDate}.
	 */
	public static final String DATE_NAME = "date";

	/**
	 * The field's name for {@link io.github.gabrielgp0811.jsonlite.impl.JsonEnum JsonEnum}.
	 */
	public static final String ENUM_NAME = "enum";

	/**
	 * The field's name for {@link io.github.gabrielgp0811.jsonlite.impl.JsonLocalDate JsonLocalDate}.
	 */
	public static final String LOCALDATE_NAME = "localDate";

	/**
	 * The field's name for {@link io.github.gabrielgp0811.jsonlite.impl.JsonLocalTime JsonLocalTime}.
	 */
	public static final String LOCALTIME_NAME = "localTime";

	/**
	 * The field's name for {@link io.github.gabrielgp0811.jsonlite.impl.JsonLocalDateTime JsonLocalDateTime}.
	 */
	public static final String LOCALDATETIME_NAME = "localDateTime";

	/**
	 * The field's name for {@link io.github.gabrielgp0811.jsonlite.impl.JsonNull JsonNull}.
	 */
	public static final String NULL_NAME = "null";

	/**
	 * The field's name for {@link io.github.gabrielgp0811.jsonlite.impl.JsonNumber JsonNumber}.
	 */
	public static final String NUMBER_NAME = "number";

	/**
	 * The field's name for {@link io.github.gabrielgp0811.jsonlite.impl.JsonObject JsonObject}.
	 */
	public static final String OBJECT_NAME = "object";

	/**
	 * The field's name for {@link io.github.gabrielgp0811.jsonlite.impl.JsonString JsonString}.
	 */
	public static final String STRING_NAME = "string";

}