// Sample run
public class Main {

    public static void main(String[] args) {
        LinkedList line = new LinkedList();

        // Required sample (from assignment pdf)
        System.out.println("Add customer: Alice, Priority: 2 -> Added at end");
        line.addCustomerToEnd("Alice", 2);

        System.out.println("Add customer: Bob, Priority: 3 -> Added based on " + "priority (now at front)");
        line.addBasedOnPriority("Bob", 3);

        System.out.println("Add customer: Charlie, Priority: 1 -> Added at end");
        line.addCustomerToEnd("Charlie", 1);

        System.out.println("Display List:");
        line.displayLine();

        // Extensions
        // displayLine(): prints each person as "Position n: Name, Priority p" (reads like a sentence).
        // advancedDisplayLine(): same order and data, but "Priority _ | Name | Place _" so priority is first.
        System.out.println();
        System.out.println("-----------Additional Methods-----------");
        System.out.println("Advanced display:");
        line.advancedDisplayLine();

        System.out.println();
        System.out.println("Moving the cursor to a specific customer -> Name: Charlie");
        if (line.moveCursorToCustomer("Charlie")) {
            System.out.println("Cursor: found and stopped on that specific customer");
        } else {
            System.out.println("Cursor: did not find specified customer");
        }

        System.out.println();
        System.out.println("Removing customer by name -> Name: Alice");
        if (line.removeCustomerByName("Alice")) {
            System.out.println("Removal: removed customer with specified name from the line");
        } else {
            System.out.println("Removal: no customer with specified name to remove");
        }

        System.out.println();
        System.out.println("After removal, display list:");
        line.displayLine();

        System.out.println();
        System.out.println("Moving the cursor to a specific customer -> Name: Nobody");
        if (line.moveCursorToCustomer("Nobody")) {
            System.out.println("Cursor: found and stopped on that specific customer");
        } else {
            System.out.println("Cursor: did not find specified customer");
        }

        System.out.println();
        System.out.println("Removing customer by name -> Name: Nobody");
        if (line.removeCustomerByName("Nobody")) {
            System.out.println("Removal: removed customer with specified name from the line");
        } else {
            System.out.println("Removal: no customer with specified name to remove");
        }

        // More test cases (edge cases / illegal arguments)
        System.out.println();
        System.out.println("--------Edge Cases---------");

        LinkedList emptyList = new LinkedList();
        System.out.println("Display on empty list:");
        emptyList.displayLine();

        System.out.println();

        System.out.println("Remove on empty list -> " + emptyList.removeCustomerByName("Any"));
        if (emptyList.moveCursorToCustomer("Any")) {
            System.out.println("oving cursor of empty list to customer: found");
        } else {
            System.out.println("Moving cursor of empty list to customer: did not find specified customer");
        }

        LinkedList newList = new LinkedList();
        newList.addCustomerToEnd("Tom", 1);
        newList.addCustomerToEnd("Jerry", 1);
        System.out.println();
        System.out.println("addCustomerAtPosition(\"Sara\", 2, 2) -> " +
                newList.addCustomerAtPosition("Sara", 2, 2));
        newList.displayLine();

        System.out.println();
        System.out.println("addCustomerAtPosition(\"OutOfRange\", 2, 10) -> " +
                newList.addCustomerAtPosition("OutOfRange", 2, 10));

        System.out.println();
        try {
            line.addCustomerToEnd("InvalidPriorityEnd", 0);
        } catch (IllegalArgumentException e) {
            System.out.println("Caught expected exception (addCustomerToEnd): " + e.getMessage());
        }

        try {
            line.addCustomerAtPosition("InvalidPriorityPos", -1, 1);
        } catch (IllegalArgumentException e) {
            System.out.println("Caught expected exception (addCustomerAtPosition priority): " + e.getMessage());
        }

        try {
            line.addCustomerAtPosition("InvalidPosition", 1, 0);
        } catch (IllegalArgumentException e) {
            System.out.println("Caught expected exception (addCustomerAtPosition position): " + e.getMessage());
        }

        try {
            line.addBasedOnPriority("InvalidPriorityPriorityInsert", 0);
        } catch (IllegalArgumentException e) {
            System.out.println("Caught expected exception (addBasedOnPriority): " + e.getMessage());
        }
    }
}
