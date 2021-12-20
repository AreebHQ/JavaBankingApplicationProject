/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BankSystem;

import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author areeb
 */
public class DataValidation  {
    Main main;
    Scanner scnr = new Scanner(System.in);
    boolean check;
    Bank bnkAcc;

    /**
     *
     * @throws ClassNotFoundException
     */
    public DataValidation() throws ClassNotFoundException {
        bnkAcc = new Bank();
    }
    
    /**
     *
     * @param email
     * @return
     */
    public boolean checkEmail(String email)
    {
        Pattern pattern = Pattern.compile("[a-zA-Z_0-9]{2,30}@[a-zA-Z_0-9]{2,30}.edu|com|net"); //cehck email address
       
        try {Matcher matcher = pattern.matcher(email);
        boolean matchFound = matcher.find();
        
            if(matchFound)
            {
                System.out.println("Email verified!");
                return true;
            } 
        throw new InvalidInputException();
           
        }
        catch (InvalidInputException x)
        {
              main.setAlertWindow("Invalid Email", "Invalid Input! \nPlease Enter the Email again!");
              return false;
        }
  
    }
  
    /**
     *
     * @param data
     * @return
     * @throws IOException
     */
    public boolean checkAmount(String data) throws IOException
    {   
        double num;
        
               try { num = Double.parseDouble(data);
                       if (num < 500)
                        {throw new InvalidInputException();}
                   }
               catch (InputMismatchException  | NumberFormatException e) {
                     main.setAlertWindow("Invalid Input", "Invalid Input! \nPlease Enter the amount again!");
                     System.out.println("Invalid Input! Please Enter the amount again: ");
                     bnkAcc.logActivity.logs.info("Error: \t  Invalid Input!");
                     return false;
                     }
               catch (InvalidInputException e)
               {
                     main.setAlertWindow("Insufficient Amount", "Insufficient Amount! \nAmount should be atleast $500."
                            + " Please try again");
                     System.out.println("Insufficient Amount! \nAmount should be atleast $500."
                            + " Please try again: ");
                     bnkAcc.logActivity.logs.info("Insufficient Amount Error: \t  Ammount $"+ data +" is less than $500.");
                     return false;
               }

        return true;
    }

    /**
     *
     * @param data
     * @param field
     * @return
     * @throws IOException
     */
    public boolean checkifEmpty(String data, String field) throws IOException
    {   
       
        
               try { 
                       if (data.isEmpty() || data.equals(" "))
                        {throw new InvalidInputException();}
                   }
             
               catch (InvalidInputException e)
               {
                     main.setAlertWindow("Invalid " + field, "Pleae Fill "+ field
                            + " and try again");
                     
                     bnkAcc.logActivity.logs.info("Empty Fields Error: \t  "+ field+" is incomplete.");
                     return false;
               }

        return true;
    }
    
    /**
     *
     * @param num
     * @return
     */
    public boolean checkAmountRange(double num)
    {

        if (num < 500)
            return false;
     
        return true;
    }
    
    /**
     *
     * @param data
     * @return
     * @throws IOException
     */
    public int checkAccount(String data) throws IOException
    {  
        int AccNum;
        
               try { AccNum = Integer.parseInt(data);
                    
                     if (bnkAcc.isActive(AccNum))
                         {check = bnkAcc.isActive(AccNum);}
                     else 
                     { throw new InvalidInputException();} 
                   }  
               catch (InputMismatchException  | NumberFormatException e) {
                    main.setAlertWindow("Invalid Input", "Invalid Account Num! \nPlease try again");
                    System.out.println("Invalid Account Num! Please try again:");
                    
                    bnkAcc.logActivity.logs.info(" Error: \t Invalid Account Num: " + data);
                    check = false;  } 
               catch (InvalidInputException e)
               {
                   
                     System.out.println("Account does not Exist or is Inactive");
                     bnkAcc.logActivity.logs.info(" Error: \t Account No:" + data + " does not Exist or is Inactive.");
                     check = false;  }
              
            
           AccNum = Integer.parseInt(data);
         return AccNum;
    }
}

        