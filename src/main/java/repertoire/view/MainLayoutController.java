package repertoire.view;

import javafx.scene.Node;
import repertoire.service.StageService;
import repertoire.service.ViewService;

public class MainLayoutController {
	
	public void closeApplication() {
		StageService.closeStage();
	}

	public void gotoHome() {
		StageService.showView((Node) ViewService.getView("HomeScreen"));
	}
}
