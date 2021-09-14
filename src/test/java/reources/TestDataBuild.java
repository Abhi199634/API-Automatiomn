package reources;

import java.util.ArrayList;

import POJO.Addplace;
import POJO.location;

public class TestDataBuild {
	
	
	public Addplace addplacePayload(String name, String langauge, String Address, String data) {
		
		Addplace p = new Addplace();
		p.setAccuracy(50);
		p.setLanguage(langauge);
		p.setPhone_number("9929483903");
		p.setAddress(Address);
		p.setName(name);
		p.setWebsite("https://rahulshettyacademy.com");
		String testdata = data;
		String [] my_array = testdata.split(",");
		ArrayList<String> ar = new ArrayList<>();
		for (String str : my_array) {
			ar.add(str);
		}
		p.setTypes(ar);
		location l = new location();
		l.setLat(67.90);	
		l.setLng(56.89);
		p.setLocation(l);
		
		return p;
	}
	
	public String  deleteplacePayload(String place_id) {
		
		return "{\r\n\"place_id\": \""+place_id+"\"\r\n}";
	
	}
}	
