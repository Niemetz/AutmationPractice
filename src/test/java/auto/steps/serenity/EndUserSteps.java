package auto.steps.serenity;

import auto.pages.*;
import net.thucydides.core.steps.ScenarioSteps;

public class EndUserSteps extends ScenarioSteps {

	AmazonBasePageObject currentPage;

	String pageName = null;
	String elementName = null;
	
	public void navigates_to_page(String pageName) throws Throwable 
	{
		currentPage = getPages().getPage(Home.class);
		currentPage.open();
		currentPage.masterElementsTable.clear();
    }
	
    public void clicks_on(String gherkinElement) throws Throwable
	{
    	elementName = gherkinElement.toLowerCase();
    	currentPage.getElement(elementName).waitUntilPresent().and().waitUntilClickable().click();
    }
	
	public void lands_on_page(String pageName) throws Throwable 
	{
		this.pageName = pageName.toLowerCase();
		switch(this.pageName)
			{
			  case "home": currentPage =  getPages().getPage(Home.class);  break;
			  case "login": currentPage =  getPages().getPage(Login.class);  break;
			  case "account main": currentPage = getPages().getPage(AccountMain.class); break;
			}
		currentPage.getElement("Page Unique Element".toLowerCase()).waitUntilPresent().and().waitUntilEnabled();
	}

	public void enters_into_the_input_field(String inputValue, String gherkinElement) throws Throwable 
	{
		        elementName = gherkinElement.toLowerCase();
				currentPage.getElement(elementName).sendKeys(inputValue);
				currentPage.insertEntryToMasterElementsTable(pageName,elementName,inputValue);
	}
	
	
	public void verifies_that_all_elements_are_on_the_page() throws Throwable
	{
		  currentPage.verifyThatAllElementsAreOnThePage();
	}	

}