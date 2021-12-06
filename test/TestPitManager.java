import org.junit.jupiter.api.Test;

import java.awt.Color;

public class TestPitManager {

    private PitManager pitManager = new PitManager();
    private static final Color PIT_COLOR = new Color(255, 255, 0);
    // MancalaStore a0 = new MancalaStore(0,0,0,0,0, PIT_COLOR);
    // Pit a1 = new Pit(0,0,4, PIT_COLOR);
    // Pit a2 = new Pit(0,0,4, PIT_COLOR);
    // Pit a3 = new Pit(0,0,4, PIT_COLOR);
    // Pit a4 = new Pit(0,0,4, PIT_COLOR);
    // Pit a5 = new Pit(0,0,4, PIT_COLOR);
    // Pit a6 = new Pit(0,0,4, PIT_COLOR);
    // MancalaStore b0 = new MancalaStore(0,0,0,0,0, PIT_COLOR);
    // Pit b1 = new Pit(0,0,4, PIT_COLOR);
    // Pit b2 = new Pit(0,0,4, PIT_COLOR);
    // Pit b3 = new Pit(0,0,4, PIT_COLOR);
    // Pit b4 = new Pit(0,0,4, PIT_COLOR);
    // Pit b5 = new Pit(0,0,4, PIT_COLOR);
    // Pit b6 = new Pit(0,0,4, PIT_COLOR);

    public void addPits() {
        // pitManager.addAPit(a0);
        // pitManager.addAPit(a1);
        // pitManager.addAPit(a2);
        // pitManager.addAPit(a3);
        // pitManager.addAPit(a4);
        // pitManager.addAPit(a5);
        // pitManager.addAPit(a6);
        // pitManager.addBPit(b0);
        // pitManager.addBPit(b1);
        // pitManager.addBPit(b2);
        // pitManager.addBPit(b3);
        // pitManager.addBPit(b4);
        // pitManager.addBPit(b5);
        // pitManager.addBPit(b6);
    }

    public void printPits() {
        // System.out.println("a0 : " + a0.getStoneCount());
        // System.out.println("a1 : " + a1.getStoneCount());
        // System.out.println("a2 : " + a2.getStoneCount());
        // System.out.println("a3 : " + a3.getStoneCount());
        // System.out.println("a4 : " + a4.getStoneCount());
        // System.out.println("a5 : " + a5.getStoneCount());
        // System.out.println("a6 : " + a6.getStoneCount());
        // System.out.println("b0 : " + b0.getStoneCount());
        // System.out.println("b1 : " + b1.getStoneCount());
        // System.out.println("b2 : " + b2.getStoneCount());
        // System.out.println("b3 : " + b3.getStoneCount());
        // System.out.println("b4 : " + b4.getStoneCount());
        // System.out.println("b5 : " + b5.getStoneCount());
        // System.out.println("b6 : " + b6.getStoneCount());
    }

    @Test
    public void testSelect() {
        System.out.println("INITIAL");
        printPits();
        addPits();
        System.out.println("PLAYER A TURN? " + pitManager.isPlayerATurn());
        // pitManager.select(a2);
        System.out.println("SELECTED A2");
        printPits();
        System.out.println("PLAYER A TURN? " + pitManager.isPlayerATurn());
    }

}
