package hooks;

import io.cucumber.java.Before;
import io.cucumber.java.After;

public class CustomScenarioHooks {
	
	@Before
	public void beforeScenario() {
		
		//To set up the pre-requisites
		System.out.println("Inside beforeScenario..");
	}

	
	@After
	public void afterScenario() {
		
		
		System.out.println("Inside afterScenario..");
	}
	
}
