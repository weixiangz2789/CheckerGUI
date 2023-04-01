import java.util.*;
public class Move{
    private ArrayList<Integer> legalMovesBlack = new ArrayList<Integer>();
    private ArrayList<Integer> legalMovesRed = new ArrayList<Integer>();
    boolean blackMove;
//    MovePromoted movePromoted = new MovePromoted();
//    CapturePromoted capturePromoted = new CapturePromoted();


    public Move() {
        blackMove = true;
    }

    public boolean canMoveLeftBlack(Checker[][] checkerPieces, Checker e){
        if(e.getPiecePositionX()+1 <= 7 && e.getPiecePositionY()-1 >= 0){
            if(checkerPieces[e.getPiecePositionX()+1][e.getPiecePositionY()-1].getColor().equals("")
                    && !(checkerPieces[e.getPiecePositionX()+1][e.getPiecePositionY()-1].getColor().equals("black"))){
                legalMovesBlack.add(e.getPiecePositionX() * 10 + e.getPiecePositionY());
                return true;
            }
            return false;
        }
        return false;
    }
    public boolean canMoveRightBlack(Checker[][] checkerPieces, Checker e){
        if(e.getPiecePositionX()+1 <= 7 && e.getPiecePositionY()+1 <= 7){
            if(checkerPieces[e.getPiecePositionX()+1][e.getPiecePositionY()+1].getColor().equals("")
                    && !(checkerPieces[e.getPiecePositionX()+1][e.getPiecePositionY()+1].getColor().equals("black"))){
                legalMovesBlack.add(e.getPiecePositionX() * 10 + e.getPiecePositionY());
                return true;
            }
            return false;
        }
        return false;
    }

    public boolean canMoveLeftRed(Checker[][] checkerPieces, Checker e){
        if(e.getPiecePositionX()-1 >= 0 && e.getPiecePositionY() - 1 >=0){
            if(checkerPieces[e.getPiecePositionX()-1][e.getPiecePositionY()-1].getColor().equals("")
                    && !(checkerPieces[e.getPiecePositionX()-1][e.getPiecePositionY()-1].getColor().equals("red"))){
                legalMovesRed.add(e.getPiecePositionX() * 10 + e.getPiecePositionY());
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
                legalMovesRed.add((e.getPiecePositionX() * 10) + e.getPiecePositionY());
                return true;
            }
            return false;
        }
        return false;
    }
    public boolean onLastRank(Checker e){
        if(e.getColor().equals("black") && e.getPiecePositionY() == 7){
            e.setPromoted(true);
            return true;
        } else if(e.getColor().equals("red") && e.getPiecePositionY() == 0){
            e.setPromoted(true);
            return true;
        } else{
            return false;
        }
    }

    public void switchTurns() {

        if (blackMove == false) {
            blackMove = true;
        } else {
            blackMove = false;
        }
    }

    public boolean isBlackMove() {
        return blackMove;
    }

//    public boolean winCondition(Checker[][] checkerPieces) {
//        boolean allCanMove = false;
//        for (int i = 0; i < 8; i++) {
//            for (int y = 0; y < 8; y++) {
//                if (blackMove) { //if it is black to move, check the legal moves for all black checkers
//                    if (checkerPieces[i][y].getColor().equals("black")){
//                        if(checkerPieces[i][y].isPromoted()) {
//                            if ((movePromoted.canMoveLeftBlack(checkerPieces, checkerPieces[i][y]) && movePromoted.canMoveRightBlack
//                                    (checkerPieces, checkerPieces[i][y])) || (capturePromoted.canCaptureLeftBlack
//                                    (checkerPieces, checkerPieces[i][y]) && (capturePromoted.canCaptureRightBlack(checkerPieces, checkerPieces[i][y])))) { //if black piece has any regular moves
//                                allCanMove = true;
//                            }
//                        } else if (canMoveLeftBlack(checkerPieces, checkerPieces[i][y]) && canMoveRightBlack(checkerPieces, checkerPieces[i][y])) {
//                            allCanMove = true;
//                        }
//                    }
//                } else if(checkerPieces[i][y].getColor().equals("red")) {
//                    if(checkerPieces[i][y].isPromoted()){
//                        if((movePromoted.canMoveLeftRed(checkerPieces, checkerPieces[i][y]) && movePromoted.canMoveRightRed
//                                (checkerPieces, checkerPieces[i][y])) || (capturePromoted.canCaptureLeftRed
//                                (checkerPieces, checkerPieces[i][y]) && (capturePromoted.canCaptureRightRed(checkerPieces, checkerPieces[i][y])))) {
//                            allCanMove = true;
//                        }
//                    }
//                    if (canMoveLeftRed(checkerPieces, checkerPieces[i][y]) && canMoveRightRed(checkerPieces, checkerPieces[i][y])) {
//                        allCanMove = true;
//                    }
//                }
//            }
//        }
//        return allCanMove;
//}
    }


