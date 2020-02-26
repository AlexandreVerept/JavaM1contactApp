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
		
		PersonDao personDao = new PersonDao();
		
		List<Person> personsInTheDB = personDao.listPersons();
		
		for(int i=0;i<personsInTheDB.size();i++) {
			persons.add(personsInTheDB.get(i));
		}
		
		/*
		persons.add(new Person(0, "Gheysens", "Daniel", "Dany", "0648168206", "20 square Foch",
				"daniel.gheysens@isen.yncrea.fr", null));
		persons.add(new Person(0, "Verept", "Alexandre", "Alex", "..........", "36 rue gambrinus",
				"alexandre.verept@isen.yncrea.fr", null));
		persons.add(new Person(0, "Desmullier", "Gabriel", "Gaby", "........", "........",
				"daniel.gheysens@isen.yncrea.fr", null)); */
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
