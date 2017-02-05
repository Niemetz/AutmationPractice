package auto.steps.serenity;

import auto.util.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

//import org.apache.log4j.Logger;
import org.junit.Assert;


//import java.util.Map.Entry;
import auto.pages.*;
import net.serenitybdd.core.pages.PageObject;
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
	
	List<Map<String,String>> masterList = new ArrayList<Map<String,String>>();
	LinkedList<Map<String,String>> ll = new LinkedList<Map<String,String>>();
	
	// This table is used to store the contents of a page which is extracted from the master table
	public Map<String,Map<String, String>> retrievedPage = new HashMap<>();
	
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
		this.sectionName = null;

	}
	
	public AmazonBasePageObject getCurrentPage(String gherkinPageName) 
	{
		this.pageName = gherkinPageName.toLowerCase();
		// if the requested page is new to the list of all page under test, then go find the page ...
		if (allPagesUnderTest.containsKey(pageName) == false)
		{
		    switch (pageName) 
		    {
		       case "home":currentPage = getPages().getPage(HomePage.class);break;
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
   
	@Step
	public void navigates_to_page(String gherkinPageName) throws Throwable {
		// clear the contents of the tableOfAllPagesUnderTest in the Super Class
		//AmazonBasePageObject.masterTable.clear();
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
			//System.out.println(masterList.get(0).containsValue(sectionName));
			System.out.println("I am on the " + "\""+ masterList.get(0).get("Page Name")+ "\"");
			System.out.println("I set the \"Released To Public\" = " + masterList.get(0).get("Released To Public"));
			System.out.println("I set the \"Display\" = " + masterList.get(0).get("Display"));
			System.out.println("I set the \"Intelligence Analysis\" = " + masterList.get(0).get("Intelligence Analysis"));
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
			//System.out.println(masterList.get(1).containsValue("ISA Markings for Type Field"));
			System.out.println("I am on the " + "\""+ masterList.get(1).get("Page Name")+ "\"");
			System.out.println("I set the \"Released To Public\" = " + masterList.get(1).get("Released To Public"));
			System.out.println("I set the \"Display\" = " + masterList.get(1).get("Display"));
			System.out.println("I set the \"Intelligence Analysis\" = " + masterList.get(1).get("Intelligence Analysis"));
		}
	}

	public void enters_inputX_into_the_elementY_input_field(String inputValue, String gherkinElement) throws Throwable {
		// Enter the input into the input field
		targetElement(gherkinElement).waitUntilVisible().and().waitUntilEnabled().sendKeys(inputValue);
	
		// insert the elementName and the inputValue into the MasterPageTable
		//currentPage.insertIntoMasterTable(pageName, sectionName, gherkinElement, inputValue);
		System.out.println("============================================");
		MainSection mainSection = new MainSection();
		this.sectionName = "Main Login";
		mainSection.getMainSection().put(new String("Page Name"), this.pageName);			
		mainSection.getMainSection().put(new String("Section"), this.sectionName);
		mainSection.getMainSection().put(new String(gherkinElement), inputValue);
		masterList.add(mainSection.getMainSection());
	}

	public void _verifies_that_all_input_were_conrrectly_captured_saved_and_dislayed() throws Throwable 
	{
		System.out.println("I am on the " + "\"" + masterList.get(2).get("Page Name") + "\"" + " page");
		System.out.println("And I am at the " + "\"" + masterList.get(2).get("Section") + "\"" + " section");
		
		String storedEmailAddress = masterList.get(2).get("Email");
		String EmailAddressOnPage = targetElement("Email") .waitUntilVisible().and().waitUntilClickable().getTextValue() ;
		
		System.out.println("I compare the stored Email input = " + storedEmailAddress);
		System.out.println("to the actual value of Email     = " + EmailAddressOnPage +" on the page");
		try {
			  //System.out.println("============================================");
			  System.out.println("Result of comparison...");
		      System.out.println("Actual Value On Page             => " + EmailAddressOnPage);
		      System.out.println("Stored Value in input Data Table => " + storedEmailAddress);
		      Assert.assertEquals(EmailAddressOnPage,storedEmailAddress);
	     } catch (Exception e) {
		     System.out.println("  **** ERROR:   Element " + "\"" + EmailAddressOnPage + "\""+ " is NOT MATCHED ...");
		     System.out.println("  ============================================");
	 }
		
//		// retrieve all Elements and their values from the
//		// tableOfAllPagesUnderTest and set them to the retrievedPage
//		retrievedPage = currentPage.masterTable.get(pageName);
//		System.out.println("Data Input Validation and Verification on " + "\"" + pageName.toUpperCase() + "\"" + " Page...");
//		System.out.println("============================================");
////		System.out.println("Snapshot of Master Table... \n" + atCurrentPage.masterTable);
////		System.out.println("============================================");
//		System.out.println("Content of the retrievedPage... " + pageName + " = " + retrievedPage);
//		System.out.println("Content of the " +"\"" + pageName +"\" page...");
// 
//		// for each element, check and and see its value matches what has been saved
//		for (Entry<String, Map<String, String>> sectionEntry : retrievedPage.entrySet()) {
//	
//			int i = 0;
//			for (Entry<String, String> elementSet : retrievedPage.get(sectionEntry.getKey()).entrySet())
//			{
//			if (elementSet.getKey().equals("Email")) {
//				// retrieve the stored webElementFacad, on at a time, from the webElementFacadeTable
//				targetElement = targetElement(elementSet.getKey());
//                
//				// go get the value of the element on the page and compare it
//				// with the saved input value in the retrievedPage
//				try {
//					Assert.assertEquals(targetElement.waitUntilVisible().and().waitUntilEnabled().getTextValue(),retrievedPage.get(elementSet.getKey()));
//					System.out.println(++i + ") " + "Value On Page             => " + elementSet.getKey() + " = "+ targetElement.waitUntilVisible().and().waitUntilEnabled().getTextValue());
//					System.out.println(i + ") " +   "Value in input Data Table => " + elementSet.getKey() + " = " + retrievedPage.get(elementSet.getKey()));
//				} catch (Exception e) {
//					System.out.println("  **** ERROR:   Element " + "\"" + elementSet.getKey().toUpperCase() + "\""+ " is NOT MATCHED ...");
//					System.out.println("  ============================================");
//				}
//			  }
//			}
//		}
	}
	

	public void verifies_that_all_expected_elelments_are_displayed_on_the_page() throws Throwable {
		currentPage.verifyThatAllExpectedElementsAreDisplayedOnPage(pageName);
	}
}
