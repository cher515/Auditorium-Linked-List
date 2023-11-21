//CHERYL DE MELLO
//cxd220019
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the filename: ");
        String fileName = scanner.nextLine();
        Auditorium<Seat> aud_obj = new Auditorium<>(); // Create an Auditorium object
        aud_obj.set_Auditorium(fileName); // Set the auditorium to display

        boolean exit = false;
        System.out.println("Main Menu:");
        System.out.println("Display Auditorium");
        aud_obj.printAuditorium();
        String choice;

        do {
            // Print main menu giving option to user what they want to select
            System.out.println();
            System.out.println("1. Reserve Seats");
            System.out.println("2. Exit");
            System.out.print("Enter your choice: ");
            
            try {
                choice = scanner.next();
            } catch (InputMismatchException e) { //if the input is not a valid choice this will show
                System.out.println("Invalid input. Please enter a valid answer.");
                scanner.next(); // Clear the invalid input
                continue; // Restart the loop
            }
            
            switch (choice) {
                case "1":
                    // Ask user for row, seat, and quantities (adult, child, senior)

                    // Prompt the user to enter the row number
                System.out.print("Enter row: ");

                // Read the user input as a string
                String s = scanner.next();

                // Validate the input to ensure it is a single digit, a digit between 1 and the total rows,
                // and that the character is a digit
                while (s.length() != 1 || !Character.isDigit(s.charAt(0)) ||
                    ((int)s.charAt(0) - 48 < 1 || (int)s.charAt(0) - 48 > aud_obj.totRows)) {
                    System.out.println("Invalid input. Please enter a valid integer for row.");
                    
                    // Clear the invalid input and prompt the user again
                    scanner.nextLine();
                    s = scanner.next();
                }

                // Convert the validated character to an integer representing the row
                int row = (int)s.charAt(0) - 48;

                    System.out.print("Enter seat: ");
                    s = scanner.next();
                    while (s.length() != 1 || !Character.isAlphabetic(s.charAt(0))) {
                        System.out.println("Invalid input. Please enter a valid letter for seat.");
                        scanner.nextLine(); // Clear the invalid input
                        s = scanner.next();
                    }
                    char seat = s.charAt(0);

                    // Get quantities with validation
                    System.out.print("Enter adult quantity: ");
                    int adultQuantity = getValidIntInput(scanner);

                    System.out.print("Enter child quantity: ");
                    int childQuantity = getValidIntInput(scanner);

                    System.out.print("Enter senior quantity: ");
                    int seniorQuantity = getValidIntInput(scanner);

                    // Validate input for each input
                    // Check if the input values for row, seat, and quantities are valid
                    if (validateInput(row, seat, adultQuantity, childQuantity, seniorQuantity)) {

                        // Check if the required number of seats is available in the auditorium
                        boolean seatsAvailable = aud_obj.checkAuditorium(
                            adultQuantity + childQuantity + seniorQuantity, row, seat);

                        if (seatsAvailable) {
                            // Reserve seats if available
                            aud_obj.bookAuditorium(row, seat, adultQuantity, childQuantity, seniorQuantity);
                            System.out.println("Seats reserved successfully.");
                        } else {
                            // Find the best available seats if the requested seats are not available
                            Node<Seat> bestAvailable = aud_obj.bestAvailable(
                                adultQuantity + childQuantity + seniorQuantity, 
                                adultQuantity, childQuantity, seniorQuantity);

                            if (bestAvailable != null) {
                                // Ask the user if they want to reserve the best available seats
                                System.out.print("Best available seats found. Do you want to reserve them? (Y/N): ");
                                char choice2 = scanner.next().charAt(0);

                                if (choice2 == 'N' || choice2 == 'n') {
                                    System.out.println("Best available seats NOT reserved successfully.");
                                } else if (choice2 == 'Y' || choice2 == 'y') {
                                    // Reserve the best available seats and print the updated auditorium
                                    row = bestAvailable.getPayload().getRow();
                                    seat = (char) bestAvailable.getPayload().getSeat();
                                    aud_obj.bookAuditorium(row + 1, seat, adultQuantity, childQuantity, seniorQuantity);
                                    System.out.println("Best available seats reserved successfully.");
                                    aud_obj.printAuditorium(); // print the updated auditorium
                                }
                            } else {
                                System.out.println("No available seats found.");
                            }
                        }
                    } else {
                        System.out.println("Invalid input. Please try again.");
                    }

                    break;

                case "2":
                    exit = true;
                    break;

                default:
                    System.out.println("Pick a choice between 1 and 2");
            }
        } while (!exit);

        aud_obj.writeToFile(); // Write the updated auditorium to a file
        scanner.close();
    }

    private static boolean validateInput(int row, char seat, int adultQuantity, int childQuantity, int seniorQuantity) {
        if (row < 1 || adultQuantity < 0 || childQuantity < 0 || seniorQuantity < 0) {
            return false; // Invalid row number or negative quantities
        }

        // Additional check for seat being an alphabet character
        if (!Character.isAlphabetic(seat)) {
            return false; // Invalid seat (not an alphabet character)
        }

        return true;
    }

    private static int getValidIntInput(Scanner scanner) {
        while (true) {
            try {
                return scanner.nextInt();
            } catch (Exception e) {
                System.out.println("Invalid input. Please enter a valid integer.");
                scanner.nextLine(); // Clear the invalid input
            }
        }
    }
}
