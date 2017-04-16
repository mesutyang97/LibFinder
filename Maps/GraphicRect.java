package Maps;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.Stroke;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.io.File;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

import javax.imageio.ImageIO;

public class GraphicRect extends JPanel {
    private static final Color SHAPE_COLOR = new Color(100, 150, 255);
    private static final Stroke STROKE = new BasicStroke(10f);

    static String stacksimgPath = "images/stacks_c-1crop.png";
    static String whiteimgPath = "images/White_square.png";

    static private BufferedImage img;
    static private List<Shape> shapes = new ArrayList<>();

    public GraphicRect(BufferedImage img) {
        this.img = img;

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


    public static void createAndShowGui(BookInfo bookinfo) {
        BufferedImage whiteimg = null;
        BufferedImage stacksimg = null;

        try {
            whiteimg = ImageIO.read(new File(whiteimgPath));
            stacksimg = ImageIO.read(new File(stacksimgPath));
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(-1);
        }


        JFrame buildingf = new JFrame("Building");



        //JFrame floorf = new JFrame("Floor");



        buildingf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        buildingf.getContentPane().setLayout(new BorderLayout());
        System.out.println("sfs");
        buildingf.getContentPane().add(new GraphicRect(whiteimg), BorderLayout.CENTER);
        System.out.println("fsf");

        //floorf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //floorf.getContentPane().setLayout(new BorderLayout());
        //floorf.getContentPane().add(new GraphicRect(stacksimg), BorderLayout.CENTER);


        JPanel buildingp = new JPanel();
        buildingp.setLayout(new FlowLayout());


        JPanel floorp = new JPanel();
        floorp.setLayout(new FlowLayout());

        buildingf.add(buildingp, BorderLayout.SOUTH);
        buildingf.pack();
        buildingf.setLocationRelativeTo(null);
        buildingf.setVisible(true);


        //floorf.add(floorp, BorderLayout.SOUTH);
        //floorf.pack();
        //floorf.setLocationRelativeTo(null);
        //floorf.setVisible(false);

        buildingView(bookinfo);

        /*

        JPanel shelvesp = new JPanel();
        shelvesp.setLayout(new FlowLayout());


        JPanel shelfp = new JPanel();
        shelfp.setLayout(new FlowLayout());

        JPanel colp = new JPanel();
        colp.setLayout(new FlowLayout());

        */


        JLabel book = new JLabel(bookinfo.getTitle());
        JLabel call = new JLabel(bookinfo.getCallno());
        buildingp.add(book);
        buildingp.add(call);


        floorp.add(book);
        floorp.add(call);



        JButton buildingNext = new JButton("Next");
        buildingp.add(buildingNext);
        buildingNext.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                buildingp.setVisible(false);
                floorp.setVisible(true);
                floorView(bookinfo);

            }
        });


        JButton floorPrev = new JButton("Prev");
        floorp.add(floorPrev);
        floorPrev.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                floorp.setVisible(false);
                //shelvesp.setVisible(true);
                shelvesView();

            }
        });

        JButton floorNext = new JButton("Next");
        floorp.add(floorNext);
        floorNext.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                floorp.setVisible(false);
                //selvesp.setVisible(true);
                shelvesView();

            }
        });



    }




    public static void buildingView(BookInfo bookinfo) {
        List<String> floors = bookinfo.getFloors();
        String floor = bookinfo.getFloors().get(bookinfo.getParams()[0]);
        shapes = new ArrayList<>();
        double rect_x = 50;
        double rect_y = 50;
        double rect_h = 200;
        double rect_w = 300;
        shapes = new ArrayList<>();
        shapes.add(new Rectangle2D.Double(rect_x, rect_y, rect_w, rect_h));


    }


    public static void floorView(BookInfo bookinfo) {

        /*
        lt = bookinfo.lt;
        rb = bookinfo.rb;
        len = bookinfo.length;
        wid = bookinfo.width;
        */

        double rect_x = 50;
        double rect_y = 50;
        double rect_h = 200;
        double rect_w = 300;
        shapes = new ArrayList<>();
        shapes.add(new Rectangle2D.Double(rect_x, rect_y, rect_w, rect_h));

    }


    public static void shelvesView() {

        /*
        shelfno = bookinfo.shelfno;
        shelf = bookinfo.shelf;
        */

        double rect_x = 50;
        double rect_y = 50;
        double rect_h = 200;
        double rect_w = 300;

        double start = 100;
        double gap = 50;

        shapes = new ArrayList<>();
        //rect = shelfno;
        double rect = 3;
        for (double i = 0, pos = start; rect > i; i += 1, pos += gap) {
            shapes.add(new Rectangle2D.Double(rect_x + pos, rect_y, rect_w, rect_h));
        }

    }

    public static void shelfView() {
        double rect_x = 50;
        double rect_y = 50;
        double rect_h = 200;
        double rect_w = 300;
        shapes = new ArrayList<>();
        shapes.add(new Rectangle2D.Double(rect_x, rect_y, rect_w, rect_h));

    }

    public static void colView() {
        double rect_x = 50;
        double rect_y = 50;
        double rect_h = 200;
        double rect_w = 300;
        shapes = new ArrayList<>();
        shapes.add(new Rectangle2D.Double(rect_x, rect_y, rect_w, rect_h));

    }



    public static void main(String[] args) {

        BookInfo bookinfo = new BookInfo("Main Stacks","callno", "book", new ArrayList<String>(), 0, 0, 0, 0, 0, 0, 0, 0, 0);
        //stacksimgPath = "/Users/Phoenix/Downloads/stacks_c-1crop.png";
        //whiteimgPath = "/Users/Phoenix/Downloads/White_square.png";
        createAndShowGui(bookinfo);

       /*
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGui();
            }
        });
        */
    }

}