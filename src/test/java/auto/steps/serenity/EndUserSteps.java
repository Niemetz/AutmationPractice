package auto.steps.serenity;

import java.util.HashMap;
import java.util.Map;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.interactions.Actions;

import auto.pages.AmazonBasePageObject;
import auto.util.TableOfAllPages;
import net.thucydides.core.annotations.Step;
import net.thucydides.core.steps.ScenarioSteps;

@SuppressWarnings("serial")
public class EndUserSteps extends ScenarioSteps {

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
	
	public void clicks_on_the_link_from_the_menu(String gherkinElement, String gherkinMenu) throws Throwable 
	{
			currentPage.getElement(gherkinMenu).findElement(By.linkText(gherkinElement)).click();
	}
}
