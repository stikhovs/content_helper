
package com.sergio.contenthelper;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Tab;
import javafx.scene.control.TextArea;
import javafx.scene.web.WebEngine;
import static com.sergio.contenthelper.ForeignLanguagesController.textF;

public class FLReady extends ForeignLanguagesController implements Initializable {
    
    @FXML
    TextArea textAreaENG;
    @FXML
    TextArea textAreaDE;
    @FXML
    TextArea textAreaESP;
    @FXML
    TextArea textAreaCH;
    @FXML
    TextArea textAreaAR;
    
    @FXML
    private Tab tabENG;
    @FXML
    private Tab tabDE;
    @FXML
    private Tab tabESP;
    @FXML
    private Tab tabCH;
    @FXML
    private Tab tabAR;
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {        
        textAreaENG.textProperty().setValue(englishText);
        textAreaDE.textProperty().setValue(germanText);
        textAreaESP.textProperty().setValue(spanishText);
        textAreaCH.textProperty().setValue(chineseText);
        textAreaAR.textProperty().setValue(arabicText);
        
        String styleForTabs = "-fx-font-size: 16px;";
        tabENG.setStyle(styleForTabs);
        tabDE.setStyle(styleForTabs);
        tabESP.setStyle(styleForTabs);
        tabCH.setStyle(styleForTabs);
        tabAR.setStyle(styleForTabs);
        
    }  
    
}
