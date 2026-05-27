import java.util.Scanner;

/*
 * Client for the person manager: menu loop and input prompts.
 * Commands: I=import, A=add, R=remove, G=get, P=print table, S=save, Q=quit.
 */
public class PersonManager {

    private static final PersonDataManager currManager = new PersonDataManager();

    public PersonManager(){}

    /*
     * Reads a name from the user (letters only, first token). Reprompts until valid.
     * private static String readStringNames(Scanner input, String text)
     * Parameters: input – scanner; text – prompt to display
     * Returns: first token of the line, letters only, non-empty; first letter capitalized.
     */
    private static String readStringNames(Scanner input, String text){
        while(true){
            System.out.println(text);
            String value = input.nextLine().toLowerCase().trim();
            if(!value.isEmpty()){
                value = value.toUpperCase().charAt(0) + value.substring(1);
                String[] line = value.split("\\s");
                if(!line[0].matches("[a-zA-Z]+")){
                    System.out.println();
                    System.out.println("Invalid input - don't use numbers");
                    System.out.println();
                }
                else if(!line[0].isEmpty() && line[0].matches("[a-zA-Z]+"))
                    return line[0];
            }
            else{
                System.out.println("Input cannot be empty");
                System.out.println();
            }
        }
    }

    /*
     * Reads the first non-empty token from the user. Reprompts on empty.
     * private static String readString(Scanner input, String text)
     * Parameters: input – scanner; text – prompt to display
     * Returns: first token of the line (e.g. file path or file name).
     */
    private static String readString(Scanner input, String text){
        while(true){
            System.out.println(text);
            String value = input.nextLine().trim();
            String[] line = value.split("\\s");
            if(!line[0].isEmpty())
                return line[0];
            System.out.println("Input cannot be empty");
            System.out.println();
        }
    }

    /*
     * Reads a single letter M or F (gender). Reprompts until valid.
     * private static String readStringLetter(Scanner input, String text)
     * Parameters: input – scanner; text – prompt to display
     * Returns: "M" or "F" (as entered by user, case preserved).
     */
    private static String readStringLetter(Scanner input, String text){
        while(true){
            System.out.println(text);
            String value = input.nextLine().trim();
            if(value.isEmpty()){
                System.out.println("Input cannot be empty");
                System.out.println();
            }
            else if(value.equalsIgnoreCase("m") || value.equalsIgnoreCase("f")){
                return value;
            }
            else{
                System.out.println();
                System.out.println("Make sure to use lower/upper -case letters 'M' or 'F'");
                System.out.println();
            }

        }
    }

    /*
     * Reads a positive integer. Reprompts on empty, non-integer, or non-positive.
     * private static int readInt(Scanner input, String text)
     * Parameters: input – scanner; text – prompt to display
     * Returns: integer value > 0.
     * Preconditions: none (invalid input causes reprompt).
     */
    private static int readInt(Scanner input, String text){
        while(true){
            System.out.println(text);
            String line = input.nextLine();
            if(line.isEmpty()){
                System.out.println("Input cannot be empty");
                System.out.println();
            } else {
                try {
                    int value = Integer.parseInt(line);
                    if (value > 0)
                        return value;
                    System.out.println();
                    System.out.println("Value must be positive");
                    System.out.println();
                } catch (Exception e) {
                    System.out.println();
                    System.out.println("Invalid integer");
                    System.out.println();
                }
            }
        }
    }

    /*
     * Reads a positive double. Reprompts on empty, non-number, or non-positive.
     * private static double readDouble(Scanner input, String text)
     * Parameters: input – scanner; text – prompt to display
     * Returns: double value > 0.
     * Preconditions: none (invalid input causes reprompt).
     */
    private static double readDouble(Scanner input, String text){
        while(true){
            System.out.println(text);
            String line = input.nextLine();
            if(line.isEmpty()){
                System.out.println("Input cannot be empty");
                System.out.println();
            } else {
                try{
                    double value = Double.parseDouble(line);
                    if(value > 0)
                        return value;
                    System.out.println();
                    System.out.println("Value must be positive");
                    System.out.println();
                } catch(Exception e){
                    System.out.println();
                    System.out.println("Invalid number");
                    System.out.println();
                }
            }
        }
    }

    /*
     * Import from file (command I).
     * private static boolean I(String location)
     * Parameters: location – path to the CSV file
     * Preconditions: none (invalid path is handled).
     * Returns: true if buildFromFile succeeded; false otherwise. Caller should reprompt until true.
     */
    private static boolean I(String location){
        System.out.println();
        return currManager.buildFromFile(location);
    }

