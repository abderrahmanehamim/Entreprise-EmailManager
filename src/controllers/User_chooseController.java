package controllers;

import models.EmailModel;

import java.io.IOException;
import java.util.Calendar;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;

public class User_chooseController {
	   @FXML
	    private Label Body;

	    @FXML
	    private Label Let;

	    @FXML
	    private Label Subject;

	    @FXML
	    private Label Username;
	    @FXML
	    private Label time;
	    
	    
	    private String firstTwo(String str) {
	        return str.length() < 2 ? str : str.substring(0, 2);
	    }
	    public void initdata(EmailModel em) {
	    	Let.setText(firstTwo(em.getFrom()));
	    	Username.setText(em.getFrom());
	    	Subject.setText(em.getSubject());
	    	Body.setText(em.getBody());
	    	Calendar calendar = Calendar.getInstance();
	        calendar.setTime(em.getDate());
	    	time.setText(calendar.get(Calendar.HOUR_OF_DAY)+":"+calendar.get(Calendar.MINUTE));
	    }

	    @FXML
	    void ShowMessage(MouseEvent event) throws IOException {
	    		 FXMLLoader fxmlLoader = new FXMLLoader();
		          
				
	            fxmlLoader.setLocation(getClass().getResource("/fxml/ShowMail.fxml"));
	            ShowMailController sw = fxmlLoader.<ShowMailController>getController();
	            
				Pane p = fxmlLoader.load();
				FXMLLoader fxmlLoa= new FXMLLoader();
						fxmlLoa.setLocation(getClass().getResource("/fxml/Dashboard_hamim.fxml"));
				
				Dashboard_hamimController ctl = fxmlLoa.<Dashboard_hamimController>getController();
				ctl.loadmess(p);
				
	    }
	
	
	

}
