import java.util.ArrayList;

/**
 * The minimum any gui must have to be able to be implemented
 * @author TheTechTeen
 */
public interface GUI {
    void displayBoard (ArrayList<BoardObject> boardObjects, int boardWidth, int boardHeight);
}
