import java.time.LocalDate;

public class Pet {
    private int petId;
    private String name;
    private String species;
    private String breed;
    private LocalDate birthDate;
    private String medHistory;

    public Pet(int petId, String name, String species, String breed, LocalDate birthDate, String medHistory) {
        this.petId = petId;
        this.name = name;
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

}
