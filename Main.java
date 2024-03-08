import java.util.*;

class TreeNode {
    int data;
    TreeNode left, right;

    TreeNode(int item) {
        data = item;
        left = right = null;
    }
}

class BinaryTree {
    TreeNode root;

    BinaryTree() {
        root = null;
    }

    void insert(int data) {
        root = insertRec(root, data);
    }

    TreeNode insertRec(TreeNode root, int data) {
        if (root == null) {
            root = new TreeNode(data);
            return root;
        }

        if (data < root.data) {
            root.left = insertRec(root.left, data);
        } else if (data > root.data) {
            root.right = insertRec(root.right, data);
        }

        return root;
    }

    void deleteKey(int key, boolean deleteFromLeft) {
        root = deleteRec(root, key, deleteFromLeft);
    }

    TreeNode deleteRec(TreeNode root, int key, boolean deleteFromLeft) {
        if (root == null) {
            return root;
        }

        if (key < root.data) {
            root.left = deleteRec(root.left, key, deleteFromLeft);
        } else if (key > root.data) {
            root.right = deleteRec(root.right, key, deleteFromLeft);
        } else {
            if (root.left == null) {
                return root.right;
            } else if (root.right == null) {
                return root.left;
            }
            if (deleteFromLeft) {
                root.data = maxValue(root.left);
                root.left = deleteRec(root.left, root.data, deleteFromLeft);
            } else {
                root.data = minValue(root.right);
                root.right = deleteRec(root.right, root.data, deleteFromLeft);
            }
        }
        return root;
    }

    int maxValue(TreeNode root) {
        int maxv = root.data;
        while (root.right != null) {
            maxv = root.right.data;
            root = root.right;
        }
        return maxv;
    }

    int minValue(TreeNode root) {
        int minv = root.data;
        while (root.left != null) {
            minv = root.left.data;
            root = root.left;
        }
        return minv;
    }

    void inOrderTraversal(TreeNode node) {
        if (node != null) {
            inOrderTraversal(node.left);
            System.out.print(node.data + " ");
            inOrderTraversal(node.right);
        }
    }

    void preOrderTraversal(TreeNode node) {
        if (node != null) {
            System.out.print(node.data + " ");
            preOrderTraversal(node.left);
            preOrderTraversal(node.right);
        }
    }

    void postOrderTraversal(TreeNode node) {
        if (node != null) {
            postOrderTraversal(node.left);
            postOrderTraversal(node.right);
            System.out.print(node.data + " ");
        }
    }

    void toListWithPlaceholders() {
        if (root == null) {
            System.out.println("List with placeholders: []");
            return;
        }

        List<Object> list = new ArrayList<>();
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);

        while (!queue.isEmpty()) {
            TreeNode temp = queue.poll();
            if (temp != null) {
                list.add(temp.data);
                queue.add(temp.left);
                queue.add(temp.right);
            } else {
                list.add(null);
                list.add(null);
            }
        }
        System.out.println("1D Array Representation: " + list);
    }
}

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        BinaryTree binaryTree = new BinaryTree();
        char choice;

        do {
            int input;

            do {
                System.out.println("Choose the method: 1. Insert 2. Delete 3. End Traversal:");
                input = scanner.nextInt();

                switch (input) {
                    case 1:
                        System.out.print("Enter the value to insert: ");
                        int item = scanner.nextInt();
                        binaryTree.insert(item);
                        break;

                    case 2:
                        System.out.print("Enter the value to delete: ");
                        int item2 = scanner.nextInt();
                        System.out.print("Delete from:\n1. Left\n2. Right\nEnter your choice: ");
                        int deleteFrom = scanner.nextInt();
                        binaryTree.deleteKey(item2, deleteFrom == 1);
                        break;

                    case 3:
                        System.out.println("Exiting...");
                        break;

                    default:
                        System.out.println("Invalid input");
                        break;
                }
            } while (input != 3);

            binaryTree.toListWithPlaceholders();
            System.out.println("Pre-order traversal:");
            binaryTree.preOrderTraversal(binaryTree.root);
            System.out.println("\nIn-order traversal:");
            binaryTree.inOrderTraversal(binaryTree.root);
            System.out.println("\nPost-order traversal:");
            binaryTree.postOrderTraversal(binaryTree.root);

            System.out.println("\nDo you want to try again? (y/n): ");
            choice = scanner.next().charAt(0);
        } while (choice == 'y' || choice == 'Y');
        scanner.close();
    }
}
