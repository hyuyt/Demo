import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    private static ArrayList<Treatment> treatments = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {

        // Test data
        treatments.add(new Treatment(
                1, "General Checkup", "Routine examination",
                50, LocalDate.now(), 0));

        treatments.add(new Vaccination(
                2, "Rabies Shot", "Rabies vaccination",
                40, LocalDate.now(), 0,
                "Rabies", LocalDate.now().plusYears(1)));

        treatments.add(new Surgery(
                3, "Neutering", "Neutering surgery",
                300, LocalDate.now(), 0,
                "Neutering", 90));

        boolean running = true;

        while (running) {
            displayMenu();
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    addTreatment();
                    break;
                case 2:
                    addVaccination();
                    break;
                case 3:
                    addSurgery();
                    break;
                case 4:
                    viewAllTreatments();
                    break;
                case 5:
                    demonstratePolymorphism();
                    break;
                case 6:
                    viewVaccinationsOnly();
                    break;
                case 7:
                    viewSurgeriesOnly();
                    break;
                case 0:
                    System.out.println("\nGoodbye! 👋");
                    running = false;
                    break;
                default:
                    System.out.println("Invalid choice!");
            }

            if (running) {
                System.out.println("\nPress Enter to continue...");
                scanner.nextLine();
            }
        }
        scanner.close();
    }

    private static void displayMenu() {
        System.out.println("\n========================================");
        System.out.println("   VET CLINIC TREATMENT SYSTEM");
        System.out.println("========================================");
        System.out.println("1. Add Treatment (General)");
        System.out.println("2. Add Vaccination");
        System.out.println("3. Add Surgery");
        System.out.println("4. View All Treatments (Polymorphic)");
        System.out.println("5. Demonstrate Polymorphism");
        System.out.println("6. View Vaccinations Only");
        System.out.println("7. View Surgeries Only");
        System.out.println("0. Exit");
        System.out.println("========================================");
        System.out.print("Enter your choice: ");
    }

    private static void addTreatment() {
        System.out.println("\n--- ADD GENERAL TREATMENT ---");

        System.out.print("Enter ID: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Enter name: ");
        String name = scanner.nextLine();

        System.out.print("Enter description: ");
        String desc = scanner.nextLine();

        System.out.print("Enter cost: ");
        double cost = scanner.nextDouble();
        scanner.nextLine();

        Treatment t = new Treatment(
                id, name, desc, cost,
                LocalDate.now(), 0
        );

        treatments.add(t);
        System.out.println("✅ Treatment added!");
    }

    private static void addVaccination() {
        System.out.println("\n--- ADD VACCINATION ---");

        System.out.print("Enter ID: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Enter name: ");
        String name = scanner.nextLine();

        System.out.print("Enter description: ");
        String desc = scanner.nextLine();

        System.out.print("Enter cost: ");
        double cost = scanner.nextDouble();
        scanner.nextLine();

        System.out.print("Enter vaccine name: ");
        String vaccine = scanner.nextLine();

        System.out.print("Enter next due date (YYYY-MM-DD): ");
        LocalDate nextDate = LocalDate.parse(scanner.nextLine());

        Treatment t = new Vaccination(
                id, name, desc, cost,
                LocalDate.now(), 0,
                vaccine, nextDate
        );

        treatments.add(t);
        System.out.println("✅ Vaccination added!");
    }

    private static void addSurgery() {
        System.out.println("\n--- ADD SURGERY ---");

        System.out.print("Enter ID: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Enter name: ");
        String name = scanner.nextLine();

        System.out.print("Enter description: ");
        String desc = scanner.nextLine();

        System.out.print("Enter cost: ");
        double cost = scanner.nextDouble();
        scanner.nextLine();

        System.out.print("Enter surgery type: ");
        String type = scanner.nextLine();

        System.out.print("Enter duration (minutes): ");
        int duration = scanner.nextInt();
        scanner.nextLine();

        Treatment t = new Surgery(
                id, name, desc, cost,
                LocalDate.now(), 0,
                type, duration
        );

        treatments.add(t);
        System.out.println("✅ Surgery added!");
    }

    private static void viewAllTreatments() {
        System.out.println("\n=== ALL TREATMENTS (POLYMORPHIC) ===");

        for (Treatment t : treatments) {
            System.out.println(t);
            t.performTreatment();
            System.out.println();
        }
    }

    private static void demonstratePolymorphism() {
        System.out.println("\n=== POLYMORPHISM DEMONSTRATION ===");

        for (Treatment t : treatments) {
            t.performTreatment();
        }

        System.out.println("\n✨ Same method, different behavior!");
    }

    private static void viewVaccinationsOnly() {
        System.out.println("\n=== VACCINATIONS ONLY ===");

        for (Treatment t : treatments) {
            if (t instanceof Vaccination) {
                System.out.println(t);
            }
        }
    }

    private static void viewSurgeriesOnly() {
        System.out.println("\n=== SURGERIES ONLY ===");

        for (Treatment t : treatments) {
            if (t instanceof Surgery) {
                System.out.println(t);
            }
        }
    }
}