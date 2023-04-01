import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class CheckerGUI {
    private Color redBackground = new Color(255, 0, 0, 255);
    private Color blackBackGround = new Color(0, 0, 0, 255);
    private final JPanel gui = new JPanel(new BorderLayout(3, 3));
    private JButton[][] checkerBoardSquares = new JButton[8][8];
    private Checker[][] checkerPieces = new Checker[8][8];
    private JPanel checkerBoard;
    private static final String COLS = "ABCDEFGH";
    private Move moves;
    private Capture capture;
    private CapturePromoted capturePromoted;
    private MovePromoted movePromoted;
    private int rowIndex = -1;
    private int colIndex = -1;
    private ImageIcon blank = new ImageIcon("blank.png");
    private ImageIcon black = new ImageIcon("black.png");
    private ImageIcon red = new ImageIcon("red.png");
    private ImageIcon blackKing = new ImageIcon("blackKing.png");
    private ImageIcon redKing = new ImageIcon("redKing.png");
    private Checker selected;
    private int turnNum = 1;


    CheckerGUI() {
        initializeGui();
        moves = new Move();
        capture = new Capture();
        capturePromoted = new CapturePromoted();
        movePromoted = new MovePromoted();
    }

    public final void initializeGui() {
        checkerBoard = new JPanel(new GridLayout(0, 9)) {
            @Override
            public final Dimension getPreferredSize() {
                return new Dimension(900, 900);
            }
        };
        // Set the BG to be ochre
        Color ochre = new Color(204, 119, 34);
        checkerBoard.setBackground(ochre);
        JPanel boardConstrain = new JPanel(new GridBagLayout());
        boardConstrain.setBackground(ochre);
        boardConstrain.add(checkerBoard);
        gui.add(boardConstrain);

        Insets buttonMargin = new Insets(0, 0, 0, 0);
        for (int i = 0; i < checkerBoardSquares.length; i++) {
            for (int j = 0; j < checkerBoardSquares[i].length; j++) {
                JButton b = new JButton();
                final int row = i;
                final int col = j;
                b.setMargin(buttonMargin);
                b.setEnabled(false);
                if ((j % 2 == 1 && i % 2 == 1) || (j % 2 == 0 && i % 2 == 0)) {
                    b.setBackground(redBackground);
                } else {
                    b.setBackground(blackBackGround);
                }
                if (i < 3 && (i + j) % 2 == 1) {
                    ImageIcon icon = new ImageIcon("black.png");
                    b.setIcon(icon);
                    b.setEnabled(true);
                    checkerPieces[i][j] = new Checker("black", i, j, false);
                } else if (i > 4 && (i + j) % 2 == 1) {
                    ImageIcon icon = new ImageIcon("red.png");
                    b.setDisabledIcon(red);
                    b.setIcon(icon);
                    b.setEnabled(false);
                    checkerPieces[i][j] = new Checker("red", i, j, false);
                } else {
                    checkerPieces[i][j] = new Checker("", i, j, false);
                }
                b.setBorderPainted(false);
                b.setFocusPainted(false);
                b.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        rowIndex = row;
                        colIndex = col;
                        promote();
                        showMoves(checkerPieces[row][col]);
                        Object source = e.getSource();
                        JButton clicked = (JButton) source;
                        String desc = ((ImageIcon) clicked.getIcon()).getDescription();
                        if (desc.equals(red.getDescription()) || desc.equals(black.getDescription())
                                || desc.equals(redKing.getDescription()) || desc.equals(blackKing.getDescription())) {
                            endMoves();
                            selected = checkerPieces[row][col];
                            if (selected.isPromoted()) {
                                showPromotedMoves(checkerPieces[row][col]);
                            } else {
                                showMoves(checkerPieces[row][col]);
                            }
                        }
                        if (desc.equals(blank.getDescription())) {
                            if (selected.getColor().equals("red")) {
                                if (selected.isPromoted()) {
                                    checkerPieces[row][col].setPromoted(true);
                                    checkerBoardSquares[row][col].setIcon(redKing);
                                    if (capturePromoted.canCaptureLeftRed(checkerPieces, selected)){
                                        if (rowIndex == selected.getPiecePositionX() + 2 && colIndex == selected.getPiecePositionY() - 2){
                                            checkerBoardSquares[selected.getPiecePositionX() + 1][selected.getPiecePositionY() - 1].setIcon(null);
                                            checkerBoardSquares[selected.getPiecePositionX() + 1][selected.getPiecePositionY() - 1].setEnabled(false);
                                            checkerPieces[selected.getPiecePositionX() + 1][selected.getPiecePositionY() - 1].setColor("");
                                            checkerPieces[selected.getPiecePositionX() + 1][selected.getPiecePositionY() - 1].setPromoted(false);
                                        }
                                    }
                                    if (capturePromoted.canCaptureRightRed(checkerPieces, selected)){
                                        if (rowIndex == selected.getPiecePositionX() + 2 && colIndex == selected.getPiecePositionY() + 2){
                                            checkerBoardSquares[selected.getPiecePositionX() + 1][selected.getPiecePositionY() + 1].setIcon(null);
                                            checkerBoardSquares[selected.getPiecePositionX() + 1][selected.getPiecePositionY() + 1].setEnabled(false);
                                            checkerPieces[selected.getPiecePositionX() + 1][selected.getPiecePositionY() + 1].setColor("");
                                            checkerPieces[selected.getPiecePositionX() + 1][selected.getPiecePositionY() + 1].setPromoted(false);
                                        }
                                    }
                                }else {
                                    checkerBoardSquares[row][col].setIcon(red);
                                }
                                if (capture.canCaptureLeftRed(checkerPieces, selected)
                                        && rowIndex == selected.getPiecePositionX() - 2 && colIndex == selected.getPiecePositionY() - 2) {
                                    checkerBoardSquares[selected.getPiecePositionX() - 1][selected.getPiecePositionY() - 1].setIcon(null);
                                    checkerBoardSquares[selected.getPiecePositionX() - 1][selected.getPiecePositionY() - 1].setEnabled(false);
                                    checkerPieces[selected.getPiecePositionX() - 1][selected.getPiecePositionY() - 1].setColor("");
                                    checkerPieces[selected.getPiecePositionX() - 1][selected.getPiecePositionY() - 1].setPromoted(false);
                                }
                                if (capture.canCaptureRightRed(checkerPieces, selected)
                                        && rowIndex == selected.getPiecePositionX() - 2 && colIndex == selected.getPiecePositionY() + 2) {
                                    checkerBoardSquares[selected.getPiecePositionX() - 1][selected.getPiecePositionY() + 1].setIcon(null);
                                    checkerBoardSquares[selected.getPiecePositionX() - 1][selected.getPiecePositionY() + 1].setEnabled(false);
                                    checkerPieces[selected.getPiecePositionX() - 1][selected.getPiecePositionY() + 1].setColor("");
                                    checkerPieces[selected.getPiecePositionX() - 1][selected.getPiecePositionY() + 1].setPromoted(false);
                                }
                                checkerPieces[row][col].setColor("red");
                            } else if (selected.getColor().equals("black")) {
                                if (selected.isPromoted()) {
                                    checkerPieces[row][col].setPromoted(true);
                                    checkerBoardSquares[row][col].setIcon(blackKing);
                                    if (capturePromoted.canCaptureLeftBlack(checkerPieces, selected)){
                                        if (rowIndex == selected.getPiecePositionX() - 2 && colIndex == selected.getPiecePositionY() - 2){
                                            checkerBoardSquares[selected.getPiecePositionX() - 1][selected.getPiecePositionY() - 1].setIcon(null);
                                            checkerBoardSquares[selected.getPiecePositionX() - 1][selected.getPiecePositionY() - 1].setEnabled(false);
                                            checkerPieces[selected.getPiecePositionX() - 1][selected.getPiecePositionY() - 1].setColor("");
                                            checkerPieces[selected.getPiecePositionX() - 1][selected.getPiecePositionY() - 1].setPromoted(false);
                                        }
                                    }
                                    if (capturePromoted.canCaptureRightBlack(checkerPieces, selected)){
                                        if (rowIndex == selected.getPiecePositionX() - 2 && colIndex == selected.getPiecePositionY() + 2){
                                            checkerBoardSquares[selected.getPiecePositionX() - 1][selected.getPiecePositionY() + 1].setIcon(null);
                                            checkerBoardSquares[selected.getPiecePositionX() - 1][selected.getPiecePositionY() + 1].setEnabled(false);
                                            checkerPieces[selected.getPiecePositionX() - 1][selected.getPiecePositionY() + 1].setColor("");
                                            checkerPieces[selected.getPiecePositionX() - 1][selected.getPiecePositionY() + 1].setPromoted(false);
                                        }
                                    }
                                } else {
                                    checkerBoardSquares[row][col].setIcon(black);
                                }
                                if (capture.canCaptureLeftBlack(checkerPieces, selected)
                                        && rowIndex == selected.getPiecePositionX() + 2 && colIndex == selected.getPiecePositionY() - 2) {
                                    checkerBoardSquares[selected.getPiecePositionX() + 1][selected.getPiecePositionY() - 1].setIcon(null);
                                    checkerBoardSquares[selected.getPiecePositionX() + 1][selected.getPiecePositionY() - 1].setEnabled(false);
                                    checkerPieces[selected.getPiecePositionX() + 1][selected.getPiecePositionY() - 1].setColor("");
                                    checkerPieces[selected.getPiecePositionX() + 1][selected.getPiecePositionY() - 1].setPromoted(false);
                                }
                                if (capture.canCaptureRightBlack(checkerPieces, selected)
                                        && rowIndex == selected.getPiecePositionX() + 2 && colIndex == selected.getPiecePositionY() + 2) {
                                    checkerBoardSquares[selected.getPiecePositionX() + 1][selected.getPiecePositionY() + 1].setIcon(null);
                                    checkerBoardSquares[selected.getPiecePositionX() + 1][selected.getPiecePositionY() + 1].setEnabled(false);
                                    checkerPieces[selected.getPiecePositionX() + 1][selected.getPiecePositionY() + 1].setColor("");
                                    checkerPieces[selected.getPiecePositionX() + 1][selected.getPiecePositionY() + 1].setPromoted(false);
                                }
                                checkerPieces[row][col].setColor("black");
                            }
                            turnNum++;
                            switchTurns();
                            checkerPieces[row][col].setColor(selected.getColor());
                            if (selected.isPromoted()) {
                                checkerPieces[selected.getPiecePositionX()][selected.getPiecePositionY()].setPromoted(false);
                                checkerPieces[row][col].setPromoted(true);

                            }
                            checkerPieces[selected.getPiecePositionX()][selected.getPiecePositionY()].setColor("");
                            checkerBoardSquares[selected.getPiecePositionX()][selected.getPiecePositionY()].setIcon(null);
                            checkerBoardSquares[selected.getPiecePositionX()][selected.getPiecePositionY()].setEnabled(false);
                            endMoves();
                        }
                    }
                });
                checkerBoardSquares[i][j] = b;
            }
        }

        checkerBoard.add(new JLabel(""));
        for (int ii = 0; ii < 8; ii++) {
            checkerBoard.add(
                    new JLabel(COLS.substring(ii, ii + 1),
                            SwingConstants.CENTER));
        }
        for (int ii = 0; ii < 8; ii++) {
            for (int jj = 0; jj < 8; jj++) {
                switch (jj) {
                    case 0:
                        checkerBoard.add(new JLabel("" + (9 - (ii + 1)),
                                SwingConstants.CENTER));
                    default:
                        checkerBoard.add(checkerBoardSquares[ii][jj]);
                }
            }
        }
    }

    public void endMoves() {
        for (int i = 0; i < checkerBoardSquares.length; i++) {
            for (int x = 0; x < checkerBoardSquares.length; x++) {
                if (checkerBoardSquares[i][x].getIcon() != null) {
                    String temp = ((ImageIcon) checkerBoardSquares[i][x].getIcon()).getDescription();
                    if (temp.equals(blank.getDescription())) {
                        checkerBoardSquares[i][x].setIcon(null);
                        checkerBoardSquares[i][x].setEnabled(false);
                    }
                }
            }
            moves.switchTurns();
        }
        moves.resetMoves();
        movePromoted.resetMoves();
        capture.resetMoves();
        capturePromoted.resetMoves();
    }

    private void switchTurns() {
        promote();
        if (turnNum % 2 != 0) {
            for (int row = 0; row < 8; row++) {
                for (int col = 0; col < 8; col++) {
                    if (checkerPieces[row][col].getColor().equals("red")) {
                        if (checkerPieces[row][col].isPromoted()) {
                            checkerBoardSquares[row][col].setIcon(redKing);
                            checkerBoardSquares[row][col].setDisabledIcon(redKing);
                        } else {
                            checkerBoardSquares[row][col].setDisabledIcon(red);
                        }
                        checkerBoardSquares[row][col].setEnabled(false);
                    }
                    if (checkerPieces[row][col].getColor().equals("black")) {
                        checkerBoardSquares[row][col].setEnabled(true);
                    }
                }
            }
        } else {
            for (int row = 0; row < 8; row++) {
                for (int col = 0; col < 8; col++) {
                    if (checkerPieces[row][col].getColor().equals("black")) {
                        if (checkerPieces[row][col].isPromoted()) {
                            checkerBoardSquares[row][col].setIcon(blackKing);
                            checkerBoardSquares[row][col].setDisabledIcon(blackKing);
                        } else {
                            checkerBoardSquares[row][col].setDisabledIcon(black);
                        }
                        checkerBoardSquares[row][col].setEnabled(false);
                    }
                    if (checkerPieces[row][col].getColor().equals("red")) {
                        checkerBoardSquares[row][col].setEnabled(true);
                    }
                }
            }
        }
    }

    private void promote() {
        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {
                if (checkerPieces[row][col].getColor().equals("red")) {
                    if (row == 0) {
                        checkerBoardSquares[row][col].setIcon(redKing);
                        checkerBoardSquares[row][col].setEnabled(true);
                        checkerPieces[row][col].setPromoted(true);
                    }
                }
                if (checkerPieces[row][col].getColor().equals("black")) {
                    if (row == 7) {
                        checkerBoardSquares[row][col].setIcon(blackKing);
                        checkerBoardSquares[row][col].setEnabled(true);
                        checkerPieces[row][col].setPromoted(true);
                    }
                }
            }
        }
    }

    public void showPromotedMoves(Checker e) {
        ArrayList<Integer> temp = new ArrayList<>();
        if (e.getColor().equals("red")) {
            movePromoted.canMoveRightRed(checkerPieces, e);
            movePromoted.canMoveLeftRed(checkerPieces, e);
            temp = movePromoted.getLegalMovesRed();
        } else if (e.getColor().equals("black")) {
            movePromoted.canMoveLeftBlack(checkerPieces, e);
            movePromoted.canMoveRightBlack(checkerPieces, e);
            temp = movePromoted.getLegalMovesBlack();
        }
        for (int i = 0; i < temp.size(); i++) {
            checkerBoardSquares[temp.get(i) / 10][temp.get(i) % 10].setEnabled(true);
            checkerBoardSquares[temp.get(i) / 10][temp.get(i) % 10].setIcon(blank);
        }
        temp = captureMoves(e);
        for (int i = 0; i < temp.size(); i++) {
            checkerBoardSquares[temp.get(i) / 10][temp.get(i) % 10].setEnabled(true);
            checkerBoardSquares[temp.get(i) / 10][temp.get(i) % 10].setIcon(blank);
        }
    }

    public void showMoves(Checker e) {
//        if (movePromoted.winCondition(checkerPieces)) {
            ArrayList<Integer> temp = new ArrayList<>();
            if (e.getColor().equals("red")) {
                moves.canMoveLeftRed(checkerPieces, e);
                moves.canMoveRightRed(checkerPieces, e);
                temp = moves.getLegalMovesRed();
            } else if (e.getColor().equals("black")) {
                moves.canMoveLeftBlack(checkerPieces, e);
                moves.canMoveRightBlack(checkerPieces, e);
                temp = moves.getLegalMovesBlack();
            }
            for (int i = 0; i < temp.size(); i++) {
                checkerBoardSquares[temp.get(i) / 10][temp.get(i) % 10].setEnabled(true);
                checkerBoardSquares[temp.get(i) / 10][temp.get(i) % 10].setIcon(blank);
            }
            temp = captureMoves(e);
            for (int i = 0; i < temp.size(); i++) {
                checkerBoardSquares[temp.get(i) / 10][temp.get(i) % 10].setEnabled(true);
                checkerBoardSquares[temp.get(i) / 10][temp.get(i) % 10].setIcon(blank);
            }
//        }
    }

    public ArrayList<Integer> captureMoves(Checker e) {
        ArrayList<Integer> legalMoves = new ArrayList<>();
        if (e.getColor().equals("red")) {
            if (e.isPromoted) {
                capturePromoted.canCaptureLeftRed(checkerPieces, e);
                capturePromoted.canCaptureRightRed(checkerPieces, e);
                legalMoves = capturePromoted.getLegalMovesRed();
            } else {
                capture.canCaptureLeftRed(checkerPieces, e);
                capture.canCaptureRightRed(checkerPieces, e);
                legalMoves = capture.getLegalMovesRed();
            }
        } else if (e.getColor().equals("black")) {
            if (e.isPromoted) {
                capturePromoted.canCaptureLeftBlack(checkerPieces, e);
                capturePromoted.canCaptureRightBlack(checkerPieces, e);
                legalMoves = capturePromoted.getLegalMovesBlack();
            } else {
                capture.canCaptureLeftBlack(checkerPieces, e);
                capture.canCaptureRightBlack(checkerPieces, e);
                legalMoves = capture.getLegalMovesBlack();
            }
        }
        return legalMoves;
    }

    public final JComponent getGui() {
        return gui;
    }
}