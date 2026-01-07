import java.awt.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class Main {
    private static ArrayList<Pet> pets = new ArrayList<>();
    private static ArrayList<Owner> owners = new ArrayList<>();
    private static ArrayList<Veterinarian> veterinarians = new ArrayList<>();
    private static ArrayList<Appointment> appointments = new ArrayList<>();
    private static ArrayList<Treatment> treatments = new ArrayList<>();
    private static ArrayList<Vaccination> vaccinations = new ArrayList<>();

    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        pets.add(new Pet(
                0,
                "It",
                0,
                "Dog",
                "Golden Retriever",
                LocalDate.of(2024, 12, 12),
                "Healthy"));
        owners.add(new Owner(
                0,
                "Adam",
                new ArrayList<>(List.of(0)),
                "87771206100",
                "adam@mail.kz"));
        veterinarians.add(new Veterinarian(
                0,
                "Doctor Doctor",
                "Surgery",
                "87771206013"));
        appointments.add(new Appointment(
                0,
                LocalDate.of(2026, 1, 1),
                LocalTime.of(12, 0, 0),
                "Over",
                0,
                0));
        treatments.add(new Treatment(
                0,
                "Tooth cleaning",
                "Wound cleaned and disinfected",
                150,
                LocalDate.of(2026, 1, 2),
                0));
        vaccinations.add(new Vaccination(
                0,
                "Dari",
                LocalDate.of(2025, 12, 1),
                LocalDate.of(2026, 12, 1),
                0,
                0));
        boolean running = true;
        while (running) {
            displayMenu(); // Show menu options
            int choice = scanner.nextInt(); // Read user's choice
            scanner.nextLine(); // IMPORTANT: consume leftover newline

            switch (choice) {
                case 1:
                    addPet();
                    break;
                case 2:
                    viewAllPets();
                    break;
                case 3:
                    addOwner();
                    break;
                case 4:
                    viewAllOwners();
                    break;
                case 5:
                    addTreatment();
                    break;
                case 6:
                    viewAllTreatments();
                    break;
                case 0:
                    System.out.println("\nGoodbye! 👋");
                    running = false; // Exit loop
                    break;
                default:
                    System.out.println("\nInvalid choice! ❌");
            }

            if (running) {
                System.out.println("\nPress Enter to continue...");
                scanner.nextLine(); // Wait for user
            }
        }
        scanner.close();
    }
    private static void displayMenu() {
        System.out.println("\n========================================");
        System.out.println("      VETERINARY CLINIC SYSTEM");
        System.out.println("========================================");
        System.out.println("1. Add Pet");
        System.out.println("2. View All Pets");
        System.out.println("3. Add Owner");
        System.out.println("4. View All Owners");
        System.out.println("5. Add Treatment");
        System.out.println("6. View All Treatments");
        System.out.println("0. Exit");
        System.out.println("========================================");
        System.out.print("Enter your choice: ");
    }

    private static void addPet() {
        System.out.println("\n--- ADD PET ---");

        System.out.print("Enter pet ID: ");
        int petId = scanner.nextInt();
        scanner.nextLine(); // consume leftover newline

        System.out.print("Enter pet name: ");
        String name = scanner.nextLine();

        System.out.print("Enter owner ID: ");
        int ownerId = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Enter species: ");
        String species = scanner.nextLine();

        System.out.print("Enter breed: ");
        String breed = scanner.nextLine();

        System.out.print("Enter birthday date (YYYY-MM-DD): ");
        LocalDate birthDate = LocalDate.parse(scanner.nextLine());

        System.out.print("Enter medical history: ");
        String medHistory = scanner.nextLine();

        Pet pet = new Pet(petId, name, ownerId, species, breed, birthDate, medHistory);
        pets.add(pet);

        System.out.println("\nPet added successfully! ✅");
    }

    private static void viewAllPets() {
        System.out.println("\n========================================");
        System.out.println(" ALL PETS");
        System.out.println("========================================");

        if (pets.isEmpty()) {
            System.out.println("No pets found.");
            return;
        }

        System.out.println("Total pets: " + pets.size());
        System.out.println();

        for (int i = 0; i < pets.size(); i++) {
            Pet pet = pets.get(i);

            System.out.println((i + 1) + ". " + pet.getName());
            System.out.println(" Owner ID: " + pet.getOwnerId());
            System.out.println(" Species: " + pet.getSpecies());
            System.out.println(" Breed: " + pet.getBreed());
            System.out.println(" Birth Date: " + pet.getBirthDate());
            System.out.println(" Medical History: " + pet.getMedHistory());
            System.out.println();
        }
    }

    private static void addOwner() {
        System.out.println("\n--- ADD OWNER ---");

        System.out.print("Enter owner ID: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Enter full name: ");
        String name = scanner.nextLine();

        System.out.print("Enter pet index: ");
        int petIndex = scanner.nextInt();
        scanner.nextLine();

        List<Integer> petIndexes = new ArrayList<>();
        petIndexes.add(petIndex);

        System.out.print("Enter phone number: ");
        String phone = scanner.nextLine();

        System.out.print("Enter email: ");
        String email = scanner.nextLine();

        Owner owner = new Owner(id, name, petIndexes, phone, email);
        owners.add(owner);

        System.out.println("\nOwner added successfully! ✅");
    }

    private static void viewAllOwners() {
        System.out.println("\n========================================");
        System.out.println(" ALL OWNERS");
        System.out.println("========================================");

        if (owners.isEmpty()) {
            System.out.println("No owners found.");
            return;
        }

        for (int i = 0; i < owners.size(); i++) {
            Owner o = owners.get(i);

            System.out.println((i + 1) + ". " + o.getFullName());
            System.out.println(" Phone: " + o.getPhoneNum());
            System.out.println(" Email: " + o.getEmail());
            System.out.println(" Pet Indexes: " + o.getPets());
            System.out.println();
        }
    }

    private static void addTreatment() {
        System.out.println("\n--- ADD TREATMENT ---");

        System.out.print("Enter treatment ID: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // consume leftover newline

        System.out.print("Enter treatment name: ");
        String name = scanner.nextLine();

        System.out.print("Enter treatment description: ");
        String desc = scanner.nextLine();

        System.out.print("Enter treatment cost: ");
        double cost = scanner.nextDouble();
        scanner.nextLine(); // consume leftover newline

        System.out.print("Enter vet ID: ");
        int vetId = scanner.nextInt();
        scanner.nextLine(); // consume leftover newline

        LocalDate date = LocalDate.now();

        // Create Treatment object with new constructor
        Treatment treatment = new Treatment(id, name, desc, cost, date, vetId);
        treatments.add(treatment);

        System.out.println("\nTreatment added successfully! ✅");
    }

    private static void viewAllTreatments() {
        System.out.println("\n========================================");
        System.out.println(" ALL TREATMENTS");
        System.out.println("========================================");

        if (treatments.isEmpty()) {
            System.out.println("No treatments found.");
            return;
        }

        for (int i = 0; i < treatments.size(); i++) {
            Treatment t = treatments.get(i);

            System.out.println((i + 1) + ". Treatment Name: " + t.getTreatmentName());
            System.out.println(" Description: " + t.getDescription());
            System.out.println(" Cost: " + t.getCost());
            System.out.println(" Date: " + t.getTreatmentDate());
            System.out.println(" Vet ID: " + t.getVetId());
            System.out.println();
        }
    }
}
