package Cucumber.Options;



import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;



@RunWith(Cucumber.class)
@CucumberOptions(features="src/test/java/Features/place.feature",plugin="json:target/jsonReports/cuccumber-reports.json", glue= {"stepDefinitions"}, tags="@DeletePlace")
public class TestRunner {
	
	
}
