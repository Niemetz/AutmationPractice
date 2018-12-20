package auto.pages;

public class AccountProfilePage extends AmazonBasePageObject 
{
	public AccountProfilePage() 
	{
		mapTable.put("Order History And Details".toLowerCase(), ".icon-list-ol");
		mapTable.put("My Credit Slips".toLowerCase(), ".icon-ban-circle");
		mapTable.put("My Addresses".toLowerCase(), ".icon-building");
		mapTable.put("My Personal Information".toLowerCase(), ".icon-user");
		mapTable.put("My Wishlists".toLowerCase(), ".icon-heart");
		
		mapTable.put("Page Unique Element".toLowerCase(), ".icon-list-ol");
	}
}