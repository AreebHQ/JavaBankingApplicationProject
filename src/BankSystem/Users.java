/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BankSystem;

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
import java.util.Random;
import java.util.Scanner;


/**
 *
 * @author areeb
 */
public class Users {

    /**
     *
     */
    public Map <String,UserInfo> userList = new HashMap<>();
    Bank bank;
    
    /**
     *
     * @throws ClassNotFoundException
     * @throws FileNotFoundException
     * @throws IOException
     */
    public Users() throws ClassNotFoundException, FileNotFoundException, IOException {

        try {
            Scanner readFile = new Scanner(new File("TotalUsers.txt"));
            int allUsers = Integer.parseInt( readFile.next() );
            
            File file = new File("UserInfo.txt");
            if(!file.exists())
            {
                System.out.println("File not found!");
            }
            FileInputStream fileInStream = new FileInputStream(file);
            ObjectInputStream   objInStream = new ObjectInputStream(fileInStream);
            bank.logActivity.logs.info("UserInfo.txt files Accessed!");
            userList = (Map<String,UserInfo>) objInStream .readObject();
            
            readFile.close();
            
            objInStream .close();

        } catch (IOException e) {
            System.out.println(e.getMessage() + "\n");
            bank.logActivity.logs.warning("Exception Error! \t Error reading the UserInfo file!");
        }
    }

    /**
     *
     * @param fname
     * @param lname
     * @param userID
     * @param pass
     */
    public void CreateUser(String fname, String lname,String userID, String pass)
{
    
    UserInfo user = new UserInfo(fname,lname,userID,pass);
    setUserStatus(userID,"Active");
    
    try {
            File allUsers = new File("TotalUsers.txt");
            FileWriter fw = new FileWriter(allUsers);
            PrintWriter pw = new PrintWriter(fw);

            userList.put(user.getUserID(), user);
            int totalUsers = userList.size();
            pw.println(totalUsers);
            writeUserListToFile(  userList  );
            bank.logActivity.logs.warning("Total Users saved: " +totalUsers);
            pw.close();
         
        } catch (IOException e) {
            System.out.println(e.getMessage());
        } catch (Exception e) {
            System.out.println("Error!");
            bank.logActivity.logs.warning("Exception Error! \t Couldn't create a New User!");
        }

}

    /**
     *
     * @param userList
     * @throws IOException
     * @throws FileNotFoundException
     */
    public void writeUserListToFile(Map<String, UserInfo> userList) throws IOException, FileNotFoundException {
        FileOutputStream fileOstream = new FileOutputStream("UserInfo.txt");
        ObjectOutputStream objOstream = new ObjectOutputStream(fileOstream);
        
        objOstream.writeObject(userList);
        objOstream.close();
        fileOstream.close();
        objOstream.flush();
        bank.logActivity.logs.info("Users list Updated!");
    }

    /**
     *
     * @param userid
     * @return
     */

public String getUserFirstName(String userid)
{
     Map.Entry<String, UserInfo> i;
            Iterator<Map.Entry<String, UserInfo>> it = userList.entrySet().iterator();
            while (it.hasNext())
            {
                i = it.next();
                if(i.getKey().equals(userid))
                {return i.getValue().getFistname();}
            }
     return "Not Found";
}

    /**
     *
     * @param userid
     * @return
     */
    public String getUserLastName(String userid)
{
     Map.Entry<String, UserInfo> i;
            Iterator<Map.Entry<String, UserInfo>> it = userList.entrySet().iterator();
            while (it.hasNext())
            {
                i = it.next();
                if(i.getKey().equals(userid))
                { return i.getValue().getLastname();}
            }
     return "Not Found";
}

    /**
     *
     * @param userid
     * @return
     */
    public String getUserID(String userid)
{
     Map.Entry<String, UserInfo> i;
            Iterator<Map.Entry<String, UserInfo>> it = userList.entrySet().iterator();
            while (it.hasNext())
            {
                i = it.next();
                if(i.getKey().equals(userid))
                return i.getValue().getUserID();
            }
     return "Not Found";
}

    /**
     *
     * @param userid
     * @return
     */
    public String getUserPassword(String userid)
{
     Map.Entry<String, UserInfo> i;
            Iterator<Map.Entry<String, UserInfo>> it = userList.entrySet().iterator();
            while (it.hasNext())
            {
                i = it.next();
                if(i.getKey().equals(userid))
                {return i.getValue().getPassword();}
            }
     return "Not Found";
}

    /**
     *
     * @param userid
     * @param status
     */
    public void setUserStatus(String userid,String status)
{
     Map.Entry<String, UserInfo> i;
            Iterator<Map.Entry<String, UserInfo>> it = userList.entrySet().iterator();
            while (it.hasNext())
            {
                i = it.next();
                if(i.getKey().equals(userid))
                {i.getValue().serUserStatus(status);}
            }
}

    /**
     *
     * @param userid
     * @return
     */
    public String getUserStatus(String userid)
{
     Map.Entry<String, UserInfo> i;
            Iterator<Map.Entry<String, UserInfo>> it = userList.entrySet().iterator();
            while (it.hasNext())
            {
                i = it.next();
                if(i.getKey().equals(userid))
                { return i.getValue().getUserStatus();}
            }
     return "Not Found";
}

    /**
     *
     * @param userid
     * @return
     */
    public boolean isActive (String userid)
    {
        for (UserInfo user: userList.values()) 
        {
            
            if (user.getUserID().equals(userid))
            { if (user.getUserStatus().equals("Active"))
                return true;
            }
        }
        return false;
    }


}

