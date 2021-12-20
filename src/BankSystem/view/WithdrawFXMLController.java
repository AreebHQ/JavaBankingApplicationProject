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
public class WithdrawFXMLController {
    
    
    //SystemCode source;
    DataValidation data;
    Bank bank;
    
    @FXML
    private Button withdrawBtn;  
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
    private TextField withdrawAmountField;
    @FXML
    private Text withdrawResult;
    
    int accountNumber;
    double amount;
    boolean check = false;
    
    
    public WithdrawFXMLController() throws ClassNotFoundException
    {
        data = new DataValidation();
        bank = new Bank();
    }
    
   @FXML
    private void goBackMainOptions() throws IOException
    {
        Main.backToMainfrmWithdraw();
 
    }
    
    @FXML
    private void withdrawAmount() throws IOException
    {
       accountNumber = data.checkAccount(accNumberField.getText());
       if(data.checkAmount(withdrawAmountField.getText()))
       {
           amount = Double.parseDouble(withdrawAmountField.getText());
           
           if (bank.withdraw(accountNumber, amount)){
              withdrawResult.setText("Withdraw Successful!");
              withdrawResult.setFill(GREEN);
              Main.setConfirmWindow("Action Successful","Amount $" +amount+" Has been Withdrawn!");
              goBackMainOptions();}
           else {
              withdrawResult.setText("Withdraw Error!");
              withdrawResult.setFill(RED);
             
            }
       } else {
            withdrawResult.setText("Withdraw Error!");
            withdrawResult.setFill(RED); }       
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
        bank.getBalance(accountNumber);
        accBalance.setText("$"+bank.getBalance(accountNumber));
   
    }
    
}
