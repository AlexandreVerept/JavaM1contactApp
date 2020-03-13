package repertoire.view;

import java.time.LocalDate;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.TextField;
import repertoire.service.StageService;
import repertoire.service.ViewService;
import repertoire.dao.PersonDao;
import repertoire.entities.Person;
import repertoire.io.Import;

/**
 * @authors Gabriel Desmullier, Daniel Gheyssens, Alexandre Verept
 */

public class AddRepertoireController {

	@FXML
	private TextField lastname;

	@FXML
	private TextField firstname;

	@FXML
	private TextField nickname;

	@FXML
	private TextField adresse;

	@FXML
	private TextField mail;

	@FXML
	private TextField phone;

	@FXML
	private TextField birthday;

	@FXML
	private TextField url;

	private Person currentPerson = new Person();
	private String addurl;

	/**
	 * Handle the button that add a person to the app in importing the content of
	 * the textfields
	 * 
	 * @throws Exception
	 */
	@FXML
	public void handleAddButton() throws Exception {
		this.currentPerson.setLastName(this.lastname.getText());
		this.currentPerson.setFirstName(this.firstname.getText());
		this.currentPerson.setNickName(this.nickname.getText());
		this.currentPerson.setAddress(this.adresse.getText());
		this.currentPerson.seteMailAddress(this.mail.getText());
		this.currentPerson.setPhoneNumber(this.phone.getText());
		if (!this.birthday.getText().equals("")) {
			this.currentPerson.setBirthDate(LocalDate.parse(this.birthday.getText()));
		}

		PersonDao personDao = new PersonDao();
		this.currentPerson = personDao.addPerson(this.currentPerson);

		StageService.showView((Node) ViewService.getView("RepertoireList"));
	}

	/**
	 * Handle the button that go to the repertoir list
	 * 
	 * @throws Exception
	 */
	@FXML
	public void handleReturnButton() throws Exception {
		StageService.showView((Node) ViewService.getView("RepertoireList"));
	}

	/**
	 * Handle the URL button to import the URL typed into the UI
	 * 
	 * @throws Exception
	 */
	@FXML
	public void handleUrlButton() throws Exception {
		addurl = this.url.getText();
		Import newImport = new Import(addurl);// lien vers l'import
		if (newImport.checkFile() == true) {// verification du chemin
			this.currentPerson = newImport.importfromVcard();// import

			this.lastname.setText(this.currentPerson.getLastName());
			this.firstname.setText(this.currentPerson.getFirstName());
			this.nickname.setText(this.currentPerson.getNickName());
			this.phone.setText(this.currentPerson.getPhoneNumber());
			this.adresse.setText(this.currentPerson.getAddress());
			this.mail.setText(this.currentPerson.geteMailAddress());
			this.birthday.setText(this.currentPerson.getBirthDateString());
		} else {
			System.out.println("impossible operation");
		}

	}
}
