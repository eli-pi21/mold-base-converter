package converter.controllers;

import converter.*;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;

public class ExceptionController {

    @FXML
    private Label label;
    @FXML
    private Button exit;
    @FXML
    private Button home;
    @FXML
    private Button moreDetails;
    @FXML
    private Label exceptionDetails;
    @FXML
    private ScrollPane scrollPane;

    public void initialize() {

        if (Main.exception instanceof NumberFormatException || Main.exception instanceof IndexOutOfBoundsException)
            label.setText("File selected not valid. Try again.");
        else
            label.setText("An exception occurred.");
        scrollPane.setVisible(false);
        home.setOnAction(e -> ConversionController.returnHome());
        exit.setOnAction(e -> System.exit(0));
        moreDetails.setOnAction(e -> showExceptionDetails());
    }

    public void showExceptionDetails() {
        String details = Main.ioStackTrace;
        exceptionDetails.setText(details);
        scrollPane.setVisible(true);
    }
}
