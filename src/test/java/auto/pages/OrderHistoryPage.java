package auto.pages;

public class OrderHistoryPage extends AmazonBasePageObject {
	public OrderHistoryPage() {
		mapTable.put("Order Reference".toLowerCase(), ".history_link.bold.footable-first-column");
		mapTable.put("Date".toLowerCase(), ".history_date.bold");
		mapTable.put("Total Price".toLowerCase(), "span[class='price']");
		mapTable.put("Payment".toLowerCase(), ".//*[@id='order-list']/tbody/tr/td[4]");
		mapTable.put("Status".toLowerCase(), ".//*[@id='order-list']/tbody/tr/td[5]/span");

		mapTable.put("Page Unique Element".toLowerCase(), ".history_link.bold.footable-first-column");
	}
}