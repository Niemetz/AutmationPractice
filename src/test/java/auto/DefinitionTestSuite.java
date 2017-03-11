package auto;

import cucumber.api.CucumberOptions;
import net.serenitybdd.cucumber.CucumberWithSerenity;
import org.junit.runner.RunWith;


@RunWith(CucumberWithSerenity.class)
@CucumberOptions( 
		features={"src/test/resources/features/UserLoginAuthentication"}
		//,tags={"@Login-As-Admin-User, @Verify-Elements-On-Account-Main"}
		,tags={"@Amazon-User-Login"}
		)
public class DefinitionTestSuite {

}
