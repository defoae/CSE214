public class Node {

    // Fields
    private Node link;
    private Customer customer;

    // Constructor
    public Node(Customer customer){
        this.link = null;
        this.customer = customer;
    }

    // Getters
    public Node getLink(){
        return this.link;
    }

    public Customer getCustomer(){
        return this.customer;
    }

    // Setters
    public void setLink(Node node){
        this.link = node;
    }

    public void setCustomer(Customer customer){
        this.customer = customer;
    }
}
