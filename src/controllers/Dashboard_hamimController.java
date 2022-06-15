package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;

import MailFunctions.FetchEmails;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import models.EmailModel;
import models.ListModel;
import models.UserModel;

public class Dashboard_hamimController implements Initializable {
	@FXML
	private JFXButton NewMail;
	@FXML
	private JFXButton Inbox;
	@FXML
	private JFXButton Sent;
	@FXML
	private JFXButton Trash;
	@FXML
	private JFXButton MailingList;
	@FXML
	private JFXButton Archive;
	@FXML
	private JFXButton Logout;

    @FXML
    private Pane option;

    @FXML
    private VBox load;

    @FXML
    private MenuButton vb;
	private int current =0;
	private UserModel us = null;
	private List<UserModel> lists;
	private List<EmailModel> emails;
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		try {
			lists = this.getdata();
		} catch (NumberFormatException | SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		vb.getItems().clear();
		for (int i = 0; i <lists.size();i++) {
			
		
               
					MenuItem m = new MenuItem(lists.get(i).getEmail());
					m.setOnAction(event -> {
					   vb.setText(m.getText());
					   us=this.getcurrent(m.getText());
					   System.out.println(us.getName());
					});
					vb.getItems().add(m);
					vb.setText(m.getText());
					
					System.out.println("done");
					
					
					
		}
		
		
		
	}
	
	private UserModel getcurrent(String add) {
		for(UserModel o : lists) {
			if(o.getEmail().equals(add))return o;
		}
		return null;
	}
		
		private List<UserModel> getdata() throws NumberFormatException, SQLException{
			
			lists = new ArrayList<>();
			
			Connection conn= DBConnect.getConnection(); 
			
			
			ResultSet re = conn.createStatement().executeQuery("select * from userinf where connected='true'");
			
			while(re.next()) {
				
					lists.add(new UserModel(re.getString("Name"), re.getString("Email"),re.getString("password")));
			}
		
			return lists; 
		}
		
/*private List<EmailModel> getemails() throws NumberFormatException, SQLException{
			
			emails = new ArrayList<>();
			
			Connection conn= DBConnect.getConnection(); 
			
			
			ResultSet re = conn.createStatement().executeQuery("select * from email where tos='"+us.getEmail()+"';");
			
			while(re.next()) {
				
					emails.add(new EmailModel(re.getInt("id"), re.getString("froms"), re.getString("tos"), re.getDate("dates"),  re.getString("subject"), re.getString("body")));
			}
		
			return emails; 
		}
	*/
	
	
	
	
	@FXML
    void newmess(ActionEvent event) throws IOException {
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/SendMail.fxml"));
          
         
          		//option.setStyle("-fx-background-color: darkred" );
				Pane newpane = fxmlLoader.load();
				 option.getChildren().add(newpane);
			
    }
	
	public void loadmess(Node nd) {
		option.getChildren().add(nd);
	}
	  @FXML
	    void loadinbox(ActionEvent event) throws NumberFormatException, SQLException {
		  
		  emails = FetchEmails.fetch(us.getEmail(), us.getPassword());
			//emails = this.getemails();
			//Show In_box**********************
			if(emails.size()==0) {
				Alert alert = new Alert(Alert.AlertType.INFORMATION);
				alert.setContentText("Empty Inbox");
				System.out.println("welp");
				alert.show();
			}else {
			
			
			for(int i=0; i<emails.size();i++) {
				
				System.out.println(emails.get(i).getBody());
				try {
				FXMLLoader fxmlLoader = new FXMLLoader();
		          
				
	            fxmlLoader.setLocation(getClass().getResource("/fxml/User_choose.fxml"));
				Pane p = fxmlLoader.load();
				User_chooseController txtctl = fxmlLoader.<User_chooseController>getController();
				txtctl.initdata(emails.get(i));
					 load.getChildren().add(p); 
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				}
		         
			}
	    }

}
