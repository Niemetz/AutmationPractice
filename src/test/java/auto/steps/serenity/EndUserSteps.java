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
	AmazonBasePageObject atCurrentPage;

	
	// This master table contains the "Page Name" (PageID) and its "Page Object ID" of all pages under test.
	// e.i ... | PageID | Page Object ID |
	// Important Note: ONLY the pages that are needed are initialized and saved to this table.
	//final Map<String, AmazonBasePageObject> allPagesTable = new HashMap<String, AmazonBasePageObject>();
	
	// This table contains all of the page objects under test
	final Map<String, AmazonBasePageObject> allPagesUnderTest = new HashMap<String, AmazonBasePageObject>();
	
	// This table contains all of the element objects under test
	//final Map<String, WebElementFacade> allWebElementsUnderTest =  new HashMap<String, WebElementFacade>();
	
	// This temp table is used to store the contents of a page which is extracted from the master table
	public Map<String, String> retrievedPage = new HashMap<String,  String>();

	public EndUserSteps() {
		this.pageName = null;
		this.elementName = null;
		this.retrievedPage = null;
		this.targetElement = null;
		this.atCurrentPage = null;
	}

	// public void createTableOfAllPages()
	// {
	// allPagesTable.put("home".toLowerCase(), getPages().getPage(Home.class));
	// allPagesTable.put("login".toLowerCase(),
	// getPages().getPage(Login.class));
	// allPagesTable.put("account main".toLowerCase(),
	// getPages().getPage(AccountMain.class));
	// allPagesTable.put("your account".toLowerCase(),
	// getPages().getPage(YourAccount.class));
	// }

	public AmazonBasePageObject getCurrentPage(String gherkinPageName) {
		this.pageName = gherkinPageName.toLowerCase();
		switch (pageName) {
		case "home":allPagesUnderTest.put("home", getPages().getPage(HomePage.class));break;
		case "login":allPagesUnderTest.put("login", getPages().getPage(LoginPage.class));break;
		case "account main":allPagesUnderTest.put("account main", getPages().getPage(AccountMainPage.class));break;
		case "your account":allPagesUnderTest.put("your account", getPages().getPage(YourAccountPage.class));break;
		default: System.out.println(String.format("ERROR... Page %s NOT FOUND!!!",gherkinPageName ));
		}
		return allPagesUnderTest.get(pageName);
	}

	public void navigates_to_page(String gherkinPageName) throws Throwable {
		// clear the contents of the tableOfAllPagesUnderTest in the Super Class
		AmazonBasePageObject.masterTable.clear();

		this.pageName = gherkinPageName.toLowerCase();

//		String variable = "John";
//		String example = String.format("tr[svd='{%s}']", variable);
//		System.out.println("Value of example = " + example);
		//////////////////////////////////
		// // for the Map approach
		// createTableOfAllPages();
		//
		// if(pagesUnderTest.containsKey(pageName) == false)
		// pagesUnderTest.put(pageName,allPagesTable.get(pageName) );
		//
		// this.atCurrentPage = pagesUnderTest.get(pageName);
		// atCurrentPage.open();
		//
		
		//////////////////////////////////
		// for the "Switch-Case" approach. This approach is 5.5% faster than the Map approach.
		// Also it won't instantiate the pages that the test suite doesn't need.

		// If the new page, then register it to the allPagesUnderTest
		if (allPagesUnderTest.containsKey(pageName) == false)
			allPagesUnderTest.put(pageName, getCurrentPage(pageName));

		// Set the current page from the allPagesUnderTest
		this.atCurrentPage = allPagesUnderTest.get(pageName);

		atCurrentPage.open();
		//////////////////////////////////
	}

	@SuppressWarnings("static-access")
	public WebElementFacade targetElement(String gherkinElement) {
		// this method serves two purposes:
		// 1 - returns the target WebElementFacade
		// 2 - registers the WebElementFacade to the webElementFacadeTable for later use.
		this.elementName = gherkinElement.toLowerCase();
		
		if (atCurrentPage.allWebElementsUnderTest.containsKey(elementName) == false && !elementName.equalsIgnoreCase("Page Unique Element"))
			atCurrentPage.allWebElementsUnderTest.put(elementName, atCurrentPage.getElementFacade(elementName));
		if (elementName.equalsIgnoreCase("Page Unique Element"))
			return atCurrentPage.getElementFacade(elementName);
		else
			return atCurrentPage.allWebElementsUnderTest.get(elementName);
	}

	@SuppressWarnings("static-access")
	public void clicks_on_elementX(String gherkinElement) throws Throwable {
		targetElement(gherkinElement).waitUntilVisible().and().waitUntilClickable().click();
		System.out.println("============================================");
		System.out.println(String.format("Click on Element %s" , gherkinElement));
		System.out.println("Saving elementFacade to the allWebElementsUnderTest table." + gherkinElement);		
		System.out.println("Snapshot of \"allWebElementsUnderTest table.\"..." );
	    for (Entry<String, WebElementFacade> entry : atCurrentPage.allWebElementsUnderTest.entrySet()) {
	    	System.out.println( "       " +entry.getKey() + " = " +   entry.getValue() );
	    }
		System.out.println("============================================");
	}

	@SuppressWarnings("static-access")
	public void lands_on_pageX(String gherkinPageName) throws Throwable {
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
		this.atCurrentPage = allPagesUnderTest.get(pageName);

		targetElement("Page Unique Element").waitUntilPresent().and().waitUntilEnabled();

		if (pageName.equalsIgnoreCase("home")) {
			System.out.println("I am at the \"ISA Markings for Indicator\" container/form...");
			System.out.println("I set the \"Released To Public\" = Yes");
			atCurrentPage.insertIntoTableOfAllPagesUnderTestThisEntry("ISA Markings for Indicator", "Released To Public", "Yes");
			System.out.println("I set the \"Display\" = Yes");
			atCurrentPage.insertIntoTableOfAllPagesUnderTestThisEntry("ISA Markings for Indicator", "Display", "Yes");
			System.out.println("I set the \"Intelligence Analysis\" = No");
			atCurrentPage.insertIntoTableOfAllPagesUnderTestThisEntry("ISA Markings for Indicator", "Intelligence Analysis", "No");

		}
		if (pageName.toLowerCase().equalsIgnoreCase("login")) {
			System.out.println("I am at the \"ISA Markings for Type\" container/form...");
			System.out.println("I set the \"Released To Public\" = No");
			atCurrentPage.insertIntoTableOfAllPagesUnderTestThisEntry("ISA Markings for Type","Released To Public" , "No");
			System.out.println("I set the \"Display\" = No");
			atCurrentPage.insertIntoTableOfAllPagesUnderTestThisEntry("ISA Markings for Type", "Display", "No");
			System.out.println("I set the \"Intelligence Analysis\" = Yes");
			atCurrentPage.insertIntoTableOfAllPagesUnderTestThisEntry("ISA Markings for Type", "Inteligence Analysis", "Yes");
		}
	}

	@SuppressWarnings("static-access")
	public void enters_inputX_into_the_elementY_input_field(String inputValue, String gherkinElement) throws Throwable {
		// Enter the input into the input field
		targetElement(gherkinElement).waitUntilVisible().and().waitUntilEnabled().sendKeys(inputValue);
		
		// insert the elementName and the inputValue into the MasterPageTable
		atCurrentPage.insertIntoTableOfAllPagesUnderTestThisEntry(pageName, gherkinElement, inputValue);
		
		System.out.println("============================================");
		System.out.println(String.format("Enter \"" + inputValue + "\" on Element \"" + gherkinElement + "\""));
		System.out.println("Saving elementFacade to the allWebElementsUnderTest table." + gherkinElement);		
		System.out.println("Snapshot of \"allWebElementsUnderTest table.\"..." );
	    for (Entry<String, WebElementFacade> entry : atCurrentPage.allWebElementsUnderTest.entrySet()) {
	    	System.out.println( "*** " +entry.getKey() + " = " +   entry.getValue() );
	    } 
		System.out.println("============================================");

//		System.out.println("============================================");
//		System.out.println("Map -> Snapshot of \"Master Table\"...\n" + atCurrentPage.masterTable);
//		System.out.println("============================================") ;
//		System.out.println("Action = Enter => Snapshot of \"WebElementFacade Table\"...\n" + atCurrentPage.allWebElementsUnderTest);
	}

	@SuppressWarnings("static-access")
	public void _verifies_that_all_input_were_conrrectly_captured_saved_and_dislayed() throws Throwable {

		// retrieve all Elements and their values from the
		// tableOfAllPagesUnderTest and set them to the retrievedPage
		retrievedPage = atCurrentPage.masterTable.get(pageName);
//		System.out.println("============================================");
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
		atCurrentPage.verifyThatAllExpectedElementsAreDisplayedOnPage(pageName);
	}
}
