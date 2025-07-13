/**
 * 
 */
package io.github.gabrielgp0811.jsonlite;

import java.util.Date;

import io.github.gabrielgp0811.jsonlite.annotation.JsonField;
import io.github.gabrielgp0811.jsonlite.annotation.JsonPattern;
import io.github.gabrielgp0811.jsonlite.annotation.JsonSerializer;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * @author gabrielgp0811
 */
@JsonSerializer(
	name = "User.serialize",
	fields = {
		@JsonField("id"),
		@JsonField("username"),
		@JsonField("password"),
		@JsonField("birthDate"),
		@JsonField("address"),
	}
)
@JsonSerializer(
	name = "User.prettySerialize",
	fields = {
		@JsonField(value = "id", customNameSerialization = "ID"),
		@JsonField(value = "username", customNameSerialization = "Username"),
		@JsonField(value = "password", customNameSerialization = "Password"),
		@JsonField(
			value = "birthDate",
			customNameSerialization = "Birth Date",
			pattern = @JsonPattern("yyyy-MM-dd")
		),
		@JsonField(
			value = "address",
			customNameSerialization = "Address",
			serializerName = "Address.prettySerialize"
		),
	}
)
@JsonSerializer(
	name = "User.serializeSecure",
	fields = {
		@JsonField("id"),
		@JsonField("username"),
		@JsonField("birthDate"),
		@JsonField("address")
	}
)
@JsonSerializer(
	name = "User.prettySerializeSecure",
	fields = {
		@JsonField(value = "id", customNameSerialization = "ID"),
		@JsonField(value = "username", customNameSerialization = "Username"),
		@JsonField(value = "birthDate", customNameSerialization = "Birth Date"),
		@JsonField(
			value = "address",
			customNameSerialization = "Address",
			serializerName = "Address.prettySerialize"
		),
	}
)
@JsonSerializer(
	name = "User.serializeIdUsername", 
	fields = {
		@JsonField("id"),
		@JsonField("username"),
	}
)
@JsonSerializer(
	name = "User.prettySerializeIdUsername",
	fields = {
		@JsonField(value = "id", customNameSerialization = "ID"),
		@JsonField(value = "username", customNameSerialization = "Username"),
	}
)
@JsonSerializer(
	name = "User.serializeUsernameAddress", 
	fields = {
		@JsonField(value = "username"),
		@JsonField(value = "address"),
	}
)
@JsonSerializer(
	name = "User.prettySerializeUsernameAddress", 
	fields = {
		@JsonField(value = "username", customNameSerialization = "Username"),
		@JsonField(
			value = "address",
			customNameSerialization = "Address",
			serializerName = "Address.prettySerialize"
		),
	}
)
@JsonSerializer(
	name = "User.serializeUsernameAddressName", 
	fields = {
		@JsonField("username"),
		@JsonField("address.name")
	}
)
@JsonSerializer(
	name = "User.prettySerializeUsernameAddressName",
	fields = {
		@JsonField(value = "username", customNameSerialization = "Username"),
		@JsonField(
			value = "address",
			customNameSerialization = "Address",
			serializerName = "Address.prettySerializeName"
		),
	}
)
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class User {

	private Integer id = null;

	private String username = null;

	private String password = null;

	@JsonField(
		customNameSerialization = "Birth Date",
		customNamesDeserialization = { "BirthDate", "Birth Date" },
		pattern = @JsonPattern("yyyy-MM-dd")
	)
	private Date birthDate = null;

	@JsonField(
		customNamesDeserialization = "Address"
	)
	private Address address = null;

}