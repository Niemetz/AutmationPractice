package auto.steps.serenity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.junit.Assert;
import auto.pages.AccountMainPage;
import auto.pages.AmazonBasePageObject;
import auto.pages.HomePage;
import auto.pages.LoginPage;
import auto.pages.YourAccountPage;
import auto.util.InputEntry;
import cucumber.api.DataTable;
import net.serenitybdd.core.pages.WebElementFacade;
import net.thucydides.core.annotations.Step;
import net.thucydides.core.steps.ScenarioSteps;

@SuppressWarnings("serial")
public class EndUserSteps extends ScenarioSteps {

	String pageName;
	String elementName;
	String sectionName;
	
	WebElementFacade targetElement;
	AmazonBasePageObject currentPage;
	
	// Test 4 - check in the new code to the Development Branch
	List<InputEntry> inputList = new ArrayList<InputEntry>();

	// This table is used to store all the instance variables for pages under test
	private static final Map<String, AmazonBasePageObject> allPagesUnderTest = new HashMap<>();

	public EndUserSteps() {
		super() ;
		this.pageName = null;
		this.elementName = null;
		this.targetElement = null;
		this.currentPage = null;
		this.sectionName = null;
	}

	public AmazonBasePageObject getCurrentPage(String gherkinPageName) 
	{
		
		// if the desired page was not registered in the allPageUnderTest, 
		// then add the desired page to the allPageUnderTest.
		this.pageName = gherkinPageName.toLowerCase();
		if (!allPagesUnderTest.containsKey(pageName)) 
		{
			switch (pageName) 
			{
			  case "home": allPagesUnderTest.put(new String(pageName), getPages().get(HomePage.class));break;	         
			  case "login": allPagesUnderTest.put(new String(pageName), getPages().get(LoginPage.class));break;
			  case "account main": allPagesUnderTest.put(new String(pageName), getPages().get(AccountMainPage.class));break;
			  case "your account": allPagesUnderTest.put(new String(pageName), getPages().get(YourAccountPage.class));break;
			  default:
				      System.out.println(String.format("ERROR... Page %s NOT FOUND!!!", gherkinPageName));
				      return null;
			}
		}
		// return the desired page to the caller
		return allPagesUnderTest.get(pageName);
	}

	@Step
	public void navigates_to_page(String gherkinPageName) throws Throwable {
		currentPage = getCurrentPage(gherkinPageName);
		currentPage.open();
	}

	public WebElementFacade getElement(String gherkinElement) {
		return allPagesUnderTest.get(pageName).getElement(gherkinElement);
	}

	public void clicks_on_elementX(String gherkinElement) throws Throwable {
		allPagesUnderTest.get(pageName).getElement(gherkinElement).waitUntilVisible().and().waitUntilClickable().click();
		
	}

	public void lands_on_pageX(String gherkinPageName) throws Throwable {

		this.pageName = gherkinPageName.toLowerCase();

		currentPage = getCurrentPage(pageName);

		if (pageName.equalsIgnoreCase("home")) {
			
			sectionName = "ISA Markings for Indicator";
			InputEntry inputEntry1 = new InputEntry(pageName, sectionName, "Released To Public", "Yes");
			inputList.add(inputEntry1);
			
			InputEntry inputEntry2 = new InputEntry(pageName, sectionName, "Display", "Yes");
			inputList.add(inputEntry2);
			
			InputEntry inputEntry3 = new InputEntry(pageName, sectionName, "Intelligence Analysis", "No");
			inputList.add(inputEntry3);
	
			System.out.println("============================================");
			System.out.println(String.format("I am on the " + "\"" + "%s" + "\"" + " page, and at the "+ "\"" + "%s"  + "\"" +" section , I set: ", pageName, sectionName));

			int i = 0;
			for (InputEntry inputEntryIndex : inputList) {
				System.out.println(++i +") " + inputEntryIndex.getElementName() + " = " + inputEntryIndex.getInputValue());
            }

		}
		if (pageName.toLowerCase().equalsIgnoreCase("login")) {

			sectionName = "ISA Markings for Title";
			InputEntry inputEntry1 = new InputEntry(pageName, sectionName, "Released To Public", "No");
			inputList.add(inputEntry1);
			
			InputEntry inputEntry2 = new InputEntry(pageName, sectionName, "Display", "No");
			inputList.add(inputEntry2);
			
			InputEntry inputEntry3 = new InputEntry(pageName, sectionName, "Intelligence Analysis", "Yes");
			inputList.add(inputEntry3);
	
			System.out.println("============================================");
			System.out.println(String.format("I am on the " + "\"" + "%s" + "\"" + " page, and at the "+ "\"" + "%s"  + "\"" +" section , I set: ", pageName, sectionName));
			int j=0;
			for (int i = 3; i < inputList.size(); i++) {
				System.out.println(++j +") " + inputList.get(i).getElementName() + " = " + inputList.get(i).getInputValue());
            }
			
			System.out.println("============================================");
			System.out.println("contents of the input list...");
			for (int i = 0; i < inputList.size(); i++) {
				System.out.println(i +") Section: "+ inputList.get(i).getSectionName() + " => " + inputList.get(i).getElementName() + " = " + inputList.get(i).getInputValue());

		    }
		}
	}

