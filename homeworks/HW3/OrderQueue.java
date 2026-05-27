// A custom implementation of a Queue using a Singly Linked List
public class OrderQueue {

    // Data Fields
    private OrderNode front;
    private OrderNode rear;

    // Constructor
    public OrderQueue(){
        this.front = null;
        this.rear = null;
    }

    // Methods
    public void enqueue(Order o){
        OrderNode newNode = new OrderNode(o);
        if(front == null){
            front = newNode;
            rear = newNode;
        }
        else{
            rear.setLink(newNode);
            rear = newNode;
        }
    }

    public Order dequeue() throws RuntimeException{
        if (front == null) throw new RuntimeException("Queue is empty");
        Order answer = front.getData();
        front = front.getLink();
        if(front == null){
            rear = null;
        }
        return answer;
    }

    public Order peek() throws RuntimeException{
        if(front != null){
            return (front.getData());
        }
        else throw new RuntimeException("Queue is empty");
    }

    public boolean isEmpty(){
        return (this.front == null);
    }

    /*
     * Builds a numbered multiline listing of queued orders from front to rear for display (View Warehouse).
     * public String warehouseListing()
     * Returns: numbered lines matching Order.toString(); if empty, a single placeholder line for an empty queue.
     */
    public String warehouseView(){
        if(isEmpty()){
            return "[Queue is Empty]";
        }
        StringBuilder sb = new StringBuilder();
        OrderNode curr = front;
        int index = 1;
        while(curr != null){
            sb.append(index).append(". ").append(curr.getData().toString()).append("\n");
            index++;
            curr = curr.getLink();
        }
        return sb.toString().trim();
    }
}
