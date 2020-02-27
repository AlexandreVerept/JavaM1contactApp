package repertoire.service;

import repertoire.dao.PersonDao;
import repertoire.entities.Person;

import java.time.LocalDate;
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
		for (int i = 0; i < personsInTheDB.size(); i++) {
			persons.add(personsInTheDB.get(i));
		}
		
	}

	public static ObservableList<Person> getPersons() {
		
		PersonServiceHolder.INSTANCE.persons.clear();
		
		PersonDao personDao = new PersonDao();
		List<Person> personsInTheDB = personDao.listPersons();
		
		for (int i = 0; i < personsInTheDB.size(); i++) {
			PersonServiceHolder.INSTANCE.persons.add(personsInTheDB.get(i));
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
