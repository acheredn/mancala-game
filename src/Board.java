import edu.macalester.graphics.CanvasWindow;
import edu.macalester.graphics.GraphicsGroup;
import edu.macalester.graphics.GraphicsText;
import edu.macalester.graphics.Rectangle;
import edu.macalester.graphics.Ellipse;
import edu.macalester.graphics.FontStyle;

import java.util.HashMap;
import java.util.Map;

import java.awt.Color;

/**
 * Represents and manages board graphics
 */
public class Board extends GraphicsGroup {

    private int lowerY = 500;
    private int higherY = 100;
    private int stones = 4;
    private static final Color BOARD_COLOR = new Color(245, 219, 147);
    private static final Color PIT_COLOR = new Color(255, 255, 0);
    private CanvasWindow canvas;
    private GraphicsText playerTurn;
    private PitManager pitManager;
    private Map<Ellipse, MancalaStore> ellipseMap;

    /**
     * Manages graphics on the screen, eventually setting up the game board.
     * 
     * @param canvas
     * @param pitManager
     */
    public Board(CanvasWindow canvas, PitManager pitManager) {
        this.canvas = canvas;
        this.pitManager = pitManager;
        ellipseMap = new HashMap<>();
        playerTurn = new GraphicsText();
        playerTurn.setCenter(230, 35);
        playerTurn.setFontSize(30);
        playerTurn.setFontStyle(FontStyle.BOLD);
        playerTurn.setFillColor(Color.BLACK);
        this.draw();
    }

    /**
     * Draws graphical objects on the screen: background, two mancala stores, and twelve pits (six from
     * each side).
     */
    private void draw() {

        rectangleDrawer(0, 0, 1000, 600, BOARD_COLOR);

        storeDrawer(900, 300, 100, 500, true);
        storeDrawer(100, 300, 100, 500, false);

        pitDrawer(225, higherY, stones, false);
        pitDrawer(335, higherY, stones, false);
        pitDrawer(445, higherY, stones, false);
        pitDrawer(555, higherY, stones, false);
        pitDrawer(665, higherY, stones, false);
        pitDrawer(775, higherY, stones, false);

        pitDrawer(775, lowerY, stones, true);
        pitDrawer(665, lowerY, stones, true);
        pitDrawer(555, lowerY, stones, true);
        pitDrawer(445, lowerY, stones, true);
        pitDrawer(335, lowerY, stones, true);
        pitDrawer(225, lowerY, stones, true);
    }

    /**
     * Draws Mancala Store and updates the score in it based on whose turn is it.
     * 
     * @param x
     * @param y
     * @param width
     * @param height
     * @param a      Player 1 turn if true, Player 2 turn if false.
     */
    private void storeDrawer(int x, int y, int width, int height, boolean a) {
        MancalaStore mancalaStore = new MancalaStore(x, y, width, height, 0, PIT_COLOR, canvas);
        this.add(mancalaStore.getGroup());
        if (a == true) {
            pitManager.addAPit(mancalaStore);
        } else {
            pitManager.addBPit(mancalaStore);
        }
        ellipseMap.put(mancalaStore.getEllipse(), mancalaStore);
    }

    /**
     * Draws Mancala Pit and updates the score in it based on whose turn is it. Assigns each pit to each
     * ellipse on the board to allow for mouse handling events.
     * 
     * @param x
     * @param y
     * @param stones Number of stones in the pit.
     * @param a      Player 1 turn if true, Player 2 turn if false.
     */
    private void pitDrawer(int x, int y, int stones, boolean a) {
        Pit pit = new Pit(x, y, stones, PIT_COLOR, canvas);
        this.add(pit.getGroup());
        if (a == true) {
            pitManager.addAPit(pit);
        } else {
            pitManager.addBPit(pit);
        }
        ellipseMap.put(pit.getEllipse(), pit);
    }

    /**
     * @return Map of pits assigned to their corresponding ellipses on the board.
     */
    public Map<Ellipse, MancalaStore> getMap() {
        return ellipseMap;
    }

    /**
     * Creates Background in a form of a Rectangle object with its specific color.
     * 
     * @param x
     * @param y
     * @param width
     * @param height
     * @param color
     */
    public void rectangleDrawer(int x, int y, int width, int height, Color color) {
        Rectangle rectangle = new Rectangle(x, y, width, height);
        rectangle.setFillColor(color);
        this.add(rectangle);
    }

    /**
     * Textual representation of whose turn is it based on the helper method in Pit Manager class.
     */
    public void updateTurn() {
        playerTurn.setText("Player 1 turn -- Take from Bottom");
        if (pitManager.isPlayerATurn()) {
            playerTurn.setText("Player 1 turn -- Take from Bottom");
            canvas.add(playerTurn);
        } else {
            playerTurn.setText("Player 2 turn -- Take from Top");
            canvas.add(playerTurn);
        }
    }
}
