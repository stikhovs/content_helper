/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sergio.contenthelper;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.TitledPane;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFHyperlink;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTHyperlink;

/**
 *
 * @author tihov
 */
public class Digest implements Initializable {
        
    @FXML
    protected TextArea textArea;
    @FXML
    protected Label photosAddedLabel;
    @FXML
    protected Label videosAddedLabel;
    @FXML
    protected Label voteAddedLabel;
    @FXML
    private Button photogalleryButton;
    @FXML
    private Button videoButton;
    @FXML
    private Button voteButton;
    @FXML
    private CheckBox englishCheckBox;
        
    String chosenFile = "";
    ArrayList<String> tableNames = new ArrayList();
    ObservableList<TitledPane> panes = FXCollections.observableArrayList();
    private String savedPath = "";
    List<String> lines = new ArrayList<>();
    
    
    String fileName = "";
    
    String[] months = {"Январь", "Февраль", "Март", "Апрель", "Май", "Июнь", "Июль", "Август", "Сентябрь", "Октябрь", "Ноябрь", "Декабрь"};
    String[] monthsENG = {"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"};
    String month = "";
    
    
    
    String divIdEnd = " <!--end of content-->\n" + 
"<p style=\"overflow: hidden; page-break-after: always;\"></p>\n" +
"  <div id=\"end\">\n" +
"    <div class=\"hidden-sm hidden-xs\">\n" +
"      <h3>Благодарим за внимание!</h3>\n" +
"      <p>С уважением и надеждой на сотрудничество,</p>\n" +
"      <p><ins><strong></strong></ins></p>\n" +
"      <p></p>\n" +
"    </div>\n" +
"    <div class=\"row\">\n" +
"      <div class=\"col-md-6\">\n" +
"        <div class=\"hidden-md hidden-lg\">\n" +
"          <h3>Благодарим за внимание!</h3>\n" +
"          <p>С уважением и надеждой на сотрудничество,</p>\n" +
"          <p></p>\n" +
"        </div>\n" +
"        <div class=\"row\">\n" +
"          <div class=\"col-md-2 col-sm-2 col-xs-2\"><img width=\"62\" height=\"\" src=\"https://investmoscow.ru/media/3153879/11111.jpg\" /></div>\n" +
"          <div class=\"col-md-10 col-sm-9 col-xs-10\">\n" +
"            <p>Департамент экономической политики и развития города Москвы</p>\n" +
"            <p><a href=\"http://depr.mos.ru/\">www.depr.mos.ru</a></p>\n" +
"          </div>\n" +
"        </div>\n" +
"        <div class=\"row\">\n" +
"          <div class=\"col-md-2 col-sm-2 col-xs-2\"><img width=\"62\" height=\"\" src=\"https://investmoscow.ru/media/3153880/22222.jpg\" /></div>\n" +
"          <div class=\"col-md-10 col-sm-9 col-xs-10\">\n" +
"            <p>Городское агентство управления инвестициями</p>\n" +
"            <p><a href=\"http://www.investmoscow.ru/agency\">www.investmoscow.ru/agency</a></p>\n" +
"          </div>\n" +
"        </div>\n" +
"        <div class=\"row\">\n" +
"          <div class=\"col-md-2 col-sm-2 col-xs-2\"><img width=\"62\" height=\"\" src=\"https://investmoscow.ru/media/3153878/img239.jpg\" /></div>\n" +
"          <div class=\"col-md-10 col-sm-9 col-xs-10\">\n" +
"            <p>Портал поставщиков</p>\n" +
"            <p><a href=\"http://zakupki.mos.ru/\">www.zakupki.mos.ru</a></p>\n" +
"          </div>\n" +
"        </div>\n" +
"      </div>\n" +
"    </div>\n" +
"    <p>Архив дайджеста представлен на <a href=\"http://investmoscow.ru/agency/media/invest-digest/\">Инвестиционном портале Москвы</a>.</p>\n" +
"  </div>\n" +
"  <div style=\"display: none;\"><a id=\"digest-print-lin\" href=\"/media/2588979/2017-01-26_digest_january_.pdf\" alt=\"alt\">alt</a></div>\n" +
"  <p></p>\n" +
"</div>";
    
