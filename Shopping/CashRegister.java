package Shopping;

/**
 * This method acts as a cash register,
 * the user may buy items from the current stock, and/or add new items to the stock.
 * once the transaction is complete, a complete receipt is given.
 *
 * @author Trevor Lindberg
 * @version 3/13/2021
 */

import java.util.ArrayList;
import java.util.Scanner;

public class CashRegister {
    static Scanner in = new Scanner(System.in);

    public static void main(String[] args) {
        // Hard coded items for sale
        ArrayList<Item> stock = new ArrayList<>();
        stock.add(new Item("Hammer", 7.50));
        stock.add(new Item("Drill", 20));
        stock.add(new Item("Nail", 0.50));
        stock.add(new Item("Screw", 0.75));
        stock.add(new Item("Wooden Plank(2x4x8)", 10));

        String[] paymentTypes = {"Cash:  ", "Debit: ", "Credit:", "Check: "};
        final double TAX = 1.07;
        double total = 0, subtotal = 0, paid, totalPaid = 0;
        String receiptItems = "", receiptPayment = "";
        int option, quantity;

        do {
            printStock(stock);  // Prints all items
            option = in.nextInt();
            in.nextLine();

            if (option > 0 && option <= stock.size()) {  // Will be false if "Checkout" is selected
                System.out.println("How many of this item would you like?");
                quantity = in.nextInt();
                in.nextLine();
                option--;  // Subtracting 1 from the option so it matches the arraylist index

                receiptItems = itemsToReceipt(receiptItems, quantity, stock, option);
                subtotal = addToTotal(subtotal, quantity, stock, option);
            } else if (option == (stock.size() + 1)) {
                total = subtotal * TAX;
                System.out.println("Hope you found everything today!\n" +
                        "Total: $" + total);
            } else if (option == 0) {
                addItem(stock);
            } else {
                System.out.println("Invalid input!");
            }
        }while(option != (stock.size() + 1));  // Will be false if "Checkout" is selected

        do {
            printPaymentTypes(paymentTypes);
            option = in.nextInt();
            in.nextLine();
            option--;  // Subtracting 1 to match option to array index of paymentTypes
            System.out.println("How much would you like to pay with this type?");
            paid = in.nextDouble();
            in.nextLine();

            totalPaid += paid;
            receiptPayment = paymentTypesToReceipt(receiptPayment, paymentTypes[option], paid);
            System.out.println("Remaining balance: $" + (total - totalPaid));
        } while(total - totalPaid > 0);
        System.out.println("\nPrinting Receipt...");
        printReceipt(receiptItems, total, subtotal, receiptPayment, totalPaid);
    }

    /**
     * This method creates one big string for the list of items the user buys.
     *
     * @param str - String containing all previous listed items for the transaction
     * @param quantity - the quantity of the item the user wants
     * @param stock - the full arraylist of items the user can choose from
     * @param item - the index for the item in the arraylist "stock" that the user choose
     * @return - str -> the new string containing all the receipt information
     */
    public static String itemsToReceipt(String str, int quantity, ArrayList<Item> stock, int item) {
        str += quantity + "x" + stock.get(item).getName() + ": $" + (quantity * stock.get(item).getCost()) + "\n";
        return str;
    }

    /**
     * This method combines all of the payment information into a single string
     * @param str - previous payment information
     * @param payment - payment type the user choose
     * @param amountPaid - the amount the user is payingwith this type
     * @return str - the new string with all the current payment info
     */
    public static String paymentTypesToReceipt(String str, String payment, double amountPaid) {
        str += payment + "   $" + amountPaid + "\n";
        return str;
    }

    /**
     * This method takes all the needed information and prints it into a well formatted receipt
     * @param items - the string with the full list of item bought, including item quantity
     * @param total - the final cost of the transaction
     * @param subtotal - cost before tax is added
     * @param paymentTypes - the full list of ways the user paid, including amounts of each
     * @param amountPaid - the total amount of money the user paid, used to find the change
     */
    public static void printReceipt(String items, double total, double subtotal, String paymentTypes, double amountPaid) {
        System.out.println(items + "\n" +
                "----------------------------------------------\n" +
                "Subtotal: $" + subtotal + "\n" +
                "Tax:      $" + (total - subtotal) + "\n" +
                "Total:    $" + total + "\n" +
                paymentTypes + "\n" +
                "Change:   $" + -(total - amountPaid));
    }

    /**
     * This method tracks the cumulative total cost of the transaction
     *
     * @param total - total cost before the current item's cost is added
     * @param quantity - quantity of the item the user wants
     * @param stock - the full arraylist of items the user can choose from
     * @param item - the index for the item in the arraylist "stock" that the user choose
     * @return - total -> the cost after the current item's cost is added
     */
    public static double addToTotal(double total, int quantity, ArrayList<Item> stock, int item) {
        total += quantity * stock.get(item).getCost();
        return total;
    }

    /**
     * This method allows the user to add their own items to the list of items
     * @param items - arraylist containing all current items
     */
    public static void addItem(ArrayList<Item> items) {
        System.out.println("Enter the name of the new item.");
        String name = in.nextLine();

        System.out.println("Enter the cost of the item.");
        double cost = in.nextDouble();
        in.nextLine();

        items.add(new Item(name, cost));
        System.out.println("Item added!");
    }

    /**
     * This method prints the full list of items in stock, including items the user adds
     *
     * @param items - the full arraylist of items the user can choose from
     */
    public static void printStock(ArrayList<Item> items) {
        System.out.println("0: Add item");
        for (int i = 0; i < items.size(); i++) {
            System.out.println((i + 1) + ": " + items.get(i).getName() + ": $" + items.get(i).getCost());
        }
        System.out.println((items.size() + 1) + ": Checkout");
    }

    /**
     * This method prints the payment types
     * @param payment - the array of the payment types
     */
    public static void printPaymentTypes(String[] payment) {
        System.out.println("In what way will you be paying?\n" +
                "1: " + payment[0] + "\n" +
                "2: " + payment[1] + "\n" +
                "3: " + payment[2] + "\n" +
                "4: " + payment[3]);
    }
}
/* Output

Printing Receipt...
2xDrill: $40.0
3xWooden Plank(2x4x8): $30.0

----------------------------------------------
Subtotal: $70.0
Tax:      $4.900000000000006
Total:    $74.9
Cash:     $75.0

Change:   $0.09999999999999432

 */