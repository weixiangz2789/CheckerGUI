import java.util.*;
public class Move{
    private ArrayList<Integer> legalMovesBlack = new ArrayList<Integer>();
    private ArrayList<Integer> legalMovesRed = new ArrayList<Integer>();

    public Move(){
    }

    public boolean canMoveLeftBlack(Checker[][] checkerPieces, Checker e){
        if(e.getPiecePositionX()-1 >= 0 && e.getPiecePositionY()-1 >= 0){
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
        if(e.getPiecePositionX()-1 >= 0 && e.getPiecePositionY() > 1){
            if(checkerPieces[e.getPiecePositionX()-1][e.getPiecePositionY()-1].getColor().equals("")
                    && !(checkerPieces[e.getPiecePositionX()-1][e.getPiecePositionY()-1].getColor().equals("red"))){
                legalMovesRed.add(e.getPiecePositionX() * 10 + e.getPiecePositionY());
                return true;
            }
            return false;
        }
        return false;
    }

    public boolean canMoveRightRed(Checker[][] checkerPieces, Checker e){
        if(e.getPiecePositionX()+1 <= 7 && e.getPiecePositionY()+1 <= 7){
            if(checkerPieces[e.getPiecePositionX()-1][e.getPiecePositionY()+1].getColor().equals("")
                    && !(checkerPieces[e.getPiecePositionX()-1][e.getPiecePositionY()+1].getColor().equals("red"))){
                legalMovesRed.add(e.getPiecePositionX() * 10 + e.getPiecePositionY());
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

//    public void updatePosition(Checker e){
//        if()
//    }
//    public ArrayList<Integer> getLegalMovesBlack(){
//        return legalMovesBlack;
//    }
//    public ArrayList<Integer> getLegalMovesBlack(){
//        return legalMovesRed;
//    }
}


