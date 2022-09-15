/**
 * This class imports the words from the files and puts them into ArrayLists
 * @author Trevor Lindberg
 * @version 1.0
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class FileIO {

    /**
     * This method scans in the words from the english file
     * @return engWords - array list of english words
     * @throws FileNotFoundException
     */
    public static ArrayList<String> getEngArray() throws FileNotFoundException {
        File englishFile = new File("English.txt");
        Scanner scanEng = new Scanner(englishFile);
        ArrayList<String> engWords = new ArrayList<>();

        while (scanEng.hasNextLine()) engWords.add(scanEng.nextLine());
        scanEng.close();

        return engWords;
    }

    /**
     * This method scans in the words from the japanese file
     * @return japWords - array list of japanese words
     * @throws FileNotFoundException
     */
    public static ArrayList<String> getJapArray() throws FileNotFoundException {
        File japaneseFile = new File("Japanese.txt");
        Scanner scanJap = new Scanner(japaneseFile);
        ArrayList<String> japWords = new ArrayList<>();

        while (scanJap.hasNextLine()) japWords.add(scanJap.nextLine());
        scanJap.close();

        return japWords;
    }

}