	public void enters_inputX_into_the_elementY_input_field(String inputValue, String gherkinElement) throws Throwable {

		getElement(gherkinElement).waitUntilVisible().and().waitUntilEnabled().sendKeys(inputValue);
		
		this.sectionName = "Main Login";
		
		InputEntry inputEntry = new InputEntry(pageName, sectionName, gherkinElement, inputValue);
		inputList.add(inputEntry);

	}

	@Step
	public void verifies_that_all_input_were_conrrectly_captured_saved_and_dislayed() throws Throwable {

		compare_actual_input_value_to_dislayed_value(this.pageName, this.sectionName);
	}

	@Step
	public void on_the_page_and_at_the_section_user_verifies_that_all_input_data_were_conrrectly_captured_saved_and_dislayed(
			String gherkinPageName, String gherkinSectionName) throws Throwable {
		
		System.out.println("I'm at the " + gherkinPageName + " page and at the " + gherkinSectionName + " section");
		
		compare_actual_input_value_to_dislayed_value(gherkinPageName, gherkinSectionName);
	}

	@Step
	public void compare_actual_input_value_to_dislayed_value(String gherkinPageName, String gherkinSectionName) {

		String storedEmailAddress = inputList.get(6).getInputValue();
		String EmailAddressOnPage = getElement("Email").waitUntilVisible().and().waitUntilClickable().getTextValue();

		
		System.out.println("I compare the stored Email input     = " + storedEmailAddress);
		System.out.println("to the actual value of Email on page = " + EmailAddressOnPage);

		try {
			Assert.assertEquals(EmailAddressOnPage, storedEmailAddress);
			
			System.out.println("============================================");
			System.out.println("Result of comparison...");
			System.out.println("*** PASSED:");
			System.out.println("Actual Value On Page  => " + EmailAddressOnPage);
			System.out.println("Stored Value In Table => " + storedEmailAddress);

		} catch (Exception e) {
			value_not_match(EmailAddressOnPage);
		}
	}

	@Step("Field Value Verification Error......")
	public void value_not_match(String displayedValue) {
		System.err.println("**** FAILED:   Element " + "\"" + displayedValue + "\"" + " is NOT MATCHED ...");
	}

	//@Step("I am at the EndUserSteps class")
	//@Step
	public void verifyThatAllExpectedElementsAreDisplayedOnPage() {
		allPagesUnderTest.get(pageName).verifyThatAllExpectedElementsAreDisplayedOnPage(this.pageName);
	    //currentPage.verifyThatAllExpectedElementsAreDisplayedOnPage(this.pageName);
	}
	
	public void fills_out_the_form_wiht_the_followng_data(DataTable recordRow) throws Throwable {
		
		List<List<String>> table = recordRow.raw();

		System.out.println("row size =" + table.size());
		for (int i = 2; i < table.size(); i++) {
			System.out.println(i + "-  Action = " + table.get(i).get(0) + " | " + "input = "+ table.get(i).get(1) + " | " + "Target Element = "+ table.get(i).get(2)) ;
		}
	}

	public void user_is_on_the_page(String gherkinPage) throws Throwable {

	}
}
