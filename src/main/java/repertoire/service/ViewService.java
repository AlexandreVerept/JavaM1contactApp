package repertoire.service;

import java.io.IOException;
import javafx.fxml.FXMLLoader;
import repertoire.app.RepertoireApp;

public class ViewService {
	
	public static <T> T getView(String id) {
		return getLoader(id).getRoot();
	}

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
