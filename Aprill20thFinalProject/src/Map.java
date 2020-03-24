//Francis Walker
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.util.Scanner;

public class Map {

    private Scanner m;
    private String[] Map = new String[14];
    private Image grass, wall;

    public Map(){

        //stores icons for painting
        ImageIcon img = new ImageIcon("Resources/Maps/Tiles/Grass.png");
        grass = img.getImage();
        img = new ImageIcon("Resources/Maps/Tiles/Wall.png");
        wall = img.getImage();




        openFile();
        readFile();
        closeFile();
    }


    public Image getGrass()
    {
        return grass;
    }

    public Image getWall()
    {
        return wall;
    }

    //helper function for painting map tile y tile
    public String getMap(int x, int y){
        String index = Map[y].substring(x,x+1);
        //System.out.println(index);
        return (index);

    }


    //open text document where map layout is stored
    public void openFile(){

        try {
            m = new Scanner(new File("Resources/Maps/sampleMaps/testMap"));
        }
        catch (Exception e){
            System.out.println("error loading map:");
        }
    }

    //reads file
    public void readFile(){
        while(m.hasNext())
        {
            for(int i = 0; i <14; i++)
            {
                Map[i] = m.next();
                //System.out.println(Map[i]);
            }
        }
    }
    public void closeFile(){
    m.close();
    }
}
