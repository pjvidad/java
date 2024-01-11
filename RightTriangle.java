import java.util.Scanner;

public class Main {
    public static void RightTriangle(int n) {
        for(int a = 0; a < n; a++) {
            
            for (int b = 0; b <= a; b++){
                System.out.print("* ");
            }

            System.out.println();
        }
    }

    public static void main(String[] args) {
        Scanner rows = new Scanner(System.in);
        System.out.print("Enter number of rows: ");
        int row = rows.nextInt();
        rows.close();

        RightTriangle(row);
    }
}
