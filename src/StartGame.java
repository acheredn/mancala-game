import java.awt.Color;
import java.util.Map;
import edu.macalester.graphics.*;

/**
 * Sets up game, contains main method to play game
 */
public class StartGame {
    private static final int CANVAS_WIDTH = 1000;
    private static final int CANVAS_HEIGHT = 600;
    private static final Color BOARD_COLOR = new Color(245, 219, 147);

    private CanvasWindow canvas;
    private Board board;
    private PitManager pitManager;
    private Map<Ellipse, MancalaStore> ellipseMap;
    private GraphicsText gameName;

    private Image startScreen = new Image(0, 0, "startScreen.png");
    private Image instructionsScreen = new Image(0, 0, "instructionsScreen.png");

    private boolean calledGameOnce;
    private boolean calledInstructionsOnce;

    /**
     * Sets up the game by linking all the other classes together.
     */
    public StartGame() {
        canvas = new CanvasWindow("Mancala", CANVAS_WIDTH, CANVAS_HEIGHT);
        pitManager = new PitManager();
        board = new Board(canvas, pitManager);
        ellipseMap = board.getMap();
        canvas.add(startScreen);
        calledGameOnce = false;
        calledInstructionsOnce = false;
    }

    /**
     * Creates text "MANCALA" in the middle of the board during the game.
     */
    public void setUpTitle() {
        gameName = new GraphicsText("MANCALA");
        gameName.setCenter(230, 320);
        gameName.setFontSize(120);
        gameName.setFontStyle(FontStyle.BOLD);
        gameName.setFillColor(Color.BLUE);
        canvas.add(gameName);
    }

    public static void main(String[] args) {
        StartGame game = new StartGame();
        game.run();
    }

    /**
     * Sets up mouse listeners for the different interfaces of the game: the welcoming and instructions
     * screens, as well as events when pressing on the pits.
     */
    public void run() {
        canvas.onKeyUp(event -> {
            if (!calledInstructionsOnce) {
                canvas.add(instructionsScreen);
                calledInstructionsOnce = true;
            }
        });

        canvas.onClick(event -> {
            if (!calledGameOnce) {
                canvas.remove(startScreen);
                canvas.add(board);
                setUpTitle();
                board.updateTurn();
                calledGameOnce = true;
            } else {
                GraphicsObject element = canvas.getElementAt(event.getPosition());
                if (element != null && element instanceof Ellipse) {
                    pitManager.select((Pit) ellipseMap.get(element));
                    board.updateTurn();
                    if (pitManager.gameOver()) {
                        endScreen();
                    }
                }
            }
        });

        canvas.onMouseDown(event -> {
            GraphicsObject element = canvas.getElementAt(event.getPosition());
            if (element != null && element instanceof Ellipse) {
                Ellipse currentEllipse = (Ellipse) element;
                currentEllipse.setFillColor(Color.ORANGE);

            }

        });

        canvas.onMouseUp(event -> {
            GraphicsObject element = canvas.getElementAt(event.getPosition());
            if (element != null && element instanceof Ellipse) {
                Ellipse currentEllipse = (Ellipse) element;
                currentEllipse.setFillColor(Color.YELLOW);

            }

        });
    }

    /**
     * Creates Background in a form of a Rectangle object with its specific color. Used for the final
     * screen where the score is being displayed.
     * 
     * @param x
     * @param y
     * @param width
     * @param height
     * @param color
     */
    public void finalRectangleDrawer(int x, int y, int width, int height, Color color) {
        Rectangle rectangle = new Rectangle(x, y, width, height);
        rectangle.setFillColor(color);
        canvas.add(rectangle);
    }

    /**
     * Displays the final score after the game is over.
     * 
     * @param string
     * @param x
     * @param y
     * @param size
     */
    public void finalTextDrawer(String string, double x, double y, int size) {
        GraphicsText text = new GraphicsText(string, x, y);
        text.setFont(FontStyle.BOLD, size);
        text.setFillColor(Color.RED);
        canvas.add(text);
    }

    /**
     * Clears the canvas and sets up the final screen by calling drawing background and retrieving the
     * winner and the final scores.
     */
    public void endScreen() {
        canvas.removeAll();
        finalRectangleDrawer(0, 0, 1000, 600, BOARD_COLOR);
        if (pitManager.isTie()) {
            finalTextDrawer("It is a tie!", 375, 300, 50);
        } else if (pitManager.playerAWinner()) {
            finalTextDrawer("Player 1 Won!", 330, 300, 50);
        } else {
            finalTextDrawer("Player 2 Won!", 330, 300, 50);
        }
        finalTextDrawer("Player 1 score is" + " " + pitManager.getAscore(), 315, 375, 40);
        finalTextDrawer("Player 2 score is" + " " + pitManager.getBscore(), 315, 425, 40);
    }

    @Override
    public String toString() {
        return "StartGame [pitManager =" + board + ", canvas = " + canvas + ", pitManager =" + pitManager
            + "]";
    }
}

