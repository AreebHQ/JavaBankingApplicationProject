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
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;

/**
 *
 * @author areeb
 */
public class CustomerDetailFXMLController {
    
    Bank bank;
    Main main;
    DataValidation data;
    
    @FXML
    TextField accNumberField;
    @FXML
    Text accStatus;
    @FXML
    Text accNumber;
    @FXML
    Text firstName;
    @FXML
    Text lastName;
    @FXML
    Text balance;
    @FXML
    Text contact;
    @FXML
    Text email;
    @FXML
    Text address;
    @FXML
    Text age;
    @FXML
    Text maritalStatus;
    @FXML
    Text accountType;
    @FXML
    private Circle statusCircle;
    
    int accountNumber;
    boolean check;
    public CustomerDetailFXMLController() throws ClassNotFoundException
    {
              data = new DataValidation();
              bank = new Bank();
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
            setInformation();
            
        } else {
            setToNull();
            Main.setAlertWindow("Error","Acount doesn't exist or inactive!");
            accStatus.setText("Doesn't Exist or Inactive");
            statusCircle.setFill(Color.RED); 
        }
    }
    
    
    private void setInformation()
    {
     accNumber.setText("#000"+accNumberField.getText());
     firstName.setText(bank.getFirstName(accountNumber));
     lastName.setText(bank.getLastName(accountNumber));
     balance.setText( "$"+Double.toString(bank.getBalance(accountNumber)));
     contact.setText(bank.getAccountContact(accountNumber));
     email.setText(bank.getAccountEmail(accountNumber));
     address.setText(bank.getAccountAddress(accountNumber));
     age.setText(bank.getAccAge(accountNumber));
     maritalStatus.setText(bank.getAccMaritialStatus(accountNumber));
     accountType.setText(bank.getAccountType(accountNumber));
    }
    
    
    
    private void setToNull()
    {
     accNumber.setText("#000");
     firstName.setText("N/A");
     lastName.setText("N/A");
     balance.setText( "$0.00"); 
     contact.setText("N/A"); 
     email.setText("N/A");
     address.setText("N/A");
     age.setText("N/A");
     maritalStatus.setText("N/A");
     accountType.setText("N/A");
    }
    
    @FXML
    private void btnClose() throws IOException
    {
        main.bankToMainfrmCustomerDetail();
    }   
    
}
