public class Capture extends Move{
    boolean isOnEdge;

    public Capture(boolean isOnEdge){
        super(isOnEdge);
    }

    public boolean canCaptureLeftRed(Checker[][] checkerPieces, Checker e){
        if(checkerPieces[e.getPiecePositionX()-2][e.getPiecePositionY()-2].getColor().equals("")
                && checkerPieces[e.getPiecePositionX()-1][e.getPiecePositionY()-1].getColor().equals("black") && (e.getPiecePositionX()-2 >= 0 ) && (e.getPiecePositionY()-2 >=0)){
            return true;
        }
        return false;
    }

    public boolean canCaptureRightRed(Checker[][] checkerPieces, Checker e){
        if(checkerPieces[e.getPiecePositionX()-2][e.getPiecePositionY()-2].getColor().equals("")
                && checkerPieces[e.getPiecePositionX()-1][e.getPiecePositionY()-1].getColor().equals("black") && (e.getPiecePositionX()-2 >= 0 ) && (e.getPiecePositionY()-2 >=0)){
            return true;
        }
        return false;
    }

    public boolean canCaptureRightBlack(Checker[][] checkerPieces, Checker e){
        if(checkerPieces[e.getPiecePositionX()+2][e.getPiecePositionX()+2].getColor().equals("")
                && checkerPieces[e.getPiecePositionX()+1][e.getPiecePositionX()+1].getColor().equals("Red")){
            return true;
        }
        return false;
    }

    public boolean canCaptureLeftBlack(Checker[][] checkerPieces, Checker e){
        if(checkerPieces[e.getPiecePositionX()-2][e.getPiecePositionX()-2].getColor().equals("")
                && checkerPieces[e.getPiecePositionX()-2][e.getPiecePositionX()-2].getColor().equals("red")){
            return true;
        }
        return false;
    }

    public void captureStandard(){

    }

    public void capturePromoted(){
        //if()
    }


}