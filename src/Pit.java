import edu.macalester.graphics.CanvasWindow;

import java.awt.Color;

/**
 * Represents the mancala pits, extends the MancalaStore class
 */
public class Pit extends MancalaStore {

    public static final int WIDTH = 100;
    public static final int HEIGHT = 100;

    /**
     * Pit constructor, calls MancalaStore constructor
     * @param x
     * @param y
     * @param stones
     * @param color
     * @param canvas
     */
    public Pit(int x, int y, int stones, Color color, CanvasWindow canvas) {
        super(x, y, WIDTH, HEIGHT, stones, color, canvas);
    }

    /**
     * Takes all stones from pit, sets stone count to zero
     */
    public void removeAllStones() {
        setStoneCount(0);
    }
}
