import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class Vaccination {
    private int vaccinationId;
    private String vaccineName;
    private LocalDate dateGiven;
    private LocalDate nextDueDate;
    private int petId;
    private int vetId;

    public Vaccination(int vaccinationId, String vaccineName, LocalDate dateGiven, LocalDate nextDueDate, int petId, int vetId) {
        this.vaccinationId = vaccinationId;
        this.vaccineName = vaccineName;
        this.dateGiven = dateGiven;
        this.nextDueDate = nextDueDate;
        this.petId = petId;
        this.vetId = vetId;
    }

    public int getVaccinationId() {
        return vaccinationId;
    }

    public void setVaccinationId(int vaccinationId) {
        this.vaccinationId = vaccinationId;
    }

    public String getVaccineName() {
        return vaccineName;
    }

    public void setVaccineName(String vaccineName) {
        this.vaccineName = vaccineName;
    }

    public LocalDate getDateGiven() {
        return dateGiven;
    }

    public void setDateGiven(LocalDate dateGiven) {
        this.dateGiven = dateGiven;
    }

    public LocalDate getNextDueDate() {
        return nextDueDate;
    }

    public void setNextDueDate(LocalDate nextDueDate) {
        this.nextDueDate = nextDueDate;
    }

    public int getPetId() {
        return petId;
    }

    public void setPetId(int petId) {
        this.petId = petId;
    }

    public int getVetId() {
        return vetId;
    }

    public void setVetId(int vetId) {
        this.vetId = vetId;
    }

    public boolean isOverdue() {
        return LocalDate.now().isAfter(nextDueDate);
    }

    // Method 2
    public long daysUntilNextDose() {
        return ChronoUnit.DAYS.between(LocalDate.now(), nextDueDate);
    }

    @Override
    public String toString() {
        return "Vaccination{" +
                "vaccinationId=" + vaccinationId +
                ", vaccineName='" + vaccineName + '\'' +
                ", dateGiven=" + dateGiven +
                ", nextDueDate=" + nextDueDate +
                ", petId=" + petId +
                ", vetId=" + vetId +
                '}';
    }
}