package auto.pages;

import org.junit.Assert;
import net.serenitybdd.core.pages.WebElementFacade;
import net.thucydides.core.pages.PageObject;
import java.util.Map.Entry;
import java.util.TreeMap;

public class AmazonBasePageObject extends PageObject{

	// This table holds the input entries on ALL page
	// Each entry is identified by the page name, a unique element ID on the page and its input value
	// | page name | element ID | inputValue| 
	public TreeMap<String, TreeMap<String, String>> masterElementsTable = new TreeMap<String, TreeMap<String, String>>();
	
	// This table holds the input entries on A page
	// Each entry is identified by a unique element ID on the page and its input value
	// | element ID | inputValue |
	protected TreeMap<String, String> pageInputTable = new TreeMap<String, String>();
	
	// This table holds the temporary ALL elements on A page
	// Each entry is identified by a unique element ID and its location on the page
	// | element ID | element location |
	protected TreeMap<String, String> pageElementsTable = new TreeMap<String, String>();
	
	// This is the constructor of the Superclass page of all CIAP pages
	// It inherits the PageObjects Superclass from the Serenity framework
	// The content of the pageElementsTable is cleared when a page is initiated.
	// The purpose of clearing the pageElementsTable is to save space in the memory.
	public AmazonBasePageObject()
	{
		pageElementsTable.clear();
	}
	
	// Get a single element on the page
	public WebElementFacade getElement(String gherkinElement) 
	{
		System.out.println("Target Elelment => " + gherkinElement.toLowerCase() + " = " + pageElementsTable.get(gherkinElement.toLowerCase()));
		System.out.println("=====================================");
		if(!pageElementsTable.containsKey(gherkinElement.toLowerCase()) || pageElementsTable.get(gherkinElement.toLowerCase()).isEmpty())
		{
			System.out.println("ERROR... Element Name = " + "\"" + gherkinElement.toLowerCase() + "\"" + " ; " +
		                                 "Element Location = " + "\"" + pageElementsTable.get(gherkinElement.toLowerCase()) + "\"");
		}
		return $(pageElementsTable.get(gherkinElement.toLowerCase())) ; 
    }

	// verify that all known elements are displayed on the page
	public void verifyThatAllElementsAreOnThePage() 
	{
		for (Entry<String, String> entry : pageElementsTable.entrySet())
		{
			System.out.println("Verifying Element => " + entry.getKey() + " = " + entry.getValue());
			Assert.assertNotNull($(entry.getValue()));
		}
		System.out.println("=====================================");
    }
	
	// When an input action takes place, CIAp will insert the input value to the masterElementsTable
	// The masterElementsTable serves as the input holder so that it can be used to verify that
	// CIAP correctly saves and displays the input results after a save transaction took place.
	public void insertEntryToMasterElementsTable(String pageName, String gherkinElement, String inputValue)
	{

		// save the "target element name" and the "inputValue" to the "pageInputTable"
		// | gherkinElement | inputValue |
		pageInputTable.put(gherkinElement.toLowerCase(), inputValue);

		// save the "pageInputTable" along with its "pageName" where it belongs to the "masterElementsTable"
		// | page name | gherkinElement | inputValue|
		masterElementsTable.put(pageName, pageInputTable);
		
		System.out.println("Master's new entry => Page ID = "+ pageName + " ; " 
		                   + "Element ID = "+ gherkinElement.toLowerCase() + " ; " 
				           + "Input Value = " + masterElementsTable.get(pageName).get(gherkinElement.toLowerCase()));
		
	}
	
	// get the saved input value from the masterElementsTable
	// and compare it to the actual value displayed on the profile page.
	public String getSavedInputValueFrommasterElementsTable(String pageName, String gherkinElement)
	{
		return masterElementsTable.get(pageName).get(gherkinElement.toLowerCase());
	}
	
	
	
}
