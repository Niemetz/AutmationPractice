package auto.util;

public class InputEntry {
	private String pageName;
	private String sectionName;
	private String elementName;
	private String inputValue;

	public InputEntry(String pageName, String sectionName, String elementName, String inputValue) {
		this.pageName = pageName;
		this.sectionName = sectionName;
		this.elementName = elementName;
		this.inputValue = inputValue;
	}

	public String getPageName() {
		return pageName;
	}

	public String getSectionName() {
		return sectionName;
	}

	public String getElementName() {
		return elementName;
	}

	public String getInputValue() {
		return inputValue;
	}
}
