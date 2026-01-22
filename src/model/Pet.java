package model;

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
        if (petId <= 0)
            throw new IllegalArgumentException("model.Pet ID must be positive");
        this.petId = petId;
    }

    public void setName(String name) {
        if (name == null || name.isBlank())
            throw new IllegalArgumentException("model.Pet name cannot be empty");
        this.name = name;
    }

    public void setOwnerId(int ownerId) {
        if (ownerId <= 0)
            throw new IllegalArgumentException("model.Owner ID must be positive");
        this.ownerId = ownerId;
    }

    public void setSpecies(String species) {
        if (species == null || species.isBlank())
            throw new IllegalArgumentException("Species cannot be empty");
        this.species = species;
    }

    public void setBreed(String breed) {
        if (breed == null || breed.isBlank())
            throw new IllegalArgumentException("Breed cannot be empty");
        this.breed = breed;
    }

    public void setBirthDate(LocalDate birthDate) {
        if (birthDate == null || birthDate.isAfter(LocalDate.now()))
            throw new IllegalArgumentException("Invalid birth date");
        this.birthDate = birthDate;
    }

    public void setMedHistory(String medHistory) {
        if (medHistory == null)
            throw new IllegalArgumentException("Medical history cannot be null");
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
        return "model.Pet{" +
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
