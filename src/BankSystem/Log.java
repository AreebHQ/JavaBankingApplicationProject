/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BankSystem;


import java.io.File;
import java.io.IOException;
import java.util.logging.*;


/**
 *
 * @author areeb
 */
public class Log {
    
    /**
     *
     */
    public Logger logs;

    /**
     *
     */
    public FileHandler fileH;
   
     Log(String log_file) throws IOException
    {
        
        LogManager.getLogManager().reset();
        File f = new File(log_file);
        if (!f.exists())
        {
            f.createNewFile();
        }
        fileH = new FileHandler(log_file,true);
        logs =  logs.getLogger(Log.class.getName());
        logs.addHandler(fileH);
        SimpleFormatter formatter = new SimpleFormatter();
        fileH.setFormatter(formatter);
        
        
    }

}
