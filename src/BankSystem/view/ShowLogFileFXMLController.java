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
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;

/**
 *
 * @author areeb
 */
public class ShowLogFileFXMLController {
   
    Main main;
    @FXML
    TextArea txtArea;
    

    
    @FXML
    private void openFile() throws IOException
    {
        fileOpen("Bank_log.txt");
       
    }
    
    private void fileOpen(String fileName) throws FileNotFoundException, IOException
    {
        
        //opening file
       
        File file = new File (fileName);
      
        txtArea.setText("Click on Show button!");

            try (Scanner inputStream = new Scanner(file))
            {
               
                 while (inputStream.hasNext())
                {
                    String lines = inputStream.nextLine(); // reading from file by each line
                    txtArea.appendText(lines + "\n"); // settig display text 
                }
             
             inputStream.close();}
            catch (FileNotFoundException e) {
            System.out.println("Cant read the file");
            }
        }
    
    @FXML
    private void closeLogFile() throws IOException
    {
        main. bankFrmshowLogStage();
    }
    
}

