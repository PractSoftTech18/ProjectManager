package customer;

/**
 * In this class all details about a person are stored.
 * 
 * @author Lukas Schiefermueller
 * @version 1.00, June 27th 2018
 */
public class Person {
	/**
	 * the name of the person
	 */
	private String name;

	/**
	 * the phone number of the person
	 */
	private String phoneNumber;

	/**
	 * the email address of the person
	 */
	private String email;

	/**
	 * the address of the person
	 */
	private String address;

	/**
	 * the relation of the person to the project
	 */
	private String relation;

	/**
	 * constructor
	 * 
	 * @param name
	 *            the name to set
	 * @param phoneNumber
	 *            the phone number to set
	 * @param email
	 *            the email address to set
	 * @param address
	 *            the address to set
	 * @param relation
	 *            the relation to set
	 */
	public Person(String name, String phoneNumber, String email, String address, String relation) {
		this.name = name;
		this.phoneNumber = phoneNumber;
		this.email = email;
		this.address = address;
		this.relation = relation;
	}

	/**
	 * default constructor
	 */
	public Person() {
		// everything has to be set via setters
	}

	/**
	 * getter for name
	 * 
	 * @return the name of the person
	 */
	public String getName() {
		return name;
	}

	/**
	 * setter for name
	 * 
	 * @param name
	 *            the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * getter for phone number
	 * 
	 * @return the phone number of the person
	 */
	public String getPhoneNumber() {
		return phoneNumber;
	}

	/**
	 * setter for phone number
	 * 
	 * @param phoneNumber
	 *            the phone number to set
	 */
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	/**
	 * getter for email address
	 * 
	 * @return the email address of the person
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * setter for email
	 * 
	 * @param email
	 *            the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * getter for address
	 * 
	 * @return the address of the person
	 */
	public String getAddress() {
		return address;
	}

	/**
	 * setter for address
	 * 
	 * @param address
	 *            the address to set
	 */
	public void setAddress(String address) {
		this.address = address;
	}

	/**
	 * getter for relation
	 * 
	 * @return the relation of the person to the project
	 */
	public String getRelation() {
		return relation;
	}

	/**
	 * setter for relation
	 * 
	 * @param relation
	 *            the relation to set
	 */
	public void setRelation(String relation) {
		this.relation = relation;
	}

	/**
	 * compute a String for saving into file
	 * 
	 * @return String representation for saving into file
	 */
	public String toFile() {
		String ret = "";
		if (name != null)
			ret += name;
		ret += ";";
		if (phoneNumber != null)
			ret += phoneNumber;
		ret += ";";
		if (email != null)
			ret += email;
		ret += ";";
		if (address != null)
			ret += address;
		ret += ";";
		if (relation != null)
			ret += relation;
		ret += ";";
		return ret;
	}
}
