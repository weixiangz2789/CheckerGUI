public class CapturePromoted extends Capture {
    private int piecesCaptured;

    public CapturePromoted(int piecesCaptured) {
        this.piecesCaptured = piecesCaptured;
    }

    @Override
    public boolean canCaptureLeftRed(Checker[][] checkerPieces, Checker e) {
        super.canCaptureLeftRed(checkerPieces, e); // checks forward moves and adds to arraylist
        // check what legal backward moves there are using similar logic to capture
        // class
        if ((e.getPiecePositionX() - 2 >= 0 && e.getPiecePositionY() - 2 >= 0)
                && checkerPieces[e.getPiecePositionX() + 2][e.getPiecePositionY() - 2].getColor().equals("")
                && checkerPieces[e.getPiecePositionX() + 1][e.getPiecePositionY() - 1].getColor().equals("black")
                && (e.getPiecePositionX() + 2 <= 7) && (e.getPiecePositionY() - 2 >= 0)) {

            getLegalMovesRed().add((e.getPiecePositionX() + 2) * 10 + e.getPiecePositionY() - 2);
            return true;
        }
        return false;
    }

    // row 5 col 2 row 7 col 4
    @Override
    public boolean canCaptureRightRed(Checker[][] checkerPieces, Checker e) {
        super.canCaptureRightRed(checkerPieces, e);
        if ((e.getPiecePositionX() + 2 <= 7) && (e.getPiecePositionY() + 2 <= 7)
                && checkerPieces[e.getPiecePositionX() + 2][e.getPiecePositionY() + 2].getColor().equals("")
                && checkerPieces[e.getPiecePositionX() + 1][e.getPiecePositionY() + 1].getColor().equals("black")
                && (e.getPiecePositionX() + 2 <= 7) && (e.getPiecePositionY() + 2 <= 7)) {
            getLegalMovesRed().add((e.getPiecePositionX() + 2) * 10 + e.getPiecePositionY() + 2);
            return true;
        }
        return false;
    }

    @Override
    // r2 c3 to r0 c5
    public boolean canCaptureRightBlack(Checker[][] checkerPieces, Checker e) {
        super.canCaptureRightBlack(checkerPieces, e);
        if ((e.getPiecePositionX() - 2 >= 0  && (e.getPiecePositionY() + 2 <= 7))
                && checkerPieces[e.getPiecePositionX() - 2][e.getPiecePositionY() + 2].getColor().equals("")
                && checkerPieces[e.getPiecePositionX() - 1][e.getPiecePositionY() + 1].getColor().equals("red")
                && (e.getPiecePositionX() - 2 >= 0) && (e.getPiecePositionY() + 2 <= 7)) {
            getLegalMovesBlack().add((e.getPiecePositionX() - 2) * 10 + e.getPiecePositionY() + 2);
            return true;
        }
        return false;
    }

    // r2 col3 to r0 col1 y-2 x-2
    @Override
    public boolean canCaptureLeftBlack(Checker[][] checkerPieces, Checker e) {
        super.canCaptureLeftBlack(checkerPieces, e);
        if ((e.getPiecePositionX() - 2 >= 0) && (e.getPiecePositionY() - 2 >= 0)
                && checkerPieces[e.getPiecePositionX() - 2][e.getPiecePositionY() - 2].getColor().equals("")
                && checkerPieces[e.getPiecePositionX() - 1][e.getPiecePositionY() - 1].getColor().equals("red")
                && (e.getPiecePositionX() - 2 >= 0) && (e.getPiecePositionY() - 2 >= 0)) {

            getLegalMovesBlack().add((e.getPiecePositionX() - 2) * 10 + e.getPiecePositionY() - 2);
            return true;
        }
        return false;
    }

    public int getPiecesCapturedCount(){
        return piecesCaptured;
    }

    public void addCaptureCount(){
        piecesCaptured++;
    }


}

