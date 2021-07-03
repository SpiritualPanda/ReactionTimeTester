import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

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

        ArrayList<JButton> buttons = new ArrayList<>();
        buttons.add(a15SecondsButton);
        buttons.add(a30SecondsButton);
        buttons.add(a45SecondsButton);
        buttons.add(a60SecondsButton);

        for (JButton butt : buttons) {
            butt.addActionListener(e -> {
                ReactionTimeRunner frame = new ReactionTimeRunner("ReactionTimeTester", Integer.parseInt(butt.getToolTipText()));
                frame.setSize(900, 700);
                frame.setVisible(true);
                frame.setLocationRelativeTo(null);
            });
        }
    }
}
