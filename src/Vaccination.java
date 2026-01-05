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

    public String getVaccineName() {
        return vaccineName;
    }

    public LocalDate getDateGiven() {
        return dateGiven;
    }

    public LocalDate getNextDueDate() {
        return nextDueDate;
    }

    public int getPetId() {
        return petId;
    }

    public int getVetId() {
        return vetId;
    }

    public void setVaccinationId(int vaccinationId) {
        if (vaccinationId > 0) {
            this.vaccinationId = vaccinationId;
        } else {
            System.out.println("Warning: Vaccination ID must be positive. Setting to 0.");
            this.vaccinationId = 0;
        }
    }

    public void setVaccineName(String vaccineName) {
        if (vaccineName != null && !vaccineName.trim().isEmpty()) {
            this.vaccineName = vaccineName;
        } else {
            System.out.println("Warning: Vaccine name cannot be empty.");
            this.vaccineName = "Unknown";
        }
    }

    public void setDateGiven(LocalDate dateGiven) {
        if (dateGiven != null) {
            this.dateGiven = dateGiven;
        } else {
            System.out.println("Warning: Invalid vaccination date. Using today.");
            this.dateGiven = LocalDate.now();
        }
    }

    public void setNextDueDate(LocalDate nextDueDate) {
        if (nextDueDate != null) {
            this.nextDueDate = nextDueDate;
        } else {
            System.out.println("Warning: Invalid next due date. Using today.");
            this.nextDueDate = LocalDate.now();
        }
    }

    public void setPetId(int petId) {
        if (petId > 0) {
            this.petId = petId;
        } else {
            System.out.println("Warning: Pet ID must be positive. Setting to 0.");
            this.petId = 0;
        }
    }

    public void setVetId(int vetId) {
        if (vetId > 0) {
            this.vetId = vetId;
        } else {
            System.out.println("Warning: Vet ID must be positive. Setting to 0.");
            this.vetId = 0;
        }
    }


    public boolean isOverdue() {
        return LocalDate.now().isAfter(nextDueDate);
    }

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