package stepDefinations;

import java.io.IOException;

import io.cucumber.java.Before;

public class Hooks {
	
	@Before ("@DeletePlace")
	public void beforeScenario() throws IOException {
		
		StepDefination stepDefination = new StepDefination();
		if (StepDefination.place_id == null ) {
			
			stepDefination.add_place_payload_with("Channel", "Bark", "Garcia");
			stepDefination.user_calls_with_http_request("addPlaceAPI", "Post");
			stepDefination.verify_place_id_created_maps_to_using("Channel", "getPlaceAPI");
			
		}
		
	}

}
