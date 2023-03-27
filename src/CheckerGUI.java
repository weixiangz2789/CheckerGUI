import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;

public class CheckerGUI {
    private HashMap<Integer, Checker> map = new HashMap<Integer, Checker>();
    private Color red = new Color(255,0,0,255);
    private Color black = new Color(0,0,0,255);
    private final JPanel gui = new JPanel(new BorderLayout(3, 3));
    private JButton[][] checkerBoardSquares = new JButton[8][8];
    private Checker[][] checkerPieces = new Checker[8][8];
    private JPanel checkerBoard;
    private static final String COLS = "ABCDEFGH";

    CheckerGUI() {
        initializeGui();
    }

    public final void initializeGui() {
        checkerBoard = new JPanel(new GridLayout(0, 9)) {
            @Override
            public final Dimension getPreferredSize() {
                return new Dimension(900,900);
            }
        };
        // Set the BG to be ochre
        Color ochre = new Color(204,119,34);
        checkerBoard.setBackground(ochre);
        JPanel boardConstrain = new JPanel(new GridBagLayout());
        boardConstrain.setBackground(ochre);
        boardConstrain.add(checkerBoard);
        gui.add(boardConstrain);

        Insets buttonMargin = new Insets(0, 0, 0, 0);
        for (int i = 0; i < checkerBoardSquares.length; i++) {
            for (int j = 0; j < checkerBoardSquares[i].length; j++) {
                JButton b = new JButton();
                b.setMargin(buttonMargin);
                b.setEnabled(false);
                if ((j % 2 == 1 && i % 2 == 1) || (j % 2 == 0 && i % 2 == 0)) {
                    b.setBackground(red);
                } else {
                    b.setBackground(black);
                }
                if (i < 3 && (i+j) % 2 == 1){
                    ImageIcon icon = new ImageIcon("black.png");
                    b.setIcon(icon);
                    b.setEnabled(true);
                    checkerPieces[i][j] = new Checker("black", i, j);
                }
                else if (i > 4 && (i+j) % 2 == 1){
                    ImageIcon icon = new ImageIcon("red.png");
                    b.setIcon(icon);
                    b.setEnabled(true);
                    checkerPieces[i][j] = new Checker("red", i, j);
                }
                else{
                    checkerPieces[i][j] = new Checker("", i, j);
                }
                b.setBorderPainted(false);
                b.setFocusPainted(false);
                int finalI = i;
                int finalJ = j;
                b.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        showMoves(finalI, finalJ);
                    }
                });
                checkerBoardSquares[j][i] = b;
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
                        checkerBoard.add(new JLabel("" + (9-(ii + 1)),
                                SwingConstants.CENTER));
                    default:
                        checkerBoard.add(checkerBoardSquares[jj][ii]);
                }
            }
        }
    }

    public void showMoves(int i, int j){
        if (checkerPieces[i][j].getColor().equals("red")){

        }
        else if (checkerPieces[i][j].getColor().equals("black")){

        }

    }
    public final JComponent getGui() {
        return gui;
    }

}