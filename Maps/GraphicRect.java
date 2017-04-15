package Maps;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.Stroke;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.io.File;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import Search.BookInfo;

import javax.imageio.ImageIO;
import javax.swing.*;

public class GraphicRect extends JPanel {
    private static final String PATH = "";
    private static final Color SHAPE_COLOR = new Color(100, 150, 255);
    private static final Stroke STROKE = new BasicStroke(10f);


    private static final double OVAL_X = 200;
    private static final double OVAL_Y = 200;
    private static final double OVAL_W = 50;
    private BufferedImage img;
    private List<Shape> shapes = new ArrayList<>();

    public GraphicRect(BufferedImage img) {
        this.img = img;
        shapes.add(new Ellipse2D.Double(OVAL_X+100, OVAL_Y+100, OVAL_W, OVAL_W));
        shapes.add(new Rectangle2D.Double(OVAL_X, OVAL_Y, OVAL_W, OVAL_W));
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (img != null) {
            g.drawImage(img, 0, 0, this);
        }
        Graphics2D g2 = (Graphics2D) g;
        g2.setStroke(STROKE);
        g2.setColor(SHAPE_COLOR);
        for (Shape shape : shapes) {
            g2.draw(shape);
            g2.fill(shape);
        }
    }

    @Override
    public Dimension getPreferredSize() {
        if (isPreferredSizeSet() || img == null) {
            return super.getPreferredSize();
        }
        int w = img.getWidth();
        int h = img.getHeight();
        return new Dimension(w, h);
    }

    private static void createAndShowGui(String imgFile, BookInfo bookinfo) {
        BufferedImage img = null;

        try {
            img = ImageIO.read(new File(PATH));
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(-1);
        }

        JFrame frame = new JFrame("Draw On Image");


        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(new BorderLayout());
        frame.getContentPane().add(new GraphicRect(img), BorderLayout.CENTER);

        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout());
               /* FIX FIX FIX */
        JLabel book = new JLabel(bookinfo.getTitle());
        JLabel call = new JLabel(bookinfo.getCallno());
        panel.add(book);
        panel.add(call);
        JButton button = new JButton("Prev");
        panel.add(button);
        JButton button2 = new JButton("Next");
        panel.add(button2);



        frame.add(panel, BorderLayout.SOUTH);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }


    @Override
    public void actionPerformed(ActionEvent evt) {

        Object control = evt.getSource();
        if (control == building) {
            if (evt.getActionCommand().equals("Next")) {
                floorView();
            }

        }
        else if (control == floor) {
            if (evt.getActionCommand().equals("Next")) {
                shelvesView();
            }
            else if (evt.getActionCommand().equals("Prev")) {
                buildingView();
            }
        }

        else if (control == shelves) {
            if (evt.getActionCommand().equals("Next")) {
                shelfView();
            }
            else if (evt.getActionCommand().equals("Prev")) {
                floorView();
            }
        }

        else if (control == shelf) {
            if (evt.getActionCommand().equals("Next")) {
                colView();
            }

            else if (evt.getActionCommand().equals("Prev")) {
                shelvesView();
            }
        }

        else if (control == col) {

            else if (evt.getActionCommand().equals("Prev")) {
                shelfView();
            }
        }


        }

    }

    public void buildingView() {
        List<String> floors = bookinfo.floors;
        String floor = bookinfo.floor;
        shapes = new ArrayList<>();
        double start = ;
        double end = ;
        shapes = new ArrayList<>();
        shapes.add


    }


    public void floorView() {

        lt = bookinfo.lt;
        rb = bookinfo.rb;
        len = bookinfo.length;
        wid = bookinfo.width;

        double rect_x = lt;
        double rect_y;
        double rect_h = len;
        double rect_w = wid;
        shapes = new ArrayList<>();
        shapes.add(new Rectangle2D.Double(rect_x, rect_y, rect_w, rect_h));

    }


    public void shelvesView() {

        shelfno = bookinfo.shelfno;
        shelf = bookinfo.shelf;

        double rect_x = lt;
        double rect_y;
        double rect_h = ;
        double rect_w = ;

        double start = ;
        double gap = ;

        shapes = new ArrayList<>();
        for (int i = 0, pos = start; rect > i; i += 1, pos += gap) {
            shapes.add(new Rectangle2D.Double(rect_x + pos, rect_y, rect_w, rect_h));
        }

    }

    public void shelfView() {

    }

    public void colView() {

    }



    public static void main(String[] args) {

        BookInfo bookinfo = new BookInfo();
        createAndShowGui("/Users/Phoenix/Downloads/stacks_c-1 copy.png", bookinfo);
        PATH = "/Users/Phoenix/Downloads/stacks_c-1 copy.png";
       /*
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGui();
            }
        });
        */
    }
}