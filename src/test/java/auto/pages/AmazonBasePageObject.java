package auto.pages;


import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;


import org.junit.Assert;

import net.serenitybdd.core.pages.WebElementFacade;
import net.thucydides.core.annotations.Step;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.pages.PageObject;

public class AmazonBasePageObject extends PageObject {
       
	// | page name | section ID | element ID | inputValue| 
     public final static  Map< String,Map<String, Map<String, String>>> masterTable = new HashMap<>();

	// sectionID | element ID | inputValue |
	final  Map<String,Map<String, String>> allElementsAndTheirInputDataOnPageX = new HashMap<>();
	
	// For login mapping
	protected  Map<String,Map<String, String>> loginMap = new HashMap<>();

	// This table holds the temporary ALL elements on A page
	// Each entry is identified by a unique element ID and its location on the page
	// | element ID | element location |
	//final Map<String, String> mapTable = new HashMap<>();
	public final static Map<String, String> mapTable = new HashMap<>();
	
	// temp elementID and its value
	//final Map<String, String> elementIDAndValue = new HashMap<>();
	final HashMap<String, String> elementIDAndValue = new HashMap<>();

	// My implementation of a TreeMap that contains a list
	//Map<Double,List<Object>> multiMap = new TreeMap<Double,List<Object>>();
	//Map<String, List<String>> multimap = new HashMap<String, List<String>>();
	
	
	// This is the constructor of the Superclass page of all application pages
	// It inherits the PageObjects Superclass from the Serenity framework
	// The content of the pageElementsTable is cleared when a page is initiated.
	// The purpose of clearing the pageElementsTable is to save space in the memory.
	public AmazonBasePageObject()
	{
		//allElementsAndTheirInputDataOnPageX.clear();
		//mapTable.clear();
	}
	
	// Get a single element on the page
	public WebElementFacade getElement(String gherkinElement) 
	{
		return $(mapTable.get(gherkinElement.toLowerCase())).waitUntilVisible().and().waitUntilEnabled() ; 
    }

	@Step("I am at the  AmazonPbasePageObject class")
	public void verifyThatAllExpectedElementsAreDisplayedOnPage(String pageName) 
	{
		int i = 0;
		System.out.println("============================================");
		System.out.println("Verifying all expected Elelments on the " + "\"" + pageName + "\"" + " page...");
		//System.out.println("============================================");
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
				error_Element_is_Not_On_Page(entry.getKey().toUpperCase());
			}
			}
		}
    }
	
	
	@Step("Error.... Element not Found on Page!!!!")
	public void error_Element_is_Not_On_Page(String element)
	{
		System.err.println("  **** FAILED:   Element " + "\"" + element + "\"" + " is NOT FOUND ...");
		System.out.println("  ============================================");
	}
	
	
	
	// When an input action takes place, program will insert the input value to the tableOfAllPagesUnderTest
	// The tableOfAllPagesUnderTest serves as the input holder so that it can be used to verify that
	// program correctly saves and displays the input results after a save transaction took place.
	public void insertIntoMasterTable(String pageName, String sectionID, String gherkinElement, String inputValue)
	{
		
		if(pageName.equalsIgnoreCase("login"))
		{
			elementIDAndValue.put(new String(gherkinElement), inputValue);
			loginMap.put(new String(sectionID), elementIDAndValue);
			masterTable.put(new String(pageName), loginMap);
			String printcontent  = String.format("Insert into the \"Master Table\"...Page  = %s ->  Section = %s -> Element = %s -> Value = %s", pageName, sectionID, gherkinElement, masterTable.get(pageName).get(sectionID).get(gherkinElement)) ;
			System.out.println(printcontent);
		} 
		else
		{
		// save the "target element name" and the "inputValue" to the "pageInputTable"
		// sectionID| gherkinElement | inputValue |
	    elementIDAndValue.put(new String(gherkinElement), inputValue);
		allElementsAndTheirInputDataOnPageX.put(new String(sectionID), elementIDAndValue);
			
		// save the "pageInputTable" along with its "pageName" where it belongs to the "tableOfAllPagesUnderTest"
		// | page name | gherkinElement | inputValue|
		elementIDAndValue.put(new String(gherkinElement), inputValue);
		allElementsAndTheirInputDataOnPageX.put(new String(sectionID), elementIDAndValue);
		masterTable.put(new String(pageName), allElementsAndTheirInputDataOnPageX);
		
		String printcontent  = String.format("Insert into the \"Master Table\"...Page  = %s ->  Section = %s -> Element = %s -> Value = %s", pageName, sectionID, gherkinElement, masterTable.get(pageName).get(sectionID).get(gherkinElement)) ;
		System.out.println(printcontent);
		
		} // End of If
		
		System.out.println("============================================");
		System.out.println("Snapshot of \"Master Table\"... size = " + masterTable.size());
		for (Entry<String,Map<String, Map<String, String>>> pageEntry : masterTable.entrySet()) {	
			

		    System.out.println("Page = " + pageEntry.getKey());
		    
		    for (Entry<String,Map<String, String>> sectionEntry : masterTable.get(pageEntry.getKey()).entrySet()) {
		    	
		    	System.out.println("Section  = " + sectionEntry.getKey());
		    	
		    	for (Entry<String, String> elementEntry : masterTable.get(pageEntry.getKey()).get(sectionEntry.getKey()).entrySet()) {
			    	System.out.println("          * " + elementEntry.getKey() + " = "  + elementEntry.getValue());
		    	
		    }
		    System.out.println("============================================");
		}
	  }
	}
}
