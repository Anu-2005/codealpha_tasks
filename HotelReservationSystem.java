import java.io.*;
import java.util.*;

class Room {
    int roomNo;
    String type;
    double price;
    boolean isBooked;

    Room(int roomNo, String type, double price) {
        this.roomNo = roomNo;
        this.type = type;
        this.price = price;
        this.isBooked = false;
    }
}

class Hotel {
    ArrayList<Room> rooms = new ArrayList<>();

    Hotel() {
        rooms.add(new Room(101, "Standard", 1500));
        rooms.add(new Room(102, "Standard", 1500));
        rooms.add(new Room(201, "Deluxe", 2500));
        rooms.add(new Room(202, "Deluxe", 2500));
        rooms.add(new Room(301, "Suite", 4000));
    }

    void showAvailableRooms() {
        System.out.println("\n=== Available Rooms ===");
        for (Room r : rooms)
            if (!r.isBooked)
                System.out.println("Room " + r.roomNo + " | " + r.type + " | ₹" + r.price);
    }

    Room getRoom(int roomNo) {
        for (Room r : rooms)
            if (r.roomNo == roomNo)
                return r;
        return null;
    }
}

class Booking {
    String name;
    int roomNo;
    double amount;

    Booking(String name, int roomNo, double amount) {
        this.name = name;
        this.roomNo = roomNo;
        this.amount = amount;
    }

    static void saveBooking(Booking b) {
        try {
            FileWriter fw = new FileWriter("bookings.txt", true);
            fw.write(b.name + " booked Room " + b.roomNo + " | Amount: ₹" + b.amount + "\n");
            fw.close();
        } catch (Exception e) {
            System.out.println("Error saving booking!");
        }
    }

    static void showAllBookings() {
        try {
            BufferedReader br = new BufferedReader(new FileReader("bookings.txt"));
            String line;
            System.out.println("\n=== Booking Records ===");
            while ((line = br.readLine()) != null)
                System.out.println(line);
            br.close();
        } catch (Exception e) {
            System.out.println("No records found!");
        }
    }
}

public class HotelReservationSystem {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Hotel h = new Hotel();

        while (true) {
            System.out.println("\n========== HOTEL MENU ==========");
            System.out.println("1. View Available Rooms");
            System.out.println("2. Book Room");
            System.out.println("3. Cancel Reservation");
            System.out.println("4. View Booking History");
            System.out.println("5. Exit");
            System.out.print("Enter choice: ");
            int ch = sc.nextInt();

            switch (ch) {
                case 1 -> h.showAvailableRooms();

                case 2 -> {
                    System.out.print("Enter Your Name: ");
                    sc.nextLine();
                    String name = sc.nextLine();
                    System.out.print("Enter Room Number to Book: ");
                    int rn = sc.nextInt();

                    Room room = h.getRoom(rn);
                    if (room != null && !room.isBooked) {
                        System.out.println("Payment Processing...");
                        System.out.println("Paid Successfully ₹" + room.price);

                        room.isBooked = true;
                        Booking booking = new Booking(name, rn, room.price);
                        Booking.saveBooking(booking);

                        System.out.println("Booking Confirmed! Room " + rn + " reserved.");
                    } else
                        System.out.println("Room not available!");
                }

                case 3 -> {
                    System.out.print("Enter Room Number to Cancel: ");
                    int rn = sc.nextInt();
                    Room room = h.getRoom(rn);

                    if (room != null && room.isBooked) {
                        room.isBooked = false;
                        System.out.println("Reservation Cancelled for Room " + rn);
                    } else
                        System.out.println("Room was not booked!");
                }

                case 4 -> Booking.showAllBookings();

                case 5 -> {
                    System.out.println("Thank you for using Hotel System!");
                    return;
                }

                default -> System.out.println("Invalid option!");
            }
        }
    }
}
