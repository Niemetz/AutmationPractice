package auto.steps.serenity;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.junit.Assert;

//import java.util.Map.Entry;
import auto.pages.*;
import net.serenitybdd.core.pages.WebElementFacade;
import net.thucydides.core.steps.ScenarioSteps;

@SuppressWarnings("serial")
public class EndUserSteps extends ScenarioSteps {

	String pageName = null;
	String elementName = null;
	WebElementFacade elementFacadeID = null;
	AmazonBasePageObject atCurrentPage = null;
	final Map<String, AmazonBasePageObject> allPagesTable = new HashMap<String, AmazonBasePageObject>();
	final Map<String, AmazonBasePageObject> pagesUnderTest = new HashMap<String, AmazonBasePageObject>();
	final Map<String, String> allElementsOnPage = new HashMap<String, String>();
	public Map<String, String> retrievedPage = new HashMap<String, String>();

	public EndUserSteps()
	{
		this.pageName = null;
		this.elementName = null;
		this.retrievedPage = null;	
	}

	public void createTableOfAllPages()
	{
		allPagesTable.put("home".toLowerCase(), getPages().getPage(Home.class));        
		allPagesTable.put("login".toLowerCase(), getPages().getPage(Login.class)); 
		allPagesTable.put("account main".toLowerCase(), getPages().getPage(AccountMain.class)); 
		allPagesTable.put("your account".toLowerCase(), getPages().getPage(YourAccount.class));
	}
	
	public void navigates_to_page(String gherkinPageName) throws Throwable 
	{
		// clear the contents of the tableOfAllPagesUnderTest in the Super Class
		AmazonBasePageObject.tableOfAllPagesUnderTest.clear();
		
		this.pageName = gherkinPageName.toLowerCase();
		createTableOfAllPages();
		if(pagesUnderTest.containsKey(pageName) == false)
		  pagesUnderTest.put(pageName,allPagesTable.get(pageName) );
		this.atCurrentPage = pagesUnderTest.get(pageName);
		atCurrentPage.open();
	}

	public void clicks_on_elementX(String gherkinElement) throws Throwable 
	{
		atCurrentPage.getElementFacade(gherkinElement.toLowerCase()).waitUntilVisible().and().waitUntilClickable().click();;
	}

//	public WebElementFacade getElementFacade(String elementName)
//	{
//		this.elementName = elementName.toLowerCase();
//		// check to see if the element has been fetched and was stored in the WebElementFacadeTable table?
//		if( atCurrentPage.getWebElementFacadeTable().get(this.elementName) == null)
//			atCurrentPage.getWebElementFacadeTable().put(this.elementName, atCurrentPage.getElement(this.elementName));
//		return atCurrentPage.getWebElementFacadeTable().get(this.elementName);
//	}

	@SuppressWarnings("static-access")
	public void lands_on_pageX(String gherkinPageName) throws Throwable 
	{
		this.pageName = gherkinPageName.toLowerCase();
		if(pagesUnderTest.containsKey(pageName) == false)
			  pagesUnderTest.put(pageName,allPagesTable.get(pageName) );
			this.atCurrentPage = pagesUnderTest.get(pageName);


		atCurrentPage.getElementFacade("Page Unique Element".toLowerCase()).waitUntilPresent().and().waitUntilEnabled();
		
		if(pageName.equalsIgnoreCase("home"))
		{
			System.out.println("I am at the HOME page...");
			//String pageName, String gherkinElement, WebElementFacade webElementFacadeID
			atCurrentPage.insertEntryTotableOfAllPagesUnderTest(pageName, "elementX","Yes");
			atCurrentPage.insertEntryTotableOfAllPagesUnderTest(pageName, "elementY","Yes");
			atCurrentPage.insertEntryTotableOfAllPagesUnderTest(pageName, "elementZ","No");
			
			System.out.println("============================================");
			System.out.println("Map -> Snapshot of \"Master Table\" => " + atCurrentPage.tableOfAllPagesUnderTest);
			System.out.println("============================================");

		}
		if(pageName.toLowerCase().equalsIgnoreCase("login"))
		{
			System.out.println("I am at the LOGIN page...");
			
			atCurrentPage.insertEntryTotableOfAllPagesUnderTest(this.pageName, "elementX", "No");
			atCurrentPage.insertEntryTotableOfAllPagesUnderTest(this.pageName, "elementY", "No");
			atCurrentPage.insertEntryTotableOfAllPagesUnderTest(this.pageName, "elementZ", "Yes");
			
			System.out.println("============================================");
			System.out.println("Map -> Snapshot of \"Master Table\" = " + atCurrentPage.tableOfAllPagesUnderTest);
			System.out.println("============================================");

		}
	}

