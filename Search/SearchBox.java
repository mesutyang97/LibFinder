package Search;

import javax.swing.*;
import java.awt.event.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class SearchBox implements ActionListener{


    JLabel bookName;

    JLabel callNo;


    JTextField enterBook;
    JTextField enterCall;
    JButton search;


    public SearchBox() {

        JFrame frame = new JFrame();
        frame.setTitle("LibFinder");
        frame.setSize(300, 100);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel totalPanel = new JPanel();
        totalPanel.setLayout(new BoxLayout(totalPanel, BoxLayout.Y_AXIS));
        totalPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        frame.add(totalPanel);



        bookName = new JLabel("Enter book title");
        callNo = new JLabel("Enter Call no.");

        JPanel book = new JPanel();
        book.add(bookName);

        enterBook = new JTextField("e.g. The Perks of Being a WallFlower");
        enterBook.setEditable(true);

        book.add(enterBook);

        totalPanel.add(book);

        JPanel call = new JPanel();
        call.add(callNo);
        enterCall = new JTextField("e.g. Q143.H6 .A37 1980");
        call.add(enterCall);

        totalPanel.add(call);

        JPanel buttons = new JPanel();
        search = new JButton("Find Book");
        buttons.add(search);
        search.addActionListener(this);

        totalPanel.add(buttons);

        frame.pack();
        frame.setVisible(true);

    }



    @Override
    public void actionPerformed(ActionEvent evt) {



        /* Current text in book title text box. */
        String inpTitle = enterBook.getText();
        /* Current text in Call no. text box. */
        String inpCall = enterCall.getText();
        String callno;

        if (!inpTitle.equals("e.g. The Perks of Being a WallFlower") && !inpTitle.equals("")) {
            /* EXECUTE FUNCTION TO FIND CALL NO */
            callno = findCall(inpTitle);
            if (callno == null) {
                Error();
            }

        }

        else if (!inpCall.equals("") && !inpCall.equals("e.g. Q143.H6 .A37 1980")) {
            callno = enterCall.getText();

        }

        else {
            Error();

        }


    }

    private void Error() {
        JOptionPane.showMessageDialog(null,
                "The book you're searching for could not be found",
                "Unable to find book.",
                JOptionPane.PLAIN_MESSAGE);
    }

    private String findCall(String bookName) {
        String callno = null;
        BufferedReader br;
        FileReader fr;
        String currLine;

        String file = "/Users/Phoenix/Documents/LibFinder/BookList.inp";

        try {
            fr = new FileReader(file);
            br = new BufferedReader(fr);

            while ((currLine = br.readLine()) != null) {
                String currcall = br.readLine();
                if (currLine.equals(bookName)) {
                    callno = currcall;
                }
            }

            br.close();
            fr.close();


        } catch (IOException e) {

        }

        return callno;

    }


    static private void libStruc(String callno) {

        BufferedReader br;
        FileReader fr;
        String currLine;

        String callsec = "A";
        String callshelf = "32";
        String callrow = "";

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

                    boolean colfound = false;

                    while ((currLine = br.readLine()).substring(0, 2).equals("col")) {
                        String[] colinfo = currLine.split("\\s+");
                        if (colinfo[1].equals(callshelf)) {
                            colfound = true;
                        } else if (!colfound) {
                            colno += 1;
                        }
                        columns += 1;
                    }

                    while (!((currLine = br.readLine()).substring(0, 2).equals("sec"))) {
                        if (currLine.substring(0,4).equals("shelf")) {
                            shelves += 1;
                        }
                    }
                    return;
                } else if (secfound) {
                    shelfno += 1;
                    shelves += 1;
                    String[] shelfinfo = currLine.split("\\s+");
                    if (callshelf.compareTo(shelfinfo[2]) <= 0) {
                        shelffound = true;
                    }
                } else if (currLine.substring(0, 4).equals("floor")) {
                    floor = Character.toString(currLine.charAt(6));

                } else if (currLine.substring(0, 4).equals("sec " + callsec)) {
                    String[] secinfo = currLine.split("\\s+");
                    lt = secinfo[2];
                    rb = secinfo[3];
                    secfound = true;
                }

            }

            System.out.println(lt);
            System.out.println(rb);
            System.out.println(shelves);
            System.out.println(shelfno);
            System.out.println(colno);
            System.out.println(columns);
            br.close();
            fr.close();


        } catch (IOException e) {

        }

    }


    public static void main(String[] args) {
        //SearchBox startSearch =
        //new SearchBox();

        libStruc("hi");

    }

}
