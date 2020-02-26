package repertoire.view;




import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;
import repertoire.entities.Person;
import repertoire.service.PersonService;
import repertoire.service.StageService;
import repertoire.service.ViewService;
import repertoire.utils.PersonValueFactory;

public class RepertoireListController {
	
	@FXML
	private TableView<Person> personsTable;
	
	@FXML
	private TableColumn<Person,String> personColumn;
	
	@FXML
	private AnchorPane formPane;

	private void refreshList() {
		this.personsTable.refresh();
		this.personsTable.getSelectionModel().clearSelection();
	}
	
	
	private void populateList() {
		this.personsTable.setItems(PersonService.getPersons());
		this.refreshList();
	}
	
	@FXML
	private void initialize() {
		this.personColumn.setCellValueFactory(new PersonValueFactory());
		this.populateList();
		
	
	}
	
	@FXML
	public void handleModifieButton() throws Exception {
		StageService.showView((Node) ViewService.getView("RepertoireAdmin"));
	}
}
