/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BankSystem.view;

import BankSystem.Main;
import BankSystem.Users;
import java.io.IOException;
import java.util.Random;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import static javafx.scene.paint.Color.GREEN;
import javafx.scene.text.Text;

/**
 *
 * @author areeb
 */
public class AddNewUserFXMLController {
    
    Users user;
    Main main;
    
    @FXML
    TextField firstNameField;
    @FXML
    TextField lastNameField;
    @FXML
    TextField passwordField;
    @FXML
    Label userIDLabel;
    @FXML
    Text headerLabel;
    
 public AddNewUserFXMLController() throws ClassNotFoundException, IOException{
    
  
} 

@FXML
public void createNewUser() throws ClassNotFoundException, IOException
{
    user = new Users();
    String fName = firstNameField.getText();
    String lName = lastNameField.getText();
    String userid = userIDDisplay();
    String pass = passwordField.getText();
    
    user.CreateUser(fName, lName, userid, pass);
    System.out.println("new user function "+userid);
    headerLabel.setText("New User Created!");
    headerLabel.setFill(GREEN);
}

@FXML
public String userIDDisplay()
{
    String fname = firstNameField.getText();
    String lname = lastNameField.getText();
    String userid = GenerateUserID(fname,lname);
    userIDLabel.setText(userid);
     System.out.println("setDisplay function "+userid);
    return userid;
}

@FXML
public String GenerateUserID(String fname, String lname)
{
    fname = firstNameField.getText();
    lname = lastNameField.getText();
    Random rand = new Random();
    int randCode = rand.nextInt(899)+100;
    String uniqueCode = Integer.toString(randCode);
    String userid = (fname+lname+uniqueCode);
    userIDLabel.setText(userid);
    
    return userid;
}


@FXML
public void goBacktoMoreOptions() throws IOException{
    
    main.bankToMorefrmNewUser();
}

}
