/**
 * 
 */
package io.github.gabrielgp0811.jsonlite;

import io.github.gabrielgp0811.jsonlite.annotation.JsonField;
import io.github.gabrielgp0811.jsonlite.annotation.JsonSerializer;

/**
 * @author gabrielgp0811
 */
@JsonSerializer(
	name = "Address.serialize",
	fields = {
		@JsonField("name"),
		@JsonField("number")
	}
)
@JsonSerializer(
	name = "Address.prettySerialize",
	fields = {
		@JsonField(value = "name", customNameSerialization = "Name"),
		@JsonField(value = "number", customNameSerialization = "Number")
	}
)
@JsonSerializer(
	name = "Address.serializeName",
	fields = @JsonField("name")
)
@JsonSerializer(
	name = "Address.prettySerializeName",
	fields = @JsonField(value = "name", customName = "Name")
)
@JsonSerializer(
	name = "Address.serializeNumber",
	fields = @JsonField("number")
)
@JsonSerializer(
	name = "Address.prettySerializeNumber",
	fields = @JsonField(value = "number", customName = "Number")
)
public class Address {

	private String name = null;

	private Integer number = null;

	public Address() {
	}

	public Address(String name, Integer number) {
		this.name = name;
		this.number = number;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getNumber() {
		return number;
	}

	public void setNumber(Integer number) {
		this.number = number;
	}

	@Override
	public String toString() {
		return "Address [name=" + name + ", number=" + number + "]";
	}

}