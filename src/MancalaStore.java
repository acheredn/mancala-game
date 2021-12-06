import edu.macalester.graphics.GraphicsGroup;
import edu.macalester.graphics.GraphicsText;
import edu.macalester.graphics.CanvasWindow;
import edu.macalester.graphics.Ellipse;
import edu.macalester.graphics.FontStyle;

import java.awt.Color;

/**
 * Represents the mancala stores 
 */
public class MancalaStore {
    private GraphicsGroup group;
    private Ellipse storeGraphic;
    private GraphicsText stoneText;
    private int stoneCount;
    private CanvasWindow canvas;
    private Color color;

    /**
     * Constructor
     * @param x
     * @param y
     * @param width
     * @param height
     * @param stones
     * @param color
     * @param canvas
     */
    public MancalaStore(int x, int y, int width, int height, int stones, Color color, CanvasWindow canvas) {
        this.canvas = canvas;
        this.color = color;

        storeGraphic = new Ellipse(0, 0, width, height);
        storeGraphic.setCenter(x, y);
        storeGraphic.setFillColor(color);

        stoneCount = stones;
        stoneText = new GraphicsText(String.valueOf(stones), 0, 0);
        stoneText.setCenter(x, y);
        stoneText.setFontStyle(FontStyle.BOLD);

        group = new GraphicsGroup();
        group.add(storeGraphic);
        group.add(stoneText);
    }

    /**
     * Returns graphics group with ellipse and stone count graphics text
     */
    public  GraphicsGroup getGroup() {
        return group;
    }

    /**
     * 
     * @return ellipse representing the mancala store on the board
     */
    public Ellipse getEllipse() {
        return storeGraphic;
    }

    /**
     * 
     * @return number of stones in store
     */
    public int getStoneCount() {
        return stoneCount;
    }

    /**
     * Sets number of stones in store
     * @param stones
     */
    public void setStoneCount(int stones) {
        stoneCount = stones;
        stoneText.setText(String.valueOf(stones));
    }

    /**
     * Adds 1 stone to the store, increases stoneCount by one
     */
    public void addStone() {
        stoneCount += 1;
        stoneText.setText(String.valueOf(stoneCount));
        flashColor();
    }   

    /**
     * Makes the store flash orange 
     */
    private void flashColor(){
        setFillColor(Color.ORANGE);
        canvas.draw();
        canvas.pause(500);
        setFillColor(color);
    }

    /**
     * Sets fill color of store
     * @param color
     */
    public void setFillColor(Color color) {
        storeGraphic.setFillColor(color);
    }

    /**
     * Checks whether there are stones in store
     * @return true if empty
     */
    public boolean isEmpty() {
        return (stoneCount == 0);
    }
}
