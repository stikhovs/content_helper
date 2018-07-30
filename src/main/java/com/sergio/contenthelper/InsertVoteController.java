package com.sergio.contenthelper;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class InsertVoteController extends Digest {
    @FXML
    private Button addVoteButton;
    @FXML
    private TextField voteID;
    
    protected static String id = "";
        
    @FXML
    private void addVoteAction(ActionEvent event){
        id = voteID.getText();
        
        id = "<p><ins><strong></strong></ins></p>\n"+
"            <div class='col-md-4 pull-right' style='border-left: 1px solid #ddd;'>\n" +
"        <div class='umb-macro-holder mceNonEditable'><!-- <?UMBRACO_MACRO macroAlias='PollMacro' pollId='"+id+"' /> --> <ins>Macro alias: <strong>PollMacro</strong></ins></div>\n" +
"      </div>";
        
        isVoteAdded = true;
        
        Stage stage = (Stage)addVoteButton.getScene().getWindow();
        stage.fireEvent(new WindowEvent(stage, WindowEvent.WINDOW_CLOSE_REQUEST));
    }
    
    
    @FXML
    private void checkForNumeric(KeyEvent keyEvent){
        if(!voteID.getText().matches("[0-9]+")){
            voteID.setText(voteID.getText().replaceAll("[^0-9]+", ""));
            voteID.selectPositionCaret(voteID.getLength());
            voteID.deselect();
        }
        addVoteButton.setDisable(false);
            
    }
}
