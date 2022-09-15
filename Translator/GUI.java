/**
 * This class sets up and runs the GUI
 * @author Trevor Lindberg
 * @version 1.0
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Random;

public class GUI extends JFrame implements ActionListener, KeyListener {

    // Fields
    private JLabel topLabel;
    private JLabel sideLabel;
    private JButton okButton;
    private Timer timer;
    private JTextField textField;

    // Word Arrays
    ArrayList<String> japWords = FileIO.getJapArray();
    ArrayList<String> engWords = FileIO.getEngArray();


    public GUI() throws FileNotFoundException {
        super();
        setTitle("LanguageGUI");

        setLayout(new GridBagLayout());

        GridBagConstraints layoutConst = new GridBagConstraints();

        // Top Label
        topLabel = new JLabel("Getting Ready to begin");
        layoutConst.gridx = 1;
        layoutConst.gridy = 0;
        add(topLabel, layoutConst);

        // Side Label
        sideLabel = new JLabel("_______");
        layoutConst.gridx = 0;
        layoutConst.gridy = 1;
        add(sideLabel, layoutConst);

        // textField
        textField = new JTextField(15);
        layoutConst.gridx = 1;
        layoutConst.gridy = 1;
        textField.addKeyListener(this);
        add(textField, layoutConst);

        // okButton
        okButton = new JButton("OK");
        layoutConst.gridx = 2;
        layoutConst.gridy = 1;
        okButton.addActionListener(this);
        add(okButton, layoutConst);

        // Timer
        timer = new Timer(7000, this);
        timer.setInitialDelay(7000);
        timer.start();
    }

    int langOption = 0;
    int wordIndex = 0;
    /**
     * This Method checks if the word the user entered match the word displayed.
     * Also, this method reset and changes the word after each attempt.
     * The method determines if it needs to reset, or check the user depending on if the textField is editable, which it changes each time.
     * @param e - runs when button is pressed, or timer ends
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        Random rng = new Random();
        timer.restart();

        if (textField.isEditable()) {
            textField.setEditable(false);

            if (langOption == 0) {
                if (textField.getText().toLowerCase().equals(japWords.get(wordIndex))) {
                    topLabel.setText("Correct");
                } else {
                    topLabel.setText("Incorrect, word was: " + japWords.get(wordIndex));
                }
            } else if (langOption == 1) {
                if (textField.getText().toLowerCase().equals(engWords.get(wordIndex))) {
                    topLabel.setText("Correct");
                } else {
                    topLabel.setText("Incorrect, word was: " + engWords.get(wordIndex));
                }
            }

        } else {
            langOption = rng.nextInt(2);
            String word = getWord(langOption);
            topLabel.setText("Enter the translation of this word!");
            sideLabel.setText(word);
            textField.setText("");
            textField.setEditable(true);
        }
    }

    /**
     * Method getting a random word from the list of words chosen, also saves the index of this word.
     * @param langOp - determines the language array the word is chosen from
     * @return - a random word for the chosen array
     */
    public String getWord(int langOp) {
        Random rng = new Random();
        if (langOp == 0) {
            int r = rng.nextInt(engWords.size());
            wordIndex = r;
            return (String) engWords.get(r);
        }
        if (langOp == 1) {
            int r = rng.nextInt(japWords.size());
            wordIndex = r;
            return (String) japWords.get(r);
        }
        return null;
    }

    /**
     * This method contains the same code for the actionPerformed method, only it run when the enter button
     * is pressed when the textBox is selected.
     * I did not know how to call the actionPerformed, hence why I copy and pasted the code.
     * @param e - runs when enter key is pressed while textField is selected
     */
    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode()==KeyEvent.VK_ENTER) {
            Random rng = new Random();
            timer.restart();

            if (textField.isEditable()) {
                textField.setEditable(false);

                if (langOption == 0) {
                    if (textField.getText().toLowerCase().equals(japWords.get(wordIndex))) {
                        topLabel.setText("Correct");
                    } else {
                        topLabel.setText("Incorrect, word was: " + japWords.get(wordIndex));
                    }
                } else if (langOption == 1) {
                    if (textField.getText().toLowerCase().equals(engWords.get(wordIndex))) {
                        topLabel.setText("Correct");
                    } else {
                        topLabel.setText("Incorrect, word was: " + engWords.get(wordIndex));
                    }
                }

            } else {
                langOption = rng.nextInt(2);
                String word = getWord(langOption);
                topLabel.setText("Enter the translation of this word!");
                sideLabel.setText(word);
                textField.setText("");
                textField.setEditable(true);
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    @Override
    public void keyTyped(KeyEvent e) {

    }
}
