/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BankSystem;
import BankSystem.Main;
import java.util.logging.*;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.text.Text; 
import javafx.scene.text.TextFlow;

/**
 *
 * @author areeb
 */
public class Bank {

    /**
     *
     */
    public  Map<Integer, Account> listOfAccounts = new HashMap<>();

    /**
     *
     */
    public  Log logActivity;
    Main main;
    
   
     /* @Bank
    Constructor -- Open Bank.txt and Account.txt files, read Account objects from file
    Read total number of account from Account.txt file 
    Insert Account objects into hashmap as <account no, account object> */

    /**
     *
     * @throws ClassNotFoundException
     */

    public Bank() throws ClassNotFoundException {

        try {
            
            logActivity = new Log("Bank_log.txt"); // creating new logfile
            logActivity.logs.setLevel(Level.ALL); //setting activity levels to record all type (fine, info, warning etc)
            logActivity.logs.info("Application Started!");
                  
            
            Scanner readFile = new Scanner(new File("AccountNo.txt"));
            int totalAcc = Integer.parseInt( readFile.next() );
            FileInputStream fileInStream = new FileInputStream(new File("Bank.txt"));
            ObjectInputStream objInStream = new ObjectInputStream(fileInStream);
            logActivity.logs.info("Account.txt & Bank.txt files Accessed!");
            System.out.println("Total Accounts: " + totalAcc);
            listOfAccounts = (Map<Integer, Account>) objInStream .readObject();
            
           
            readFile.close();
            fileInStream.close();
            objInStream .close();

        } catch (IOException e) {
            System.out.println(e.getMessage() + "\n");
            logActivity.logs.warning("Exception Error! \t Error reading the Accounts!");
        }
    }

    /*@ OpenAccount
     Create a file name Account.txt and save total no of acounts
     Create an Account object with the pram provided
     Insert the account objects in Hashmap <Account No., Account Object>
     Save the acounts objects to Bank.txt file*/
   
    /**
     *
     * @param fname
     * @param lname
     * @param initBal
     * @param contact
     * @param email
     * @param address
     * @param maritalStatus
     * @param acctype
     * @param age
     * @param accGender
     * @return
     */
    public boolean OpenAccount(String fname, String lname, double initBal, String contact, String email,String address, String maritalStatus,String acctype, int age, String accGender)
    {
  
        int accNo=0000;
        //System.out.println(listOfAccounts.keySet());
        while(listOfAccounts.keySet().contains(++accNo));
       // System.out.println("accNo: "+accNo); 
         
        Account acc_obj = new Account( fname,  lname,  initBal,  contact, email, address, maritalStatus,acctype, age,accGender, accNo );
        
        logActivity.logs.log(Level.INFO, "New Account Created! \t Account No:{0}", accNo);
        
        try {
            
            File accountNoFile = new File("AccountNo.txt");
            FileWriter fw = new FileWriter(accountNoFile);
            PrintWriter pw = new PrintWriter(fw);

           listOfAccounts.put(acc_obj.getAccNum(), acc_obj);
            int totalAccounts = listOfAccounts.size();
            pw.println(totalAccounts);
            writeListToFile(  listOfAccounts  );
            
            
            System.out.println("Total Accounts saved: " + totalAccounts);     
            pw.close();
           
         
        } catch (IOException e) {
            System.out.println(e.getMessage());
            return false;
        } catch (Exception e) {
            System.out.println("Error! OpenAccount Method");
            logActivity.logs.warning("Exception Error! \t Couldn't create a New Account!");
            return false;
        }

        return true;
    }
    
