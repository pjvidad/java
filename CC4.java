import java.util.*;

class TreeNode {
    int data;
    TreeNode left, right;

    public TreeNode(int data) {
        this.data = data;
        this.left = this.right = null;
    }
}

class CompleteBinaryTreeTraversalsWithRestart {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        do {
            System.out.print("Enter comma-separated values: ");
            String input = scanner.nextLine();

            String[] values = input.split(",");
            int[] userInput = new int[values.length];

            Set<Integer> seenValues = new HashSet<>();

            int validCount = 0;
            for (String value : values) {
                int intValue = Integer.parseInt(value.trim());
                if (intValue != 0 && seenValues.add(intValue)) {
                    userInput[validCount++] = intValue;
                }
            }

            int[] validInput = new int[validCount];
            System.arraycopy(userInput, 0, validInput, 0, validCount);

            TreeNode root = createBinarySearchTree(validInput);

            System.out.println("\n1-D Array Representation: ");
            int[] arrayRepresentation = createArrayRepresentation(root);
            for (int value : arrayRepresentation) {
                System.out.print(value + " ");
            }

            System.out.println("\nTraversals: ");
            System.out.print("Pre-order Traversal: ");
            preOrderTraversal(root);

            System.out.print("\nIn-order Traversal: ");
            inOrderTraversal(root);

            System.out.print("\nPost-order Traversal: ");
            postOrderTraversal(root);

            System.out.print("\nDo you want to enter new values? (yes/no): ");
            String restartChoice = scanner.nextLine().toLowerCase();

            if (!restartChoice.equals("yes")) {
                System.out.println("Exiting program. Goodbye!");
                break;
            }

        } while (true);
    }

    private static TreeNode createBinarySearchTree(int[] userInput) {
        if (userInput.length == 0) {
            return null;
        }

        TreeNode root = new TreeNode(userInput[0]);

        for (int i = 1; i < userInput.length; i++) {
            insertIntoBST(root, userInput[i]);
        }

        return root;
    }

    private static void insertIntoBST(TreeNode root, int value) {
        if (value < root.data) {
            if (root.left == null) {
                root.left = new TreeNode(value);
            } else {
                insertIntoBST(root.left, value);
            }
        } else if (value > root.data) {
            if (root.right == null) {
                root.right = new TreeNode(value);
            } else {
                insertIntoBST(root.right, value);
            }
        }
    }

    private static void inOrderTraversal(TreeNode root) {
        if (root != null) {
            inOrderTraversal(root.left);
            System.out.print(root.data + " ");
            inOrderTraversal(root.right);
        }
    }

    private static void preOrderTraversal(TreeNode root) {
        if (root != null) {
            System.out.print(root.data + " ");
            preOrderTraversal(root.left);
            preOrderTraversal(root.right);
        }
    }

    private static void postOrderTraversal(TreeNode root) {
        if (root != null) {
            postOrderTraversal(root.left);
            postOrderTraversal(root.right);
            System.out.print(root.data + " ");
        }
    }

    private static int[] createArrayRepresentation(TreeNode root) {
        int height = getHeight(root);
        int[] arrayRepresentation = new int[(int) Math.pow(2, height) - 1];
        fillArrayRepresentation(root, arrayRepresentation, 0);
        return arrayRepresentation;
    }

    private static void fillArrayRepresentation(TreeNode root, int[] array, int index) {
        if (root == null) {
            return;
        }

        array[index] = root.data;

        fillArrayRepresentation(root.left, array, 2 * index + 1);
        fillArrayRepresentation(root.right, array, 2 * index + 2);
    }

    private static int getHeight(TreeNode root) {
        if (root == null) {
            return 0;
        }

        int leftHeight = getHeight(root.left);
        int rightHeight = getHeight(root.right);

        return 1 + Math.max(leftHeight, rightHeight);
    }
}
