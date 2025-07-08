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
		name = "User.serializeSecure",
		fields = {
				@JsonField("id"),
				@JsonField("username"),
				@JsonField("birthDate"),
				@JsonField("address"),
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
		name = "User.serializeUsernameAddress",
		fields = {
				@JsonField("username"),
				@JsonField("address"),
		}
)
@JsonSerializer(
		name = "User.serializeUsernameAddressName",
		fields = {
				@JsonField("username"),
				@JsonField("address.name"),
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

	@JsonPattern(value = "yyyy-MM-dd")
	private Date birthDate = null;

	private Address address = null;

}