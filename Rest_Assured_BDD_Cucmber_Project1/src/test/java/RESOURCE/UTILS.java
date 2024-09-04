package RESOURCE;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Properties;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class UTILS {

	
	public static RequestSpecification req;
	
	public RequestSpecification RequestSpecfication_method() throws IOException
	{
		
		if(req==null)
		{
		PrintStream log=new PrintStream(new FileOutputStream("logging.txt"));
		req=new RequestSpecBuilder()
				.setBaseUri(setGlobalValue("BaseUri"))
				.addQueryParam("key", "qaclick123")
				.setContentType(ContentType.JSON).addFilter(RequestLoggingFilter.logRequestTo(log))
				.build();
		return req;
	}
		return req;
	}	
	public static  String setGlobalValue(String Key_baseURL) throws IOException
	{
		Properties prop=new Properties();
		FileInputStream fis=new FileInputStream("D:\\ECLIPSE NEW VERSION\\Rest_Assured_BDD_Cucmber_Project1\\src\\test\\java\\RESOURCE\\global.properties");
		prop.load(fis);
		return  prop.getProperty(Key_baseURL);
	}
	public String getJsonPath(Response response,String key) throws FileNotFoundException
	{
		
		 String resp=response.asString();
		JsonPath   js = new JsonPath(resp);
		return js.get(key);
		
	}
}
