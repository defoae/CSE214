public class BST {
    // Data Fields
    private BTNode root;
    private BTNode pointer;

    // Constructor (no-arg | creates empty BST)
    public BST(){
        root = null;
    }

    // Helpers
    public boolean isEmpty(){
        return (root == null);
    }

    public String pointerData(){
        return pointer.getData();
    }

    private int compare(String a, String b){
        return a.compareToIgnoreCase(b);
    }

    // Traversals
    public void inOrder(){
        if(!isEmpty()) root.inOrder();
    }

    public void preOrder(){
        if(!isEmpty()) root.preOrder();
    }

    public void postOrder(){
        if(!isEmpty()) root.postOrder();
    }

    // Operations
    public void insert(String data){
        BTNode newNode = new BTNode(data);
        boolean done = false;
        BTNode cursor = root;
        if(isEmpty()){
            root = newNode;
        }
        else {
            while (!done) {
                if (compare(data, cursor.getData()) > 0) {
                    if (cursor.getRight() == null) {
                        cursor.setRight(newNode);
                        done = true;
                    } else cursor = cursor.getRight();
                }
                else if (compare(data, cursor.getData()) < 0) {
                    if (cursor.getLeft() == null) {
                        cursor.setLeft(newNode);
                        done = true;
                    } else cursor = cursor.getLeft();
                }
                else done = true;
            }
        }
    }

    public boolean search(String data){
        if(isEmpty()) return false;
        BTNode cursor = root;
        while(cursor != null && !cursor.getData().equalsIgnoreCase(data)){
            if(compare(data, cursor.getData()) > 0){
                cursor = cursor.getRight();
            }
            else cursor = cursor.getLeft();
        }
        if(cursor == null) return false;
        pointer = cursor;
        return true;
    }

    public boolean delete(String data){
        // Find node to remove and its parent
        BTNode cursor = root;
        BTNode parent = null;
        while(cursor != null && !cursor.getData().equalsIgnoreCase(data)){
            parent = cursor;
            if(compare(data, cursor.getData()) < 0){
                cursor = cursor.getLeft();
            }
            else cursor = cursor.getRight();
        }

        // Not found
        if(cursor == null) return false;

        // has 0 or 1 children
        else if(cursor.getLeft() == null || cursor.getRight() == null){
            BTNode replaceWith = (cursor.getLeft() != null) ? cursor.getLeft() : cursor.getRight();

            // Link parent (or root) to its replacement
            if(cursor == root){
                root = replaceWith;
            }
            else if(cursor == parent.getLeft()){
                parent.setLeft(replaceWith);
            }
            else {
                parent.setRight(replaceWith);
            }
        }
        // has 2 children
        else{
           // Copy the rightmost data into the cursor node.
           cursor.setData(cursor.getLeft().getRightmostData());
           // Then remove the rightmost node
           cursor.setLeft(cursor.getLeft().removeRightmost());
        }
        return true;
    }
}
