public class MovePromoted extends Move {
    CapturePromoted capturePromoted = new CapturePromoted();

    public MovePromoted() {
    }

    @Override
    public boolean canMoveLeftBlack(Checker[][] checkerPieces, Checker e) { //debug this portion
        super.canMoveLeftBlack(checkerPieces, e); //
        if (e.getPiecePositionX() - 2 >= 0 && e.getPiecePositionY() - 2 >= 0) {
            if (checkerPieces[e.getPiecePositionX() - 1][e.getPiecePositionY() - 1].getColor().equals("")
                    && !(checkerPieces[e.getPiecePositionX() - 1][e.getPiecePositionY() - 1].getColor().equals("black"))) {
                getLegalMovesBlack().add((e.getPiecePositionX() - 1) * 10 + (e.getPiecePositionY() - 1));
                return true;
            }
            return false;
        }
        return false;
    }

    @Override
    public boolean canMoveRightBlack(Checker[][] checkerPieces, Checker e) {
        super.canMoveRightBlack(checkerPieces, e);
        if (e.getPiecePositionX() - 2 >= 0 && e.getPiecePositionY() + 2 <= 7) {
            if (checkerPieces[e.getPiecePositionX() - 1][e.getPiecePositionY() + 1].getColor().equals("")
                    && !(checkerPieces[e.getPiecePositionX() - 1][e.getPiecePositionY() + 1].getColor().equals("black"))) {
                getLegalMovesBlack().add((e.getPiecePositionX() - 1) * 10 + (e.getPiecePositionY() + 1));
                return true;
            }
            return false;
        }
        return false;
    }

    @Override
    public boolean canMoveLeftRed(Checker[][] checkerPieces, Checker e) {
        super.canMoveLeftRed(checkerPieces, e);
        if (e.getPiecePositionX() + 1 <= 7 && e.getPiecePositionY() - 1 >= 0) {
            if (checkerPieces[e.getPiecePositionX() + 1][e.getPiecePositionY() - 1].getColor().equals("")
                    && !(checkerPieces[e.getPiecePositionX() + 1][e.getPiecePositionY() - 1].getColor().equals("red"))) {
                getLegalMovesRed().add((e.getPiecePositionX() + 1) * 10 + (e.getPiecePositionY() - 1));
                return true;
            }
            return false;
        }
        return false;
    }

    @Override
    public boolean canMoveRightRed(Checker[][] checkerPieces, Checker e) {
        super.canMoveRightRed(checkerPieces, e);
        if (e.getPiecePositionX() + 1 <= 7 && e.getPiecePositionY() + 1 <= 7) {
            if (checkerPieces[e.getPiecePositionX() + 1][e.getPiecePositionY() + 1].getColor().equals("")
                    && !(checkerPieces[e.getPiecePositionX() + 1][e.getPiecePositionY() + 1].getColor().equals("red"))) {
                getLegalMovesRed().add((e.getPiecePositionX() + 1) * 10 + (e.getPiecePositionY() + 1));
                return true;
            }
            return false;
        }
        return false;
    }

    public boolean winCondition(Checker[][] checkerPieces) {
        if((containsRed(checkerPieces) == false && containsBlack(checkerPieces)) == false) {
            return false;
        }

        for (int i = 0; i < 8; i++) {
            for (int y = 0; y < 8; y++) {
                if (blackMove) { //if it is black to move, check the legal moves for all black checkers
                    if (checkerPieces[i][y].getColor().equals("black")){
                        if(checkerPieces[i][y].isPromoted()) {
                            if ((canMoveLeftBlack(checkerPieces, checkerPieces[i][y]) || canMoveRightBlack
                                    (checkerPieces, checkerPieces[i][y])) || (capturePromoted.canCaptureLeftBlack
                                    (checkerPieces, checkerPieces[i][y]) || (capturePromoted.canCaptureRightBlack(checkerPieces, checkerPieces[i][y])))) { //if black piece has any regular moves
                                return true;
                            }
                        } else if (super.canMoveLeftBlack(checkerPieces, checkerPieces[i][y]) || super.canMoveRightBlack(checkerPieces, checkerPieces[i][y])) {
                            return true;
                        }
                    }
                } else {
                    if(checkerPieces[i][y].getColor().equals("red")) {
                        if(checkerPieces[i][y].isPromoted()){
                            if((canMoveLeftRed(checkerPieces, checkerPieces[i][y]) || canMoveRightRed
                                    (checkerPieces, checkerPieces[i][y])) || (capturePromoted.canCaptureLeftRed
                                    (checkerPieces, checkerPieces[i][y]) || (capturePromoted.canCaptureRightRed(checkerPieces, checkerPieces[i][y])))) {
                                return true;
                            }
                        }
                        if (super.canMoveLeftRed(checkerPieces, checkerPieces[i][y]) || super.canMoveRightRed(checkerPieces, checkerPieces[i][y])) {
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }

    private boolean containsRed(Checker[][] checkerPieces) {
        boolean contains = false;
        for (int i = 0; i < 8; i++) {
            for (int y = 0; y < 8; y++) {
                if (checkerPieces[i][y].getColor().equals("red")) {
                    contains = true;
                }
            }
        }
        return contains;
    }

    private boolean containsBlack(Checker[][] checkerPieces) {
        boolean contains = false;
        for (int i = 0; i < 8; i++) {
            for (int y = 0; y < 8; y++) {
                if (checkerPieces[i][y].getColor().equals("black")) {
                    contains = true;
                }
            }
        }
        return contains;
    }
}