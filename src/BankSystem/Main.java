/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BankSystem;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 *
 * @author areeb
 */
public class Main extends Application {
    
    private static Stage primaryStage, newAccStage, depositStage, withdrawStage, errorStage, customerDetailStage,  checkBalanceStage,helpStage, newUserStage, closeAccStage, logFileStage,aboutStage;
    private static AnchorPane newLayout;
    Bank bank;

    /**
     *
     * @param primaryStage
     * @throws IOException
     * @throws ClassNotFoundException
     */
    @Override
    public void start(Stage primaryStage) throws IOException, ClassNotFoundException {
       this.primaryStage = primaryStage;
        bank = new Bank();
        this.primaryStage.setTitle("A&A Banking System");
       // showMainOptions();  // uncomment to bypass login screen
        showMainView(); // make it comment to bypass login screen
 
      this.primaryStage.setOnCloseRequest(e ->{
            try {
                closeBank();
            } catch (IOException ex) {
                System.out.println("Exiting Safely");
            }
        });
      
    }
    
    /**
     *
     * @throws IOException
     */
    public static void showMainView() throws IOException
    {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Main.class.getResource("view/MainView.fxml"));
        newLayout = loader.load();
        Scene scene = new Scene(newLayout,604,650);
        primaryStage.setTitle("A&A Banking System");
        primaryStage.setScene(scene);
        primaryStage.show();    
    }
   
    /**
     *
     * @throws IOException
     */
    public static void showMainOptions() throws IOException
    {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Main.class.getResource("view/MainOptionsFXML.fxml"));
        newLayout = loader.load();
        Scene scene = new Scene(newLayout,650,420);
        primaryStage.setTitle("Main Options");
        primaryStage.setScene(scene);
        primaryStage.show();  
        
   
    }
    
    /**
     *
     * @throws IOException
     */
    public static void showMoreOptions() throws IOException
    {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Main.class.getResource("view/MoreOptionsFXML.fxml"));
        newLayout = loader.load();
        Scene scene = new Scene(newLayout,650,420);
        primaryStage.setTitle("More Options");
        primaryStage.setScene(scene);
        primaryStage.show();  
  
    }

    /**
     *
     * @throws IOException
     */
    public static void showAccountsScene() throws IOException
    {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Main.class.getResource("view/AllAccountsFXML.fxml"));
        newLayout = loader.load();
        Scene scene = new Scene(newLayout,801,420);
        primaryStage.setTitle("Acounts List");
        primaryStage.setScene(scene);
        primaryStage.show();          
    }

    /**
     *
     * @param title
     * @param msg
     */
    public static void setAlertWindow(String title, String msg)
    {
        Alert a1 = new Alert(Alert.AlertType.ERROR);
        a1.setTitle(title);
        a1.setContentText(msg);
        a1.setHeaderText(null);
        a1.showAndWait();
    }

    /**
     *
     * @param title
     * @param msg
     */
    public static void setConfirmWindow(String title, String msg)
    {
        Alert a1 = new Alert(Alert.AlertType.CONFIRMATION);
        a1.setTitle(title);
        a1.setContentText(msg);
        a1.setHeaderText(null);
        a1.showAndWait();
    }
    
    /**
     *
     * @throws IOException
     */
    public static void showAddStage() throws IOException
    {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Main.class.getResource("view/AddNewAcc.fxml"));
        BorderPane addNewAccount = loader.load();
        newAccStage = new Stage();
        newAccStage.initModality(Modality.WINDOW_MODAL);
        newAccStage.initOwner(primaryStage);
        newAccStage.setTitle("NewAccount");
        Scene scene = new Scene(addNewAccount);
        newAccStage.setScene(scene);
        newAccStage.showAndWait();
    }
    
    /**
     *
     * @throws IOException
     */
    public static void showDepositStage() throws IOException
    {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Main.class.getResource("view/DepositFXML.fxml"));
        BorderPane depositPane = loader.load();
        depositStage = new Stage();
        depositStage.initModality(Modality.WINDOW_MODAL);
        depositStage.initOwner(primaryStage);
        depositStage.setTitle("Deposit");
        Scene scene = new Scene(depositPane);
        depositStage.setScene(scene);
        depositStage.showAndWait();  
    }
    
    /**
     *
     * @throws IOException
     */
    public static void showWithdrawStage() throws IOException
    {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Main.class.getResource("view/WithdrawFXML.fxml"));
        BorderPane withdrawPane = loader.load();
        withdrawStage = new Stage();
        withdrawStage.initModality(Modality.WINDOW_MODAL);
        withdrawStage.initOwner(primaryStage);
        withdrawStage.setTitle("Withdraw");
        Scene scene = new Scene(withdrawPane);
        withdrawStage.setScene(scene);
        withdrawStage.showAndWait();
    }
    
    /**
     *
     * @throws IOException
     */
    public static void showCustomerDetailStage() throws IOException
    {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Main.class.getResource("view/CustomerDetailFXML.fxml"));
        AnchorPane customerDetailPane = loader.load();
        customerDetailStage = new Stage();
        customerDetailStage.initModality(Modality.WINDOW_MODAL);
        customerDetailStage.initOwner(primaryStage);
        customerDetailStage.setTitle("CustomerDetails");
        Scene scene = new Scene(customerDetailPane);
        customerDetailStage.setScene(scene);
        customerDetailStage.showAndWait();
    }
    
    /**
     *
     * @throws IOException
     */
    public static void showCheckBalanceStage() throws IOException
    {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Main.class.getResource("view/CheckBalanceFXML.fxml"));
        AnchorPane customerDetailPane = loader.load();
        checkBalanceStage = new Stage();
        checkBalanceStage.initModality(Modality.WINDOW_MODAL);
        checkBalanceStage.initOwner(primaryStage);
        checkBalanceStage.setTitle("CheckBalance");
        Scene scene = new Scene(customerDetailPane);
        checkBalanceStage.setScene(scene);
        checkBalanceStage.showAndWait();
    }
     
    /**
     *
     * @throws IOException
     */
    public static void showNewUserStage() throws IOException
    {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Main.class.getResource("view/AddNewUserFXML.fxml"));
        AnchorPane newUser = loader.load();
        newUserStage = new Stage();
        newUserStage.initModality(Modality.WINDOW_MODAL);
        newUserStage.initOwner(primaryStage);
        newUserStage.setTitle("NewUser");
        Scene scene = new Scene(newUser);
        newUserStage.setScene(scene);
        newUserStage.showAndWait();
    }
    
    /**
     *
     * @throws IOException
     */
    public static void showCloseAccStage() throws IOException
    {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Main.class.getResource("view/CloseAccountFXML.fxml"));
        AnchorPane closeAccoutnPane = loader.load();
        closeAccStage = new Stage();
        closeAccStage.initModality(Modality.WINDOW_MODAL);
        closeAccStage.initOwner(primaryStage);
        closeAccStage.setTitle("CloseAccount");
        Scene scene = new Scene(closeAccoutnPane);
        closeAccStage.setScene(scene);
        closeAccStage.showAndWait();
    }

    /**
     *
     * @throws IOException
     */
    public static void showLogStage() throws IOException
    {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Main.class.getResource("view/ShowLogFileFXML.fxml"));
        AnchorPane logStage = loader.load();
        logFileStage = new Stage();
        logFileStage.initModality(Modality.WINDOW_MODAL);
        logFileStage.initOwner(primaryStage);
        logFileStage.setTitle("LogFile");
        Scene scene = new Scene(logStage);
        logFileStage.setScene(scene);
        logFileStage.showAndWait();
    }
       
     /**
     *
     * @throws IOException
     */
    public static void showAboutStage() throws IOException
    {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Main.class.getResource("view/ShowAboutFXML.fxml"));
        AnchorPane setaboutStage = loader.load();
        aboutStage = new Stage();
        aboutStage.initModality(Modality.WINDOW_MODAL);
        aboutStage.initOwner(primaryStage);
        aboutStage.setTitle("About");
        Scene scene = new Scene(setaboutStage);
        aboutStage.setScene(scene);
        aboutStage.showAndWait();
    }
    
    
    public static void showHelp() throws Exception {
       FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Main.class.getResource("view/ShowHelpFXML.fxml"));
        AnchorPane setHelpStage = loader.load();
        helpStage = new Stage();
        helpStage.initModality(Modality.WINDOW_MODAL);
        helpStage.initOwner(primaryStage);
         helpStage.setTitle("Help");
        Scene scene = new Scene(setHelpStage);
        helpStage.setScene(scene);
        helpStage.showAndWait();
    }
     public static void closeHelpStage()throws IOException
    {
        helpStage.close();
    } 
    
    
    
      /**
     *
     * @throws IOException
     */
    public static void closeAboutStage()throws IOException
    {
        aboutStage.close();
    }  
    
    
    
    /**
     *
     * @throws IOException
     */
    public static void bankFrmshowLogStage()throws IOException
    {
        logFileStage.close();
    }   

    /**
     *
     * @throws IOException
     */
    public static void bankToMorefrmCloseAccount()throws IOException
    {
        closeAccStage.close();
    }
       
    /**
     *
     * @throws IOException
     */
    public static void bankToMorefrmNewUser()throws IOException
    {
        newUserStage.close();
    }
     
    /**
     *
     * @throws IOException
     */
    public static void bankToMorefrmCheckBalance()throws IOException
    {
        checkBalanceStage.close();
    }
     
    /**
     *
     * @throws IOException
     */
    public static void bankToMainfrmCustomerDetail()throws IOException
    {
        customerDetailStage.close();
    }
    
    /**
     *
     * @throws IOException
     */
    public static void backToMainfrmNewAccount() throws IOException
    {
        newAccStage.close();  
    }
    
    /**
     *
     * @throws IOException
     */
    public static void backToMainfrmDeposit() throws IOException
    {
        depositStage.close();  
    }
    
    /**
     *
     * @throws IOException
     */
    public static void backToMainfrmWithdraw() throws IOException
    {
        withdrawStage.close();  
    }

    /**
     *
     * @throws IOException
     */
    public void closeBank() throws IOException
    {
        bank.exitBankSafely();
    }

    /**
     *
     * @param args
     * @throws ClassNotFoundException
     */
    public static void main(String[] args) throws ClassNotFoundException {
       
        launch(args);
    }
    
}
