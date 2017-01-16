package auto.pages;

import org.junit.Assert;

import net.serenitybdd.core.pages.WebElementFacade;
import net.thucydides.core.pages.PageObject;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
//import java.util.TreeMap;

public class AmazonBasePageObject extends PageObject {

	// This table holds the input entries on ALL page
	// Each entry is identified by the page name, a unique element ID on the page and its input value
	// | page name | element ID | inputValue| 
	public static  Map<String, HashMap<String, String>> tableOfAllPagesUnderTest = new HashMap<String, HashMap<String, String>>();
	
 	public Map<String, HashMap<String, String>> getTableOfAllPagesUnderTest() {
		return tableOfAllPagesUnderTest;
	}

	protected void setTableOfAllPagesUnderTest(Map<String, HashMap<String, String>> tableOfAllPagesUnderTest) {
		AmazonBasePageObject.tableOfAllPagesUnderTest = tableOfAllPagesUnderTest;
	}

	
	public static Map<String, WebElementFacade> webElementFacadeTable =  new HashMap<String, WebElementFacade>();
	
	// This table holds the input entries on a page
	// Each entry is identified by a unique element ID on the page and its input value
	// | element ID | inputValue |
	protected  Map<String, String> elementIdAndItsInput = new HashMap<String, String>();
	
	// This table holds the temporary ALL elements on A page
	// Each entry is identified by a unique element ID and its location on the page
	// | element ID | element location |
	protected Map<String, String> pageElementsTable = new HashMap<String, String>();
	
	public HashMap<String, String> getPageElementsTable() {
		return (HashMap<String, String>) pageElementsTable;
	}

	public void setPageElementsTable(HashMap<String, String> pageElementsTable) {
		this.pageElementsTable = pageElementsTable;
	}

	// This is the constructor of the Superclass page of all application pages
	// It inherits the PageObjects Superclass from the Serenity framework
	// The content of the pageElementsTable is cleared when a page is initiated.
	// The purpose of clearing the pageElementsTable is to save space in the memory.
	public AmazonBasePageObject()
	{
		elementIdAndItsInput.clear();
		pageElementsTable.clear();
	}
	
	// Get a single element on the page
	public WebElementFacade getElementFacade(String gherkinElement) 
	{
		return $(pageElementsTable.get(gherkinElement.toLowerCase())).waitUntilVisible().and().waitUntilEnabled() ; 
    }

	// verify that all known elements are displayed on the page
	public void verifyThatAllExpectedElementsAreDisplayedOnPage(String gherkinPageName) 
	{
		int i = 0;
		
		System.out.println("Verifying all expected Elelments on the " + "\"" + gherkinPageName + "\"" + " page...");
		System.out.println("============================================");
		for (Entry<String, String> entry : pageElementsTable.entrySet())
		{
			System.out.println("  " + ++i + ") " + "Verifying Element " + "\"" + entry.getKey().toUpperCase() + "\"" + " is Displayed ... ");
			
			try
			{

				Assert.assertNotNull($(entry.getValue()).waitUntilVisible().and().waitUntilEnabled()) ;
				System.out.println("  **** GOOD:   Element " + "\"" + entry.getKey().toUpperCase()  + "\"" + " is FOUND ... ");
				System.out.println("  ============================================");
			} 
			catch (Exception e)
			{
				System.out.println("  **** ERROR:   Element " + "\"" + entry.getKey().toUpperCase()  + "\"" + " is NOT FOUND ...");
				System.out.println("  ============================================");
			}

		}
	
    }
	
	// When an input action takes place, program will insert the input value to the tableOfAllPagesUnderTest
	// The tableOfAllPagesUnderTest serves as the input holder so that it can be used to verify that
	// program correctly saves and displays the input results after a save transaction took place.
	public void insertEntryToTableOfAllPagesUnderTest(String pageName, String gherkinElement, String inputValue)
	{
		
		// save the "target element name" and the "inputValue" to the "pageInputTable"
		// | gherkinElement | inputValue |
		elementIdAndItsInput.put(gherkinElement, inputValue);

		// save the "pageInputTable" along with its "pageName" where it belongs to the "tableOfAllPagesUnderTest"
		// | page name | gherkinElement | inputValue|
		
		tableOfAllPagesUnderTest.put(pageName, (HashMap<String, String>) elementIdAndItsInput);

		System.out.println("Insert into the \"Master Table\"..=> Page's ID = "+ pageName + " ; " 
		                   + "Element's ID = "+ gherkinElement+ " ; " 
				           + "Element's Value = " + tableOfAllPagesUnderTest.get(pageName).get(gherkinElement));
	}

}