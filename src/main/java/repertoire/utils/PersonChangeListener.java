package repertoire.utils;

import java.sql.SQLException;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import repertoire.entities.Person;

/**
 * @authors Gabriel Desmullier, Daniel Gheyssens, Alexandre Verept
 */

public abstract class PersonChangeListener implements ChangeListener<Person> {

	/**
	 * @param an observable, the old person and the new person
	 */
	public void changed(ObservableValue<? extends Person> observable, Person oldValue, Person newValue) {
		handleNewValue(newValue);

	}

	public abstract void handleNewValue(Person newValue);

}
