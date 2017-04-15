import javax.swing.*;
import java.awt.*;
import java.awt.event.*;


public class FindCall {



    public FindCall() {

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
        enterCall = new JTextField("", 4);
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



    }



}
