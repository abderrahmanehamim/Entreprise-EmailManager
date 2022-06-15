package controllers;

import javafx.fxml.FXML;

import com.jfoenix.controls.JFXButton;

import javafx.scene.control.Label;

import javafx.scene.control.TextArea;
import models.EmailModel;

public class ShowMailController {
	@FXML
	private JFXButton Send;
	@FXML
	private Label Subject;
	@FXML
	private JFXButton Send1;
	@FXML
	private Label From;
	@FXML
    private Label To;

	@FXML
	private Label Let;
	@FXML
	private Label time;
	@FXML
	private TextArea body;
	
	
	   private String firstTwo(String str) {
	        return str.length() < 2 ? str : str.substring(0, 2);
	    }
	
	public void setdata(EmailModel em) {
		Subject.setText(em.getSubject());
		From.setText(em.getFrom());
		To.setText("A : "+em.getTo());
		Let.setText(firstTwo(em.getFrom()));
		body.setText(em.getBody());
		
	}

}
