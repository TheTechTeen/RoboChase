import javax.swing.WindowConstants;

/**
 * The main class. Starts the GUI.
 *
 * @author TheTechTeen
 */
public class RoboChase 
{
    public static void main(String[] args) 
    {
        RoboGUI robotFrame = new RoboGUI(); // create ButtonFrame
        robotFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        robotFrame.setSize(350, 400); // set frame size
        robotFrame.setVisible(true); // display frame
        robotFrame.setLocationRelativeTo(null);
    }
}
