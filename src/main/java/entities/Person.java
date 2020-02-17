package entities;

/**
 * @authors Gabriel Desmullier, Daniel Gheyssens, Alexandre Verept
 *
 */

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

	public Person() {
		
	}
	
	/**
	 * @param idPerson
	 * @param lastName
	 * @param firstName
	 * @param nickName
	 * @param phoneNumber
	 * @param address
	 * @param eMailAddress
	 * @param birthDate
	 */
	public Person(int idPerson, String lastName, String firstName, String nickName, String phoneNumber, String address,
			String eMailAddress, LocalDate birthDate) {
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

	/**
	 * @return the idPerson
	 */
	public int getIdPerson() {
		return idPerson;
	}

	/**
	 * @param idPerson the idPerson to set
	 */
	public void setIdPerson(int idPerson) {
		this.idPerson = idPerson;
	}

	/**
	 * @return the lastName
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 * @param lastName the lastName to set
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	/**
	 * @return the firstName
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * @param firstName the firstName to set
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	/**
	 * @return the nickName
	 */
	public String getNickName() {
		return nickName;
	}

	/**
	 * @param nickName the nickName to set
	 */
	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	/**
	 * @return the phoneNumber
	 */
	public String getPhoneNumber() {
		return phoneNumber;
	}

	/**
	 * @param phoneNumber the phoneNumber to set
	 */
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	/**
	 * @return the address
	 */
	public String getAddress() {
		return address;
	}

	/**
	 * @param address the address to set
	 */
	public void setAddress(String address) {
		this.address = address;
	}

	/**
	 * @return the eMailAddress
	 */
	public String geteMailAddress() {
		return eMailAddress;
	}

	/**
	 * @param eMailAddress the eMailAddress to set
	 */
	public void seteMailAddress(String eMailAddress) {
		this.eMailAddress = eMailAddress;
	}

	/**
	 * @return the birthDate
	 */
	public LocalDate getBirthDate() {
		return birthDate;
	}

	/**
	 * @param birthDate the birthDate to set
	 */
	public void setBirthDate(LocalDate birthDate) {
		this.birthDate = birthDate;
	}

	@Override
	public String toString() {
		return "person [idPerson=" + idPerson + ", lastName=" + lastName + ", firstName=" + firstName + ", nickName="
				+ nickName + ", phoneNumber=" + phoneNumber + ", address=" + address + ", eMailAddress=" + eMailAddress
				+ ", birthDate=" + birthDate + "]";
	}
	
	
	

}
