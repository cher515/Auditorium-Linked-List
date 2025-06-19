# 🎟️ Auditorium Seat Reservation System (Linked List Implementation)

This Java project simulates an **auditorium seat reservation system** using a **2D linked list** structure to manage and reserve seats efficiently.

📌 **Project Goal**  
To design and implement a dynamic seat reservation system that:
- Reads auditorium seating from input files
- Displays and updates seat availability
- Processes user reservations
- Tracks and reports seat occupancy and ticket sales

---

## 📁 Project Structure

- `Main.java` – Entry point that handles user interaction and menu navigation  
- `Auditorium.java` – Core class that builds and manages the 2D linked list of `Seat` objects  
- `Seat.java` – Defines the properties of each seat (type, status, location)  
- `Node.java` – Generic node used to link seats horizontally and vertically  
- `SeatReservation.java` – Handles logic related to selecting and reserving seats  
- `A1.txt`, `A1b.txt`, `A2.txt` – Sample auditorium layouts for testing  
- `audit.txt` – Generated report showing reservation statistics

---

## 🧠 Features

✅ Load and display an auditorium layout from file  
✅ Reserve best available seats based on ticket quantity  
✅ Mark adult, child, and senior tickets  
✅ Automatically update seating file and audit logs  
✅ Use of **custom linked list** structure (not arrays!)  
✅ Generate an end-of-session audit report

---

## 🚀 How to Run

1. **Compile the project** (ensure you have Java installed):

```bash
javac *.java
Run the program:

bash
Copy
Edit
java Main
Follow on-screen prompts to view or reserve seats.

📊 Audit Report
The audit.txt file is automatically generated after each run and includes:

Total seats

Reserved seats

Sales breakdown by ticket type (Adult, Child, Senior)

Total revenue

💡 Learning Concepts
Custom data structures (2D linked lists)

File I/O in Java

Object-oriented design

Menu-driven console UI

Input validation and state updates

📎 Sample Input Format
Each character represents a seat:

. – Empty seat

A – Adult ticket

C – Child ticket

S – Senior ticket

Example (A1.txt):

css
Copy
Edit
.......
A.C....
..S..A.