    /*
     * Add person (command A). Prints success message and the person, or the exception message.
     * private static void A(String name, String gender, int age, double height, double weight)
     * Parameters: name, gender, age, height, weight – values for the new person
     * Preconditions: gender is "M" or "F"; age, height, weight > 0 (enforced by read* methods).
     * Throws: none (PersonAlreadyExistsException is caught and its message is printed).
     */
    private static void A(String name, String gender, int age, double height, double weight){
        try{
            System.out.println();
            Person newPerson = new Person(name, gender, age, height, weight);
            if(currManager.addPerson(newPerson)){
                System.out.println("Successfully added a new person");
                System.out.println(newPerson);
            }
        } catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    /*
     * Remove person (command R). Prints the removed person or the exception message.
     * private static void R(String name)
     * Parameters: name – name of the person to remove
     * Throws: none (PersonDoesNotExistException is caught and its message is printed).
     */
    private static void R(String name){
        try{
            System.out.println();
            Person toRemove = currManager.removePerson(name);
            if(toRemove != null){
                System.out.println("Successfully removed the person");
                System.out.println(toRemove);
            }
        } catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    /*
     * Get info on person (command G). Prints the person's data or the exception message.
     * private static void G(String name)
     * Parameters: name – name of the person to look up
     * Throws: none (PersonDoesNotExistException is caught and its message is printed).
     */
    private static void G(String name){
        try{
            System.out.println();
            System.out.println(currManager.getPerson(name));
        } catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    /*
     * Print table (command P). Prints all persons in table format.
     * private static void P()
     */
    private static void P(){
        System.out.println();
        currManager.printTable();
    }

    /*
     * Save to file (command S). Creates/overwrites the CSV file.
     * private static void S(String filename)
     * Parameters: filename – name of the file (e.g. "output.csv")
     * Postconditions: if save succeeds, success message is printed; otherwise nothing.
     */
    private static void S(String filename){
        if(currManager.saveToFile(filename)){
            System.out.println();
            System.out.println("Successfully created and saved: " + filename);
        }
    }

    /*
     * Quit (command Q). Prints message.
     * private static void Q()
     */
    private static void Q(){
        System.out.println();
        System.out.println("Thank you for using PersonManager");
    }

    public static void main(String[] args){

        Scanner input = new Scanner(System.in);

        System.out.println("Welcome to Person Manager");
        System.out.println();
        System.out.println(
                "Available options:\n" +
                        "• (I) Import from File\n" +
                        "• (A) Add Person\n" +
                        "• (R) Remove Person\n" +
                        "• (G) Get Info on Person\n" +
                        "• (P) Print Table\n" +
                        "• (S) Save to File\n" +
                        "• (Q) Quit"
        );

        String option;
        boolean isOngoing = true;

        while(isOngoing){
            System.out.println();
            System.out.println(
                    "Available options:\n" +
                            "• (I) Import from File\n" +
                            "• (A) Add Person\n" +
                            "• (R) Remove Person\n" +
                            "• (G) Get Info on Person\n" +
                            "• (P) Print Table\n" +
                            "• (S) Save to File\n" +
                            "• (Q) Quit"
            );
            System.out.println("Select an option: ");
            option = input.nextLine().trim().toUpperCase();
            switch(option){

                case "I": {
                    String path;
                    do {
                        System.out.println();
                        path = readString(input, "Provide the path to the file:");
                    } while (!PersonManager.I(path));
                    break;
                }

                case "A": {
                    System.out.println();
                    String name = readStringNames(input, "Name:");
                    String gender = readStringLetter(input, "Gender:").toUpperCase();
                    int age = readInt(input, "Age:");
                    double height = readDouble(input, "Height:");
                    double weight = readDouble(input, "Weight:");

                    PersonManager.A(name, gender, age, height, weight);
                    break;
                }

                case "R": {
                    System.out.println();
                    String name = readStringNames(input, "Provide the name of the person:");
                    PersonManager.R(name);
                    break;
                }

                case "G": {
                    System.out.println();
                    String name = readStringNames(input, "Provide the name of the person:");
                    PersonManager.G(name);
                    break;
                }

                case "P": {
                    PersonManager.P();
                    break;
                }

                case "S": {
                    System.out.println();
                    String fileName = readString(input, "Provide the file name:");
                    String cleanName = fileName.contains(".") ? fileName.substring(0, fileName.lastIndexOf('.')) : fileName;
                    fileName = cleanName + ".csv";
                    PersonManager.S(fileName);
                    break;
                }

                case "Q": {
                    PersonManager.Q();
                    isOngoing = false;
                    break;
                }

                default: {
                    System.out.println();
                    System.out.println("Invalid option");
                    break;
                }
            }
        }
        input.close();
    }
}