    /**
     *
     * @param accNum
     * @param bal
     * @return
     */
    public boolean deposit(int accNum, double bal) {
        try {
            Entry<Integer, Account> i;
            Iterator<Entry<Integer, Account>> it = listOfAccounts.entrySet().iterator();
            if (listOfAccounts.isEmpty())
                {
                    System.out.println("No Account Found!");
                    return false;
                }
            while (it.hasNext()) {
                i = it.next();

                if (i.getKey() == accNum && isActive(accNum)) {
                    i.getValue().deposit(bal);
                    logActivity.logs.log(Level.INFO, "Ammount ${0} Deposited into Account No: {1}", new Object[]{bal, accNum});
                    System.out.println("Amount $" +bal+ " has been deposited!");
                    writeListToFile(listOfAccounts);
                    return true;
                }
            }
            System.out.println("Account No:" + accNum + " does not exist!");
            logActivity.logs.log(Level.INFO,"Amount Could not be Deposited, Account No:" + accNum + " does not exist!");
          
        } catch (IOException e) {
            System.out.println("Amount Could not be Deposited! Please Try Again!");
            logActivity.logs.log(Level.WARNING, "Exception Error! \t Amount Deposit Error for Account No:{0}", accNum);
           
        }
        return false;
       
    }

    /**
     *
     * @param accNum
     * @return
     */
    public Account checkBalance(int accNum) {
        try {
            Entry<Integer, Account> i;
            Iterator<Entry<Integer, Account>> it = listOfAccounts.entrySet().iterator();
            while (it.hasNext()) {
                i = it.next();
                if (i.getKey() == accNum && isActive(accNum)) {
                    return i.getValue();
                }
            }
            logActivity.logs.log(Level.INFO, " Balance Checked! Account No:{0}", accNum);
          
        } catch (Exception e) {
            System.out.println("Error In Checking Balance! \n Please Check the Account details!");
            logActivity.logs.log(Level.WARNING, "Exception Error! \t Balance Check Error for Account No:{0}", accNum);
        }
        return null;
    }
    
    /**
     *
     * @param accNum
     * @return
     */
    public double getBalance(int accNum) {
        try {
            Entry<Integer, Account> i;
            Iterator<Entry<Integer, Account>> it = listOfAccounts.entrySet().iterator();
            while (it.hasNext()) {
                i = it.next();
                if (i.getKey() == accNum && isActive(accNum)) {
                    
                    logActivity.logs.log(Level.INFO, " Balance Checked! Account No:{0}", accNum);
                    return i.getValue().getBalance();
                }
            }
            
          
        } catch (Exception e) {
            System.out.println("Error In Checking Balance! \n Please Check the Account details!");
            logActivity.logs.log(Level.WARNING, "Exception Error! \t Balance Check Error for Account No:{0}", accNum);
        }
        return 0;
    }
    
    /**
     *
     * @param accNum
     * @param bal
     * @return
     */
    public boolean withdraw(int accNum, double bal) {
        try {
            boolean check = true;
            Entry<Integer, Account> i;
            Iterator<Entry<Integer, Account>> it = listOfAccounts.entrySet().iterator();
            if (listOfAccounts.isEmpty())
                {
                    System.out.println("No Account Found!");
                    return false;
                }
            while (it.hasNext() && isActive(accNum)) {
                i = it.next();
                if (i.getKey() == accNum) {
                   check =  i.getValue().withdraw(bal);
                   
                   if (check){
                    logActivity.logs.log(Level.INFO, "Ammount ${0} Withdrawn from Account No: {1}", new Object[]{bal, accNum});
                    System.out.println("Amount $" +bal+ " has been withdrawn!");
                    writeListToFile(listOfAccounts);
                    return true;}
                   else {
                       System.out.println("Insufficient funds");
                       logActivity.logs.log(Level.INFO,"Amount Could not be Withdrawn, Account No:" + accNum + " has insufficient funds!");
                       main.setAlertWindow("Action Denied","Insufficient funds!\n Please try again!");
                       return false;
                   }
                }
            }
            System.out.println("Account No:" + accNum + " does not exist!");
            logActivity.logs.log(Level.INFO,"Amount Could not be Withdrawn, Account No:" + accNum + " does not exist!");
    
        } catch (IOException e) {
            System.out.println("Amount Could not be Withdrawn! Please Try Again!");
            logActivity.logs.log(Level.WARNING,"Exception Error! \t Amount Could not be Withdrawn from Account No:" + accNum );
            return false;
        }
        return false;
    }

