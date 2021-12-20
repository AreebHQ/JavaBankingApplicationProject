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
public class CheckBalanceFXMLController {
    
    Main main;
    Bank bank;
    DataValidation data;
    
    @FXML
    TextField accNumberField;
    @FXML
    Label balance;
    @FXML
    Circle statusCircle;
    @FXML
    Text accStatus;
    
    int accountNumber;
    boolean check = false;
    
    public CheckBalanceFXMLController() throws ClassNotFoundException
    {
              data = new DataValidation();
              bank = new Bank();
    }
    
    
    @FXML
    private void setBalance() throws IOException
    {  
      accountNumber = data.checkAccount(accNumberField.getText());
       
        check = bank.isActive(accountNumber);
        if (check)
        {
            accStatus.setText("Active");
            statusCircle.setFill(Color.GREEN);
            String bal = Double.toString(bank.getBalance(accountNumber));
            balance.setText("$"+bal);
     
        } else {
            main.setAlertWindow("Error","Acount doesn't exist or inactive!");
            balance.setText("$0.00");
            accStatus.setText("Doesn't Exist or Inactive");
            statusCircle.setFill(Color.RED);   
        }
    }

    @FXML
    private void goBackToMoreOptions() throws IOException
    {
        main.bankToMorefrmCheckBalance();
    }
    
}
