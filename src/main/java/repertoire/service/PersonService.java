package repertoire.service;

import repertoire.entities.Person;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class PersonService {

	private ObservableList<Person> persons;
	
	private PersonService() {
		persons = FXCollections.observableArrayList();
		persons.add(new Person(0,"Gheysens","Daniel","Dany","0648168206","20 square Foch", "daniel.gheysens@isen.yncrea.fr", null));
		persons.add(new Person(0,"Verept","Alexandre","Alex","..........","36 rue gambrinus", "alexandre.verept@isen.yncrea.fr", null));
		persons.add(new Person(0,"Desmullier","Gabriel","dany","........","........", "daniel.gheysens@isen.yncrea.fr", null));
	}
	
	public static ObservableList<Person> getPersons() {
		return PersonServiceHolder.INSTANCE.persons;
	}
	
	private static class PersonServiceHolder {
		private static final PersonService INSTANCE = new PersonService();
	}
	
	public static void addPerson(Person person) {
		PersonServiceHolder.INSTANCE.persons.add(person);
	}
}
