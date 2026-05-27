/* Holds a single person's info. Used by PersonDataManager for storage/display. */
public class Person {

    private String name;
    private String gender;
    private int age;
    private double height;
    private double weight;

    public Person(){
    }

    /*
     * Creates a person with the given attributes.
     * public Person(String name, String gender, int age, double height, double weight)
     * Parameters:
     *   name – person's name
     *   gender – "M" or "F"
     *   age – age in years
     *   height – height in inches
     *   weight – weight in pounds
     * Preconditions: age > 0, height > 0, weight > 0 (caller's responsibility when used from CLI)
     * Postconditions: this person's fields are set to the given values.
     */
    public Person(String name, String gender, int age, double height, double weight){
        this.name = name;
        this.gender = gender;
        this.age = age;
        this.height = height;
        this.weight = weight;
    }

    public String getName(){
        return this.name;
    }

    public String getGender(){
        return this.gender;
    }

    public int getAge(){
        return this.age;
    }

    public double getHeight(){
        return this.height;
    }

    public double getWeight(){
        return this.weight;
    }

    public void setName(String name){
        this.name = name;
    }

    public void setGender(String gender){
        this.gender = gender;
    }

    public void setAge(int age){
        this.age = age;
    }

    public void setHeight(double height){
        this.height = height;
    }

    public void setWeight(double weight){
        this.weight = weight;
    }

    /*
     * String representation for table display (header + this person's row).
     * public String toString()
     * Returns: formatted string with column headers and this person's name, sex, age, height, weight.
     */
    @Override
    public String toString(){
        return String.format("%-15s %-6s %-5s %-12s %-12s\n%-15s %-6s %-5d %-12.1f %-12.1f", "Name", "Sex", "Age", "Height (in)", "Weight (lbs)", this.getName(), this.getGender(), this.getAge(), this.getHeight(), this.getWeight());
    }
}
