import java.util.*;

public class MovePromoted extends Move {
    private ArrayList<Integer> legalMovesBlack = new ArrayList<Integer>();
    private ArrayList<Integer> legalMovesRed = new ArrayList<Integer>();

    public MovePromoted() {
    }

    public ArrayList<Integer> getLegalMovesBlack(){
        return legalMovesBlack;
    }

    public ArrayList<Integer> getLegalMovesRed(){
        return legalMovesRed;
    }

    @Override
    public boolean canMoveLeftBlack(Checker[][] checkerPieces, Checker e) {

        super.canMoveLeftBlack(checkerPieces, e);
        if (e.getPiecePositionX() - 1 >= 0 || e.getPiecePositionY() - 1 >= 0) {
            if (checkerPieces[e.getPiecePositionX() - 1][e.getPiecePositionY() - 1].getColor().equals("")
                    && !(checkerPieces[e.getPiecePositionX() - 1][e.getPiecePositionY() - 1].getColor().equals("black"))) {
                getLegalMovesBlack().add((e.getPiecePositionX()-1) * 10 + (e.getPiecePositionY()-1));
                return true;
            }
            return false;
        }
        return false;
    }

    @Override
    public boolean canMoveRightBlack(Checker[][] checkerPieces, Checker e) {
        super.canMoveRightBlack(checkerPieces, e);
        if (e.getPiecePositionX() - 1 >= 0 || e.getPiecePositionY() + 1 <= 7) {
            if (checkerPieces[e.getPiecePositionX() - 1][e.getPiecePositionY() + 1].getColor().equals("")
                    && !(checkerPieces[e.getPiecePositionX() - 1][e.getPiecePositionY() + 1].getColor().equals("black"))) {
                getLegalMovesBlack().add(e.getPiecePositionX() * 10 + e.getPiecePositionY());
                return true;
            }
            return false;
        }
        return false;
    }

    @Override
    public boolean canMoveLeftRed(Checker[][] checkerPieces, Checker e) {
        super.canMoveLeftRed(checkerPieces, e);
        if (e.getPiecePositionX() + 1 <= 7 || e.getPiecePositionY() - 1 >= 0) {
            if (checkerPieces[e.getPiecePositionX() + 1][e.getPiecePositionY() - 1].getColor().equals("")
                    && !(checkerPieces[e.getPiecePositionX() + 1][e.getPiecePositionY() - 1].getColor().equals("red"))) {
                getLegalMovesRed().add(e.getPiecePositionX() * 10 + e.getPiecePositionY());
                return true;
            }
            return false;
        }
        return false;
    }

    @Override
    public boolean canMoveRightRed(Checker[][] checkerPieces, Checker e) {
        super.canMoveRightRed(checkerPieces, e);
        if (e.getPiecePositionX() + 1 <= 7 || e.getPiecePositionY() + 1 <= 7) {
            if (checkerPieces[e.getPiecePositionX() + 1][e.getPiecePositionY() + 1].getColor().equals("")
                    && !(checkerPieces[e.getPiecePositionX() + 1][e.getPiecePositionY() + 1].getColor().equals("red"))) {
                getLegalMovesRed().add(e.getPiecePositionX() * 10 + e.getPiecePositionY());
                return true;
            }
            return false;
        }
        return false;
    }


}