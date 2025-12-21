import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Integer> Pets = new ArrayList<>();

        Owner owner = new Owner(1,"Akerke", Pets,
                "+77777777777", "example@example.com");

        Pet pet = new Pet(1, "Buddy", owner.getOwnerId(), "Dog", "Golden Retriever",
                LocalDate.of(2020, 5,20), "Healthy");

        owner.newPet(pet.getPetId());

        Veterinarian vet = new Veterinarian(1, "Dr. Elya",
                "Surgery", "+77777777171");

        Appointment app = new Appointment(1, LocalDate.of(2025, 12, 25),
                LocalTime.of(10, 30), "Scheduled", pet.getPetId(), vet.getVetId());

        Treatment treatment = new Treatment(1, "Teeth Cleaning",
                "Cleaning and polishing", 120, LocalDate.of(2025, 12, 10), vet.getVetId());

        Vaccination vaccination = new Vaccination(1, "Rabies", LocalDate.of(2025, 1,1),
                LocalDate.of(2026, 1, 1), pet.getPetId(), vet.getVetId());


        pet.addMedRecord("Vaccinated for rabies on 2025-01-01");

        System.out.println("Does the veterinarian have surgery specialization? " + vet.hasSpecialization("Surgery"));

        System.out.println(owner.getFullName() + " owns " + owner.petsSum() + ((owner.petsSum() == 1) ? " pet" : " pets"));



        app.cancelApp();
        System.out.println("Is appointment upcoming? " + app.isUpcoming());

        System.out.println("Is vaccination overdue?" + vaccination.isOverdue());
        System.out.println("Days until next dose: " + vaccination.daysUntilNextDose());

        System.out.println(treatment.getTreatmentSummary());
        System.out.println("Is treatment expensive? " + treatment.isExpensive());

        System.out.println("Pet's age: " + pet.getAge());

        System.out.println("\nOwner: " + owner);
        System.out.println("Pet: " + pet);
        System.out.println("Veterinarian: " + vet);
        System.out.println("Appointment: " + app);
        System.out.println("Treatment: " + treatment);
        System.out.println("Vaccination: " + vaccination);
    }
}