// Define a generic class named Node to represent a node in a matrix or linked structure
public class Node<T> {
    // Instance variables to represent the links and data associated with the node
    private Node<T> next = null;       // Link to the next node in the same row
    private Node<T> down = null;       // Link to the next node in the same column
    private Node<T> previous = null;   // Link to the previous node in the same row
    private T payload;                 // Data stored in the node, referred to as payload

    // Overloaded constructor to initialize a node with specific links and data
    public Node(T data, Node<T> nxtRow, Node<T> dwnCol, Node<T> prev) {
        // Set the payload (data) and links to the next, down, and previous nodes
        this.payload = data;
        next = nxtRow;
        down = dwnCol;
        this.previous = prev;
    }

    // Constructor to initialize a node with only a payload (data)
    public Node(T payload) {
        this.payload = payload;
    }

    // Getter method to retrieve the payload (data) stored in the node
    public T getPayload() {
        return payload;
    }

    // Getter method to retrieve the next node in the same row
    public Node<T> getNext() {
        return next;
    }

    // Getter method to retrieve the next node in the same column
    public Node<T> getDown() {
        return down;
    }

    // Getter method to retrieve the previous node in the same row
    public Node<T> getPrevious() {
        return previous;
    }

    // Setter method to update the link to the next node in the same row
    public void setNext(Node<T> next){
        this.next = next;
    }

    // Setter method to update the link to the next node in the same column
    public void setDown(Node<T> down) {
        this.down = down;
    }

    // Setter method to update the link to the previous node in the same row
    public void setPrevious(Node<T> previous){
        this.previous = previous;
    }
}
