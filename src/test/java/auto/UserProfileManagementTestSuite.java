package auto;

import cucumber.api.CucumberOptions;
import net.serenitybdd.cucumber.CucumberWithSerenity;
import org.junit.runner.RunWith;


@RunWith(CucumberWithSerenity.class)
@CucumberOptions( 
		features={"src/test/resources/features/admin/user_management/user_Login_authentication"}
		,plugin = {"junit:target/JunitReport"}
		,tags={"@History-And-Details"} 
		)
public class UserProfileManagementTestSuite {

}