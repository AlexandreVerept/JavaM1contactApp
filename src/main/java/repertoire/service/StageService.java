package repertoire.service;

import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

/**
 * @authors Gabriel Desmullier, Daniel Gheyssens, Alexandre Verept
 */

public class StageService {

	private StageService() {
		mainLayout = ViewService.getView("MainLayout");

	}

	/**
	 * Method Use to create on holder for the Stage
	 */
	private static class StageServiceHolder {
		private static final StageService INSTANCE = new StageService();
	}

	private Stage primaryStage;

	private BorderPane mainLayout;

	public static Stage getPrimaryStage() {
		return StageServiceHolder.INSTANCE.primaryStage;
	}

	/**
	 * Method used to initialize The Primary Stage
	 * @param primaryStage
	 */
	public static void initPrimaryStage(Stage primaryStage) {
		primaryStage.setTitle("Repertoire App");
		primaryStage.setScene(new Scene(StageServiceHolder.INSTANCE.mainLayout));
		primaryStage.show();

		StageServiceHolder.INSTANCE.primaryStage = primaryStage;
	}

	/**
	 * Method used to show a view in the stage
	 * @param rootElement
	 */
	public static void showView(Node rootElement) {
		StageServiceHolder.INSTANCE.mainLayout.setCenter(rootElement);
	}

	/**
	 * Method used to close a view
	 */
	public static void closeStage() {
		StageServiceHolder.INSTANCE.primaryStage
				.fireEvent(new WindowEvent(StageServiceHolder.INSTANCE.primaryStage, WindowEvent.WINDOW_CLOSE_REQUEST));
	}
}
