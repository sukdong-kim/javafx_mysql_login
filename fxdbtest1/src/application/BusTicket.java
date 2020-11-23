package application;
import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
public class BusTicket  extends Application  {
	private static Stage primaryStage;
	@Override
	public void start(Stage primaryStage) {
		try {
			Parent root = FXMLLoader.load(getClass().getResource("ViewTicket.fxml"));
			Scene scene = new Scene(root,1100,500);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	public static void showCheckStage() throws IOException {
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(Main.class.getResource("ViewCheck.fxml"));
		AnchorPane checkView = loader.load();

		Stage checkDialogStage = new Stage();
		checkDialogStage.setTitle("Check Seat");
		checkDialogStage.initModality(Modality.WINDOW_MODAL);
		checkDialogStage.initOwner(primaryStage);
		Scene scene = new Scene(checkView);
		checkDialogStage.setScene(scene);
		checkDialogStage.showAndWait();

	}
	public static void main(String[] args) {
		launch(args);
	}
}

