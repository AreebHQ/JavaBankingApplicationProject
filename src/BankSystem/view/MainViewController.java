
package BankSystem.view;

import BankSystem.Main;
import java.io.IOException;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;


public class MainViewController {
    
    private Main main;
    
    @FXML
    TextField userIdField;
    
    @FXML
    TextField passwordField;
    private final String loginID = "Java345";
    private final String loginPassword = "J123456";
    
    
    
     public MainViewController (){
   
    }
    
     @FXML
     private void checkLogin() throws IOException
     {
         String userID = userIdField.getText();
         String userPassword = passwordField.getText();
         
         if (loginID.equals(userID))
         {
             if (loginPassword.equals(userPassword))
             {
                 gotoMainOptions();
             }else {
                  main.setAlertWindow("Invalid Password", "Invalid Password! \nPlease try again!");
             }
         } else {
              main.setAlertWindow("Invalid UserID", "Invalid User ID! \nPlease try again!");
         } 
         
     }
     

    private void gotoMainOptions() throws IOException
    {
        main.showMainOptions();
    }
    
 
    
}
