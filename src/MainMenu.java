import javax.swing.*;
import java.awt.*;

public class MainMenu extends JFrame {
    private JButton a15SecondsButton;
    private JButton a60SecondsButton;
    private JButton a30SecondsButton;
    private JButton a45SecondsButton;
    private JPanel panelMain;
    private JLabel welcomeLbl;

    public void increaseFontSize(JLabel label, float inc) {
        Font ogFont = label.getFont();
        Font biggerFont = ogFont.deriveFont(inc);
        label.setFont(biggerFont);
    }

    public MainMenu(String title) {
        super(title);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(panelMain);
        increaseFontSize(welcomeLbl, 25.0F);

        a15SecondsButton.addActionListener(e -> {
            ReactionTimeRunner frame = new ReactionTimeRunner("Reaction Time Tester", 16);
            frame.setSize(1200, 1000);
            frame.setVisible(true);
        });

        a30SecondsButton.addActionListener(e -> {
            ReactionTimeRunner frame = new ReactionTimeRunner("Reaction Time Tester", 31);
            frame.setSize(1200, 1000);
            frame.setVisible(true);
        });

        a45SecondsButton.addActionListener(e -> {
            ReactionTimeRunner frame = new ReactionTimeRunner("Reaction Time Tester", 46);
            frame.setSize(1200, 1000);
            frame.setVisible(true);
        });

        a60SecondsButton.addActionListener(e -> {
            ReactionTimeRunner frame = new ReactionTimeRunner("Reaction Time Tester", 61);
            frame.setSize(1200, 1000);
            frame.setVisible(true);
        });
    }
}
