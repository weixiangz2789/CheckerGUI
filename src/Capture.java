import java.util.ArrayList;

public class Capture{
    private ArrayList<Integer> legalMovesRed;
    private ArrayList<Integer> legalMovesBlack;

    private int blackPieces = 12;
    private int redPieces = 12;

    public Capture(){
        legalMovesRed = new ArrayList<>();
        legalMovesBlack = new ArrayList<>();
    }


    public ArrayList<Integer> getLegalMovesRed() {
        return legalMovesRed;
    }

    public ArrayList<Integer> getLegalMovesBlack() {
        return legalMovesBlack;
    }


    public boolean canCaptureLeftRed(Checker[][] checkerPieces, Checker e){
        if((e.getPiecePositionX()-2 >= 0 ) && (e.getPiecePositionY()-2 >=0)
                && checkerPieces[e.getPiecePositionX()-2][e.getPiecePositionY()-2].getColor().equals("")
                && checkerPieces[e.getPiecePositionX()-1][e.getPiecePositionY()-1].getColor().equals("black")){
            legalMovesRed.add((e.getPiecePositionX() - 2) * 10 + e.getPiecePositionY() - 2);
            return true;

        }
        return false;
    }

    public boolean canCaptureRightRed(Checker[][] checkerPieces, Checker e){
        if((e.getPiecePositionX()-2 >= 0 && (e.getPiecePositionY()+2 <=7))
                && checkerPieces[e.getPiecePositionX()-2][e.getPiecePositionY()+2].getColor().equals("")
                && checkerPieces[e.getPiecePositionX()-1][e.getPiecePositionY()+1].getColor().equals("black")){
            legalMovesRed.add((e.getPiecePositionX() - 2) * 10 + e.getPiecePositionY() + 2);
            return true;
        }
        return false;
    }

    public boolean canCaptureRightBlack(Checker[][] checkerPieces, Checker e){
        if((e.getPiecePositionX()+2 <= 7 ) && (e.getPiecePositionY()+2 <=7)
                && checkerPieces[e.getPiecePositionX()+2][e.getPiecePositionY()+2].getColor().equals("")
                && checkerPieces[e.getPiecePositionX()+1][e.getPiecePositionY()+1].getColor().equals("red")){
            legalMovesBlack.add((e.getPiecePositionX() + 2) * 10 + e.getPiecePositionY() + 2);
            return true;
        }
        return false;
    }

    public boolean canCaptureLeftBlack(Checker[][] checkerPieces, Checker e){
        if((e.getPiecePositionX()+2 <= 7  && e.getPiecePositionY()-2 >=0)
                && checkerPieces[e.getPiecePositionX()+2][e.getPiecePositionY()-2].getColor().equals("")
                && checkerPieces[e.getPiecePositionX()+1][e.getPiecePositionX()-1].getColor().equals("red")){
            legalMovesBlack.add((e.getPiecePositionX() + 2) * 10 + e.getPiecePositionY() - 2);
            return true;
        }
        return false;
    }

//public void captureStandard(Checker e){

    // if(e.getColor().equals("red")) {
    //     if(canCaptureRightRed(//give prameter)) {
    //         System.out.println((e.getPiecePositionX() - 2) +","+ (e.getPiecePositionY() + 2 ));
    //     }
    //     if(canCaptureLeftRed()) {
    //         System.out.println(e.getPiecePositionX() - 2 +","+e.getPiecePositionY() - 2 );
    //     }
    // }else{
    //   if(canCaptureRightBlack()){
    //     System.out.println(e.getPiecePositionX() + 2 +","+e.getPiecePositionY()+2 );
    //   }
    //   if(canCaptureLeftBlack()){
    //     System.out.println(e.getPiecePositionX() + 2 +","+e.getPiecePositionY() - 2 );
    //   }
    // }
    // must give in a New piece clicked in order for it to give possible positions or else it would not work
//}



//    public void winCondition() {
//        if(redPieces == 0 || blackPieces == 0) {
//            System.exit(0); //stub for now
//        }
//        for(int row = )
//    }


}