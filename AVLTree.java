import java.util.Scanner;

public class AVLTree {
    class Node {
        int key, height;
        Node left, right;

        Node(int d) {
            key = d;
            height = 1;
        }
    }

    Node root;

    int height(Node N) {
        return N == null ? 0 : N.height;
    }

    int max(int a, int b) {
        return (a > b) ? a : b;
    }

    int getBalance(Node N) {
        return (N == null) ? 0 : height(N.left) - height(N.right);
    }

    Node rightRotate(Node y) {
        Node x = y.left;
        Node T2 = x.right;
        x.right = y;
        y.left = T2;
        y.height = max(height(y.left), height(y.right)) + 1;
        x.height = max(height(x.left), height(x.right)) + 1;
        return x;
    }

    Node leftRotate(Node x) {
        Node y = x.right;
        Node T2 = y.left;
        y.left = x;
        x.right = T2;
        x.height = max(height(x.left), height(x.right)) + 1;
        y.height = max(height(y.left), height(y.right)) + 1;
        return y;
    }

    Node insert(Node node, int key) {
        if (node == null)
            return (new Node(key));
        if (key < node.key)
            node.left = insert(node.left, key);
        else if (key > node.key)
            node.right = insert(node.right, key);
        else
            return node;

        node.height = 1 + max(height(node.left), height(node.right));

        int balance = getBalance(node);

        if (balance > 1 && key < node.left.key)
            return rightRotate(node);
        if (balance < -1 && key > node.right.key)
            return leftRotate(node);
        if (balance > 1 && key > node.left.key) {
            node.left = leftRotate(node.left);
            return rightRotate(node);
        }
        if (balance < -1 && key < node.right.key) {
            node.right = rightRotate(node.right);
            return leftRotate(node);
        }
        return node;
    }

    Node minValueNode(Node node) {
        Node current = node;
        while (current.left != null)
            current = current.left;
        return current;
    }

    Node deleteNode(Node root, int key) {
        if (root == null)
            return root;
        if (key < root.key)
            root.left = deleteNode(root.left, key);
        else if (key > root.key)
            root.right = deleteNode(root.right, key);
        else {
            if ((root.left == null) || (root.right == null)) {
                Node temp = (root.left == null) ? root.right : root.left;
                if (temp == null) {
                    temp = root;
                    root = null;
                } else
                    root = temp;
            } else {
                Node temp = minValueNode(root.right);
                root.key = temp.key;
                root.right = deleteNode(root.right, temp.key);
            }
        }
        if (root == null)
            return root;
        root.height = max(height(root.left), height(root.right)) + 1;
        int balance = getBalance(root);
        if (balance > 1 && getBalance(root.left) >= 0)
            return rightRotate(root);
        if (balance > 1 && getBalance(root.left) < 0) {
            root.left = leftRotate(root.left);
            return rightRotate(root);
        }
        if (balance < -1 && getBalance(root.right) <= 0)
            return leftRotate(root);
        if (balance < -1 && getBalance(root.right) > 0) {
            root.right = rightRotate(root.right);
            return leftRotate(root);
        }
        return root;
    }

    void preOrder(Node node) {
        if (node != null) {
            System.out.print(node.key + " ");
            preOrder(node.left);
            preOrder(node.right);
        }
    }

    void inOrder(Node node) {
        if (node != null) {
            inOrder(node.left);
            System.out.print(node.key + " ");
            inOrder(node.right);
        }
    }

    void postOrder(Node node) {
        if (node != null) {
            postOrder(node.left);
            postOrder(node.right);
            System.out.print(node.key + " ");
        }
    }

    // Method to convert the AVL tree into an array
    public int[] toArray() {
        int height = heightRepresentation(root);
        int size = (int) Math.pow(2, height) - 1;
        int[] array = new int[size];
        toArrayUtil(root, array, 0);
        return array;
    }

    // Utility method to convert tree to array
    private void toArrayUtil(Node root, int[] array, int index) {
        if (root != null && index < array.length) {
            toArrayUtil(root.left, array, 2 * index + 1);
            toArrayUtil(root.right, array, 2 * index + 2);
            array[index] = root.key;
        }
    }

    // Calculate height of the tree
    private int heightRepresentation(Node root) {
        if (root == null)
            return 0;
        else {
            int leftHeight = height(root.left);
            int rightHeight = height(root.right);
            return Math.max(leftHeight, rightHeight) + 1;
        }
    }

    // Display the array representation of the AVL tree
    public void displayIndex() {
        int[] array = toArray();
        System.out.print("\n1D Array: [");
        for (int i = 0; i < array.length; i++) {
            System.out.print(array[i] + ", ");
        }
        System.out.println("]");
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean tryAgain = true;
    
        while (tryAgain) {
            AVLTree avl = new AVLTree(); // Create a new AVL tree for each attempt
    
            int choice = 0;
            while (choice != 3) {
                System.out.println("\nAVL Tree Operations:");
                System.out.println("1. Insert a node");
                System.out.println("2. Delete a node");
                System.out.println("3. Exit");
                System.out.print("Enter your choice: ");
                choice = scanner.nextInt();
                switch (choice) {
                    case 1:
                        System.out.print("Enter the value to insert: ");
                        int insertValue = scanner.nextInt();
                        avl.root = avl.insert(avl.root, insertValue);
                        System.out.println(insertValue + " inserted into the tree.");
                        break;
                    case 2:
                        System.out.print("Enter the value to delete: ");
                        int deleteValue = scanner.nextInt();
                        avl.root = avl.deleteNode(avl.root, deleteValue);
                        System.out.println(deleteValue + " deleted from the tree.");
                        break;
                    case 3:
                        System.out.println("Exiting...");
                        break;
                    default:
                        System.out.println("Invalid choice.");
                }
                avl.displayIndex();
            }
    
            System.out.println("\nPreorder traversal:");
            avl.preOrder(avl.root);
            System.out.println("\nInorder traversal:");
            avl.inOrder(avl.root);
            System.out.println("\nPostorder traversal:");
            avl.postOrder(avl.root);
    
            System.out.print("\nDo you want to try again? (y/n): ");
            char tryAgainChar = scanner.next().charAt(0);
            tryAgain = (tryAgainChar == 'y' || tryAgainChar == 'Y');
        }
    
        scanner.close();
    }
}    
