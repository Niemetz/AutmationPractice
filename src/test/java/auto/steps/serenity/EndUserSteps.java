package auto.steps.serenity;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import org.junit.Assert;
//import java.util.Map.Entry;
import auto.pages.*;
import net.serenitybdd.core.pages.WebElementFacade;
//import net.thucydides.core.annotations.Step;
import net.thucydides.core.steps.ScenarioSteps;

@SuppressWarnings("serial")
public class EndUserSteps extends ScenarioSteps {
	String pageName;
	String elementName;
	WebElementFacade targetElement;
	AmazonBasePageObject currentPage;
	
	// This table is used to store the contents of a page which is extracted from the master table
	public Map<String, String> retrievedPage = new HashMap<>();
	
	// This table is used to store all the instance variables for pages under test
	public Map<String, AmazonBasePageObject> allPagesUnderTest = new HashMap<>();
	
	// This table is used to store all the WebElementFacade under test
	public Map<String, WebElementFacade> allWebElementsUnderTest = new HashMap<>();

	public EndUserSteps() {
		this.pageName = null;
		this.elementName = null;
		this.retrievedPage = null;
		this.targetElement = null;
		this.currentPage = null;
	}
	
	public AmazonBasePageObject getCurrentPage(String gherkinPageName) 
	{
		this.pageName = gherkinPageName.toLowerCase();
		// if the requested page is new to the list of all page under test, then go find the page ...
		if (allPagesUnderTest.containsKey(pageName) == false)
		{
		    switch (pageName) 
		    {
		       case "home":currentPage = getPages().getPage(HomePage.class); break;
		       case "login":currentPage = getPages().getPage(LoginPage.class); break; 
		       case "account main":currentPage = getPages().getPage(AccountMainPage.class);break;
		       case "your account":currentPage = getPages().getPage(YourAccountPage.class);break;
		       default: System.out.println(String.format("ERROR... Page %s NOT FOUND!!!",gherkinPageName ));
		     } 
			//... then add the new page to the list of all pages under test
			allPagesUnderTest.put(new String(pageName), currentPage);
		  } 
		// return the found page to the caller
		return currentPage = allPagesUnderTest.get(pageName);
	}

	public void navigates_to_page(String gherkinPageName) throws Throwable {
		// clear the contents of the tableOfAllPagesUnderTest in the Super Class
		AmazonBasePageObject.masterTable.clear();
		this.pageName = gherkinPageName.toLowerCase();
		currentPage = getCurrentPage(pageName);
		currentPage.open();
	}

	public WebElementFacade targetElement(String gherkinElement) {
		// this method serves two purposes:
		// 1 - returns the target WebElementFacade
		// 2 - registers the WebElementFacade to the webElementFacadeTable for later use.
		this.elementName = gherkinElement.toLowerCase();
		
		if (allWebElementsUnderTest.containsKey(elementName) == false && !elementName.equalsIgnoreCase("Page Unique Element"))
			allWebElementsUnderTest.put(elementName, currentPage.getElement(elementName));
		if (elementName.equalsIgnoreCase("Page Unique Element"))
			return currentPage.getElement(elementName);
		else
			return allWebElementsUnderTest.get(elementName);
	}

//	// THIS IS WHERE THE GETELEMENT METHODS ARE DEFINED
//	public WebElementFacade getElement(String gherkinElement) {
//		this.elementName = gherkinElement.toLowerCase();
//		return currentPage.getElement(gherkinElement);
//	}

	public void clicks_on_elementX(String gherkinElement) throws Throwable {
		targetElement(gherkinElement) .waitUntilVisible().and().waitUntilClickable().click();
	}

