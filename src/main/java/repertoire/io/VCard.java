package repertoire.io;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import repertoire.entities.Person;

/**
 * @authors Gabriel Desmullier, Daniel Gheyssens, Alexandre Verept
 */
public class VCard {

	private String BEGIN = "BEGIN:VCARD";
	private String VERSION = "VERSION:2.1";
	private String n;
	private String tel;
	private String adr;
	private String email;
	private String anniversary;
	private String END = "END:VCARD";

	/**
	 * 
	 */
	public VCard(Person person) {
		// Creation des lignes d'un modele Vcard pour une persone
		this.n = "N:" + person.getLastName() + ";" + person.getFirstName() + ";" + person.getNickName();
		this.tel = "TEL:TYPE=cell:" + person.getPhoneNumber();
		this.adr = "ADR:TYPE=home:" + person.getAddress();
		this.email = "EMAIL:" + person.geteMailAddress();
		this.anniversary = "ANNIVERSARY:" + person.getBirthDate().toString();
	}

	public VCard(List<String> lines) {
		// transposition de l'information de string en format Vcard

		// mise en place d'un iterator pour parcourir toutes les lignes
		Iterator<String> iterator = lines.iterator();
		while (iterator.hasNext()) {
			String element = iterator.next();

			// decoupage de la ligne pour comparer les premiers caractères
			String[] lineDecomposition = element.split(":");

			// correspondance de la ligne
			switch (lineDecomposition[0]) {
			case "N":
				this.n = element; // enregistrement de l'information
				break;
			case "TEL":
				this.tel = element;
				break;
			case "ADR":
				this.adr = element;
				break;
			case "EMAIL":
				this.email = element;
				break;
			case "ANNIVERSARY":
				this.anniversary = element;
				break;
			}

		}
	}

	public Person toPerson() {
		// transformer un Vcard en person
		Person newPerson = new Person();

		// decomposition du nom
		String[] stringDecomposition = this.n.split(";|:");
		if (stringDecomposition.length >= 2) {
			newPerson.setLastName(stringDecomposition[1]);
			if (stringDecomposition.length >= 3) {
				newPerson.setFirstName(stringDecomposition[2]);
				if (stringDecomposition.length >= 4) {
					newPerson.setNickName(stringDecomposition[3]);
				}
			}
		}

		// recuperation du numero de telephone
		stringDecomposition = this.tel.split(":");
		if (stringDecomposition.length >= 3) {
			newPerson.setPhoneNumber(stringDecomposition[2]);
		}

		// recuperation adresse
		stringDecomposition = this.adr.split(":");
		if (stringDecomposition.length >= 3) {
			newPerson.setAddress(stringDecomposition[2]);
		}

		// recuperation email
		stringDecomposition = this.email.split(":");
		if (stringDecomposition.length >= 2) {
			newPerson.seteMailAddress(stringDecomposition[1]);
		}

		// recuperation birthdate
		stringDecomposition = this.anniversary.split(":");
		if (stringDecomposition.length >= 2) {
			// on parse la date de naissance en localdate, l'opération peut être risqué d'où
			// une gestion d'exception
			try {
				newPerson.setBirthDate(LocalDate.parse(stringDecomposition[1]));
			} catch (DateTimeParseException e) {
				System.err.println("Unable to parse the birthdate!");
				newPerson.setBirthDate(null);// dans le cas où c'est un echec on préferera mettre la date de naissance à
												// null
			}
		}

		return newPerson;
	}

	public List<String> toPack() {
		// cree une liste de string a partir de la vcard
		List<String> vcardString = new ArrayList<>();
		vcardString.add(this.BEGIN);
		vcardString.add(this.VERSION);
		vcardString.add(this.n);
		vcardString.add(this.tel);
		vcardString.add(this.adr);
		vcardString.add(this.email);
		vcardString.add(this.anniversary);
		vcardString.add(this.END);
		return vcardString;
	}

}
