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
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class MainMenu implements Initializable {
        
    @FXML
    private void openSlideMaker(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/fxml/SlideMaker.fxml"));
            Stage stage = new Stage();          
            stage.setTitle("Slide Maker");
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
    private void openTablesNMC(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/fxml/TablesNMC.fxml"));
            Stage stage = new Stage();          
            stage.setTitle("Таблицы НМЦ");
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
    private void openDigest(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/fxml/Digest.fxml"));
            Stage stage = new Stage();          
            stage.setTitle("Digest");
            Image icon = new Image("/images/favicon.png");
            stage.getIcons().add(icon);
            stage.setScene(new Scene(root));
            stage.initModality(Modality.WINDOW_MODAL);
            stage.initOwner(((Node)event.getSource()).getScene().getWindow());
            
            stage.setHeight(650);
            stage.setWidth(900);
            stage.setMinWidth(900);
            stage.setMinHeight(650);
            
            stage.show();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    
    @FXML
    private void openPhotoAlbum(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/fxml/PhotoAlbum.fxml"));
            Stage stage = new Stage();          
            stage.setTitle("Photo Album Maker");
            Image icon = new Image("/images/favicon.png");
            stage.getIcons().add(icon);
            stage.setScene(new Scene(root));
            stage.initModality(Modality.WINDOW_MODAL);
            stage.initOwner(((Node)event.getSource()).getScene().getWindow());
            stage.setMinWidth(608.0);
            stage.setMinHeight(444.0);
            stage.show();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    
    @FXML
    private void openMultiLinks(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/fxml/MultiLinks.fxml"));
            Stage stage = new Stage();          
            stage.setTitle("MultiLinks");
            Image icon = new Image("/images/favicon.png");
            stage.getIcons().add(icon);
            stage.setScene(new Scene(root));
            stage.initModality(Modality.WINDOW_MODAL);
            stage.initOwner(((Node)event.getSource()).getScene().getWindow());
            
            stage.setMinWidth(650.0);
            stage.setMinHeight(375.0);
            
            stage.show();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    
    @FXML
    private void openTablesIPP(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/fxml/TablesIPP.fxml"));
            Stage stage = new Stage();          
            stage.setTitle("Таблицы ИПП");
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
        // TODO
    }    
}