	public void lands_on_pageX(String gherkinPageName) throws Throwable {
		
		////////////
		this.pageName = gherkinPageName.toLowerCase();
        //////////////////////////////////
		// // for the Map approach
		// register the page object to the pagesUnderTest
		//if (allPagesUnderTest.containsKey(pageName) == false)
		//   pagesUnderTest.put(pageName,allPagesTable.get(pageName) );
		// this.atCurrentPage = pagesUnderTest.get(pageName);

		//////////////////////////////////
		// for the "Switch-Case" approach. This approach is 5.5% faster than the Map approach.
		// Also it won't instantiate the pages that the test suite doesn't need.

		// If the new page, then register it to the allPagesUnderTest
		if (allPagesUnderTest.containsKey(pageName) == false)
			allPagesUnderTest.put(pageName, getCurrentPage(pageName));

		// get the current page from the allPagesUnderTest
		this.currentPage = allPagesUnderTest.get(pageName);
		
		currentPage = getCurrentPage(pageName);
		
		if (pageName.equalsIgnoreCase("home")) {
			System.out.println("I am at the \"ISA Markings for Indicator\" container/form...");
			System.out.println("I set the \"Released To Public\" = Yes");
			currentPage.insertIntoMasterTable("ISA Markings for Indicator", "Released To Public", "Yes");
			System.out.println("I set the \"Display\" = Yes");
			currentPage.insertIntoMasterTable("ISA Markings for Indicator", "Display", "Yes");
			System.out.println("I set the \"Intelligence Analysis\" = No");
			currentPage.insertIntoMasterTable("ISA Markings for Indicator", "Intelligence Analysis", "No");

		}
		if (pageName.toLowerCase().equalsIgnoreCase("login")) {
			System.out.println("I am at the \"ISA Markings for Type\" container/form...");
			System.out.println("I set the \"Released To Public\" = No");
			currentPage.insertIntoMasterTable("ISA Markings for Type","Released To Public" , "No");
			System.out.println("I set the \"Display\" = No");
			currentPage.insertIntoMasterTable("ISA Markings for Type", "Display", "No");
			System.out.println("I set the \"Intelligence Analysis\" = Yes");
			currentPage.insertIntoMasterTable("ISA Markings for Type", "Inteligence Analysis", "Yes");
		}
	}

	public void enters_inputX_into_the_elementY_input_field(String inputValue, String gherkinElement) throws Throwable {
		// Enter the input into the input field
		targetElement(gherkinElement).waitUntilVisible().and().waitUntilEnabled().sendKeys(inputValue);
		
		// insert the elementName and the inputValue into the MasterPageTable
		currentPage.insertIntoMasterTable(pageName, gherkinElement, inputValue);
	}

	@SuppressWarnings("static-access")
	public void _verifies_that_all_input_were_conrrectly_captured_saved_and_dislayed() throws Throwable {

		// retrieve all Elements and their values from the
		// tableOfAllPagesUnderTest and set them to the retrievedPage
		retrievedPage = currentPage.masterTable.get(pageName);
		System.out.println("Data Input Validation and Verification on " + "\"" + pageName.toUpperCase() + "\"" + " Page...");
		System.out.println("============================================");
//		System.out.println("Snapshot of Master Table... \n" + atCurrentPage.masterTable);
//		System.out.println("============================================");
		System.out.println("Content of the retrievedPage... " + pageName + " = " + retrievedPage);
		System.out.println("Content of the " +"\"" + pageName +"\" page...");
 
		// for each element, check and and see its value matches what has been saved
		for (Entry<String, String> entry : retrievedPage.entrySet()) {
	
			int i = 0;
			if (entry.getKey().equals("Email")) {
				// retrieve the stored webElementFacad, on at a time, from the webElementFacadeTable
				targetElement = targetElement(entry.getKey());
				
				// go get the value of the element on the page and compare it
				// with the saved input value in the retrievedPage
				try {
					Assert.assertEquals(targetElement.waitUntilVisible().and().waitUntilEnabled().getTextValue(),retrievedPage.get(entry.getKey()));
					System.out.println(++i + ") " + "Value On Page             => " + entry.getKey() + " = "+ targetElement.waitUntilVisible().and().waitUntilEnabled().getTextValue());
					System.out.println(i + ") " +   "Value in input Data Table => " + entry.getKey() + " = " + retrievedPage.get(entry.getKey()));
				} catch (Exception e) {
					System.out.println("  **** ERROR:   Element " + "\"" + entry.getKey().toUpperCase() + "\""+ " is NOT MATCHED ...");
					System.out.println("  ============================================");
				}
			}
		}
	}

	public void verifies_that_all_expected_elelments_are_displayed_on_the_page() throws Throwable {
		currentPage.verifyThatAllExpectedElementsAreDisplayedOnPage(pageName);
	}
}
