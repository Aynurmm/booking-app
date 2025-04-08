import java.util.Scanner;

public class ConsoleApp {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("""
                    1.Online-board
                    2.Show flight info
                    3.Search and book flight
                    4.Cancel booking
                    5.My flights
                    6.Exit""");

            System.out.println("Enter your choice:");
            int choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    System.out.println("Online-board");
                    break;
                case 2:
                    System.out.println("Show flight info");
                    break;
                case 3:
                    System.out.println("Search and book flight");
                    break;
                case 4:
                    System.out.println("Cancel booking");
                    break;
                case 5:
                    System.out.println("My flights");
                    break;
                case 6:
                    System.out.println("Exit");
                    break;
                default:
                    System.out.println("Invalid choice");
            }
        }
    }
}
