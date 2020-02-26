package repertoire.view;

import javafx.fxml.FXML;
import javafx.scene.Node;
import repertoire.service.StageService;
import repertoire.service.ViewService;

public class HomeScreenController {

	
	@FXML
	public void handleShowButton() throws Exception {
		StageService.showView((Node) ViewService.getView("RepertoireList"));
	}
	
	@FXML
	public void handleAddButton() throws Exception {
		StageService.showView((Node) ViewService.getView("AddRepertoire"));
	}
}
