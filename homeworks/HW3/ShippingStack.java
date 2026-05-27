// A custom implementation of a Stack using an Array
public class ShippingStack {

    // Data Fields
    private Order[] data;
    private int top;

    // Constructor: No Arg - Initial capacity of array = 100
    public ShippingStack(){
        this.data = new Order[100];
        this.top = -1;
    }

    // Methods

    public boolean isEmpty(){
        return (this.top == -1);
    }

    public void push(Order o){
        if(top + 1 == data.length) ensureCapacity(data.length * 2 + 1);
        top++;
        data[top] = o;
    }

    public Order pop() throws RuntimeException{
        if(isEmpty()) throw new RuntimeException("Stack is empty");
        Order answer = data[top];
        top--;
        return answer;
    }

    // Helpers
    public void ensureCapacity(int newCapacity){
        Order[] biggerData = new Order[newCapacity];
        System.arraycopy(this.data, 0, biggerData, 0, (this.top + 1));
        this.data = biggerData;
    }

    /*
     * Builds a numbered multiline listing of stack contents from top to bottom for display (View Warehouse).
     * public String warehouseListing()
     * Returns: numbered lines matching Order.toString(); if empty, pallet empty placeholder text.
     */
    public String warehouseView(){
        if(isEmpty()){
            return "[Pallet is Empty]";
        }
        StringBuilder sb = new StringBuilder();
        int index = 1;
        for(int i = this.top; i >= 0; i--){
            sb.append(index).append(". ").append(this.data[i].toString()).append("\n");
            index++;
        }
        return sb.toString().trim();
    }
}
