package converter.controllers;

import converter.*;

import converter.operations.ExcelFile;
import converter.operations.ExceptionHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;

public class ConversionController {

    @FXML
    private Button home;
    @FXML
    private Button exit;
    @FXML
    private Button openFile;

    public void initialize() {
        home.setOnAction(e -> returnHome());
        exit.setOnAction(e -> System.exit(0));
        openFile.setOnAction(e -> {
            try {
                openNewFile();
            } catch (IOException ioException) {
                StringWriter sw = new StringWriter();
                ioException.printStackTrace(new PrintWriter(sw));
                Main.ioStackTrace = sw.toString();
                Main.exception = ioException;
                ExceptionHandler exceptionHandler = new ExceptionHandler();
            }
        });
    }

    public static void returnHome() {
        Main.secondStage.hide();
        Main.homeStage.show();
    }

    public static void openNewFile() throws IOException {
        File file = new File(ExcelFile.newName);
        Desktop desktop = Desktop.getDesktop();
        desktop.open(file);
    }
}
