package auto.util;

import java.util.HashMap;
import java.util.Map;

public class IsaMarkings {
	String pageID;

	public String getPageID() {
		return pageID;
	}

	public void setPageID(String pageID) {
		this.pageID = pageID;
	}

	///////////////////////////
	String sectionID;

	public String getSectionID() {
		return sectionID;
	}

	public void setSectionID(String sectionID) {
		this.sectionID = sectionID;
	}

	///////////////////////////
	Map<String, String> mainSection = new HashMap<>();

	public Map<String, String> isaMarkingsMain() {
		return mainSection;
	}

	public void setMainSection(Map<String, String> mainSection) {
		this.mainSection = mainSection;
	}

	public IsaMarkings() {
		this.pageID = null;
		this.sectionID = null;
		this.mainSection = null;
	}
}
