package auto.steps.serenity;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;
import auto.pages.*;

import net.thucydides.core.steps.ScenarioSteps;

@SuppressWarnings("serial")
public class EndUserSteps extends ScenarioSteps {

	String pageName = null;
	String elementName = null;
	final TreeMap<String, AmazonBasePageObject> allPages = new TreeMap<String, AmazonBasePageObject>();
	final Map<String, String> allElementsOnPage = new HashMap<String, String>();
	public TreeMap<String, String> retrievedPage = new TreeMap<String, String>();

	public EndUserSteps()
	{
		this.pageName = null;
		this.elementName = null;
		this.retrievedPage = null;
	}
	
	public void create_map_table()
	{
		allElementsOnPage.put("elementH", "No");
		allElementsOnPage.put("elementA", "Yes");
		allElementsOnPage.put("elementC", "Yes");
		allElementsOnPage.put("elementB", "No");
		allElementsOnPage.put("elementD", "No");
		allElementsOnPage.put("elementE", "Yes");
		allElementsOnPage.put("elementG", "Yes");
		allElementsOnPage.put("elementF", "No");

	}
	public void map_search(String elementToBeSearched)
	{
		int step = 0;
		System.out.println("++++++++++++++++++++++++++++++");
		System.out.println("Searching for element => "  +"\"" + elementToBeSearched +"\"");
		for(Entry<String, String> element: allElementsOnPage.entrySet())
		{
			System.out.println("Step = " + ++step + " => " + element.getKey() + " = " + allElementsOnPage.get(element.getKey()));
			if (element.getKey().equalsIgnoreCase(elementToBeSearched.toLowerCase()))
					System.out.println("Found Element => " +"\"" + element.getKey() +"\"");
		}
		System.out.println("++++++++++++++++++++++++++++++");
	}
	
	public void create_table_of_all_pages() {
		allPages.put("home", getPages().getPage(Home.class));
		allPages.put("login", getPages().getPage(Login.class));
		allPages.put("account main", getPages().getPage(AccountMain.class));
		allPages.put("your account", getPages().getPage(YourAccount.class));
	}

	public AmazonBasePageObject get_current_page(String pageName) {
		return allPages.get(pageName.toLowerCase());
	}

	public void navigates_to_page(String pageName) throws Throwable {
		// When the user first logins to the application
		// he must create a table that contains all pages under test
		create_table_of_all_pages();
		
		// and must clear the contents of the tableOfAllPagesUnderTest in the Super Class
		AmazonBasePageObject.tableOfAllPagesUnderTest.clear();
		
		//currentPage = get_current_page(pageName.toLowerCase());
		//currentPage.open();
		
		onPage(pageName).open();

		// Demo of map search sample
		create_map_table();
		map_search("elementH");
	}

	public AmazonBasePageObject onPage(String pageName)
	{
		return get_current_page(pageName.toLowerCase());
	}
	public void clicks_on_elementX(String gherkinElement) throws Throwable {
		this.elementName = gherkinElement.toLowerCase();
		onPage(pageName).getElementFacade(this.elementName).waitUntilPresent().and().waitUntilClickable().click();

	}

	@SuppressWarnings("static-access")
	public void lands_on_pageX(String pageName) throws Throwable {
		this.pageName = pageName.toLowerCase();
		onPage(pageName).waitFor(onPage(this.pageName).getElementFacade("Page Unique Element".toLowerCase()));
		onPage(this.pageName).getElementFacade("Page Unique Element".toLowerCase()).waitUntilPresent().and().waitUntilEnabled();
		if(this.pageName.equalsIgnoreCase("home"))
		{
			onPage(pageName).insertEntryToMasterElementsTable(this.pageName, "elementx".toLowerCase(), "Yes");
			onPage(pageName).insertEntryToMasterElementsTable(this.pageName, "elementy".toLowerCase(), "Yes");
			onPage(pageName).insertEntryToMasterElementsTable(this.pageName, "elementz".toLowerCase(), "No");
			System.out.println("============================================");
			System.out.println("Snapshot of \"Table of all Pages Under Test\" = " + onPage(pageName).tableOfAllPagesUnderTest);
			System.out.println("============================================");

		}
		if(pageName.toLowerCase().equalsIgnoreCase("login"))
		{
			onPage(pageName).insertEntryToMasterElementsTable(this.pageName, "elementx", "No");
			onPage(pageName).insertEntryToMasterElementsTable(this.pageName, "elementy", "No");
			onPage(pageName).insertEntryToMasterElementsTable(this.pageName, "elementz", "Yes");
			
			System.out.println("============================================");
			System.out.println("Snapshot of \"Table of all Pages Under Test\" = " + onPage(pageName).tableOfAllPagesUnderTest);
			System.out.println("============================================");

		}
	}

	public void enters_inputX_into_the_elementY_input_field(String inputValue, String gherkinElement) throws Throwable {
		elementName = gherkinElement.toLowerCase();
		onPage(pageName).getElementFacade(elementName).waitUntilVisible().and().waitUntilEnabled().sendKeys(inputValue);
		onPage(pageName).insertEntryToMasterElementsTable(pageName, gherkinElement, inputValue);
		
		
	}
	
	@SuppressWarnings("static-access")
    public TreeMap<String, String> getPageFromMasterTable (String pageName) {
    	return onPage(pageName).getMasterElementsTable().get(pageName.toLowerCase()); 
    }
	
	@SuppressWarnings("static-access")
	public void verifies_that_all_elements_are_on_the_page() throws Throwable {
		onPage(pageName).verifyThatAllExpectedElementsAreDisplayed();
		if(this.pageName.equalsIgnoreCase("login"))
		{
			System.out.println("============================================");
			System.out.println("Snapshot of \"Table of all Pages Under Test\" = " + onPage(pageName).tableOfAllPagesUnderTest);
			System.out.println("============================================");
			
			// Retrieve the "home" page from the master table
			TreeMap<String, String> HomePage = getPageFromMasterTable ("home");
			
			System.out.println("All Elements and their input values on the \"Home\" Page = " + HomePage);
			System.out.println("On home page; elementx = " + HomePage.get("elementx"));
			System.out.println("On home page; elementy = " + HomePage.get("elementy"));
			System.out.println("On home page; elementz = " + HomePage.get("elementz"));
			
			System.out.println("============================================");
			// Retrieve the "login" page from the master table
			TreeMap<String, String> loginPage = getPageFromMasterTable ("login");
			
			System.out.println("All Elements and their input values on the \"Login\" Page = " + loginPage);
			System.out.println("On Login page; elementx = " + loginPage.get("elementx"));
			System.out.println("On Login page; elementy = " + loginPage.get("elementy"));
			System.out.println("On Login page; elementz = " + loginPage.get("elementz"));
		}
	}
}