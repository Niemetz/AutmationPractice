package auto;

import cucumber.api.CucumberOptions;
import net.serenitybdd.cucumber.CucumberWithSerenity;
import net.thucydides.core.annotations.Managed;
import net.thucydides.core.webdriver.WebdriverContext;

import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;

@RunWith(CucumberWithSerenity.class)
@CucumberOptions( 
		features={"src/test/resources/features"}
		//,tags={"@Login-As-Admin-User, @Verify-Elements-On-Account-Main"}
		,tags={"@Amazon-User-Login"}
		)
public class DefinitionTestSuite {

}
