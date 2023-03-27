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

            /**
             * Override the preferred size to return the largest it can, in
             * a square shape.  Must (must, must) be added to a GridBagLayout
             * as the only component (it uses the parent as a guide to size)
             * with no GridBagConstaint (so it is centered).
             */
            @Override
            public final Dimension getPreferredSize() {
                Dimension d = super.getPreferredSize();
                Dimension prefSize = null;
                Component c = getParent();
                if (c == null) {
                    prefSize = new Dimension(
                            (int)d.getWidth(),(int)d.getHeight());
                } else if (c!=null &&
                        c.getWidth()>d.getWidth() &&
                        c.getHeight()>d.getHeight()) {
                    prefSize = c.getSize();
                } else {
                    prefSize = d;
                }
                int w = (int) prefSize.getWidth();
                int h = (int) prefSize.getHeight();
                // the smaller of the two sizes
                int s = (w>h ? h : w);
                return new Dimension(s,s);
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

    public void checkersInitialize(){
        for(int i = 1; i < 9; i ++){
            for(int b = 1; b < 9; b++){
                int num = (i * 10) + b;
                map.put(num, null); // blank hashmap
            }
        }

        for(int j = 12; j < 20; j+=2){ //row
            map.put(j, new Checker("black", j / 10, j % 10));
        }

        for(int a = 21; a < 28; a += 2){
            map.put(a, new Checker("black", a / 10, a % 10));
        }

        for(int b = 32; b < 39; b += 2){
            map.put(b, new Checker("black", b / 10, b % 10));
        }

        for(int i = 61; i< 68; i+=2){
            map.put(i, new Checker("red", i/10, i%10));
        }
        for(int i = 72; i< 79; i+=2){
            map.put(i, new Checker("red", i/10, i%10));
        }
        for(int i = 81; i< 88; i+=2){
            map.put(i, new Checker("red", i/10, i%10));
        }
    }
}