    String divIdEndENG = " <!--end of content-->\n" + 
"<p style=\"overflow: hidden; page-break-after: always;\"></p>\n" +
"  <div id=\"end\">\n" +
"    <div class=\"hidden-sm hidden-xs\">\n" +
"      <h3>Thank You for Your attention!</h3>\n" +
"      <p>Looking forward for cooperation, faithfully Yours,</p>\n" +
"      <p><ins><strong></strong></ins></p>\n" +
"      <p></p>\n" +
"    </div>\n" +
"    <div class=\"row\">\n" +
"      <div class=\"col-md-6\">\n" +
"        <div class=\"hidden-md hidden-lg\">\n" +
"          <h3>Thank You for Your attention!</h3>\n" +
"          <p>Looking forward for cooperation, faithfully Yours,</p>\n" +
"          <p></p>\n" +
"        </div>\n" +
"        <div class=\"row\">\n" +
"          <div class=\"col-md-2 col-sm-2 col-xs-2\"><img width=\"62\" height=\"\" src=\"https://investmoscow.ru/media/3153879/11111.jpg\" /></div>\n" +
"          <div class=\"col-md-10 col-sm-9 col-xs-10\">\n" +
"            <p>The Department of Economic Policy and Development of Moscow</p>\n" +
"            <p><a href=\"http://depr.mos.ru/\">www.depr.mos.ru</a></p>\n" +
"          </div>\n" +
"        </div>\n" +
"        <div class=\"row\">\n" +
"          <div class=\"col-md-2 col-sm-2 col-xs-2\"><img width=\"62\" height=\"\" src=\"https://investmoscow.ru/media/3153880/22222.jpg\" /></div>\n" +
"          <div class=\"col-md-10 col-sm-9 col-xs-10\">\n" +
"            <p>Moscow City Investment Agency</p>\n" +
"            <p><a href=\"https://en.investmoscow.ru/agency/main/\">https://en.investmoscow.ru/agency/</a></p>\n" +
"          </div>\n" +
"        </div>\n" +
"        <div class=\"row\">\n" +
"          <div class=\"col-md-2 col-sm-2 col-xs-2\"><img width=\"62\" height=\"\" src=\"https://investmoscow.ru/media/3153878/img239.jpg\" /></div>\n" +
"          <div class=\"col-md-10 col-sm-9 col-xs-10\">\n" +
"            <p>Purchasers’ portal</p>\n" +
"            <p><a href=\"http://zakupki.mos.ru/\">www.zakupki.mos.ru</a></p>\n" +
"          </div>\n" +
"        </div>\n" +
"      </div>\n" +
"    </div>\n" +
"    <p>The Moscow Digest archive is available at the <a href=\"http://en.investmoscow.ru/agency/media/invest-digest/\">Moscow Investment Portal</a>.</p>\n" +
"  </div>\n" +
"  <div style=\"display: none;\"><a id=\"digest-print-lin\" href=\"/media/2588979/2017-01-26_digest_january_.pdf\" alt=\"alt\">alt</a></div>\n" +
"  <p></p>\n" +
"  <p></p>\n" +
"</div>";
    
