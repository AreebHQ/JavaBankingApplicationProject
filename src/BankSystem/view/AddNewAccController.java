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
import java.util.Calendar;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;





public class AddNewAccController {
    
    Main main;
    DataValidation data;
    Bank bank;
    
   
    
    ObservableList<String> maritalStatusList = FXCollections.observableArrayList("Single","Married");
    String fname,lname, email, contact, address,mrgStatus, accGender;
   
    
    // Account info
    @FXML
    private TextField fnameField;  
    @FXML
    private TextField lnameField;  
    @FXML
    private TextField amountField;      
    @FXML
    private CheckBox savingBox;   
    @FXML
    private CheckBox checkingBox;
     
    // Personal info 
    @FXML
    private TextField contactField;
    @FXML
    private TextField emailField;
    @FXML
    private TextField addressField;
    @FXML 
    private DatePicker dateofBirth;  
    @FXML
    private Text currentAge;   
    
    // maritial status box
    @FXML
    private ChoiceBox maritalStatus;   
    @FXML
    private RadioButton maleBtn;   
    @FXML
    private RadioButton femaleBtn;  
    
    private int age = 0;
    public String accType = "Checking";
    boolean check;
    // setting maritial status list
    @FXML
    private void initialize()
    {
        maritalStatus.setItems(maritalStatusList);
        maritalStatus.setValue("Single");
    }
    
     public AddNewAccController() throws ClassNotFoundException
    {
              data = new DataValidation();
              bank = new Bank();
    }
    
    
    @FXML
    private void showAge()
    {
        Calendar current = Calendar.getInstance();
        int year = current.get(Calendar.YEAR);
        int birthYear = (dateofBirth.getValue().getYear());
        int ageFound = year  - birthYear;
        currentAge.setText(Integer.toString(ageFound) + " Years");  
         if (ageFound < 18 )
        {
            Main.setAlertWindow("Invalid Age","Age must be 18 and above!\nPlease enter correct age and try again!"); 
            
        } else {
 
         age = ageFound;}
    }
    

    @FXML
    private void handleSavingBox()
    {
        if(savingBox.isSelected())
        {
            checkingBox.setSelected(false);
             accType = "Savings";
        }
    }
    
    @FXML
    private void handleCheckingBox()
    {
         if(checkingBox.isSelected())
        {
            savingBox.setSelected(false);
             accType = "Checking";
        } 
    }
    
    @FXML
    private void backToMain()  throws IOException
    {

       Main.backToMainfrmNewAccount();
 
    }
  
    @FXML
    public void createNewAccount() throws IOException
    {
       
       // setFields();
        check = false;
        double amount = 0;
        if(data.checkifEmpty(fnameField.getText(), "FirstName"))
        {
            fname = fnameField.getText();
            check = true;
        }
        check = false;
        if(data.checkifEmpty(lnameField.getText(), "LastName"))
        {
            lname = lnameField.getText();
             check = true;
        }
        check = false;
        if( data.checkAmount(amountField.getText()))
        {
           amount = Double.parseDouble(amountField.getText());
            check = true;
        }
        check = false;
        if(data.checkifEmpty(contactField.getText(), "Contact"))
        {
           contact = contactField.getText();
            check = true;
        }
        check = false;
        if(data.checkifEmpty(addressField.getText(),"Address"))
        {
            address = addressField.getText();
             check = true;
        }
        check = false;
        if(data.checkifEmpty(emailField.getText(),"Email"))
        {
         if (data.checkEmail(emailField.getText()))
        { email  = emailField.getText();
         check = true;}
        }else { 
        check = false;}
        if (age < 18)
        {
            Main.setAlertWindow("Invalid Age","Age must be 18 and above!\nPlease enter correct age and try again!");
            check = false;
        }
        if (maleBtn.isSelected())
        {
            accGender = "Male";
            maleBtn.setUserData("Male");
        } if (femaleBtn.isSelected())
        {
             accGender = "Female";
        } 
        
        mrgStatus = (String) maritalStatus.getValue();   
        if ( check )
        {
          if (bank.OpenAccount(fname, lname, amount, contact, email, address, mrgStatus,accType, age, accGender ))  
            { Main.setConfirmWindow("Action Successful","New Account Has Been Created!");
            backToMain();}
            else {
                Main.setAlertWindow("Action Denied","New Account Could Not be Created!");
            }
        } else {
             
                Main.setAlertWindow("Action Denied","New Account Could Not be Created!"); 
        }
     }
}
