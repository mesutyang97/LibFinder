package Search;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.regex.Pattern;
import java.util.regex.Matcher;


public class LibStruc{

    static public void libStruc(String callno) {

        BufferedReader br;
        FileReader fr;
        String currLine;

        String[] splitCallNo = splitCallNum(callno);
        String callsec = splitCallNo[0];
        String callshelf = splitCallNo[1];
        String callrow = splitCallNo[2];

        //String lt;
        //String rb;

        String file = "Search/LibStructure.inp";

        try {
            fr = new FileReader(file);
            br = new BufferedReader(fr);

            String floor = null;
            boolean secfound = false;
            boolean shelffound = false;

            int shelfno = 0;
            int shelves = 0;

            int colno = 0;
            int columns = 0;

            String lt = "";
            String rb = "";


            while ((currLine = br.readLine()) != null) {

                if (shelffound) {
                    System.out.println("Shelf is found, has column: " + currLine);
                    boolean colfound = false;

                    while ((currLine = br.readLine()).substring(0, 3).equals("col")) {
                        String[] colinfo = currLine.split("\\s+");
                        if (colinfo[1].equals(callshelf)) {
                            colfound = true;
                        } else if (!colfound) {
                            colno += 1;
                        }
                        columns += 1;
                    }
                    currLine = br.readLine();
                    while ((currLine != null) &&!(currLine.substring(0, 2).equals("sec"))) {
                        if (currLine.substring(0, 4).equals("shelf")) {
                            shelves += 1;
                        }
                        currLine = br.readLine();
                    }
                    break;
                } else if (secfound) {
                    System.out.println("Section is found, has shelf" + currLine);
                    shelfno += 1;
                    shelves += 1;
                    String[] shelfinfo = currLine.split("\\s+");
                    if (callshelf.compareTo(shelfinfo[1]) <= 0) {
                        shelffound = true;
                    }
                } else if (currLine.substring(0, 5).equals("floor")) {
                    System.out.println("Floor is found" + currLine);
                    floor = Character.toString(currLine.charAt(6));

                } else if (currLine.substring(0, 5).equals("sec " + callsec)) {
                    System.out.println("Storing Information about section" + callsec);
                    String[] secinfo = currLine.split("\\s+");
                    lt = secinfo[2];
                    rb = secinfo[3];
                    secfound = true;
                } else {
                    System.out.println("Catch an unreadable line");
                }


            }

            System.out.println("Summary");
            System.out.println(lt);
            System.out.println(rb);
            System.out.println("Floor: " + floor);
            System.out.println("Shelf " + shelves + " out of " + shelfno + " shelves.");
            System.out.println("May be at " + columns + " out of " + colno + " columns.");
            br.close();
            fr.close();


        } catch (IOException e) {

        }
    }

    static String[] splitCallNum(String callNum) {
        Pattern p = Pattern.compile("([A-Z]{1,2})(\\d{1,3}).([A-Z]{0,2}:\\d{1,3})");
        Matcher matcher = p.matcher(callNum);
        return new String[] {matcher.group(1), matcher.group(2), matcher.group(3)};
    }

    public static void main(String[] args) {
        //SearchBox startSearch =
        //new SearchBox();

        libStruc("A32.H4");

    }
}