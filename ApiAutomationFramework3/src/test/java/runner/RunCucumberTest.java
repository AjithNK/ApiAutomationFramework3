package runner;

import org.testng.annotations.DataProvider;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
		tags="@tag1", 
		features={"src\\test\\resources"},
		glue = {"hooks"},
		plugin= {"pretty","html:target/cucumber-reports.html"},
		monochrome = true
		 
		   
		)


public class RunCucumberTest extends AbstractTestNGCucumberTests {

	    @DataProvider(parallel = true)
	    public Object[][] scenarios() {
	        return super.scenarios();
	    }


}
