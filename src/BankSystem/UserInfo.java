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

/**
 *
 * @author areeb
 */
public class UserInfo {
    
    private String userFirstName;
    private String userLastName;
    private String userID;
    private String userPassword;
    private String userStatus;
    
public UserInfo(String fname, String lname, String id, String pass){
    super();
    userFirstName = fname;
    userLastName = lname;
    userID = id;
    userPassword = pass;
    }


public void setName(String fname, String lname){ userFirstName = fname; userLastName = lname;}
public void setUserID(String id){ userID = id;}
public void setUserPassword(String pass){ userPassword = pass;}
public void serUserStatus(String status){userStatus = status;}
public String getFistname(){return userFirstName;}
public String getLastname(){return userLastName;}
public String getUserID(){return userID;}
public String getPassword(){return userPassword;}
public String getUserStatus(){return userStatus;}

public void readObject(ObjectInputStream aInputStream) throws ClassNotFoundException, IOException {
        userFirstName = aInputStream.readUTF();
        userLastName = aInputStream.readUTF();
        userPassword = aInputStream.readUTF();
        userID = aInputStream.readUTF();
        userStatus = aInputStream.readUTF();
    }

public void writeObject(ObjectOutputStream aOutputStream) throws IOException {
        aOutputStream.writeUTF(userFirstName);
        aOutputStream.writeUTF(userLastName);
        aOutputStream.writeUTF(userPassword);
        aOutputStream.writeUTF(userID);
        aOutputStream.writeUTF(userStatus);
    }

public void readObjectNoData()
            throws ObjectStreamException {
    }

}
