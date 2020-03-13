package repertoire.io;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import repertoire.entities.Person;

public class ImportTestCase {
	
	    @Before
	    public void initfiles() throws IOException {
	    	Path newPath= Paths.get("src\\main\\resources\\repertoire\\test\\Vcard\\");
	    	List<String> newList=new ArrayList();
	    	newList.add("BEGIN:VCARD");
	    	newList.add("VERSION:2.1");
	    	newList.add("N:DeGaulle;Charles;Charly Frenchy");
	    	newList.add("TEL:TYPE=cell:3630");
	    	newList.add("ADR:TYPE=home:1 rue du Général-de-Gaulle 52330 Colombey-les-deux-eglises");
	    	newList.add("EMAIL:FranceLibre@gmail.com");
	    	newList.add("ANNIVERSARY:1890-11-22");
	    	newList.add("END:VCARD");
	    	Path filePath=newPath.resolve("1945DeGaulleCharles.vcard");
	    	if(Files.notExists(filePath)) {
	    	Files.createFile(newPath.resolve("1945DeGaulleCharles.vcard"));
	    	Files.write(newPath.resolve("1945DeGaulleCharles.vcard"),newList,StandardCharsets.UTF_8);}
	    	
	    	filePath=newPath.resolve("1945DeGaulleCharles.txt");
	    	if(Files.notExists(filePath)) {
		    	Files.createFile(filePath);}
	    	
	    }
	

	@Before
	public void initfiles() {
		
	}

	  //test si fichier est correcte
	@Test
	public void shouldBeCorrect() throws IOException {
		Import testImport = new Import("src\\main\\resources\\repertoire\\test\\Vcard\\1945DeGaulleCharles.vcard");
		assertThat(testImport.checkFile()).isEqualTo(true);
	}

	// test si fichier est incorrecte pour cause de non existance
	@Test
	public void shouldNotExist() throws IOException {
		Import testImport = new Import("src\\main\\resources\\repertoire\\test\\Vcard\\42AlbertEinstein.vcard");
		assertThat(testImport.checkFile()).isEqualTo(false);
	}

	// test si fichier est incorrecte pour cause de mauvaise extension
	@Test
	public void shouldNotBeTheGoodExtension() throws IOException {
		Import testImport = new Import("src\\main\\resources\\repertoire\\test\\Vcard\\1945DeGaulleCharles.txt");
		assertThat(testImport.checkFile()).isEqualTo(false);
	}

	// test si fichier est incorrecte pour cause chemin de répertoire
	@Test
	public void shouldNotBeADirectory() throws IOException {
		Import testImport = new Import("src\\main\\resources\\repertoire\\test\\Vcard");
		assertThat(testImport.checkFile()).isEqualTo(false);
	}

	// test bonne Lecture du fichier
	@Test
	public void shouldGiveGoodInformation() throws IOException {
		Import testImport = new Import("src\\main\\resources\\repertoire\\test\\Vcard\\1945DeGaulleCharles.vcard");
		Person testPerson = testImport.importfromVcard();
		assertThat(testPerson.getFirstName()).isEqualTo("Charles");
		assertThat(testPerson.getLastName()).isEqualTo("DeGaulle");
		assertThat(testPerson.getNickName()).isEqualTo("Charly Frenchy");
		assertThat(testPerson.getAddress()).isEqualTo("1 rue du Général-de-Gaulle 52330 Colombey-les-deux-eglises");
		assertThat(testPerson.getPhoneNumber()).isEqualTo("3630");
		assertThat(testPerson.geteMailAddress()).isEqualTo("FranceLibre@gmail.com");
		assertThat(testPerson.getBirthDate()).isEqualTo(LocalDate.parse("1890-11-22"));

	}
}
