import java.time.LocalDate;
import java.time.Period;

public class Pet {
    private int petId;
    private String name;
    int ownerId;
    private String species;
    private String breed;
    private LocalDate birthDate;
    private String medHistory;

    public Pet(int petId, String name, int ownerId, String species, String breed, LocalDate birthDate, String medHistory) {
        this.petId = petId;
        this.name = name;
        this.ownerId = ownerId;
        this.species = species;
        this.breed = breed;
        this.birthDate = birthDate;
        this.medHistory = medHistory;
    }

    public int getPetId() {
        return petId;
    }

    public void setPetId(int petId) {
        this.petId = petId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(int ownerId) {
        this.ownerId = ownerId;
    }

    public String getSpecies() {
        return species;
    }

    public void setSpecies(String species) {
        this.species = species;
    }

    public String getBreed() {
        return breed;
    }

    public void setBreed(String breed) {
        this.breed = breed;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public String getMedHistory() {
        return medHistory;
    }

    public void setMedHistory(String medHistory) {
        this.medHistory = medHistory;
    }

    public int getAge(){
        return Period.between(birthDate, LocalDate.now()).getYears();
    }

    public void addMedRecord(String record) {
        medHistory += "\n" + record;
    }

    @Override
    public String toString() {
        return "Pet{" +
                "petId=" + petId +
                ", name='" + name + '\'' +
                ", ownerId=" + ownerId +
                ", species='" + species + '\'' +
                ", breed='" + breed + '\'' +
                ", birthDate=" + birthDate +
                ", medHistory='" + medHistory + '\'' +
                '}';
    }
}
