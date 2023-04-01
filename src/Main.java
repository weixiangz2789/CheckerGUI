import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        CheckerGUI cg = new CheckerGUI();
        JFrame f = new JFrame();
        f.add(cg.getGui());
        f.setSize(1000, 750);
        f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        f.pack();
        f.setVisible(true);
    }
}