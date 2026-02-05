package menu;

import database.TreatmentDAO;
import model.Surgery;
import model.Treatment;
import model.Vaccination;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

public class MenuManager implements Menu {

    private final Scanner scanner;
    private final TreatmentDAO treatmentDAO;

    public MenuManager() {
        this.scanner = new Scanner(System.in);
        this.treatmentDAO = new TreatmentDAO();
    }

    @Override
    public void displayMenu() {
        System.out.println("""
                ========================================
                   VET CLINIC TREATMENT SYSTEM
                ========================================
                1. Add Vaccination
                2. Add Surgery
                3. View All Treatments
                4. View Vaccinations Only
                5. View Surgeries Only
                6. Update Treatment
                7. Delete Treatment
                8. Search by Name
                9. Search by Cost Range
                10. Search by Min Cost (Cost >= X)
                11. Polymorphism Demo
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
                    case 1 -> addVaccination();
                    case 2 -> addSurgery();
                    case 3 -> viewAll();
                    case 4 -> viewVaccinations();
                    case 5 -> viewSurgeries();
                    case 6 -> updateTreatment();
                    case 7 -> deleteTreatment();
                    case 8 -> searchTreatmentByName();
                    case 9 -> searchTreatmentByCostRange();
                    case 10 -> searchTreatmentByMinCost();
                    case 11 -> demonstratePolymorphism();
                    case 0 -> running = false;
                    default -> System.out.println("❌ Invalid option!");
                }

            } catch (NumberFormatException e) {
                System.out.println("❌ Please enter a number.");
            } catch (Exception e) {
                System.out.println("❌ Error: " + e.getMessage());
            }
        }

        scanner.close();
    }

    // -------------------- ADD --------------------

    private void addVaccination() {
        System.out.println("\n--- ADD VACCINATION ---");

        try {
            System.out.print("Treatment ID: ");
            int id = Integer.parseInt(scanner.nextLine());

            System.out.print("Name: ");
            String name = scanner.nextLine();

            System.out.print("Description: ");
            String desc = scanner.nextLine();

            System.out.print("Cost: ");
            double cost = Double.parseDouble(scanner.nextLine());

            System.out.print("Vet ID: ");
            int vetId = Integer.parseInt(scanner.nextLine());

            System.out.print("Vaccine name: ");
            String vaccine = scanner.nextLine();

            System.out.print("Next due date (YYYY-MM-DD): ");
            LocalDate nextDate = LocalDate.parse(scanner.nextLine());

            Vaccination v = new Vaccination(
                    id, name, desc, cost,
                    LocalDate.now(), vetId,
                    vaccine, nextDate
            );

            treatmentDAO.insertTreatment(v);
            System.out.println("✅ Vaccination added!");

        } catch (Exception e) {
            System.out.println("❌ Error adding vaccination: " + e.getMessage());
        }
    }

    private void addSurgery() {
        System.out.println("\n--- ADD SURGERY ---");

        try {
            System.out.print("Treatment ID: ");
            int id = Integer.parseInt(scanner.nextLine());

            System.out.print("Name: ");
            String name = scanner.nextLine();

            System.out.print("Description: ");
            String desc = scanner.nextLine();

            System.out.print("Cost: ");
            double cost = Double.parseDouble(scanner.nextLine());

            System.out.print("Vet ID: ");
            int vetId = Integer.parseInt(scanner.nextLine());

            System.out.print("Surgery type: ");
            String surgeryType = scanner.nextLine();

            System.out.print("Duration (minutes): ");
            int duration = Integer.parseInt(scanner.nextLine());

            Surgery s = new Surgery(
                    id, name, desc, cost,
                    LocalDate.now(), vetId,
                    surgeryType, duration
            );

            treatmentDAO.insertTreatment(s);
            System.out.println("✅ Surgery added!");

        } catch (Exception e) {
            System.out.println("❌ Error adding surgery: " + e.getMessage());
        }
    }

    // -------------------- VIEW --------------------

    private void viewAll() {
        System.out.println("\n--- ALL TREATMENTS ---");
        // your DAO method prints directly:
        treatmentDAO.getAllTreatments();
    }

    private void viewVaccinations() {
        System.out.println("\n--- VACCINATIONS ONLY ---");
        List<Treatment> vaccinations = treatmentDAO.searchByType("VACCINATION");
        if (vaccinations.isEmpty()) {
            System.out.println("⚠️ No vaccinations found.");
            return;
        }
        for (Treatment t : vaccinations) System.out.println(t);
    }

    private void viewSurgeries() {
        System.out.println("\n--- SURGERIES ONLY ---");
        List<Treatment> surgeries = treatmentDAO.searchByType("SURGERY");
        if (surgeries.isEmpty()) {
            System.out.println("⚠️ No surgeries found.");
            return;
        }
        for (Treatment t : surgeries) System.out.println(t);
    }

    private void demonstratePolymorphism() {
        System.out.println("\n--- POLYMORPHISM DEMO ---");

        // Week 8: load from DB, then call polymorphic method
        List<Treatment> list = treatmentDAO.searchByName(""); // load all by searching empty string
        if (list.isEmpty()) {
            System.out.println("⚠️ No treatments found in DB.");
            return;
        }

        for (Treatment t : list) {
            t.performTreatment();
        }
    }

    // -------------------- UPDATE --------------------

    private void updateTreatment() {
        try {
            System.out.print("Enter Treatment ID to update: ");
            int treatmentId = Integer.parseInt(scanner.nextLine());

            Treatment existing = TreatmentDAO.getTreatmentById(treatmentId);
            if (existing == null) {
                System.out.println("❌ No treatment found with ID: " + treatmentId);
                return;
            }

            System.out.println("\nCurrent Treatment Info:");
            System.out.println(existing);

            System.out.print("New Name [" + existing.getTreatmentName() + "]: ");
            String newName = scanner.nextLine();
            if (newName.trim().isEmpty()) newName = existing.getTreatmentName();

            System.out.print("New Cost [" + existing.getCost() + "]: ");
            String costInput = scanner.nextLine();
            double newCost = costInput.trim().isEmpty()
                    ? existing.getCost()
                    : Double.parseDouble(costInput);

            if (existing instanceof Surgery surgery) {

                System.out.print("New Surgery Type [" + surgery.getSurgeryType() + "]: ");
                String newType = scanner.nextLine();
                if (newType.trim().isEmpty()) newType = surgery.getSurgeryType();

                System.out.print("New Duration [" + surgery.getDurationMinutes() + "]: ");
                String durationInput = scanner.nextLine();
                int newDuration = durationInput.trim().isEmpty()
                        ? surgery.getDurationMinutes()
                        : Integer.parseInt(durationInput);

                Surgery updated = new Surgery(
                        treatmentId,
                        newName,
                        surgery.getDescription(),
                        newCost,
                        surgery.getTreatmentDate(),
                        surgery.getVetId(),
                        newType,
                        newDuration
                );

                boolean ok = TreatmentDAO.updateSurgery(updated);
                System.out.println(ok ? "✅ Surgery updated!" : "❌ Update failed");

            } else if (existing instanceof Vaccination vaccination) {

                System.out.print("New Vaccine Name [" + vaccination.getVaccineName() + "]: ");
                String newVaccine = scanner.nextLine();
                if (newVaccine.trim().isEmpty()) newVaccine = vaccination.getVaccineName();

                Vaccination updated = new Vaccination(
                        treatmentId,
                        newName,
                        vaccination.getDescription(),
                        newCost,
                        vaccination.getTreatmentDate(),
                        vaccination.getVetId(),
                        newVaccine,
                        vaccination.getNextDueDate()
                );

                boolean ok = TreatmentDAO.updateVaccination(updated);
                System.out.println(ok ? "✅ Vaccination updated!" : "❌ Update failed");

            } else {
                System.out.println("❌ Unknown treatment type.");
            }

        } catch (Exception e) {
            System.out.println("❌ Error updating treatment: " + e.getMessage());
        }
    }

    // -------------------- DELETE (SAFE) --------------------

    private void deleteTreatment() {
        try {
            System.out.print("Enter Treatment ID to delete: ");
            int treatmentId = Integer.parseInt(scanner.nextLine());

            Treatment t = TreatmentDAO.getTreatmentById(treatmentId);
            if (t == null) {
                System.out.println("❌ No treatment found with ID: " + treatmentId);
                return;
            }

            System.out.println("\nTreatment to be deleted:");
            System.out.println(t);

            System.out.print("⚠️ Are you sure? (yes/no): ");
            String confirmation = scanner.nextLine();

            if (confirmation.equalsIgnoreCase("yes")) {
                boolean ok = TreatmentDAO.deleteTreatment(treatmentId);
                System.out.println(ok ? "✅ Deletion completed." : "❌ Delete failed.");
            } else {
                System.out.println("❌ Deletion cancelled.");
            }

        } catch (Exception e) {
            System.out.println("❌ Error deleting treatment: " + e.getMessage());
        }
    }

    // -------------------- SEARCH --------------------

    private void searchTreatmentByName() {
        System.out.print("Enter treatment name to search: ");
        String name = scanner.nextLine();

        List<Treatment> results = TreatmentDAO.searchByName(name);

        if (results.isEmpty()) {
            System.out.println("⚠️ No treatments found.");
            return;
        }

        System.out.println("\nSearch Results:");
        for (Treatment t : results) System.out.println(t);
    }

    private void searchTreatmentByCostRange() {
        try {
            System.out.print("Enter minimum cost: ");
            double min = Double.parseDouble(scanner.nextLine());

            System.out.print("Enter maximum cost: ");
            double max = Double.parseDouble(scanner.nextLine());

            List<Treatment> results = TreatmentDAO.searchByCostRange(min, max);

            if (results.isEmpty()) {
                System.out.println("⚠️ No treatments found in this range.");
                return;
            }

            System.out.println("\nSearch Results:");
            for (Treatment t : results) System.out.println(t);

        } catch (Exception e) {
            System.out.println("❌ Error: " + e.getMessage());
        }
    }

    private void searchTreatmentByMinCost() {
        try {
            System.out.print("Enter minimum cost: ");
            double min = Double.parseDouble(scanner.nextLine());

            List<Treatment> results = TreatmentDAO.searchByMinCost(min);

            if (results.isEmpty()) {
                System.out.println("⚠️ No treatments found.");
                return;
            }

            System.out.println("\nTreatments costing at least " + min + ":");
            for (Treatment t : results) System.out.println(t);

        } catch (Exception e) {
            System.out.println("❌ Error: " + e.getMessage());
        }
    }
}
