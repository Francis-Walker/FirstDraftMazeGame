//Francis Walker

import javax.swing.*;
import java.awt.*;

public class Bullet {

    private int x, y, tileX, tileY;
    private boolean spawned = true;
    private int[] moveDir = new int[4];
    private char dir;


    private Image bullet;


    public Bullet() {

        ImageIcon img = new ImageIcon("Resources/CharactarIcons/Bullet.png");
        bullet = img.getImage();
        newDir('d');


        x = 1 * 32;
        y = 1 * 32;

        tileY = 1;
        tileX = 1;
    }

    //sets bullet to off screen postion when no used
    public void setSpawned(Boolean newValue) {
        spawned = newValue;
        if (!spawned) {
            x = -32;
            y = -32;
            tileX =-1;
            tileY = -1;
        }
    }

    //changes bullet direct and movement direct based on where the object that shoot it was facing
    public void newDir(char d) {


        switch (d) {
            case 'u':
                moveDir[0] = 0;
                moveDir[1] = -32;
                moveDir[2] = 0;
                moveDir[3] = -1;

                break;
            case 'd':
                moveDir[0] = 0;
                moveDir[1] = 32;
                moveDir[2] = 0;
                moveDir[3] = 1;

                break;
            case 'l':
                moveDir[0] = -32;
                moveDir[1] = 0;
                moveDir[2] = -1;
                moveDir[3] = 0;

                break;

            case 'r':
                moveDir[0] = 32;
                moveDir[1] = 0;
                moveDir[2] = 1;
                moveDir[3] = 0;

                break;


        }
        }

        //sets location to object that shot it and moves it in direction object was facing
        public void spawnNew(Player player){
        dir = player.getFacing();
        x = player.getX();
        y = player.getY();
        tileX = player.getTileX();
        tileY = player.getTileY();
        newDir(player.getFacing());
        setSpawned(true);


        }
        //same as above but for different enemies
    public void spawnNew(BasicEnemy basicEnemy){
        dir = basicEnemy.getFacing();
        x = basicEnemy.getX();
        y = basicEnemy.getY();
        tileX = basicEnemy.getTileX();
        tileY = basicEnemy.getTileY();
        newDir(basicEnemy.getFacing());
        setSpawned(true);


    }


        public Boolean getSpawned () {
            return spawned;
        }

        public int getX () {
            return x;
        }

        public int getY () {
            return y;
        }

        public int getTileX () {
            return tileX;
        }

        public int getTileY () {
            return tileY;
        }

        //return img icon of ulelt
        public Image getBullet () {
            return bullet;
        }
        public char getDir(){
        return dir;
        }

        //changes bullet location based on direcction of travel
//        public void move ( int dx, int dy, int tx, int ty)
//        {
//            x += dx;
//            y += dy;
//
//            tileX += tx;
//            tileY += ty;
//
//        }


    //changes bullet location based on direcction of travel
        public void move(){
        x += moveDir[0];
        y +=moveDir[1];
        tileX +=moveDir[2];
        tileY +=moveDir[3];
        }

    }
