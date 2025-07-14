/**
 * 
 */
package io.github.gabrielgp0811.jsonlite;

import java.util.Date;

import io.github.gabrielgp0811.jsonlite.annotation.JsonField;
import io.github.gabrielgp0811.jsonlite.annotation.JsonPattern;
import io.github.gabrielgp0811.jsonlite.annotation.JsonSerializer;

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

	public User() {
	}

	public User(Integer id, String username, String password, Date birthDate, Address address) {
		this.id = id;
		this.username = username;
		this.password = password;
		this.birthDate = birthDate;
		this.address = address;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Date getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", username=" + username + ", password=" + password + ", birthDate=" + birthDate
				+ ", address=" + address + "]";
	}

}