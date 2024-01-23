import java.util.Scanner;

class LabAct1 {
    public static int counter = 0; {
        counter++;
    }
    public static void main(String[] args) {

    System.out.println("My Unique Algorithm to Prepare to Sleep:");
    counter++;
    System.out.println("Start");
    counter++;
    System.out.println("1. Take a hot bath");
    counter++;

    System.out.println("Here are the items I need to take a bath:");
    counter++;
    String[] items = {"shampoo", "conditioner", "hair mask", "facial wash", "soap","loofa", "towel", "toothbrush", "toothpaste", "face moisturizer", "lotion"};
    counter++;
        
    for (int i = 0; i < items.length; i++) { counter++; counter++;
        System.out.println("        *" + items[i]); counter++;
    }

    System.out.println("2. Change into pajamas");
    counter++;
    System.out.println("3. Brush teeth");
    counter++;
    System.out.println("4. Floss teeth");
    counter++;
    System.out.println("5. Wear retainers");
    counter++;
    System.out.println("6. Apply cleanser and toner on my face");
    counter++;
    System.out.println("7. Go to bed");
    counter++;
    System.out.println("8. Turn on the lights");
    counter++;
    System.out.println("9. Read a book");
    counter++;

    Scanner input = new Scanner(System.in);
    counter++;
    System.out.print("Q: Is today a weekday or a weekend? ");
    counter++;
    String answer = input.nextLine();
    counter++;
    input.close();
    counter++;

    if(answer.toLowerCase().equals("weekday")) {
    System.out.println("10. Turn off the lights");
    counter++;
    System.out.println("11. Pray");
    counter++;
    System.out.println("12. Go to sleep");
    counter++;
    System.out.println("End");
    counter++;
    }
    else if(answer.toLowerCase().equals("weekend")) {
    System.out.println("10. Play Stardew Valley until sleepy");
    counter++;
    System.out.println("11. Turn off the lights");
    counter++;
    System.out.println("12. Pray");
    counter++;
    System.out.println("13. Go to sleep");
    counter++;
    System.out.println("End");
    counter++;
    }
    else {
    System.out.println("'Weekday' or 'Weekend' option only! Run the code again.");
    counter++;
    }
    System.out.print(counter);
    }
    
    
}
