package auto.pages;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.junit.Assert;

import net.serenitybdd.core.pages.WebElementFacade;
import net.thucydides.core.annotations.Step;
import net.thucydides.core.pages.PageObject;

public class AmazonBasePageObject extends PageObject 
{
	protected final static Map<String, String> mapTable = new HashMap<>();
	
    public AmazonBasePageObject()
    {
    	mapTable.clear();
    }

	// Get a single element on the page
	public WebElementFacade getElement(String gherkinElement) 
	{
		return $(mapTable.get(gherkinElement.toLowerCase())).waitUntilPresent().and().waitUntilVisible();
    }

	@Step("I am at the  AmazonPbasePageObject class")
	public void verifyThatAllExpectedElementsAreDisplayedOnPage(String pageName) 
	{
		int i = 0;
		System.out.println("============================================");
		System.out.println("Verifying all expected Elelments on the " + "\"" + pageName + "\"" + " page...");
		for (Entry<String, String> entry : mapTable.entrySet()) 
		{
			if (!entry.getKey().equalsIgnoreCase("page unique element")) 
			{
				System.out.println("  " + ++i + ") " + "Verifying Element " + "\"" + entry.getKey() + "\"" + " is Displayed ... ");
				try 
				{
					Assert.assertNotNull($(entry.getValue()).waitUntilVisible().and().waitUntilEnabled());
					System.out.println("  **** PASSED:   Element " + "\"" + entry.getKey() + "\"" + " FOUND ... ");
					System.out.println("  ============================================");
				} catch (Exception e) 
				{
					error_Element_is_Not_On_Page(entry.getKey());
		        }
           }
	    }
     }

	@Step("Error.... Element not Found on Page !!!")
	public void error_Element_is_Not_On_Page(String element) 
	{
		System.err.println("  **** FAILED:   Element " + "\"" + element + "\"" + " is NOT FOUND ...");
		System.out.println("  ============================================");
	}
}
