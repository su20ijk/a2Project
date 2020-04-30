/**
 * 
 */
package application;

import java.io.File;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import Internal.Data;
import Internal.FarmPerMonth;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
/**
 * @author Qikun, Xueshen
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
		Data database = new Data();
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
		
		HBox topBox = new HBox();
	    topBox.setPadding(new Insets(15, 12, 15, 12));
	    topBox.setSpacing(10);

	    Label label1 = new Label("File Path:");
	    TextField textField = new TextField ();
	    textField.setPrefSize(500, 20);
	    HBox hb = new HBox();
	    Button button = new Button("Enter");
	    button.setOnAction(
	    		e -> database.readFile(new File(textField.getText()))
	    		);
	    button.setPrefSize(100, 20);
	    topBox.getChildren().addAll(label1, textField, button);
	    topBox.setAlignment(Pos.CENTER);
		
		
		
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
		
		TextField IDInput=new TextField();
		IDInput.setMinWidth(200);
		IDInput.setPromptText("Enter farm ID.");
		
		Button submitIDInput=new Button("Submit");
		submitIDInput.setPrefSize(150, 20);
		
		HBox IDPart=new HBox();
		IDPart.getChildren().add(IDInput);
		IDPart.getChildren().add(submitIDInput);
		IDPart.setAlignment(Pos.CENTER);
		centerContainer.getChildren().add(IDPart);
		
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
		
		
	    buttonA.setOnAction(
	    		e -> {
    				textOutputPrompt.setText("Please enter year below:");
    				IDInput.setPromptText("Enter format: <Year>");
	    		}
	    		);
	    

	    buttonD.setOnAction(
	    		e -> {
	    				textOutputPrompt.setText("Please enter the initial date and final date below:");
	    				IDInput.setPromptText("Enter format: <Year>,<Initial month>,<Inital day>,<Final month>,<Final day>");
	    		}
	    		);
	    
	    buttonF.setOnAction(
	    		e -> {
	    			textOutputPrompt.setText("Please enter your desired farm id and year below:");
    				IDInput.setPromptText("Enter format: <Farm Id>,<Year>");
	    		}
	    		);
	    buttonM.setOnAction(
	    		e -> {
	    			textOutputPrompt.setText("Please enter your desired month and year below:");
	    			IDInput.setPromptText("Enter format: <Month>,<Year>");
	    		}
	    		);
	    submitIDInput.setOnAction(
	    		e -> {
	    			if(textOutputPrompt.getText().equals("Please enter the initial date and final date below:")) {
	    				int year=Integer.parseInt(IDInput.getText().split(",")[0]);
	    				int sMonth=Integer.parseInt(IDInput.getText().split(",")[1]);
	    				int sDay=Integer.parseInt(IDInput.getText().split(",")[2]);
	    				int eMonth=Integer.parseInt(IDInput.getText().split(",")[3]);
	    				int eDay=Integer.parseInt(IDInput.getText().split(",")[4]);
	    				Map<String, Integer> maps = database.dateRangeReport(year, sMonth, sDay, eMonth, eDay);
	    				double[] percent = database.percentList(maps);
	    				Iterator<Entry<String, Integer>> iter = maps.entrySet().iterator();
	    				int count=0;
	    				String output = "";
	    				while (iter.hasNext())
	    			    {
	    			      Entry<String, Integer> pair = iter.next();
	    			      output = output+"Farm "+pair.getKey()+":          Total Weight: "+pair.getValue()+"          Percentage Weight: "+percent[count];
	    				  output = output+"\n";
	    				  count++;
	    			}
	    				dataTable.setText(output);
	    			}
	    			else if(textOutputPrompt.getText().equals("Please enter year below:")) {
	    				
	    				Map<String, Integer> maps = database.yearReport(Integer.parseInt(IDInput.getText()));
	    				double[] percent = database.percentList(maps);
	    				Iterator<Entry<String, Integer>> iter = maps.entrySet().iterator();
	    				int count=0;
	    				String output = "";
	    				while (iter.hasNext())
	    			    {
	    			      Entry<String, Integer> pair = iter.next();
	    			      output = output+"Farm "+pair.getKey()+":          Total Weight: "+pair.getValue()+"          Percentage Weight: "+percent[count];
	    				  output = output+"\n";
	    				  count++;
	    			}
	    				dataTable.setText(output);
	    			}
	    			else if(textOutputPrompt.getText().equals("Please enter your desired farm id and year below:")) {
	    				String farmId = Integer.parseInt(IDInput.getText().split(",")[0])+"";
	    				int year = Integer.parseInt(IDInput.getText().split(",")[1]);
	    				String output = "";
	    				int[] total = database.farmReport(farmId, year);
	    				double[] percent = database.percentList(total);
	    				for(int i=1; i<=12; i++) {
	    					output = output+"Month "+i+":          Total Weight: "+total[i-1]+"          Percentage Weight: "+percent[i-1];
	    					output = output+"\n";
	    				}
	    				dataTable.setText(output);
	    			}
	    			else if(textOutputPrompt.getText().equals("Please enter your desired month and year below:")) {
	    				int month = Integer.parseInt(IDInput.getText().split(",")[0]);
	    				int year = Integer.parseInt(IDInput.getText().split(",")[1]);
	    				String output = "";
	    				Map<String, Integer> maps= database.monthReport(year, month);
	    				double[] percent = database.percentList(maps);
	    				Iterator<Entry<String, Integer>> iter = maps.entrySet().iterator();
	    				int count=0;
	    				while (iter.hasNext())
	    			    {
	    			      Entry<String, Integer> pair = iter.next();
	    			      output = output+"Farm "+pair.getKey()+":          Total Weight: "+pair.getValue()+"          Percentage Weight: "+percent[count];
	    				  output = output+"\n";
	    				  count++;
	    			}
	    				dataTable.setText(output);
	    		}
	    		}
	    		);
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		launch(args);
	}
}
