package auto.pages;

// Start of Import Section
//Total LOC = 6
import net.thucydides.core.annotations.DefaultUrl;

 @DefaultUrl("https://www.amazon.com") 

public class HomePage extends AmazonBasePageObject 
{
		public HomePage()
		{
			super() ;
			elementsOnPage.put("Sign in securely".toLowerCase(),"#a-autoid-0-announce");
			elementsOnPage.put("Account & Lists".toLowerCase(),"#nav-link-accountList");
			elementsOnPage.put("Page Unique Element".toLowerCase(), "#a-autoid-0-announce");	
		}
}


