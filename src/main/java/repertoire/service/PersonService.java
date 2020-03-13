package repertoire.service;

import repertoire.dao.PersonDao;
import repertoire.entities.Person;

import java.time.LocalDate;
import java.util.Iterator;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * @authors Gabriel Desmullier, Daniel Gheyssens, Alexandre Verept
 */

public class PersonService {

	private ObservableList<Person> persons;

	private PersonService() {
		persons = FXCollections.observableArrayList();

		PersonDao personDao = new PersonDao();
		List<Person> personsInTheDB = personDao.listPersons();

		Iterator<Person> iter = personsInTheDB.iterator();

		while (iter.hasNext()) {
			persons.add(iter.next());
		}

	}

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

	public static void addPerson(Person person) {
		PersonServiceHolder.INSTANCE.persons.add(person);
	}

	private static class PersonServiceHolder {
		private static final PersonService INSTANCE = new PersonService();
	}

}
