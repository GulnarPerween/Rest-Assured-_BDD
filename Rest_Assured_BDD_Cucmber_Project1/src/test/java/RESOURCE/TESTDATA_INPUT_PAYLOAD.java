package RESOURCE;

import java.util.ArrayList;
import java.util.List;

import POJO_CLASS.AddPlace;
import POJO_CLASS.location;

public class TESTDATA_INPUT_PAYLOAD {

	public AddPlace addPlace_payload(Integer accuracy, String name, String phone_number, String address,String language,String website)
	{
		AddPlace p=new AddPlace();
		p.setAccuracy(accuracy);
		p.setAddress(address);
		p.setLanguage(language);
		p.setName(name);
		p.setPhone_number(phone_number);
		p.setWebsite(website);
		
		location loc=new location();
		loc.setLat("-38.383494");
		loc.setLng("33.427362");
		p.setLocation(loc);
		
		
		List<String> mylist=new ArrayList<String>();
		mylist.add("shoe parkssss");
		mylist.add("shop");
		
		p.setTypes(mylist);
		
		return p;
	}
	
	public String DELETEPlaceMETHOD(String  place_Id)
	{
		return "{\r\n"
				+ "    \"place_id\":\""+place_Id+"\"\r\n"
				+ "}\r\n"
				+ "";
	}
}
