import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

/* Data manager for people: dynamic array, file load/save, add/remove/get. Expects CSV with header: Name, Sex, Age, Height, Weight. */
public class PersonDataManager {

    private Person[] people = new Person[1];
    private int size;

    public PersonDataManager(){}

    /*
     * Ensures the internal array can hold at least minimumCapacity persons.
     * public void ensureCapacity(int minimumCapacity)
     * Parameters:
     *   minimumCapacity – minimum number of slots required
     * Preconditions: minimumCapacity >= 0
     * Postconditions: people.length >= minimumCapacity; existing elements unchanged.
     * Throws: OutOfMemoryError – if the JVM cannot allocate the larger array.
     */
    public void ensureCapacity(int minimumCapacity){
        Person[] biggerArray;
        if(people.length < minimumCapacity){
            biggerArray = new Person[minimumCapacity];
            System.arraycopy(this.people, 0, biggerArray, 0, this.size);
            this.people = biggerArray;
        }
    }

    /*
     * Loads persons from a CSV file, replacing current data. First line is treated as header and skipped.
     * public boolean buildFromFile(String location)
     * Parameters:
     *   location – path to the CSV file
     * Preconditions: file exists, readable; CSV rows have 5 columns: Name, Sex, Age, Height, Weight.
     * Postconditions: current data is cleared; then file contents are loaded (duplicates within file skipped).
     * Returns: true if load completed; false if file not found, not readable, or a line had invalid formatting.
     * Throws: none (IOException and IllegalArgumentException are caught; duplicates within file are skipped).
     *         OutOfMemoryError – may propagate from addPerson() if array growth fails during load.
     * Note: Duplicate names within the file are skipped and a message is printed; rest of file still loads.
     */
    public boolean buildFromFile(String location){
        Scanner input = null;
        try{
            input = new Scanner(new File(location));
            this.size = 0;
            if(input.hasNextLine()){
                input.nextLine();
            }
            while (input.hasNextLine()){
                String rawLine = input.nextLine();
                if(rawLine.trim().isEmpty())
                    continue;
                String[] line = rawLine.split(",");
                if (line.length != 5) {
                    throw new IllegalArgumentException("Invalid CSV formatting: " + rawLine);
                }
                String name = line[0].trim().replace("\"", "");
                String gender = line[1].trim().replace("\"", "");
                int age;
                double height;
                double weight;
                try {
                    age = Integer.parseInt(line[2].trim());
                    height = Double.parseDouble(line[3].trim());
                    weight = Double.parseDouble(line[4].trim());
                } catch (NumberFormatException e) {
                    throw new IllegalArgumentException("Invalid numeric field in CSV: " + rawLine);
                }
                try {
                    this.addPerson(new Person(name, gender, age, height, weight));
                } catch (PersonAlreadyExistsException e){
                    System.out.println("Encountered an existing person when building the data manager");
                }
            }
            if(size != 0){
                System.out.println("Successfully built from file");
                return true;
            }
            else{
                System.out.println("File might be empty, but build is successful");
                return true;
            }
        } catch (IllegalArgumentException e){
            System.out.println(e.getMessage());
            return false;
        } catch (IOException e){
            System.out.println(e.getMessage());
            return false;
        } finally {
            if(input != null){
                input.close();
            }
        }
    }

    /*
     * Adds a new person to the manager.
     * public boolean addPerson(Person newPerson) throws PersonAlreadyExistsException
     * Parameters:
     *   newPerson – the person to add
     * Preconditions: newPerson is not null.
     * Postconditions: newPerson is stored; size increases by 1; array is grown if full.
     * Returns: true if the person was added.
     * Throws: PersonAlreadyExistsException – indicates a person with the same name (case-insensitive) already exists.
     *         OutOfMemoryError – if array growth in ensureCapacity() fails.
     */
    public boolean addPerson(Person newPerson) throws PersonAlreadyExistsException{
        for(int i = 0; i < this.size; i++){
            if(people[i].getName().equalsIgnoreCase(newPerson.getName()))
                throw new PersonAlreadyExistsException("Person you are trying to add already exists");
        }
        if(people.length == size)
            ensureCapacity(this.size * 2 + 1);
        this.people[this.size] = newPerson;
        this.size++;
        return true;
    }

    /*
     * Looks up a person by name.
     * public Person getPerson(String name) throws PersonDoesNotExistException
     * Parameters:
     *   name – the name to search for (case-insensitive)
     * Returns: the first person whose name matches.
     * Throws: PersonDoesNotExistException – indicates no person with that name exists in the manager.
     */
    public Person getPerson(String name) throws PersonDoesNotExistException{
        for(int i = 0; i < this.size; i++){
            if(people[i].getName().equalsIgnoreCase(name))
                return people[i];
        }
        throw new PersonDoesNotExistException("Person you're looking for does not exist");
    }

    /*
     * Removes the person with the given name. Uses swap-with-last for O(1) removal.
     * public Person removePerson(String name) throws PersonDoesNotExistException
     * Parameters:
     *   name – the name of the person to remove (case-insensitive)
     * Postconditions: that person is no longer in the manager; size decreases by 1.
     * Returns: the person that was removed.
     * Throws: PersonDoesNotExistException – indicates no person with that name exists in the manager.
     */
    public Person removePerson(String name) throws PersonDoesNotExistException{
        Person toRemove;
        int i = 0;
        for(i = 0; i < this.size; i++){
            if(this.people[i].getName().equalsIgnoreCase(name)){
                toRemove = this.people[i];
                this.size--;
                this.people[i] = this.people[this.size];
                this.people[this.size] = null;
                return toRemove;
            }
        }
        throw new PersonDoesNotExistException("Person you're trying to remove does not exist");
    }

    /*
     * Prints all persons in a formatted table to standard output.
     * public void printTable()
     * Postconditions: header row and one row per person are printed (Name, Sex, Age, Height, Weight).
     * Returns: nothing.
     */
    public void printTable(){
        System.out.printf("%-15s %-6s %-5s %-12s %-12s%n",
                "Name", "Sex", "Age", "Height (in)", "Weight (lbs)");
        for (int i = 0; i < this.size; i++) {
            Person p = this.people[i];
            System.out.printf("%-15s %-6s %-5d %-12.1f %-12.1f", p.getName(), p.getGender(), p.getAge(), p.getHeight(), p.getWeight());
            System.out.println();
        }
    }

    /*
     * Writes all persons to a CSV file with a header line.
     * public boolean saveToFile(String filename)
     * Parameters:
     *   filename – path/name of the file to create or overwrite
     * Preconditions: filename is a valid file name; directory (if any) must be writable.
     * Postconditions: file contains header and one row per person; file is closed.
     * Returns: true if the write succeeded; false if an IOException occurred (message printed).
     */
    public boolean saveToFile(String filename){
        PrintWriter printWriter = null;

        try{
            printWriter = new PrintWriter(new FileWriter(filename));

            printWriter.println("\"Name\",\"Sex\",\"Age\",\"Height (in)\",\"Weight (lbs)\"");

            for(int i = 0; i < this.size; i++){
                Person p = this.people[i];

                printWriter.printf("\"%s\",\"%s\",%d,%.1f,%.1f%n", p.getName(), p.getGender(), p.getAge(), p.getHeight(), p.getWeight());
            }

            return true;

        } catch (IOException e){
            System.out.println();
            System.out.println(e.getMessage());
            return false;
        }
        finally {
            if(printWriter != null)
                printWriter.close();
        }
    }
}
