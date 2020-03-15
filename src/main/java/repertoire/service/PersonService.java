package repertoire.service;

import repertoire.dao.PersonDao;
import repertoire.entities.Person;

import java.util.Iterator;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * @authors Gabriel Desmullier, Daniel Gheysens, Alexandre Verept
 */

public class PersonService {

	private ObservableList<Person> persons;

	/**
	 * Method used to create the service
	 */
	private PersonService() {
		persons = FXCollections.observableArrayList();

		PersonDao personDao = new PersonDao();
		List<Person> personsInTheDB = personDao.listPersons();

		Iterator<Person> iter = personsInTheDB.iterator();

		while (iter.hasNext()) {
			persons.add(iter.next());
		}

	}

	/**
	 * @return An ObservableList for java FX with all the person got from the BDD
	 */
	public static ObservableList<Person> getPersons() {

		PersonServiceHolder.INSTANCE.persons.clear();

		PersonDao personDao = new PersonDao();
		List<Person> personsInTheDB = personDao.listPersons();

		Iterator<Person> iter = personsInTheDB.iterator();

		while (iter.hasNext()) {
			PersonServiceHolder.INSTANCE.persons.add(iter.next());
		}

		return PersonServiceHolder.INSTANCE.persons;
	}

	/**
	 * Method used to add a person in the list in Person Service (used to test the
	 * application before the BDD)
	 * 
	 * @param person
	 */
	public static void addPerson(Person person) {
		PersonServiceHolder.INSTANCE.persons.add(person);
	}

	/**
	 * Method used to hold the service
	 */
	private static class PersonServiceHolder {
		private static final PersonService INSTANCE = new PersonService();
	}

}
