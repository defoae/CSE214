// A simple object representing a customer purchase
public class Order {

    // Data Fields
    private String itemName;
    private int orderID;
    private double price;

    // Methods

    // Constructor (with all data fields)
    public Order(String itemName, int orderID, double price) throws IllegalArgumentException{
        if(price <= 0) throw new IllegalArgumentException("Price must be greater than zero");
        this.itemName = itemName;
        this.orderID = orderID;
        this.price = price;
    }

    // Getters
    public String getItemName(){
        return this.itemName;
    }

    public int getOrderID(){
        return this.orderID;
    }

    public double getPrice(){
        return this.price;
    }

    // Setters
    public void setItemName(String itemName){
        this.itemName = itemName;
    }

    public void setOrderID(int orderID){
        this.orderID = orderID;
    }

    public void setPrice(double price) throws IllegalArgumentException{
        if(price <= 0) throw new IllegalArgumentException("Price must be greater than zero");
        this.price = price;
    }

    // Helpers
    public String toString(){
        return ("Order #" + this.orderID + ": " + this.itemName + " ($" + String.format("%.2f", this.price) + ")");
    }
}
