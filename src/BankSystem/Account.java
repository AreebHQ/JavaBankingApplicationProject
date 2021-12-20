/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BankSystem;


import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.ObjectStreamException;
import java.io.Serializable;

/**
 *
 * @author areeb
 */
public class Account implements Serializable {

    private String FirstName;
    private String LastName;
    private double balance;
    private int AccNum;
    private String status;
    private String email;
    private String contact;
    private String address;
    private int age;
    private int nextAccNum = 0000;
    private String maritalStatus;
    private String accountType;
    private String gender;
    //private static final long serialVersionUID = 1L;
 
    //----------------------------------------------
    //Constructor -- initializes balance, owner, and account number
    //----------------------------------------------

    /**
     *
     * @param fname
     * @param lname
     * @param initBal
     * @param contact
     * @param email
     * @param address
     * @param maritalStatus
     * @param accType
     * @param age
     * @param accGender
     * @param accNum
     */
    public Account(String fname, String lname, double initBal, String contact, String email,String address, String maritalStatus, String accType,int age,String accGender,int accNum ) {
        super();
       
        AccNum = accNum;
        balance = initBal;
        FirstName = fname;
        LastName = lname;
        status = "Active";
        this.contact = contact;
        this.email = email;
        this.address = address;
        this.age = age;
        this.maritalStatus = maritalStatus;
        accountType = accType;
        gender = accGender;
        
    }
    
    /**
     *
     * @param maritalStatus
     */
    public void setMaritalStatus(String maritalStatus)
    {
        this.maritalStatus = maritalStatus;
    }

    /**
     *
     * @return
     */
    public String getmaritalStatus()
    {
        return maritalStatus;
    }

    /**
     *
     * @param address
     */
    public void setAddress(String address)
    {
        this.address = address;
    }

    /**
     *
     * @return
     */
    public String getAddress()
    {
        return address;
    }

    /**
     *
     * @param contact
     */
    public void setContact(String contact)
    {
        this.contact = contact;
    }

    /**
     *
     * @return
     */
    public String getContact(){
        return contact;
    }

    /**
     *
     * @param age
     */
    public void setAge(int age)
    {
        this.age = age;
    }

    /**
     *
     * @return
     */
    public int getAge()
    {
        return age;
    }
    
    /**
     *
     * @param accNumber
     */
    public  void setNextAccNum(int accNumber)
    {
          nextAccNum = accNumber;
    }

    /**
     *
     * @return
     */
    public  int getNextAccNum()
    {
        return nextAccNum;
    }
    
    /**
     *
     * @param newStatus
     */
    public void setStatus(String newStatus)
    {
        status = newStatus;
    }

    /**
     *
     * @return
     */
    public String getStatus()
    {
        return status;
    }
    
    /**
     *
     */
    public Account() {
        super();
    }
    
    /**
     *
     * @return
     */
    public int getAccNum() {
        return AccNum;
    }

    /**
     *
     * @param email
     */
    public void setEmail(String email)
    {
        this.email = email;
    }
    
    /**
     *
     * @return
     */
    public String getEmail()
    {
        return email;
    }

    /**
     *
     * @param acctype
     */
    public void setAccountType(String acctype)
    {
        accountType = acctype;
    }

    /**
     *
     * @return
     */
    public String getAccountType()
    {
        return accountType;
    }
    
    private void readObject(ObjectInputStream aInputStream) throws ClassNotFoundException, IOException {
        FirstName = aInputStream.readUTF();
        LastName = aInputStream.readUTF();
        balance = aInputStream.readDouble();
        AccNum = aInputStream.readInt();
        status = aInputStream.readUTF();
        email = aInputStream.readUTF();
        address = aInputStream.readUTF();
        maritalStatus = aInputStream.readUTF();
        accountType = aInputStream.readUTF();
        age = aInputStream.readInt();
        contact = aInputStream.readUTF();
        gender = aInputStream.readUTF();
    }

    private void writeObject(ObjectOutputStream aOutputStream) throws IOException {
        aOutputStream.writeUTF(FirstName);
        aOutputStream.writeUTF(LastName);
        aOutputStream.writeDouble(balance);
        aOutputStream.writeInt(AccNum);
        aOutputStream.writeUTF(status);
        aOutputStream.writeUTF(email);
        aOutputStream.writeUTF(address);
        aOutputStream.writeUTF(maritalStatus);
        aOutputStream.writeUTF(accountType);
        aOutputStream.writeInt(age);
        aOutputStream.writeUTF(contact);
        aOutputStream.writeUTF(gender);
    }

    private void readObjectNoData()
            throws ObjectStreamException {
    }

    /**
     *
     * @return
     */
    public String getName() {
        return (FirstName + " " + LastName);
    }

    /**
     *
     * @return
     */
    public String getFirstName() {
        return (FirstName );
    }

    /**
     *
     * @return
     */
    public String getLastName() {
        return ( LastName);
    }
    //----------------------------------------------
    // Checks to see if balance is sufficient for withdrawal.
    // If so, decrements balance by amount; if not, prints message.
    //----------------------------------------------
 
    /**
     *
     * @param amount
     * @return
     */
    public boolean withdraw(double amount) //throws Insufficient_Funds
  {
     
    if (balance >= amount)
    {balance -= amount;
    return true;}
    
    else
   
    {System.out.println("Insufficient funds");
     return false;}
    
  }
    //----------------------------------------------
    // Adds deposit amount to balance.
    //----------------------------------------------

    /**
     *
     * @param amount
     */
    public void deposit(double amount) {
        balance += amount;
    }

    //----------------------------------------------
    // Returns balance.
    //----------------------------------------------

    /**
     *
     * @return
     */
    public double getBalance() {
        return balance;
    }

    //----------------------------------------------
    // Returns a string containing the name, account number, and balance
    //----------------------------------------------
    public String toString() {
        return (FirstName + " " + LastName + "\t \t #000" + AccNum + "\t $" + balance + "\t" + accountType+ " \t"+ status);
    }

    /**
     *
     * @return
     */
    public String printAccDetails()
    {
      
         String data = (FirstName+" "+LastName +"\t"+ contact +"\t"+ email+"\t"+ age +"\t"+ gender + "\t    "+ maritalStatus);
         return data;
    }
    
    //---------------------------------------------------
    // Deducts $10 service fee 
    //---------------------------------------------------

    /**
     *
     * @return
     */
    public double chargeFee() {
        balance = balance - 10;
        return balance;
    }

    //----------------------------------------------
    // Changes the name on the account 
    //----------------------------------------------

    /**
     *
     * @param newfname
     * @param newlname
     */
    public void changeName(String newfname, String newlname) {
        FirstName = newfname;
        LastName = newlname;
    }
    
  

}
