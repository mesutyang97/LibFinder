package Search;

import Maps.BookInfo;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.regex.Pattern;
import java.util.regex.Matcher;
import java.util.List;


public class LibStruc{



    static public BookInfo libStruc(String callno, String bookName, String libStructFile) {

        BufferedReader br;
        FileReader fr;
        String currLine;

        String[] splitCallNo = splitCallNum(callno);
        String callsec = splitCallNo[0];
        String callshelf = splitCallNo[1];
        String callrow = splitCallNo[2];

        Pattern col_p = Pattern.compile("col ([0-9]+).*?");

        Pattern shelf_p = Pattern.compile("shelf ([0-9]+).*?([0-9]+).*?");



        try {
            fr = new FileReader(libStructFile);
            br = new BufferedReader(fr);

            String curfloor = null;
            boolean secfound = false;
            boolean shelffound = false;
            boolean colfound = false;

            String floorS = null;
            /* Offset floor number by 1 such that the first floor is 0. */
            int floorno = -1;

            int shelfno = 0;
            int shelves = 1;

            int colno = 0;
            int columns = 1;

            int x_tl = 0;
            int y_tl = 0;
            int sec_width = 0;
            int sec_length = 0;

            String libName = br.readLine();

            List<String> floorList = new LinkedList<>();
            for (String flr_name : br.readLine().split("\\s+")){
                floorList.add(flr_name);
            }



            Outerloop:
            while ((currLine = br.readLine()) != null) {

                if (shelffound) {
                    System.out.println("Shelf is found, has column: " + currLine);

                    while ((currLine = br.readLine()).substring(0, 3).equals("col")) {
                        Matcher col_matcher = col_p.matcher(currLine);
                        col_matcher.matches();
                        String colUpper= col_matcher.group(1);

                        if (callshelf.compareTo(colUpper) <= 0) {
                            colfound = true;
                        } else if (!colfound) {
                            colno += 1;
                        }
                        columns += 1;
                    }

                    currLine = br.readLine();
                    while (currLine != null) {
                        /* The section in which our book locate has ended, so terminate the loop. */
                        if (currLine.substring(0, 3).equals("sec")) {
                            break Outerloop;
                        } else if (currLine.substring(0, 5).equals("shelf")) {
                            System.out.println("leftover");
                            shelves += 1;
                        }
                        currLine = br.readLine();
                    }

                } else if (secfound) {
                    //System.out.println("Section is found, has shelf" + currLine + shelffound);
                    Matcher shelf_matcher = shelf_p.matcher(currLine);

                    if (!shelffound && shelf_matcher.matches()) {
                        shelfno += 1;
                        shelves += 1;
                        String lowBound = shelf_matcher.group(1);
                        String highBound = shelf_matcher.group(2);
                        //System.out.println("Comparison" + callshelf + " "
                        // + highBound + " " + (callshelf.compareTo(highBound) <= 0));

                        if (callshelf.compareTo(highBound) <= 0) {
                            shelffound = true;
                        }
                    }
                } else if (currLine.substring(0, 5).equals("floor")) {
                    //System.out.println("Floor is found" + currLine);
                    curfloor = Character.toString(currLine.charAt(6));
                    floorno += 1;
                } else if (currLine.substring(0, 5).equals("sec " + callsec)) {
                    //System.out.println("Storing Information about section" + callsec);
                    floorS = curfloor;
                    String[] secinfo = currLine.split("\\s+");
                    x_tl = Integer.parseInt(secinfo[2]);
                    y_tl = Integer.parseInt(secinfo[3]);
                    sec_width = Integer.parseInt(secinfo[4]);
                    sec_length = Integer.parseInt(secinfo[5]);
                    secfound = true;
                } else {
                    System.out.println("Catch an unreadable line");
                }


            }

            System.out.println("Summary");
            System.out.println(x_tl);
            System.out.println(y_tl);
            System.out.println("Floor: " + floorS + " with floor number " + floorno);
            System.out.println("Shelf " + shelfno + " out of " + shelves + " shelves.");
            System.out.println("Column " + colno + " out of " + columns + " columns.");
            br.close();
            fr.close();


            return new BookInfo(libName, callno, bookName,
                    floorList, floorno, shelves, shelfno, columns, colno, x_tl, y_tl, sec_width, sec_length);


        } catch (IOException e) {

        }
        return null;
    }

    static String[] splitCallNum(String callNum) {
        System.out.println(callNum);
        Pattern p = Pattern.compile("([A-Z]).*?(\\d+).*?([A-Z]+\\d+)");
        Matcher matcher = p.matcher(callNum);
        matcher.matches();
        System.out.println(matcher.group(1));
        System.out.println(matcher.group(2));
        System.out.println(matcher.group(3));
        return new String[] {matcher.group(1), matcher.group(2), matcher.group(3)};
    }



    public static void main(String[] args) {
        //SearchBox startSearch =
        //new SearchBox();

        libStruc("A28.H4", "Blablablab", "Search/LibStructure.inp");

    }
}
