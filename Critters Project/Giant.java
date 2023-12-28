import java.awt.*;

public class Giant extends Critter {
    private int moveCount = 0;

    private String sound;
    private int curIndex = 0;
    private String[] availableSounds = {"fee", "fie", "foe", "fum"};

    public Giant() {
    }

    //always infect if enemey is in front
    // otherwise hop if possible
    // otherwise turn right
    @Override
    public Action getMove(CritterInfo info) {
        moveCount++;
        if (info.getFront() == Neighbor.OTHER) {
            return Action.INFECT;
        } else if (info.getFront() == Neighbor.EMPTY) {
            return Action.HOP;
        } else {
            return Action.RIGHT;
        }

    }

    @Override
    public String toString() {
        //after every 6 moves
        if (moveCount % 6 == 0) {
            updateSound();

        }
        return sound;
    }

    @Override
    public Color getColor() {
        return Color.GRAY;
    }

    private void updateSound() {
        sound = availableSounds[curIndex];
        curIndex++;
        resetSoundAtLength();

    }

    private void resetSoundAtLength() {
        if (curIndex == 4) {
            curIndex = 0;
        }
    }
}

