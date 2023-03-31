import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Array;
import java.util.ArrayList;
import java.util.Objects;

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
    int rowIndex = -1;
    int colIndex = -1;
    private ImageIcon blank = new ImageIcon("blank.png");
    private ImageIcon black = new ImageIcon("black.png");
    private ImageIcon red = new ImageIcon("red.png");
    private Checker selected;


    CheckerGUI() {
        initializeGui();
        moves = new Move();
        capture = new Capture();
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
                    b.setIcon(icon);
                    b.setEnabled(true);
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
                        showMoves(checkerPieces[row][col]);
                        Object source = e.getSource();
                        JButton clicked = (JButton) source;
                        String desc = ((ImageIcon) clicked.getIcon()).getDescription();
                        if (desc.equals(red.getDescription()) || desc.equals(black.getDescription())) {
                            endMoves();
                            selected = checkerPieces[row][col];
                            showMoves(checkerPieces[row][col]);
                        }
                        if (desc.equals(blank.getDescription())) {
                            if (selected.getColor().equals("red")) {
                                checkerBoardSquares[row][col].setIcon(red);
                                if (capture.canCaptureLeftRed(checkerPieces, selected)) {
                                    checkerBoardSquares[selected.getPiecePositionX() - 1][selected.getPiecePositionY() - 1].setIcon(null);
                                    checkerBoardSquares[selected.getPiecePositionX() - 1][selected.getPiecePositionY() - 1].setEnabled(false);
                                    checkerPieces[selected.getPiecePositionX() - 1][selected.getPiecePositionY() - 1].setColor("");
                                }
                                if (capture.canCaptureRightRed(checkerPieces, selected)) {
                                    checkerBoardSquares[selected.getPiecePositionX() - 1][selected.getPiecePositionY() + 1].setIcon(null);
                                    checkerBoardSquares[selected.getPiecePositionX() - 1][selected.getPiecePositionY() + 1].setEnabled(false);
                                    checkerPieces[selected.getPiecePositionX() - 1][selected.getPiecePositionY() + 1].setColor("");
                                }
                                checkerPieces[row][col].setColor("red");
                            } else if (selected.getColor().equals("black")) {
                                checkerBoardSquares[row][col].setIcon(black);
                                if (capture.canCaptureLeftBlack(checkerPieces, selected)) {
                                    checkerBoardSquares[selected.getPiecePositionX() + 1][selected.getPiecePositionY() - 1].setIcon(null);
                                    checkerBoardSquares[selected.getPiecePositionX() + 1][selected.getPiecePositionY() - 1].setEnabled(false);
                                    checkerPieces[selected.getPiecePositionX() + 1][selected.getPiecePositionY() - 1].setColor("");
                                }
                                if (capture.canCaptureRightBlack(checkerPieces, selected)) {
                                    checkerBoardSquares[selected.getPiecePositionX() + 1][selected.getPiecePositionY() + 1].setIcon(null);
                                    checkerBoardSquares[selected.getPiecePositionX() + 1][selected.getPiecePositionY() + 1].setEnabled(false);
                                    checkerPieces[selected.getPiecePositionX() + 1][selected.getPiecePositionY() + 1].setColor("");
                                }
                                checkerPieces[row][col].setColor("black");
                            }
                            checkerPieces[row][col].setColor(selected.getColor());
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
//        for (int i = 0; i < checkerPieces.length; i ++){
//            for (int x = 0; x < checkerPieces[0].length; x ++){
//                System.out.print(checkerPieces[i][x].getColor() + " ");
//            }
//            System.out.println();
//        }
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
        capture.resetMoves();
    }

    public void showMoves(Checker e) {

//        System.out.println("CAN Capture right red " + capture.canCaptureRightRed(checkerPieces, e));
//        System.out.println("CAN Capture left red " + capture.canCaptureLeftRed(checkerPieces, e));
//        System.out.println("CAN Capture right black " + capture.canCaptureRightBlack(checkerPieces, e));
//        System.out.println("CAN Capture left black " + capture.canCaptureLeftBlack(checkerPieces, e));
//        if (moves.isBlackMove()) {
//           disc
//           for (int i = 0; i < 8; i++) {
//                for (int j = 0; j < 8; j++) {
//                    if (checkerPieces[i][j].getColor().equals("red")) {
//                        checkerBoardSquares[i][j].setEnabled(false);
//                    }
//                    if (checkerPieces[i][j].getColor().equals("black")) {
//                        checkerBoardSquares[i][j].setEnabled(true);
//                    }
//                }
//            }
//        } else {
//            for (int i = 0; i < 8; i++) {
//                for (int j = 0; j < 8; j++) {
//                    if (checkerPieces[i][j].getColor().equals("black")) {
//                        checkerBoardSquares[i][j].setEnabled(false);
//                    }
//                    if (checkerPieces[i][j].getColor().equals("red")) {
//                        checkerBoardSquares[i][j].setEnabled(true);
//                    }
//                }
//            }
//        }
        if (e.getColor().equals("red")) {

            if (moves.canMoveLeftRed(checkerPieces, e)) {
                checkerBoardSquares[e.getPiecePositionX() - 1][e.getPiecePositionY() - 1].setEnabled(true);
                checkerBoardSquares[e.getPiecePositionX() - 1][e.getPiecePositionY() - 1].setIcon(blank);
            }
            if (moves.canMoveRightRed(checkerPieces, e)) {
                checkerBoardSquares[e.getPiecePositionX() - 1][e.getPiecePositionY() + 1].setEnabled(true);
                checkerBoardSquares[e.getPiecePositionX() - 1][e.getPiecePositionY() + 1].setIcon(blank);
            }
        } else if (e.getColor().equals("black")) {
            if (moves.canMoveLeftBlack(checkerPieces, e)) {
                checkerBoardSquares[e.getPiecePositionX() + 1][e.getPiecePositionY() - 1].setEnabled(true);
                checkerBoardSquares[e.getPiecePositionX() + 1][e.getPiecePositionY() - 1].setIcon(blank);
            }
            if (moves.canMoveRightBlack(checkerPieces, e)) {
                checkerBoardSquares[e.getPiecePositionX() + 1][e.getPiecePositionY() + 1].setEnabled(true);
                checkerBoardSquares[e.getPiecePositionX() + 1][e.getPiecePositionY() + 1].setIcon(blank);
            }
        }
        ArrayList<Integer> temp = captureMoves(e);
        for (int i = 0; i < temp.size(); i++) {
            checkerBoardSquares[temp.get(i) / 10][temp.get(i) % 10].setEnabled(true);
            checkerBoardSquares[temp.get(i) / 10][temp.get(i) % 10].setIcon(blank);
        }

    }

    public ArrayList<Integer> captureMoves(Checker e) {
        ArrayList<Integer> legalMoves = new ArrayList<>();
        if (e.getColor().equals("red")) {
            capture.canCaptureLeftRed(checkerPieces, e);
            capture.canCaptureRightRed(checkerPieces, e);
            legalMoves = capture.getLegalMovesRed();
        } else if (e.getColor().equals("black")) {
            capture.canCaptureLeftBlack(checkerPieces, e);
            capture.canCaptureRightBlack(checkerPieces, e);
            legalMoves = capture.getLegalMovesBlack();
        }
        return legalMoves;
    }

    public final JComponent getGui() {
        return gui;
    }
}