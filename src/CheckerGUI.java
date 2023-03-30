import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Objects;

public class CheckerGUI  {
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
//                b.addActionListener(this);
                b.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        rowIndex = row;
                        colIndex = col;
                        showMoves(checkerPieces[row][col]);
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
            System.out.println(moves.canMoveRightBlack(checkerPieces, e));
            System.out.println(moves.canMoveLeftBlack(checkerPieces, e));
            System.out.println(e.getPiecePositionX());
            System.out.println(e.getPiecePositionY());
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

    public final JComponent getGui() {
        return gui;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        ImageIcon blank = new ImageIcon("blank.png");
        ImageIcon black = new ImageIcon("black.png");
        ImageIcon red = new ImageIcon("red.png");
        Object source = e.getSource();
        JButton clicked = (JButton) source;
        int idx1 = 0;
        int idx2 = 0;
        String desc = ((ImageIcon) clicked.getIcon()).getDescription();
        for (int i = 0; i < checkerBoardSquares.length; i ++){
            for (int j = 0; j < checkerBoardSquares[0].length; j ++){
                if (checkerBoardSquares[i][j] == clicked){
                    idx1 = i;
                    idx2 = j;
                }
            }
        }
        if (desc.equals(blank.getDescription())) {
            System.out.println("blank");
        }
        else if (desc.equals(red.getDescription()) || desc.equals(black.getDescription())) {
            System.out.println(idx1 + " " + idx2);
            showMoves(checkerPieces[idx1][idx2]);
        }
    }
}