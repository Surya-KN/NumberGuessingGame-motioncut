import javax.swing.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Random;

public class GuessingGame {
    private static int randomNumber,tries;
    private static JTextField userText;
    private static JLabel resultLabel;

    public static void main(String[] args) {
        randomNumber = new Random().nextInt(100) + 1;

        JFrame frame = new JFrame("Guessing Game");
        frame.setSize(300, 200);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel();
        frame.add(panel);
        placeComponents(panel);

        frame.setVisible(true);
    }

    private static void placeComponents(JPanel panel) {
        panel.setLayout(null);

        JLabel userLabel = new JLabel("Enter a number (1-100):");
        userLabel.setBounds(10, 20, 200, 25);
        panel.add(userLabel);

        userText = new JTextField(20);
        userText.setBounds(10, 50, 160, 25);
        panel.add(userText);

        JButton loginButton = new JButton("Guess");
        loginButton.setBounds(10, 80, 80, 25);
        loginButton.setFocusPainted(false);
        panel.add(loginButton);

        resultLabel = new JLabel("");
        resultLabel.setBounds(10, 110, 280, 50);
        panel.add(resultLabel);

        // Add a KeyListener to the JTextField
        userText.addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    loginButton.doClick();
                }
            }
        });

        loginButton.addActionListener(e -> {
            try {
                int guess = Integer.parseInt(userText.getText());
                if (guess < 1 || guess > 100) {
                    resultLabel.setText("<html><font color='red'>Invalid input! Please enter a number between 1 and 100.</font></html>");
                    userText.requestFocus();
                    userText.selectAll();
                    return;
                }
                int difference = Math.abs(guess - randomNumber);

                tries++;

                if (guess == randomNumber) {
                    resultLabel.setText("Congratulations! You guessed the number.");
                    JOptionPane.showMessageDialog(null, "Congratulations! You won in " + tries + " tries!", "Winner",JOptionPane.INFORMATION_MESSAGE);
                    // Reset the game
                    randomNumber = new Random().nextInt(100) + 1;
                    tries = 0;
                    resultLabel.setText("A new game has started. Enter your first guess.");
                } else {
                    if (guess < randomNumber) {
                        if (difference >= 10) {
                            resultLabel.setText("Too low! Try again.");
                        } else if (difference <= 5) {
                            resultLabel.setText("You're very close! Try a slightly higher number.");
                        } else {
                            resultLabel.setText("You're close! Try a higher number.");
                        }
                    } else {
                        if (difference >= 10) {
                            resultLabel.setText("Too high! Try again.");
                        } else if (difference <= 5) {
                            resultLabel.setText("You're very close! Try a slightly lower number.");
                        } else {
                            resultLabel.setText("You're close! Try a lower number.");
                        }
                    }
                    userText.requestFocus();
                    userText.selectAll();
                }
            } catch (NumberFormatException ex) {
                resultLabel.setText("<html><font color='red'>Invalid input! Please enter a number between 1 and 100.</font></html>");
                userText.requestFocus();
                userText.selectAll();
            }
        });
    }
}
