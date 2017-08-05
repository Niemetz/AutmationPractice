package auto.pages;

import net.thucydides.core.annotations.DefaultUrl;

@DefaultUrl("https://www.amazon.com") 

public class HomePage extends AmazonBasePageObject 
{
		public HomePage()
		{
			mapTable.clear();
			mapTable.put("Account & Lists".toLowerCase(),".//*[contains(text(),'Account & Lists')]"); 
			mapTable.put("Page Unique Element".toLowerCase(), ".//*[contains(text(),'Account & Lists')]");
		}
}