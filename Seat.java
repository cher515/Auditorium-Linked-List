// Define a class named Seat to represent individual seats in an auditorium
public class Seat {
    // Private instance variables to store row, seat, and ticket type information
    private int row;
    private int seat;
    private char ticketType;

    // Constructor to initialize a Seat object with the given row, seat, and ticket type
    public Seat(int row, char seat, char ticketType) {
        this.row = row;
        this.seat = seat;
        this.ticketType = ticketType;
    }

    // Getter method for row of the seat
    public int getRow() {
        return row;
    }

    // Getter method for the seat number
    public int getSeat() {
        return seat;
    }

    // Getter method to get the ticket type of the seat
    public char getTicketType() {
        return ticketType;
    }

    // Setter method to update the ticket type of the seat
    public void setTicketType(char t) {
        this.ticketType = t;
    }

    // Setter method to update the seat number
    public void setSeat(int seat) {
        this.seat = seat;
    }

    // Setter method to update the row number
    public void setRow(int row) {
        this.row = row;
    }

    // Override the toString method to provide a custom string representation of the Seat object
    @Override
    public String toString() {
        // Return a string in the format: "row-seat (ticketType)"
        return row + "" + seat + " (" + ticketType + ")";
    }
}
