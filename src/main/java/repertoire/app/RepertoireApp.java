package repertoire.app;

import javafx.application.Application;
import javafx.stage.Stage;
import repertoire.service.StageService;
import repertoire.service.ViewService;

/**
 * @authors Gabriel Desmullier, Daniel Gheyssens, Alexandre Verept
 *
 */
public class RepertoireApp extends Application{
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		launch(args);

	}
	
	public RepertoireApp() {
		
	}

	@Override
	public void start(Stage primaryStage) {
		StageService.initPrimaryStage(primaryStage);
		StageService.showView(ViewService.getView("HomeScreen"));
	}

}
