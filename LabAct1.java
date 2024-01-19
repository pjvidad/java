import java.util.Scanner;

class LabAct1 {

    public static void main(String[] args) {

    System.out.println("My Unique Algorithm to Prepare to Sleep:");
    System.out.println("Start");
    System.out.println("1. Take a hot bath");

    System.out.println("Here are the items I need to take a bath:");
    String[] items = {"shampoo", "conditioner", "facial wash", "soap", "towel"};
    for (String item : items) {
        System.out.println("        *" + item);
    }

    System.out.println("2. Change into pajamas");
    System.out.println("3. Brush teeth");
    System.out.println("4. Floss teeth");
    System.out.println("5. Wear retainers");
    System.out.println("6. Apply cleanser and toner on my face");
    System.out.println("7. Go to bed");
    System.out.println("8. Turn on the lights");
    System.out.println("9. Read a book");

    Scanner input = new Scanner(System.in);
    System.out.print("Q: Is today a weekday or a weekend? ");
    String answer = input.nextLine();
    input.close();

    if(answer.toLowerCase().equals("weekday")) {
    System.out.println("10. Turn off the lights");
    System.out.println("11. Pray");
    System.out.println("12. Go to sleep");
    System.out.println("End");
    }
    else if(answer.toLowerCase().equals("weekend")) {
    System.out.println("10. Play Stardew Valley until sleepy");
    System.out.println("11. Turn off the lights");
    System.out.println("12. Pray");
    System.out.println("13. Go to sleep");
    System.out.println("End");
    }
    else {
    System.out.println("'Weekday' or 'Weekend' option only! Run the code again.");
    }


    }
}
