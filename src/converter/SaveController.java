package converter;

import converter.*;

import converter.operations.ExcelFile;
import converter.operations.ExceptionHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;

public class SaveController {

    private ExcelFile ef;

    @FXML
    private Button bConvertSave;
    @FXML
    private TextField newFileName;
    @FXML
    private ProgressIndicator progressIndicator;

    @FXML
    public void initialize() {
        progressIndicator.setVisible(false);
        bConvertSave.setDefaultButton(true);
        bConvertSave.setOnAction(e -> {
            try {
                saveFile();
            } catch (IOException ioException) {
                StringWriter sw = new StringWriter();
                ioException.printStackTrace(new PrintWriter(sw));
                Main.ioStackTrace = sw.toString();
                Main.exception = ioException;
                ExceptionHandler exceptionHandler = new ExceptionHandler();
            }
        });
    }

    public void saveFile() throws IOException {

        bConvertSave.setVisible(false);
        newFileName.setDisable(true);

        progressIndicator.setVisible(true);
        String fileName = newFileName.getText();
        ef.cleanFile(fileName);
        progressIndicator.setVisible(false);

        FXMLLoader loader = new FXMLLoader(getClass().getResource("fxmlFiles/ConversionCompleted.fxml"));
        Parent root = (Parent) loader.load();
        Main.secondStage.setScene(new Scene(root, 390, 200));
        Main.secondStage.setResizable(false);
        Main.secondStage.getIcons().add(new Image("file:resources/iconfinder_General_Office_48_3592847.png"));
    }

    public void setEf(ExcelFile ef) {
        this.ef = ef;
    }
}
