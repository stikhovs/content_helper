package com.sergio.contenthelper;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;


public class InsertPhotogalleryController extends Digest implements Initializable  {
    
    @FXML
    private Button addMorePhotosButton;
    @FXML
    private Button addPhotoGalleryButton;
    @FXML
    private Button addPhotosButton;
    @FXML
    private TextField albumTitle;
    @FXML
    private TextField firstPhotoLink;
    @FXML
    private TextField photoQuantity;
    @FXML
    private Label added;
    
    protected List<String> photoGalleryList = new ArrayList<>();   
    protected List<String> visiblePrintBlockList = new ArrayList<>();
    protected List<String> visiblePrintBlockPhotosList = new ArrayList<>();
    
    private int albumCounter = 0;
    
    protected static String finalPhotoGallery = "";
    
    
    @FXML
    private void addPhotosAction(ActionEvent event){
        
        String title = albumTitle.getText();
        String linkText = firstPhotoLink.getText().replaceAll("web1.", "");
        int count = Integer.parseInt(photoQuantity.getText());
        
        for(int i = 0; i < count; i++){
            String photoItem = "<a title=\""+title+"\" class=\"nofancy\" href=\""+linkText+"\" rel=\"fancybox-thumb\"> \n" +
"                <img title=\""+title+"\" src=\""+linkText+"\" /> \n" +
"            </a> ";
            photoGalleryList.add(photoItem);            
            if(i < 3 && albumCounter == 0){
                visiblePrintBlockPhotosList.add(linkText);
                linkText = updateLink(linkText);
                System.out.println(visiblePrintBlockPhotosList.get(i));
            } else
            linkText = updateLink(linkText);
        }
        albumCounter++;
        if(albumCounter == 1){
            for(int i = 0; i < 3; i++){
                String forPrint = "<div class='for-print'>\n" +
"                <div class='for-print-image'><img src='"+visiblePrintBlockPhotosList.get(i)+"' /></div>\n" +
"                <h5 class='print-description'>"+title+"</h5>\n" +
"            </div>";
                visiblePrintBlockList.add(forPrint);
            }
        }
        else if(albumCounter == 2){
            String forPrint = "<div class='for-print'>\n" +
"                <div class='for-print-image'><img src='"+firstPhotoLink.getText().replaceAll("web1.", "")+"' /></div>\n" +
"                <h5 class='print-description'>"+title+"</h5>\n" +
"            </div>";
            visiblePrintBlockList.add(2, forPrint);
            visiblePrintBlockList.remove(visiblePrintBlockList.size()-1);
            //System.out.println(visiblePrintBlockList.size());
        }
        else if(albumCounter == 3){
            String forPrint = "<div class='for-print'>\n" +
"                <div class='for-print-image'><img src='"+firstPhotoLink.getText().replaceAll("web1.", "")+"' /></div>\n" +
"                <h5 class='print-description'>"+title+"</h5>\n" +
"            </div>";            
            visiblePrintBlockList.add(3, forPrint);
            visiblePrintBlockList.remove(visiblePrintBlockList.size()-1);
        }
        added.setText("Добавлено: " + albumCounter);
        added.setVisible(true);
        addMorePhotosButton.setDisable(false);
        addPhotoGalleryButton.setDisable(false);
    }
    
    
    
    @FXML
    protected void addPhotoGalleryAction(ActionEvent event){
        photoGalleryList.add("</div>\n");
        visiblePrintBlockList.add("</div>\n");
        visiblePrintBlockList.add("</div>\n");
        visiblePrintBlockList.add("<!--end of photos-->\n");
        for(String s : photoGalleryList) finalPhotoGallery += s;
        for(String s : visiblePrintBlockList) finalPhotoGallery += s;
        
        isPhotogalleryAdded = true;
        
        photoGalleryList.clear();
        visiblePrintBlockList.clear();
        
        Stage stage = (Stage)addPhotoGalleryButton.getScene().getWindow();
        stage.fireEvent(new WindowEvent(stage, WindowEvent.WINDOW_CLOSE_REQUEST));
        
    }
    
    
    
    
    @FXML
    private void addMorePhotosAction(ActionEvent event){
        albumTitle.setText("");
        firstPhotoLink.setText("");
        photoQuantity.setText("");
        addMorePhotosButton.setDisable(true);
        addPhotoGalleryButton.setDisable(true);
        addPhotosButton.setDisable(true);
    }
    
    
    @FXML
    private void checkForNumeric(KeyEvent keyEvent){
        if(!photoQuantity.getText().matches("[0-9]+")){
            photoQuantity.setText(photoQuantity.getText().replaceAll("[^0-9]+", ""));
            photoQuantity.selectPositionCaret(photoQuantity.getLength());
            photoQuantity.deselect();
        }
        addPhotosButton.setDisable(false);    
    }
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       photoGalleryList.add("<!--end of content-->\n");
       photoGalleryList.add("<!--photos-->\n");
       photoGalleryList.add("<div class='no-page-break' id='gallery'>\n");
       photoGalleryList.add("<h3 class='photo-block-title'>Фотоматериалы</h3>\n");
       photoGalleryList.add("<div class='digest-slider hidden-print'>\n");
       
       visiblePrintBlockList.add("<div class=\"gallery-print visible-print-block\">\n");
       //textArea.getText();
       
    }    
}
