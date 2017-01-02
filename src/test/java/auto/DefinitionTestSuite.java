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
		//,glue={"src.test.java.auto"}
		,tags={"@Login-Amazon"}
		)
public class DefinitionTestSuite {

}
