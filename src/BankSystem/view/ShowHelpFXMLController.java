/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BankSystem.view;

import BankSystem.Main;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;
import java.util.Set;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;

/**
 *
 * @author areeb
 */
public class ShowHelpFXMLController {
  
    
   
    @FXML
    TextArea txtArea;
    
   
     @FXML
    private void openFile() throws IOException
    {
        fileOpen("help_manual.txt");
       
    }
    
    private void fileOpen(String fileName) throws FileNotFoundException, IOException
    {   
        
        txtArea.setText("Click on Show button!");
        
        //opening file
       
        File file = new File (fileName);
      
       
        
            try (Scanner inputStream = new Scanner(file))
            {
               
                 while (inputStream.hasNext())
                {
                    String lines = inputStream.nextLine(); // reading from file by each line
                    txtArea.appendText(lines + "\n"); // settig display text 
                }
             
             inputStream.close();}
            catch (FileNotFoundException e) {
                Main.setAlertWindow("Error Opening File","Cant Open the File!");
                
            System.out.println("Cant read the file");
            }
        }
   @FXML
    public void closeHelp() throws IOException
    {
        Main.closeHelpStage();
    }
 
}
