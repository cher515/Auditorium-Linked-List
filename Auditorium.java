import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

class Auditorium<T> {
    // Variables to keep track of the best available seats
    public Node<Seat> bestStartSeat = null;
    private Node<Seat> first; // The first node of the auditorium
    public int totSeatsRow = 0;
    public int totRows = 0;
    public int seats = 0;
    public int tickets = 0;

    // Constructor for the Auditorium class
    public Auditorium() {
        first = null; // Initialize the first node to null
    }

    // Method to read the auditorium layout from a file and create a linked list structure
    public void set_Auditorium(String filename) {
        // Reset the auditorium
        first = null;

        try {
            // Open a file for reading
            BufferedReader br = new BufferedReader(new FileReader(filename));
            String line;
        
            // Variables to manage the auditorium layout
            Node<Seat> currentRow = null;
            Node<Seat> previousRow = null;
        
            // Initialize row count and current node pointer
            int rowCount = 0;
            Node<Seat> currentNode = first; // Assuming "first" is a member variable of the Auditorium class
        
            // Read each line from the file
            while ((line = br.readLine()) != null) {
                int colCount = 0; // Initialize column count for each row
        
                // Flag to track the first element of a row
                boolean firstElementOfRow = true;
        
                // Iterate through each character (seat) in the line
                for (char seatChar : line.toCharArray()) {
                    // Create a Seat object with row, seat, and ticket type information
                    Seat seat = new Seat(rowCount, (char) (colCount + 65), seatChar);
                    
                    // Create a new Node with the Seat object
                    Node<Seat> newNode = new Node<Seat>(seat);
        
                    // Check if it's the first element of the row
                    if (firstElementOfRow) {
                        if (first == null) {
                            // If it's the first element overall, set it as the first node
                            currentNode = newNode;
                            first = currentNode;
                            previousRow = first;
                        }
                        currentRow = newNode; // Set the current row's node
                        currentNode = currentRow; // Move the current node pointer to the current row
                        firstElementOfRow = false; // Update the flag
                    } else {
                        // If not the first element, set the new node as the next node in the row
                        currentNode.setNext(newNode);
                        currentNode = newNode; // Move the current node pointer to the new node
                    }
        
                    // Update the total number of seats in a row if it's the second row
                    if (rowCount == 1) {
                        totSeatsRow += 1;
                    }
        
                    colCount++; // Move to the next column (seat)
                }
        
                // Connect the previous row to the current row (linking rows)
                if (rowCount != 0) {
                    previousRow.setDown(currentRow);
                }
        
                // Update the previous row to the current row for the next iteration
                previousRow = currentRow;
        
                // Move to the next row
                rowCount++;
            }
        
            // Update the total number of rows in the auditorium
            totRows = rowCount;
        
            // Close the BufferedReader
            br.close();
        } catch (IOException e) {
            // Handle IO exception (e.g., file not found)
            e.printStackTrace();
        }
        
    }
    // Method to print the auditorium layout
    public void printAuditorium() {
        Node<Seat> current = first; // Initialize a pointer to the first node
        tickets = 0; // Initialize the count of tickets
        seats = 0; // Initialize the count of seats
        int numSeats = 0; // Variable to be determined later (currently set to 0)

        // Check if the auditorium is empty
        if (current == null) {
            System.out.println("Auditorium Object Empty"); // Display a message for an empty auditorium
            return;
        }

        Node<Seat> currentRow = first; // Initialize a pointer to the first node of the current row
        int rowNumber = 0; // Initialize the row number

        System.out.println("\n\nAuditorium Seating"); // Display a header for the auditorium seating

        // Display the seat characters at the top of the auditorium
        for (char c = 'A'; c < 'A' + numSeats; c++) {
            System.out.print(c);
        }

        // Loop through each row of the auditorium
        while (currentRow != null) {
            rowNumber++; // Increment the row number
            System.out.println(); // Move to a new line for the new row

            // Loop through each seat in the current row
            while (current != null) {
                Seat s = current.getPayload(); // Get the Seat object from the current node

                // Check if the seat is reserved (ticket type is not '.')
                if (s.getTicketType() != '.') {
                    System.out.print('#'); // Display '#' for a reserved seat
                    tickets++; // Increment the count of tickets
                    seats++; // Increment the count of seats
                } else {
                    System.out.print(s.getTicketType()); // Display the ticket type (empty seat)
                    seats++; // Increment the count of seats
                }

                current = current.getNext(); // Move to the next node (seat) in the current row
            }

            currentRow = currentRow.getDown(); // Move to the next row
            current = currentRow; // Reset the pointer to the first node of the new row
    }
}

