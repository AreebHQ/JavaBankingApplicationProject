/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BankSystem.view;

import BankSystem.Main;
import java.io.IOException;
import javafx.fxml.FXML;

/**
 *
 * @author areeb
 */
public class MoreOptionsFXMLController {
    
 
   @FXML
    private void gotoCheckBalance() throws IOException
    {
        Main.showCheckBalanceStage();
    }
    
    @FXML
     private void goBackMainOptions() throws IOException
    {
        Main.showMainOptions();
    }
     
     
     @FXML
     private void showAddNewUser() throws IOException
     {
         Main.showNewUserStage();
     }
     
     
     @FXML
    private void gotoCloseAccount() throws IOException
    {
        Main.showCloseAccStage();
    }
    
     @FXML
    public void showLogfile() throws IOException
    {
        Main.showLogStage();
    }

    @FXML
    public void showAbout() throws IOException
    {
        Main.showAboutStage();
    }

    @FXML
    public void showHelp() throws IOException, Exception
    {
        Main.showHelp();
    }
     
    
     @FXML
    public void exitApp()
    {
        System.exit(0);
    }
 
}
