package auto.steps;

import java.util.List;
import auto.steps.serenity.EndUserSteps;
import cucumber.api.DataTable;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import net.thucydides.core.annotations.Step;
import net.thucydides.core.annotations.Steps;

public class DefinitionSteps {

	@Steps
	EndUserSteps user;
	
	@Given("^user navigates to Amazon \"([^\"]*)\" website$")
	public void user_navigates_to_Amazon_website(String gherkinPageName) throws Throwable {
		user.navigates_to_page(gherkinPageName.toLowerCase());
	}

	// @Step
	@When("^user clicks on the \"([^\"]*)\" (?:button|radio button|check box)$")
	public void user_clicks_on_elementX(String gherkinElement) throws Throwable {
		user.clicks_on_elementX(gherkinElement);
	}

	// @Step
	@Then("^user lands on the \"([^\"]*)\" page$")
	public void user_lands_on_pageX(String gherkinPageName) throws Throwable {
		user.lands_on_pageX(gherkinPageName.toLowerCase());
	}

	@When("^user enters \"([^\"]*)\" into the \"([^\"]*)\" input field$")
	public void user_enters_inputX_into_the_elementY_input_field(String inputValue, String gherkinElement)
			throws Throwable {
		user.enters_inputX_into_the_elementY_input_field(inputValue, gherkinElement);
	}
	
	@Then("^user verifies that all expected elelments are displayed on the page$")
	public void user_verifies_that_all_expected_elelments_are_displayed_on_the_page() throws Throwable 
	{
		user.verifyThatAllExpectedElementsAreDisplayedOnPage();
	}
	@When("^user moves the cursor over the \"([^\"]*)\" button$")
	public void moves_the_cursor_over_the_X_Element(String accountAndLists) throws Throwable {
		user.moves_the_cursor_over_the_X_Element(accountAndLists);
	}
	@When("^user clicks on the \"([^\"]*)\" link from the \"([^\"]*)\" menu$")
	public void user_clicks_on_the_link_from_the_menu(String gherkinElement, String gherkinMenu) throws Throwable {
        user.clicks_on_the_link_from_the_menu(gherkinElement, gherkinMenu);
	}
	
	@Then("^user verifies that the value of the \"([^\"]*)\" field is \"([^\"]*)\"$")
	public void user_verifies_that_the_value_of_the_field_is(String gherkinElement, String gherkinValue) throws Throwable {
                user.verifies_that_the_value_of_the_field_is(gherkinElement, gherkinValue);
	}
	@Then("^user logs out$")
	public void user_logs_out() throws Exception {
           user.user_logs_out();
	}

}