package auto;

import cucumber.api.CucumberOptions;
import net.serenitybdd.cucumber.CucumberWithSerenity;
import org.junit.runner.RunWith;


@RunWith(CucumberWithSerenity.class)
@CucumberOptions( 
		features={"src/test/resources/features/admin"}
		//,plugin = {"junit:target/JunitReport"}
		//,tags={"@View-History-And-Details, @Change_Addresses"}
		,tags={"@View-History-And-Details, @Change_Addresses"} 
		)
public class UserProfileManagementTestSuite {

}