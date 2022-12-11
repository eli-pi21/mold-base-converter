package converter.operations;/*
 *Excel file elabora il file di testo dato in input scomponendo i suoi vari elementi.
 *"lines" sono le righe del file, "oldpath" è invece il percorso del file di testo da elaborare.
 */

import converter.Main;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ExcelFile {

    //Le righe di ciascun file da mantenere sono sempre 15.
    //La prima riga rappresenta solamente le etichette delle colonne.
    public static final int N_LINES = 16;
    //Le righe sono formate da oggetti di tipo Converter.Data, che mantengono le 3 proprietà di ogni componente.
    private final List<Data> lines;
    private final String oldPath;
    public static String newName;

    public ExcelFile(File file) {
        oldPath = file.getParent();
        lines = new ArrayList<Data>(N_LINES);
        readingFile(file, this);
        //Viene chiamato il metodo di appoggio readingFile che analizza le righe e le costruisce come oggetti di tipo Converter.Data
        //Viene passato il file in ingresso e l'oggetto in questione.
    }

    //file f file è il file da leggere, excelFile l'oggetto in questione da costruire
    public static void readingFile(File f, ExcelFile excelFile) throws IndexOutOfBoundsException, NumberFormatException {
        Scanner file = null;
        try {
            file = new Scanner(f);
            file.nextLine(); //La prima riga viene scartata visto che è formata da 3 stringhe.
            for (int i = 1; i < N_LINES; i++) {
                String line = file.nextLine();
                excelFile.setLines(new Data(line));
            }
            //Dopo questo for verrà effettivamente a costruirsi il file Excel con le sole 15 righe fromate da oggetti Converter.Data
        } catch (IOException e) {
            StringWriter sw = new StringWriter();
            e.printStackTrace(new PrintWriter(sw));
            Main.ioStackTrace = sw.toString();
            Main.exception = e;
            ExceptionHandler exceptionHandler = new ExceptionHandler();
        } finally {
            if (file != null) {
                file.close(); //Chiude lo stream.
            }
        }
    }

    public void setLines(Data line) {
        lines.add(line);
    }

    /*
     * Nome del file dato come parametro in ingresso
     * Questo metodo invocato sull'Converter.ExcelFile produce un file con il nome passato per parametro.
     * Il file viene collocato nella stessa cartella del file scelto dell'utente inizialmente.
     */
    public void cleanFile(String fileName) throws IOException {
        PrintWriter outputStream = null;
        //oldPath è il percorso della cartella contenente il file che l'utente vuole convertire.
        newName = oldPath + "\\" + fileName;
        try {
            outputStream = new PrintWriter(new File(newName));
            for (Data d : lines) {
                String newLine = d.cleanLine(); //Elimina "formula" dai dati
                outputStream.println(newLine);
            }
            //Viene stampato un file con formattazione dove è eliminato l'attributo "formula" dell'oggetto Converter.Data.
        } catch (IOException e) {
            throw new IOException();
            //e.printStackTrace();
        } finally {
            if (outputStream != null)
                outputStream.close();
        }
    }
}