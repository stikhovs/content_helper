
package com.sergio.contenthelper;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class SlideMaker implements Initializable {
    
    @FXML
    private TextField title;
    @FXML
    private TextField subTitle;
    @FXML
    protected TextField newsLink;    
    @FXML
    protected TextField imageLink;
    @FXML
    protected WebView imageView;
    
    @FXML
    protected ImageView validMark;
    @FXML
    protected ImageView invalidMark;
    
    protected static String newsLinkRus = "";
    
    @FXML
    private void foreignLanguageAction(ActionEvent event){        
        try {
            newsLinkRus = newsLink.getText();
            Parent root = FXMLLoader.load(getClass().getResource("/fxml/ForeignLanguages.fxml"));
            Stage stage = new Stage();            
            
            System.out.println("news1: "+newsLinkRus);
            stage.setTitle("Иностранные языки");
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
    
    protected static boolean isValid(String link){
        try {
            URL url = new URL(link);            
            try{
                url.getContent();
            }
            catch(IOException ex){
                System.out.println("invalid link: " + ex.getMessage());
                return false;
            }
        } 
        catch (MalformedURLException ex) {
            System.out.println(ex.getMessage());
            return false;
        }
        
        System.out.println("link is valid");
        return true;
    }
    
    
    @FXML
    private void isValidLink(KeyEvent keyEvent){
        if (isValid(newsLink.getText())){
            invalidMark.setVisible(false);
            validMark.setVisible(true);
        }
        else {
            invalidMark.setVisible(true);
            validMark.setVisible(false);
        }
    }
    
    @FXML
    private void showImage(KeyEvent keyEvent){
        WebEngine webEngine = imageView.getEngine();
        webEngine.reload();
        webEngine.load(imageLink.getText());      
        String text = imageLink.getText();
        ForeignLanguagesController.textF = text;
        DigestController.imageLinkDigest = text;
    }
    
    @FXML
    private void digestAction(ActionEvent event){
        newsLinkRus = newsLink.getText();
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/fxml/DigestSlideMaker.fxml"));
            Stage stage = new Stage();          
            
            System.out.println("news1: "+newsLinkRus);
            stage.setTitle("Slide Maker for Digest");
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
    
    @FXML
    private void handleButtonAction(ActionEvent event) {   
        
        TextArea result = new TextArea();
        result.setStyle("-fx-font-size: 14px;");
        
        if(!(title.getText().isEmpty() && subTitle.getText().isEmpty() && imageLink.getText().isEmpty())){
            
            String linkText = "";
            String subtitleText = subTitle.getText().trim();
            String titleText = title.getText().trim();
            String imageLinkText = imageLink.getText().replaceAll("web1\\.","");
                        
            if(newsLink.getText().isEmpty()){
                linkText = "#";
            }
            else
            linkText = newsLink.getText();
            
            if(!subtitleText.matches(".+\\.$")) subtitleText += ".";
            
            String resText = "<!-----------------------SLIDE------------------------------->\n" +
"    <li>\n" +
"        <div class=\"promo promo--shadow promo--first\">\n" +
"            <a href=\""+linkText+"\" class=\"promo__item\">\n" +
"                <div class=\"promo__content\">\n" +
"                    <div class=\"promo__content-text\">\n" +
"                        <span class=\"promo__title hidden-sm\">"+titleText+"</span>\n" +
"                        <p>"+subtitleText+"</p>\n" +
"                    </div>\n" +
"                </div>\n" +
"            </a>\n" +
"            <img src=\""+imageLinkText+"\" height=\"280\" alt=\"\" class=\"promo__pict\" style=\"height: 280px;\">\n" +
"        </div>\n" +
"    </li>\n" +
"    <!-----------------------/SLIDE------------------------------->";
        
            result.appendText(resText);        

            AnchorPane secondaryLayout = new AnchorPane();

            Scene secondScene = new Scene(secondaryLayout, 800, 400);

                

            AnchorPane.setTopAnchor(result, 30.0);
            AnchorPane.setRightAnchor(result, 10.0);
            AnchorPane.setBottomAnchor(result, 10.0);
            AnchorPane.setLeftAnchor(result, 10.0);
            
            secondaryLayout.getChildren().add(result);
            
            Stage newWindow = new Stage();
            newWindow.initModality(Modality.WINDOW_MODAL);
            newWindow.initOwner(((Node)event.getSource()).getScene().getWindow());
            newWindow.setTitle("Here's your slide");
            Image icon = new Image("/images/favicon.png");
            newWindow.getIcons().add(icon);
            newWindow.setScene(secondScene);
            newWindow.show();
        }
        else return;
        
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
