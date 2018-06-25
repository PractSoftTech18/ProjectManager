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
		switch (this) {
		case LOW:
			return "Niedrig";
		case NORMAL:
			return "Normal";
		case HIGH:
			return "Hoch";
		default: return null;
		}
	}

	public static Priority returnPriority(String prio) {
		switch (prio) {
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
