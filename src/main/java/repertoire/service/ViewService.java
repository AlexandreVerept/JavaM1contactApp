package repertoire.service;

import java.io.IOException;
import javafx.fxml.FXMLLoader;
import repertoire.RepertoireApp;

/**
 * @authors Gabriel Desmullier, Daniel Gheyssens, Alexandre Verept
 */
public class ViewService {

	/**
	 * @param <T>
	 * @param id
	 * @return a View of any type (Text, ...) from its id
	 */
	public static <T> T getView(String id) {
		return getLoader(id).getRoot();
	}

	/**
	 * @param id
	 * @return a Loader FXML from the id of a view
	 */
	private static FXMLLoader getLoader(String id) {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(RepertoireApp.class.getResource("view/" + id + ".fxml"));
			loader.load();
			return loader;
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}

	}
}
