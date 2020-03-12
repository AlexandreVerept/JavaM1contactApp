package repertoire.io;
import static org.assertj.core.api.Assertions.assertThat;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;

import org.junit.Test;
import repertoire.entities.Person;

public class ImportTestCase {

	  //test si fichier est correcte
		@Test
		public void shouldBeCorrect() throws IOException {
			Import testImport=new Import("src\\main\\resources\\repertoire\\test\\Vcard\\1945DeGaulleCharles.vcard");
			assertThat(testImport.checkFile()).isEqualTo(true);
		}

		//test si fichier est incorrecte pour cause de non existance
		@Test
		public void shouldNotExist() throws IOException {
			Import testImport=new Import("src\\main\\resources\\repertoire\\test\\Vcard\\42AlbertEinstein.vcard");
			assertThat(testImport.checkFile()).isEqualTo(false);
		}
			
		//test si fichier est incorrecte pour cause de mauvaise extension
		@Test
		public void shouldNotBeTheGoodExtension() throws IOException {
			Import testImport=new Import("src\\main\\resources\\repertoire\\test\\Vcard\\1945DeGaulleCharles.txt");
			assertThat(testImport.checkFile()).isEqualTo(false);
		}
				
		//test si fichier est incorrecte pour cause chemin de répertoire
		@Test
		public void shouldNotBeADirectory() throws IOException {
			Import testImport=new Import("src\\main\\resources\\repertoire\\test\\Vcard");
			assertThat(testImport.checkFile()).isEqualTo(false);
		}
		
		//test bonne Lecture du fichier
		@Test
		public void shouldGiveGoodInformation() throws IOException{
			Import testImport=new Import("src\\main\\resources\\repertoire\\test\\Vcard\\1945DeGaulleCharles.vcard");
			Person testPerson=testImport.importfromVcard();
			assertThat(testPerson.getFirstName()).isEqualTo("Charles");
			assertThat(testPerson.getLastName()).isEqualTo("DeGaulle");
			assertThat(testPerson.getNickName()).isEqualTo("Charly Frenchy");
			assertThat(testPerson.getAddress()).isEqualTo("1 rue du Général-de-Gaulle 52330 Colombey-les-deux-eglises");
			assertThat(testPerson.getPhoneNumber()).isEqualTo("3630");
			assertThat(testPerson.geteMailAddress()).isEqualTo("FranceLibre@gmail.com");
			assertThat(testPerson.getBirthDate()).isEqualTo(LocalDate.parse("1890-11-22"));
		
		}
}