    @FXML Button parse;
    
    
    @FXML
    private void openFileAction(ActionEvent event){
        
//        textArea.setText(System.getProperty("user.dir"));
//        textArea.appendText(System.getProperty("user.home"));
//        System.out.println(System.getProperty("user.dir"));
        
        chosenFile = "";
        panes.clear(); 
        tableNames.clear();        
        FileChooser fc = new FileChooser();
        
//        System.out.println(path);
//        try (Stream<String> lineStream = Files.lines(path)) {
//            lines = lineStream.collect(Collectors.toList());
//            //System.out.println("startPathFrom "+startPathFrom);
//            for (String s : lines){
//            	startPathFrom += s;
//                //System.out.println(startPathFrom);
//            }
//            //System.out.println(startPathFrom);
//        } 
//        catch (IOException ex) {
//        	ex.getMessage();
//        	System.out.println("Файл, где указан путь не найден");
//        }

        //System.out.println(startPathFrom);
        
//        if (startPath.exists()) {
//        	fc.setInitialDirectory(startPath);
//        }
        
        fc.setInitialDirectory(new File(System.getProperty("user.home")+"/Desktop"));        
        
        if(!savedPath.equals("")){
            fc.setInitialDirectory(new File(savedPath));
        }
        
        
        fc.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("docx files","*.docx"));
        File file = fc.showOpenDialog(null);
        //panes.addAll(accord.getPanes());
        if(file != null){
            savedPath = file.getParent();
            chosenFile = file.getAbsolutePath();
            //System.out.println(savedPath);
            fileName = file.getName();
            parse.setDisable(false);
        }
        else {
            System.out.println("Файл не был выбран");
        }
        
    }
    
