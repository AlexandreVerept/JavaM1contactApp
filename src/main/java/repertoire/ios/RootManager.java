/**
 * 
 */
package repertoire.ios;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * @authors Gabriel Desmullier, Daniel Gheyssens, Alexandre Verept
 */
public class RootManager {

	private Path root;
	private String extension;

	public RootManager() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param root
	 * @throws IOException
	 * 
	 */
	public RootManager(String root) throws IOException {
		this.root = Paths.get(root);
		if (Files.notExists(this.root)) {
			Files.createDirectory(this.root);
		}
		this.extension = ".vcard";
	}

	private boolean fileAlreadyExist(String fileName) {
		Path file = this.root.resolve(fileName + this.extension);
		return Files.exists(file);
	}

}
