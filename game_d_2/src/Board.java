//Francis Walker

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Hashtable;import java.util.Random;

public class Board extends JPanel implements ActionListener {

    //creates all objects used

    private int counter;
    private Map m;
    private Player p;
    private BasicEnemy b;
    private ArrayList<Bullet> bulletArray = new ArrayList<>();
    private ArrayList<BasicEnemy> basicEnemyArray = new ArrayList<>();
    private ArrayList<EliteEnemy> eliteEnemyArray = new ArrayList<>();
    private int width;
    private int height;


    public Board() {
        width =26;
        height =26;

        m = new Map(width,height);
        p = new Player(23,23);

        ArrayList<Integer[]> validStartPos = new ArrayList<>();

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                if (m.getMap(x, y).equals("g")) {
                    Integer [] pos = {x,y};
                    validStartPos.add(pos);

                }

            }

        }

        int numElite = 10 ;
        int numBasic= 8;

        //spawns random enemy
        for (int i =0; i<numElite; i++)
        {
            int randomPos = (int)(Math.random() * (validStartPos.size()-1));
            Integer [] objectToRemove = validStartPos.get(randomPos);
            validStartPos.remove(objectToRemove);
            eliteEnemyArray.add(new EliteEnemy(objectToRemove[0],objectToRemove[1]));
        }

        for (int i =0; i<numBasic; i++)
        {
            int randomPos = (int)(Math.random() * (validStartPos.size()-1));
            Integer [] objectToRemove = validStartPos.get(randomPos);
            validStartPos.remove(objectToRemove);
            basicEnemyArray.add(new BasicEnemy(objectToRemove[0],objectToRemove[1]));
        }











        addKeyListener(new Al());
        setFocusable(true);
        Timer timer = new Timer(100, this);
        timer.start();

    }

    //updates board when ever key is pressed or timer runs out
    public void actionPerformed(ActionEvent e) {
        repaint();
    }

    //paints all objects on map every few split seconds
    public void paint(Graphics g) {
        //iteration();
        super.paint(g);

        //paints map
        for (int y = 0; y <this.width; y++) {
            for (int x = 0; x < this.width; x++) {
                if (m.getMap(x, y).equals("g")) {
                    g.drawImage(m.getGrass(), x * 32, y * 32, null);
                }
                if (m.getMap(x, y).equals("w")) {
                    g.drawImage(m.getWall(), x * 32, y * 32, null);

                }

            }

        }

        //paints player
        g.drawImage(p.getImg(), p.getTileX()*32, p.getTileY()*32, null);
        
        //paints bullets
        for (Bullet bul: bulletArray)  {
            g.drawImage(bul.getImg(), bul.getTileX()*32, bul.getTileY()*32, null);
            
        }

        for (EliteEnemy e: eliteEnemyArray)  {
            g.drawImage(e.getImg(), e.getTileX()*32, e.getTileY()*32, null);

        }

        //paints enemy
        for(BasicEnemy be : basicEnemyArray) {
            g.drawImage(be.getImg(), be.getTileX() * 32, be.getTileY() * 32, null);
        }

        }


    public class Al extends KeyAdapter {
        public void keyPressed(KeyEvent e) {

            int keyCode = e.getKeyCode();


            if (keyCode == KeyEvent.VK_SPACE) {

                bulletArray.add(p.shoot());
//                b.spawnNew(p);
//                System.out.println("y" + b.getTileY() + ",x" + b.getTileX());

            }

            if (keyCode == KeyEvent.VK_UP) {

                p.setFacing('u');
                if (wallCollide(p.getTileX(),p.getTileY(),p.getFacing())) {
                    p.move( 0, -1);
                }

            }

            if (keyCode == KeyEvent.VK_DOWN) {
                p.setFacing('d');
                if (wallCollide(p.getTileX(),p.getTileY(),p.getFacing())) {
                    p.move(0, 1);
                }

            }

            if (keyCode == KeyEvent.VK_LEFT) {

                p.setFacing('l');
                if (wallCollide(p.getTileX(),p.getTileY(),p.getFacing())){
                    p.move(-1, 0);
                }
            }

            if (keyCode == KeyEvent.VK_RIGHT) {
                p.setFacing('r');
                if (wallCollide(p.getTileX(),p.getTileY(),p.getFacing())) {
                    p.move( 1, 0);
                }
            }

        }



//        public void keyReleased(KeyEvent e){
//
//        }
    }

    //return true if next block is grass else false i\if next block is wall
    public boolean wallCollide(int tileX, int tileY, char direction){
        //!m.getMap(p.getTileX() + 1, p.getTileY()).equals("w")
        boolean check = true;
        if (direction == 'r'){
            if (m.getMap(tileX + 1, tileY).equals("w"))
            {
               check = false;
            }

        }else
            if (direction == 'l'){
            if (m.getMap(tileX - 1, tileY).equals("w"))
            {
                check = false;
            }

        }else
            if (direction == 'd'){
            if (m.getMap(tileX , tileY+1).equals("w"))
            {
                check = false;
            }

        }else
            if (direction == 'u') {
                if (m.getMap(tileX, tileY - 1).equals("w"))
                    {
                        check = false;
                    }
            }
        return check;
    }

    public void iteration(){
        Hashtable<String,Bullet> collisionMap = new Hashtable<>();
        Random random = new Random();


        //only enemy on screen shots 1 per second
        if(counter%5==0) {
            //dictates basic enemy actions (50% move, 50% shoot)
            for (BasicEnemy be:basicEnemyArray) {
                if (random.nextBoolean()){
                    int newdir = random.nextInt(4);

                    if ((newdir==0)&&(wallCollide(be.getTileX(),be.getTileY(),'u'))){
                        be.setFacing('u');
                    }
                    else if((newdir==1)&&(wallCollide(be.getTileX(),be.getTileY(),'d'))){
                        be.setFacing('d');
                    }
                    else if((newdir==2)&&(wallCollide(be.getTileX(),be.getTileY(),'l'))){
                        be.setFacing('l');
                    }
                    else if((newdir==3)&&(wallCollide(be.getTileX(),be.getTileY(),'r'))){
                        be.setFacing('r');
                    }
                    if (wallCollide(be.getTileX(),be.getTileY(),be.getFacing())) {
                        be.move();
                    }
                }
                else {
                    bulletArray.add(be.shoot());
                }
            }

            //dictates EliteEnemy action
            for (EliteEnemy e:eliteEnemyArray){
                if(e.ai(p.getTileX(),p.getTileY())){
                    bulletArray.add(e.shoot());
                }
                else{
                    if((wallCollide(e.getTileX(),e.getTileY(),e.getFacing()))){
                        e.move();
                    }

                }
            }


        }
        //moves all bullet and removes them when hit wall
        ArrayList<Bullet> bulletsToRemove = new ArrayList<>();
        for (Bullet bul:bulletArray) {

            if (wallCollide(bul.getTileX(),bul.getTileY(),bul.getFacing())){
                bul.move();
                collisionMap.put(""+bul.getTileX()+","+bul.getTileY(),bul);
            }
            else{
                bulletsToRemove.add(bul);
            }

        }


        ArrayList<BasicEnemy> basicEnemiesToRemove  = new ArrayList<>();

        //if basic enemy is hit remove them
        for(BasicEnemy be:basicEnemyArray){
            String xyMap = ""+be.getTileX()+","+be.getTileY();
            if (collisionMap.containsKey(xyMap)){
                bulletsToRemove.add(collisionMap.get(xyMap));
                basicEnemiesToRemove.add(be);
                System.out.println("hit");

            }
        }

        ArrayList<EliteEnemy> eliteEnemiesToRemove  = new ArrayList<>();

        //if Elite enemy is hit remove them
        for(EliteEnemy e:eliteEnemyArray){
            String xyMap = ""+e.getTileX()+","+e.getTileY();
            if (collisionMap.containsKey(xyMap)){
                bulletsToRemove.add(collisionMap.get(xyMap));
                eliteEnemiesToRemove.add(e);
                System.out.println("hit");

            }
        }


        //check if player is hit
        if(collisionMap.containsKey(""+p.getTileX()+","+p.getTileY())){

            p.hit();

            bulletsToRemove.add(collisionMap.get(""+p.getTileX()+","+p.getTileY()));
        }

        for (Bullet bul: bulletsToRemove) {
            bulletArray.remove(bul);

        }

        for(EliteEnemy e:eliteEnemiesToRemove){
            eliteEnemyArray.remove(e);
        }

        for(BasicEnemy be: basicEnemiesToRemove)
        {
            basicEnemyArray.remove(be);
        }

        counter++;
    }
}

