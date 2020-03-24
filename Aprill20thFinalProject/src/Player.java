//Francis Walker

import javax.swing.*;
import java.awt.*;

public class Player {

    private int tileX,tileY;
   private int x,y;
    private Image player;
    private char facing = 'd';



    public Player(){

        ImageIcon img = new ImageIcon("Resources/CharactarIcons/Player.png");
        player = img.getImage();


        x = 10*32;
        y = 10*32;

        tileY =10;
        tileX =10;
    }

    public int getX(){
        return x;
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

    public Image getPlayer(){
        return player;
    }


    public void move(int dx , int dy, int tx , int ty)
    {
        x +=dx;
        y +=dy;

        tileX +=tx;
        tileY +=ty;

    }
}
