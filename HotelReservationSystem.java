import java.io.*;
import java.util.*;

/*
 * TASK 4: HOTEL RESERVATION SYSTEM
 * 
 * Features:
 * 1. Search available rooms
 * 2. Room categorization - Standard, Deluxe, Suite
 * 3. Book a room
 * 4. Cancel a reservation
 * 5. Payment simulation
 * 6. View booking details
 * 7. File I/O for storing bookings and room availability
 * 8. OOP concepts
 */

// -------------------- ROOM CLASS --------------------
class Room {
    private int roomNumber;
    private String category;
    private double price;
    private boolean available;

    public Room(int roomNumber, String category, double price) {
        this.roomNumber = roomNumber;
        this.category = category;
        this.price = price;
        this.available = true;
    }

    public int getRoomNumber() {
        return roomNumber;
    }

    public String getCategory() {
        return category;
    }

    public double getPrice() {
        return price;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    @Override
    public String toString() {
        return "Room " + roomNumber +
                " | Category: " + category +
                " | Price: Rs." + price +
                " | Status: " + (available ? "Available" : "Booked");
    }
}


// -------------------- RESERVATION CLASS --------------------
class Reservation {
    private int reservationId;
    private String customerName;
    private String phoneNumber;
    private int roomNumber;
    private String roomCategory;
    private double amount;
    private String paymentStatus;

    public Reservation(int reservationId, String customerName,
                       String phoneNumber, int roomNumber,
                       String roomCategory, double amount,
                       String paymentStatus) {

        this.reservationId = reservationId;
        this.customerName = customerName;
        this.phoneNumber = phoneNumber;
        this.roomNumber = roomNumber;
        this.roomCategory = roomCategory;
        this.amount = amount;
        this.paymentStatus = paymentStatus;
    }

    public int getReservationId() {
        return reservationId;
    }

    public int getRoomNumber() {
        return roomNumber;
    }

    public String getCustomerName() {
        return customerName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public double getAmount() {
        return amount;
    }

    public String getPaymentStatus() {
        return paymentStatus;
    }

    public String getRoomCategory() {
        return roomCategory;
    }

    @Override
    public String toString() {
        return "Reservation ID: " + reservationId +
                " | Customer: " + customerName +
                " | Phone: " + phoneNumber +
                " | Room: " + roomNumber +
                " | Category: " + roomCategory +
                " | Amount: Rs." + amount +
                " | Payment: " + paymentStatus;
    }
}


// -------------------- PAYMENT CLASS --------------------
class Payment {

    public static boolean processPayment(double amount) {

        
        Scanner scanner = new Scanner(System.in);

        System.out.println("\n========== PAYMENT ==========");
        System.out.println("Amount to Pay: Rs." + amount);

        System.out.println("Select Payment Method:");
        System.out.println("1. UPI");
        System.out.println("2. Debit/Credit Card");
        System.out.println("3. Cash");

        System.out.print("Enter choice: ");
        int choice = scanner.nextInt();

        if (choice < 1 || choice > 3) {
            System.out.println("Invalid payment method.");
            return false;
        }

        System.out.println("\nProcessing payment...");

        // Payment simulation
        System.out.println("Payment successful!");

        return true;
    }
}


// -------------------- HOTEL SYSTEM CLASS --------------------
public class HotelReservationSystem {

    private static ArrayList<Room> rooms = new ArrayList<>();
    private static ArrayList<Reservation> reservations = new ArrayList<>();

    private static final String ROOM_FILE = "rooms.txt";
    private static final String RESERVATION_FILE = "reservations.txt";

    private static int nextReservationId = 1001;

    private static Scanner scanner = new Scanner(System.in);