    /**
     *
     * @param accNum
     */
    public void closeAccount(int accNum) {
        try {
            Entry<Integer, Account> i;
            Iterator<Entry<Integer, Account>> it = listOfAccounts.entrySet().iterator();
          
           while (it.hasNext()) {
                i = it.next();
                                              
                if (i.getKey() == accNum ) {
                    
                         i.getValue().setStatus("Closed");
                         System.out.println("Account Number: " + accNum + " has been Closed!");
                         logActivity.logs.log(Level.WARNING,"Account No: {0} was Closed", accNum);
                         Main.setAlertWindow("Action Successful!", "Account has been closed!");
                         writeListToFile(listOfAccounts);
                         return;
                    
                }  
            } System.out.println("Account No:" + accNum + " was not found!");            
          
        } catch (IOException e) {
            System.out.println("Error! Account was not Closed! Please try again!");
            logActivity.logs.log(Level.WARNING,"Exception Error! \t Failed to Close Account No: {0}", accNum);  
            Main.setAlertWindow("Action Denied!", "Account was not cloased!");
        }     
    }

    /**
     *
     */
    public void showActiveAccounts() {
        
        if (listOfAccounts.isEmpty())
        {
            System.out.println("Error! No Account Found!");
            logActivity.logs.log(Level.INFO,"Error! \t Account List Empty!");
            return;
        }
        
        for (Account acc : listOfAccounts.values()) 
        {
            if (acc.getStatus().equals("Active"))
            System.out.println(acc + "\n");
            
            
        }
        logActivity.logs.log(Level.INFO,"Show List of Active Accounts!");
    }
    
    /**
     *
     */
    public void showAllAccounts() {
        
        if (listOfAccounts.isEmpty())
        {
            System.out.println("Error! No Account Found!");
            logActivity.logs.log(Level.INFO,"Error! \t Account List Empty!");
            return;
        }
        
        for (Account acc : listOfAccounts.values()) 
        {
            
            System.out.println(acc + "\n");
            
        }
        logActivity.logs.log(Level.INFO,"Show List of All Accounts!");
    }
    
    /**
     *
     * @param accNum
     * @return
     */
    public boolean isActive (int accNum)
    {
        for (Account acc : listOfAccounts.values()) 
        {
            
            if (acc.getAccNum() == accNum)
            { if (acc.getStatus().equals("Active"))
                return true;
            }
        }
        return false;
    }
    
    /**
     *
     * @param accNum
     * @return
     */
    public String getFirstName(int accNum)
    {
          
            Entry<Integer, Account> i;
            Iterator<Entry<Integer, Account>> it = listOfAccounts.entrySet().iterator();
          
           while (it.hasNext()) {
                i = it.next();
                                              
                if (i.getKey() == accNum )
                {
                  return  i.getValue().getFirstName();
                }
             }
            
           return null;
    }

    /**
     *
     * @param accNum
     * @return
     */
    public String getLastName(int accNum)
    {
          
            Entry<Integer, Account> i;
            Iterator<Entry<Integer, Account>> it = listOfAccounts.entrySet().iterator();
          
           while (it.hasNext()) {
                i = it.next();
                                              
                if (i.getKey() == accNum )
                {
                  return  i.getValue().getLastName();
                }
             }
            
           return null;
    }
    
    /**
     *
     */
    public void showClosedAccounts() {
        
        if (listOfAccounts.isEmpty())
        {
            System.out.println("Error! No Account Found!");
            logActivity.logs.log(Level.INFO,"Error! \t Account List Empty!");
            return;
        }
        
        for (Account acc : listOfAccounts.values()) 
        {
            if (acc.getStatus().equals("Closed"))
            System.out.println(acc + "\n");
            
        }
        logActivity.logs.log(Level.INFO,"Show List of Closed Accounts!");
    }
    
