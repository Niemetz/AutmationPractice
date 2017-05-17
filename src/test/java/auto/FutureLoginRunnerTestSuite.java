package auto;

import cucumber.api.CucumberOptions;
import net.serenitybdd.cucumber.CucumberWithSerenity;
import org.junit.runner.RunWith;


@RunWith(CucumberWithSerenity.class)
@CucumberOptions( 
		features={"src/test/resources/features/admin/user_management/user_Login_authentication"}
		//,tags={" @Creation-Of-Type-Analyst-1"}
		)
public class FutureLoginRunnerTestSuite {

}