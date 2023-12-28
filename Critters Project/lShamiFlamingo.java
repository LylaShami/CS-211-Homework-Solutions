import java.awt.*;

public class lShamiFlamingo extends Critter {
    int moveCount = 0;

    public lShamiFlamingo() {

    }

    @Override
    public Action getMove(CritterInfo info) {
        moveCount++;
        if (info.getFront() == Neighbor.OTHER) {
            return Action.INFECT;
        } else if (info.getBack() == Neighbor.EMPTY || info.getFront() == Neighbor.EMPTY) {
            return Action.HOP;

        } else {
            return Action.RIGHT;
        }
    }

    @Override
    public String toString() {
        return "flamingo";
    }

    @Override
    public Color getColor() {
        return Color.PINK;
    }
}
