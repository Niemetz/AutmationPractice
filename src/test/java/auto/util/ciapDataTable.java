package auto.util;

public class ciapDataTable {

    private String action;
    private String input;
    private String targetElement;
    private String destinationElement;

    public ciapDataTable (String action, String input, String targetElement, String destinationElement) {
        this.action = action;
        this.input = input;
        this.targetElement = targetElement;
        this.destinationElement = destinationElement;
    }

	public String getAction() {
		return action;
	}


	public String getInput() {
		return input;
	}


	public String getTargetElement() {
		return targetElement;
	}


	public String getDestinationElement() {
		return destinationElement;
	} 
    
}