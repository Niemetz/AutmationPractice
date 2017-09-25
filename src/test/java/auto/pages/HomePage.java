package auto.pages;

import net.thucydides.core.annotations.DefaultUrl;

@DefaultUrl("https://www.amazon.com") 

public class HomePage extends AmazonBasePageObject 
{
		public HomePage()
		{
			mapTable.clear();
			mapTable.put("Hello, Sign in".toLowerCase(),".//*[@id='nav-link-accountList']"); 
			mapTable.put("Page Unique Element".toLowerCase(),".//*[@id='nav-link-accountList']");
		}
}