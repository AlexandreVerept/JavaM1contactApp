package repertoire.view;

import java.time.LocalDate;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.TextField;
import repertoire.service.StageService;
import repertoire.service.ViewService;
import repertoire.dao.PersonDao;
import repertoire.entities.Person;

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
	
	private Person currentPerson = new Person();
	
	@FXML
	public void handleAddButton() throws Exception {
		System.out.println("ctrl1");
		this.currentPerson.setLastName(this.lastname.getText());
		System.out.println("ctrl2");
		this.currentPerson.setFirstName(this.firstname.getText());
		System.out.println("ctrl3");
		this.currentPerson.setNickName(this.nickname.getText());
		System.out.println("ctrl4");
		this.currentPerson.setAddress(this.adresse.getText());
		System.out.println("ctrl5");
		this.currentPerson.seteMailAddress(this.mail.getText());
		System.out.println("ctrl6");
		this.currentPerson.setPhoneNumber(this.phone.getText());
		System.out.println("ctrl7");
		this.currentPerson.setBirthDate(LocalDate.parse(this.birthday.getText()));
		System.out.println("ctrl8");
		
		// Get in the BDD:
		PersonDao personDao = new PersonDao();
		this.currentPerson = personDao.addPerson(this.currentPerson);
		
		StageService.showView((Node) ViewService.getView("RepertoireList"));
	}

	@FXML
	public void handleReturnButton() throws Exception {
		StageService.showView((Node) ViewService.getView("RepertoireList"));
	}
}
