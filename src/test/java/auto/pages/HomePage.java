package auto.pages;

import net.thucydides.core.annotations.DefaultUrl;

@DefaultUrl("http://automationpractice.com") 

public class HomePage extends AmazonBasePageObject 
{
		public HomePage()
		{
			mapTable.clear();
			mapTable.put("Sign in".toLowerCase(),".login"); 
			mapTable.put("Page Unique Element".toLowerCase(),".login");
		}
}