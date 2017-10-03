package auto.steps.serenity;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.jruby.RubyProcess.Sys;
import org.openqa.selenium.By;

import org.openqa.selenium.interactions.Actions;

import auto.pages.AmazonBasePageObject;
import auto.util.TableOfAllPages;
import net.serenitybdd.core.pages.WebElementFacade;
import net.thucydides.core.annotations.Step;
import net.thucydides.core.steps.ScenarioSteps;

@SuppressWarnings("serial")
public class EndUserSteps extends ScenarioSteps  {

	String pageName;
	AmazonBasePageObject currentPage;

	// This table is used to store all the instance variables for pages under BinarySearchTree
	private static final Map<String, AmazonBasePageObject> allPagesUnderTest = new HashMap<>();
	
	// This table contains all pages of the app
	TableOfAllPages tableOfAllPages = new TableOfAllPages();

	public EndUserSteps() {
		super() ;
		this.pageName = null;
		this.currentPage = null;
	}
	
	@Step
	public void navigates_to_page(String gherkinPageName) throws Throwable {
		currentPage = getCurrentPage(gherkinPageName);
		currentPage.open();
	}
	
	@SuppressWarnings("unchecked")
	public AmazonBasePageObject getCurrentPage(String gherkinPageName) throws ClassNotFoundException 
	{
		// if the desired page was not registered in the allPageUnderTest, 
		// then add the desired page to the allPageUnderTest.
		this.pageName = gherkinPageName.toLowerCase();
		if (!allPagesUnderTest.containsKey(pageName)) 
		{
		    @SuppressWarnings("rawtypes")
		    Class targetPageClass = (Class) tableOfAllPages.getClass(pageName);
			allPagesUnderTest.put(new String(pageName),  (AmazonBasePageObject) getPages().get(targetPageClass));
		}
		// return the desired page to the caller
		return allPagesUnderTest.get(pageName);
	}
	
	public void clicks_on_elementX(String gherkinElement) throws Throwable {
		currentPage.getElement(gherkinElement).waitUntilVisible().and().waitUntilClickable().click();
	}

	public void lands_on_pageX(String gherkinPageName) throws Throwable {
		this.pageName = gherkinPageName.toLowerCase();
		currentPage =  getCurrentPage(pageName);
		currentPage.getElement("page unique element");
	}

	public void enters_inputX_into_the_elementY_input_field(String gherkinInputValue, String gherkinElement) throws Throwable {
		currentPage.getElement(gherkinElement).waitUntilVisible().and().waitUntilEnabled().sendKeys(gherkinInputValue);
	}

	public void verifyThatAllExpectedElementsAreDisplayedOnPage() {
		currentPage.verifyThatAllExpectedElementsAreDisplayedOnPage(this.pageName);
	}
	
	public void moves_the_cursor_over_the_X_Element(String accountAndLists) throws Throwable {
           Actions action = new Actions(getDriver());
           action.moveToElement(currentPage.getElement(accountAndLists)).build().perform();
	}
	
	public String getCurrentLocalDateTimeStamp() {
	    return LocalDateTime.now()
	           .format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS"));
	}
	public int totalTime(String startTime, String endTime) {
	   int totalTime = Integer.parseInt(endTime.split("\\.")[1]) - Integer.parseInt(startTime.split("\\.")[1]);
	   if(totalTime < 0)
		   totalTime = totalTime + 1000;
	    return totalTime;
	}
	
	public void clicks_on_the_link_from_the_menu(String gherkinElement, String gherkinMenu) throws Throwable 
	{    
		     String startTime =  getCurrentLocalDateTimeStamp();
		     
		     List<WebElementFacade> listContents = currentPage.getElement(gherkinMenu).thenFindAll("a span");
		     if(listContents.get(listContents.size()-1).getText().equalsIgnoreCase(gherkinElement))
		    	 listContents.get(listContents.size()-1).click();
		     
		     
//		     listContents.get(listContents.size()-1).click();
//		     for(WebElementFacade item : listContents)
//		     {
//		    	 //System.out.println("Element Text = " + item.getText());
//		    	 if(item.getText().equalsIgnoreCase(gherkinElement))
//		    	 {
//		    		 item.click();
//		    		 break;
//		    	 }
//		     }
		
	        //currentPage.getElement(gherkinMenu).findElement(By.linkText(gherkinElement)).click(); 
	        String endTime = getCurrentLocalDateTimeStamp();
	        System.out.println("Total Time Taken to search the list = " + totalTime(startTime, endTime) + "ms");
	}
	
	public void verifies_that_the_value_of_the_field_is(String gherkinElement, String gherkinValue) throws Throwable 
	{
		String actualValue = currentPage.getElement(gherkinElement).waitUntilVisible().getText();  
		System.out.println("Found... Field " + "\"" + gherkinElement + "\"" + "\'s value is: " + actualValue);
	}
}
