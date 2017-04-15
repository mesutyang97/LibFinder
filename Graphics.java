package Maps;
import javax.swing.*;
//import java.awt.*;
import java.awt.event.*;
import java.util.List;
import java.util.LinkedList;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Graphics2D;



public class Graphics implements ActionListener{
    /* The name of book. */
    String bookName;
    /* The call number. */
    String callNum;

    /* The list of FLOOR Objects. */
    List<Floor> flrL;
    /* The index for floor it the book is on. */
    int flr;
    /* Width of each floor on the screen. */
    int flr_w = 30;
    /* Length of each floor on the screen. */
    int flr_l = 10;
    /* Gap between floors. */
    int flr_g = 50;

    /* LinkedList of floor button. */
    LinkedList<Rectangles> flrBt;



    /* The list of SECTION Objects. */
    List secL;
    /* The index for section in FLR the book is on. */
    int sec;

    /* The list of SHELF Objects. */
    List slfL;
    /* The index for shelf in SEC the book is on. */
    int slf;

    /* The list of COLUMN Objects. */
    List colL;
    /* The index for column in SLF the book is on. */
    int col;

    /* !!! The number of rows in the column, since the row is an estimation.  */
    int NumRows;
    /* The index for row in COL the book is on. */
    int row;




    public static void main(String[] args) {
        //LinkedList<Floor> qwa = new LinkedList(java.util.Arrays.asList(new Floor("dfs"), new Floor("bfs")));

        //new MapLayers(qwa, 1);
        new Graphics();
    }

    public Graphics(){

        JFrame totalFrame = new JFrame();
        totalFrame.setTitle("Libray Maps");
        totalFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JPanel totalPanel = new JPanel();

        totalPanel.setLayout(new BorderLayout());


        totalPanel.setContentPane(new JLabel(new ImageIcon("/Users/Phoenix/Downloads/stacks_c-1.png")));
        totalPanel.setLayout(new FlowLayout());
        l1=new JLabel("Here is a button");
        b1=new JButton("I am a button");
        add(l1);
        add(b1);
        // Just for refresh :) Not optional!
        setSize(399,399);
        setSize(400,400);
        totalFrame.setVisible(true);
    }








}
