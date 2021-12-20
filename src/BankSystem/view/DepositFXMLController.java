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
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import static javafx.scene.paint.Color.GREEN;
import static javafx.scene.paint.Color.RED;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;

/**
 *
 * @author areeb
 */
public class DepositFXMLController {
    
    
    private Main main;
    DataValidation data;
    Bank bank;
    
    
    
    @FXML
    private Button depositBtn;  
    @FXML
    private Button cancelBtn;
    @FXML
    private TextField accNumberField;
    @FXML
    private Button checkAccountBtn;
    @FXML
    private Text accStatus;
    @FXML
    private Circle statusCircle;
    @FXML
    private Text accNumber;
    @FXML
    private Text first_Name;
    @FXML
    private Text last_Name;
    @FXML
    private Text accBalance;
    @FXML
    private TextField depositeAmountField;
    @FXML
    private Text depositResult;
    
    
    int accountNumber;
    double amount;
    boolean check = false;
    
   
    
   @FXML
    private void goBackMainOptions() throws IOException
    {
        main.backToMainfrmDeposit();
 
    }
    
    public DepositFXMLController() throws ClassNotFoundException
    {
              data = new DataValidation();
              bank = new Bank();
    }
    
 
    
    @FXML
    private void depositAmount() throws IOException
    {
        
       accountNumber = data.checkAccount(accNumberField.getText());
      if(data.checkAmount(depositeAmountField.getText()))
       {
           amount = Double.parseDouble(depositeAmountField.getText());
           
           if (bank.deposit(accountNumber, amount)){
              depositResult.setText("Deposit Successful!");
              depositResult.setFill(GREEN);
              main.setConfirmWindow("Action Successful","Amount $" +amount+" Has been Deposited!");
              goBackMainOptions();}
           else {
              depositResult.setText("Deposit Error!");
              
              return;
            }
       } else {
           
            depositResult.setText("Insufficinent Amount!");
            depositResult.setFill(RED);
       }
      
      
    }
    
    @FXML
    private void checkifActive() throws IOException
    {
         
        accountNumber = data.checkAccount(accNumberField.getText());
       
        check = bank.isActive(accountNumber);
        if (check)
        {
            accStatus.setText("Active");
            statusCircle.setFill(Color.GREEN);
            
        } else {
            main.setAlertWindow("Error","Acount doesn't exist or inactive!");
            accStatus.setText("Doesn't Exist or Inactive");
            statusCircle.setFill(Color.RED);
            
        }
        setAccountInfo();
    }
    
    private void setAccountInfo()
    {
        
        accNumber.setText("#00000000"+accountNumber);
        first_Name.setText(""+bank.getFirstName(accountNumber));
        last_Name.setText(""+bank.getLastName(accountNumber));
        accBalance.setText("$"+bank.getBalance(accountNumber));
   
    }
}
