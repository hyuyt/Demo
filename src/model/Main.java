package model;

import menu.Menu;
import menu.MenuManager;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Period;

public class Main {
    public static void main(String[] args) {
        Menu menu = new MenuManager();
        menu.run();
    }

    public static class Appointment {
        private int appId;
        private LocalDate appDate;
        private LocalTime appTime;
        private String status;
        private int petId;
        private int vetId;

        public Appointment(int appId, LocalDate appDate, LocalTime appTime, String status, int petId, int vetId) {
            this.appId = appId;
            this.appDate = appDate;
            this.appTime = appTime;
            this.status = status;
            this.petId = petId;
            this.vetId = vetId;
        }

        public int getAppId() {
            return appId;
        }

        public LocalDate getAppDate() {
            return appDate;
        }

        public LocalTime getAppTime() {
            return appTime;
        }

        public String getStatus() {
            return status;
        }

        public int getPetId() {
            return petId;
        }

        public int getVetId() {
            return vetId;
        }

        public boolean isUpcoming() {
            if("Cancelled".equalsIgnoreCase(status)) {
                return false;
            }
            LocalDateTime appointmentDateTime =
                    LocalDateTime.of(appDate, appTime);
            return appointmentDateTime.isAfter(LocalDateTime.now());
        }

        public void setAppId(int appId) {
            if (appId <= 0)
                throw new IllegalArgumentException("model.Main.Appointment ID must be positive");
            this.appId = appId;
        }

        public void setAppDate(LocalDate appDate) {
            if (appDate == null)
                throw new IllegalArgumentException("model.Main.Appointment date cannot be null");
            this.appDate = appDate;
        }

        public void setAppTime(LocalTime appTime) {
            if (appTime == null)
                throw new IllegalArgumentException("model.Main.Appointment time cannot be null");
            this.appTime = appTime;
        }

        public void setStatus(String status) {
            if (status == null || status.isBlank())
                throw new IllegalArgumentException("Status cannot be empty");
            this.status = status;
        }

        public void setPetId(int petId) {
            if (petId <= 0)
                throw new IllegalArgumentException("model.Main.Pet ID must be positive");
            this.petId = petId;
        }

        public void setVetId(int vetId) {
            if (vetId <= 0)
                throw new IllegalArgumentException("Vet ID must be positive");
            this.vetId = vetId;
        }


        @Override
        public String toString() {
            return "model.Main.Appointment{" +
                    "appId=" + appId +
                    ", appDate=" + appDate +
                    ", appTime=" + appTime +
                    ", status='" + status + '\'' +
                    ", petId=" + petId +
                    ", vetId=" + vetId +
                    '}';
        }
    }

    public static class Pet {
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
                throw new IllegalArgumentException("model.Main.Pet ID must be positive");
            this.petId = petId;
        }

        public void setName(String name) {
            if (name == null || name.isBlank())
                throw new IllegalArgumentException("model.Main.Pet name cannot be empty");
            this.name = name;
        }

        public void setOwnerId(int ownerId) {
            if (ownerId <= 0)
                throw new IllegalArgumentException("model.Billable.Owner ID must be positive");
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
            return "model.Main.Pet{" +
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

    public static class Surgery extends Treatment {

        private String surgeryType;
        private int durationMinutes;

        public Surgery(int id, String name, String desc,
                       double cost, LocalDate date, int vetId,
                       String surgeryType, int durationMinutes) {

            super(id, name, desc, cost, date, vetId);
            this.surgeryType = surgeryType;
            this.durationMinutes = durationMinutes;
        }

        @Override
        public void performTreatment() {
            System.out.println("Performing surgery: " + surgeryType +
                    " (" + durationMinutes + " minutes)");
        }

        @Override
        public String getType() {
            return "Surgery";
        }
    }

    public static class Vaccination extends Treatment {

        private String vaccineName;
        private LocalDate nextDueDate;

        public Vaccination(int id, String name, String desc,
                           double cost, LocalDate date, int vetId,
                           String vaccineName, LocalDate nextDueDate) {

            super(id, name, desc, cost, date, vetId);
            this.vaccineName = vaccineName;
            this.nextDueDate = nextDueDate;
        }

        @Override
        public void performTreatment() {
            System.out.println("Vaccinating with " + vaccineName +
                    ", next dose: " + nextDueDate);
        }

        @Override
        public String getType() {
            return "Vaccination";
        }
    }
}
