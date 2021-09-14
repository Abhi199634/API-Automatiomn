package Utilities;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Properties;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class utils {
	
	static RequestSpecification req;
	JsonPath js;
	
	public io.restassured.specification.RequestSpecification RequestSpecification() throws IOException {
		
		if(req==null ) {
		
		PrintStream log = new PrintStream(new FileOutputStream("log.txt"));
		
		req= new RequestSpecBuilder().setBaseUri(getglobalvalue("baseurl")).addQueryParam("key", "qaclick123")
				.addFilter(RequestLoggingFilter.logRequestTo(log))
				.addFilter(ResponseLoggingFilter.logResponseTo(log))
				.setContentType(ContentType.JSON).build();
		
			return req;
		}
		
		return req;
	}
	

	public io.restassured.specification.ResponseSpecification ResponseSpecification() {
		
		ResponseSpecification resspec = new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON).build();
		
		return resspec;
		
	}
	
	public static  String  getglobalvalue(String key) throws IOException {
		
		Properties prop = new Properties();
		FileInputStream fis = new FileInputStream("global.properties");
		prop.load(fis);
		return (String) prop.get(key);
		
	}
	
	public String getJsonPath(Response response, String key) {
		
		String resp= response.asString();
		js = new JsonPath(resp);
		String name =js.get(key);
		return name;
		
	}

}
