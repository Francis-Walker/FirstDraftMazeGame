package Maze_test_code;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.UIManager;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class TestRotateImage {

    public static void main(String[] args) {
        new TestRotateImage();
    }

    public TestRotateImage() {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
                } catch (Exception ex) {
                }

                JFrame frame = new JFrame("Test");
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.add(new TestPane());
                frame.pack();
                frame.setLocationRelativeTo(null);
                frame.setVisible(true);
            }
        });
    }

    public class TestPane extends JPanel {

        private JSlider slider;
        private BufferedImage image;

        public TestPane() {
            setLayout(new BorderLayout());

            try {
                image = ImageIO.read(new File("Resources/CharactarIcons/Player.png"));
            } catch (IOException ex) {
                ex.printStackTrace();
            }

            slider = new JSlider();
            slider.setMinimum(0);
            slider.setMaximum(360);
            slider.setMinorTickSpacing(5);
            slider.setMajorTickSpacing(10);
            slider.setValue(0);
            add(slider, BorderLayout.SOUTH);
            slider.addChangeListener(new ChangeListener() {
                @Override
                public void stateChanged(ChangeEvent e) {
                    repaint();
                }
            });
        }

        @Override
        public Dimension getPreferredSize() {
            return image == null ? new Dimension(200, 200) : new Dimension(image.getWidth(), image.getHeight());
        }

        public double getAngle() {

            return Math.toRadians(slider.getValue());

        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);

            Graphics2D g2d = (Graphics2D) g.create();

            g2d.setColor(Color.RED);
            g2d.drawLine(getWidth() / 2, 0, getWidth() / 2, getHeight());
            g2d.drawLine(0, getHeight() / 2, getWidth(), getHeight() / 2);

            g2d.setColor(Color.BLACK);
            int x = (getWidth() - image.getWidth()) / 2;
            int y = (getHeight() - image.getHeight()) / 2;
            AffineTransform at = new AffineTransform();
            at.setToRotation(getAngle(), x + (image.getWidth() / 2), y + (image.getHeight() / 2));
            at.translate(x, y);
            g2d.setTransform(at);
            g2d.drawImage(image, 0, 0, this);
            g2d.dispose();

        }
    }
}
