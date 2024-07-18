package stepDefinations;
import static io.restassured.RestAssured.given;
import java.util.List;
import java.io.IOException;
import java.util.ArrayList;
import pojo.AddPlace;
import pojo.Location;
import resources.APIResources;
import resources.TestDataBuild;
import resources.Utils;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import static org.junit.Assert.*;


//In a StepDefination Class is where we define all the steps from our feature file, as you can see, we have all the
//Gherkin annotations here with their code  that runs each method required to test our test case.

//We extend from Utils class in prder to avoid creating an object of it. This so we can use our RequestSpecification Method freely.
public class StepDefination extends Utils {
	RequestSpecification res;
	ResponseSpecification responseSpec;
	Response response;
	String stringResponse;
	TestDataBuild data = new TestDataBuild();
	static String place_id;
	
	
// Remember, it's very important that the text from the Gherkin annotation is exactly the same as the feature file 
// This because in case it's not exactly the same, the code below the annotation won't run and your test will fail.
	@Given("Add Place Payload with {string} {string} {string}")
	public void add_place_payload_with(String name, String language, String address) throws IOException {
	    // Write code here that turns the phrase above into concrete actions
		AddPlace addP = data.addPlacePayload(name, language, address);
		
		res = given().spec(requestSpecification()).body(addP);
	}
	
	
	
	@When("user calls {string} with {string} http request")
	public void user_calls_with_http_request(String resource, String httpMethod) {

		APIResources resourceAPI = APIResources.valueOf(resource);
		
		
		responseSpec = new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON).build();
		
		if(httpMethod.equalsIgnoreCase("POST")) {
			response = res.when().post(resourceAPI.getResource());	
		}else if(httpMethod.equalsIgnoreCase("DELETE")) {
			response = res.when().post(resourceAPI.getResource());	
		}else if (httpMethod.equalsIgnoreCase("GET")) {
			response = res.when().get(resourceAPI.getResource());		
		}
		
		System.out.println(resourceAPI.getResource());
	}
	
	@Then("the API call is successfull with status code {int}")
	public void the_api_call_is_successfull_with_status_code(int httpStatusCode) {

	    assertEquals(httpStatusCode,response.getStatusCode());
	}
	
	@Then("{string} in response body is {string}")
	public void in_response_body_is(String keyValue, String expectedKeyValue) {

		
		assertEquals(expectedKeyValue,getJsonPath(response, keyValue).toString());
		
	}
	

	@Then("verify place_Id created maps to {string} using {string}")
	public void verify_place_id_created_maps_to_using(String name, String resource) throws IOException {
		
		// Prepare Request Specification.
		
		place_id = getJsonPath(response, "place_id");
		res = given().spec(requestSpecification()).queryParam("place_id",place_id);
		user_calls_with_http_request(resource, "GET");
		String newName = getJsonPath(response,"name");
		assertEquals(newName, name);
	}


	@Given("DeletePlace Payload")
	public void delete_place_payload() throws IOException {
		res = given().spec(requestSpecification()).body(data.deletePlacePayload(place_id));
	    
	}









	
}
