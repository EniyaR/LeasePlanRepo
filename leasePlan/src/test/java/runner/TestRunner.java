package runner;

import org.junit.runner.RunWith;

import io.cucumber.junit.CucumberOptions;
import net.serenitybdd.cucumber.CucumberWithSerenity;

@RunWith (CucumberWithSerenity.class)
@CucumberOptions (
		features="src/test/java/features/PetStoreOrders.feature",
		glue="stepDefinitions", 
		//dryRun=true,
		plugin= {"pretty"},
		monochrome=true
		)

public class TestRunner {

}
