package auto.pages;

import net.thucydides.core.annotations.DefaultUrl;

 @DefaultUrl("https://www.amazon.com") 

public class HomePage extends AmazonBasePageObject 
{
		public HomePage()
		{
			super() ;
			mapTable.put("Sign in securely".toLowerCase(),"#a-autoid-0-announce");
			mapTable.put("Account & Lists".toLowerCase(),"#nav-link-accountList");
			mapTable.put("Page Unique Element".toLowerCase(), "#a-autoid-0-announce");	
		}
}


