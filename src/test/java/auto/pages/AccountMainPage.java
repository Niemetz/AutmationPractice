package auto.pages;

public class AccountMainPage extends AmazonBasePageObject 
{
	public AccountMainPage() 
	{
		super();
		mapTable.put("Your Account".toLowerCase(), "#nav-link-accountList");
		mapTable.put("Page Unique Element".toLowerCase(), "#ap_password");
	}
}