package STEPS;

import java.io.IOException;

import io.cucumber.java.Before;

public class HOOKS {

	@Before("@DeletePlace")
	public void beforeScenario() throws IOException
	{	//execute this code only when place id is null
		//write a code that will give you place id
		
		STEPS m =new STEPS();
		if(STEPS.place_id==null)
		{
		
		m.add_place_payload_with(50,"Frontline house",  "(+91) 983 893 3937",  "29, side ","French-IN", "http://google.com");
		m.user_calls_the_with_http_request("AddPlaceAPI", "POST");
		m.verify_the_place_id_for_get_request_using_the_using_the("Frontline house","French-IN","getPlaceAPI");
		}
		
	

	}
}
