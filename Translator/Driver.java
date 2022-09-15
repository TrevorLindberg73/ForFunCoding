/**
 * This program runs a translation flash card game.
 * With the given files, translations go from english to japanese, and from japanese to english.
 * @author Trevor Lindberg
 * @version 1.0
 */

import javax.swing.*;
import java.io.FileNotFoundException;

public class Driver {
    public static void main(String[] args) throws FileNotFoundException {
        GUI gui = new GUI();
        gui.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        gui.setVisible(true);
    }
}
