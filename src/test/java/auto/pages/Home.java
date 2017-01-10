package auto.pages;

// Start of Import Section
//Total LOC = 6
import net.thucydides.core.annotations.DefaultUrl;

 @DefaultUrl("https://www.amazon.com") 

public class Home extends AmazonBasePageObject 

{
		public Home()
		{
			super() ;
			pageElementsTable.put("Sign in securely".toLowerCase(),"#a-autoid-0-announce");
            pageElementsTable.put("Account & Lists".toLowerCase(),"#nav-link-accountList");
			pageElementsTable.put("Page Unique Element".toLowerCase(), "#a-autoid-0-announce");	
		}
}


