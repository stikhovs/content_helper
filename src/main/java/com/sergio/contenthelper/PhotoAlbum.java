/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sergio.contenthelper;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

/**
 *
 * @author tihov
 */
public class PhotoAlbum  implements Initializable {
    
    @FXML
    private Label errorLabel;
    @FXML
    private TextField title;
    @FXML
    private TextField imageLink;
    @FXML
    private TextField quantity;
    @FXML
    private AnchorPane resultPlace;
    
    TextArea finalList = new TextArea();
    
    public static String findFormat(String str){
        String format = "";
        String[] arr = str.split("\\.");
        format = arr[arr.length-1];
        format = format.replaceAll("[\"']", "");
        return "." + format;
    }
    
    
    public static String updateLink(String startStr){
        String s1 = "";
        String endStr = "";
        int result = 0;
        String format = "";
         
            s1 = startStr;
            String[] sAr = s1.split("/");
            format = findFormat(sAr[sAr.length-1]);
            s1="";
            int length = 0;
            Pattern pattern = Pattern.compile("\\d+");
            for(String str : sAr){            
                Matcher matcher = pattern.matcher(str);
                int start = 0;
                while (matcher.find(start)) {
                    String value = str.substring(matcher.start(), matcher.end());
                    result = Integer.parseInt(value);
                    result++;
                    str = ""+result;
                    start = matcher.end();
                }
                if(length == sAr.length-1){
                    s1+=str+format;
                } else{
                    s1+=str+"/";
                    length++;
                }                
            }
            
        return s1;
    }
    
    @FXML
    private void handleButtonAction(ActionEvent event) {
        if(title.getText().isEmpty() || imageLink.getText().isEmpty()) return;
        String titleText = title.getText();
        String imageLinkText = imageLink.getText();
        String count = quantity.getText();
        errorLabel.setVisible(false);
        if(!count.matches("\\d+")) {
            errorLabel.setVisible(true);
            return;
        }
        finalList.clear();
        finalList.setStyle("-fx-font-size: 14px");
        finalList.setWrapText(true);
        if(resultPlace.getChildren().isEmpty()){
            resultPlace.getChildren().add(finalList);
            resultPlace.setRightAnchor(finalList, 0.0);
            resultPlace.setLeftAnchor(finalList, 0.0);
            resultPlace.setBottomAnchor(finalList, 10.0);
            resultPlace.setTopAnchor(finalList, 0.0);
        }
        
        List<String> htmlElements = new ArrayList<>();
        htmlElements.add("<div class=\"widget-container\">\n");
        htmlElements.add("<div class=\"text\">\n");
        htmlElements.add("<a href=\""+ imageLinkText +"\" data-toggle=\"&quot;collapse&quot;\">\n");
        htmlElements.add("<strong>");
        htmlElements.add(titleText);
        htmlElements.add("</strong>\n");
        htmlElements.add("</a>\n");
        htmlElements.add("</div>\n");
        htmlElements.add("<div class=\"account-widget row\">\n");
        htmlElements.add("<span>\n");
        htmlElements.add("<a rel=\"gallery13\" href=\"");
        htmlElements.add(imageLinkText);
        htmlElements.add("\" class=\"fancybox\" data-toggle=\"&quot;collapse&quot;\">\n");
        htmlElements.add("<img style=\"width: 70%;\" src=\"");
        htmlElements.add(imageLinkText);
        htmlElements.add("\" alt=\"\" rel=\"960,640\" /> </a>");
        
        String s = imageLinkText;
        
        for(int i = 0; i < Integer.parseInt(count)-1 ; i++){
            htmlElements.add("\n<a rel=\"gallery13\" href=\"");
            s = updateLink(s);
            htmlElements.add(s);
            htmlElements.add("\" class=\"fancybox dnone\" data-toggle=\"&quot;collapse&quot;\"></a>");
        }
        
        htmlElements.add("\n</span>\n");
        htmlElements.add("</div>\n");
        htmlElements.add("</div>");
        
        for(String str : htmlElements){
            finalList.appendText(str);
        }
        
        
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
