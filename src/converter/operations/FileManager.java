package converter.operations;/*
 * Gestisce FileChooser al momento della selezione del file da parte dell'utente.
 * L'oggetto FileChooser è l'unico suo attributo.
 */

import converter.Main;
import javafx.stage.FileChooser;

import java.io.File;

public final class FileManager {

    public File chooseFile() {
        final FileChooser fileChooser = new FileChooser();
        configureFileChooser(fileChooser);
        File file = fileChooser.showOpenDialog(Main.homeStage);
        return file;
    }

    private static void configureFileChooser(final FileChooser fileChooser) {
        fileChooser.setTitle("Choose a file");
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("TXT", "*.txt"));
        fileChooser.setInitialDirectory(new File(System.getProperty("user.home")));
    }
}