import java.util.Scanner;

class LabAct1 {
    public static void main(String[] args) {

    System.out.println("An Algorithm to Prepare to Sleep:");
    System.out.println("Start");
    System.out.println("Take a hot bath");
    System.out.println("Change into pajamas");
    System.out.println("Brush teeth");
    System.out.println("Floss teeth");
    System.out.println("Wear retainers");
    System.out.println("Apply cleanser and toner on my face");
    System.out.println("Go to bed");
    System.out.println("Read a book");

    Scanner input = new Scanner(System.in);
    System.out.print("Is today a weekday or a weekend? ");
    String answer = input.nextLine();
    input.close();

    if(answer.toLowerCase().equals("weekday")) {
    System.out.println("Turn off the lights");
    System.out.println("Pray");
    System.out.println("Go to sleep");
    System.out.println("End");
    }
    else if(answer.toLowerCase().equals("weekend")) {
    System.out.println("Play Stardew Valley until sleepy");
    System.out.println("Turn off the lights");
    System.out.println("Pray");
    System.out.println("Go to sleep");
    System.out.println("End");
    }
    else {
    System.out.println("'Weekday' or 'Weekend' option only! Run the code again.no");
    }
    }
    
}
