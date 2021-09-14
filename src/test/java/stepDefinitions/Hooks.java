package stepDefinitions;

import java.io.IOException;

import io.cucumber.java.Before;

public class Hooks {
	
	StepDefinition sd = new StepDefinition();
	@Before("@DeletePlace")
	public void beforeSceanrio() throws IOException {
		
		if(StepDefinition.place_id!=null) {
		
		//write a code to place only 	when place id null;
			sd.add_place_payload_with("shetty","french", "Asia", "[shoe,park]");
			sd.user_calls_with_post_http_request("AddPlaceAPI", "post");
			sd.verify_place_id_created_maps_to_using("shetty", "AddPlaceAPI");	
	}
	}	

}