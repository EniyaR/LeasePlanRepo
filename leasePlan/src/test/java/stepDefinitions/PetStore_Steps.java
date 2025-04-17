package stepDefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import net.serenitybdd.core.Serenity;
import utils.FileUtils;



import api_POM.PetAPI;

public class PetStore_Steps {
	
	Response response;

	
	
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
	}
	
	@When("Send DELETE request to delete pet details")
	public void send_delete_request_to_delete_pet_details() {
		int petId=Serenity.sessionVariableCalled("petId");
	    response=PetAPI.deletePetById(petId);
	}


	@Then("Status code should be {int}")
	public void status_code_should_be(int statusCode) {
	    response.then().statusCode(statusCode);
	    System.out.println("Expected Status Code: "+statusCode);
	}

	
	
	@When("Send GET request for non-exists petID {int}")
	public void send_get_request_for_non_exists_pet_id(int petIdNotExist) {
		response=PetAPI.getPetById(petIdNotExist);
		
	}


}
