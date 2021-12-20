/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BankSystem.view;

import BankSystem.Account;
import BankSystem.Bank;
import BankSystem.Log;
import BankSystem.Main;
import java.io.IOException;
import java.util.logging.Level;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;


public class AllAccountsFXMLController {
    

    Bank bank;
    
    @FXML
    private TextFlow generalTab;
    
    @FXML
    private TextFlow detailTab;
    
    @FXML
    private Text generalText;
    @FXML
    private Text generalText1;
    
    @FXML
    private TextArea generaltxtArea;
    @FXML
    private Text generalHeader;
     @FXML
    private Text generalHeader1;
    
    
    
    public AllAccountsFXMLController() throws ClassNotFoundException{
        bank = new Bank();   
         
    }
    
    private void setHeader()
    {
        generalHeader.setText("Full Name\t\tAccNo.\t Balance\tAccType\t      AccStatus");
        generalHeader.setStyle("-fx-font-family: monospace");
        generalHeader1.setText("FullName\tContact\t\tEmail\t\t\tAge\tGender\t    M.Status");
        generalHeader1.setStyle("-fx-font-family: monospace");
    }
    
    @FXML
    private void showAllAccounts()
    {
       setHeader();
       bank.showFXMLAllAccounts(generalText);
       generalText.setStyle("-fx-font-family: monospace");
       bank.showFXMLAllAccountsDetails(generalText1);
       generalText1.setStyle("-fx-font-family: monospace");
    }
    
    
    @FXML
    private void showActiveAccounts()
    {
        setHeader();
        bank.showFXMLActiveAccounts(generalText);
        generalText.setStyle("-fx-font-family: monospace");
        bank.showFXMLActiveAccountsDetails(generalText1);
        generalText1.setStyle("-fx-font-family: monospace");
       
    }
    
    
    @FXML
    private void showClosedAccounts()
    {  
        setHeader();
        bank.showFXMLClosedAccounts(generalText);
        generalText.setStyle("-fx-font-family: monospace");
         bank.showFXMLClosedAccountsDetails(generalText1);
        generalText1.setStyle("-fx-font-family: monospace");
    }
    
     
    @FXML
    private void showAllAccountsDetails()
    {
       setHeader();
       bank.showFXMLAllAccountsDetails(generalText1);
       generalText1.setStyle("-fx-font-family: monospace");
    }
    
    
    @FXML
    private void showActiveAccountsDetails()
    {
        setHeader();
        bank.showFXMLActiveAccountsDetails(generalText1);
        generalText1.setStyle("-fx-font-family: monospace");
       
    }
    
    
    @FXML
    private void showClosedAccountsDetails()
    {  
        setHeader();
        bank.showFXMLClosedAccountsDetails(generalText1);
        generalText1.setStyle("-fx-font-family: monospace");
    }
    
    
    
    
    
     @FXML
    private void backToMain()  throws IOException
    {

       Main.showMainOptions();
 
    }
    
}
