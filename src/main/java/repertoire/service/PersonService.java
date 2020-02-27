package repertoire.service;

import repertoire.dao.PersonDao;
import repertoire.entities.Person;
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

		// hardcoded version for test:
		/*
		 * persons.add(new Person(0, "Gheysens", "Daniel", "Dany", "06.......",
		 * "20 square des frites", "daniel.gheysens@java2.fr", LocalDate.now()));
		 * persons.add(new Person(0, "Verept", "Alexandre", "Alex", "02........",
		 * "36 rue de Magic", "alexandre.verept@java2.fr", LocalDate.now()));
		 * persons.add(new Person(0, "Desmullier", "Gabriel", "Gaby", "03......",
		 * "3 quai des clowns", "gabriel.desmullier@java2.fr", LocalDate.now()));
		 */
		// dao version:

		PersonDao personDao = new PersonDao();
		List<Person> personsInTheDB = personDao.listPersons();
		for (int i = 0; i < personsInTheDB.size(); i++) {
			persons.add(personsInTheDB.get(i));
		}

	}

	public static ObservableList<Person> getPersons() {
		return PersonServiceHolder.INSTANCE.persons;
	}

	public static void addPerson(Person person) {
		PersonServiceHolder.INSTANCE.persons.add(person);
	}

	private static class PersonServiceHolder {
		private static final PersonService INSTANCE = new PersonService();
	}

}
