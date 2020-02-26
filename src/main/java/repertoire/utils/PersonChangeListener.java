package repertoire.utils;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import repertoire.entities.Person;

public abstract class PersonChangeListener implements ChangeListener<Person>{

	public void changed(ObservableValue<? extends Person> observable, Person oldValue, Person newValue) {
		handleNewValue(newValue);
		
	}

	public abstract void handleNewValue(Person newValue);

}
