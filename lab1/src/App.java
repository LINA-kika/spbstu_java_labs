
import java.util.InputMismatchException;
import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        Hero superman = new Hero("superman");
        MoveBehavior ridingBehavior = new RidingAHorseBehavior();
        MoveBehavior walkBehavior = new WalkBehavior();
        MoveBehavior flyingBehavior = new FlyingBehavior();
        Scanner scanner = new Scanner(System.in);
        int index = 5;
        while (index != 0) {
            System.out.println("""
                    Choose index of move behavior:
                    1 - riding a horse;
                    2 - walk;
                    3 - fly
                    type 0 to quite""");
            try {
                index = scanner.nextInt();
            } catch (InputMismatchException exception) {
                System.out.println("ONLY INTEGERS ARE ALLOWED. TRY AGAIN");
                scanner.nextLine();
                continue;
            }
            switch (index) {
                case 0:
                    System.out.println("THE PROGRAM IS STOPPING");
                    break;
                case 1:
                    superman.setMoveBehavior(ridingBehavior);
                    superman.performMove();
                    break;
                case 2:
                    superman.setMoveBehavior(walkBehavior);
                    superman.performMove();
                    break;
                case 3:
                    superman.setMoveBehavior(flyingBehavior);
                    superman.performMove();
                    break;
                default:
                    System.out.println("Invalid behavior index is chosen. Try again");

            }
        }
        scanner.close();
    }
}

