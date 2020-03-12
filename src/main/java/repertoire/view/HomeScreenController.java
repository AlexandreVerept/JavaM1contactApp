package repertoire.view;

import javafx.fxml.FXML;
import javafx.scene.Node;
import repertoire.service.StageService;
import repertoire.service.ViewService;

/**
 * @authors Gabriel Desmullier, Daniel Gheyssens, Alexandre Verept
 */

public class HomeScreenController {

	/**
	 * Handdle the button that go to the repertoire 
	 * @throws Exception
	 */
	@FXML
	public void handleShowButton() throws Exception {
		StageService.showView((Node) ViewService.getView("RepertoireList"));
	}
	
	/**
	 * Handle the button that add a person to the app in importing the content of the textfields
	 * @throws Exception
	 */
	@FXML
	public void handleAddButton() throws Exception {
		StageService.showView((Node) ViewService.getView("AddRepertoire"));
	}
}
