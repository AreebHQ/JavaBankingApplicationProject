/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BankSystem.view;

import BankSystem.Bank;
import BankSystem.DataValidation;
import BankSystem.Main;
import java.io.IOException;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;

/**
 *
 * @author areeb
 */
public class CloseAccountFXMLController {
 
    Bank bank;
    DataValidation data;
    
    @FXML
    TextField accNumberField;
    @FXML
    Label status;
    @FXML
    Circle statusCircle;
    @FXML
    Text accStatus;
    
    int accountNumber;
    boolean check = false;
    
    public CloseAccountFXMLController() throws ClassNotFoundException
    {
              data = new DataValidation();
              bank = new Bank();
    }
    
    @FXML
    private void closeAccount() throws IOException
    {
         accountNumber = data.checkAccount(accNumberField.getText());
       
        check = bank.isActive(accountNumber);
        if (check)
        {
           bank.closeAccount(accountNumber);
            
            accStatus.setText("Account Inactive!");
            statusCircle.setFill(Color.RED);
            accStatus.setFill(Color.RED);
            status.setText("Account has\nbeen Closed!");
            
     
        } else {

            Main.setAlertWindow("Action Denied!","Acount doesn't exist or inactive!");
            status.setText("Doesn't Exist\n or is Inactive");
            accStatus.setText("Doesn't Exist\n or is Inactive");
            accStatus.setFill(Color.RED);
            statusCircle.setFill(Color.RED);   
        }
    }
    
    @FXML
    private void checkAccount() throws IOException
    {  
      accountNumber = data.checkAccount(accNumberField.getText());
       
        check = bank.isActive(accountNumber);
        if (check)
        {
            accStatus.setText("Active");
            statusCircle.setFill(Color.GREEN);
            status.setText("Active");
     
        } else {
     
            Main.setAlertWindow("Alert! ","Acount doesn't exist or inactive!");
            status.setText("Doesn't Exist\n or is Inactive");
            accStatus.setText("Doesn't Exist\n or is Inactive");
            accStatus.setFill(Color.RED);
            statusCircle.setFill(Color.RED);   
        }
    }
    
    
     @FXML
    private void goBackToMoreOptions() throws IOException
    {
        Main.bankToMorefrmCloseAccount();
    }
}
