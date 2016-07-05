package mainpackagenlp;

import java.util.Scanner;

import chat.ChatController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import shops.Inventory;
import shops.Product;
import shops.Questionnaire;

public class MainClass extends Application{
	

	public static void main(String[] args) {		
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		// TODO Auto-generated method stub
		ChatController.myStage=primaryStage;
		FXMLLoader loader = new FXMLLoader(getClass().getResource("chat_view.fxml"));
		Parent root = (Parent) loader.load();
		Scene scene = new Scene(root, 300, 360);
		primaryStage.setTitle("Internet Shop");
		primaryStage.setScene(scene);
		primaryStage.show();
		primaryStage.setResizable(false);

	}
	@Override
	public void stop()
	{
		
	}
}
