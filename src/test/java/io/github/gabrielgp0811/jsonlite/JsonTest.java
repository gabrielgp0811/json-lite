package io.github.gabrielgp0811.jsonlite;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.LogManager;
import java.util.logging.Logger;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import io.github.gabrielgp0811.jsonlite.constants.JsonStrings;
import io.github.gabrielgp0811.jsonlite.exception.JsonException;
import io.github.gabrielgp0811.jsonlite.impl.JsonBoolean;
import io.github.gabrielgp0811.jsonlite.impl.JsonLocalDate;
import io.github.gabrielgp0811.jsonlite.impl.JsonLocalDateTime;
import io.github.gabrielgp0811.jsonlite.impl.JsonLocalTime;
import io.github.gabrielgp0811.jsonlite.impl.JsonNumber;
import io.github.gabrielgp0811.jsonlite.impl.JsonObject;
import io.github.gabrielgp0811.jsonlite.impl.JsonString;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class JsonTest {

	private static final Logger LOGGER = Logger.getLogger(JsonTest.class.getName());

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		LogManager.getLogManager().readConfiguration();
	}

	@Test
	@Order(1)
	public void testToString() {
		JsonObject json = new JsonObject();

		json.addChildren(
				Json.toJson("JSON String 1"),
				new JsonString("string2", "JSON String 2"),
				Json.toJson(10),
				Json.toJson(new Address("Some Street", 1)),
				Json.toJson("address2", new Address("Some Street", 2)),
				new JsonNumber("bigint", BigInteger.valueOf(100)),
				new JsonNumber("bigdecimal", BigDecimal.valueOf(200.15D)),
				new JsonBoolean("boolean", true),
				new JsonLocalDate("localDate", LocalDate.now()),
				new JsonLocalDate("localDateFormat", LocalDate.now(), "MM/dd/yyyy"),
				new JsonLocalTime("localTime", LocalTime.now()),
				new JsonLocalTime("localTimeFormat", LocalTime.now(), "hh:mm:ss a"),
				new JsonLocalDateTime("localDateTime", LocalDateTime.now()),
				new JsonLocalDateTime("localDateTimeFormat", LocalDateTime.now(), "MM/dd/yyyy hh:mm:ss a"));

		LOGGER.info("toString():" + json);
		LOGGER.info("toPrettyString():" + JsonStrings.LINE_SEPARATOR + json.toPrettyString());
	}

	@Test
	@Order(2)
	public void testSerializeStringToJson() {
		LOGGER.info("toString():" + Json.toString("jsonString", "JSON String"));
		LOGGER.info("toPrettyString():" + Json.toPrettyString("jsonString", "JSON String"));
		LOGGER.info("toString():" + new JsonString("JSON String 2"));
		LOGGER.info("toPrettyString():" + new JsonString("JSON String 2").toPrettyString());
	}

	@Test
	@Order(3)
	public void testStringCollectionToJson() {
		List<String> list = new ArrayList<>();

		list.add("A");
		list.add("B");
		list.add("C");

		LOGGER.info("toString():" + Json.toString(list));
		LOGGER.info("toPrettyString():" + JsonStrings.LINE_SEPARATOR + Json.toPrettyString(list));

		JsonEntry<?> jsonCollection = Json.toJson(list);

		LOGGER.info("jsonCollection toString():" + jsonCollection);
		LOGGER.info("jsonCollection toPrettyString():" + JsonStrings.LINE_SEPARATOR + jsonCollection.toPrettyString());

		LOGGER.info("jsonCollection child(index=0) toString():" + jsonCollection.getChild(0));
		LOGGER.info("jsonCollection child(index=0) toPrettyString():" + jsonCollection.getChild(0).toPrettyString());
	}

	@Test
	@Order(4)
	public void testStringArrayToJson() {
		LOGGER.info("toString():" + Json.toString(new String[] { "A", "B", "C" }));
		LOGGER.info(
				"toPrettyString():" + JsonStrings.LINE_SEPARATOR + Json.toPrettyString(new String[] { "A", "B", "C" }));
	}

	@Test
	@Order(5)
	public void testNumberArrayToJson() {
		List<Integer> list = new ArrayList<>();

		list.add(1);
		list.add(2);
		list.add(3);

		LOGGER.info("toString():" + Json.toString(list));
		LOGGER.info("toPrettyString():" + JsonStrings.LINE_SEPARATOR + Json.toPrettyString(list));
	}

	@Test
	@Order(6)
	public void testObjectToString() {
		User user = new User(1, "gabrielgp0811", "pass123", Date.from(LocalDate.of(1987, 11, 8).atStartOfDay(ZoneId.systemDefault()).toInstant()), new Address("Some Street", 1));

		LOGGER.info("toString():" + Json.toString(user));
		LOGGER.info(
				"toPrettyString():" + JsonStrings.LINE_SEPARATOR + Json.toPrettyString(user));
	}

	@Test
	@Order(7)
	public void testRandomObjectArrayToJson() {
		List<Object> list = new ArrayList<>();

		User user = new User(1, "gabrielgp0811", "pass123", Date.from(LocalDate.of(1987, 11, 8).atStartOfDay(ZoneId.systemDefault()).toInstant()), new Address("Some Street", 1));

		list.add(1);
		list.add("TESTE");
		list.add(true);
		list.add(null);
		list.add(new Date());
		list.add(LocalDate.now());
		list.add(LocalTime.now());
		list.add(LocalDateTime.now());
		list.add(user.getBirthDate());
		list.add(user.getAddress());
		list.add(user);

		LOGGER.info("toString():" + Json.toString(list));
		LOGGER.info("toPrettyString():" + JsonStrings.LINE_SEPARATOR + Json.toPrettyString(list));
	}

	@Test
	@Order(8)
	public void testObjectCollectionToJson() {
		List<Address> list = new ArrayList<>();

		list.add(new Address("Some Street", 1));
		list.add(new Address("Some Street", 2));
		list.add(new Address("Some Street", 3));

		LOGGER.info("toString():" + Json.toString(list));
		LOGGER.info("toPrettyString():" + JsonStrings.LINE_SEPARATOR + Json.toPrettyString(list));
	}

	@Test
	@Order(9)
	public void testUserArrayToJson() {
		User user1 = new User(1, "gabrielgp0811_1", "pass123", Date.from(LocalDate.of(1987, 11, 8).atStartOfDay(ZoneId.systemDefault()).toInstant()), new Address("Some Street", 1));
		User user2 = new User(2, "gabrielgp0811_2", "pass456", Date.from(LocalDate.of(1987, 11, 8).atStartOfDay(ZoneId.systemDefault()).toInstant()), new Address("Some Street", 2));
		User user3 = new User(3, "gabrielgp0811_3", "pass789", Date.from(LocalDate.of(1987, 11, 8).atStartOfDay(ZoneId.systemDefault()).toInstant()), new Address("Some Street", 3));

		LOGGER.info("toString():" + Json.toString(new User[] { user1, user2, user3 }));
		LOGGER.info("toPrettyString():" + JsonStrings.LINE_SEPARATOR + Json.toPrettyString(new User[] { user1, user2, user3 }));
	}

	@Test
	@Order(10)
	public void testUserCollectionToJson() {
		List<User> list = new ArrayList<>();

		User user1 = new User(1, "gabrielgp0811_1", "pass123", Date.from(LocalDate.of(1987, 11, 8).atStartOfDay(ZoneId.systemDefault()).toInstant()), new Address("Some Street", 1));
		User user2 = new User(2, "gabrielgp0811_2", "pass456", Date.from(LocalDate.of(1987, 11, 8).atStartOfDay(ZoneId.systemDefault()).toInstant()), new Address("Some Street", 2));
		User user3 = new User(3, "gabrielgp0811_3", "pass789", Date.from(LocalDate.of(1987, 11, 8).atStartOfDay(ZoneId.systemDefault()).toInstant()), new Address("Some Street", 3));

		list.add(user1);
		list.add(user2);
		list.add(user3);

		LOGGER.info("toString():" + Json.toString(list));
		LOGGER.info("toPrettyString():" + JsonStrings.LINE_SEPARATOR + Json.toPrettyString(list));
	}

	@Test
	@Order(11)
	public void testBooleanToJson() {
		LOGGER.info(Json.toString(Boolean.TRUE));
		LOGGER.info(Json.toString("bool", Boolean.FALSE));
		LOGGER.info(Json.toPrettyString(Boolean.TRUE));
		LOGGER.info(Json.toPrettyString("bool", Boolean.FALSE));
	}

	@Test
	@Order(12)
	public void testFromJson() {
		String jsonText = "{\"name\":\"Gabriel\",\"lastname\":\"Pereira\"}";

		JsonEntry<?> json = Json.fromJson(jsonText);

		LOGGER.info("Text:" + jsonText);
		LOGGER.info("Json -> type:" + json.getClass());
		LOGGER.info("Json -> childrenSize:" + json.childrenSize());
		for (int i = 0; i < json.childrenSize(); i++) {
			LOGGER.info("Json child(" + i + ") -> type:" + json.getChild(i).getClass());
		}
		LOGGER.info("Json -> content:" + json);
		LOGGER.info("Json -> pretty content:" + JsonStrings.LINE_SEPARATOR + json.toPrettyString());
	}

	@Test
	@Order(13)
	public void testJsonSerialize() throws JsonException {
		Address address = new Address("Some Street", 1);

		User user = new User(1, "gabrielgp0811", "pass123", Date.from(LocalDate.of(1987, 11, 8).atStartOfDay(ZoneId.systemDefault()).toInstant()), address);

		LOGGER.info("Address.serialize -> " + Json.serialize("Address.serialize", address));
		LOGGER.info("Address.serializeName -> " + Json.serialize("Address.serializeName", address));
		LOGGER.info("Address.serializeNumber -> " + Json.serialize("Address.serializeNumber", address));

		LOGGER.info("User.prettySerialize -> " + Json.serialize("User.prettySerialize", user));
		LOGGER.info("User.serializeSecure -> " + Json.serialize("User.serializeSecure", user));
		LOGGER.info("User.serializeIdUsername -> " + Json.serialize("User.serializeIdUsername", user));
		LOGGER.info("User.serializeUsernameAddress -> " + Json.serialize("User.serializeUsernameAddress", user));
		LOGGER.info("User.serializeUsernameAddressName -> " + Json.serialize("User.serializeUsernameAddressName", user));
	}

	@Test
	@Order(14)
	public void testJsonPrettySerialize() throws JsonException {
		Address address = new Address("Some Street", 1);

		User user = new User(1, "gabrielgp0811", "pass123", Date.from(LocalDate.of(1987, 11, 8).atStartOfDay(ZoneId.systemDefault()).toInstant()), address);

		LOGGER.info("Address.prettySerialize -> " + Json.prettySerialize("Address.prettySerialize", address));
		LOGGER.info("Address.prettySerializeName -> " + Json.prettySerialize("Address.prettySerializeName", address));
		LOGGER.info("Address.prettySerializeNumber -> " + Json.prettySerialize("Address.prettySerializeNumber", address));

		LOGGER.info("User.prettySerialize -> " + Json.prettySerialize("User.prettySerialize", user));
		LOGGER.info("User.prettySerializeSecure -> " + Json.prettySerialize("User.prettySerializeSecure", user));
		LOGGER.info("User.prettySerializeIdUsername -> " + Json.prettySerialize("User.prettySerializeIdUsername", user));
		LOGGER.info("User.prettySerializeUsernameAddress -> " + Json.prettySerialize("User.prettySerializeUsernameAddress", user));
		LOGGER.info("User.prettySerializeUsernameAddressName -> " + Json.prettySerialize("User.prettySerializeUsernameAddressName", user));
	}

	@Test
	@Order(15)
	public void testDeserializer() throws JsonException {
		String jsonObjectString = "    {\"name\":\"Gabriel\",    \"lastname\":\"Pereira\",\"age\":35,\"double\":10.5,\"stringArray\":[\"first\",\"second\",\"third\"],\"intArray\":[1,2,3]      }";
		String jsonArrayString = "   [  \"first\"   ,\"second\",\"third\"  ]   ";

		JsonEntry<?> jsonObject = Json.fromJson(jsonObjectString);
		JsonEntry<?> jsonArray = Json.fromJson(jsonArrayString);

		LOGGER.info("jsonObject:" + JsonStrings.LINE_SEPARATOR + jsonObject.toPrettyString());
		LOGGER.info("jsonObject:" + jsonObject.getClass().toString());
		jsonObject.getChildren().forEach(json -> LOGGER.info(json.getName() + ":" + json.getClass()));

		LOGGER.info("jsonArray:" + JsonStrings.LINE_SEPARATOR + jsonArray.toPrettyString());
		LOGGER.info("jsonArray:" + jsonArray.getClass().toString());
		jsonArray.getChildren().forEach(json -> LOGGER.info(json.getValue() + ":" + json.getClass()));
	}

	@Test
	@Order(16)
	public void testStaticToJavaObject() {
		String jsonAddress1 = "{\"name\":\"Some Street\",\"number\":1}";
		String jsonAddress2 = "{\"name\":\"Some Street\",\"number\":2}";
		String jsonArrayAddress = "[".concat(jsonAddress1).concat("]");
		String jsonArrayAddresses = "[".concat(jsonAddress1).concat(",").concat(jsonAddress2).concat("]");
		String jsonUser = "{\"id\":1,\"username\":\"gabrielgp0811\",\"password\":\"pass123\",\"BirthDate\":\"1987-11-08\",\"Address\":".concat(jsonAddress1).concat("}");

		LOGGER.info(jsonAddress1);
		LOGGER.info(jsonAddress2);
		LOGGER.info(jsonArrayAddress);
		LOGGER.info(jsonArrayAddresses);
		LOGGER.info(jsonUser);
		LOGGER.info("");

		LOGGER.info(Json.toJavaObject(jsonAddress1, Address.class).toString());
		LOGGER.info(Json.toJavaObject(jsonAddress2, Address.class).toString());
		LOGGER.info(Json.toJavaObject(jsonArrayAddress, Address.class).toString());
		LOGGER.info(Json.toJavaObject(jsonArrayAddresses, Address.class).toString());
		LOGGER.info(Json.toJavaCollection(jsonAddress1, Address.class).toString());
		LOGGER.info(Json.toJavaCollection(jsonAddress2, Address.class).toString());
		LOGGER.info(Json.toJavaCollection(jsonArrayAddress, Address.class).toString());
		LOGGER.info(Json.toJavaCollection(jsonArrayAddresses, Address.class).toString());
		LOGGER.info(Json.toJavaObject(jsonUser, User.class).toString());
	}

	@Test
	@Order(17)
	public void testToJavaObject() {
		String jsonAddress1 = "{\"name\":\"Some Street\",\"number\":1}";
		String jsonAddress2 = "{\"name\":\"Some Street\",\"number\":2}";
		String jsonArrayAddress = "[".concat(jsonAddress1).concat("]");
		String jsonArrayAddresses = "[".concat(jsonAddress1).concat(",").concat(jsonAddress2).concat("]");
		String jsonUser = "{\"id\":1,\"username\":\"gabrielgp0811\",\"password\":\"pass123\",\"Birth Date\":\"1987-11-08\",\"Address\":".concat(jsonAddress1).concat("}");

		LOGGER.info(jsonAddress1);
		LOGGER.info(jsonAddress2);
		LOGGER.info(jsonArrayAddress);
		LOGGER.info(jsonArrayAddresses);
		LOGGER.info(jsonUser);
		LOGGER.info("");

		LOGGER.info(Json.fromJson(jsonAddress1).toJavaObject(Address.class).toString());
		LOGGER.info(Json.fromJson(jsonAddress2).toJavaObject(Address.class).toString());
		LOGGER.info(Json.fromJson(jsonAddress1).toJavaCollection(Address.class).toString());
		LOGGER.info(Json.fromJson(jsonAddress2).toJavaCollection(Address.class).toString());
		LOGGER.info(Json.fromJson(jsonArrayAddress).toJavaCollection(Address.class).toString());
		LOGGER.info(Json.fromJson(jsonArrayAddresses).toJavaCollection(Address.class).toString());
		LOGGER.info(Json.fromJson(jsonUser).toJavaObject(User.class).toString());
	}

}