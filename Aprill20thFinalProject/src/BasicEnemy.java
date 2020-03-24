//Francis Walker

import javax.swing.*;
import java.awt.*;
//test class for future enemy implementations
public class BasicEnemy {





        private int tileX,tileY;
        private int x,y;
        private Image  basic_enemy;
        private char facing = 'd';
        private boolean alive = true;


        public BasicEnemy(){

            ImageIcon img = new ImageIcon("Resources/CharactarIcons/Basic_Enemy.png");
            basic_enemy = img.getImage();


            x = 1*32;
            y = 1*32;

            tileY =1;
            tileX =1;
        }

        public int getX(){
            return x;
        }

    public boolean isAlive() {
        return alive;
    }

    public void setAlive(boolean alive) {
        this.alive = alive;
    }

    public void setFacing(char newFacing){
            facing = newFacing;
        }
        public char getFacing(){
            return facing;
        }

        public  int getY(){
            return y;
        }

        public int getTileX(){
            return tileX;
        }

        public  int getTileY(){
            return tileY;
        }

        public Image getBasic_enemy(){
            return  basic_enemy;
        }

        public void move(int dx , int dy, int tx , int ty)
        {
            x +=dx;
            y +=dy;

            tileX +=tx;
            tileY +=ty;

        }
    }


