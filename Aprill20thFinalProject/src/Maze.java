//Francis Walker
import javax.swing.*;

public class Maze {
    //provides basic frame map is projected into
    public static void main(String[] args)
    {
        new Maze();
    }
    public Maze(){
        //creates basic Frame to allow other functionality
        JFrame f = new JFrame();
        f.setTitle("BETA TEST");
        //adds a board to the frame
        f.add(new Board());

        //sets size and starting location,
        f.setSize(500,500);
        f.setLocationRelativeTo(null);

        //makes frame visible
        f.setVisible(true);
        //stops program on close of frame
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }
}
