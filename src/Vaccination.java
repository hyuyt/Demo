import java.time.LocalDate;

public class Vaccination extends Treatment {

    private String vaccineName;
    private LocalDate nextDueDate;

    public Vaccination(int treatmentId, String treatmentName, String description,
                       double cost, LocalDate treatmentDate, int vetId,
                       String vaccineName, LocalDate nextDueDate) {

        super(treatmentId, treatmentName, description, cost, treatmentDate, vetId);
        this.vaccineName = vaccineName;
        this.nextDueDate = nextDueDate;
    }

    public String getVaccineName() {
        return vaccineName;
    }

    public LocalDate getNextDueDate() {
        return nextDueDate;
    }

    @Override
    public void performTreatment() {
        System.out.println("Vaccination with " + vaccineName +
                ". Next dose due: " + nextDueDate);
    }

    @Override
    public String getType() {
        return "Vaccination";
    }

    public boolean boosterNeededSoon() {
        return nextDueDate.isBefore(LocalDate.now().plusMonths(3));
    }
}
