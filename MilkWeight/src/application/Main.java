/**
 * 
 */
package application;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * @author 72465
 *
 */
public class Main extends Application {
	
	private static final int WINDOW_WIDTH = 800;
	private static final int WINDOW_HEIGHT = 640;
	private static final String APP_TITLE = "Hello World!";
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		// TODO Auto-generated method stub
		BorderPane root = new BorderPane();
		VBox centerContainer=new VBox();
		Label textOutputPrompt=new Label("Please enter your desired farm's ID below:");
		textOutputPrompt.setMinSize(100, 65);
		centerContainer.getChildren().add(textOutputPrompt);
		
		TextField farmIDInput=new TextField("Enter farm ID.");
		farmIDInput.setMaxWidth(200);
		centerContainer.getChildren().add(farmIDInput);
		
		Label dataTable=new Label("Date table");
		dataTable.setMinSize(100, 200);
		dataTable.setAlignment(Pos.CENTER);
		centerContainer.getChildren().add(dataTable);
		
		TextField filePath=new TextField("File path here.");
		filePath.setMaxWidth(100);
		centerContainer.getChildren().add(filePath);
		
		centerContainer.setAlignment(Pos.CENTER);
		root.setCenter(centerContainer);
		
		Scene mainScene=new Scene(root,WINDOW_WIDTH, WINDOW_HEIGHT);
		
		primaryStage.setTitle(APP_TITLE);
		primaryStage.setScene(mainScene);
		primaryStage.show();
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		launch(args);
	}

}
