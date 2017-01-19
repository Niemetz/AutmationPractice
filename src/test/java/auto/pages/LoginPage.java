package auto.pages;

public class LoginPage extends AmazonBasePageObject {	
	public LoginPage()
	{
		super();
		elementsOnPage.put("Sign in".toLowerCase(), "#signInSubmit");
		elementsOnPage.put("Email".toLowerCase(), "#ap_email");
		elementsOnPage.put("Password".toLowerCase(), "#ap_password");
		elementsOnPage.put("Page Unique Element".toLowerCase(), "#signInSubmit");
	}
}