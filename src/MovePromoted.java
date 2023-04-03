public class MovePromoted extends Move {
    private CapturePromoted capturePromoted = new CapturePromoted();
    private String loser = "";
    private Capture capture = new Capture();

    public MovePromoted() {
    }

    public String getLoser() {
        return loser;
    }

    @Override
    public boolean canMoveLeftBlack(Checker[][] checkerPieces, Checker e) { //debug this portion
        super.canMoveLeftBlack(checkerPieces, e); //
        if (e.getPiecePositionX() - 1 >= 0 && e.getPiecePositionY() - 1 >= 0) {
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
        if (e.getPiecePositionX() - 1 >= 0 && e.getPiecePositionY() + 1 <= 7) {
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

//    public boolean winCondition(Checker[][] checkerPieces) {
//        if((containsRed(checkerPieces) == false) || (containsBlack(checkerPieces)) == false) {
//            return false;
//        }
//
//
//        for (int i = 0; i < 8; i++) {
//            for (int y = 0; y < 8; y++) {
//                if (blackMove) { //if it is black to move, check the legal moves for all black checkers
//                    if (checkerPieces[i][y].getColor().equals("black")){
//                        if(checkerPieces[i][y].isPromoted()) {
//                            if ((canMoveLeftBlack(checkerPieces, checkerPieces[i][y]) || canMoveRightBlack
//                                    (checkerPieces, checkerPieces[i][y])) || (capturePromoted.canCaptureLeftBlack
//                                    (checkerPieces, checkerPieces[i][y]) || (capturePromoted.canCaptureRightBlack(checkerPieces, checkerPieces[i][y])))) { //if black piece has any regular moves
//                                return true;
//                            }
//                        } else if (super.canMoveLeftBlack(checkerPieces, checkerPieces[i][y]) || super.canMoveRightBlack(checkerPieces, checkerPieces[i][y])) {
//                            return true;
//                        }
//                    }
//                } else {
//                    if(checkerPieces[i][y].getColor().equals("red")) {
//                        if(checkerPieces[i][y].isPromoted()) {
//                            if ((canMoveLeftRed(checkerPieces, checkerPieces[i][y]) || canMoveRightRed
//                                    (checkerPieces, checkerPieces[i][y])) || (capturePromoted.canCaptureLeftRed
//                                    (checkerPieces, checkerPieces[i][y]) || (capturePromoted.canCaptureRightRed(checkerPieces, checkerPieces[i][y])))) {
//                                return true;
//                            }
//
//                        } else if (super.canMoveLeftRed(checkerPieces, checkerPieces[i][y]) || super.canMoveRightRed(checkerPieces, checkerPieces[i][y]) ) {
//                            return true;
//                        }
//                    }
//                }
//            }
//        }
//        return false;
//    }

    //false = end true = continue
    public boolean winCondition(Checker[][] checkerPieces) {
        if ((containsRed(checkerPieces) == false) || (containsBlack(checkerPieces)) == false) {
            return false;
        }
        for (int i = 0; i < 8; i++) {
            for (int y = 0; y < 8; y++) {
                if (blackMove) { //if it is black to move, check the legal moves for all black checkers
                    if (checkerPieces[i][y].getColor().equals("black")) {
                        if (checkerPieces[i][y].isPromoted()) {
                            canMoveLeftBlack(checkerPieces, checkerPieces[i][y]);
                            canMoveRightBlack(checkerPieces, checkerPieces[i][y]);
                            capturePromoted.canCaptureLeftBlack(checkerPieces, checkerPieces[i][y]);
                            capturePromoted.canCaptureRightBlack(checkerPieces, checkerPieces[i][y]);
                        } else { //not promoted but still black
                            super.canMoveLeftBlack(checkerPieces, checkerPieces[i][y]);
                            super.canMoveRightBlack(checkerPieces, checkerPieces[i][y]);
                            capture.canCaptureLeftBlack(checkerPieces, checkerPieces[i][y]);
                            capture.canCaptureRightBlack(checkerPieces, checkerPieces[i][y]);
                        }
                        if (getLegalMovesBlack().size() > 0) {
                            return true;
                        }
                    }
                } else { //red
                    if (checkerPieces[i][y].isPromoted()) { //promoted red
                        canMoveLeftRed(checkerPieces, checkerPieces[i][y]);
                        canMoveRightRed(checkerPieces, checkerPieces[i][y]);
                        capturePromoted.canCaptureLeftRed(checkerPieces, checkerPieces[i][y]);
                        capturePromoted.canCaptureRightRed(checkerPieces, checkerPieces[i][y]);
                    } else { //non promoted red
                        super.canMoveLeftRed(checkerPieces, checkerPieces[i][y]);
                        super.canMoveRightRed(checkerPieces, checkerPieces[i][y]);
                        capture.canCaptureLeftRed(checkerPieces, checkerPieces[i][y]);
                        capture.canCaptureRightRed(checkerPieces, checkerPieces[i][y]);
                    }
                    if (getLegalMovesRed().size() > 0) {
                        return true;
                    }
                }
            }
        }
        if (blackMove){
            loser = "black";
        }
        else{
            loser = "red";
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
        if (!contains) {
            loser = "red";
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
        if (!contains) {
            loser = "black";
        }
        return contains;
    }
}