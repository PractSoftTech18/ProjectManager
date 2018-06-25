package project;

/**
 * The priority of a project or a task can be low, normal or high.
 * 
 * @author Lukas Schiefermueller
 * @version 1.00, June 26th 2018
 */
public enum Priority {
	LOW, NORMAL, HIGH;
	
	/**
	 * @author Lydia Grillenberger
	 * @return String representation of this priority
	 */
	@Override
	public String toString() {
		String ret = "";
		switch(this) {
		case LOW:
			ret = "Niedrig";
			break;
		case NORMAL:
			ret = "Normal";
			break;
		case HIGH:
			ret = "Hoch";
			break;
		}
		return ret;
	}
}
