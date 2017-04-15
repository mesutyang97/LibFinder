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

import javax.imageio.ImageIO;
import javax.swing.*;

public class GraphicRect extends JPanel {
    private static final String PATH = "/Users/Phoenix/Downloads/stacks_c-1 copy.png";
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
        JLabel book = new JLabel("The scientist:");
        JLabel call = new JLabel("Q 143 .C23 1991");
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

    public static void main(String[] args) {
        createAndShowGui();
       /*
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGui();
            }
        });
        */
    }
}