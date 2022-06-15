package controllers;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;
import models.*;

public class homeController implements Initializable{
	@FXML
	private ScrollPane scroll;
	@FXML
	private GridPane grid;
	
	private List<ListModel>lists = new ArrayList<>();
	private PreparedStatement pst;
	
	
	private List<ListModel> getdata() throws NumberFormatException, SQLException{
		
		List<ListModel>lists = new ArrayList<>();
		
		Connection conn= DBConnect.getConnection(); 
		
		
		ResultSet re = conn.createStatement().executeQuery("select * from lists");
		
		while(re.next()) {
			
				lists.add(new ListModel(""+re.getInt("id"), re.getString("name"),re.getInt("mem_num"), re.getString("img")));
		}
		
		
		
		
		return lists; 
	}
	

	public void initialize(URL arg0, ResourceBundle arg1) {
			scroll.setPrefSize(736, 415);
			try {
				lists.add(0, new ListModel("Special", "Créer liste" ,0, "C:/Users/hajar/eclipse-workspace/Testfx/src/img/plus.png"));
				lists.addAll(this.getdata());
			} catch (NumberFormatException | SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			int column = 0;
            int row = 1;
        	try {

        		  
  					

  						for (int i = 0; i <lists.size();i++) {
  							FXMLLoader fxmlLoader = new FXMLLoader();
  			                  fxmlLoader.setLocation(getClass().getResource("/fxml/List.fxml"));
  			                  
  			                 
  			  			
  			  					AnchorPane anchorPane = fxmlLoader.load();
  			  					ListController listctrl = fxmlLoader.getController();
  			  					listctrl.setData(lists.get(i));
  							
  					
  					
		
					 
                
                if (column == 3) {
                    column = 0;
                    row++;
                }

                grid.add(anchorPane, column++, row); //(child,column,row)
                //set grid width
                grid.setMinWidth(Region.USE_COMPUTED_SIZE);
                grid.setPrefWidth(Region.USE_COMPUTED_SIZE);
                grid.setMaxWidth(Region.USE_PREF_SIZE);

                //set grid height
                grid.setMinHeight(Region.USE_COMPUTED_SIZE);
                grid.setPrefHeight(Region.USE_COMPUTED_SIZE);
                grid.setMaxHeight(Region.USE_PREF_SIZE);

                GridPane.setMargin(anchorPane, new Insets(10));
			}
  					
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        	}
	

}

