package application;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class GestioneCommesse extends Application{

	@Override
	public void start(Stage primaryStage) {
		
		try {
			Parent root = FXMLLoader.load(getClass().getResource("/resources/OverviewPage.fxml"));
			Scene scene = new Scene(root);
			scene.getStylesheets().add(getClass().getResource("/resources/application.css").toExternalForm());
			OverviewController overView = new OverviewController();
			primaryStage.getIcons().add(overView.getIcon());
			primaryStage.setTitle("Overview");
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	@Override
    public void stop() {
        OverviewController overview = new OverviewController();
        if (overview.getProgettiTotali() != null) overview.salvaProgettiInJson();
    }
	
	public static void main(String[] args) {
		launch(args);
	}
}
