/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BankSystem.view;

import BankSystem.Main;
import java.io.IOException;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.text.Text;

/**
 *
 * @author areeb
 */
public class ShowAboutFXMLController {
    
    @FXML
    Label header;
   
    @FXML
    Text displayText;
    
    
    
    
    
    
    
   @FXML
    public void closeAbout() throws IOException
    {
        Main.closeAboutStage();
    }

    
    
    
}
