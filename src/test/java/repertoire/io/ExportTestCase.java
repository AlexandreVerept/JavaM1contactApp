/**
 * 
 */
package repertoire.io;
import static org.assertj.core.api.Assertions.assertThat;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;

import org.junit.Test;
import repertoire.entities.Person;

/**
 * @author Gabriel
 *
 */
public class ExportTestCase {

	/**
	 * 
	 */

	
    //test si fichier est bien créé
	@Test
	public void shouldAddVcard() throws IOException {
		Export testExport=new Export("src\\main\\resources\\repertoire\\test\\Vcard");
		Person testPerson=new Person(565,"Churchill","Winston","WC","3640","5th BakerStreet London","winston59@gmail.com",LocalDate.now());
		testExport.exportToVcard(testPerson);
		Path testPath=Paths.get("src\\main\\resources\\repertoire\\test\\Vcard");
		assertThat(Files.exists(testPath)).isEqualTo(true);
	}

}
