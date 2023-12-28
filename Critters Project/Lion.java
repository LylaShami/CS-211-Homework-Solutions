import java.awt.*;

public class Lion extends Critter {
    int moveCount = 0;
    Color lionColor;

    public Lion() {

    }

    // always infect if enemy is in front,
    // otherwise if a wall is in front or the right then turn left
    //otherwise hop
    @Override
    public Action getMove(CritterInfo info) {
        moveCount++;
        if (info.getFront() == Neighbor.OTHER) {
            return Action.INFECT;
        } else if (info.getFront() == Neighbor.WALL) {
            return Action.LEFT;
        } else if (info.getRight() == Neighbor.WALL) {
            return Action.LEFT;
        } else if (info.getFront() == Neighbor.SAME) {
            return Action.RIGHT;
        } else {
            return Action.HOP;
        }

    }

    @Override
    public String toString() {
        return "L";
    }

    @Override
    public Color getColor() {
        setLionColor();//every three moves
        return lionColor;
    }

    private void setLionColor() {
        if (moveCount % 3 == 0) { // on every third move there is a color change
            pickRandomColor();
        }
    }

    private void pickRandomColor() { //todo switch case
        int randomNumber = (int) (Math.random() * 3 + 1);

        if (randomNumber == 1) {
            lionColor = Color.RED;
        } else if (randomNumber == 2) {
            lionColor = Color.BLUE;
        } else {
            lionColor = Color.GREEN;
        }

    }
}