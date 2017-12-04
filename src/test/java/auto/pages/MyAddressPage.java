package auto.pages;

public class MyAddressPage extends AmazonBasePageObject 
{
	public MyAddressPage() 
	{
		mapTable.put("Update".toLowerCase(), ".address_update>a[title='Update']>span");
		mapTable.put("Delete".toLowerCase(), ".address_update>a[title='Delete']>span");
		mapTable.put("Add a new address".toLowerCase(), ".main-page-indent>a[title='Add an address']>span");
		
		mapTable.put("Page Unique Element".toLowerCase(), ".address_update>a[title='Update']>span");
	}
}