/**
 * 
 */
package io.github.gabrielgp0811.jsonlite;

import io.github.gabrielgp0811.jsonlite.annotation.JsonField;
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
		name = "Address.serialize",
		fields = {
				@JsonField("name"),
				@JsonField("number")
		}
)
@JsonSerializer(
		name = "Address.serializeName",
		fields = @JsonField("name")
)
@JsonSerializer(
		name = "Address.serializeNumber",
		fields = @JsonField("number")
)
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class Address {

	private String name = null;

	private Integer number = null;

}