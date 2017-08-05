package auto.pages;

public class AccountAndListsSection extends AmazonBasePageObject 
{
	public AccountAndListsSection() 
	{
		mapTable.clear();
		mapTable.put("Your Account".toLowerCase(), ".//*[@id='nav-al-your-account']");
		mapTable.put("Page Unique Element".toLowerCase(), ".//*[@id='nav-al-your-account']");
	}
}