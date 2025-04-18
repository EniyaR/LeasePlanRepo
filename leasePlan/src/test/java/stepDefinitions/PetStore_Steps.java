package stepDefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import net.serenitybdd.core.Serenity;
import utils.FileUtils;



import api_POM.PetAPI;
import api_POM.PetStoreOrdersAPI;

public class PetStore_Steps {
	
	 private Response response;


	@Given("Set the pet endpoint")
	public void set_the_pet_endpoint() {
	    RestAssured.baseURI="https://petstore.swagger.io/v2";
	    
	}

	@When("Send POST request with valid data")
	public void send_post_request_with_valid_data() {
	    String jsonPayload=FileUtils.readJsonFromFile("testData/createPet.json");
	    System.out.println("Payload is: " + jsonPayload);
	    
	    response=PetAPI.createPet(jsonPayload);
	    System.out.println(response.getBody().asPrettyString());
	    
	    Serenity.setSessionVariable("petId").to(response.then().extract().path("id"));	    		
	}
	
	@When("Send GET request to get pet details")
	public void send_get_request_to_get_pet_details() {
	    int petId=Serenity.sessionVariableCalled("petId");
	    response=PetAPI.getPetById(petId);
	    System.out.println(response.getBody().asPrettyString());
	}
	
	@When("Send DELETE request to delete pet details")
	public void send_delete_request_to_delete_pet_details() {
		int petId=Serenity.sessionVariableCalled("petId");
	    response=PetAPI.deletePetById(petId);
	}


	@Then("Status code should be {int}")
	public void status_code_should_be(int statusCode) {
		if (response == null) {
            throw new IllegalStateException("Response is null. Ensure the request step is called before this.");
        }	    
		response.then().statusCode(statusCode);
	    System.out.println("Expected Status Code: "+statusCode);
	}

	
	
	@When("Send GET request for non-exists petID {int}")
	public void send_get_request_for_non_exists_pet_id(int petIdNotExist) {
		response=PetAPI.getPetById(petIdNotExist);
		
	}
	
	@When("Send PUT request update pet details")
	public void send_put_request_update_pet_details() {
		String jsonPayload=FileUtils.readJsonFromFile("testData/updatePet.json");
	    System.out.println("Payload is: " + jsonPayload);
	    
	    response=PetAPI.updatePet(jsonPayload);
	    System.out.println(response.getBody().asPrettyString());
	}
	
	@When("Send POST request with invalid data")
	public void send_post_request_with_invalid_data() {
		String jsonPayload=FileUtils.readJsonFromFile("testData/createInvalidPet.json");
	    System.out.println("Payload is: " + jsonPayload);
	    
	    response=PetAPI.createPet(jsonPayload);
	    System.out.println(response.getBody().asPrettyString());
	}
	
	@When("Send GET request to retrieve pet inventory")
	public void send_get_request_to_retrieve_pet_inventory() {
	    response=PetStoreOrdersAPI.getPetInventory();
	    System.out.println(response.getBody().asPrettyString());
	}
	
	@When("Send invalid GET request to retrieve pet inventory")
	public void send_invalid_get_request_to_retrieve_pet_inventory() {
	    response=PetStoreOrdersAPI.getInvalidPetInventory();
	}
	
	@When("Send POST request to place an order")
	public void send_post_request_to_place_an_order() {
		String jsonPayload=FileUtils.readJsonFromFile("testData/PlaceOrder.json");
	    System.out.println("Payload is: " + jsonPayload);
	    
	   response=PetStoreOrdersAPI.placeOrder(jsonPayload);
	   System.out.println(response.getBody().asPrettyString());
	}
	
	@When("Send invalid POST request to place an order")
	public void send_invalid_post_request_to_place_an_order() {
		String jsonPayload=FileUtils.readJsonFromFile("testData/PlaceInvalidOrder.json");
	    System.out.println("Payload is: " + jsonPayload);
	    
	   response=PetStoreOrdersAPI.placeOrder(jsonPayload);
	   System.out.println(response.getBody().asPrettyString());
	}


}
