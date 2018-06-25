package customer;

import java.util.ArrayList;

/**
 * In this class all details about a customer are stored: an arbitrary number of persons where one is a
 * contact person.
 * 
 * @author Lukas Schiefermueller
 * @version 1.00, June 26th 2018
 */
public class Customer {
	private ArrayList<Person> persons;
	private int contactPerson;

	/**
	 * @param persons
	 * @param contactPerson
	 */
	public Customer(ArrayList<Person> persons, int contactPerson) {
		//super();
		this.persons = persons;
		this.contactPerson = contactPerson < 0 ? 0 : (contactPerson >= persons.size() ? 0 : contactPerson);
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
	public String getContactPerson() {
		return persons.get(contactPerson).getName();
	}

	/**
	 * @param contactPerson
	 *            the contactPerson to set
	 */
	public void setContactPerson(int contactPerson) {
		this.contactPerson = contactPerson;
	}

}