    /**
     *
     * @param accNum
     * @return
     */
    public String getAccountEmail(int accNum)
    {
          
            Entry<Integer, Account> i;
            Iterator<Entry<Integer, Account>> it = listOfAccounts.entrySet().iterator();
          
           while (it.hasNext()) {
                i = it.next();
                                              
                if (i.getKey() == accNum )
                {
                  return  i.getValue().getEmail();
                }
             }
            
           return null;
    }
     
    /**
     *
     * @param accNum
     * @return
     */
    public String getAccountContact(int accNum)
    {
          
            Entry<Integer, Account> i;
            Iterator<Entry<Integer, Account>> it = listOfAccounts.entrySet().iterator();
          
           while (it.hasNext()) {
                i = it.next();
                                              
                if (i.getKey() == accNum )
                {
                  return  i.getValue().getContact();
                }
             }
            
           return null;
    }

    /**
     *
     * @param accNum
     * @return
     */
    public String getAccountType(int accNum)
    {
          
            Entry<Integer, Account> i;
            Iterator<Entry<Integer, Account>> it = listOfAccounts.entrySet().iterator();
          
           while (it.hasNext()) {
                i = it.next();
                                              
                if (i.getKey() == accNum )
                {
                  return  i.getValue().getAccountType();
                }
             }
            
           return null;
    }

    /**
     *
     * @param accNum
     * @return
     */
    public String getAccountAddress(int accNum)
    {
          
            Entry<Integer, Account> i;
            Iterator<Entry<Integer, Account>> it = listOfAccounts.entrySet().iterator();
          
           while (it.hasNext()) {
                i = it.next();
                                              
                if (i.getKey() == accNum )
                {
                  return  i.getValue().getAddress();
                }
             }
            
           return null;
    }
    
    /**
     *
     * @param accNum
     * @return
     */
    public String getAccAge(int accNum)
    {
          
            Entry<Integer, Account> i;
            Iterator<Entry<Integer, Account>> it = listOfAccounts.entrySet().iterator();
          
           while (it.hasNext()) {
                i = it.next();
                                              
                if (i.getKey() == accNum )
                {
                  return  Integer.toString(i.getValue().getAge());
                }
             }
            
           return null;
    }

    /**
     *
     * @param accNum
     * @return
     */
    public String getAccMaritialStatus(int accNum)
    {
          
            Entry<Integer, Account> i;
            Iterator<Entry<Integer, Account>> it = listOfAccounts.entrySet().iterator();
          
           while (it.hasNext()) {
                i = it.next();
                                              
                if (i.getKey() == accNum )
                {
                  return  i.getValue().getmaritalStatus();
                }
             }
            
           return null;
    }
   
    /**
     *
     * @param generalText
     */
    public void showFXMLClosedAccounts(Text generalText)
    {
           generalText.setText("");
           
         if (listOfAccounts.isEmpty())
        {
            System.out.println("Error! No Account Found!");
            generalText.setText("No Account Found!");
            logActivity.logs.log(Level.INFO,"Error! \t Account List Empty!");
            return;
        }
        
        for (Account acc : listOfAccounts.values()) 
        {   
            if (acc.getStatus().equals("Closed"))
            {System.out.println(acc + "\n");
            generalText.setText(generalText.getText() + "\n" + acc.toString());
            }
            
        }
         
         logActivity.logs.log(Level.INFO,"Called List of Closed Accounts!");
        }
    
    /**
     *
     * @param generalText
     */
    public void showFXMLClosedAccountsDetails(Text generalText)
    {
           generalText.setText("");
           
         if (listOfAccounts.isEmpty())
        {
            System.out.println("Error! No Account Found!");
            generalText.setText("No Account Found!");
            logActivity.logs.log(Level.INFO,"Error! \t Account List Empty!");
            return;
        }
        
        for (Account acc : listOfAccounts.values()) 
        {
            
            if (acc.getStatus().equals("Closed"))
            {System.out.println(acc + "\n");
           
            generalText.setText(generalText.getText() + "\n" + acc.printAccDetails());
            }
            
        }
         
         logActivity.logs.log(Level.INFO,"Called List of Closed Accounts!");
        }