    // Method to check if seats are available in the auditorium
    public boolean checkAuditorium(int tt, int row, int seat) {
        // Check if the auditorium is empty
        if (first == null) {
            return false;
        }
    
        Node<Seat> current = first; // Initialize a pointer to the first node of the auditorium
    
        int i = 1;
        // Move the pointer to the specified row
        while (i < row) {
            current = current.getDown();
            i++;
        }
    
        int asciiSeat = 65; // ASCII value for the character 'A'
        // Move the pointer to the specified seat in the row
        while (asciiSeat < seat) {
            current = current.getNext();
            asciiSeat++;
        }
    
        i = 1;
        // Check the availability of the specified number of consecutive seats in the row
        while (i <= tt) {
            if (current.getPayload().getTicketType() != '.') {
                return false; // Return false if any seat in the range is already reserved
            } else {
                current = current.getNext(); // Move to the next seat
                i++;
            }
        }
        return true; // Return true if all seats in the range are available
    }

    // Method to reserve seats in the auditorium
    public void bookAuditorium(int row, int seat, int a, int c, int s) {
        Node<Seat> current = first; // Initialize a pointer to the first node
    
        int i = 1;
    
        // Move the pointer to the specified row in the auditorium
        while (i < row) {
            current = current.getDown();
            i++;
        }
    
        int asciiSeat = 65;
    
        // Move the pointer to the specified starting seat character in the row
        while (asciiSeat < seat) {
            current = current.getNext();
            asciiSeat++;
        }
    
        // Book adult seats
        for (i = 0; i < a; i++) {
            current.getPayload().setTicketType('A'); // Set the ticket type to 'A' (adult)
            current = current.getNext(); // Move to the next seat
        }
    
        // Book child seats
        for (i = 0; i < c; i++) {
            current.getPayload().setTicketType('C'); // Set the ticket type to 'C' (child)
            current = current.getNext(); // Move to the next seat
        }
    
        // Book senior seats
        for (i = 0; i < s; i++) {
            current.getPayload().setTicketType('S'); // Set the ticket type to 'S' (senior)
            current = current.getNext(); // Move to the next seat
        }
    }

    // Method to find the best available seats in the auditorium
    public Node<Seat> bestAvailable(int totalSeatsRequired, int totAdults, int totChild, int totSeniors) {
        bestStartSeat = null; // Initialize the starting seat for the best available seats
        int bestRow = 0; // Initialize the best row number
    
        Node<Seat> currentRow = first; // Start at the first row
        int currentRowNumber = 1; // Initialize the current row number
        double bestDistance = Double.MAX_VALUE; // Initialize the best distance
    
        // Iterate through each row in the auditorium
        while (currentRow != null) {
            Node<Seat> currentSeat = currentRow; // Start at the first seat in the row
            int currentSeatNumber = 1; // Initialize the current seat number
    
            System.out.println("New row");
    
            // Iterate through each seat in the row
            while (currentSeat != null) {
                if (currentSeat.getPayload().getTicketType() == '.') { // Check if the seat is available
                    Node<Seat> endSeat = currentSeat; // Initialize the end seat for checking available consecutive seats
                    int availableSeats = 0; // Initialize the count of available consecutive seats
    
                    // Check for consecutive available seats
                    while (endSeat != null && availableSeats < totalSeatsRequired) {
                        if (endSeat.getPayload().getTicketType() == '.') {
                            availableSeats++;
                            endSeat = endSeat.getNext();
                        } else {
                            break;
                        }
                    }
    
                    // Check if the current row has enough available seats
                    if (availableSeats == totalSeatsRequired) {
                        // Calculate the center of the selection
                        double selectionCenterX = currentSeatNumber + (totalSeatsRequired - 1) / 2.0;
                        double selectionCenterY = currentRowNumber;
    
                        // Calculate the center of the auditorium
                        double auditoriumCenterX = (totSeatsRow + 1) / 2.0;
                        double auditoriumCenterY = (totRows + 1) / 2.0;
    
                        // Calculate the Euclidean distance between the two centers
                        double distance = Math.sqrt(Math.pow(selectionCenterX - auditoriumCenterX, 2)
                                + Math.pow(selectionCenterY - auditoriumCenterY, 2));
    
                        // Update the best available seats if they are closer to the center
                        if (distance < bestDistance) {
                            System.out.println(currentSeatNumber);
                            bestStartSeat = currentSeat;
                            bestRow = currentRowNumber;
                            bestDistance = distance;
                            System.out.println("Distance: " + bestDistance);
                            System.out.print("Seat: " + bestStartSeat.getPayload().getRow());
                            System.out.println((char) bestStartSeat.getPayload().getSeat());
                        } else if (distance == bestDistance) {
                            // If the distances are equal, prioritize seats closer to the center of the auditorium
                            if (Math.abs(selectionCenterY - auditoriumCenterY) < Math.abs((bestRow - auditoriumCenterY))) {
                                bestStartSeat = currentSeat;
                                bestRow = currentRowNumber;
                                bestDistance = distance;
                            } else if (Math.abs(selectionCenterY - auditoriumCenterY) == Math
                                    .abs((bestRow - auditoriumCenterY))) {
                                // If rows are equidistant, prioritize seats in lower rows
                                if (selectionCenterY < bestRow) {
                                    bestStartSeat = currentSeat;
                                    bestRow = currentRowNumber;
                                    bestDistance = distance;
                                }
                            }
                        }
    
                        System.out.println("Found better Seat");
                        System.out.print((char) currentSeat.getPayload().getSeat());
                        System.out.println(currentSeat.getPayload().getRow());
                    }
                }
                currentSeatNumber++;
                currentSeat = currentSeat.getNext(); // Move to the next seat
            }
    
            currentRowNumber++;
            currentRow = currentRow.getDown(); // Move to the next row
        }
        System.out.print("Test: " + bestStartSeat.getPayload().getRow());
        System.out.println((char) bestStartSeat.getPayload().getSeat());
        System.out.print("Best Available Seats: ");
        System.out.print(bestStartSeat.getPayload().getRow() + 1);
        System.out.print((char) bestStartSeat.getPayload().getSeat());
        System.out.print(" - ");
        System.out.print(bestStartSeat.getPayload().getRow() + 1);
        System.out.println((char) (bestStartSeat.getPayload().getSeat() + totalSeatsRequired - 1));
    
        return bestStartSeat; // Return the starting seat node for the best available seats
    }

