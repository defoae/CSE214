public class Test {
    private static final String[] sampleInput = {
            "New York", "Chicago", "Los Angeles", "Boston", "Houston",
            "Miami", "Seattle", "Denver", "Atlanta", "Dallas"
    };

    private static BST buildTreeSample() {
        BST tree = new BST();
        for (String city : sampleInput) {
            tree.insert(city);
        }
        return tree;
    }

    private static void printTraversals(BST tree) {
        System.out.println("Inorder:");
        tree.inOrder();
        System.out.println();

        System.out.println("Preorder:");
        tree.preOrder();
        System.out.println();

        System.out.println("Postorder:");
        tree.postOrder();
        System.out.println();
    }

    public static void main(String[] args) {
        BST binarySearchTree = buildTreeSample();

        // Part A: BST Construction
        System.out.println("=== Part A: BST Construction ===");
        System.out.println();
        System.out.println("BST created from sample input city array and displayed by traversals.");
        printTraversals(binarySearchTree);

        // Part B: BST Operations (8 points)
        System.out.println("=== Part B: BST Operations ===");

        // 1. Insertion
        System.out.println("Insert: San Francisco");
        binarySearchTree.insert("San Francisco");
        System.out.println("Inorder after insertion:");
        binarySearchTree.inOrder();
        System.out.println();

        // 2. Search
        System.out.println("Search: Miami");
        if(binarySearchTree.search("Miami")){
            System.out.println("Found -> " + binarySearchTree.pointerData());
        } else {
            System.out.println("Not found");
        }

        System.out.println("Search: Austin");
        if(binarySearchTree.search("Austin")){
            System.out.println(binarySearchTree.pointerData());
        } else {
            System.out.println("Not found");
        }
        System.out.println();

        // 3. Deletion
        System.out.println("Deletion Case 1 (leaf node): delete Atlanta");
        binarySearchTree.delete("Atlanta");
        printTraversals(binarySearchTree);

        System.out.println("Deletion Case 2 (node with one child): delete Denver");
        binarySearchTree.delete("Denver");
        printTraversals(binarySearchTree);

        System.out.println("Deletion Case 3 (node with two children): delete Chicago");
        binarySearchTree.delete("Chicago");
        printTraversals(binarySearchTree);

        // Part C: Traversals
        System.out.println("=== Part C: Traversals ===");
        System.out.println("Final traversals after all the operations:");
        printTraversals(binarySearchTree);

        System.out.println("Why inorder is sorted:");
        System.out.println("Inorder performs Inorder on Left Subtree -> Visits Node -> performs Inorder on Right Subtree, so in a BST it outputs keys");
        System.out.println("in lexicographic order since left child's value is less than parent's and right child's value is greater than parent's.");
    }
}