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


public class InsertVideogalleryController extends Digest implements Initializable {
    
    @FXML
    private Button addMoreVideosButton;
    @FXML
    private Button addVideoGalleryButton;
    @FXML
    private Button addVideoButton;
    @FXML
    private TextField videoTitle;
    @FXML
    private TextField videoPageLink;
    @FXML
    private TextField videoFileLink;
    @FXML
    private TextField videoImageLink;
    @FXML
    private Label addedVideosLabel;
    
    protected List<String> videoGalleryList = new ArrayList<>(); 
    
    private int jwPlayerCounter = 1;
    private int videosAdded = 0;
    protected static String finalVideoGallery = "";
    
    
    @FXML
    private void addVideoAction(ActionEvent event){
        String title = videoTitle.getText();
        String pageLink = videoPageLink.getText();
        String fileLink = videoFileLink.getText().replaceAll("web1.", "");;
        String imageLink = videoImageLink.getText().replaceAll("web1.", "");;
        
        String str = "<div class='row'>\n" +
"            <div class='col-md-12'>\n" +
"                <div class='tenders-list'>\n" +
"                    <div class='tenders-line project-desc'>\n" +
"                        <h4 class='address' style='margin-top: 0;'><a href='"+pageLink+"' target='_blank' style='font-size: 18px;' rel='noopener noreferrer'>"+title+"</a></h4>\n" +
"                        <div class='video'>\n" +
"                            <div id='jwplayer"+jwPlayerCounter+"' style='width: 100%; height: 300px; background-color: black;'></div>\n" +
"                        </div>\n" +
"                        <script type='text/javascript'>\n" +
"                            jwplayer('jwplayer"+jwPlayerCounter+"').setup({\n" +
"                                file: '"+fileLink+"',\n" +
"                                image: '"+imageLink+"',\n" +
"                                width: '100%',\n" +
"                                aspectratio: '16:9'\n" +
"                            });\n" +
"\n" +
"                        </script>\n" +
"                    </div>\n" +
"                </div>\n" +
"            </div>\n" +
"        </div>";
        
        videoGalleryList.add(str);
        jwPlayerCounter++;
        
        videosAdded++;
        addedVideosLabel.setText("Видеороликов добавлено: " + videosAdded);
        
        addMoreVideosButton.setDisable(false);
        addVideoGalleryButton.setDisable(false);
    }
    
    @FXML
    private void checkForEmpty(KeyEvent keyEvent){
        if(!videoTitle.getText().isEmpty() &&
           !videoPageLink.getText().isEmpty() &&
           !videoFileLink.getText().isEmpty() &&
           !videoImageLink.getText().isEmpty()){
           addVideoButton.setDisable(false);
        }   
    }
    
    @FXML
    private void addMoreVideosAction(ActionEvent event){
        videoTitle.setText("");
        videoPageLink.setText("");
        videoFileLink.setText("");
        videoImageLink.setText("");
        addMoreVideosButton.setDisable(true);
        addVideoGalleryButton.setDisable(true);
        addVideoButton.setDisable(true);
    }
    
    @FXML
    private void addVideoGalleryAction(ActionEvent event){
        videoGalleryList.add("</div>\n");
        videoGalleryList.add("<!--end of videos-->\n");
        
        for(String s : videoGalleryList) finalVideoGallery += s;
        isVideogalleryAdded = true;
        
        videoGalleryList.clear();
        
        Stage stage = (Stage)addVideoGalleryButton.getScene().getWindow();
        stage.fireEvent(new WindowEvent(stage, WindowEvent.WINDOW_CLOSE_REQUEST));
        
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        videoGalleryList.add("<!--end of content-->\n");
        videoGalleryList.add("<!--videos-->\n");
        videoGalleryList.add("<div class='no-page-break'>\n");
        videoGalleryList.add("<h3>Видеоматериалы</h3>\n");
    }   
}
