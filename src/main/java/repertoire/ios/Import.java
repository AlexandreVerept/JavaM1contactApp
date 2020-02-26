/**
 * 
 */
package repertoire.ios;

import java.io.IOException;

import repertoire.entities.Person;


/**
 * @author Gabriel
 *
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
		this.importRoot=new RootManager(path);
	}
		
		
	
	
	public Person importfromVcard() {
		Person person=new Person();
		return person;
		
	}

}
