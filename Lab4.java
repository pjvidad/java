import java.util.*;

public class Lab4 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Select Algorithm:\nPress 1 for Stack\nPress 2 for Queue");
        System.out.print("Enter input: ");
        int user_input = scanner.nextInt();

        if (user_input == 1) performStackOperations(scanner);
        else if (user_input == 2) performQueueOperations(scanner);
        else System.out.println("Invalid Choice! Press 1 for Stack, Press 2 for Queue. Try again.");

        scanner.close();
    }

    private static void performStackOperations(Scanner scanner) {
        Stack<Integer> stack = new Stack<>();
        int upperBound = getInput(scanner, "Enter upper bound: ");

        int operation;
        do {
            operation = getInput(scanner, "1. Push\n2. Pop\n3. Finish\nEnter operation: ");
            if (operation == 1) performPush(stack, scanner, upperBound);
            else if (operation == 2) performPop(stack);
        } while (operation != 3);
        System.out.println("Stack: " + stack);
    }

    private static void performQueueOperations(Scanner scanner) {
        Queue<Integer> queue = new LinkedList<>();
        int upperBound = getInput(scanner, "Enter upper bound: ");

        int operation;
        do {
            operation = getInput(scanner, "1. Enqueue\n2. Dequeue\n3. Finish\nEnter operation: ");
            if (operation == 1) performEnqueue(queue, scanner, upperBound);
            else if (operation == 2) performDequeue(queue);
        } while (operation != 3);
        System.out.println("Queue: " + queue);
    }

    private static int getInput(Scanner scanner, String message) {
        System.out.print(message);
        return scanner.nextInt();
    }

    private static void performPush(Stack<Integer> stack, Scanner scanner, int upperBound) {
        if (stack.size() < upperBound) {
            int int_push = getInput(scanner, "Enter input: ");
            stack.push(int_push);
            System.out.println("Pushed " + int_push + " into the stack");
        } else {
            System.out.println("Your stack is full.");
        }
    }

    private static void performPop(Stack<Integer> stack) {
        if (!stack.isEmpty()) System.out.println("Popped " + stack.pop() + " from the stack");
        else System.out.println("Your stack is empty.");
    }

    private static void performEnqueue(Queue<Integer> queue, Scanner scanner, int upperBound) {
        if (queue.size() < upperBound) {
            int int_enqueu = getInput(scanner, "Enter input: ");
            queue.add(int_enqueu);
            System.out.println("Enqueued " + int_enqueu + " into the queue");
        } else {
            System.out.println("Your queue is full.");
        }
    }

    private static void performDequeue(Queue<Integer> queue) {
        if (!queue.isEmpty()) System.out.println("Dequeued " + queue.remove() + " from the queue");
        else System.out.println("Your queue is empty.");
    }
}

