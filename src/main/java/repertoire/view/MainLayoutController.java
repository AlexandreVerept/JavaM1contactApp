package repertoire.view;

import javafx.scene.Node;
import repertoire.service.StageService;
import repertoire.service.ViewService;

/**
 * @authors Gabriel Desmullier, Daniel Gheyssens, Alexandre Verept
 */

public class MainLayoutController {

	/**
	 * Handle the button that close the application
	 * @throws Exception
	 */
	public void closeApplication() {
		StageService.closeStage();
	}

	/**
	 * Handle the button that go back to the menu
	 * @throws Exception
	 */
	public void gotoHome() {
		StageService.showView((Node) ViewService.getView("HomeScreen"));
	}
}
