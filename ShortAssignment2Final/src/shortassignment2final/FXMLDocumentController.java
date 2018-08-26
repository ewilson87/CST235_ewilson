/**
 * Program: CST-235 Short Project 2
 * File: FXMLDocumentController.java
 * Summary: Controller class to link the JavaFX application with the JavaBean from week 1
 * Author: Evan Wilson
 * Date: August 12th, 2018
 * */
package shortassignment2final;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

public class FXMLDocumentController implements Initializable {

    //Declare variables that point to controls within the FXML document
    @FXML
    private TextField inputFilePath;
    @FXML
    private Text charsAnswer;
    @FXML
    private Text wordsAnswer;
    @FXML
    private Text sumAnswer;

    //Method to handle the button click from the JavaFX application after user enters a file path
    @FXML
    private void handleButtonAction(ActionEvent event) {

        //instantiates an EntityBean object from week 1
        EntityBean entityBean = new EntityBean();

        //sets the FilePath using the input text box
        entityBean.setFilePath(inputFilePath.getText());

        //try and catch to open the file, process it according to the bean and assign results to each area as applicable
        try {
            entityBean.openFile();
            entityBean.processFile();

            charsAnswer.setText(String.valueOf(entityBean.getTotalChars()));
            wordsAnswer.setText(String.valueOf(entityBean.getTotalWords()));
            sumAnswer.setText(String.valueOf(entityBean.getSumOfNumbers()));
            //catch statement to let user know there was an error opening the text file
        } catch (IOException ex) {
            entityBean.setFileContent("Incorrect file path or type entered.");
            charsAnswer.setText("Incorrect file path or type entered.");
            wordsAnswer.setText("Incorrect file path or type entered.");
            sumAnswer.setText("Incorrect file path or type entered.");
        }

    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

}
