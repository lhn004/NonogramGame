/* *****************************************
 * CSCI205 -Software Engineering and Design
 * Spring2023* Instructor: Prof. Brian King
 ** Name: LINH NGUYEN
 * Section: 9am
 * Date: 09/04/2023
 * Time: 14:57
 *
 * Project: csci205_labs
 * Package: lab11.ex1
 * Class: HelloFXMLController
 *
 * Description:
 *
 * *****************************************/
package lab11.ex1;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class HelloFXMLController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField textFieldName;

    @FXML
    private Text textName;

    @FXML
    private Slider sliderBlue;

    @FXML
    private Slider sliderGreen;

    @FXML
    private Slider sliderRed;

    @FXML
    private Slider sliderSize;

    @FXML
    private Label lblStatusBar;

    public HelloFXMLController() {
    }


    @FXML
    void initialize() {
        assert textFieldName != null : "fx:id=\"textFieldName\" was not injected: check your FXML file 'hellofxml.fxml'.";
        assert textName != null : "fx:id=\"textName\" was not injected: check your FXML file 'hellofxml.fxml'.";
        assert sliderBlue != null : "fx:id=\"sliderBlue\" was not injected: check your FXML file 'hellofxml.fxml'.";
        assert sliderGreen != null : "fx:id=\"sliderGreen\" was not injected: check your FXML file 'hellofxml.fxml'.";
        assert sliderRed != null : "fx:id=\"sliderRed\" was not injected: check your FXML file 'hellofxml.fxml'.";
        assert sliderSize != null : "fx:id=\"sliderSize\" was not injected: check your FXML file 'hellofxml.fxml'.";

        System.out.println(textName != null);

        initBindings();

//        Initialize the slider values with the correct data
        Color c = (Color) textName.getFill();
        sliderRed.setValue(c.getRed());
        sliderGreen.setValue(c.getGreen());
        sliderBlue.setValue(c.getBlue());
        sliderSize.setValue(textName.getFont().getSize());

        updateStatusBar();
    }

    
    /**
     * Set up bindings in our app
     */
    private void initBindings() {
        //Update the text object based on the textField changes
        textName.textProperty().bind(textFieldName.textProperty());

        // Adjust the red value based on the red slider
        sliderRed.valueProperty().addListener((observable, oldValue, newValue) ->{
            Color c = (Color)textName.getFill();
            textName.setFill(Color.color(newValue.doubleValue(), c.getGreen(), c.getBlue()));
            updateStatusBar();
        });

        // Adjust the green value based on the green slider
        sliderGreen.valueProperty().addListener((observable, oldValue, newValue) ->{
            Color c = (Color)textName.getFill();
            textName.setFill(Color.color(c.getRed(), newValue.doubleValue(),c.getBlue()));
            updateStatusBar();
        });

        // Adjust the blue value based on the blue slider
        sliderBlue.valueProperty().addListener((observable, oldValue, newValue) ->{
            Color c = (Color)textName.getFill();
            textName.setFill(Color.color(c.getRed(),c.getGreen(), newValue.doubleValue()));
            updateStatusBar();
        });

        // Adjust the font size based on the size slider
        sliderSize.valueProperty().addListener((observable, oldValue, newValue) ->{
            Font f = textName.getFont();
            textName.setFont(Font.font(f.getFamily(), newValue.doubleValue()));
            updateStatusBar();
        });

    }

    /**
     * Update the status bar with current color and size of the text
     */
    private void updateStatusBar(){
        Color c = (Color) textName.getFill();
        lblStatusBar.setText(String.format("Color: %s   Size: %.1f", c.toString(), textName.getFont().getSize()));
    }



}
