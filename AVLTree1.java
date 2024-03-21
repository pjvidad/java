import java.util.Scanner;

class Node {
    int key, height;
    Node left, right;

    Node(int d) {
        key = d;
        height = 1;
    }
}

class AVLTree {
    Node root;

    int height(Node N) {
        if (N == null)
            return 0;
        return N.height;
    }

    int max(int a, int b) {
        return (a > b) ? a : b;
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

    int getBalance(Node N) {
        if (N == null)
            return 0;
        return height(N.left) - height(N.right);
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

    Node deleteNode(Node root, int key) {
        if (root == null) {
            return root;
        }

        if (key < root.key) {
            root.left = deleteNode(root.left, key);
        } else if (key > root.key) {
            root.right = deleteNode(root.right, key);
        } else {
            if (root.left == null || root.right == null) {
                Node temp = null;
                if (temp == root.left) {
                    temp = root.right;
                } else {
                    temp = root.left;
                }

                if (temp == null) {
                    temp = root;
                    root = null;
                } else {
                    root = temp;
                }
            } else {
                Node temp = minValueNode(root.right);
                root.key = temp.key;
                root.right = deleteNode(root.right, temp.key);
            }
        }

        if (root == null) {
            return root;
        }

        root.height = Math.max(height(root.left), height(root.right)) + 1;
        int balance = getBalance(root);

        if (balance > 1 && getBalance(root.left) >= 0) {
            return rightRotate(root);
        }

        if (balance > 1 && getBalance(root.left) < 0) {
            root.left = leftRotate(root.left);
            return rightRotate(root);
        }

        if (balance < -1 && getBalance(root.right) <= 0) {
            return leftRotate(root);
        }

        if (balance < -1 && getBalance(root.right) > 0) {
            root.right = rightRotate(root.right);
            return leftRotate(root);
        }

        return root;
    }

    Node minValueNode(Node node) {
        Node current = node;
        while (current.left != null) {
            current = current.left;
        }
        return current;
    }

    void preOrder(Node node) {
        if (node != null) {
            System.out.print(node.key + " ");
            preOrder(node.left);
            preOrder(node.right);
        }
    }

    void postOrder(Node node) {
        if (node != null) {
            postOrder(node.left);
            postOrder(node.right);
            System.out.print(node.key + " ");
        }
    }

    void inOrder(Node node) {
        if (node != null) {
            inOrder(node.left);
            System.out.print(node.key + " ");
            inOrder(node.right);
        }
    }

    private static int getHeight(Node root) {
        if (root == null) {
            return 0;
        }
        return Math.max(getHeight(root.left), getHeight(root.right)) + 1;
    }

    private static int[] createArrayRepresentation(Node root) {
        int height = getHeight(root);
        int[] arrayRepresentation = new int[(int) Math.pow(2, height) - 1];
        fillArrayRepresentation(root, arrayRepresentation, 0);
        return arrayRepresentation;
    }

    private static void fillArrayRepresentation(Node root, int[] array, int index) {
        if (root == null) {
            return;
        }
        array[index] = root.key;
        fillArrayRepresentation(root.left, array, 2 * index + 1);
        fillArrayRepresentation(root.right, array, 2 * index + 2);
    }
    
    public static void main(String[] args) {
    AVLTree tree = new AVLTree();
    Scanner scanner = new Scanner(System.in);

    while (true) {
        System.out.println("Choose an option:");
        System.out.println("1. Insert");
        System.out.println("2. Delete");
        System.out.println("3. Exit");

        int choice = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        switch (choice) {
            case 1:
                System.out.println("Enter an integer to insert into the AVL tree:");
                try {
                    int key = Integer.parseInt(scanner.nextLine());
                    tree.root = tree.insert(tree.root, key);
                } catch (NumberFormatException e) {
                    System.out.println("Invalid input. Please enter an integer.");
                }
                break;
            case 2:
                System.out.println("Enter an integer to delete from the AVL tree:");
                try {
                    int key = Integer.parseInt(scanner.nextLine());
                    tree.root = tree.deleteNode(tree.root, key);
                } catch (NumberFormatException e) {
                    System.out.println("Invalid input. Please enter an integer.");
                }
                break;
            case 3:
                System.out.println("Exiting program. Goodbye!");
                scanner.close();
                return;
            default:
                System.out.println("Invalid choice. Please enter a valid option (1, 2, or 3).");
        }

        int[] arrayRepresentation = createArrayRepresentation(tree.root);
        System.out.println("\nArray representation of the tree is : ");
        for (int i : arrayRepresentation) {
            System.out.print(i + " ");
        }

        System.out.println("\nPreorder traversal of constructed tree is : ");
        tree.preOrder(tree.root);

        System.out.println("\nPostorder traversal of constructed tree is : ");
        tree.postOrder(tree.root);

        System.out.println("\nInorder traversal of constructed tree is : ");
        tree.inOrder(tree.root);

        System.out.print("\nDo you want to try again? (y/n): ");
        String restartChoice = scanner.nextLine().toLowerCase();

        if (!restartChoice.equals("y")) {
            System.out.println("Exiting program. Goodbye!");
            scanner.close();
            return;
        }
    }
} 
}
