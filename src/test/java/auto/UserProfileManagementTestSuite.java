package auto;

import cucumber.api.CucumberOptions;
import net.serenitybdd.cucumber.CucumberWithSerenity;
import org.junit.runner.RunWith;

@RunWith(CucumberWithSerenity.class)
@CucumberOptions( 
		features={"src/test/resources/features/admin/user_profile_management/view_order_history_and_details"}
		//,tags={" @Creation-Of-Type-Analyst-1"}
		)

public class UserProfileManagementTestSuite {

}