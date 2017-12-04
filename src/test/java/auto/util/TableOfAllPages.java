package auto.util;

import net.thucydides.core.steps.ScenarioSteps;
import java.util.HashMap;
import java.util.Map;

@SuppressWarnings("serial")
public class TableOfAllPages extends ScenarioSteps {
	
	// This table contains all application pages
	private static final Map<String, String> appPages = new HashMap<>();
	
	public TableOfAllPages() {
		appPages.put("home".toLowerCase(), "auto.pages.HomePage");
		appPages.put("login".toLowerCase(), "auto.pages.LoginPage");
		appPages.put("My Account".toLowerCase(), "auto.pages.AccountProfilePage");
		appPages.put("Order History".toLowerCase(), "auto.pages.OrderHistoryPage");
		appPages.put("My Address".toLowerCase(), "auto.pages.MyAddressPage");
	}

	@SuppressWarnings("rawtypes")
	public Class getClass(String pageName) throws ClassNotFoundException {
		return  Class.forName(appPages.get(pageName));
	}

}
