import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Objects;

public class CheckerGUI {
    private Color red = new Color(255, 0, 0, 255);
    private Color black = new Color(0, 0, 0, 255);
    private final JPanel gui = new JPanel(new BorderLayout(3, 3));
    private JButton[][] checkerBoardSquares = new JButton[8][8];
    private Checker[][] checkerPieces = new Checker[8][8];
    private JPanel checkerBoard;
    private static final String COLS = "ABCDEFGH";
    private Move moves;
    int rowIndex = -1;
    int colIndex = -1;
    private Checker selected;


    CheckerGUI() {
        initializeGui();
        moves = new Move(false);
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
//                b.setText(Integer.toString(i) + Integer.toString(j));
                if ((j % 2 == 1 && i % 2 == 1) || (j % 2 == 0 && i % 2 == 0)) {
                    b.setBackground(red);
                } else {
                    b.setBackground(black);
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
                        ImageIcon blank = new ImageIcon("blank.png");
                        ImageIcon black = new ImageIcon("black.png");
                        ImageIcon red = new ImageIcon("red.png");
                        Object source = e.getSource();
                        JButton clicked = (JButton) source;
                        String desc = ((ImageIcon) clicked.getIcon()).getDescription();
                        if (desc.equals(red.getDescription()) || desc.equals(black.getDescription())) {
//                            System.out.println(row + " " + col);
                            selected = checkerPieces[row][col];
                            showMoves(checkerPieces[row][col]);
                        }
                        if (desc.equals(blank.getDescription())){
                            System.out.println("blank");
                            checkerPieces[row][col].setColor("red");
                            checkerBoardSquares[row][col].setIcon(red);
                            checkerBoardSquares[selected.getPiecePositionX()][selected.getPiecePositionY()].setIcon(null);
                            checkerPieces[selected.getPiecePositionX()][selected.getPiecePositionY()].setColor("");
                            endMoves(selected);
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

    public void showMoves(Checker e) {
        ImageIcon icon = new ImageIcon("blank.png");
        if (e.getColor().equals("red")) {

            if (moves.canMoveLeftRed(checkerPieces, e)) {
                checkerBoardSquares[e.getPiecePositionX() - 1][e.getPiecePositionY() - 1].setEnabled(true);
                checkerBoardSquares[e.getPiecePositionX() - 1][e.getPiecePositionY() - 1].setIcon(icon);
            }
            if (moves.canMoveRightRed(checkerPieces, e)) {
                checkerBoardSquares[e.getPiecePositionX() - 1][e.getPiecePositionY() + 1].setEnabled(true);
                checkerBoardSquares[e.getPiecePositionX() - 1][e.getPiecePositionY() + 1].setIcon(icon);
            }
        } else if (e.getColor().equals("black")) {
            if (moves.canMoveRightBlack(checkerPieces, e)) {
                checkerBoardSquares[e.getPiecePositionX() + 1][e.getPiecePositionY() - 1].setEnabled(true);
                checkerBoardSquares[e.getPiecePositionX() + 1][e.getPiecePositionY() - 1].setIcon(icon);
            }
            if (moves.canMoveRightBlack(checkerPieces, e)) {
                checkerBoardSquares[e.getPiecePositionX() + 1][e.getPiecePositionY() + 1].setEnabled(true);
                checkerBoardSquares[e.getPiecePositionX() + 1][e.getPiecePositionY() + 1].setIcon(icon);
            }
        }
    }
    public void endMoves(Checker e) {
        if (e.getColor().equals("red")) {
            if (moves.canMoveLeftRed(checkerPieces, e)) {
                checkerBoardSquares[e.getPiecePositionX() - 1][e.getPiecePositionY() - 1].setEnabled(false);
                checkerBoardSquares[e.getPiecePositionX() - 1][e.getPiecePositionY() - 1].setIcon(null);
            }
            if (moves.canMoveRightRed(checkerPieces, e)) {
                checkerBoardSquares[e.getPiecePositionX() - 1][e.getPiecePositionY() + 1].setEnabled(false);
                checkerBoardSquares[e.getPiecePositionX() - 1][e.getPiecePositionY() + 1].setIcon(null);
            }
        } else if (e.getColor().equals("black")) {
            if (moves.canMoveRightBlack(checkerPieces, e)) {
                checkerBoardSquares[e.getPiecePositionX() + 1][e.getPiecePositionY() - 1].setEnabled(false);
                checkerBoardSquares[e.getPiecePositionX() + 1][e.getPiecePositionY() - 1].setIcon(null);
            }
            if (moves.canMoveRightBlack(checkerPieces, e)) {
                checkerBoardSquares[e.getPiecePositionX() + 1][e.getPiecePositionY() + 1].setEnabled(false);
                checkerBoardSquares[e.getPiecePositionX() + 1][e.getPiecePositionY() + 1].setIcon(null);
            }
        }
    }

    public final JComponent getGui() {
        return gui;
    }
}