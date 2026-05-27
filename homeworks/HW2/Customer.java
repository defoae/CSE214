public class Customer {

    // Fields
    private String name;
    private int priority;

    // Constructor
    public Customer(String name, int priority) throws IllegalArgumentException{
        if(priority < 1) throw new IllegalArgumentException("Priority can not be less than 1");
        this.name = name;
        this.priority = priority;
    }

    // Getters
    public String getName(){
        return this.name;
    }

    public int getPriority(){
        return this.priority;
    }

    // Setters
    public void setName(String name){
        this.name = name;
    }

    public void setPriority(int priority) throws IllegalArgumentException{
        if(priority < 1) throw new IllegalArgumentException("Priority can not be less than 1");
        this.priority = priority;
    }
}