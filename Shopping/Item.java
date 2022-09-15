package Shopping;

/**
 * This class creates items objects for the CashRegister class
 *
 * @author Trevor Lindberg
 * @version 3/13/2021
 */
public class Item {
    private String name;
    private double cost;

    /**
     * Default constructor for Item
     */
    public Item() {
        name = "None";
        cost = 0;
    }

    /**
     * Overloaded constructor for Item
     * @param name - name of the item
     * @param cost - cost of the item
     */
    public Item(String name, double cost) {
        this.name = name;
        this.cost = cost;
    }

    /**
     * Getter for the name of the item
     * @return name - name of the item
     */
    public String getName() { return name; }

    /**
     * Getter for the cost of the item
     * @return cost - cost of the item
     */
    public double getCost() { return cost; }
}