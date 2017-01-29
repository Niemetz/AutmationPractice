package auto.pages;

import org.junit.Assert;
import org.openqa.selenium.Alert;

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

	public static  Map<String, Map<String, String>> masterTable = new HashMap<>();
	
	// This table holds the input entries on a page
	// Each entry is identified by a unique element ID on the page and its input value
	// | element ID | inputValue |
	protected  Map<String, String> allElementsAndTheirInputDataOnPageX = new HashMap<>();
	
	// For login mapping
	//protected  Map<String, String> loginMap = new HashMap<String, String>();
	public  Map<String, String> loginMap = new HashMap<>();
	
	// This table holds the temporary ALL elements on A page
	// Each entry is identified by a unique element ID and its location on the page
	// | element ID | element location |
	protected Map<String, String> mapTable = new HashMap<>();


	public Object allWebElementsUnderTest;
	
	// This is the constructor of the Superclass page of all application pages
	// It inherits the PageObjects Superclass from the Serenity framework
	// The content of the pageElementsTable is cleared when a page is initiated.
	// The purpose of clearing the pageElementsTable is to save space in the memory.
	public AmazonBasePageObject()
	{
		allElementsAndTheirInputDataOnPageX.clear();
		mapTable.clear();
	}
	
	// Get a single element on the page
	public WebElementFacade getElement(String gherkinElement) 
	{
//		switch(classInstance)
//		{
//		case "home2" : 
//			           HomePage2 home2 = new HomePage2();
//			           home2.
//			           
// 		}
		return $(mapTable.get(gherkinElement.toLowerCase())).waitUntilVisible().and().waitUntilEnabled() ; 
    }

	// verify that all known elements are displayed on the page
	public void verifyThatAllExpectedElementsAreDisplayedOnPage(String gherkinPageName) 
	{
		int i = 0;
		
		System.out.println("Verifying all expected Elelments on the " + "\"" + gherkinPageName + "\"" + " page...");
		System.out.println("============================================");
		for (Entry<String, String> entry : mapTable.entrySet())
		{
			if(!entry.getKey().equalsIgnoreCase("page unique element"))
			{
			System.out.println("  " + ++i + ") " + "Verifying Element " + "\"" + entry.getKey() + "\"" + " is Displayed ... ");
			try
			{
				Assert.assertNotNull($(entry.getValue()).waitUntilVisible().and().waitUntilEnabled()) ;
				System.out.println("  **** PASSED:   Element " + "\"" + entry.getKey()  + "\"" + " FOUND ... ");
				System.out.println("  ============================================");
			} 
			catch (Exception e)
			{
				System.out.println("  **** FAILED:   Element " + "\"" + entry.getKey().toUpperCase()  + "\"" + " is NOT FOUND ...");
				System.out.println("  ============================================");
			}
			}
		}
    }
	
	// When an input action takes place, program will insert the input value to the tableOfAllPagesUnderTest
	// The tableOfAllPagesUnderTest serves as the input holder so that it can be used to verify that
	// program correctly saves and displays the input results after a save transaction took place.
	public void insertIntoMasterTable(String pageName, String gherkinElement, String inputValue)
	{
		
		if(pageName.equalsIgnoreCase("login"))
		{
			loginMap.put(gherkinElement, inputValue);
			masterTable.put(pageName, loginMap);
			System.out.println("Insert into the \"Master Table\"..=> Page " + "\"" + pageName + "\": " + gherkinElement+ " = " +masterTable.get(pageName).get(gherkinElement));
		} 
		else
		{
		// save the "target element name" and the "inputValue" to the "pageInputTable"
		// | gherkinElement | inputValue |
		allElementsAndTheirInputDataOnPageX.put(new String(gherkinElement), inputValue);
			
		// save the "pageInputTable" along with its "pageName" where it belongs to the "tableOfAllPagesUnderTest"
		// | page name | gherkinElement | inputValue|
		
		masterTable.put(pageName, allElementsAndTheirInputDataOnPageX);
		

		System.out.println("Insert into the \"Master Table\"..=> Page " + "\"" + pageName + "\": " + gherkinElement+ " = " +masterTable.get(pageName).get(gherkinElement));
		} // End of If
		
		System.out.println("============================================");
		System.out.println("Snapshot of \"Master Table\"..." );
		for (Entry<String, Map<String, String>> entry : masterTable.entrySet()) {
			
		    System.out.println("Container/Form = " + entry.getKey() + ":");
		    for (Entry<String, String> entry2 : entry.getValue().entrySet()) {
		    	System.out.println( "       " +entry2.getKey() + " = " +   entry2.getValue() );
		    }
		    System.out.println("============================================");
		}
	}
}
