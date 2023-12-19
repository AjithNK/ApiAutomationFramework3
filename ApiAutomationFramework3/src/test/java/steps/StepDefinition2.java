package steps;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class StepDefinition2 {

/*	 @Given("User is in the login page of the application")
	    public void integer_no(Integer int1) throws IOException {
	        
	       System.out.println(" this is from given");
	       
	       
	       	WebDriver driver;
			Properties prObj;
			prObj = new Properties();
			FileInputStream file = new FileInputStream(
					System.getProperty("user.dir") + "\\src\\main\\resources\\propertyFile\\configuration.properties");
			prObj.load(file);
		
			if(prObj.getProperty("BROWSER").equalsIgnoreCase("chrome")) {
				driver = new ChromeDriver();
				driver.manage().window().maximize();
				driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
				driver.get(prObj.getProperty("URL"));
				
	    }
	 }
			
	    @When("User enters valid username, password and clicks submit button")
	    public void add_with(String string, String string2) {
	        // Write code here that turns the phrase above into concrete actions
	        System.out.println(" this is from when");
	    }
	    @Then("User should be logged in and taken to homepage")
	    public void result_is(String string) {
	        // Write code here that turns the phrase above into concrete actions
	        System.out.println(" this is from then");
	    }  */
}
