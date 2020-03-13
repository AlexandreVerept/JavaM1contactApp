package repertoire.io;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.SQLException;
import java.time.LocalDate;

import org.junit.Before;
import org.junit.Test;
import repertoire.entities.Person;

/**
 * @authors Gabriel Desmullier, Daniel Gheyssens, Alexandre Verept
 */

public class ExportTestCase {

	/**
	 * This methods is used to clear the test folder before each and every test.
	 */
	@Before
	public void deleteVcardIfAlreadyExists() {
		Path filePath = Paths.get("src\\main\\resources\\repertoire\\test\\Vcard");
		File file = new File(filePath.toString());
		if (file.exists() && !file.isDirectory()) {
			file.delete();
		}
	}

	/**
	 * test if the file is created
	 */
	@Test
	public void shouldAddVcard() throws IOException {
		Export testExport = new Export("src\\main\\resources\\repertoire\\test\\Vcard");
		Person testPerson = new Person(565, "Churchill", "Winston", "WC", "3640", "5th BakerStreet London",
				"winston59@gmail.com", LocalDate.now());
		testExport.exportToVcard(testPerson);
		Path testPath = Paths.get("src\\main\\resources\\repertoire\\test\\Vcard");
		assertThat(Files.exists(testPath)).isEqualTo(true);
	}

}
