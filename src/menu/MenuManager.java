package menu;

import database.TreatmentDAO;
import model.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MenuManager implements Menu {

    private ArrayList<Treatment> treatments;
    private Scanner scanner;

    public MenuManager() {
        treatments = new ArrayList<>();
        scanner = new Scanner(System.in);

        treatments.add(new Vaccination(
                1, "Rabies Shot", "Rabies vaccine",
                40, LocalDate.now(), 0,
                "Rabies", LocalDate.now().plusYears(1)));

        treatments.add(new Surgery(
                2, "Neutering", "Neutering surgery",
                300, LocalDate.now(), 0,
                "Neutering", 90));
    }

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
            } catch (IllegalArgumentException e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
        scanner.close();
    }

    private void addTreatment() {
        System.out.println("\n--- ADD GENERAL TREATMENT ---");

        try {
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

        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private void addVaccination() {
        System.out.println("\n--- ADD VACCINATION ---");

        try {
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

        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private void addSurgery() {
        try {
            System.out.print("Enter ID: ");
            int id = Integer.parseInt(scanner.nextLine());

            System.out.print("Enter name: ");
            String name = scanner.nextLine();

            System.out.print("Description: ");
            String description = scanner.nextLine();

            System.out.print("Enter cost: ");
            double cost = Double.parseDouble(scanner.nextLine());

            System.out.print("Enter surgery name: ");
            String surgeryName = scanner.nextLine();

            System.out.print("Enter duration (minutes): ");
            int duration = Integer.parseInt(scanner.nextLine());

            treatments.add(new Surgery(
                    id, name, description, cost,
                    LocalDate.now(), 0,
                    surgeryName, duration));

            System.out.println("Surgery added!");

        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }
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
        for (Treatment t : treatments) {
            if (t instanceof Surgery) {
                System.out.println(t);
            }
        }
    }

    private void updateTreatment() {

        System.out.print("Enter Treatment ID to update: ");
        int treatmentId = scanner.nextInt();
        scanner.nextLine(); // clear buffer

        Treatment existingTreatment = TreatmentDAO.getTreatmentById(treatmentId);

        if (existingTreatment == null) {
            System.out.println("❌ No treatment found with ID: " + treatmentId);
            return;
        }

        System.out.println("\nCurrent Treatment Info:");
        System.out.println(existingTreatment);

        System.out.print("New Name [" + existingTreatment.getTreatmentName() + "]: ");
        String newName = scanner.nextLine();
        if (newName.trim().isEmpty()) {
            newName = existingTreatment.getTreatmentName();
        }

        System.out.print("New Cost [" + existingTreatment.getCost() + "]: ");
        String costInput = scanner.nextLine();
        double newCost = costInput.trim().isEmpty()
                ? existingTreatment.getCost()
                : Double.parseDouble(costInput);

        if (existingTreatment instanceof Surgery surgery) {

            System.out.print("New Surgery Type [" + surgery.getSurgeryType() + "]: ");
            String newType = scanner.nextLine();
            if (newType.trim().isEmpty()) {
                newType = surgery.getSurgeryType();
            }

            System.out.print("New Duration [" + surgery.getDurationMinutes() + "]: ");
            String durationInput = scanner.nextLine();
            int newDuration = durationInput.trim().isEmpty()
                    ? surgery.getDurationMinutes()
                    : Integer.parseInt(durationInput);

            Surgery updatedSurgery = new Surgery(
                    treatmentId,
                    newName,
                    surgery.getDescription(),
                    newCost,
                    surgery.getTreatmentDate(),
                    surgery.getVetId(),
                    newType,
                    newDuration
            );

            boolean success = TreatmentDAO.updateSurgery(updatedSurgery);
            System.out.println(success ? "✅ Surgery updated!" : "❌ Update failed");
        }

        else if (existingTreatment instanceof Vaccination vaccination) {

            System.out.print("New Vaccine Name [" + vaccination.getVaccineName() + "]: ");
            String newVaccine = scanner.nextLine();
            if (newVaccine.trim().isEmpty()) {
                newVaccine = vaccination.getVaccineName();
            }

            Vaccination updatedVaccination = new Vaccination(
                    treatmentId,
                    newName,
                    vaccination.getDescription(),
                    newCost,
                    vaccination.getTreatmentDate(),
                    vaccination.getVetId(),
                    newVaccine,
                    vaccination.getNextDueDate()
            );

            boolean success = TreatmentDAO.updateVaccination(updatedVaccination);
            System.out.println(success ? "✅ Vaccination updated!" : "❌ Update failed");
        }
    }

    private void deleteTreatment() {

        System.out.print("Enter Treatment ID to delete: ");
        int treatmentId = scanner.nextInt();
        scanner.nextLine(); // clear buffer

        // 1. Load treatment from database
        Treatment treatment = TreatmentDAO.getTreatmentById(treatmentId);

        if (treatment == null) {
            System.out.println("❌ No treatment found with ID: " + treatmentId);
            return;
        }

        // 2. Show what will be deleted
        System.out.println("\nTreatment to be deleted:");
        System.out.println(treatment);

        // 3. Ask for confirmation
        System.out.print("⚠️ Are you sure? (yes/no): ");
        String confirmation = scanner.nextLine();

        // 4. Delete only if confirmed
        if (confirmation.equalsIgnoreCase("yes")) {
            boolean success = TreatmentDAO.deleteTreatment(treatmentId);
            if (success) {
                System.out.println("✅ Deletion completed.");
            }
        } else {
            System.out.println("❌ Deletion cancelled.");
        }
    }

    private void searchTreatmentByName() {

        System.out.print("Enter treatment name to search: ");
        String name = scanner.nextLine();

        List<Treatment> results = TreatmentDAO.searchByName(name);

        if (results.isEmpty()) {
            System.out.println("⚠️ No treatments found.");
        } else {
            System.out.println("\nSearch Results:");
            for (Treatment t : results) {
                System.out.println(t);
            }
        }
    }

    private void searchTreatmentByCost() {

        System.out.print("Enter minimum cost: ");
        double min = scanner.nextDouble();

        System.out.print("Enter maximum cost: ");
        double max = scanner.nextDouble();
        scanner.nextLine(); // clear buffer

        List<Treatment> results = TreatmentDAO.searchByCostRange(min, max);

        if (results.isEmpty()) {
            System.out.println("⚠️ No treatments found in this range.");
        } else {
            System.out.println("\nSearch Results:");
            for (Treatment t : results) {
                System.out.println(t);
            }
        }
    }

    private void searchTreatmentByMinCost() {

        System.out.print("Enter minimum cost: ");
        double min = scanner.nextDouble();
        scanner.nextLine();

        List<Treatment> results = TreatmentDAO.searchByMinCost(min);

        if (results.isEmpty()) {
            System.out.println("⚠️ No treatments found.");
        } else {
            System.out.println("\nTreatments costing at least " + min + ":");
            for (Treatment t : results) {
                System.out.println(t);
            }
        }
    }

}
