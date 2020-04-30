package application;

import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class MyHandler implements javafx.event.EventHandler<ActionEvent> {
	TextField textField;
	Button button;
	Label label;
	MyHandler(Button button, TextField textField){
		this.button=button;
		this.textField=textField;
		this.label=label;
	}
	@Override
	public void handle(ActionEvent arg0) {
		// TODO Auto-generated method stub
		label.setText(yearReport(textField.getText()));
	}

}
