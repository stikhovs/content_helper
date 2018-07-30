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
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Accordion;
import javafx.scene.control.TextArea;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Font;
import javafx.stage.FileChooser;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.xwpf.model.XWPFHeaderFooterPolicy;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFTable;
import org.apache.poi.xwpf.usermodel.XWPFTableCell;
import org.apache.poi.xwpf.usermodel.XWPFTableRow;

/**
 *
 * @author tihov
 */
public class TablesIPP implements Initializable {
    
       
    @FXML
    private AnchorPane textAnchor;
    
    
    ArrayList<String> chosenFiles = new ArrayList();
    ArrayList<String> tableNames = new ArrayList();
    ObservableList<TitledPane> panes = FXCollections.observableArrayList();
    List<File> selectedFiles = new ArrayList();
    private String startPathFrom = "";
    List<String> lines = new ArrayList<>();
    Path path = Paths.get("startPath.txt");
    
    @FXML
    private void openFileAction(ActionEvent event){
        chosenFiles.clear();
        panes.clear(); 
        tableNames.clear();        
        FileChooser fc = new FileChooser();
        
//        File startPath = new File("C:\\Users\\tihov\\Desktop\\Today");
//        if (!startPath.exists()) {
//          startPath = new File("C:\\Users\\All\\Desktop\\");
//        }
        System.out.println(path);
        try (Stream<String> lineStream = Files.lines(path)) {
            lines = lineStream.collect(Collectors.toList());
            for (String s : lines){
            	startPathFrom += s;
            }
            System.out.println(startPathFrom);
        } 
        catch (IOException ex) {
        	ex.getMessage();
        	System.out.println("Файл, где указан путь не найден");
        }

        System.out.println(startPathFrom);
                
        fc.setInitialDirectory(new File(System.getProperty("user.home")));
        
        
        fc.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("docx files","*.docx"));
        selectedFiles = fc.showOpenMultipleDialog(null);
        //panes.addAll(accord.getPanes());
        if(selectedFiles != null){
            for(int i = 0; i < selectedFiles.size(); i++){
                chosenFiles.add(selectedFiles.get(i).getAbsolutePath());
                
            }
            
        }
        else {
            System.out.println("Файл не был выбран");
        }
    }
    
    
    
    
    @FXML
    private void handleButtonAction(ActionEvent event) {
        
        textAnchor.getChildren().clear();
        
        Accordion accordion = new Accordion();
        
        for(int filePathNum = 0; filePathNum < chosenFiles.size(); filePathNum++){           
            
            
            
            List<String> htmlElements = new ArrayList();
            htmlElements.add("<div id=\"area\">\n\t");
            
            try (FileInputStream fileInputStream = new FileInputStream(new File(chosenFiles.get(filePathNum)))) {

                // открываем файл и считываем его содержимое в объект XWPFDocument
                XWPFDocument docxFile = new XWPFDocument(OPCPackage.open(fileInputStream));
                XWPFHeaderFooterPolicy headerFooterPolicy = new XWPFHeaderFooterPolicy(docxFile);

                

                // печатаем содержимое всех параграфов документа в консоль
                List<XWPFParagraph> paragraphs = docxFile.getParagraphs();
                for (XWPFParagraph p : paragraphs) {
                    if(p.getText().matches("^$")){
                        continue;
                    }
                    System.out.println(p.getText());
                    htmlElements.add("<h3>" + p.getText() + "</h3>\n");
                }
                
                htmlElements.add("<table>\n\t");
                htmlElements.add("<tbody>\n\t");
                //htmlElements.add("<tr>");
                
                // печатаем содержимое всех параграфов документа в консоль
                List<XWPFTable> tables = docxFile.getTables();              
                
                List<String> table = new ArrayList();

                
                int rowQuantity = 0;
                int columnQuantity = 0;
                
                for (XWPFTable t : tables) {
                    rowQuantity = t.getNumberOfRows();
                    List<XWPFTableRow> row = t.getRows();
                    for (XWPFTableRow r : row) { 
                        List<XWPFTableCell> cell = r.getTableCells();
                        columnQuantity = cell.size();
                        for (XWPFTableCell c : cell) {  
                            table.add(c.getText());
                        }
                    }
                    
                }
                                
                double x = 0;
                double y = (double)(columnQuantity-1)/columnQuantity;
                
                for(int i = 0; i < table.size(); i++) {
                    x = ((double)i/columnQuantity - i/columnQuantity);
                    if(i%columnQuantity == 0){
                        htmlElements.add("<tr>\n\t");
                    } 
                    
                    if(i < columnQuantity){
                        htmlElements.add("<td>\n\t" + "<p><strong>" + table.get(i) + "</strong></p>\n" + "</td>\n");
                    } else
                    htmlElements.add("<td>\n\t" + "<p>" + table.get(i) + "</p>\n" + "</td>\n");
                    if(String.format("%.2f", x).equals(String.format("%.2f", y))){
                        htmlElements.add("\n</tr>");
                    }
                }
                
                htmlElements.add("\n</tbody>");
                htmlElements.add("\n</table>");
                                

                for(String s : htmlElements){
                    System.out.print(s);
                }
                
                
                
                /*System.out.println("_____________________________________");
                // печатаем все содержимое Word файла
                XWPFWordExtractor extractor = new XWPFWordExtractor(docxFile);
                System.out.println(extractor.getText());*/

            } catch (Exception ex) {
                ex.printStackTrace();
            }
            
            TitledPane tp = new TitledPane();
            tableNames.add(selectedFiles.get(filePathNum).getName());
            tp.setText(tableNames.get(filePathNum));
            tp.setFont(Font.font(14));
            TextArea t = new TextArea();
            String html = "";
            for(String s : htmlElements){
                html += s;  
            }
            t.setText(html);
            t.setFont(Font.font(14));
            tp.setContent(t);
            accordion.getPanes().addAll(tp);
            
            if(filePathNum == 0) accordion.setExpandedPane(tp);
            
            
        }
        AnchorPane.setTopAnchor(accordion, 0.0);
        AnchorPane.setRightAnchor(accordion, 0.0);
        AnchorPane.setBottomAnchor(accordion, 0.0);
        AnchorPane.setLeftAnchor(accordion, 0.0);
        
        textAnchor.getChildren().addAll(accordion);
        
        
        
//        Accordion accordion = new Accordion();
//        TitledPane tp1 = new TitledPane();
//        TitledPane tp2 = new TitledPane();
//        tp1.setText("first");
//        tp2.setText("second");
//        
//        accordion.getPanes().addAll(tp1,tp2);
//                
//        System.out.println(textAnchor);
//        textAnchor.getChildren().addAll(l,accordion);
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
