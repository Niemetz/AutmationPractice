package auto.steps.serenity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

//import org.apache.log4j.Logger;
import org.junit.Assert;

//import java.util.Map.Entry;
import auto.pages.AccountMainPage;
import auto.pages.AmazonBasePageObject;
import auto.pages.HomePage;
import auto.pages.LoginPage;
import auto.pages.YourAccountPage;
import auto.util.MainSection;
import net.serenitybdd.core.pages.WebElementFacade;
import net.thucydides.core.annotations.Step;
import net.thucydides.core.steps.ScenarioSteps;

@SuppressWarnings("serial")
public class EndUserSteps extends ScenarioSteps {

	String pageName;
	String elementName;
	WebElementFacade targetElement;
	AmazonBasePageObject currentPage;
	String sectionName;

	List<Map<String, String>> masterList = new ArrayList<Map<String, String>>();
	LinkedList<Map<String, String>> ll = new LinkedList<Map<String, String>>();

	// This table is used to store the contents of a page which is extracted
	// from the master table
	public Map<String, Map<String, String>> retrievedPage = new HashMap<>();

	// This table is used to store all the instance variables for pages under
	// test
	public Map<String, AmazonBasePageObject> allPagesUnderTest = new HashMap<>();

	// This table is used to store all the WebElementFacade under test
	public Map<String, WebElementFacade> allWebElementsUnderTest = new HashMap<>();

	public EndUserSteps() {
		this.pageName = null;
		this.elementName = null;
		this.retrievedPage = null;
		this.targetElement = null;
		this.currentPage = null;
		this.sectionName = null;

	}

	public AmazonBasePageObject getCurrentPage(String gherkinPageName) {
		this.pageName = gherkinPageName.toLowerCase();
		// if the requested page is new to the list of all page under test, then
		// go find the page ...
		if (allPagesUnderTest.containsKey(pageName) == false) {
			switch (pageName) {
			case "home":
				currentPage = getPages().getPage(HomePage.class);
				break;
			case "login":
				currentPage = getPages().getPage(LoginPage.class);
				break;
			case "account main":
				currentPage = getPages().getPage(AccountMainPage.class);
				break;
			case "your account":
				currentPage = getPages().getPage(YourAccountPage.class);
				break;
			default:
				System.out.println(String.format("ERROR... Page %s NOT FOUND!!!", gherkinPageName));
			}
			// ... then add the new page to the list of all pages under test
			allPagesUnderTest.put(new String(pageName), currentPage);
		}

		// return the found page to the caller
		return currentPage = allPagesUnderTest.get(pageName);
	}

	@Step
	public void navigates_to_page(String gherkinPageName) throws Throwable {
		// clear the contents of the tableOfAllPagesUnderTest in the Super Class
		// AmazonBasePageObject.masterTable.clear();
		this.pageName = gherkinPageName.toLowerCase();
		currentPage = getCurrentPage(pageName);
		currentPage.open();

	}

	public WebElementFacade targetElement(String gherkinElement) {

		// this method serves two purposes:
		// 1 - returns the target WebElementFacade
		// 2 - registers the WebElementFacade to the webElementFacadeTable for
		// later use.
		this.elementName = gherkinElement.toLowerCase();

		if (allWebElementsUnderTest.containsKey(elementName) == false
				&& !elementName.equalsIgnoreCase("Page Unique Element"))
			allWebElementsUnderTest.put(elementName, currentPage.getElement(elementName));
		if (elementName.equalsIgnoreCase("Page Unique Element"))
			return currentPage.getElement(elementName);
		else
			return allWebElementsUnderTest.get(elementName);
	}

	// // THIS IS WHERE THE GETELEMENT METHODS ARE DEFINED
	// public WebElementFacade getElement(String gherkinElement) {
	// this.elementName = gherkinElement.toLowerCase();
	// return currentPage.getElement(gherkinElement);
	// }

	public void clicks_on_elementX(String gherkinElement) throws Throwable {
		targetElement(gherkinElement).waitUntilVisible().and().waitUntilClickable().click();
	}

	public void lands_on_pageX(String gherkinPageName) throws Throwable {

		this.pageName = gherkinPageName.toLowerCase();

		// If the new page, then register it to the allPagesUnderTest
		if (allPagesUnderTest.containsKey(pageName) == false)
			allPagesUnderTest.put(pageName, getCurrentPage(pageName));

		// get the current page from the allPagesUnderTest
		this.currentPage = allPagesUnderTest.get(pageName);

		currentPage = getCurrentPage(pageName);

		if (pageName.equalsIgnoreCase("home")) {
			MainSection mainSection = new MainSection();
			sectionName = "ISA Markings for Indicator";
			mainSection.getMainSection().put(new String("Page Name"), pageName);
			mainSection.getMainSection().put(new String("Section"), sectionName);
			mainSection.getMainSection().put(new String("Released To Public"), "Yes");
			mainSection.getMainSection().put(new String("Display"), "Yes");
			mainSection.getMainSection().put(new String("Intelligence Analysis"), "No");
			masterList.add(mainSection.getMainSection());
			System.out.println("============================================");
			// System.out.println(masterList.get(0).containsValue(sectionName));
			System.out.println("I am on the " + "\"" + masterList.get(0).get("Page Name") + "\". I set:");
			System.out.println("Released To Public    = " + masterList.get(0).get("Released To Public"));
			System.out.println("Display               = " + masterList.get(0).get("Display"));
			System.out.println("Intelligence Analysis = " + masterList.get(0).get("Intelligence Analysis"));
		}
		if (pageName.toLowerCase().equalsIgnoreCase("login")) {

			MainSection mainSection = new MainSection();
			sectionName = "ISA Markings for Type Field";
			mainSection.getMainSection().put(new String("Page Name"), pageName);
			mainSection.getMainSection().put(new String("Section"), sectionName);
			mainSection.getMainSection().put(new String("Released To Public"), "No");
			mainSection.getMainSection().put(new String("Display"), "No");
			mainSection.getMainSection().put(new String("Intelligence Analysis"), "Yes");
			masterList.add(mainSection.getMainSection());
			System.out.println("============================================");
			// System.out.println(masterList.get(1).containsValue("ISA Markings
			// for Type Field"));
			System.out.println("I am on the " + "\"" + masterList.get(1).get("Page Name") + "\". I set:");
			System.out.println("Released To Public    = " + masterList.get(1).get("Released To Public"));
			System.out.println("Display               = " + masterList.get(1).get("Display"));
			System.out.println("Intelligence Analysis = " + masterList.get(1).get("Intelligence Analysis"));
		}
	}

