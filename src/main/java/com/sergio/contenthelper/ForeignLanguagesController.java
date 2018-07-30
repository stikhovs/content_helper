
package com.sergio.contenthelper;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Tab;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Modality;
import javafx.stage.Stage;
import static com.sergio.contenthelper.SlideMaker.newsLinkRus;
import java.awt.Event;
import javafx.event.EventType;



public class ForeignLanguagesController extends SlideMaker implements Initializable {
    
    @FXML
    public TextField imageLinkForeign;
    
    public static String textF;    
    
    @FXML
    private WebView imageViewForeign;
    @FXML
    private TextField newsLinkENG;
    @FXML
    private TextField newsLinkDE;
    @FXML
    private TextField newsLinkESP;
    @FXML
    private TextField newsLinkCH;
    @FXML
    private TextField newsLinkAR;
    
    @FXML
    private TextField titleENG;
    @FXML
    private TextField titleDE;
    @FXML
    private TextField titleESP;
    @FXML
    private TextField titleCH;
    @FXML
    private TextField titleAR;
    
    @FXML
    private TextField subTitleENG;
    @FXML
    private TextField subTitleDE;
    @FXML
    private TextField subTitleESP;
    @FXML
    private TextField subTitleCH;
    @FXML
    private TextField subTitleAR;
    
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
    
        
    protected static String englishText;
    protected static String germanText;
    protected static String spanishText;
    protected static String chineseText;
    protected static String arabicText;
    
    
    @FXML
    private void showImage(KeyEvent keyEvent){
        WebEngine webEngine = imageViewForeign.getEngine();
        webEngine.reload();
        webEngine.load(imageLinkForeign.getText());
    }
    
    @FXML
    private void isValidLink(KeyEvent keyEvent){
        if(keyEvent.getSource() instanceof TextField){
            TextField thisTextField = (TextField)keyEvent.getSource();
            AnchorPane parent = (AnchorPane)thisTextField.getParent();
            
            ImageView valid = null;
            ImageView invalid = null;
            
            for(Node n : parent.getChildren()){
                if(n instanceof ImageView){
                   if(n.getId().contains("markInvalid")){
                        invalid = (ImageView)n;                    
                   }
                   else if(n.getId().contains("markValid")){
                        valid = (ImageView)n;
                   } 
                }                
            }
            if(valid != null && invalid != null){
                if (isValid(thisTextField.getText())){
                    invalid.setVisible(false);
                    valid.setVisible(true);
                }
                else {
                    invalid.setVisible(true);
                    valid.setVisible(false);
                }
            }
            
        }
        
    }
    
