import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Node {
    int key;
    Node left, right;

    public Node(int item) {
        key = item;
        left = right = null;
    }
}

public class BinarySearchTree {
    Node root;

    BinarySearchTree() {
        root = null;
    }

    void insert(int key) {
        root = insertRec(root, key);
    }

    Node insertRec(Node root, int key) {
        if (root == null) {
            root = new Node(key);
            return root;
        }

        if (key < root.key)
            root.left = insertRec(root.left, key);
        else if (key > root.key)
            root.right = insertRec(root.right, key);

        return root;
    }

    void inorder() {
        inorderRec(root);
        System.out.println();
    }

    void inorderRec(Node root) {
        if (root != null) {
            inorderRec(root.left);
            System.out.print(root.key + " ");
            inorderRec(root.right);
        }
    }

    void preorder() {
        preorderRec(root);
        System.out.println();
    }

    void preorderRec(Node root) {
        if (root != null) {
            System.out.print(root.key + " ");
            preorderRec(root.left);
            preorderRec(root.right);
        }
    }

    void postorder() {
        postorderRec(root);
        System.out.println();
    }

    void postorderRec(Node root) {
        if (root != null) {
            postorderRec(root.left);
            postorderRec(root.right);
            System.out.print(root.key + " ");
        }
    }

    List<Integer> generate1DRepresentation() {
        List<Integer> result = new ArrayList<>();
        generatePreorder(root, result);
        return result;
    }

    void generatePreorder(Node root, List<Integer> result) {
        if (root == null) {
            result.add(0);
            return;
        }
        result.add(root.key);
        if (root.left != null || root.right != null) {
            generatePreorder(root.left, result);
            generatePreorder(root.right, result);
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean tryAgain = true;

        while (tryAgain) {
            BinarySearchTree tree = new BinarySearchTree();

            System.out.println("Enter integer values separated by commas to insert into the binary search tree (press Enter to stop): ");
            String input = scanner.nextLine().trim();
            if (input.isEmpty()) {
                System.out.println("No input provided. Exiting...");
                return;
            }

            String[] values = input.split(",");
            List<Integer> inputValues = new ArrayList<>();
            for (String value : values) {
                try {
                    int num = Integer.parseInt(value.trim());
                    inputValues.add(num);
                } catch (NumberFormatException e) {
                    System.out.println("Invalid input! Please enter integers separated by commas.");
                    return;
                }
            }

            for (int value : inputValues) {
                tree.insert(value);
            }

            System.out.println("1D representation of the binary search tree:");
            List<Integer> representation = tree.generate1DRepresentation();
            System.out.println(representation);

            System.out.print("Preorder traversal: ");
            tree.preorder();

            System.out.print("Inorder traversal: ");
            tree.inorder();

            System.out.print("Postorder traversal: ");
            tree.postorder();

            System.out.println("Do you want to try again? (yes/no)");
            String choice = scanner.nextLine().toLowerCase();
            if (!choice.equals("yes")) {
                tryAgain = false;
            }
        }

        scanner.close();
    }
}

