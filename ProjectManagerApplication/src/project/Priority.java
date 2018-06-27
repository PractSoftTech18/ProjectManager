package project;

/**
 * The priority of a project or a task can be low, normal or high.
 * 
 * @author Lukas Schiefermueller
 * @version 1.00, June 27th 2018
 */
public enum Priority {
	LOW, NORMAL, HIGH;

	/**
	 * @author Lydia Grillenberger
	 * @return the String representation of the priority
	 */
	@Override
	public String toString() {
		switch (this) {
		case LOW:
			return "Niedrig";
		case NORMAL:
			return "Normal";
		case HIGH:
			return "Hoch";
		default:
			return null;
		}
	}

	/**
	 * return the priority of the String representation
	 * 
	 * @author Lukas Schiefermueller
	 * @param priority
	 *            the name of the priority
	 * @return the priority regarding to the input
	 */
	public static Priority returnPriority(String priority) {
		switch (priority) {
		case "Niedrig":
			return LOW;
		case "Normal":
			return NORMAL;
		case "Hoch":
			return HIGH;
		default:
			return null;
		}
	}
}