    // Method to write the auditorium layout to a file
    public void writeToFile() {
        try {
            calculate_Output(); // Calculate and update auditorium statistics
    
            // Create a FileWriter to write to the file "A1.txt"
            FileWriter fileWriter = new FileWriter("A1.txt");
            // Create a PrintWriter to write formatted text to the file
            PrintWriter writer = new PrintWriter(fileWriter);
    
            Node<Seat> current = first; // Initialize a pointer to the first node
            Node<Seat> currentRow = first; // Initialize a pointer to the first row
    
            // Iterate through each row in the auditorium
            while (currentRow != null) {
                // Iterate through each seat in the row
                while (current != null) {
                    Seat s = current.getPayload(); // Get the seat object
                    writer.print(s.getTicketType()); // Write the seat's ticket type to the file
                    current = current.getNext(); // Move to the next seat
                }
                writer.println(); // Move to the next line after completing a row
                currentRow = currentRow.getDown(); // Move to the next row
                if (currentRow == null)
                    System.out.print("currentrow = null"); // Debugging statement
                current = currentRow; // Reset the pointer to the beginning of the next row
            }
    
            // Close the PrintWriter and FileWriter to release resources
            writer.close();
            fileWriter.close();
        } catch (Exception e) {
            e.printStackTrace(); // Print the exception details if an error occurs
        }
    }

    // Method to calculate and print total sales and other statistics
    public void calculate_Output() {
        Node<Seat> current = first; // Initialize a pointer to the first node
        Node<Seat> current_row = first; // Initialize a pointer to the first row
        int sold_adult = 0, sold_child = 0, sold_senior = 0; // Initialize counters for sold tickets
    
        // Iterate through each row in the auditorium
        while (current_row != null) {
            current = current_row;
            // Iterate through each seat in the row
            while (current != null) {
                // Check the ticket type and update the corresponding counter
                if (current.getPayload().getTicketType() == 'A') {
                    sold_adult++;
                } else if (current.getPayload().getTicketType() == 'C') {
                    sold_child++;
                } else if (current.getPayload().getTicketType() == 'S') {
                    sold_senior++;
                }
                current = current.getNext(); // Move to the next seat
            }
            current_row = current_row.getDown(); // Move to the next row
        }
    
        // Print ticket sales statistics
        System.out.println("Ticket sales have been calculated.");
        System.out.println("Total Seats: " + seats);
        System.out.println("Total Tickets: " + (sold_adult + sold_child + sold_senior));
        System.out.println("Adult Tickets: " + sold_adult);
        System.out.println("Child Tickets: " + sold_child);
        System.out.println("Senior Tickets: " + sold_senior);
    
        // Calculate and print total sales with formatted currency
        double totalSales = sold_adult * 10 + sold_child * 5 + sold_senior * 7.5;
        String totSales = String.format("%.2f", totalSales);
        System.out.println("Total Sales: $" + totSales);
    }
}
