package repertoire.view;





import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import repertoire.entities.Person;
import repertoire.service.PersonService;
import repertoire.service.StageService;
import repertoire.service.ViewService;
import repertoire.utils.PersonChangeListener;
import repertoire.utils.PersonValueFactory;

public class RepertoireListController {
	
	@FXML
	private TableView<Person> personsTable;
	
	@FXML
	private TableColumn<Person,String> personColumn;
	
	@FXML
	private AnchorPane formPane;
	
	@FXML
	private TextField Lastname;
	
	@FXML
	private TextField Nickname;
	
	@FXML
	private TextField Firstname;
	
	@FXML
	private TextField Adresse;
	
	@FXML
	private TextField Mail;
	
	@FXML
	private TextField Phone;
	
	@FXML
	private TextField Birthday;
	
	private Person currentPerson;

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
		this.personsTable.getSelectionModel().selectedItemProperty().addListener(
				new PersonChangeListener() {
					@Override
					public void handleNewValue(Person newValue) {
						RepertoireListController.this.showPersonDetail(newValue);
					}
				}
		);
		this.resetView();
	}
	
	private void resetView() {
		this.showPersonDetail(null);
		this.refreshList();
	}
	
	private void showPersonDetail(Person person) {
		if(person==null) {
			formPane.setVisible(true);
			this.Lastname.setText(null);
			this.Firstname.setText(null);
			this.Nickname.setText(null);
			this.Adresse.setText(null);
			this.Phone.setText(null);
			this.Mail.setText(null);
			this.Birthday.setText(null);
		}
		else {
			formPane.setVisible(true);
			this.currentPerson=person;
			this.Lastname.setText(this.currentPerson.getLastName());
			this.Firstname.setText(this.currentPerson.getFirstName());
			this.Nickname.setText(this.currentPerson.getNickName());
			this.Adresse.setText(this.currentPerson.getAddress());
			this.Phone.setText(this.currentPerson.getPhoneNumber());
			this.Mail.setText(this.currentPerson.geteMailAddress());
			this.Birthday.setText(this.currentPerson.getBirthDateString());
		}
	}
	
	@FXML
	public void handleModifieButton() throws Exception {
		int selectedIndex = personsTable.getSelectionModel().getSelectedIndex();
		if(selectedIndex>=0) {
			//ton code BDD pour la modification sachant que les info sont contenues dans le current person
			this.refreshList();
		}
	}
	
	@FXML
	public void handleClearButton() throws Exception{
		this.resetView();
	}
	
	@FXML
	public void handleResearchButton() throws Exception {
		//code BDD pour la recherche
		if(this.currentPerson==null) {
			formPane.setVisible(true);
		}
		else {
			this.Lastname.setText(this.currentPerson.getLastName());
			this.Firstname.setText(this.currentPerson.getFirstName());
			this.Nickname.setText(this.currentPerson.getNickName());
			this.Adresse.setText(this.currentPerson.getAddress());
			this.Phone.setText(this.currentPerson.getPhoneNumber());
			this.Mail.setText(this.currentPerson.geteMailAddress());
			this.Birthday.setText(this.currentPerson.getBirthDateString());
		}
	}
	
	@FXML
	public void handleDeleteButton() throws Exception {
		int selectedIndex = personsTable.getSelectionModel().getSelectedIndex();
		if(selectedIndex>=0) {
			//ton code BDD pour le delete
			this.resetView();
		}
	}
}
