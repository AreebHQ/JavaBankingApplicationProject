
package BankSystem.view;

import BankSystem.Main;
import java.io.IOException;
import javafx.fxml.FXML;


public class MainOptionsFXMLController {
    
     Main main;
    
    @FXML
    private void goAccounts() throws IOException
    {
        main.showAccountsScene();
    }
    
     @FXML
    private void addAccBtn() throws IOException
    {
        main.showAddStage();
    }
    
    @FXML
    public void showDeposit() throws IOException
    {
        main.showDepositStage();
    }
    
    @FXML
    public void showWithdraw() throws IOException
    {
        main.showWithdrawStage();
    }
    
    @FXML
    public void showCustomerDetail() throws IOException
    {
        main.showCustomerDetailStage();
    }
    
    @FXML
    public void showMoreOptions() throws IOException
    {
        main.showMoreOptions();
    }
    
    
    @FXML
    public void showLogfile() throws IOException
    {
        main.showLogStage();
    }
    
   
     @FXML
    public void showAbout() throws IOException
    {
        Main.showAboutStage();
    }
    
      @FXML
    public void showHelp() throws IOException, Exception
    {
        main.showHelp();
    }
    
    
    @FXML
    public void exitApp()
    {
        System.exit(0);
    }
    
}
