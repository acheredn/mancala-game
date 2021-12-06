import java.util.ArrayList;

/**
 * Manages pits and general game logic
 */
public class PitManager {
    private ArrayList<MancalaStore> aList;
    private ArrayList<MancalaStore> bList;
    private boolean playerATurn;

    /**
     * Manages Pit objects through two lists.
     */
    public PitManager() {
        aList = new ArrayList<MancalaStore>(7);
        bList = new ArrayList<MancalaStore>(7);
        playerATurn = true;
    }

    /**
     * Adds pit to player A's list of pits.
     * @param pit
     */
    public void addAPit(MancalaStore pit) {
        aList.add(pit);
    }

    /**
     * Adds pit to player B's list of pits.
     * @param pit
     */
    public void addBPit(MancalaStore pit) {
        bList.add(pit);
    }

    /**
     * @return True if it is player A's turn, false if player B's turn.
     */
    public boolean isPlayerATurn() {
        return playerATurn;
    }

    /**
     * Sets playerATurn to value of parameter a.
     */
    public void setPlayerATurn(boolean a) {
        playerATurn = a;
    }

    /**
     * Public method called by StartGame to select a pit. 
     * If the selected pit is not on the player's side whose turn it is, nothing happens.
     * Otherwise, calls takeFrom() with pit and corresponding lists.
     * @param pit
     */
    public void select(Pit pit) {
        if (aList.contains(pit) && playerATurn) {
            takeFrom(aList, bList, pit);
        }
        if (bList.contains(pit) && !playerATurn) {
            takeFrom(bList, aList, pit);
        }
    }

    /**
     * Handles pits based on the pit selected and Mancala rules.
     * Calls helper methods secondTurn(), isCaptured(), and gameOver() to check for and handle special cases.
     * @param currentList The list containing selectedPit
     * @param otherList
     * @param selectedPit
     */
    private void takeFrom(ArrayList<MancalaStore> currentList, ArrayList<MancalaStore> otherList, Pit selectedPit) {
        int pickedUpStones = selectedPit.getStoneCount();
        selectedPit.removeAllStones();
        int index = currentList.indexOf(selectedPit) - 1;
        MancalaStore nextPit = currentList.get(index);

        while (pickedUpStones > 0) {
            if (index > 0) {
                // drop a stone into next pit
                nextPit = currentList.get(index);
                nextPit.addStone();
                pickedUpStones--;
                index--;
            } else if (index == 0) {
                nextPit = currentList.get(index);
                // only add to mancala store if on that player's turn
                if ((currentList == aList && playerATurn) || (currentList == bList && !playerATurn)) {
                    nextPit.addStone();
                    pickedUpStones--;
                }
                index--;
            } else {
                // switch lists, wrap around to end of other list
                ArrayList<MancalaStore> tempList = currentList;
                currentList = otherList;
                otherList = tempList;
                index = 6;
            }

            if (secondTurn(pickedUpStones, nextPit, currentList)) {
                return;
            }
            isCaptured(pickedUpStones, nextPit, currentList, otherList);
            gameOver();
        }
        playerATurn = !playerATurn;
    }

    /**
     * Checks if the final stone is put in an empty pit. 
     * If so, the final stone along with any stones opposite to the pit are captured.
     */
    private void isCaptured(
        int pickedUpStones,
        MancalaStore nextPit,
        ArrayList<MancalaStore> currentList,
        ArrayList<MancalaStore> otherList) {

        MancalaStore store;
        if (playerATurn)
            store = aList.get(0);
        else
            store = bList.get(0);

        if (pickedUpStones == 0 && nextPit.getStoneCount() == 1 && nextPit instanceof Pit) {
            MancalaStore opposite = otherList.get(7 - currentList.indexOf(nextPit));
            int capturedStones = opposite.getStoneCount() + 1;
            int storeCount = store.getStoneCount();
            store.setStoneCount(storeCount + capturedStones);
            opposite.setStoneCount(0);
            nextPit.setStoneCount(0);
        }
    }

    /**
     * @return True if the current player gets a second turn due to ending in mancala store, false otherwise.
     */
    private boolean secondTurn(int pickedUpStones, MancalaStore nextPit, ArrayList<MancalaStore> currentList) {
        return (pickedUpStones == 0 && currentList.indexOf(nextPit) == 0);
    }

    /**
     * Checks if all pits on current player's side are empty.
     * If so, removes all stones from opposite side and adds them to the opposite store.
     * @return True if all pits on current player's side are empty, false otherwise.
     */
    public boolean gameOver() {
        ArrayList<MancalaStore> list;
        ArrayList<MancalaStore> oppositeList;
        boolean gameIsOver = true;
        if (playerATurn) {
            list = aList;
            oppositeList = bList;
        } else {
            list = bList;
            oppositeList = aList;
        }
        for (int i = 1; i < 7; i++) {
            if (!list.get(i).isEmpty()) {
                gameIsOver = false;
            }
        }
        if (gameIsOver) {
            MancalaStore store = oppositeList.get(0);
            for (int i = 6; i > 0; i--) {
                Pit pit = (Pit) oppositeList.get(i);
                int stones = pit.getStoneCount();
                pit.removeAllStones();
                store.setStoneCount(store.getStoneCount() + stones);
            }
        }
        return gameIsOver;
    }

    /**
     * @return True if player A score is greater than player B score, false otherwise.
     */
    public boolean playerAWinner() {
        return (getAscore() > getBscore());
    }

    /**
     * @return True if player A score is equal to player B score, false otherwise.
     */
    public boolean isTie() {
        return (getAscore() == getBscore());
    }

    /**
     * @return Amount of stones in player A's mancala store.
     */
    public int getAscore() {
        return (aList.get(0).getStoneCount());
    }

    /**
     * @return Amount of stones in player B's mancala store.
     */
    public int getBscore() {
        return (bList.get(0).getStoneCount());
    }

    @Override
    public String toString() {
        return "Pit Manager for list A ("+ aList.toString() + ") and list B (" + bList.toString() +")";
    }

}
