package repertoire.entities;

import java.time.LocalDate;

public class Person {
	private int idPerson;
	private String lastName;
	private String firstName;
	private String nickName;
	private String phoneNumber;
	private String address;
	private String eMailAddress;
	private LocalDate birthDate;
	
	public Person(int idPerson, String lastName, String firstName, String nickName, String phoneNumber, String address,String eMailAddress, LocalDate birthDate) {
		super();
		this.idPerson = idPerson;
		this.lastName = lastName;
		this.firstName = firstName;
		this.nickName = nickName;
		this.phoneNumber = phoneNumber;
		this.address = address;
		this.eMailAddress = eMailAddress;
		this.birthDate = birthDate;
	}

	public int getIdPerson() {
		return idPerson;
	}

	public void setIdPerson(int idPerson) {
		this.idPerson = idPerson;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String geteMailAddress() {
		return eMailAddress;
	}

	public void seteMailAddress(String eMailAddress) {
		this.eMailAddress = eMailAddress;
	}

	public LocalDate getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(LocalDate birthDate) {
		this.birthDate = birthDate;
	}
	
	
	

}	


