package auto.pages;

public class Login extends AmazonBasePageObject {	
	public Login()
	{
		super();
		pageElementsTable.put("Sign in".toLowerCase(), "#signInSubmit");
		pageElementsTable.put("Email".toLowerCase(), "#ap_email");
		pageElementsTable.put("Password".toLowerCase(), "#ap_password");
		pageElementsTable.put("Page Unique Element".toLowerCase(), "#signInSubmit");
	}
}