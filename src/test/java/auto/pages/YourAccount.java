package auto.pages;

import ch.lambdaj.function.convert.Converter;
import net.thucydides.core.annotations.DefaultUrl;
import net.thucydides.core.annotations.Managed;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;

import net.serenitybdd.core.pages.WebElementFacade;
import net.serenitybdd.core.annotations.findby.FindBy;
import net.thucydides.core.pages.PageObject;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static ch.lambdaj.Lambda.convert;


public class YourAccount extends PageObject {
	
	
 @FindBy(id="your-orders-button-announce")
 public static WebElementFacade pageUniqueElement;
 
 @FindBy(xpath="//h4[contains(text(), 'Order History')]/following-sibling::ul [@class='a-nostyle a-vertical']")
 public static WebElementFacade orderHistoryLinks;

 public static Map<String, WebElementFacade> allElementsOnAccountLinkPage = new HashMap<String, WebElementFacade>();	
 public static WebElementFacade targetElement = null;
	
 public WebElementFacade getElement(String gherkinElement)
 {
	 allElementsOnAccountLinkPage.put("Page Unique Element",pageUniqueElement);
	 allElementsOnAccountLinkPage.put("Order History",orderHistoryLinks);
	 
	 return allElementsOnAccountLinkPage.get(gherkinElement);
 }

}
