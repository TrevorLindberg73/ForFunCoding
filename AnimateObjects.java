import java.lang.Math;
import java.util.Scanner;


public class AnimateObjects {

    final static int d20 = 20, d4 = 4, d6 = 6, d8 = 8, d10 = 10, d12 = 12;
    static Scanner in = new Scanner(System.in);

    public static void main(String[] args) {
        int numT = 0, numS = 0, numM = 0, numL = 0, numH = 0;
        System.out.println("Enter the level you are casting Animate Objects (5-9): ");
        int numObj = 10 + ((in.nextInt() - 5) * 2);

        while (numObj > 0) {
            System.out.println("What type of objects would you like?" +
                    "\n(Tiny = 1, Small = 2, Medium = 3, Large = 4, Huge = 5, Don't add more = 0)" +
                    "\nCurrent objects remaining: " + numObj);
            int choice = in.nextInt();
            if (choice == 0) break;

            System.out.println("How many of the chosen type would you like? (Cost of each: Tiny/Small = 1, Med = 2, Large = 4, Huge = 8)");
            int num;
            switch (choice) {
                case 1:
                    if ((num = in.nextInt()) <= numObj) {
                        numT += num;
                        numObj -= num;
                    } break;
                case 2:
                    if ((num = in.nextInt()) <= numObj && num >= 0) {
                        numS += num;
                        numObj -= num;
                    } break;
                case 3:
                    if (2 * (num= in.nextInt()) <= numObj && num >= 0) {
                        numM += num;
                        numObj -= 2 * num;
                    } break;
                case 4:
                    if (4 * (num = in.nextInt()) <= numObj && num >= 0) {
                        numL += num;
                        numObj -= 4 * num;
                    } break;
                case 5:
                    if (8 * (num = in.nextInt()) <= numObj && num >= 0) {
                        numH += num;
                        numObj -= 8 * num;
                    } break;
                default: System.out.println("Wrong Input"); break;
            }
        }

        System.out.println(numH + " " + numL + " " + numM + " " + numS + " " + numT);
    }

    public static int attackTiny (int numT) {
        int dmg = 0;
        for (int i = 0; i < numT; i++) {

        }
        return dmg;
    }

    public static int roll (int die) {
        return (int)(Math.random() * die) + 1;
    }
}


// Test roll method
//int[] rolls = new int[100];
//for (int i = 0; i < rolls.length; i++) {rolls[i] = roll(d20);}
//System.out.println(Arrays.toString(rolls));

// Test level and object select
//System.out.println(numT + " " + numS + " " + numM + " " + numL + " " + numH);
//System.out.println(numObj);