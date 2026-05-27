// A simple object representing a node used in Singly Linked List implementation of OrderQueue
public class OrderNode {

    // Data Fields
    private OrderNode link;
    private Order data;

    // Constructor
    public OrderNode(Order o){
        this.link = null;
        this.data = o;
    }

    // Getters
    public OrderNode getLink(){
        return this.link;
    }

    public Order getData(){
        return this.data;
    }

    // Setters
    public void setLink(OrderNode link){
        this.link = link;
    }

    public void setData(Order o){
        this.data = o;
    }
}
