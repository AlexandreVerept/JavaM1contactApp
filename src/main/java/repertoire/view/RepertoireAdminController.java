package repertoire.view;

import javafx.fxml.FXML;
import javafx.scene.Node;
import repertoire.service.StageService;
import repertoire.service.ViewService;

/**
 * @authors Gabriel Desmullier, Daniel Gheyssens, Alexandre Verept
 */

public class RepertoireAdminController {

	@FXML
	public void handleChangeButton() throws Exception {
		StageService.showView((Node) ViewService.getView("RepertoireList"));
	}

	@FXML
	public void handleDeleteButton() throws Exception {
		StageService.showView((Node) ViewService.getView("RepertoireList"));
	}

	@FXML
	public void handleReturnButton() throws Exception {
		StageService.showView((Node) ViewService.getView("RepertoireList"));
	}
}
