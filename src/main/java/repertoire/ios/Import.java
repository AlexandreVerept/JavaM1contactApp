
package repertoire.ios;

import java.io.IOException;

import repertoire.entities.Person;

/**
 * @authors Gabriel Desmullier, Daniel Gheyssens, Alexandre Verept
 */
public class Import {

	private RootManager importRoot;

	/**
	 * 
	 */
	public Import() {
		// TODO Auto-generated constructor stub
	}

	public Import(String path) throws IOException {
		this.importRoot = new RootManager(path);
	}

	public Person importfromVcard() {
		Person person = new Person();
		return person;

	}

}
