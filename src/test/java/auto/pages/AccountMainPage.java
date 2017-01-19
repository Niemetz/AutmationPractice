package auto.pages;

public class AccountMainPage extends AmazonBasePageObject {
	
 public AccountMainPage()
 {
	 super() ;
        
	 elementsOnPage.put("Your Account".toLowerCase(),"#nav-link-accountList");
	 elementsOnPage.put("Page Unique Element".toLowerCase(),"#nav-link-accountList");
 }
 
}