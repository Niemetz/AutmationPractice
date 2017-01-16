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
	final Map<String, AmazonBasePageObject> allPagesTable = new HashMap<String, AmazonBasePageObject>();
	
	// This temp table contains all of the page objects under test
	final Map<String, AmazonBasePageObject> pagesUnderTest = new HashMap<String, AmazonBasePageObject>();
	
	// This temp table contains the element names (user assigned names) of elements under test
	final Map<String, String> allElementsOnPage = new HashMap<String, String>();
	
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
	//
	public AmazonBasePageObject getCurrentPage(String gherkinPageName) {
		this.pageName = gherkinPageName.toLowerCase();
		switch (pageName) {
		case "home":pagesUnderTest.put("home", getPages().getPage(Home.class));break;
		case "login":pagesUnderTest.put("login", getPages().getPage(Login.class));;break;
		case "account main":pagesUnderTest.put("account main", getPages().getPage(AccountMain.class));;break;
		case "your account":pagesUnderTest.put("your account", getPages().getPage(YourAccount.class));;break;
		default: System.out.println("ERROR... Page " + "\"" + gherkinPageName + "\"" + " NOT FOUND!!!");
		}
		return pagesUnderTest.get(pageName);
	}

	public void navigates_to_page(String gherkinPageName) throws Throwable {
		// clear the contents of the tableOfAllPagesUnderTest in the Super Class
		AmazonBasePageObject.tableOfAllPagesUnderTest.clear();

		this.pageName = gherkinPageName.toLowerCase();

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

		if (pagesUnderTest.containsKey(pageName) == false)
			pagesUnderTest.put(pageName, getCurrentPage(pageName));

		this.atCurrentPage = pagesUnderTest.get(pageName);

		atCurrentPage.open();
		//////////////////////////////////
	}

	@SuppressWarnings("static-access")
	public WebElementFacade targetElement(String gherkinElement) {
		// this method serves two purposes:
		// 1 - returns the target WebElementFacade
		// 2 - registers the WebElementFacade to the webElementFacadeTable for later use.
		this.elementName = gherkinElement.toLowerCase();

		if (atCurrentPage.webElementFacadeTable.containsKey(elementName) == false && !elementName.equalsIgnoreCase("Page Unique Element"))
			atCurrentPage.webElementFacadeTable.put(elementName, atCurrentPage.getElementFacade(elementName));
		if (elementName.equalsIgnoreCase("Page Unique Element"))
			return atCurrentPage.getElementFacade(elementName);
		else
			return atCurrentPage.webElementFacadeTable.get(elementName);
	}

	@SuppressWarnings("static-access")
	public void clicks_on_elementX(String gherkinElement) throws Throwable {
		targetElement(gherkinElement).waitUntilVisible().and().waitUntilClickable().click();
		System.out.println("============================================");
		System.out.println("Action = CLick => Snapshot of \"WebElementFacade Table\"...\n " + atCurrentPage.webElementFacadeTable);
		System.out.println("============================================");
	}

	@SuppressWarnings("static-access")
	public void lands_on_pageX(String gherkinPageName) throws Throwable {
		this.pageName = gherkinPageName.toLowerCase();
		if (pagesUnderTest.containsKey(pageName) == false)
			pagesUnderTest.put(pageName, allPagesTable.get(pageName));

		// // for the Map approach
		// this.atCurrentPage = pagesUnderTest.get(pageName);

		// for the "Switch-Case" approach
		this.atCurrentPage = getCurrentPage(gherkinPageName);
		targetElement("Page Unique Element").waitUntilPresent().and().waitUntilEnabled();

		if (pageName.equalsIgnoreCase("home")) {
			System.out.println("I am at the HOME page...");
			atCurrentPage.insertEntryToTableOfAllPagesUnderTest(pageName, "elementX", "Yes");
			atCurrentPage.insertEntryToTableOfAllPagesUnderTest(pageName, "elementY", "Yes");
			atCurrentPage.insertEntryToTableOfAllPagesUnderTest(pageName, "elementZ", "No");
			System.out.println("============================================");
			System.out.println("Map -> Snapshot of \"Master Table\"...\n" + atCurrentPage.tableOfAllPagesUnderTest);
			System.out.println("============================================");
		}
		if (pageName.toLowerCase().equalsIgnoreCase("login")) {
			System.out.println("I am at the LOGIN page...");
			atCurrentPage.insertEntryToTableOfAllPagesUnderTest(pageName, "elementX", "No");
			atCurrentPage.insertEntryToTableOfAllPagesUnderTest(pageName, "elementY", "No");
			atCurrentPage.insertEntryToTableOfAllPagesUnderTest(pageName, "elementZ", "Yes");
			System.out.println("============================================");
			System.out.println("Map -> Snapshot of \"Master Table\"...\n " + atCurrentPage.tableOfAllPagesUnderTest);
			System.out.println("============================================");
		}
	}

	@SuppressWarnings("static-access")
	public void enters_inputX_into_the_elementY_input_field(String inputValue, String gherkinElement) throws Throwable {
		// Enter the input into the input field
		targetElement(gherkinElement).waitUntilVisible().and().waitUntilEnabled().sendKeys(inputValue);
		// insert the elementName and the inputValue into the MasterPageTable
		atCurrentPage.insertEntryToTableOfAllPagesUnderTest(pageName, gherkinElement, inputValue);

		System.out.println("============================================");
		System.out.println("Map -> Snapshot of \"Master Table\"...\n" + atCurrentPage.tableOfAllPagesUnderTest);
		System.out.println("============================================") ;

		System.out.println("Action = Enter => Snapshot of \"WebElementFacade Table\"...\n" + atCurrentPage.webElementFacadeTable);


	}

	@SuppressWarnings("static-access")
	public void _verifies_that_all_input_were_conrrectly_captured_saved_and_dislayed() throws Throwable {

		// retrieve all Elements and their values from the
		// tableOfAllPagesUnderTest and set them to the retrievedPage
		retrievedPage = atCurrentPage.tableOfAllPagesUnderTest.get(pageName);
		System.out.println("============================================");
		System.out.println("Data Input Validation and Verification on " + "\"" + pageName.toUpperCase() + "\"" + " Page...");
		System.out.println("============================================");
		System.out.println("Snapshot of Master Table... \n" + atCurrentPage.tableOfAllPagesUnderTest);
		System.out.println("============================================");
		System.out.println("Content of the retrievedPage..." + retrievedPage);
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
					System.out.println(++i + ") " + "Value On Page        => " + entry.getKey() + " = "+ targetElement.waitUntilVisible().and().waitUntilEnabled().getTextValue());
					System.out.println(i + ") " +   "Value in Store Table => " + entry.getKey() + " = " + retrievedPage.get(entry.getKey()));
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
