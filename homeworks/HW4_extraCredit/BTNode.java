public class BTNode {
    // Data Fields
    private String data;
    private BTNode left;
    private BTNode right;

    // Constructor (takes some String data as a parameter)
    public BTNode(String data){
        this.data = data;
        left = null;
        right = null;
    }

    // Getters
    public String getData(){
        return data;
    }

    public BTNode getLeft(){
        return left;
    }

    public BTNode getRight(){
        return right;
    }

    // Setters
    public void setData(String data){
        this.data = data;
    }

    public void setLeft(BTNode left){
        this.left = left;
    }

    public void setRight(BTNode right){
        this.right = right;
    }

    // Traversals
    public void inOrder(){
        if(left != null) left.inOrder();
        System.out.println(data);
        if(right != null) right.inOrder();
    }

    public void preOrder(){
        System.out.println(data);
        if(left != null) left.preOrder();
        if(right != null) right.preOrder();
    }

    public void postOrder(){
        if(left != null) left.postOrder();
        if(right != null) right.postOrder();
        System.out.println(data);
    }

    // Helpers
    public String getRightmostData(){
        if (right == null) return data;
        return right.getRightmostData();
    }

    public BTNode removeRightmost(){
        if (right == null) return left;
        right = right.removeRightmost();
        return this;
    }
}
