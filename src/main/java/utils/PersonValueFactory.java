package utils;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.util.Callback;
import repertoire.entities.Person;

public class PersonValueFactory implements Callback<TableColumn.CellDataFeatures<Person, String>, ObservableValue<String>> {

	public ObservableValue<String> call(CellDataFeatures<Person, String> cellData) {
		return new SimpleStringProperty(cellData.getValue().getNickName());
	}
}

