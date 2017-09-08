package auto.pages;

import net.thucydides.core.annotations.DefaultUrl;

@DefaultUrl("https://www.amazon.com") 

public class HomePage extends AmazonBasePageObject 
{
		public HomePage()
		{
			mapTable.clear();
			mapTable.put("Sign in securely".toLowerCase(),"#a-autoid-0-announce"); 
			mapTable.put("Page Unique Element".toLowerCase(), "#a-autoid-0-announce");
		}
}