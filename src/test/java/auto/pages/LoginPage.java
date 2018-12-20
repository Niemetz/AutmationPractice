package auto.pages;

public class LoginPage extends AmazonBasePageObject 
{
	public LoginPage() 
	{
		mapTable.clear();
		mapTable.put("Sign in".toLowerCase(), "#SubmitLogin");
		mapTable.put("Email address".toLowerCase(), "#email");
		mapTable.put("Password".toLowerCase(), "#passwd");
		mapTable.put("Page Unique Element".toLowerCase(), "#SubmitLogin");
	}
}