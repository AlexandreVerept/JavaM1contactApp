package repertoire.view;

import repertoire.service.StageService;
import repertoire.service.ViewService;

public class MainLayoutController {


	public void closeApplication() {
		StageService.closeStage();
	}

	public void gotoHome() {
		StageService.showView(ViewService.getView("HomeScreen"));
	}

	public void gotoQuestionAdmin() {
		StageService.showView(ViewService.getView("QuestionsAdmin"));
	}

}
