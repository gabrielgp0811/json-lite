[![Github contributors][contributors-shield]][contributors-url]
[![Github forks][forks-shield]][forks-url]
[![Github stars][stars-shield]][stars-url]
[![Github issues][issues-shield]][issues-url]
[![Github license][license-shield]][license-url]


# Json Lite

Lite library for converting Java Objects from/to JSON.
## Usage

Let's take **User** and **Address** class below.

```java
import java.util.Date;

public class User {

    private Integer id = null;

    private String username = null;

    private String password = null;

    @JsonPattern(value = "yyyy-MM-dd")
    private Date birthDate = null;

    private Address address = null;

    // ...getters, setters and toString...

}
```

```java
public class Address {

    private String name = null;

    private Integer number = null;

    // ...getters, setters and toString...

}
```

The **User** class can be converted to JSON like this:

```java
import io.github.gabrielgp0811.jsonlite;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

public class MainClass {

    public static void main(String[] args) {
        User user = new User();

        user.setId(1);
        user.setUsername("gabrielgp0811");
        user.setPassword("pass123");
        user.setBirthdate(Date.from(LocalDate.of(1987, 11, 8).atStartOfDay(ZoneId.systemDefault()).toInstant()));
        user.setAddress(new Address("Some Street", 1));

        System.out.println(Json.toString(user));
        
        // Output will be
        // {"id":1,"username":"gabrielgp0811","password":"pass123","birthDate":"1987-11-08","address":{"name":"Some Street","number":1}}

        // Also possible to "pretty string" for better visualization
        System.out.println(Json.toPrettyString(user));
        
        // Output will be
        //  {
        //      "id": 1,
        //      "username": "gabrielgp0811",
        //      "password": "pass123",
        //      "birthDate": "1987-11-08"
        //      "andress": {
        //          "name": "Some Street",
        //          "number": 1
        //      }
        //  }
    }

}
```

Converting from JSON would be like this:

```java
import io.github.gabrielgp0811.jsonlite;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

public class MainClass {

    public static void main(String[] args) {
        String json = "{\"id\":1,\"username\":\"gabrielgp0811\",\"password\":\"pass123\",\"birthDate\":\"1987-11-08\",\"address\":{\"name\":\"Some Street\",\"number\":1}}";
        User user = null;

        JsonEntry<?> jsonEntry = Json.fromJson(json); // It's possible to manipulate jsonEntry

        user = jsonEntry.toJavaObject(User.class);

        // Or

        user = Json.toJavaObject(json, User.class);

        System.out.println(user);
        
        // Output will be
        // User(id=1, username=gabrielgp0811, password=pass123, birthDate=Sun Nov 08 00:00:00 BRST 1987, address=Address(name=Some Street, number=1))
    }

}
```

## Advanced Features

It's possible to build your own serialization like this:

```java
import java.util.Date;

import io.github.gabrielgp0811.jsonlite.annotation.JsonField;
import io.github.gabrielgp0811.jsonlite.annotation.JsonPattern;
import io.github.gabrielgp0811.jsonlite.annotation.JsonSerializer;

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
public class User {

    private Integer id = null;

    private String username = null;

    private String password = null;

    @JsonPattern(value = "yyyy-MM-dd")
    private Date birthDate = null;

    private Address address = null;

    //...getters, setters and toString...

}
```

That way, it's possible to convert your class to JSON like this:

```java
import io.github.gabrielgp0811.jsonlite;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

public class MainClass {

    public static void main(String[] args) {
        User user = new User();

        user.setId(1);
        user.setUsername("gabrielgp0811");
        user.setPassword("pass123");
        user.setBirthdate(Date.from(LocalDate.of(1987, 11, 8).atStartOfDay(ZoneId.systemDefault()).toInstant()));

        System.out.println("User.serialize -> " + Json.serialize("User.serialize", user));
        System.out.println("User.serializeSecure -> " + Json.serialize("User.serializeSecure", user));
        System.out.println("User.serializeIdUsername -> " + Json.serialize("User.serializeIdUsername", user));
        System.out.println("User.serializeUsernameAddress -> " + Json.serialize("User.serializeUsernameAddress", user));
        System.out.println("User.serializeUsernameAddressName -> " + Json.serialize("User.serializeUsernameAddressName", user));
       
        // Output will be
        // User.serialize -> {"id":1,"username":"gabrielgp0811","password":"pass123","birthDate":"1987-11-08","address":{"name":"Some Street","number":1}}
        // User.serializeSecure -> {"id":1,"username":"gabrielgp0811","birthDate":"1987-11-08","address":{"name":"Some Street","number":1}}
        // User.serializeIdUsername -> {"id":1,"username":"gabrielgp0811"}
        // User.serializeUsernameAddress -> {"username":"gabrielgp0811","address":{"name":"Some Street","number":1}}
        // User.serializeUsernameAddressName -> {"username":"gabrielgp0811","address":{"name":"Some Street"}}

        // Also possible to "pretty serialize" for better visualization
        System.out.println("User.serialize -> " + Json.prettySerialize("User.serialize", user));
        System.out.println("User.serializeSecure -> " + Json.prettySerialize("User.serializeSecure", user));
        System.out.println("User.serializeIdUsername -> " + Json.prettySerialize("User.serializeIdUsername", user));
        System.out.println("User.serializeUsernameAddress -> " + Json.prettySerialize("User.serializeUsernameAddress", user));
        System.out.println("User.serializeUsernameAddressName -> " + Json.prettySerialize("User.serializeUsernameAddressName", user));
        
        // Output will be
        // User.serialize -> {
        //      "id": 1,
        //      "username": "gabrielgp0811",
        //      "password": "pass123",
        //      "birthDate":"1987-11-08",
        //      "address": {
        //          "name": "Some Street",
        //          "number": 1
        //      }
        // }
        // User.serializeSecure -> {
        //      "id": 1,
        //      "username": "gabrielgp0811",
        //      "birthDate":"1987-11-08",
        //      "address": {
        //          "name": "Some Street",
        //          "number": 1
        //      }
        // }
        // User.serializeIdUsername -> {
        //      "id": 1,
        //      "username": "gabrielgp0811"
        // }
        // User.serializeUsernameAddress -> {
        //      "username": "gabrielgp0811",
        //      "address": {
        //          "name": "Some Street",
        //          "number": 1
        //      }
        // }
        // User.serializeUsernameAddressName -> {
        //      "username": "gabrielgp0811",
        //      "address": {
        //          "name": "Some Street"
        //      }
        // }
    }

}
```

## Feedback

If you have any feedback, please reach out to me at gabrielgp0811@gmail.com

[contributors-shield]: https://img.shields.io/github/contributors/gabrielgp0811/json-lite.svg?style=for-the-badge
[contributors-url]: https://github.com/gabrielgp0811/json-lite/graphs/contributors
[forks-shield]: https://img.shields.io/github/forks/gabrielgp0811/json-lite.svg?style=for-the-badge
[forks-url]: https://github.com/gabrielgp0811/json-lite/network/members
[stars-shield]: https://img.shields.io/github/stars/gabrielgp0811/json-lite.svg?style=for-the-badge
[stars-url]: https://github.com/gabrielgp0811/json-lite/stargazers
[issues-shield]: https://img.shields.io/github/issues/gabrielgp0811/json-lite.svg?style=for-the-badge
[issues-url]: https://github.com/gabrielgp0811/json-lite/issues
[license-shield]: https://img.shields.io/github/license/gabrielgp0811/json-lite?style=for-the-badge
[license-url]: https://github.com/gabrielgp0811/json-lite/blob/master/LICENSE