	public void enters_inputX_into_the_elementY_input_field(String inputValue, String gherkinElement) throws Throwable 
	{
		this.elementName = gherkinElement.toLowerCase();
		// get elementFacadeID from the current page
		elementFacadeID = atCurrentPage.getElementFacade(elementName);
		// Enter the input into the input field
		elementFacadeID.waitUntilVisible().and().waitUntilEnabled().sendKeys(inputValue);
		// register the webElementID to the webElementFacadeTable
		atCurrentPage.webElementFacadeTable.put(elementName, elementFacadeID);
		// insert the elementName and the inputValue into the MasterPageTable
		atCurrentPage.insertEntryTotableOfAllPagesUnderTest(pageName, elementName, inputValue);
		
		System.out.println("============================================");
		System.out.println("Map -> Snapshot of \"Master Table\" = " + atCurrentPage.tableOfAllPagesUnderTest);
		System.out.println("============================================");	
	}
	
	   public void _verifies_that_all_input_were_conrrectly_captured_saved_and_dislayed() throws Throwable {
		   
		   // retrieve all Elements and their values from the tableOfAllPagesUnderTest and set them to  the retrievedPage
		   retrievedPage = atCurrentPage.tableOfAllPagesUnderTest.get(pageName);
		   
			  System.out.println("============================================");
			  System.out.println("Data Input Validation and Verification... " );	
			  System.out.println("============================================");
		   
			  System.out.println("============================================");
			  System.out.println("Snapshot of Master Table.. " + atCurrentPage.tableOfAllPagesUnderTest);	
			  System.out.println("============================================");
			  
		      System.out.println("Content of the retrievedPage = " + retrievedPage);
		   
		   
		   // for each element, check and and see its value matches what has been saved
		   for (Entry<String, String> entry : retrievedPage.entrySet())
		   {

			   int i = 0;
			   if(entry.getKey().equals("email"))
			   {
			      //get the webElementFacade from the webElementFacadeTable
			      elementFacadeID = atCurrentPage.getElementFacade(entry.getKey());
			      // go get the value of the element on the page and compare it with the saved input value in the retrievedPage

				  try
				  {
			         Assert.assertEquals(elementFacadeID.waitUntilVisible().and().waitUntilEnabled().getTextValue(), retrievedPage.get(entry.getKey()));
			        System.out.println(++i + ") " + "On Page,      " + entry.getKey() +" = "+ elementFacadeID.waitUntilVisible().and().waitUntilEnabled().getTextValue());
			        System.out.println(i + ") " + "Stored Value, " + entry.getKey() +" = "+ retrievedPage.get(entry.getKey()));
				  }catch (Exception e)
				  {
						System.out.println("  **** ERROR:   Element " + "\"" + entry.getKey().toUpperCase()  + "\"" + " is NOT MATCHED ...");
						System.out.println("  ============================================");
				  }
			      
			   }
			   
		   }

	   }
	
//	@SuppressWarnings("static-access")
//    public Map<String, String> getPageFromMasterTable (String pageName) {
//    	return atCurrentPage.tableOfAllPagesUnderTest().get(pageName.toLowerCase());
//    }
	
	@SuppressWarnings("static-access")

	public void verifies_that_all_expected_elelments_are_displayed_on_the_page() throws Throwable 
	{
		    atCurrentPage.verifyThatAllExpectedElementsAreDisplayedOnPage(this.pageName) ;   

	}
}
