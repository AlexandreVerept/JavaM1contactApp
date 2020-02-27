package repertoire.view;

import java.time.LocalDate;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.TextField;
import repertoire.service.StageService;
import repertoire.service.ViewService;
import repertoire.entities.Person;

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
		this.currentPerson.setLastName(this.lastname.getText());
		this.currentPerson.setFirstName(this.firstname.getText());
		this.currentPerson.setNickName(this.nickname.getText());
		this.currentPerson.setAddress(this.adresse.getText());
		this.currentPerson.seteMailAddress(this.mail.getText());
		this.currentPerson.setPhoneNumber(this.phone.getText());
		//this.currentPerson.setBirthDate(LocalDate.parse(this.birthday.getText()));
		//Ton code pour le ADD
		StageService.showView((Node) ViewService.getView("RepertoireList"));
	}
	
	@FXML
	public void handleReturnButton() throws Exception {
		StageService.showView((Node) ViewService.getView("RepertoireList"));
	}
}
