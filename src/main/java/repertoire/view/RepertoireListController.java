package repertoire.view;

import java.time.LocalDate;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import repertoire.dao.PersonDao;
import repertoire.entities.Person;
import repertoire.io.Export;
import repertoire.service.PersonService;
import repertoire.utils.PersonChangeListener;
import repertoire.utils.PersonValueFactory;

/**
 * @authors Gabriel Desmullier, Daniel Gheyssens, Alexandre Verept
 */

public class RepertoireListController {

	@FXML
	private TableView<Person> personsTable;

	@FXML
	private TableColumn<Person, String> personColumn;

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
	private boolean verif;

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
		this.personsTable.getSelectionModel().selectedItemProperty().addListener(new PersonChangeListener() {
			@Override
			public void handleNewValue(Person newValue) {
				RepertoireListController.this.showPersonDetail(newValue);
			}
		});
		this.resetView();
	}

	private void resetView() {
		this.showPersonDetail(null);
		this.populateList();
	}

	private void showPersonDetail(Person person) {
		if (person == null) {
			formPane.setVisible(true);
			this.Lastname.setText(null);
			this.Firstname.setText(null);
			this.Nickname.setText(null);
			this.Adresse.setText(null);
			this.Phone.setText(null);
			this.Mail.setText(null);
			this.Birthday.setText(null);
		} else {
			formPane.setVisible(true);
			this.currentPerson = person;
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
			this.currentPerson.setLastName(this.Lastname.getText());
			this.currentPerson.setFirstName(this.Firstname.getText());
			this.currentPerson.setPhoneNumber(this.Phone.getText());
			this.currentPerson.setNickName(this.Nickname.getText());
			this.currentPerson.seteMailAddress(this.Mail.getText());
			this.currentPerson.setAddress(this.Adresse.getText());
			this.currentPerson.setBirthDate(LocalDate.parse(this.Birthday.getText()));
			PersonDao personDao = new PersonDao();
			personDao.updatePerson(this.currentPerson);
			this.populateList();
		}
	}

	@FXML
	public void handleClearButton() throws Exception {
		this.resetView();
	}

	@FXML
	public void handleResearchButton() throws Exception {
		this.prep_recherche();
		if(verif==true) {
			PersonDao personDao = new PersonDao();
			List<Person> listOfPerson = personDao.searchPersons(this.currentPerson.getLastName(),this.currentPerson.getFirstName(),this.currentPerson.getNickName(),this.currentPerson.getPhoneNumber(),this.currentPerson.getAddress(),this.currentPerson.geteMailAddress());
			ObservableList<Person> listresearch= FXCollections.observableArrayList();
			for(int i = 0;i<listOfPerson.size();i++) {
				listresearch.add(listOfPerson.get(i));
			}
			this.personsTable.setItems(listresearch);
			this.refreshList();
		}
	}

	@FXML
	public void handleDeleteButton() throws Exception {
		int selectedIndex = personsTable.getSelectionModel().getSelectedIndex();
		if(selectedIndex>=0) {
			PersonDao personDao = new PersonDao();
			personDao.deletePersonbyID(this.currentPerson.getIdPerson());
			
			this.populateList();
			this.resetView();
		}
	}
	
	public void prep_recherche() {
		this.currentPerson = new Person();
		this.verif=false;
		if(this.Lastname.getText()==null) {
			this.currentPerson.setLastName(null);
		}
		else {
			this.currentPerson.setLastName(this.Lastname.getText());
			verif=true;	
		}
		if(this.Firstname.getText()==null) {
			this.currentPerson.setFirstName(null);
		}
		else{
			this.currentPerson.setFirstName(this.Firstname.getText());
			verif=true;
		}
		if(this.Phone.getText()==null){
			this.currentPerson.setPhoneNumber(null);
		}
		else{
			this.currentPerson.setPhoneNumber(this.Phone.getText());
			verif=true;
		}
		if(this.Nickname.getText()==null) {
			this.currentPerson.setNickName(null);
		}
		else {
			this.currentPerson.setNickName(this.Nickname.getText());
			verif=true;
		}
		if(this.Mail.getText()==null){
			this.currentPerson.seteMailAddress(null);
		}
		else {
			this.currentPerson.seteMailAddress(this.Mail.getText());
			verif=true;
		}
		if(this.Adresse.getText()==null){
			this.currentPerson.setAddress(null);
		}
		else{
			this.currentPerson.setAddress(this.Adresse.getText());
			verif=true;
		}
	}
	
	@FXML
	public void handleUrlexpButton() throws Exception {
		// TODO la person a export est dans currentPerson
		Export newExport=new Export("Comment je fais pour demander l'url à l'utilisateur?");//lien d'exportation vers un répertoire
		if(newExport.checkDirectory()) { //vérification du répertoire
			newExport.exportToVcard(this.currentPerson); //exportation
			}
		
		
		
	}
}
