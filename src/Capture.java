//public class Capture extends Move{
//
//    public Capture(){
//        super();
//
//    }
//
//    public boolean canCaptureLeftBlack(Checker[][] checkerPieces, Checker e){
//        if(checkerPieces[e.getPiecePositionX()-1][e.getPiecePositionX()+1].getColor().equals("")
//                && checkerPieces[e.getPiecePositionX()-1][e.getPiecePositionX()+1].getColor().equals("black")){
//            return true;
//        }
//        return false;
//    }
//
//    public boolean canCaptureRightBlack(Checker[][] checkerPieces, Checker e){
//        if(checkerPieces[e.getPiecePositionX()+2][e.getPiecePositionX()+2].getColor().equals("")
//                && checkerPieces[e.getPiecePositionX()+1][e.getPiecePositionX()+1].getColor().equals("Red")){
//            return true;
//        }
//        return false;
//    }
//
//    public boolean canCaptureLeftRed(Checker[][] checkerPieces, Checker e){
//        if(checkerPieces[e.getPiecePositionX()-2][e.getPiecePositionX()-2].getColor().equals("")
//                && checkerPieces[e.getPiecePositionX()-2][e.getPiecePositionX()-2].getColor().equals("red")){
//            return true;
//        }
//        return false;
//    }
//
//    public void captureStandard(){
//
//    }
//
//    public void capturePromoted(){
//        //if()
//    }
//
//
//}