    @FXML
    private void handleButtonAction(ActionEvent event) {
        String titleTextENG = titleENG.getText().trim();
        String titleTextDE = titleDE.getText().trim();
        String titleTextESP = titleESP.getText().trim();
        String titleTextCH = titleCH.getText().trim();
        String titleTextAR = titleAR.getText().trim();
        
        String subTitleTextENG = subTitleENG.getText().trim();
        String subTitleTextDE = subTitleDE.getText().trim();
        String subTitleTextESP = subTitleESP.getText().trim();
        String subTitleTextCH = subTitleCH.getText().trim();
        String subTitleTextAR = subTitleAR.getText().trim();
        
        if(titleTextENG.isEmpty() || 
                subTitleTextENG.isEmpty() || 
                titleTextDE.isEmpty() || 
                subTitleTextDE.isEmpty() || 
                titleTextESP.isEmpty() || 
                subTitleTextESP.isEmpty() || 
                titleTextCH.isEmpty() || 
                subTitleTextCH.isEmpty() || 
                titleTextAR.isEmpty() || 
                subTitleTextAR.isEmpty()) 
            return;
        
        titleTextENG = titleTextENG.replaceAll("\\.$","");
        titleTextDE = titleTextDE.replaceAll("\\.$","");
        titleTextESP = titleTextESP.replaceAll("\\.$","");
        titleTextCH = titleTextCH.replaceAll("\\.|。$","");
        
        if(newsLinkRus.isEmpty()) newsLinkRus = "#";        
        if(newsLinkENG.getText().isEmpty()){
             newsLinkENG.setText(newsLinkRus.replaceAll("https://investmoscow", "https://en.investmoscow"));
        }
        if(newsLinkDE.getText().isEmpty()){
            newsLinkDE.setText(newsLinkRus.replaceAll("https://investmoscow", "https://de.investmoscow"));
        }
        if(newsLinkESP.getText().isEmpty()){
            newsLinkESP.setText(newsLinkRus.replaceAll("https://investmoscow", "https://es.investmoscow"));
        }
        if(newsLinkCH.getText().isEmpty()){
            newsLinkCH.setText(newsLinkRus.replaceAll("https://investmoscow", "https://ch.investmoscow"));
        }
        if(newsLinkAR.getText().isEmpty()){
            newsLinkAR.setText(newsLinkRus.replaceAll("https://investmoscow", "https://ae.investmoscow"));
        }
        
        
        
        if(!subTitleTextENG.matches(".+\\.$")) subTitleTextENG += ".";
        if(!subTitleTextDE.matches(".+\\.$")) subTitleTextDE += ".";
        if(!subTitleTextESP.matches(".+\\.$")) subTitleTextESP += ".";
        if(!subTitleTextCH.matches(".+。$")) subTitleTextCH += "。";
                
        englishText = "<!-----------------------SLIDE------------------------------->\n" +
"    <li>\n" +
"        <div class=\"promo promo--shadow promo--first\">\n" +
"            <a href=\""+newsLinkENG.getText()+"\" class=\"promo__item\">\n" +
"                <div class=\"promo__content\">\n" +
"                    <div class=\"promo__content-text\">\n" +
"                        <span class=\"promo__title hidden-sm\">"+titleTextENG+"</span>\n" +
"                        <p>"+subTitleTextENG+"</p>\n" +
"                    </div>\n" +
"                </div>\n" +
"            </a>\n" +
"            <img src=\""+imageLinkForeign.getText()+"\" height=\"280\" alt=\"\" class=\"promo__pict\" style=\"height: 280px;\">\n" +
"        </div>\n" +
"    </li>\n" +
"    <!-----------------------/SLIDE------------------------------->";
        
        germanText = "<!-----------------------SLIDE------------------------------->\n" +
"    <li>\n" +
"        <div class=\"promo promo--shadow promo--first\">\n" +
"            <a href=\""+newsLinkDE.getText()+"\" class=\"promo__item\">\n" +
"                <div class=\"promo__content\">\n" +
"                    <div class=\"promo__content-text\">\n" +
"                        <span class=\"promo__title hidden-sm\">"+titleTextDE+"</span>\n" +
"                        <p>"+subTitleTextDE+"</p>\n" +
"                    </div>\n" +
"                </div>\n" +
"            </a>\n" +
"            <img src=\""+imageLinkForeign.getText()+"\" height=\"280\" alt=\"\" class=\"promo__pict\" style=\"height: 280px;\">\n" +
"        </div>\n" +
"    </li>\n" +
"    <!-----------------------/SLIDE------------------------------->";
        
        spanishText = "<!-----------------------SLIDE------------------------------->\n" +
"    <li>\n" +
"        <div class=\"promo promo--shadow promo--first\">\n" +
"            <a href=\""+newsLinkESP.getText()+"\" class=\"promo__item\">\n" +
"                <div class=\"promo__content\">\n" +
"                    <div class=\"promo__content-text\">\n" +
"                        <span class=\"promo__title hidden-sm\">"+titleTextESP+"</span>\n" +
"                        <p>"+subTitleTextESP+"</p>\n" +
"                    </div>\n" +
"                </div>\n" +
"            </a>\n" +
"            <img src=\""+imageLinkForeign.getText()+"\" height=\"280\" alt=\"\" class=\"promo__pict\" style=\"height: 280px;\">\n" +
"        </div>\n" +
"    </li>\n" +
"    <!-----------------------/SLIDE------------------------------->";
        
        chineseText = "<!-----------------------SLIDE------------------------------->\n" +
"    <li>\n" +
"        <div class=\"promo promo--shadow promo--first\">\n" +
"            <a href=\""+newsLinkCH.getText()+"\" class=\"promo__item\">\n" +
"                <div class=\"promo__content\">\n" +
"                    <div class=\"promo__content-text\">\n" +
"                        <span class=\"promo__title hidden-sm\">"+titleTextCH+"</span>\n" +
"                        <p>"+subTitleTextCH+"</p>\n" +
"                    </div>\n" +
"                </div>\n" +
"            </a>\n" +
"            <img src=\""+imageLinkForeign.getText()+"\" height=\"280\" alt=\"\" class=\"promo__pict\" style=\"height: 280px;\">\n" +
"        </div>\n" +
"    </li>\n" +
"    <!-----------------------/SLIDE------------------------------->";
        
        arabicText = "<!-----------------------SLIDE------------------------------->\n" +
"    <li>\n" +
"        <div class=\"promo promo--shadow promo--first\">\n" +
"            <a href=\""+newsLinkAR.getText()+"\" class=\"promo__item\">\n" +
"                <div class=\"promo__content\">\n" +
"                    <div class=\"promo__content-text\">\n" +
"                        <span class=\"promo__title hidden-sm\">"+titleTextAR+"</span>\n" +
"                        <p>"+subTitleTextAR+"</p>\n" +
"                    </div>\n" +
"                </div>\n" +
"            </a>\n" +
"            <img src=\""+imageLinkForeign.getText()+"\" height=\"280\" alt=\"\" class=\"promo__pict\" style=\"height: 280px;\">\n" +
"        </div>\n" +
"    </li>\n" +
"    <!-----------------------/SLIDE------------------------------->";
        
        
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/fxml/FLReady.fxml"));
            Stage stage = new Stage();            
            //newsLinkRus = newsLink.getText();
            stage.setTitle("Слайды на иностранных языках");
            Image icon = new Image("/images/favicon.png");
            stage.getIcons().add(icon);
            stage.setScene(new Scene(root));
            stage.initModality(Modality.WINDOW_MODAL);
            stage.initOwner(((Node)event.getSource()).getScene().getWindow());
            stage.show();            
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    
        
    @Override
    public void initialize(URL url, ResourceBundle rb) {        
        imageLinkForeign.textProperty().setValue(textF);
        System.out.println("textF: "+ textF);        
        WebEngine we = imageViewForeign.getEngine();
        we.load(imageLinkForeign.getText());
        String styleForTabs = "-fx-font-size: 16px;";
        tabENG.setStyle(styleForTabs);
        tabDE.setStyle(styleForTabs);
        tabESP.setStyle(styleForTabs);
        tabCH.setStyle(styleForTabs);
        tabAR.setStyle(styleForTabs);
               
        
        if(!newsLinkRus.isEmpty()){
            newsLinkENG.setText(newsLinkRus.replaceAll("https://investmoscow", "https://en.investmoscow"));
            newsLinkDE.setText(newsLinkRus.replaceAll("https://investmoscow", "https://de.investmoscow"));
            newsLinkESP.setText(newsLinkRus.replaceAll("https://investmoscow", "https://es.investmoscow"));
            newsLinkCH.setText(newsLinkRus.replaceAll("https://investmoscow", "https://ch.investmoscow"));
            newsLinkAR.setText(newsLinkRus.replaceAll("https://investmoscow", "https://ae.investmoscow"));
            
            newsLinkENG.fireEvent(new KeyEvent(KeyEvent.KEY_RELEASED, "", "", KeyCode.HOME, true, true, true, true));
            newsLinkDE.fireEvent(new KeyEvent(KeyEvent.KEY_RELEASED, "", "", KeyCode.HOME, true, true, true, true));
            newsLinkESP.fireEvent(new KeyEvent(KeyEvent.KEY_RELEASED, "", "", KeyCode.HOME, true, true, true, true));
            newsLinkCH.fireEvent(new KeyEvent(KeyEvent.KEY_RELEASED, "", "", KeyCode.HOME, true, true, true, true));
            newsLinkAR.fireEvent(new KeyEvent(KeyEvent.KEY_RELEASED, "", "", KeyCode.HOME, true, true, true, true));
        }
            
        
        newsLinkENG.setOnKeyTyped((event) -> {
            if(isValid(newsLinkENG.getText())){
                newsLinkDE.setText(newsLinkENG.getText().replaceAll("https://en.investmoscow", "https://de.investmoscow"));
                newsLinkESP.setText(newsLinkENG.getText().replaceAll("https://en.investmoscow", "https://es.investmoscow"));
                newsLinkCH.setText(newsLinkENG.getText().replaceAll("https://en.investmoscow", "https://ch.investmoscow"));
                newsLinkAR.setText(newsLinkENG.getText().replaceAll("https://en.investmoscow", "https://ae.investmoscow"));
                newsLinkENG.fireEvent(new KeyEvent(KeyEvent.KEY_RELEASED, "", "", KeyCode.HOME, true, true, true, true));
                newsLinkDE.fireEvent(new KeyEvent(KeyEvent.KEY_RELEASED, "", "", KeyCode.HOME, true, true, true, true));
                newsLinkESP.fireEvent(new KeyEvent(KeyEvent.KEY_RELEASED, "", "", KeyCode.HOME, true, true, true, true));
                newsLinkCH.fireEvent(new KeyEvent(KeyEvent.KEY_RELEASED, "", "", KeyCode.HOME, true, true, true, true));
                newsLinkAR.fireEvent(new KeyEvent(KeyEvent.KEY_RELEASED, "", "", KeyCode.HOME, true, true, true, true));
            }
            
        });
        
    }  
    
}
