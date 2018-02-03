package auto;

import cucumber.api.CucumberOptions;
import net.serenitybdd.cucumber.CucumberWithSerenity;
import org.junit.runner.RunWith;


@RunWith(CucumberWithSerenity.class)
@CucumberOptions( 
		features={"src/test/resources/features/admin"}
		//,tags={"@View-History-And-Details or @Address-Change" } 
		,tags={ "@View-History-And-Details, @Address-Change" } 
		)
public class UserProfileManagementTestSuite {

}