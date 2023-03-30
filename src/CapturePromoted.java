import java.util.*;

public class CapturePromoted extends Capture {

    public CapturePromoted() {
    }

    @Override
    public void canCaptureLeftRed(Checker[][] checkerPieces, Checker e) {
        super.canCaptureLeftRed(checkerPieces, e); // checks forward moves and adds to arraylist

        // check what legal backward moves there are using similar logic to capture
        // class

        if (checkerPieces[e.getPiecePositionX() + 2][e.getPiecePositionY() - 2].getColor().equals("")
                && checkerPieces[e.getPiecePositionX() + 1][e.getPiecePositionY() - 1].getColor().equals("black")
                && (e.getPiecePositionX() + 2 <= 7) && (e.getPiecePositionY() - 2 >= 0)) {



            getLegalMovesRed().add((e.getPiecePositionX() + 2) * 10 + e.getPiecePositionY() - 2);
        }
        // super old method and add logic for backwards capture left for red pieces
    }

    // row 5 col 2 row 7 col 4
    @Override
    public void canCaptureRightRed(Checker[][] checkerPieces, Checker e) {
        super.canCaptureRightRed(checkerPieces, e);

        if (checkerPieces[e.getPiecePositionX() + 2][e.getPiecePositionY() + 2].getColor().equals("")
                && checkerPieces[e.getPiecePositionX() + 1][e.getPiecePositionY() + 1].getColor().equals("black")
                && (e.getPiecePositionX() + 2 <= 7) && (e.getPiecePositionY() + 2 <= 7)) {
            getLegalMovesRed().add((e.getPiecePositionX() + 2) * 10 + e.getPiecePositionY() + 2);
        }
    }

    @Override
    // r2 c3 to r0 c5
    public void canCaptureRightBlack(Checker[][] checkerPieces, Checker e) {
        super.canCaptureLeftRed(checkerPieces, e);
        if (checkerPieces[e.getPiecePositionX() - 2][e.getPiecePositionY() + 2].getColor().equals("")
                && checkerPieces[e.getPiecePositionX() - 1][e.getPiecePositionY() + 1].getColor().equals("red")
                && (e.getPiecePositionX() - 2 >= 0) && (e.getPiecePositionY() + 2 <= 7)) {
            getLegalMovesRed().add((e.getPiecePositionX() - 2) * 10 + e.getPiecePositionY() + 2);
        }
    }

    // r2 col3 to r0 col1 y-2 x-2
    @Override
    public void canCaptureLeftBlack(Checker[][] checkerPieces, Checker e) {

        if (checkerPieces[e.getPiecePositionX() - 2][e.getPiecePositionY() - 2].getColor().equals("")
                && checkerPieces[e.getPiecePositionX() - 1][e.getPiecePositionY() - 1].getColor().equals("red")
                && (e.getPiecePositionX() - 2 >= 0) && (e.getPiecePositionY() - 2 >= 0)) {

            getLegalMovesRed().add((e.getPiecePositionX() - 2) * 10 + e.getPiecePositionY() - 2);
        }

    }



}

// finish capturepromoted & movepromoted classes
