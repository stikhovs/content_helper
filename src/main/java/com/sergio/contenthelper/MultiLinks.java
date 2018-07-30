/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sergio.contenthelper;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

/**
 *
 * @author tihov
 */
public class MultiLinks implements Initializable {
    
    @FXML
    private TextField linkField;
    @FXML
    private TextField quantity;
    @FXML
    private Label errorLabel;
    @FXML
    private AnchorPane resultPlace;
    @FXML
    private CheckBox checkBox;
        
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
        Pattern p = Pattern.compile("href=('|\"){0,1}[\\w\\.:_\\-а-яА-Я/]+('|\"){0,1}|src=('|\"){0,1}[\\w\\.:_\\-а-яА-Я/]+('|\"){0,1}");
        Matcher m = p.matcher(startStr);
            
        int start1 = 0;
        int result = 0;
        String format = "";
        while (m.find(start1)) {
            s1 = startStr.substring(m.start(), m.end());
            
            start1 = m.end();
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
            s1+="\"";
            endStr = startStr;
            Pattern p1 = Pattern.compile("href=('|\"){0,1}[\\w\\.:_\\-а-яА-Я/]+('|\"){0,1}|src=('|\"){0,1}[\\w\\.:_\\-а-яА-Я/]+('|\"){0,1}");
            Matcher m1 = p1.matcher(endStr);
            
            int start2 = 0;
            while (m1.find(start2)) {                
                endStr = endStr.replaceAll(m1.group(), s1);
                start2 = m1.end();
            }
            
        }
        System.out.println("final endStr: "+ endStr);
        return endStr;
    }
    
    
    public static String updateLinkNoAlbum(String startStr){
        String s1 = "";
        String endStr = "";
        Pattern p = Pattern.compile("href=('|\"){0,1}[\\w\\.:_\\-а-яА-Я/]+('|\"){0,1}|src=('|\"){0,1}[\\w\\.:_\\-а-яА-Я/]+('|\"){0,1}");
        Matcher m = p.matcher(startStr);
            
        int start1 = 0;
        int result = 0;
        String format = "";
        while (m.find(start1)) {
            s1 = startStr.substring(m.start(), m.end());
            
            start1 = m.end();
            String[] sAr = s1.split("/");
            format = findFormat(sAr[sAr.length-1]);
            s1="";
            int length = 0;
            Pattern pattern = Pattern.compile("\\d+");
            System.out.println("before: "+ sAr[sAr.length-1]); 
            String str = sAr[sAr.length-1];          
                Matcher matcher = pattern.matcher(str);
                int start = 0;
                while (matcher.find(start)) {
                    String value = str.substring(matcher.start(), matcher.end());
                    result = Integer.parseInt(value);
                    result++;
                    str = ""+result;
                    start = matcher.end();
                }
            
            s1 = startStr.replaceAll("/.+"+format, "/"+str+format);
            str += format;
            endStr = startStr.replaceAll(sAr[sAr.length-1], str+"\"");            
            System.out.println("final endStr: "+ endStr);             
            
        }
        
        return endStr;
    }
    
    
    @FXML
    private void multiplyAction(ActionEvent event) {
        if(linkField.getText().isEmpty()) return;
        
        String s = linkField.getText();
        
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
        
        
        if(checkBox.isSelected()){
            updateLinkNoAlbum(s);
            for(int i = 0; i < Integer.parseInt(count); i++){
                finalList.appendText(updateLinkNoAlbum(s) + "\n");
                s = updateLinkNoAlbum(s);
            }
        }
        else {
            updateLink(s);
            for(int i = 0; i < Integer.parseInt(count); i++){
                finalList.appendText(updateLink(s) + "\n");
                s = updateLink(s);
            }
        }
        //System.out.println(s);
        
        

                
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
}
