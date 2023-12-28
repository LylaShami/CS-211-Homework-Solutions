import java.awt.*;

public class Bear extends Critter {
    private int moveCount = 0;
    private Color bearColor;

    public Bear(boolean polar) {
        this.moveCount = 0;
        assignColor(polar);
    }

//always infect if enemy is in front,
// otherwise hop if possible,
// otherwise turn left
    // if enemy is infront infect, else hop, else turn left

    @Override
    public Action getMove(CritterInfo info) {
        moveCount++;
        if(info.getFront() == Neighbor.OTHER){
            return Action.INFECT;
        } else if (info.getFront() == Neighbor.EMPTY){ // todo ask if each action counts as a move
            return Action.HOP;
        } else{
            return Action.LEFT;
        }
    }

    @Override
    public Color getColor() {
        return bearColor;

    }

    /**
     *
     * @return
     */
    @Override
    public String toString() { //todo figure out the backslash
        if (moveCount % 2 == 0) {
            return "/";
        } else {
            return "\\";
        }
    }

    private void assignColor(boolean polar) {
        // when polar is true, the bearColor is white. when polar is false,then the bearColor is black
        if (polar == true) {
            bearColor = Color.WHITE;
        } else {
            bearColor = Color.BLACK;
        }
    }
}
