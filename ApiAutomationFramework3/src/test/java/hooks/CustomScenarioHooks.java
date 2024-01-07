package hooks;

import io.cucumber.java.Before;
import io.cucumber.java.After;

public class CustomScenarioHooks {

	//To set up the pre-requisites
	@Before
	public void beforeScenario() {

		//System.out.println("Inside beforeScenario..");
		
	}

	@After
	public void afterScenario() {

		//System.out.println("Inside afterScenario..");
	}

}
