package converter;

import converter.controllers.ConversionController;
import converter.operations.ExcelFile;
import converter.operations.ExceptionHandler;
import converter.operations.FileManager;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;

public class HomeController {

    @FXML
    private Button bChoose;
    @FXML
    private Button info;
    @FXML
    private Pane infoPane;
    @FXML
    private Button close;

    private ExcelFile excelFile;

    @FXML
    public void initialize() {

        infoPane.setVisible(false);
        bChoose.setDefaultButton(true);
        bChoose.setOnAction(e -> {
            try {
                this.chooseFile();
            } catch (IOException ioException) {
                StringWriter sw = new StringWriter();
                ioException.printStackTrace(new PrintWriter(sw));
                Main.ioStackTrace = sw.toString();
                Main.exception = ioException;
                ExceptionHandler exceptionHandler = new ExceptionHandler();
            } catch (IndexOutOfBoundsException indexOutOfBoundsException) {
                StringWriter sw = new StringWriter();
                indexOutOfBoundsException.printStackTrace(new PrintWriter(sw));
                Main.ioStackTrace = sw.toString();
                Main.exception = indexOutOfBoundsException;
                ExceptionHandler exceptionHandler = new ExceptionHandler();
            } catch (NumberFormatException numberFormatException) {
                StringWriter sw = new StringWriter();
                numberFormatException.printStackTrace(new PrintWriter(sw));
                Main.ioStackTrace = sw.toString();
                Main.exception = numberFormatException;
                ExceptionHandler exceptionHandler = new ExceptionHandler();
            }
        });
        info.setOnAction(e -> infoPane.setVisible(true));
        close.setOnAction(e -> infoPane.setVisible(false));

    }

    public void chooseFile() throws IOException, NumberFormatException, IndexOutOfBoundsException {

        FileManager fm = new FileManager();
        File f = fm.chooseFile();
        if (f == null) {
            bChoose.setDisable(false);
        } else {
            Main.homeStage.hide();

            FXMLLoader loader = new FXMLLoader(getClass().getResource("fxmlFiles/SaveOptions.fxml"));
            Parent root = loader.load();

            excelFile = new ExcelFile(f);
            SaveController controller = loader.getController();
            controller.setEf(excelFile);

            Main.secondStage.setTitle("Saving Options");
            Main.secondStage.setScene(new Scene(root, 400, 251));
            Main.secondStage.setResizable(false);
            Main.secondStage.setOnCloseRequest(e -> ConversionController.returnHome());
            Main.secondStage.show();
        }
    }
}
