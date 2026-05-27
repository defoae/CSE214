import java.util.Scanner;

public class WarehouseManager {

    private static final OrderQueue orderQueue = new OrderQueue();
    private static final ShippingStack shippingStack = new ShippingStack();

    /*
     * Reads a non-empty line (full line, trimmed) from the user. Reprompts on empty.
     * private static String readLineNonEmpty(Scanner input, String text)
     * Parameters: input – scanner; text – prompt to display
     * Returns: trimmed line suitable for multi-word fields (e.g. item name).
     */
    private static String readLineNonEmpty(Scanner input, String text){
        while(true){
            System.out.println(text);
            String value = input.nextLine().trim();
            if(!value.isEmpty())
                return value;
            System.out.println("Input cannot be empty");
            System.out.println();
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
     * Add Order (menu A). Prompts for item name, id, and price; enqueues the order and prints confirmation.
     * private static void addOrder(Scanner input)
     * Parameters: input – scanner for prompts
     * Postconditions: new Order is appended to orderQueue unless construction fails (validated inputs avoid this).
     */
    private static void addOrder(Scanner input){
        System.out.println();
        String itemName = readLineNonEmpty(input, "Enter Item Name:");
        int orderID = readInt(input, "Enter Order ID:");
        double price = readDouble(input, "Enter Price:");
        Order o = new Order(itemName, orderID, price);
        orderQueue.enqueue(o);
        System.out.println();
        System.out.println("Result: Order #" + orderID + " added to the queue.");
    }

    /*
     * Process Order (menu P). Dequeues front of orderQueue and pushes onto shippingStack when possible.
     * private static void processOrder()
     * Postconditions: if queue nonempty, oldest order moves to stack; otherwise an error message is printed.
     */
    private static void processOrder(){
        System.out.println();
        if(orderQueue.isEmpty()){
            System.out.println("Queue is empty. There are no orders to process.");
            return;
        }
        System.out.println("Loading...");
        try {
            Order next = orderQueue.dequeue();
            shippingStack.push(next);
            System.out.println("Order #" + next.getOrderID() + " (" + next.getItemName()
                    + ") has been moved to the Shipping Stack.");
        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
        }
    }

    /*
     * View Warehouse (menu V). Prints current queue listing (front to back) then stack listing (top to bottom).
     * private static void viewWarehouse()
     */
    private static void viewWarehouse(){
        System.out.println();
        System.out.println("CURRENT QUEUE (Front to Back):");
        System.out.println(orderQueue.warehouseView());
        System.out.println("CURRENT STACK (Top to Bottom):");
        System.out.println(shippingStack.warehouseView());
    }

    /*
     * Dispatch Pallet (menu D). Pops shippingStack until empty, printing dispatch lines in LIFO order.
     * private static void dispatchPallet()
     */
    private static void dispatchPallet(){
        System.out.println();
        System.out.println("Dispatching Pallet...");
        if(shippingStack.isEmpty()){
            System.out.println();
            System.out.println("The shipping pallet is already empty. Nothing to dispatch.");
            System.out.println();
            return;
        }
        int n = 1;
        try {
            while(!shippingStack.isEmpty()){
                Order o = shippingStack.pop();
                System.out.println(n + ". Dispatching Order #" + o.getOrderID() + ": " + o.getItemName());
                n++;
            }
            System.out.println();
            System.out.println("Result: All items dispatched. Pallet is now empty.");
        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
        }
    }

    /*
     * Quit (menu Q). Prints goodbye message.
     * private static void quit()
     */
    private static void quit(){
        System.out.println();
        System.out.println("Goodbye!");
    }

    /*
     * Prints the main warehouse menu banner (options A, P, V, D, Q).
     * private static void printMenu()
     */
    private static void printMenu(){
        System.out.println(
                "(A) Add Order to Queue\n" +
                        "(P) Process Order (Queue -> Stack)\n" +
                        "(V) View Warehouse State\n" +
                        "(D) Dispatch Pallet (Empty Stack)\n" +
                        "(Q) Quit"
        );
    }

    public static void main(String[] args){

        Scanner input = new Scanner(System.in);

        printMenu();

        boolean isOngoing = true;

        while(isOngoing){
            System.out.println();
            System.out.print("Selection: ");
            String option = input.nextLine().trim().toUpperCase();

            switch(option){

                case "A": {
                    addOrder(input);
                    break;
                }

                case "P": {
                    processOrder();
                    break;
                }

                case "V": {
                    viewWarehouse();
                    break;
                }

                case "D": {
                    dispatchPallet();
                    break;
                }

                case "Q": {
                    quit();
                    isOngoing = false;
                    break;
                }

                default: {
                    System.out.println();
                    System.out.println("Invalid selection. Choose A, P, V, D, or Q.");
                    break;
                }
            }
        }
        input.close();
    }
}
