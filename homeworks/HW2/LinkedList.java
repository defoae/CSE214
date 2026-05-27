public class LinkedList {

    // Fields
    private Node head;
    private Node tail;
    private Node cursor;

    // Constructor
    public LinkedList() {
        head = null;
        tail = null;
        cursor = null;
    }

    // Helper Methods
    public boolean isEmpty() {
        return head == null;
    }

    public void resetCursor() {
        cursor = head;
    }

    public int count() {
        int num = 0;
        Node nodePtr = head;
        while (nodePtr != null) {
            num++;
            nodePtr = nodePtr.getLink();
        }
        return num;
    }

    public void addNewHead(Node node) {
        node.setLink(head);
        head = node;
        if (tail == null) {
            tail = node;
        }
        resetCursor();
    }

    // Operations

    // a)
    public void addCustomerToEnd(String name, int priority) {
        if (priority <= 0)
            throw new IllegalArgumentException("Priority can not be less than or equal to 0");
        Customer newCustomer = new Customer(name, priority);
        Node newNode = new Node(newCustomer);
        if (isEmpty()) {
            head = newNode;
            tail = newNode;
            cursor = newNode;
        } else {
            tail.setLink(newNode);
            tail = newNode;
        }
    }

    // b)
    public boolean addCustomerAtPosition(String name, int priority, int pos) {
        if (pos <= 0)
            throw new IllegalArgumentException("Position can not be less than or equal to 0");
        if (priority <= 0)
            throw new IllegalArgumentException("Priority can not be less than or equal to 0");
        if (isEmpty()) {
            if (pos == 1) {
                addCustomerToEnd(name, priority);
                return true;
            }
            return false;
        }
        int len = count();
        if (pos > len + 1) {
            return false;
        }
        if (pos == len + 1) {
            addCustomerToEnd(name, priority);
            return true;
        }
        cursor = head;
        Node prev = null;
        int i = 1;
        while (i < pos && cursor != null) {
            prev = cursor;
            cursor = cursor.getLink();
            i++;
        }
        if (cursor == null) {
            return false;
        }
        Customer newCustomer = new Customer(name, priority);
        Node newNode = new Node(newCustomer);
        if (pos == 1) {
            addNewHead(newNode);
            return true;
        }
        newNode.setLink(cursor);
        prev.setLink(newNode);
        resetCursor();
        if (newNode.getLink() == null)
            tail = newNode;
        return true;
    }

    // c)
    public boolean addBasedOnPriority(String name, int priority) {
        if (priority <= 0)
            throw new IllegalArgumentException("Priority can not be less than or equal to 0");
        Customer newCustomer = new Customer(name, priority);
        Node newNode = new Node(newCustomer);
        if (isEmpty()) {
            head = newNode;
            tail = newNode;
            cursor = newNode;
            return true;
        }
        Node prev = null;
        Node curr = head;
        while (curr != null && curr.getCustomer().getPriority() > priority) {
            prev = curr;
            curr = curr.getLink();
        }
        if (curr == null) {
            tail.setLink(newNode);
            tail = newNode;
            resetCursor();
            return true;
        }
        if (curr.getCustomer().getPriority() < priority) {
            if (prev == null) {
                newNode.setLink(head);
                head = newNode;
            } else {
                newNode.setLink(curr);
                prev.setLink(newNode);
            }
            resetCursor();
            return true;
        }
        // equal priority: after last node with same priority
        while (curr.getLink() != null && curr.getLink().getCustomer().getPriority() == priority) {
            curr = curr.getLink();
        }
        newNode.setLink(curr.getLink());
        curr.setLink(newNode);
        if (newNode.getLink() == null)
            tail = newNode;
        resetCursor();
        return true;
    }

    // d)
    public void displayLine() {
        if (isEmpty()) {
            System.out.println("The waiting line is currently empty.");
            System.out.println("Number of people in line: 0");
            return;
        }
        Node nodePrt = head;
        int i = 1;
        while (nodePrt != null) {
            Customer currCustomer = nodePrt.getCustomer();
            System.out.println("Position " + i + ": " + currCustomer.getName() + ", Priority " + currCustomer.getPriority());
            i++;
            nodePrt = nodePrt.getLink();
        }
        System.out.println("Number of people in line: " + count());
    }

    // Extensions
    public boolean removeCustomerByName(String name) {
        if (isEmpty()) {
            return false;
        }
        resetCursor();
        if (!moveCursorToCustomer(name)) {
            return false;
        }
        if (head == cursor) {
            head = head.getLink();
            if (head == null) {
                tail = null;
            }
            cursor = head;
            return true;
        }
        Node nodePtr = head;
        while (nodePtr.getLink() != cursor) {
            nodePtr = nodePtr.getLink();
        }
        nodePtr.setLink(cursor.getLink());
        if (nodePtr.getLink() == null) {
            tail = nodePtr;
        }
        cursor = head;
        return true;
    }

    public boolean moveCursorToCustomer(String name) {
        if (isEmpty()) {
            return false;
        }
        cursor = head;
        while (cursor != null) {
            if (cursor.getCustomer().getName().equals(name)) {
                return true;
            }
            cursor = cursor.getLink();
        }
        return false;
    }

    // Same data as displayLine(), but priority is shown first (easy to scan urgency).
    public void advancedDisplayLine() {
        Node nodePtr = head;
        int i = 1;
        while (nodePtr != null) {
            Customer customer = nodePtr.getCustomer();
            System.out.println("  Priority " + customer.getPriority() + "  |  " + customer.getName()
                    + "  |  Place " + i + " in line");
            i++;
            nodePtr = nodePtr.getLink();
        }
    }
}
