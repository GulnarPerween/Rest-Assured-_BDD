package STEPS;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

import java.io.FileNotFoundException;
import java.io.IOException;

import RESOURCE.GETRESOURCE_API;
import RESOURCE.TESTDATA_INPUT_PAYLOAD;
import RESOURCE.UTILS;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class STEPS extends UTILS {

	Response response;
	RequestSpecification res;

	ResponseSpecification respec;
	static String place_id;
	
	TESTDATA_INPUT_PAYLOAD obj = new TESTDATA_INPUT_PAYLOAD();

	@Given("Add place payload with {int},{string},{string},{string},{string},{string}")
	public void add_place_payload_with(Integer accuracy, String name, String phone_number, String address,
			String language, String website) throws IOException {

		respec = new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON).build();

		res = given().spec(RequestSpecfication_method())
				.body(obj.addPlace_payload(accuracy, name, phone_number, address, language, website));

	}

	@When("user calls the {string} with {string} http request")
	public void user_calls_the_with_http_request(String resource, String method) 
	{
		GETRESOURCE_API helloAPI_resource = GETRESOURCE_API.valueOf(resource);

		System.out.println(helloAPI_resource.getResource());
		
		if (method.equalsIgnoreCase("POST"))
			response = res.when().post(helloAPI_resource.getResource());

		else if (method.equalsIgnoreCase("GET"))

			response = res.when().get(helloAPI_resource.getResource());

		else if (method.equalsIgnoreCase("DELETE"))

			response = res.when().delete(helloAPI_resource.getResource());

	}

	@Then("the API call got sucess with status code {int}")
	public void the_api_call_got_sucess_with_status_code(Integer int1) {
		assertEquals(response.getStatusCode(), 200);

	}

	@Then("{string} in reponse body is equal to {string}")
	public void in_reponse_body_is_equal_to(String Key, String Expectedvalue) throws FileNotFoundException 
	{
		
		assertEquals(getJsonPath(response,Key), Expectedvalue);
	}

	@Then("verify the PlaceID  for Get Request using the {string},{string} using the {string}")
	public void verify_the_place_id_for_get_request_using_the_using_the(String expected, String language, String resource) throws IOException 
	{

		place_id =getJsonPath(response,"place_id");
		System.out.println(place_id);
		res=given().spec(RequestSpecfication_method()).queryParam("place_id", place_id);
		//calling the below same method from "steps class"
		user_calls_the_with_http_request(resource,"GET");
		
		
		String actualName=getJsonPath(response,"name");
		  assertEquals(actualName,expected);
		  
		 String actualName_2=getJsonPath(response,"language");
		 assertEquals(actualName_2,language);
		  
	}
	@Given("DeletePlace Payload")
	public void delete_place_payload() throws IOException {
	
		res=given().spec(RequestSpecfication_method()).body(obj.DELETEPlaceMETHOD(place_id));
	}
	
	


}
