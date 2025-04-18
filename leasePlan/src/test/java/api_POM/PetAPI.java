package api_POM;

import io.restassured.response.Response;
import static io.restassured.RestAssured.given;

import io.restassured.http.ContentType;

public class PetAPI {
	
	private static final String BASE_PATH="/pet";
	
	public static Response createPet(String payload) {
		return given()
				.contentType(ContentType.JSON)
				.body(payload)
				.when()
				.post(BASE_PATH);
		
	}
	
	public static Response getPetById(int id) {
		return given()
				.contentType(ContentType.JSON)
				.pathParam("petId", id)
				.when()
				.get(BASE_PATH + "/{petId}");
	}
	
	public static Response deletePetById(int id) {
		return given()
				.contentType(ContentType.JSON)
				.pathParam("petId", id)
				.when()
				.delete(BASE_PATH + "/{petId}");
	}
	
	public static Response updatePet(String payload) {
		return given()
				.contentType(ContentType.JSON)
				.body(payload)
				.when()
				.put(BASE_PATH); 
	}
		


}
