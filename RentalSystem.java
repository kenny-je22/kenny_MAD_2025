import java.util.ArrayList;
import java.util.Scanner;

// =================== Class Vehicle (Parent) ===================
class Vehicle {
    protected String brand;
    protected String model;
    protected double pricePerDay;
    protected boolean available;

    public Vehicle(String brand, String model, double pricePerDay) {
        this.brand = brand;
        this.model = model;
        this.pricePerDay = pricePerDay;
        this.available = true;
    }

    public boolean rent() {
        if (available) {
            available = false;
            return true;
        }
        return false;
    }

    public void returnVehicle() {
        available = true;
    }

    public double getPrice() {
        return pricePerDay;
    }

    public String getInfo() {
        String status = available ? "Tersedia" : "Disewa";
        return brand + " " + model + " - Rp" + pricePerDay + "/hari - " + status;
    }
}

// =================== Class Car (Inheritance) ===================
class Car extends Vehicle {
    private int doors;

    public Car(String brand, String model, double pricePerDay, int doors) {
        super(brand, model, pricePerDay);
        this.doors = doors;
    }

    @Override
    public String getInfo() {
        return super.getInfo() + " - " + doors + " pintu";
    }
}

// =================== Class Customer ===================
class Customer {
    private String customerId;
    private String name;

    public Customer(String customerId, String name) {
        this.customerId = customerId;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public String getInfo() {
        return customerId + " - " + name;
    }
}

// =================== Class Rental (Composition) ===================
class Rental {
    private Customer customer;
    private Vehicle vehicle;
    private int days;
    private double totalPrice;

    public Rental(Customer customer, Vehicle vehicle, int days) {
        this.customer = customer;
        this.vehicle = vehicle;
        this.days = days;
        this.totalPrice = vehicle.getPrice() * days;
    }

    public String getInfo() {
        return "Customer: " + customer.getName() +
                " | Mobil: " + vehicle.brand + " " + vehicle.model +
                " | Hari: " + days +
                " | Total: Rp" + totalPrice;
    }
}

// =================== Main Rental System ===================
public class RentalSystem {
    private static ArrayList<Vehicle> vehicles = new ArrayList<>();
    private static ArrayList<Customer> customers = new ArrayList<>();
    private static ArrayList<Rental> rentals = new ArrayList<>();
    private static Scanner input = new Scanner(System.in);

    public static void main(String[] args) {
        while (true) {
            System.out.println("===== SISTEM RENTAL MOBIL =====");
            System.out.println("1. Tambah Mobil");
            System.out.println("2. Tambah Customer");
            System.out.println("3. Lihat Daftar Mobil");
            System.out.println("4. Sewa Mobil");
            System.out.println("5. Kembalikan Mobil");
            System.out.println("6. Lihat Transaksi");
            System.out.println("0. Keluar");
            System.out.print("Pilih menu: ");

            int pilihan;
            if (input.hasNextInt()) {
                pilihan = input.nextInt();
                input.nextLine(); // buang newline
            } else {
                System.out.println("Input tidak valid!\n");
                input.nextLine(); // buang input invalid
                continue;
            }

            switch (pilihan) {
                case 1: tambahMobil(); break;
                case 2: tambahCustomer(); break;
                case 3: lihatMobil(); break;
                case 4: sewaMobil(); break;
                case 5: kembalikanMobil(); break;
                case 6: lihatRental(); break;
                case 0:
                    System.out.println("Terima kasih telah menggunakan sistem rental!");
                    return;
                default:
                    System.out.println("Pilihan tidak valid!\n");
            }
        }
    }

    // =================== Tambah Mobil ===================
    private static void tambahMobil() {
        System.out.print("Merk: ");
        String brand = input.nextLine();
        System.out.print("Model: ");
        String model = input.nextLine();
        System.out.print("Harga per hari: ");
        double price;
        try {
            price = Double.parseDouble(input.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Input harga tidak valid!\n");
            return;
        }
        System.out.print("Jumlah pintu: ");
        int doors;
        try {
            doors = Integer.parseInt(input.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Input jumlah pintu tidak valid!\n");
            return;
        }

        vehicles.add(new Car(brand, model, price, doors));
        System.out.println("Mobil berhasil ditambahkan!\n");
    }

    // =================== Tambah Customer ===================
    private static void tambahCustomer() {
        System.out.print("ID Customer: ");
        String id = input.nextLine();
        System.out.print("Nama Customer: ");
        String name = input.nextLine();

        customers.add(new Customer(id, name));
        System.out.println("Customer berhasil ditambahkan!\n");
    }

    // =================== Lihat Mobil ===================
    private static void lihatMobil() {
        if (vehicles.isEmpty()) {
            System.out.println("Belum ada mobil.\n");
            return;
        }

        for (int i = 0; i < vehicles.size(); i++) {
            System.out.println((i + 1) + ". " + vehicles.get(i).getInfo());
        }
        System.out.println();
    }

    // =================== Sewa Mobil ===================
    private static void sewaMobil() {
        if (vehicles.isEmpty()) {
            System.out.println("Belum ada mobil.\n");
            return;
        }

        lihatMobil();
        System.out.print("Pilih nomor mobil: ");
        int choice;
        try {
            choice = Integer.parseInt(input.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Input tidak valid!\n");
            return;
        }

        int index = choice - 1; // sesuaikan index
        if (index < 0 || index >= vehicles.size()) {
            System.out.println("Nomor mobil tidak valid!\n");
            return;
        }

        Vehicle vehicle = vehicles.get(index);

        if (!vehicle.rent()) {
            System.out.println("Mobil sedang tidak tersedia!\n");
            return;
        }

        System.out.print("Nama customer: ");
        String name = input.nextLine();
        Customer customer = new Customer("C" + (customers.size() + 1), name);
        customers.add(customer);

        System.out.print("Berapa hari sewa: ");
        int days;
        try {
            days = Integer.parseInt(input.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Input hari sewa tidak valid!\n");
            vehicle.returnVehicle(); // batalkan sewa
            return;
        }

        rentals.add(new Rental(customer, vehicle, days));
        System.out.println("Mobil berhasil disewa!\n");
    }

    // =================== Kembalikan Mobil ===================
    private static void kembalikanMobil() {
        if (vehicles.isEmpty()) {
            System.out.println("Belum ada mobil.\n");
            return;
        }

        lihatMobil();
        System.out.print("Pilih nomor mobil yang dikembalikan: ");
        int choice;
        try {
            choice = Integer.parseInt(input.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Input tidak valid!\n");
            return;
        }

        int index = choice - 1; // sesuaikan index
        if (index < 0 || index >= vehicles.size()) {
            System.out.println("Nomor mobil tidak valid!\n");
            return;
        }

        vehicles.get(index).returnVehicle();
        System.out.println("Mobil berhasil dikembalikan!\n");
    }

    // =================== Lihat Transaksi ===================
    private static void lihatRental() {
        if (rentals.isEmpty()) {
            System.out.println("Belum ada transaksi.\n");
            return;
        }

        for (Rental r : rentals) {
            System.out.println(r.getInfo());
        }
        System.out.println();
    }
}

	