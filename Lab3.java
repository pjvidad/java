import java.util.Scanner;

public class Lab3 {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        // base
        System.out.print("Enter Base: ");
        int base = input.nextInt();

        // weight
        System.out.print("Enter Weight: ");
        int weight = input.nextInt();

        // dimension
        System.out.print("Enter number of Dimensions: ");
        int dimension = input.nextInt();

        input.close();
        
        // upper bounds
        int[] upperBounds = new int[dimension];
        for (int i = 0; i < dimension; i++) {
            System.out.print("Enter upper bound " + (i + 1) + " : ");
            upperBounds[i] = input.nextInt();
        }

        // indices
        int[] indices = new int[dimension];
        for (int j = 0; j < dimension; j++) {
            System.out.print("Enter array index " + (j + 1) + " : ");
            indices[j] = input.nextInt();
        }

        // assign method to a variable
        long rowMajorAddress = calculateRMSAddress(base, weight, upperBounds, indices);

        //displays output
        System.out.println("Row-major address: " + rowMajorAddress);
    }

    public static long calculateRMSAddress(int base, int weight, int[] upperBounds, int[] indices) {
        int dimensions = upperBounds.length;
        int product = 1;

        // calculate product of upper bounds
        for (int i = 1; i < dimensions; i++) {
            product *= upperBounds[i];
        }

        // multiplies index and add base and weight
        long rowMajorAddress = base;
        for (int i = 0; i < dimensions; i++) {
            rowMajorAddress += weight * (indices[i] * product);
            if (i + 1 < dimensions) {
                product /= upperBounds[i + 1];
            }
        }

        return rowMajorAddress;
    }
}
