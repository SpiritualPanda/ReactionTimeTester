import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Objects;

public class ReactionTimeRunner extends JFrame {

    public void increaseFontSize(JLabel label, float inc) {
        Font ogFont = label.getFont();
        Font biggerFont = ogFont.deriveFont(inc);
        label.setFont(biggerFont);
    }
    public void playSound(String url, int volume, int delayMS) {
        try {
            Clip clip = AudioSystem.getClip();
            AudioInputStream inputStream = AudioSystem.getAudioInputStream(Objects.requireNonNull(ReactionTimeRunner.class.getResourceAsStream(url)));
            clip.open(inputStream);
            Thread.sleep(delayMS);
            FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
            gainControl.setValue(20f * (float) Math.log10(volume * 0.01f));
            clip.start();
        } catch (Exception ex) {
            System.err.println(ex.getMessage());
        }
    }
    public void nextWord(Words w) {
        wordLabel.setText(w.randomWord());
    }

    // Form variables
    private static JFrame menu;
    private JPanel panelMain;
    private JLabel wordLabel;
    private JLabel titleLabel;
    private JButton a1Button;
    private JButton a2Button;
    private JButton a3Button;
    private JButton a4Button;
    private JButton a5Button;
    private JButton a6Button;
    private JButton a7Button;
    private JButton a8Button;
    private JButton a9Button;
    private JButton a10Button;
    private JLabel scoreLabel;
    private JLabel scoreTitle;
    private JLabel timerLabel;
    // Instance variables
    private int score;
    private final int timeSelection;

    public ReactionTimeRunner(String title, int time) {
        super(title);
        menu.setVisible(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(panelMain);
        increaseFontSize(titleLabel, 20.0F);
        increaseFontSize(scoreTitle, 25.0F);
        increaseFontSize(scoreLabel, 25.0F);
        increaseFontSize(timerLabel, 25.0F);
        timeSelection = time;
        ArrayList<JButton> buttons = new ArrayList<>();
        buttons.add(a1Button);
        buttons.add(a2Button);
        buttons.add(a3Button);
        buttons.add(a4Button);
        buttons.add(a5Button);
        buttons.add(a6Button);
        buttons.add(a7Button);
        buttons.add(a8Button);
        buttons.add(a9Button);
        buttons.add(a10Button);

        final int[] secondsPassed = {0};
        ReactionTimeRunner self = this;
        ActionListener al = e -> {
            secondsPassed[0]++;
            int timeLeft = timeSelection - secondsPassed[0];
            timerLabel.setText(String.valueOf(timeLeft));
            if (timeLeft <= 5) {
                timerLabel.setForeground(new Color(255, 0, 0));
            }
            if (timeLeft == 0) {
                JOptionPane pane = new JOptionPane();
                JOptionPane.showMessageDialog(pane, "Time! Final score: " + score);
                self.setVisible(false);
                menu.setVisible(true);
            }
        };

        Timer timer = new Timer(1000, al);
        timer.start();
        Words words = new Words();
        increaseFontSize(wordLabel, 50.0F);
        wordLabel.setText(words.randomWord());

        for (JButton butt : buttons) {
            butt.addActionListener(e -> {
                if (wordLabel.getText().length() == Integer.parseInt(butt.getToolTipText())) {
                    score++;
                    playSound("/resources/Correct.wav", 80, 0);
                } else {
                    score--;
                    playSound("/resources/Incorrect.wav", 60, 0);
                }
                scoreLabel.setText(String.valueOf(score));
                nextWord(words);
            });
        }
    }

    public static void main(String[] args) {
        menu = new MainMenu("Main Menu");
        menu.setSize(900, 700);
        menu.setVisible(true);
        menu.setLocationRelativeTo(null);
    }

}
