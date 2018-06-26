package customer;

/**
 * In this class all details about a person are stored.
 * 
 * @author Lukas Schiefermueller
 * @version 1.00, June 26th 2018
 */
public class Person {
	private String name;
	private String phoneNumber;
	private String email;
	private String address;
	private String relation;

	/**
	 * @param name
	 * @param phoneNumber
	 * @param email
	 * @param address
	 * @param relation
	 */
	public Person(String name, String phoneNumber, String email, String address, String relation) {
		this.name = name;
		this.phoneNumber = phoneNumber;
		this.email = email;
		this.address = address;
		this.relation = relation;
	}

	public Person() {
		// everything has to be set via setters
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name
	 *            the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the phoneNumber
	 */
	public String getPhoneNumber() {
		return phoneNumber;
	}

	/**
	 * @param phoneNumber
	 *            the phoneNumber to set
	 */
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param email
	 *            the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * @return the address
	 */
	public String getAddress() {
		return address;
	}

	/**
	 * @param address
	 *            the address to set
	 */
	public void setAddress(String address) {
		this.address = address;
	}

	/**
	 * @return the relation
	 */
	public String getRelation() {
		return relation;
	}

	/**
	 * @param relation
	 *            the relation to set
	 */
	public void setRelation(String relation) {
		this.relation = relation;
	}

	/**
	 * @return String representation for saving into file
	 */
	public String toFile() {
		String ret = "";
		if(name != null)
			ret += name;
		ret += ";";
		if(phoneNumber != null)
			ret += phoneNumber;
		ret += ";";
		if(email != null)
			ret += email;
		ret += ";";
		if(address != null)
			ret += address;
		ret += ";";
		if(relation != null)
			ret += relation;
		ret += ";";
		return ret;
	}
}
