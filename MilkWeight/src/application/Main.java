/**
 * 
 */
package application;

import java.util.List;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

/**
 * @author 72465
 *
 */
public class Main extends Application {
	private List<String> args;

	private static final int WINDOW_WIDTH = 1000;
	private static final int WINDOW_HEIGHT = 600;
	private static final String APP_TITLE = "Milk Weight";
	@Override
	public void start(Stage primaryStage) throws Exception {
		// TODO Auto-generated method stub

		// save args example
		args = this.getParameters().getRaw();

		// Create a vertical box with Hello labels for each args
		VBox vbox = new VBox();
		
		// Creates a canvas that can draw shapes and text
		Canvas canvas = new Canvas(WINDOW_WIDTH, WINDOW_HEIGHT);
		GraphicsContext gc = canvas.getGraphicsContext2D();
		// Write some text
		// Text is filled with the fill color
		gc.setFill(Color.GREEN);
		gc.setFont(new Font(30));
		gc.fillText("Hello World!", 70, 170);
		// Draw a line
		// Lines use the stroke color
		gc.setStroke(Color.BLUE);
		gc.setLineWidth(2);
		gc.strokeLine(40, 100, 250, 50);
		// Draw a few circles
		gc.setFill(Color.BLACK);
		// The circles draw from the top left, so to center them, subtract the radius from each coordinate
		gc.fillOval(40-15, 100-15, 30, 30);
		gc.setFill(Color.RED);
		gc.fillOval(250-15, 50-15, 30, 30);

		vbox.getChildren().add(canvas);

		// Main layout is Border Pane example (top,left,center,right,bottom)
		BorderPane root = new BorderPane();
		HBox topBox = this.addHBox();
		root.setTop(topBox);
		VBox leftBox = new VBox();
		 leftBox.setPadding(new Insets(15, 12, 15, 12));
		 leftBox.setSpacing(10);

		    Button buttonF = new Button("Farm Report");
		    buttonF.setPrefSize(150, 20);
		    
		    Button buttonM = new Button("Monthly Report");
		    buttonM.setPrefSize(150, 20);
		    
		    leftBox.getChildren().addAll(buttonF, buttonM);
		    leftBox.setAlignment(Pos.CENTER);
        
        VBox rightBox = new VBox();
		 rightBox.setPadding(new Insets(15, 12, 15, 12));
		 rightBox.setSpacing(10);

		    Button buttonA = new Button("Annual Report");
		    buttonA.setPrefSize(150, 20);
		    
		    Button buttonD = new Button("Date Range Report");
		    buttonD.setPrefSize(150, 20);
		    
		    rightBox.getChildren().addAll(buttonA, buttonD);
		    rightBox.setAlignment(Pos.CENTER);
		    HBox center = new HBox();
		    center.setPadding(new Insets(15, 12, 15, 12));
		    center.setSpacing(10);
		    center.getChildren().addAll(leftBox, rightBox);
		    center.setAlignment(Pos.CENTER);
		    VBox top = new VBox();
		    top.setPadding(new Insets(15, 12, 15, 12));
			top.setSpacing(10);
			top.getChildren().addAll(topBox, center);
			
			top.setAlignment(Pos.CENTER);
			root.setTop(top);

		Scene mainScene = new Scene(root, WINDOW_WIDTH, WINDOW_HEIGHT);

		
		// Add the stuff and set the primary stage
		primaryStage.setTitle(APP_TITLE);
		primaryStage.setScene(mainScene);
		primaryStage.show();
	
		VBox centerContainer=new VBox();
		Label textOutputPrompt=new Label("Please enter your desired farm's ID below:");
		textOutputPrompt.setMinSize(100, 65);
		centerContainer.getChildren().add(textOutputPrompt);
		
		TextField farmIDInput=new TextField("Enter farm ID.");
		farmIDInput.setMaxWidth(200);
		centerContainer.getChildren().add(farmIDInput);
		
		Label dataTable=new Label("Date table");
		dataTable.setMinSize(500, 200);
		dataTable.setAlignment(Pos.CENTER);
		dataTable.setStyle("-fx-border-color: black;");
		centerContainer.getChildren().add(dataTable);
		
		TextField filePath=new TextField("File path here.");
		filePath.setMaxWidth(100);
		centerContainer.getChildren().add(filePath);
		
		centerContainer.setAlignment(Pos.CENTER);
		centerContainer.setSpacing(10);
		root.setCenter(centerContainer);
		
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		launch(args);
	}
	public HBox addHBox() {
	    HBox hbox = new HBox();
	    hbox.setPadding(new Insets(15, 12, 15, 12));
	    hbox.setSpacing(10);

	    Label label1 = new Label("File Path:");
	    TextField textField = new TextField ();
	    textField.setPrefSize(500, 20);
	    HBox hb = new HBox();

	    Button button = new Button("Enter");
	    button.setPrefSize(100, 20);
	    hbox.getChildren().addAll(label1, textField, button);
	    hbox.setAlignment(Pos.CENTER);
	    return hbox;
	}
}
