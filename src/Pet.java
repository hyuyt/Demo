import java.time.LocalDate;
import java.time.Period;

public class Pet {
    private int petId;
    private String name;
    private int ownerId;
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

    public String getName() {
        return name;
    }

    public int getOwnerId() {
        return ownerId;
    }

    public String getSpecies() {
        return species;
    }

    public String getBreed() {
        return breed;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public String getMedHistory() {
        return medHistory;
    }

    public void setPetId(int petId) {
        if (petId > 0) {
            this.petId = petId;
        } else {
            System.out.println("Warning: Pet ID must be positive. Setting to 0.");
            this.petId = 0;
        }
    }

    public void setName(String name) {
        if (name != null && !name.trim().isEmpty()) {
            this.name = name;
        } else {
            System.out.println("Warning: Name cannot be empty.");
            this.name = "Unknown";
        }
    }

    public void setOwnerId(int ownerId) {
        if (ownerId > 0) {
            this.ownerId = ownerId;
        } else {
            System.out.println("Warning: Owner ID must be positive. Setting to 0.");
            this.ownerId = 0;
        }
    }

    public void setSpecies(String species) {
        if (species != null && !species.trim().isEmpty()) {
            this.species = species;
        } else {
            this.species = "Unknown";
        }
    }

    public void setBreed(String breed) {
        if (breed != null && !breed.trim().isEmpty()) {
            this.breed = breed;
        } else {
            this.breed = "Unknown";
        }
    }

    public void setBirthDate(LocalDate birthDate) {
        if (birthDate != null && !birthDate.isAfter(LocalDate.now())) {
            this.birthDate = birthDate;
        } else {
            System.out.println("Warning: Invalid birth date. Using today.");
            this.birthDate = LocalDate.now();
        }
    }

    public void setMedHistory(String medHistory) {
        if (medHistory != null) {
            this.medHistory = medHistory;
        } else {
            this.medHistory = "";
        }
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
