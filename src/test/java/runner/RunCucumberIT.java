package runner;

import io.cucumber.junit.CucumberOptions;
import net.serenitybdd.cucumber.CucumberWithSerenity;
import org.junit.runner.RunWith;


@RunWith(CucumberWithSerenity.class)
@CucumberOptions(
		features = {"classpath:features/"},
		glue = {"stepdefinitions"},
		tags = "@CSV01",
		//tags = "@TC204",
		plugin =
			{
					"pretty",
					"html:target/cucumber-reports",
					"json:target/cucumber.json",
					"junit:target/cucumber.xml",
			},
		dryRun=false,
		monochrome = true
		)
public class RunCucumberIT {

}


