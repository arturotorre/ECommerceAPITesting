package resources;

import java.util.ArrayList;
import java.util.List;
import pojo.AddPlace;
import pojo.Location;

public class TestDataBuild {
	
	//In a TestDataBuild file we include the code that helps us to build or JSON Payload.
	
	public AddPlace addPlacePayload(String name, String language, String address) {
		AddPlace addP = new AddPlace();
		Location newL = new Location();
		
		List<String> typesList = new ArrayList<String>();
		typesList.add("shoe park");
		typesList.add("shop");
		
		addP.setAccuracy(50); 
		addP.setAddress(address);
		addP.setLanguage(language);
		addP.setPhoneNumber("81-27-31-35-73");
		addP.setWebsite("www.google.com");
		addP.setName(name);
		addP.setTypes(typesList);
		
		
		newL.setLat(-38.383494);
		newL.setLng(33.427362);
		
		addP.setLocation(newL);	
		
		return addP;
	}
	
	public String deletePlacePayload(String placeId) {
		
		return "{\r\n\"place_id\":\""+ placeId +"\"\r\n}";
		
	}

}
