package converter;

import converter.operations.ExceptionHandler;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;

public class Main extends Application {

    public static Stage homeStage;
    public static Stage secondStage;

    public static String ioStackTrace;
    public static Exception exception;

    @Override
    public void start(Stage primaryStage) throws IOException {

        homeStage = new Stage();
        secondStage = new Stage();

        FXMLLoader loader = new FXMLLoader(getClass().getResource("fxmlFiles/Home.fxml"));
        Parent root = (Parent) loader.load();

        homeStage.getIcons().add(new Image("file:resources/iconfinder_General_Office_48_3592847.png"));
        homeStage.setScene(new Scene(root, 600, 400));
        homeStage.setTitle("Mold Base Converter");
        homeStage.setResizable(false);

        homeStage.setOnCloseRequest(e -> {
            try {
                e.consume();
                closeRequest();
            } catch (IOException ioException) {
                StringWriter sw = new StringWriter();
                ioException.printStackTrace(new PrintWriter(sw));
                Main.ioStackTrace = sw.toString();
                Main.exception = ioException;
                ExceptionHandler exceptionHandler = new ExceptionHandler();
            }
        });

        homeStage.show();
    }

    public void closeRequest() throws IOException {

        Stage close = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("fxmlFiles/CloseRequest.fxml"));
        close.setScene(new Scene(root, 350, 150));
        close.setResizable(false);
        close.getIcons().add(new Image("file:resources/iconfinder_General_Office_48_3592847.png"));

        close.initModality(Modality.WINDOW_MODAL);
        close.initOwner(Main.homeStage);
        close.showAndWait();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
