package menu;

import model.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

public class MenuManager implements Menu {

    private ArrayList<Treatment> treatments;
    private Scanner scanner;

    public MenuManager() {
        treatments = new ArrayList<>();
        scanner = new Scanner(System.in);

        // Test data
        treatments.add(new Vaccination(
                1, "Rabies Shot", "Rabies vaccine",
                40, LocalDate.now(), 0,
                "Rabies", LocalDate.now().plusYears(1)));

        treatments.add(new Surgery(
                2, "Neutering", "Neutering surgery",
                300, LocalDate.now(), 0,
                "Neutering", 90));
    }

    // ===== MENU =====
    @Override
    public void displayMenu() {
        System.out.println("""
        ========================================
           VET CLINIC TREATMENT SYSTEM
        ========================================
        1. Add General Treatment
        2. Add Vaccination
        3. Add Surgery
        4. View All Treatments
        5. Demonstrate Polymorphism
        6. View Vaccinations Only
        7. View Surgeries Only
        0. Exit
        ========================================
        """);
        System.out.print("Enter choice: ");
    }

    // ===== RUN =====
    @Override
    public void run() {
        boolean running = true;

        while (running) {
            displayMenu();
            try {
                int choice = Integer.parseInt(scanner.nextLine());

                switch (choice) {
                    case 1 -> addTreatment();
                    case 2 -> addVaccination();
                    case 3 -> addSurgery();
                    case 4 -> viewAll();
                    case 5 -> demonstratePolymorphism();
                    case 6 -> viewVaccinations();
                    case 7 -> viewSurgeries();
                    case 0 -> running = false;
                    default -> System.out.println("Invalid option!");
                }

            } catch (NumberFormatException e) {
                System.out.println("Please enter a number!");
            } catch (IllegalArgumentException e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
        scanner.close();
    }

    // ===== FUNCTIONS =====
    private void addTreatment() {
        System.out.println("\n--- ADD GENERAL TREATMENT ---");

        System.out.print("ID: ");
        int id = Integer.parseInt(scanner.nextLine());

        System.out.print("Name: ");
        String name = scanner.nextLine();

        System.out.print("Description: ");
        String desc = scanner.nextLine();

        System.out.print("Cost: ");
        double cost = Double.parseDouble(scanner.nextLine());

        Treatment t = new Treatment(
                id, name, desc, cost,
                LocalDate.now(), 0) {
            @Override
            public void performTreatment() {
                System.out.println("Performing general treatment");
            }

            @Override
            public String getType() {
                return "General Treatment";
            }
        };

        treatments.add(t);
        System.out.println("Treatment added!");
    }

    private void addVaccination() {
        System.out.println("\n--- ADD VACCINATION ---");

        System.out.print("ID: ");
        int id = Integer.parseInt(scanner.nextLine());

        System.out.print("Name: ");
        String name = scanner.nextLine();

        System.out.print("Description: ");
        String desc = scanner.nextLine();

        System.out.print("Cost: ");
        double cost = Double.parseDouble(scanner.nextLine());

        System.out.print("Vaccine name: ");
        String vaccine = scanner.nextLine();

        System.out.print("Next due date (YYYY-MM-DD): ");
        LocalDate nextDate = LocalDate.parse(scanner.nextLine());

        treatments.add(new Vaccination(
                id, name, desc, cost,
                LocalDate.now(), 0,
                vaccine, nextDate));

        System.out.println("Vaccination added!");
    }

    private void addSurgery() {
        System.out.println("\n--- ADD SURGERY ---");

        System.out.print("ID: ");
        int id = Integer.parseInt(scanner.nextLine());

        System.out.print("Name: ");
        String name = scanner.nextLine();

        System.out.print("Description: ");
        String desc = scanner.nextLine();

        System.out.print("Cost: ");
        double cost = Double.parseDouble(scanner.nextLine());

        System.out.print("Surgery type: ");
        String type = scanner.nextLine();

        System.out.print("Duration (minutes): ");
        int duration = Integer.parseInt(scanner.nextLine());

        treatments.add(new Surgery(
                id, name, desc, cost,
                LocalDate.now(), 0,
                type, duration));

        System.out.println("Surgery added!");
    }

    private void viewAll() {
        System.out.println("\n--- ALL TREATMENTS ---");
        for (Treatment t : treatments) {
            System.out.println(t);
        }
    }

    private void demonstratePolymorphism() {
        System.out.println("\n--- POLYMORPHISM DEMO ---");
        for (Treatment t : treatments) {
            t.performTreatment();
        }
    }

    private void viewVaccinations() {
        System.out.println("\n--- VACCINATIONS ONLY ---");
        for (Treatment t : treatments) {
            if (t instanceof Vaccination) {
                System.out.println(t);
            }
        }
    }

    private void viewSurgeries() {
        System.out.println("\n--- SURGERIES ONLY ---");
        for (Treatment t : treatments) {
            if (t instanceof Surgery) {
                System.out.println(t);
            }
        }
    }
}
