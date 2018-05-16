/**
 * 
 */
package customer;

import java.util.ArrayList;

/**
 * @author Lukas Schiefermueller
 *
 */
public class Customer {
	private ArrayList<Person> persons;
	private int contactPerson;

	/**
	 * @param persons
	 * @param contactPerson
	 */
	public Customer(ArrayList<Person> persons, int contactPerson) {
		super();
		this.persons = persons;
		this.contactPerson = contactPerson;
	}

	/**
	 * @return the persons
	 */
	public ArrayList<Person> getPersons() {
		return persons;
	}

	/**
	 * @param persons
	 *            the persons to set
	 */
	public void setPersons(ArrayList<Person> persons) {
		this.persons = persons;
	}

	/**
	 * @return the contactPerson
	 */
	public int getContactPerson() {
		return contactPerson;
	}

	/**
	 * @param contactPerson
	 *            the contactPerson to set
	 */
	public void setContactPerson(int contactPerson) {
		this.contactPerson = contactPerson;
	}

}
