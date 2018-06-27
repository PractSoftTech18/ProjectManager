package customer;

import java.util.ArrayList;

/**
 * In this class all details about a customer are stored: an arbitrary number of
 * persons where one is a contact person.
 * 
 * @author Lukas Schiefermueller
 * @version 1.00, June 27th 2018
 */
public class Customer {
	/**
	 * the persons of the customer
	 */
	private ArrayList<Person> persons;

	/**
	 * the contact person of the customer
	 */
	private int contactPerson;

	/**
	 * default constructor
	 */
	public Customer() {
		persons = new ArrayList<>();
		contactPerson = 0;
	}

	/**
	 * constructor
	 * 
	 * @param persons
	 *            the persons to set
	 * @param contactPerson
	 *            the contact person to set
	 */
	public Customer(ArrayList<Person> persons, int contactPerson) {
		this.persons = persons;
		this.contactPerson = contactPerson < 0 ? 0 : (contactPerson >= persons.size() ? 0 : contactPerson);
	}

	/**
	 * add a person to persons
	 * 
	 * @param person
	 *            the person to add
	 */
	public void add(Person person) {
		persons.add(person);
	}

	/**
	 * getter for persons
	 * 
	 * @return the persons of the customer
	 */
	public ArrayList<Person> getPersons() {
		return persons;
	}

	/**
	 * setter for persons
	 * 
	 * @param persons
	 *            the persons to set
	 */
	public void setPersons(ArrayList<Person> persons) {
		this.persons = persons;
	}

	/**
	 * getter for contact person
	 * 
	 * @return the contact person of the customer
	 */
	public String getContactPerson() {
		return persons.get(contactPerson).getName();
	}

	/**
	 * getter for the index of contact person
	 * 
	 * @return the index of the contact person of the customer
	 */
	public int getContactPersonIndex() {
		return contactPerson;
	}

	/**
	 * setter for contact person
	 * 
	 * @param contactPerson
	 *            the contactPerson to set
	 */
	public void setContactPerson(int contactPerson) {
		this.contactPerson = contactPerson;
	}
}
