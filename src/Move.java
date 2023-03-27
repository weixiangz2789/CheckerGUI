public class Move{
    private boolean isOnEdge;

    public Move(boolean isOnEdge){
        this.isOnEdge = isOnEdge;
    }

    public boolean canMoveLeftBlack(Checker[][] checkerPieces, Checker e){
        if(checkerPieces[e.getPiecePositionX()+1][e.getPiecePositionY()-1].getColor().equals("")
                && checkerPieces[e.getPiecePositionX()+1][e.getPiecePositionY()+1].getColor().equals("black")){
            return true;
        }
        return false;
    }

    public boolean canMoveRightBlack(Checker[][] checkerPieces, Checker e){
        if(checkerPieces[e.getPiecePositionX()+1][e.getPiecePositionY()+1].getColor().equals("")
                && checkerPieces[e.getPiecePositionX()+1][e.getPiecePositionY()+1].getColor().equals("black")){
            return true;
        }
        return false;
    }

    public boolean canMoveLeftRed(Checker[][] checkerPieces, Checker e){
        if(checkerPieces[e.getPiecePositionX()-1][e.getPiecePositionX()-1].getColor().equals("")
                && checkerPieces[e.getPiecePositionX()-1][e.getPiecePositionX()-1].getColor().equals("red")){
            return true;
        }
        return false;
    }

    public boolean canMoveRightRed(Checker[][] checkerPieces, Checker e){
        if(checkerPieces[e.getPiecePositionX()+1][e.getPiecePositionX()-1].getColor().equals("")
                && checkerPieces[e.getPiecePositionX()+1][e.getPiecePositionX()-1].getColor().equals("red")){
            return true;
        }
        return false;
    }
}