	public void enters_inputX_into_the_elementY_input_field(String inputValue, String gherkinElement) throws Throwable {
		// Enter the input into the input field
		targetElement(gherkinElement).waitUntilVisible().and().waitUntilEnabled().sendKeys(inputValue);

		// insert the elementName and the inputValue into the MasterPageTable
		// currentPage.insertIntoMasterTable(pageName, sectionName,
		// gherkinElement, inputValue);
		System.out.println("============================================");
		MainSection mainSection = new MainSection();
		this.sectionName = "Main Login";
		mainSection.getMainSection().put(new String("Page Name"), this.pageName);
		mainSection.getMainSection().put(new String("Section"), this.sectionName);
		mainSection.getMainSection().put(new String(gherkinElement), inputValue);
		masterList.add(mainSection.getMainSection());
	}

	@Step
	public void verifies_that_all_input_were_conrrectly_captured_saved_and_dislayed() throws Throwable {
		/*
		 * Write the loop here to go thru all elements on the page Look up the
		 * list for these two conditions: 1 - page name, 2 - the section name
		 * Then get the element's displayed value and compare it to the actual
		 * input value That is: masterList.get(2).get("Page Name") =
		 * gherkinPageName And masterList.get(2).get("Section") =
		 * gherkinSectionName When calling the method below, just simply pass
		 * the argument above and the target element whose value is to be
		 * compared example: compare_actual_input_value_to_dislayed_value
		 * (gherkinPageName, gherkinSectionName, gherkinElement)
		 */
		compare_actual_input_value_to_dislayed_value(this.pageName, this.sectionName);
	}

	@Step
	public void on_the_page_and_at_the_section_user_verifies_that_all_input_data_were_conrrectly_captured_saved_and_dislayed(
			String gherkinPageName, String gherkinSectionName) throws Throwable {
		/*
		 * Write the loop here to go thru all elements on the page Look up the
		 * list for these two conditions: 1 - page name, 2 - the section name
		 * Then get the element's displayed value and compare it to the actual
		 * input value That is: masterList.get(2).get("Page Name") =
		 * gherkinPageName And masterList.get(2).get("Section") =
		 * gherkinSectionName When calling the method below, just simply pass
		 * the argument above and the target element whose value is to be
		 * compared example: compare_actual_input_value_to_dislayed_value
		 * (gherkinPageName, gherkinSectionName, gherkinElement)
		 */
		compare_actual_input_value_to_dislayed_value(gherkinPageName, gherkinSectionName);
	}

	@Step
	public void compare_actual_input_value_to_dislayed_value(String gherkinPageName, String gherkinSectionName) {
		/*
		 * Look up the list for these two conditions: 1 - page name, 2 - the
		 * section name Then get the element's displayed value and compare it to
		 * the actual input value That is: masterList.get(2).get("Page Name") =
		 * gherkinPageName And masterList.get(2).get("Section") =
		 * gherkinSectionName
		 */
		System.out.println("I am on the " + "\"" + gherkinPageName + "\"" + " page");
		System.out.println("And I am at the " + "\"" + gherkinSectionName + "\"" + " section");

		// System.out.println("I am on the " + "\"" +
		// masterList.get(2).get("Page Name") + "\"" + " page");
		// System.out.println("And I am at the " + "\"" +
		// masterList.get(2).get("Section") + "\"" + " section");

		String storedEmailAddress = masterList.get(2).get("Email");
		String EmailAddressOnPage = targetElement("Email").waitUntilVisible().and().waitUntilClickable().getTextValue();

		System.out.println("I compare the stored Email input = " + storedEmailAddress);
		System.out.println("to the actual value of Email     = " + EmailAddressOnPage);

		try {
			System.out.println("============================================");
			System.out.println("Result of comparison...");
			System.out.println("Actual Value On Page  => " + EmailAddressOnPage);
			System.out.println("Stored Value In Table => " + storedEmailAddress);
			Assert.assertEquals(EmailAddressOnPage, storedEmailAddress);
		} catch (Exception e) {
			value_not_match(EmailAddressOnPage);
		}
	}

	@Step("Field Value Verification Error......")
	public void value_not_match(String displayedValue) {
		System.err.println("**** ERROR:   Element " + "\"" + displayedValue + "\"" + " is NOT MATCHED ...");
	}

	// @Step("I am at the EndUserSteps class")
	@Step
	public void verifyThatAllExpectedElementsAreDisplayedOnPage() {
		currentPage.verifyThatAllExpectedElementsAreDisplayedOnPage(this.pageName);
	}

}
