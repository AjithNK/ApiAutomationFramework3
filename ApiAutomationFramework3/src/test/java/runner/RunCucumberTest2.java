package runner;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.testng.annotations.AfterSuite;
import org.testng.annotations.DataProvider;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import net.masterthought.cucumber.Configuration;
import net.masterthought.cucumber.ReportBuilder;
import net.masterthought.cucumber.presentation.PresentationMode;
import net.masterthought.cucumber.sorting.SortingMethod;

@CucumberOptions(
		features={"src\\test\\resources\\testresources"},
		glue = {"steps"},
		plugin= {"pretty","html:target/cucumber-reports1.html",
		"json:target/cucumber1.json"}
		
		 
		   
		)


public class RunCucumberTest2 extends AbstractTestNGCucumberTests {

	    @DataProvider(parallel = true)
	    public Object[][] scenarios() {
	        return super.scenarios();
	    }

	    @AfterSuite
	    public void afterSuite(){
	        generateReport();
	    }   
	    
	    private void generateReport() {
	        Configuration configuration = new Configuration(new File("target"), "Suite name");
	        configuration.setSortingMethod(SortingMethod.NATURAL);
	        configuration.addPresentationModes(PresentationMode.EXPAND_ALL_STEPS);
	        configuration.setBuildNumber("1");
	        List<String> jsonFiles = new ArrayList<>();
	        jsonFiles.add("target/cucumber1.json");
	        ReportBuilder reportBuilder = new ReportBuilder(jsonFiles, configuration);
	        reportBuilder.generateReports();

	    }

}
