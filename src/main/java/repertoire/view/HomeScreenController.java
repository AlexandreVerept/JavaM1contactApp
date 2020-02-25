package repertoire.view;

import javafx.fxml.FXML;
import repertoire.service.StageService;
import repertoire.service.ViewService;

public class HomeScreenController {

	@FXML
	public void handleLaunchButton() throws Exception {
		StageService.showView(ViewService.getView("QuizOverview"));
	}

}