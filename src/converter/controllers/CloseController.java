package converter.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class CloseController {

    @FXML
    private Button yes;
    @FXML
    private Button no;

    public void initialize() {
        yes.setOnAction(e -> close());
        no.setOnAction(e -> dontClose());
    }

    public void close() {
        System.exit(0);
    }

    public void dontClose() {
        ((Stage) no.getScene().getWindow()).hide();
    }

}
