package repertoire;

import javafx.application.Application;
import javafx.scene.Node;
import javafx.stage.Stage;
import repertoire.service.StageService;
import repertoire.service.ViewService;

public class RepertoireApp extends Application {

	public RepertoireApp() {
		
	}
	
	public static void main(String[] args) {
		launch(args);

	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		StageService.initPrimaryStage(primaryStage);
		StageService.showView((Node) ViewService.getView("HomeScreen"));
		
	}

}
