package repertoire.ios;

import java.io.IOException;

import repertoire.entities.Person;

/**
 * @authors Gabriel Desmullier, Daniel Gheyssens, Alexandre Verept
 */
public class Export {

	private RootManager exportRoot;

	/**
	 * 
	 */
	public Export() {
		// TODO Auto-generated constructor stub
	}

	public Export(String path) throws IOException {
		this.exportRoot = new RootManager(path);

	}

	public void exportToVcard(Person person) {

	}
}