    // -------------------- MAIN METHOD --------------------
    public static void main(String[] args) {

        loadRooms();
        loadReservations();

        while (true) {

            System.out.println("\n");
            System.out.println("==============================================");
            System.out.println("        🏨 HOTEL RESERVATION SYSTEM");
            System.out.println("==============================================");
            System.out.println("1. Search Available Rooms");
            System.out.println("2. Book a Room");
            System.out.println("3. Cancel Reservation");
            System.out.println("4. View Booking Details");
            System.out.println("5. View All Rooms");
            System.out.println("6. Exit");
            System.out.println("==============================================");

            System.out.print("Enter your choice: ");

            int choice;

            try {
                choice = scanner.nextInt();
                scanner.nextLine();
            } catch (Exception e) {
                System.out.println("Please enter a valid number.");
                scanner.nextLine();
                continue;
            }

            switch (choice) {

                case 1:
                    searchRooms();
                    break;

                case 2:
                    bookRoom();
                    break;

                case 3:
                    cancelReservation();
                    break;

                case 4:
                    viewBookingDetails();
                    break;

                case 5:
                    viewAllRooms();
                    break;

                case 6:
                    saveRooms();
                    saveReservations();

                    System.out.println("\nThank you for using Hotel Reservation System!");
                    System.out.println("Program exited successfully.");
                    return;

                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }


    // -------------------- INITIALIZE ROOMS --------------------
    private static void initializeRooms() {

        rooms.clear();

        // Standard Rooms
        rooms.add(new Room(101, "Standard", 1500));
        rooms.add(new Room(102, "Standard", 1500));
        rooms.add(new Room(103, "Standard", 1500));

        // Deluxe Rooms
        rooms.add(new Room(201, "Deluxe", 2500));
        rooms.add(new Room(202, "Deluxe", 2500));
        rooms.add(new Room(203, "Deluxe", 2500));

        // Suite Rooms
        rooms.add(new Room(301, "Suite", 4000));
        rooms.add(new Room(302, "Suite", 4000));
        rooms.add(new Room(303, "Suite", 4000));
    }


    // -------------------- SEARCH ROOMS --------------------
    private static void searchRooms() {

        System.out.println("\n========== SEARCH ROOMS ==========");

        System.out.println("Select Room Category:");
        System.out.println("1. Standard - Rs.1500");
        System.out.println("2. Deluxe   - Rs.2500");
        System.out.println("3. Suite    - Rs.4000");
        System.out.println("4. All Categories");

        System.out.print("Enter choice: ");

        int choice = scanner.nextInt();
        scanner.nextLine();

        String category = "";

        switch (choice) {

            case 1:
                category = "Standard";
                break;

            case 2:
                category = "Deluxe";
                break;

            case 3:
                category = "Suite";
                break;

            case 4:
                category = "All";
                break;

            default:
                System.out.println("Invalid category.");
                return;
        }

        boolean found = false;

        System.out.println("\n---------- AVAILABLE ROOMS ----------");

        for (Room room : rooms) {

            if (room.isAvailable() &&
                    (category.equals("All") ||
                     room.getCategory().equalsIgnoreCase(category))) {

                System.out.println(room);
                found = true;
            }
        }

        if (!found) {
            System.out.println("No rooms available in the selected category.");
        }
    }


    // -------------------- BOOK ROOM --------------------
    private static void bookRoom() {

        System.out.println("\n========== BOOK A ROOM ==========");

        // Display available rooms first
        System.out.println("\nAvailable Rooms:");

        boolean availableRoomFound = false;

        for (Room room : rooms) {

            if (room.isAvailable()) {
                System.out.println(room);
                availableRoomFound = true;
            }
        }

        if (!availableRoomFound) {
            System.out.println("Sorry, no rooms are currently available.");
            return;
        }

        System.out.print("\nEnter Room Number to Book: ");
        int roomNumber = scanner.nextInt();
        scanner.nextLine();

        Room selectedRoom = null;

        for (Room room : rooms) {

            if (room.getRoomNumber() == roomNumber) {
                selectedRoom = room;
                break;
            }
        }

        if (selectedRoom == null) {
            System.out.println("Room not found.");
            return;
        }

        if (!selectedRoom.isAvailable()) {
            System.out.println("Sorry, this room is already booked.");
            return;
        }

        System.out.print("Enter Customer Name: ");
        String customerName = scanner.nextLine();

        System.out.print("Enter Phone Number: ");
        String phoneNumber = scanner.nextLine();

        System.out.println("\n========== BOOKING SUMMARY ==========");
        System.out.println("Customer Name : " + customerName);
        System.out.println("Phone Number  : " + phoneNumber);
        System.out.println("Room Number   : " + selectedRoom.getRoomNumber());
        System.out.println("Category      : " + selectedRoom.getCategory());
        System.out.println("Price         : Rs." + selectedRoom.getPrice());

        // Payment simulation
        boolean paymentSuccessful =
                Payment.processPayment(selectedRoom.getPrice());

        if (!paymentSuccessful) {
            System.out.println("Booking cancelled because payment failed.");
            return;
        }

        int reservationId = nextReservationId++;

        Reservation reservation = new Reservation(
                reservationId,
                customerName,
                phoneNumber,
                selectedRoom.getRoomNumber(),
                selectedRoom.getCategory(),
                selectedRoom.getPrice(),
                "PAID"
        );

        reservations.add(reservation);

        // Mark room as booked
        selectedRoom.setAvailable(false);

        // Save data to files
        saveRooms();
        saveReservations();

        System.out.println("\n==============================================");
        System.out.println("        ✅ BOOKING SUCCESSFUL");
        System.out.println("==============================================");
        System.out.println("Reservation ID : " + reservationId);
        System.out.println("Customer Name  : " + customerName);
        System.out.println("Room Number    : " + selectedRoom.getRoomNumber());
        System.out.println("Category       : " + selectedRoom.getCategory());
        System.out.println("Amount Paid    : Rs." + selectedRoom.getPrice());
        System.out.println("Payment Status : PAID");
        System.out.println("==============================================");
    }


    // -------------------- CANCEL RESERVATION --------------------
    private static void cancelReservation() {

        System.out.println("\n========== CANCEL RESERVATION ==========");

        if (reservations.isEmpty()) {
            System.out.println("No reservations found.");
            return;
        }

        System.out.print("Enter Reservation ID: ");
        int reservationId = scanner.nextInt();
        scanner.nextLine();

        Reservation reservationToCancel = null;

        for (Reservation reservation : reservations) {

            if (reservation.getReservationId() == reservationId) {
                reservationToCancel = reservation;
                break;
            }
        }

        if (reservationToCancel == null) {
            System.out.println("Reservation not found.");
            return;
        }

        // Make the room available again
        for (Room room : rooms) {

            if (room.getRoomNumber() ==
                    reservationToCancel.getRoomNumber()) {

                room.setAvailable(true);
                break;
            }
        }

        reservations.remove(reservationToCancel);

        saveRooms();
        saveReservations();

        System.out.println("\nReservation cancelled successfully.");
        System.out.println("Room " +
                reservationToCancel.getRoomNumber() +
                " is now available.");

        System.out.println("Refund simulation: Rs." +
                reservationToCancel.getAmount() +
                " will be processed.");
    }


    // -------------------- VIEW BOOKING DETAILS --------------------
    private static void viewBookingDetails() {

        System.out.println("\n========== BOOKING DETAILS ==========");

        if (reservations.isEmpty()) {
            System.out.println("No bookings available.");
            return;
        }

        System.out.print("Enter Reservation ID: ");
        int reservationId = scanner.nextInt();
        scanner.nextLine();

        boolean found = false;

        for (Reservation reservation : reservations) {

            if (reservation.getReservationId() == reservationId) {

                System.out.println("\n------------------------------------------");
                System.out.println("           BOOKING DETAILS");
                System.out.println("------------------------------------------");

                System.out.println("Reservation ID : " +
                        reservation.getReservationId());

                System.out.println("Customer Name  : " +
                        reservation.getCustomerName());

                System.out.println("Phone Number   : " +
                        reservation.getPhoneNumber());

                System.out.println("Room Number    : " +
                        reservation.getRoomNumber());

                System.out.println("Room Category  : " +
                        reservation.getRoomCategory());

                System.out.println("Amount         : Rs." +
                        reservation.getAmount());

                System.out.println("Payment Status : " +
                        reservation.getPaymentStatus());

                System.out.println("------------------------------------------");

                found = true;
                break;
            }
        }

        if (!found) {
            System.out.println("Reservation not found.");
        }
    }


    // -------------------- VIEW ALL ROOMS --------------------
    private static void viewAllRooms() {

        System.out.println("\n========== ALL HOTEL ROOMS ==========");

        for (Room room : rooms) {
            System.out.println(room);
        }
    }


    // -------------------- SAVE ROOMS --------------------
    private static void saveRooms() {

        try (BufferedWriter writer =
                     new BufferedWriter(new FileWriter(ROOM_FILE))) {

            for (Room room : rooms) {

                writer.write(
                        room.getRoomNumber() + "," +
                        room.getCategory() + "," +
                        room.getPrice() + "," +
                        room.isAvailable()
                );

                writer.newLine();
            }

        } catch (IOException e) {

            System.out.println(
                    "Error saving room data: " + e.getMessage());
        }
    }


    // -------------------- LOAD ROOMS --------------------
    private static void loadRooms() {

        File file = new File(ROOM_FILE);

        // If rooms file does not exist, create default rooms
        if (!file.exists()) {

            initializeRooms();
            saveRooms();
            return;
        }

        try (BufferedReader reader =
                     new BufferedReader(new FileReader(ROOM_FILE))) {

            rooms.clear();

            String line;

            while ((line = reader.readLine()) != null) {

                String[] data = line.split(",");

                if (data.length == 4) {

                    int roomNumber =
                            Integer.parseInt(data[0]);

                    String category = data[1];

                    double price =
                            Double.parseDouble(data[2]);

                    boolean available =
                            Boolean.parseBoolean(data[3]);

                    Room room =
                            new Room(roomNumber, category, price);

                    room.setAvailable(available);

                    rooms.add(room);
                }
            }

        } catch (IOException | NumberFormatException e) {

            System.out.println(
                    "Error loading room data.");

            initializeRooms();
            saveRooms();
        }
    }


    // -------------------- SAVE RESERVATIONS --------------------
    private static void saveReservations() {

        try (BufferedWriter writer =
                     new BufferedWriter(
                             new FileWriter(RESERVATION_FILE))) {

            for (Reservation reservation : reservations) {

                writer.write(
                        reservation.getReservationId() + "," +
                        reservation.getCustomerName() + "," +
                        reservation.getPhoneNumber() + "," +
                        reservation.getRoomNumber() + "," +
                        reservation.getRoomCategory() + "," +
                        reservation.getAmount() + "," +
                        reservation.getPaymentStatus()
                );

                writer.newLine();
            }

        } catch (IOException e) {

            System.out.println(
                    "Error saving reservation data: " +
                    e.getMessage());
        }
    }


    // -------------------- LOAD RESERVATIONS --------------------
    private static void loadReservations() {

        File file = new File(RESERVATION_FILE);

        if (!file.exists()) {
            return;
        }

        try (BufferedReader reader =
                     new BufferedReader(
                             new FileReader(RESERVATION_FILE))) {

            reservations.clear();

            String line;

            while ((line = reader.readLine()) != null) {

                String[] data = line.split(",");

                if (data.length == 7) {

                    int reservationId =
                            Integer.parseInt(data[0]);

                    String customerName = data[1];

                    String phoneNumber = data[2];

                    int roomNumber =
                            Integer.parseInt(data[3]);

                    String roomCategory = data[4];

                    double amount =
                            Double.parseDouble(data[5]);

                    String paymentStatus = data[6];

                    Reservation reservation =
                            new Reservation(
                                    reservationId,
                                    customerName,
                                    phoneNumber,
                                    roomNumber,
                                    roomCategory,
                                    amount,
                                    paymentStatus
                            );

                    reservations.add(reservation);

                    // Update next reservation ID
                    if (reservationId >= nextReservationId) {
                        nextReservationId =
                                reservationId + 1;
                    }
                }
            }

        } catch (IOException | NumberFormatException e) {

            System.out.println(
                    "Error loading reservation data.");
        }
    }
}