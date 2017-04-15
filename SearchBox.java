import javax.swing.*;
import java.awt.*;
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


    public static void main(String[] args) {
        //SearchBox startSearch =
        new SearchBox();


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



}