    /**
     *
     * @param generalText
     */
    public void showFXMLActiveAccounts(Text generalText)
    {
      generalText.setText("");
         if (listOfAccounts.isEmpty())
        {
            System.out.println("Error! No Account Found!");
            generalText.setText("No Account Found!");
            logActivity.logs.log(Level.INFO,"Error! \t Account List Empty!");
            return;
        }
        
        for (Account acc : listOfAccounts.values()) 
        {
            if (acc.getStatus().equals("Active")){   
            System.out.println(acc + "\n");
           generalText.setText(generalText.getText() + "\n" + acc.toString());}
            
        }
         
        logActivity.logs.log(Level.INFO,"Show List of Active Accounts!");
    }
    
    /**
     *
     * @param generalText
     */
    public void showFXMLActiveAccountsDetails(Text generalText)
    {
      generalText.setText("");
         if (listOfAccounts.isEmpty())
        {
            System.out.println("Error! No Account Found!");
            generalText.setText("No Account Found!");
            logActivity.logs.log(Level.INFO,"Error! \t Account List Empty!");
            return;
        }
        
        for (Account acc : listOfAccounts.values()) 
        {
            if (acc.getStatus().equals("Active"))  {
            System.out.println(acc + "\n");
           generalText.setText(generalText.getText() + "\n" + acc.printAccDetails());}
            
        }
         
        logActivity.logs.log(Level.INFO,"Show List of Active Accounts!");
    }

    /**
     *
     * @param generalText
     */
    public void showFXMLAllAccounts(Text generalText)
    {
        generalText.setText("");
          if (listOfAccounts.isEmpty())
        {
            System.out.println("Error! No Account Found!");
            generalText.setText("No Account Found!");
            logActivity.logs.log(Level.INFO,"Error! \t Account List Empty!");
            return;
        }
        
        for (Account acc : listOfAccounts.values()) 
        {
            
            System.out.println(acc + "\n");
            
            //generalText.setText(generalText.getText() + "\n" + acc.printObject());
           generalText.setText(generalText.getText() + "\n" + acc.toString());
   
        }
        
        logActivity.logs.log(Level.INFO,"Show List of All Accounts!");

    }
   
    /**
     *
     * @param generalText
     */
    public void showFXMLAllAccountsDetails(Text generalText)
    {
        generalText.setText("");
          if (listOfAccounts.isEmpty())
        {
            System.out.println("Error! No Account Found!");
            generalText.setText("No Account Found!");
            logActivity.logs.log(Level.INFO,"Error! \t Account List Empty!");
            return;
        }
        
        for (Account acc : listOfAccounts.values()) 
        {
            
            System.out.println(acc + "\n");
            
            //generalText.setText(generalText.getText() + "\n" + acc.printObject());
           generalText.setText(generalText.getText() + "\n" + acc.printAccDetails());
   
        }
        
        logActivity.logs.log(Level.INFO,"Show List of All Accounts!");

    }
   
    /**
     *
     * @throws IOException
     */
    public void exitBankSafely() throws IOException{
        writeListToFile(listOfAccounts);
        logActivity.logs.log(Level.INFO,"Exit Application!");
    }

    private void writeListToFile(Map<Integer, Account> listOfAccounts) throws IOException, FileNotFoundException {
        FileOutputStream fileOstream = new FileOutputStream("Bank.txt");
        ObjectOutputStream objOstream = new ObjectOutputStream(fileOstream);
        
        objOstream.writeObject(listOfAccounts);
        objOstream.close();
        fileOstream.close();
        objOstream.flush();
        logActivity.logs.log(Level.INFO,"Account list Updated!");
    }
        
}