//    public static void setClipboard(String str) {
//        Clipboard clipboard = Clipboard.getSystemClipboard();
//        ClipboardContent content = new ClipboardContent();
//        //content.putRtf(str);
//        content.putHtml(str);
//        clipboard.setContent(content);
//    }
//    
//    public static String getClipboard() {
//        Clipboard clipboard = Clipboard.getSystemClipboard();
//        //ClipboardContent content = new ClipboardContent();
//        return clipboard.getHtml();
//    }
  
    
       
    
    
    
    
    @FXML
    private void handleButtonAction(ActionEvent event) throws IOException {
        
        List<String> htmlElements = new ArrayList();
        
        htmlElements.clear();
        textArea.clear();
        
        htmlElements.add("<div id='invest-digest'>\n");
        if(englishCheckBox.isSelected()){
            htmlElements.add("<h1 align='center'>Moscow investment digest</h1>\n");
        }else
        htmlElements.add("<h1 align='center'>Инвестиционный дайджест Москвы</h1>\n");
        //if(englishCheckBox.isSelected())
        
        int monthCounter = 0;
        for(String m : months){
            if(fileName.toLowerCase().contains(m.toLowerCase())){
                if(englishCheckBox.isSelected()){
                    m = monthsENG[monthCounter];
                    month = m;
                }else
                month = m;
                break;
            }
            monthCounter++;
        }
        
        
        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        
        htmlElements.add("<h2 align='center'>" + month + " " + year + "</h2>\n");
        
        try (FileInputStream fileInputStream = new FileInputStream(new File(chosenFile))) {
                
            XWPFDocument docxFile = new XWPFDocument(OPCPackage.open(fileInputStream));
            List<XWPFParagraph> paragraphs = docxFile.getParagraphs();
             
            XWPFHyperlink[] linksArray = docxFile.getHyperlinks();
            List<XWPFHyperlink> links = Arrays.asList(linksArray);
                
            Map<String, String> linksMap = new HashMap<>();
            List<String> linksList = new ArrayList<>();   
                
            links.forEach((l) -> {
                linksMap.put(l.getId(), l.getURL());
            });
            
            
            /* Создание списка ссылок */
                
            Map<String, String> linksText = new HashMap<>();
                
            for (XWPFParagraph p : paragraphs) {     
                List<XWPFRun> run = p.getRuns();
                List<String> str = new ArrayList<>();
                    
                if(!run.isEmpty()){
                    if(p.getCTP().toString().contains("<w:hyperlink r:id=")){
                        System.out.println("Содержит HYPERLINK");
                        CTHyperlink[] hyperLinks = p.getCTP().getHyperlinkArray();
                        for(CTHyperlink hyperLink : hyperLinks){
                            System.out.println(hyperLink);
                            String text = "";
                            String id = hyperLink.getDomNode().getAttributes().getNamedItem("r:id").getNodeValue();
                            for(int i = 0; i < hyperLink.getRArray().length; i++){
                                //System.out.print(hyperLink.getRArray(i).getTArray(0).getStringValue());//текст первой ссылки полностью
                                text += hyperLink.getRArray(i).getTArray(0).getStringValue();
                            }
                            linksText.put(id, text);
                                    //System.out.println(hyperLink.getRArray(0).getTArray(0).getStringValue()); //текст первой части ссылки
                                    //System.out.println(hyperLink.getDomNode().getFirstChild().getChildNodes().item(1).getNodeName());                                    
                                    //System.out.println(hyperLink.getDomNode().getAttributes().getNamedItem("r:id").getNodeValue()); //id ссылки
                                }

                            }
                        
                    }
                }
                    System.out.println("Список ссылок:");
                    for( Map.Entry<String, String> entry : linksText.entrySet() ){
                        System.out.println(entry.getKey() + ": " + entry.getValue());
                    }
                    System.out.println("***************************");
                    
                    Map<String, String> finalLinksText = new HashMap<>();
                    
                    for( Map.Entry<String, String> entryOne : linksMap.entrySet() ){
                        for( Map.Entry<String, String> entryTwo : linksText.entrySet() ){
                            if(entryOne.getKey().equals(entryTwo.getKey())){
                                finalLinksText.put(entryOne.getKey(),"<a href ='"+ entryOne.getValue() + "' target='_blank'>" + entryTwo.getValue() + "</a>");
                                linksList.add("<a href ='"+ entryOne.getValue() + "' target='_blank'>" + entryTwo.getValue() + "</a>");
                                break;
                            }
                        }
                    }
                    
                    for(String s : linksList){
                        System.out.println(s);
                    }
                    
                    int h2ThemeCount = 0;
                    int h3Count = 0;
                    
                    paragraphsLabel: for (XWPFParagraph p : paragraphs) {
                        String finalP = ""; 
                        
                        if(p.getText().contains("Уважаемые коллеги") || p.getText().contains("Dear colleagues")){
                            finalP = "<p class='big'><strong>" + p.getText() + "</strong></p>\n";
                            htmlElements.add(finalP);
                        }
                        
                        else if(p.getCTP().toString().contains("<w:hyperlink r:id=")){
                               
                            String[] wh = p.getCTP().toString().split("<w:hyperlink r:id=");
                            int hyperlinkCount = wh.length-1;
                            int count = 0;
                            String newPar = p.getText();
                            
                                CTHyperlink[] hyperLinks = p.getCTP().getHyperlinkArray();                                
                                for(CTHyperlink hyperLink : hyperLinks){
                                    for( Map.Entry<String, String> entry : finalLinksText.entrySet() ){
                                        if(hyperLink.getId().equals(entry.getKey())){
                                            String text = "";
                                            for(int i = 0; i < hyperLink.getRArray().length; i++){                                                
                                                text += hyperLink.getRArray(i).getTArray(0).getStringValue();
                                            }
                                            
                                            finalP = newPar.replace(text, entry.getValue());
                                            newPar = finalP;
                                            count++;
                                            
                                            if(count == hyperlinkCount){
                                                if(finalP.contains("В настоящее время на Инвестиционном портале Москвы") || finalP.contains("objects are presented at Investment portal of Moscow")){
                                                htmlElements.add("</div>\n");
                                                //finalP = "<p class='aboutTenders'>" +newPar.replace(text, entry.getValue()) + "</p>\n";
                                                finalP = "<p class='aboutTenders'>" +newPar + "</p>\n";
                                                htmlElements.add(finalP);
                                                htmlElements.add(divIdEnd);
                                                break paragraphsLabel;
                                            }
                                            else{
                                                //finalP = "<p>" + newPar.replace(text, entry.getValue()) + "</p>\n";
                                                finalP = "<p>" + newPar + "</p>\n";
                                                htmlElements.add(finalP);
                                            }
                                            }
                                            
                                            
                                        }
                                    }
                                    
                                }
                        }
                        else if(p.getText().equalsIgnoreCase("Благодарим за внимание!") || p.getText().equalsIgnoreCase("Thank You for Your attention!")){
                            htmlElements.add("</div>\n");
                            if(englishCheckBox.isSelected()){
                                htmlElements.add(divIdEndENG);
                            }
                            else
                                htmlElements.add(divIdEnd);
                            break;                        
                        }
                        else if(p.getText().matches("[А-ЯA-Z ]+")){
                            if(h2ThemeCount == 0){
                                htmlElements.add("<!--content-->\n");
                                htmlElements.add("<div class='no-page-break'>\n");
                                htmlElements.add("<h2 class='theme'>" + p.getText() + "</h2>\n");
                            }
                            else {
                                htmlElements.add("</div>\n");
                                htmlElements.add("<div class='no-page-break'>\n");
                                htmlElements.add("<h2 class='theme'>" + p.getText() + "</h2>\n");
                            }                           
                            h2ThemeCount++;
                            h3Count = 0;
                            
                        }  
                        else if(!p.getRuns().isEmpty()){
                            if(p.getRuns().get(0).isBold()){
                                System.out.println(p.getRuns().get(0));
                            if(!(p.getText().contains("Фото") || p.getText().contains("Видео") || p.getText().contains("Photo") || p.getText().contains("Video"))){
                                if(h3Count == 0){                                    
                                    finalP= "<h3>" + p.getText() + "</h3>\n" + "<img class='img-responsive img' src='#' />\n";
                                    htmlElements.add(finalP);
                                    h3Count++;
                                }
                                else{
                                    htmlElements.add("</div>");
                                    htmlElements.add("<div class='no-page-break'>");
                                    finalP= "<h3>" + p.getText() + "</h3>\n" + "<img class='img-responsive img' src='#' />\n";
                                    htmlElements.add(finalP);
                                    h3Count++;
                                }
                                
                            }
                                
                           }
                            else {
                                finalP = "<p>" + p.getText() + "</p>\n";  
                                htmlElements.add(finalP);
                            }
                              
                        }
                        
                        else {
                            finalP = p.getText();
                            htmlElements.add(finalP);
                        } 
                        System.out.println(finalP);                        
                    }
                    
                    
                    for(String element : htmlElements){
                        textArea.appendText(element);
                    }
                    
                    firstImageLink.setDisable(false);
                    imageQuantity.setDisable(false);
                    photogalleryButton.setDisable(false);
                    videoButton.setDisable(false);
                    voteButton.setDisable(false);
                
        }
        catch (Exception ex) {
                System.out.println(ex.getMessage());
                ex.printStackTrace();
            }
    }
    
    @FXML
    private TextField firstImageLink;
    @FXML
    private TextField imageQuantity;
    @FXML
    private Button imageInsertButton;
    
    public static String findFormat(String str){
        String format = "";
        String[] arr = str.split("\\.");
        format = arr[arr.length-1];
        format = format.replaceAll("[\"']", "");
        return "." + format;
    }
    
    public static String updateLink(String startStr){
        String s1 = "";
        startStr = startStr.replaceAll("web1.", "");
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
    private void checkForNumeric(KeyEvent keyEvent){
        if(!imageQuantity.getText().matches("[0-9]+")){
            imageQuantity.setText(imageQuantity.getText().replaceAll("[^0-9]+", ""));
        imageQuantity.selectPositionCaret(imageQuantity.getLength());
        imageQuantity.deselect();
        }
        imageInsertButton.setDisable(false);
            
    }
    
    
    
    @FXML
    private void hasTextAction(KeyEvent keyEvent){
        if(!textArea.getText().isEmpty()){
            photogalleryButton.setDisable(false);
            videoButton.setDisable(false);
            voteButton.setDisable(false);
        } else{
            photogalleryButton.setDisable(true);
            videoButton.setDisable(true);
            voteButton.setDisable(true);
        }
            
    }
    
    @FXML
    private void imageInsertAction(ActionEvent event){
        
        try{
            String linkText = firstImageLink.getText().replaceAll("web1.", "");
            
            
            int count = Integer.parseInt(imageQuantity.getText());

            List<String> imageList = new ArrayList<>();
            imageList.add(linkText);

            for(int i = 0; i < count-1; i++){
                linkText = updateLink(linkText);
                //System.out.println(linkText);
                imageList.add(linkText);
            }
            
            String textAreaWithImage = textArea.getText();

            for(String s : imageList) {
                System.out.println(s);
                textAreaWithImage = textAreaWithImage.replaceFirst("<img class='img-responsive img' src='#' />", "<img class='img-responsive img' src='"+s+"' />");
            }
            textArea.setText(textAreaWithImage);
            imageList.clear();
        }
        catch(Exception ex){
            System.out.println("Ошибка в методе imageInsertAction");
        }
        
        
    }
    
    protected static boolean isPhotogalleryAdded = false;
    
    @FXML
    private void photogalleryInsertAction(ActionEvent event){
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/fxml/InsertPhotogallery.fxml"));
            Stage stage = new Stage();
            stage.setTitle("Добавление фотогалереи");
            Image icon = new Image("/images/favicon.png");
            stage.getIcons().add(icon);
            stage.setScene(new Scene(root));
            stage.initModality(Modality.WINDOW_MODAL);
            stage.initOwner(((Node)event.getSource()).getScene().getWindow());
            stage.setResizable(false);
            stage.show();
            
            stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
                @Override
                public void handle(WindowEvent event) {
                    if(isPhotogalleryAdded){
                        photosAddedLabel.setVisible(true);
                        textArea.setText(textArea.getText().replaceAll("<!--end of content-->", InsertPhotogalleryController.finalPhotoGallery));
                    }                    
                }
            });
            
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    
    protected static boolean isVideogalleryAdded = false;    
    @FXML
    private void videoInsertAction(ActionEvent event){
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/fxml/InsertVideo.fxml"));
            Stage stage = new Stage();
            stage.setTitle("Добавление видеоматериалов");
            Image icon = new Image("/images/favicon.png");
            stage.getIcons().add(icon);
            stage.setScene(new Scene(root));
            stage.initModality(Modality.WINDOW_MODAL);
            stage.initOwner(((Node)event.getSource()).getScene().getWindow());
            stage.setResizable(false);
            stage.show();
            
            stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
                @Override
                public void handle(WindowEvent event) {
                    if(isVideogalleryAdded){
                        videosAddedLabel.setVisible(true);                        
                        textArea.setText(textArea.getText().replaceAll("<!--end of content-->", InsertVideogalleryController.finalVideoGallery));
                    }                    
                }
            });
            
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
        
    protected static boolean isVoteAdded = false;    
    @FXML
    private void voteInsertAction(ActionEvent event){
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/fxml/InsertVote.fxml"));
            Stage stage = new Stage();
            stage.setTitle("Добавление голосования");
            Image icon = new Image("/images/favicon.png");
            stage.getIcons().add(icon);
            stage.setScene(new Scene(root));
            stage.initModality(Modality.WINDOW_MODAL);
            stage.initOwner(((Node)event.getSource()).getScene().getWindow());
            stage.setResizable(false);
            stage.show();
            
            stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
                @Override
                public void handle(WindowEvent event) {
                    if(isVoteAdded){
                        voteAddedLabel.setVisible(true);                        
                        textArea.setText(textArea.getText().replaceAll("<p><ins><strong></strong></ins></p>", InsertVoteController.id));
                    }                    
                }
            });
            
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }
}