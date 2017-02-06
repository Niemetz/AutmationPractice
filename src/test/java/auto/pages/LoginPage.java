package auto.pages;

public class LoginPage extends AmazonBasePageObject {
	public LoginPage() {
		super();
		mapTable.clear();
		mapTable.put("Sign in".toLowerCase(), "#signInSubmit");
		mapTable.put("Email".toLowerCase(), "#ap_email");
		mapTable.put("Password".toLowerCase(), "#ap_password");
		mapTable.put("Page Unique Element".toLowerCase(), "#signInSubmit");
	}
}