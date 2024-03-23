import java.util.*;

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

    Node minValueNode(Node node) {
        Node current = node;
        while (current.left != null)
            current = current.left;
        return current;
    }

    Node deleteDeepestNode(Node root) {
        if (root == null)
            return null;
        if (root.left == null && root.right == null)
            return null;
    
        Queue<Node> queue = new LinkedList<>();
        queue.add(root);
        Node deepestNode = null;
    
        while (!queue.isEmpty()) {
            deepestNode = queue.poll();
    
            if (deepestNode.left != null)
                queue.add(deepestNode.left);
            if (deepestNode.right != null)
                queue.add(deepestNode.right);
        }
    
        return deepestNode;
    }
    
    Node deleteNode(Node root, int key) {
        if (root == null)
            return root;
    
        if (key < root.key)
            root.left = deleteNode(root.left, key);
        else if (key > root.key)
            root.right = deleteNode(root.right, key);
        else {
            if (root.left == null)
                return root.right;
            else if (root.right == null)
                return root.left;
    
            root.key = maxValue(root.left);
    
            root.left = deleteNode(root.left, root.key);
        }
    
        root.height = 1 + Math.max(height(root.left), height(root.right));
    
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
    
    int maxValue(Node root) {
        int maxv = root.key;
        while (root.right != null) {
            maxv = root.right.key;
            root = root.right;
        }
        return maxv;
    }

    void preOrder(Node node, List<Integer> result) {
        if (node != null) {
            result.add(node.key);
            preOrder(node.left, result);
            preOrder(node.right, result);
        }
    }

    void inOrder(Node node, List<Integer> result) {
        if (node != null) {
            inOrder(node.left, result);
            result.add(node.key);
            inOrder(node.right, result);
        }
    }

    void postOrder(Node node, List<Integer> result) {
        if (node != null) {
            postOrder(node.left, result);
            postOrder(node.right, result);
            result.add(node.key);
        }
    }

    int getHeight(Node node) {
        if (node == null)
            return 0;
        return 1 + Math.max(getHeight(node.left), getHeight(node.right));
    }

    void printTreeAsArray(Node node, int level, List<Integer> result) {
        if (node == null) {
            result.add(0);
            return;
        }
        if (level == 1)
            result.add(node.key);
        else if (level > 1) {
            printTreeAsArray(node.left, level - 1, result);
            printTreeAsArray(node.right, level - 1, result);
        }
    }

    void printTree() {
        int height = getHeight(root);
        List<Integer> result = new ArrayList<>();
        for (int i = 1; i <= height; i++) {
            printTreeAsArray(root, i, result);
        }
        System.out.print("1-D array: ");
        System.out.println(result);

        System.out.print("Preorder Traversal: ");
        List<Integer> preorderResult = new ArrayList<>();
        preOrder(root, preorderResult);
        System.out.println(preorderResult);

        System.out.print("Inorder Traversal: ");
        List<Integer> inorderResult = new ArrayList<>();
        inOrder(root, inorderResult);
        System.out.println(inorderResult);

        System.out.print("Postorder Traversal: ");
        List<Integer> postorderResult = new ArrayList<>();
        postOrder(root, postorderResult);
        System.out.println(postorderResult);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean restartProgram = true;

        while (restartProgram) {
            AVLTree tree = new AVLTree();
            boolean finishTree = false;

            while (!finishTree) {
                System.out.println("Choose operation:");
                System.out.println("1. Insert");
                System.out.println("2. Delete");
                System.out.println("3. Exit");
                String choice = scanner.next();

                switch (choice) {
                    case "1":
                        System.out.println("Enter comma-separated integers to insert into the AVL tree:");
                        scanner.nextLine();
                        String input = scanner.nextLine();
                        String[] nums = input.split(",");

                        for (String num : nums) {
                            try {
                                int value = Integer.parseInt(num.trim());
                                tree.root = tree.insert(tree.root, value);
                            } catch (NumberFormatException e) {
                                System.out.println("Invalid input: " + num + ". Skipping...");
                            }
                        }
                        break;
                    case "2":
                        System.out.println("Enter comma-separated integers to delete from the AVL tree:");
                        scanner.nextLine();
                        String inputDelete = scanner.nextLine();
                        String[] numsDelete = inputDelete.split(",");

                        for (String num : numsDelete) {
                            try {
                                int value = Integer.parseInt(num.trim());
                                tree.root = tree.deleteNode(tree.root, value);
                            } catch (NumberFormatException e) {
                                System.out.println("Invalid input: " + num + ". Skipping...");
                            }
                        }
                        break;
                    case "3":
                        finishTree = true;
                        break;
                    default:
                        System.out.println("Invalid choice.");
                        break;
                }
            }

            tree.printTree();

            System.out.println("Do you want to try again? (Y/N)");
            String restartChoice = scanner.next();
            restartProgram = restartChoice.equalsIgnoreCase("Y");
            scanner.close();
        }
    }
}
