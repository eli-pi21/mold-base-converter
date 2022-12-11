package converter.operations;

import converter.Main;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;

import java.io.IOException;

public class ExceptionHandler {

    public ExceptionHandler() {
        manageGenericException();
    }

    public void manageGenericException() {

        try {
            Parent root = FXMLLoader.load(getClass().getResource("matrixCalculator.fxmlFiles/Exception.fxml"));
            Main.secondStage.setScene(new Scene(root, 400, 300));
            Main.secondStage.setResizable(false);
            Main.secondStage.getIcons().add(new Image("file:resources/iconfinder_General_Office_48_3592847.png"));
            Main.secondStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
