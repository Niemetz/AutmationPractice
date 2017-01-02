package auto.steps;

//import net.thucydides.core.annotations.Step;
import net.thucydides.core.annotations.Steps;
import cucumber.api.DataTable;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import java.util.List;

import auto.steps.serenity.EndUserSteps;

public class DefinitionSteps {

    @SuppressWarnings("rawtypes")
	@Steps
	EndUserSteps user;
    
   // @Step
    @Given("^user navigates to Amazon \"([^\"]*)\" website$")
    public void user_navigates_to_Amazon_website(String pageName) throws Throwable {
                user.navigates_to_page(pageName);

    }
    
   // @Step
    @When("^he clicks on the \"([^\"]*)\" (?:button|radio button|check box|.*?)$")
    public void user_clicks_on_elementX(String gherkinElement) throws Throwable {
                user.clicks_on_elementX(gherkinElement);
    }
    
   // @Step
    @Then("^he lands on the \"([^\"]*)\" page$")
    public void user_lands_on_pageX(String pageName) throws Throwable {
                user.lands_on_pageX(pageName);
    }


   @Then("^he verifies that all expected elements are displayed on the page$")
   public void user_verifies_that_all_elements_are_on_the_page() throws Throwable
   {
               user.verifies_that_all_elements_are_on_the_page();
   }
   
   @When("^he enters \"([^\"]*)\" into the \"([^\"]*)\" input field$")
   public void user_enters_inputX_into_the_elementY_input_field(String inputValue, String gherkinElement) throws Throwable {
               user.enters_inputX_into_the_elementY_input_field(inputValue, gherkinElement);
   }
   
    @Given("^he verifies that, on the \"([^\"]*)\" table, the following record exists:$")
    public void he_verifies_that_on_the_tabel_this_record_exist(String tableName, DataTable recordRow) throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        // For automatic transformation, change DataTable to one of
        // List<YourType>, List<List<E>>, List<Map<K,V>> or Map<K,V>.
        // E,K,V must be a scalar (String, Integer, Date, enum etc)
        
    	List<List<String>>  wholeRow = recordRow.raw();

    	System.out.println("Table Name =" + tableName);
    	System.out.println("row size =" + wholeRow.size());
    	for (int i = 1; i < wholeRow.size(); i++)
    	{
    	   System.out.println(wholeRow.get(i).get(0) + " = " + wholeRow.get(i).get(1));
    	}
    }


}

