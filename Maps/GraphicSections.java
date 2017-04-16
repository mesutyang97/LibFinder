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

import javax.imageio.ImageIO;
import javax.swing.*;


public class GraphicSections extends JPanel {


    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.red);
        g.drawRect(100, 10, 30, 40);
    }

    @Override
    public Dimension getPreferredSize() {
            return new Dimension(400,400); // As suggested by camickr
        }


    private static void createAndShowGui() {
        GraphicSections mp = new GraphicSections();
        JPanel jp = new JPanel();
        jp.add(mp);

        JFrame f = new JFrame();
        f.setTitle("");
        f.setSize(400, 400);
        f.setVisible(true);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.add(jp);

    }


    public static void main(String[] args)
    {
        createAndShowGui();
    }


}