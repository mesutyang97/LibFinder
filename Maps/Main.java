package Maps;

import Search.LibStruc;

import java.io.FileNotFoundException;
import java.io.FileReader;

import static Maps.MapsException.*;

/**
 * Main Class for this entire thingimigi.
 */
public class Main {
    public static void main(String... args) {
        try {
            new Main(args).process();
            return;
        } catch (MapsException excp) {
            System.err.printf("Error: %s%n", excp.getMessage());
        }
        System.exit(1);
    }

    /** Check ARGS and open the necessary files (see comment on main). */
    Main(String[] args) {
        if (args.length != 2) {
            throw error("2 arguments allowed");
        }
        _callNumDir = getInput(args[0]);
        _libStrucDir = getInput(args[1]);

    }





    /** Return a Scanner reading from the file named NAME. */
    private String getInput(String name) {
        try {
            FileReader fr = new FileReader(name);
            return name;
        } catch (FileNotFoundException excp) {
            throw error("could not open %s", name);
        }
    }

    /** HHH. */
    private void process() {
        SearchBox sb = new SearchBox(_callNumDir);
        BookInfo bookI = LibStruc.libStruc(sb.callNumS, sb.bookS, _libStrucDir);
        GraphicRect.createAndShowGui(bookI);
    }

    /** The path to file that stores the library structure. */
    private String _libStrucDir;

    /** The path to file that stores the book-call number relation. */
    private String _callNumDir;






}
