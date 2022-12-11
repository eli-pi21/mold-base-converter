package converter.operations;/*
 * Definisce una riga del foglio di Excel, dividendola dei dati delle 3 colonne.
 * Attributi nome, formula e valore.
 */

public class Data {

    private String name;
    private String formula;
    private double value;

    public Data(String lineFile) throws IndexOutOfBoundsException, NumberFormatException {
        //Il testo di Excel è in italiano, viene quindi sostituito . con , per convertire correttamente i numeri.
        String line = lineFile.replace(',', '.');
        //I 3 dati sono separati da un tab. Vengono quindi divisi in 3 con riferimento alla tabulazione \t.
        String[] data = line.split("\t");
        name = data[0];
        formula = data[1];
        value = Double.parseDouble(data[2]); //L'ultimo dato è un valore double.
    }

    public String getName() {
        return name;
    }

    public String getFormula() {
        return formula;
    }

    public double getValue() {
        return value;
    }

    //Elimina "formula" dai dati
    public String cleanLine() {
        return name + "\t" + value;
    }

    @Override
    public String toString() {
        return "Converter.Data {" +
                "name='" + name + '\'' +
                ", formula='" + formula + '\'' +
                ", value=" + value +
                '}';
    }
}
