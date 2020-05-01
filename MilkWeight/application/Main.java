/**
 * 
 */
package application;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import Internal.Data;
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

	private static final int WINDOW_WIDTH = 800;
	private static final int WINDOW_HEIGHT = 666;
	private static final String APP_TITLE = "Milk Weight";

	@Override
	public void start(Stage primaryStage) throws Exception {
		// TODO Auto-generated method stub
		Data database = new Data();
		// save args example
		args = this.getParameters().getRaw();

		// Main layout is Border Pane example (top,left,center,right,bottom)
		BorderPane root = new BorderPane();

		HBox topBox = new HBox();
		topBox.setPadding(new Insets(15, 12, 15, 12));
		topBox.setSpacing(10);

		Label label1 = new Label("File Path:");
		TextField textField = new TextField();
		textField.setPrefSize(400, 20);
		textField.setPromptText("Please enter File path here. Format: <path>");

		Button button = new Button("Enter");

		button.setPrefSize(100, 20);
		topBox.getChildren().addAll(label1, textField, button);
		topBox.setAlignment(Pos.CENTER);

		// root.setTop(status);

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

		Label status = new Label("Status");
		status.setAlignment(Pos.CENTER);
		status.setPrefSize(400, 50);
		status.setStyle("-fx-border-color: black;");
		VBox calculationPart = new VBox();
		calculationPart.getChildren().add(top);
		calculationPart.getChildren().add(status);
		calculationPart.setAlignment(Pos.CENTER);
		calculationPart.setSpacing(10);

		root.setTop(calculationPart);

		VBox centerContainer = new VBox();
		Label textOutputPrompt = new Label("Please select one of the options above :");
		textOutputPrompt.setMinSize(100, 30);
		centerContainer.getChildren().add(textOutputPrompt);

		TextField IDInput = new TextField();
		IDInput.setMinWidth(500);
		IDInput.setPromptText("Please select one of the options above.");
		IDInput.setAlignment(Pos.CENTER);

		Button submitIDInput = new Button("Submit");
		submitIDInput.setPrefSize(100, 20);

		HBox IDPart = new HBox();
		IDPart.getChildren().add(IDInput);
		IDPart.getChildren().add(submitIDInput);
		IDPart.setPadding(new Insets(15, 12, 15, 12));
		IDPart.setSpacing(10);
		IDPart.setAlignment(Pos.CENTER);
		centerContainer.getChildren().add(IDPart);

		Label dataTable = new Label("DateTable");
		dataTable.setMinSize(420, 200);
		dataTable.setAlignment(Pos.CENTER);
		dataTable.setStyle("-fx-border-color: black;");
		centerContainer.getChildren().add(dataTable);

		HBox botBox = new HBox();
		botBox.setPadding(new Insets(15, 12, 15, 12));
		botBox.setSpacing(10);
		Label labelOut = new Label("File Path:");
		TextField filePath = new TextField();
		filePath.setPromptText("Please enter File path here. Format: <path>");
		filePath.setMinWidth(300);
		Button buttonFinalEnter = new Button("Save");
	    buttonFinalEnter.setPrefSize(100, 20);
		botBox.getChildren().addAll(labelOut, filePath, buttonFinalEnter);
		botBox.setAlignment(Pos.CENTER);
		centerContainer.getChildren().add(botBox);

		centerContainer.setAlignment(Pos.CENTER);
		centerContainer.setSpacing(10);

		root.setCenter(centerContainer);

		buttonF.setDisable(true);
		buttonM.setDisable(true);
		buttonA.setDisable(true);
		buttonD.setDisable(true);
		submitIDInput.setDisable(true);
		buttonFinalEnter.setDisable(true);

		button.setOnAction(e -> {
			database.clearFile();
			String filePaths = textField.getText();
			status.setText("Status");
			String[] pathList = filePaths.split(",");
			Boolean statusPrint = true;
			for(int i=0; i<pathList.length; i++) {
				File readFile = new File(pathList[i]);
				if (readFile.exists()) {
					database.readFile(readFile);
				} else
					statusPrint = false;
			}
			if(statusPrint) {
				status.setText("All files are successfully imported.");
				buttonF.setDisable(false);
				buttonM.setDisable(false);
				buttonA.setDisable(false);
				buttonD.setDisable(false);
			}
			else {
				status.setText("Failed to import some files.");
				buttonF.setDisable(true);
				buttonM.setDisable(true);
				buttonA.setDisable(true);
				buttonD.setDisable(true);
				submitIDInput.setDisable(true);
				buttonFinalEnter.setDisable(true);
			}
 			
		});

		buttonA.setOnAction(e -> {
			IDInput.setText("");
			status.setText("Status");
			textOutputPrompt.setText("Please enter year below:");
			IDInput.setPromptText("Format: <Year>");
			submitIDInput.setDisable(false);
		});

		buttonD.setOnAction(e -> {
			IDInput.setText("");
			status.setText("Status");
			textOutputPrompt.setText("Please enter the initial date and final date below:");
			IDInput.setPromptText("Format: <Year>,<Initial month>,<Inital day>,<Final month>,<Final day>");
			submitIDInput.setDisable(false);
		});
		buttonF.setOnAction(e -> {
			IDInput.setText("");
			status.setText("Status");
			textOutputPrompt.setText("Please enter your desired farm id and year below:");
			IDInput.setPromptText("Enter format: <Farm Id>,<Year>");
			submitIDInput.setDisable(false);
		});
		buttonM.setOnAction(e -> {
			IDInput.setText("");
			status.setText("Status");
			textOutputPrompt.setText("Please enter your desired month and year below:");
			IDInput.setPromptText("Enter format: <Month>,<Year>");
			submitIDInput.setDisable(false);
		});
		buttonFinalEnter.setOnAction(e -> {
			IDInput.setText("");
			status.setText("Status");
			String wroteOut = "";

			try {
				File file = new File(filePath.getText());
				boolean isValid=false;
				String[] validOutputForm = new String[] { "txt", "csv", "json", "xml", "html" };
				for (String outForm : validOutputForm)
					if (filePath.getText().endsWith("." + outForm))
						if(!filePath.getText().equals("." + outForm))
							if(file.createNewFile())
								isValid=true;
				if(!isValid)
					throw new IllegalArgumentException();

				String lineOne = dataTable.getText().split("\n")[0];
				String[] fragments = lineOne.split(":");
				String nameOne = fragments[0].split(" ")[0];
				String nameTwo = fragments[1].trim();
				String nameThree = fragments[2].trim().split("          ")[1];
				wroteOut = nameOne + "," + nameTwo + "," + nameThree + "\n";
				if(filePath.getText().endsWith("html"))
					wroteOut+="<br/>";
				String[] lines = dataTable.getText().split("\n");
				for (int i = 0; i < lines.length; i++) {
					String[] lineFrags = lines[i].split(":");
					String valueOne = lineFrags[0].split(" ")[1];
					String valueTwo = lineFrags[2].trim().split("          ")[0];
					String valueThree = lineFrags[3].trim();
					valueThree = valueThree.substring(0, valueThree.length()-1); 
					wroteOut = wroteOut + valueOne + "," + valueTwo + "," + valueThree + "\n";
					if(filePath.getText().endsWith("html"))
						wroteOut+="<br/>";
				}

				FileWriter csvWriter = new FileWriter(filePath.getText());
				csvWriter.append(wroteOut);
				csvWriter.flush();
				csvWriter.close();
				status.setText("File is successfully outputed to file path \"" + filePath.getText() + "\"");
			} catch (Exception e1) {
				status.setText("Invalid output file path \"" + filePath.getText() + "\"");
			}

		});
		submitIDInput.setOnAction(e -> {
			try {
				if (textOutputPrompt.getText().equals("Please enter the initial date and final date below:")) {
					int year = Integer.parseInt(IDInput.getText().split(",")[0]);
					int sMonth = Integer.parseInt(IDInput.getText().split(",")[1]);
					int sDay = Integer.parseInt(IDInput.getText().split(",")[2]);
					int eMonth = Integer.parseInt(IDInput.getText().split(",")[3]);
					int eDay = Integer.parseInt(IDInput.getText().split(",")[4]);

				if (!database.dateRangeReport(year, sMonth, sDay, eMonth, eDay).containsValue(-1)) {
					Map<String, Integer> maps = database.dateRangeReport(year, sMonth, sDay, eMonth, eDay);
					double[] percent = database.percentList(maps);
					Iterator<Entry<String, Integer>> iter = maps.entrySet().iterator();
					int count = 0;
					String output = "";
					while (iter.hasNext()) {
						Entry<String, Integer> pair = iter.next();
						double percentage = ((double)Math.round(percent[count]*100))/100; 
	    			      output = output+"Farm "+pair.getKey()+":          Total Weight: "+pair.getValue()
	    			      			+"          Percentage Weight: "+percentage+"%";
						output = output + "\n";
						count++;
					}
					dataTable.setText(output);
					status.setText("Argument(s) is/are valid");
					buttonFinalEnter.setDisable(false);
					submitIDInput.setDisable(true);
				} else {
					status.setText("Argument(s) is/are invalid");
					dataTable.setText("DataTable");
					buttonFinalEnter.setDisable(true);
				}
			} else if (textOutputPrompt.getText().equals("Please enter year below:")) {
				if (!database.yearReport(Integer.parseInt(IDInput.getText())).containsValue(-1)) {
					Map<String, Integer> maps = database.yearReport(Integer.parseInt(IDInput.getText()));
					double[] percent = database.percentList(maps);
					Iterator<Entry<String, Integer>> iter = maps.entrySet().iterator();
					int count = 0;
					String output = "";
					while (iter.hasNext()) {
						Entry<String, Integer> pair = iter.next();
						double percentage = ((double)Math.round(percent[count]*100))/100; 
	    			      output = output+"Farm "+pair.getKey()+":          Total Weight: "+pair.getValue()
	    			      			+"          Percentage Weight: "+percentage+"%"; 
						output = output + "\n";
						count++;
					}

				} else if (textOutputPrompt.getText().equals("Please enter your desired farm id and year below:")) {
					String farmId = Integer.parseInt(IDInput.getText().split(",")[0]) + "";
					int year = Integer.parseInt(IDInput.getText().split(",")[1]);
					for (int i : database.farmReport(farmId, year))
						if (i == -1) {
							status.setText("Argument(s) is/are invalid");
							dataTable.setText("DataTable");
							buttonFinalEnter.setDisable(true);
							break;
						}
					if (!status.getText().equals("Argument(s) is/are invalid")) {
						String output = "";
						int[] total = database.farmReport(farmId, year);
						double[] percent = database.percentList(total, year);
						for (int i = 1; i <= 12; i++) {
							output = output + "Month " + i + ":          Total Weight: " + total[i - 1]
									+ "          Percentage Weight: " + percent[i - 1];
							output = output + "\n";
						}
						dataTable.setText(output);
						status.setText("Argument(s) is/are valid");
						buttonFinalEnter.setDisable(false);
						submitIDInput.setDisable(true);
					}
				} else if (textOutputPrompt.getText().equals("Please enter your desired month and year below:")) {
					int month = Integer.parseInt(IDInput.getText().split(",")[0]);
					int year = Integer.parseInt(IDInput.getText().split(",")[1]);
					if (!database.monthReport(year, month).containsValue(-1)) {
						String output = "";
						Map<String, Integer> maps = database.monthReport(year, month);
						double[] percent = database.percentList(maps);
						Iterator<Entry<String, Integer>> iter = maps.entrySet().iterator();
						int count = 0;
						while (iter.hasNext()) {
							Entry<String, Integer> pair = iter.next();
							output = output + "Farm " + pair.getKey() + ":          Total Weight: " + pair.getValue()
									+ "          Percentage Weight: " + percent[count];
							output = output + "\n";
							count++;
						}
						dataTable.setText(output);
						status.setText("Argument(s) is/are valid");
						buttonFinalEnter.setDisable(false);
						submitIDInput.setDisable(true);
					} else {
						status.setText("Argument(s) is/are invalid");
						dataTable.setText("DataTable");
						buttonFinalEnter.setDisable(true);
					}
				if (!status.getText().equals("Argument(s) is/are invalid")) {
					String output = "";
					int[] total = database.farmReport(farmId, year);
					double[] percent = database.percentList(total, year);
					for (int i = 1; i <= 12; i++) {
						double percentage = ((double)Math.round(percent[i-1]*100))/100; 
    					output = output+"Month "+i+":          Total Weight: "+total[i-1]
    							+"          Percentage Weight: "+percentage+"%"; 
						output = output + "\n";
					}
					dataTable.setText(output);
					status.setText("Argument(s) is/are valid");
					buttonFinalEnter.setDisable(false);
					submitIDInput.setDisable(true);
				}
			} else if (textOutputPrompt.getText().equals("Please enter your desired month and year below:")) {
				int month = Integer.parseInt(IDInput.getText().split(",")[0]);
				int year = Integer.parseInt(IDInput.getText().split(",")[1]);
				if (!database.monthReport(year, month).containsValue(-1)) {
					String output = "";
					Map<String, Integer> maps = database.monthReport(year, month);
					double[] percent = database.percentList(maps);
					Iterator<Entry<String, Integer>> iter = maps.entrySet().iterator();
					int count = 0;
					while (iter.hasNext()) {
						Entry<String, Integer> pair = iter.next();
						 double percentage = ((double)Math.round(percent[count]*100))/100; 
	    			      output = output+"Farm "+pair.getKey()+":          Total Weight: "+pair.getValue()
	    			      			+"          Percentage Weight: "+percentage+"%"; 
						output = output + "\n";
						count++;
					}
					dataTable.setText(output);
					status.setText("Argument(s) is/are valid");
					buttonFinalEnter.setDisable(false);
					submitIDInput.setDisable(true);
				} else {
					status.setText("Argument(s) is/are invalid");
					dataTable.setText("DataTable");
					buttonFinalEnter.setDisable(true);
				}
			}
			}
			catch(Exception e2) {
				status.setText("Argument(s) is/are invalid");
				dataTable.setText("DataTable");
				buttonFinalEnter.setDisable(true);
			}
		});
		Scene mainScene = new Scene(root, WINDOW_WIDTH, WINDOW_HEIGHT);

		// Add the stuff and set the primary stage
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
