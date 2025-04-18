package api_POM;

import static io.restassured.RestAssured.given;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class PetStoreOrdersAPI {
	
	private static final String BASE_PATH="/store";
	
	public static Response getPetInventory() {
		return given()
				.when().
				get(BASE_PATH + "/inventory");
	}
	
	public static Response getInvalidPetInventory() {
		return given()
				.when().
				get(BASE_PATH + "/invent");
	}
	
	public static Response placeOrder(String payload) {
		return given()
				.contentType(ContentType.JSON)
				.body(payload)
				.when()
				.post(BASE_PATH + "/order");
	
	}
	
	
	
	
	

}
