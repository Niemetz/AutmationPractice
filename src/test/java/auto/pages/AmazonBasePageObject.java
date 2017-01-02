package auto.pages;

import org.junit.Assert;

import net.serenitybdd.core.pages.WebElementFacade;
import net.thucydides.core.pages.PageObject;
import java.util.Map.Entry;
import java.util.TreeMap;

public class AmazonBasePageObject extends PageObject {

	// This table holds the input entries on ALL page
	// Each entry is identified by the page name, a unique element ID on the page and its input value
	// | page name | element ID | inputValue| 
	public static TreeMap<String, TreeMap<String, String>> tableOfAllPagesUnderTest = new TreeMap<String, TreeMap<String, String>>();

	public static TreeMap<String, TreeMap<String, String>> getMasterElementsTable() {
		return tableOfAllPagesUnderTest;
	}

	public static void setMasterElementsTable(TreeMap<String, TreeMap<String, String>> masterElementsTable) {
		AmazonBasePageObject.tableOfAllPagesUnderTest = masterElementsTable;
	}

	// This table holds the input entries on A page
	// Each entry is identified by a unique element ID on the page and its input value
	// | element ID | inputValue |
	protected  TreeMap<String, String> elementIdAndItsInput = new TreeMap<String, String>();
	
	// This table holds the temporary ALL elements on A page
	// Each entry is identified by a unique element ID and its location on the page
	// | element ID | element location |
	protected TreeMap<String, String> pageElementsTable = new TreeMap<String, String>();
	
	public TreeMap<String, String> getPageElementsTable() {
		return pageElementsTable;
	}

	public void setPageElementsTable(TreeMap<String, String> pageElementsTable) {
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
	public void verifyThatAllExpectedElementsAreDisplayed() 
	{
		for (Entry<String, String> entry : pageElementsTable.entrySet())
		{
			System.out.println("Verifying Element is Displayed on Page => " + entry.getKey() + " = " + entry.getValue());
			Assert.assertNotNull($(entry.getValue()).waitUntilVisible().and().waitUntilEnabled());
		}
		System.out.println("============================================");
    }
	
	// When an input action takes place, CIAp will insert the input value to the tableOfAllPagesUnderTest
	// The tableOfAllPagesUnderTest serves as the input holder so that it can be used to verify that
	// CIAP correctly saves and displays the input results after a save transaction took place.
	public void insertEntryToMasterElementsTable(String pageName, String gherkinElement, String inputValue)
	{
		// save the "target element name" and the "inputValue" to the "pageInputTable"
		// | gherkinElement | inputValue |
		elementIdAndItsInput.put(gherkinElement.toLowerCase(), inputValue);

		// save the "pageInputTable" along with its "pageName" where it belongs to the "tableOfAllPagesUnderTest"
		// | page name | gherkinElement | inputValue|
		
		tableOfAllPagesUnderTest.put(pageName.toLowerCase(), elementIdAndItsInput);

		System.out.println("Insert this entry into the \"Table of All Pages Under Test\" ... => Page's ID = "+ pageName + " ; " 
		                   + "Element's ID = "+ gherkinElement.toLowerCase() + " ; " 
				           + "Element's Value = " + tableOfAllPagesUnderTest.get(pageName).get(gherkinElement.toLowerCase()));
	}

}
