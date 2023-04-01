import java.util.*;

public class Move {
    private ArrayList<Integer> legalMovesBlack = new ArrayList<Integer>();
    private ArrayList<Integer> legalMovesRed = new ArrayList<Integer>();
    boolean blackMove;

    public Move() {
        blackMove = true;
    }

    public ArrayList<Integer> getLegalMovesBlack() {
        return legalMovesBlack;
    }

    public ArrayList<Integer> getLegalMovesRed() {
        return legalMovesRed;
    }

    public boolean canMoveLeftBlack(Checker[][] checkerPieces, Checker e) {
        if (e.getPiecePositionX() + 1 <= 7 && e.getPiecePositionY() - 1 >= 0) {
            if (checkerPieces[e.getPiecePositionX() + 1][e.getPiecePositionY() - 1].getColor().equals("")
                    && !(checkerPieces[e.getPiecePositionX() + 1][e.getPiecePositionY() - 1].getColor().equals("black"))) {
                legalMovesBlack.add((e.getPiecePositionX() + 1) * 10 + (e.getPiecePositionY() - 1));
                return true;
            }
            return false;
        }
        return false;
    }

    public boolean canMoveRightBlack(Checker[][] checkerPieces, Checker e) {
        if (e.getPiecePositionX() + 1 <= 7 && e.getPiecePositionY() + 1 <= 7) {
            if (checkerPieces[e.getPiecePositionX() + 1][e.getPiecePositionY() + 1].getColor().equals("")
                    && !(checkerPieces[e.getPiecePositionX() + 1][e.getPiecePositionY() + 1].getColor().equals("black"))) {
                legalMovesBlack.add((e.getPiecePositionX() + 1) * 10 + (e.getPiecePositionY() + 1));
                return true;
            }
            return false;
        }
        return false;
    }

    public boolean canMoveLeftRed(Checker[][] checkerPieces, Checker e) {
        if (e.getPiecePositionX() - 1 >= 0 && e.getPiecePositionY() - 1 >= 0) {
            if (checkerPieces[e.getPiecePositionX() - 1][e.getPiecePositionY() - 1].getColor().equals("")
                    && !(checkerPieces[e.getPiecePositionX() - 1][e.getPiecePositionY() - 1].getColor().equals("red"))) {
                legalMovesRed.add((e.getPiecePositionX() - 1) * 10 + (e.getPiecePositionY() - 1));
                return true;
            }
            return false;
        }
        return false;
    }

    public boolean canMoveRightRed(Checker[][] checkerPieces, Checker e) {
        if (e.getPiecePositionX() - 1 >= 0 && e.getPiecePositionY() + 1 <= 7) {
            if (checkerPieces[e.getPiecePositionX() - 1][e.getPiecePositionY() + 1].getColor().equals("")
                    && !(checkerPieces[e.getPiecePositionX() - 1][e.getPiecePositionY() + 1].getColor().equals("red"))) {
                legalMovesRed.add((e.getPiecePositionX() - 1) * 10 + (e.getPiecePositionY() + 1));
                return true;
            }
            return false;
        }
        return false;
    }

    public void switchTurns() {

        if (blackMove == false) {
            blackMove = true;
        } else {
            blackMove = false;
        }
    }

    public void resetMoves() {
        legalMovesRed = new ArrayList<>();
        legalMovesBlack = new ArrayList<>();
    }

    public boolean isBlackMove() {
        return blackMove;
    }



}


