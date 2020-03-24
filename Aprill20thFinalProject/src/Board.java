//Francis Walker

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Board extends JPanel implements ActionListener {

    //creates all objects used

    private Timer timer;
    private Map m;
    private Player p;
    private Bullet b;
    private Bullet beb;
    private BasicEnemy be;

    public Board() {

        m = new Map();
        p = new Player();
        b = new Bullet();

        be = new BasicEnemy();
        //basic enemy bullet
        beb = new Bullet();

        addKeyListener(new Al());
        setFocusable(true);
        timer = new Timer(25, this);
        timer.start();

    }

    //updates board when ever key is pressed
    public void actionPerformed(ActionEvent e) {
        repaint();
    }

    //paints all objects on map every few slipt seconds
    public void paint(Graphics g) {
        super.paint(g);

        //paints map
        for (int y = 0; y < 14; y++) {
            for (int x = 0; x < 14; x++) {
                if (m.getMap(x, y).equals("g")) {
                    g.drawImage(m.getGrass(), x * 32, y * 32, null);
                }
                if (m.getMap(x, y).equals("w")) {
                    g.drawImage(m.getWall(), x * 32, y * 32, null);

                }

            }

        }

        //paints player
        g.drawImage(p.getPlayer(), p.getX(), p.getY(), null);

        //paints enemy
        if (be.isAlive()) {
            // System.out.println("is alive");
            g.drawImage(be.getBasic_enemy(), be.getX(), be.getY(), null);
            System.out.println("s,"+beb.getSpawned()+",x "+beb.getTileX()+",y "+beb.getTileY());
            //moves enemy bullet
            if(beb.getSpawned()){
                beb.move();
                g.drawImage(beb.getBullet(), beb.getX(), beb.getY(), null);
                if(beb.getTileY()>13)
                {
                    beb.spawnNew(be);
                    //System.out.println("oob");
                }
            }
        }


//
//            if (beb.getSpawned()) {
//
//                if ((beb.getDir() == 'd') & (!m.getMap(beb.getTileX(), beb.getTileY() + 1).equals("w"))) {
//                    //b.move(32, 0, 1, 0);
//                    beb.move();
//                } else if ((beb.getDir() == 'u') & (!m.getMap(beb.getTileX(), beb.getTileY() - 1).equals("w"))) {
//                    //b.move(32, 0, 1, 0);
//                    b.move();
//                } else if ((beb.getDir() == 'r') & (!m.getMap(beb.getTileX() + 1, beb.getTileY()).equals("w"))) {
//                    //b.move(32, 0, 1, 0);
//                    b.move();
//                } else if ((beb.getDir() == 'l') & (!m.getMap(b.getTileX() - 1, beb.getTileY()).equals("w"))) {
//                    //b.move(32, 0, 1, 0);
//                    beb.move();
//                }
//            } else {
//                System.out.println("bullet");
//                beb.spawnNew(be);
//
//            }
//        }

//      if (beb.getSpawned()) {
//
//            if ((beb.getDir() == 'd') & (!m.getMap(beb.getTileX(), beb.getTileY() + 1).equals("w"))) {
//                //b.move(32, 0, 1, 0);
//                beb.move();
//            } else if ((beb.getDir() == 'u') & (!m.getMap(beb.getTileX(), beb.getTileY() - 1).equals("w"))) {
//                //b.move(32, 0, 1, 0);
//                b.move();
//            } else if ((beb.getDir() == 'r') & (!m.getMap(beb.getTileX() + 1, beb.getTileY()).equals("w"))) {
//                //b.move(32, 0, 1, 0);
//                b.move();
//            } else if ((beb.getDir() == 'l') & (!m.getMap(b.getTileX() - 1, beb.getTileY()).equals("w"))) {
//                //b.move(32, 0, 1, 0);
//                beb.move();
//            } else {
//                beb.setSpawned(false);
//
//            }
//
//        }

        //stops bullet from flying off screen
        if (b.getSpawned()) {

            if ((b.getDir() == 'd') & (!m.getMap(b.getTileX(), b.getTileY() + 1).equals("w"))) {
                //b.move(32, 0, 1, 0);
                b.move();
            } else if ((b.getDir() == 'u') & (!m.getMap(b.getTileX(), b.getTileY() - 1).equals("w"))) {
                //b.move(32, 0, 1, 0);
                b.move();
            } else if ((b.getDir() == 'r') & (!m.getMap(b.getTileX() + 1, b.getTileY()).equals("w"))) {
                //b.move(32, 0, 1, 0);
                b.move();
            } else if ((b.getDir() == 'l') & (!m.getMap(b.getTileX() - 1, b.getTileY()).equals("w"))) {
                //b.move(32, 0, 1, 0);
                b.move();
            } else {
                b.setSpawned(false);

            }

        }


        //check if bullet hits player
//        if ((p.getTileX() == b.getTileX()) & (p.getTileY() == b.getTileY())) {
//            System.out.println("hit!");
//            JOptionPane.showMessageDialog(null, "You are hit!");
//
//        }

        //check if enemy bullet hit player
        if ((p.getTileX() == beb.getTileX()) & (p.getTileY() == beb.getTileY())) {
            System.out.println("hit!");
            JOptionPane.showMessageDialog(null, "You are hit!");
            System.exit(0);

        }

        //checks if player hit enemy
        if (be.isAlive()) {
            if ((be.getTileX() == b.getTileX()) & (be.getTileY() == b.getTileY())) {
                System.out.println("hit!");
                JOptionPane.showMessageDialog(null, "enemy hit");
                be.setAlive(false);
            }
        }

        //paints player bullet
        g.drawImage(b.getBullet(), b.getX(), b.getY(), null);

//        if(beb.getSpawned()) {
//
//            if ((beb.getDir() == 'd') & (!m.getMap(beb.getTileX(), beb.getTileY() + 1).equals("w"))) {
//                    //b.move(32, 0, 1, 0);
//                    beb.move();
//                } else if ((beb.getDir() == 'u') & (!m.getMap(beb.getTileX(), beb.getTileY() - 1).equals("w"))) {
//                    //b.move(32, 0, 1, 0);
//                    beb.move();
//                } else if ((beb.getDir() == 'r') & (!m.getMap(beb.getTileX() + 1, beb.getTileY()).equals("w"))) {
//                    //b.move(32, 0, 1, 0);
//                    beb.move();
//                } else if ((beb.getDir() == 'l') & (!m.getMap(b.getTileX() - 1, beb.getTileY()).equals("w"))) {
//                    //b.move(32, 0, 1, 0);
//                    beb.move();
//                }
//            } else {
//                System.out.println("bullet");
//            beb.setSpawned(false);
//
//            }
            //g.drawImage(beb.getBullet(), beb.getX(), beb.getY(), null);
        }




    //action listener = Al
    //logic for what happens when player presses key
    public class Al extends KeyAdapter {
        public void keyPressed(KeyEvent e) {

            int keyCode = e.getKeyCode();


            if (keyCode == KeyEvent.VK_SPACE) {
                System.out.println("space");
                b.spawnNew(p);
                System.out.println("y" + b.getTileY() + ",x" + b.getTileX());

            }

            if (keyCode == KeyEvent.VK_UP) {
                if (!m.getMap(p.getTileX(), p.getTileY() - 1).equals("w")) {


                    p.move(0, -32, 0, -1);
                    p.setFacing('u');
                    System.out.println("u");
                    System.out.println("y" + p.getTileY() + ",x" + p.getTileX() + ",f" + p.getFacing());
                    System.out.println("__");
                }

            }
            if (keyCode == KeyEvent.VK_DOWN) {

                //System.out.println("down");
                if (!(m.getMap(p.getTileX(), p.getTileY() + 1).equals("w"))) {

                    p.move(0, 32, 0, 1);
                    p.setFacing('d');
                    System.out.println("d");
                    System.out.println("y" + p.getTileY() + ",x" + p.getTileX() + ",f" + p.getFacing());
                    System.out.println("__");

                }

            }
            if (keyCode == KeyEvent.VK_LEFT) {


                if (!m.getMap(p.getTileX() - 1, p.getTileY()).equals("w")) {
                    p.move(-32, 0, -1, 0);
                    p.setFacing('l');
                    System.out.println("l");
                    System.out.println("y" + p.getTileY() + ",x" + p.getTileX() + ",f" + p.getFacing());
                    System.out.println("__");
                }
            }
            if (keyCode == KeyEvent.VK_RIGHT) {


                if (!m.getMap(p.getTileX() + 1, p.getTileY()).equals("w")) {
                    p.move(32, 0, 1, 0);
                    p.setFacing('r');
                    System.out.println("R");
                    System.out.println("y" + p.getTileY() + ",x" + p.getTileX() + ",f" + p.getFacing());
                    System.out.println("__");
                }
            }

        }

//        public void keyReleased(KeyEvent e){
//
//        }
    }

}

