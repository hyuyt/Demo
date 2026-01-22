package model;

import java.time.LocalDate;

public abstract class Treatment implements Billable{

    protected int treatmentId;
    protected String treatmentName;
    protected String description;
    protected double cost;
    protected LocalDate treatmentDate;
    protected int vetId;

    public Treatment(int treatmentId, String treatmentName, String description,
                     double cost, LocalDate treatmentDate, int vetId) {

        setTreatmentId(treatmentId);
        setTreatmentName(treatmentName);
        setDescription(description);
        setCost(cost);
        setTreatmentDate(treatmentDate);
        setVetId(vetId);
    }

    // 🔴 ABSTRACT METHODS (обязательно)
    public abstract void performTreatment();
    public abstract String getType();

    // 🔴 SETTERS WITH EXCEPTIONS
    public void setTreatmentId(int treatmentId) {
        if (treatmentId <= 0)
            throw new IllegalArgumentException("Treatment ID must be positive");
        this.treatmentId = treatmentId;
    }

    public void setTreatmentName(String treatmentName) {
        if (treatmentName == null || treatmentName.isBlank())
            throw new IllegalArgumentException("Treatment name cannot be empty");
        this.treatmentName = treatmentName;
    }

    public void setDescription(String description) {
        if (description == null)
            throw new IllegalArgumentException("Description cannot be null");
        this.description = description;
    }

    public void setCost(double cost) {
        if (cost < 0)
            throw new IllegalArgumentException("Cost cannot be negative");
        this.cost = cost;
    }

    public void setTreatmentDate(LocalDate treatmentDate) {
        if (treatmentDate == null)
            throw new IllegalArgumentException("Date cannot be null");
        this.treatmentDate = treatmentDate;
    }

    public void setVetId(int vetId) {
        if (vetId < 0)
            throw new IllegalArgumentException("Vet ID cannot be negative");
        this.vetId = vetId;
    }

    @Override
    public String toString() {
        return getType() + " | " + treatmentName + " | $" + cost;
    }

    @Override
    public double calculateBill() {
        return cost;
    }

    public static class Veterinarian {
        private int vetId;
        private String fullName;
        private String specialization;
        private String phoneNum;

        public Veterinarian(int vetId, String fullName, String specialization, String phoneNum) {
            this.vetId = vetId;
            this.fullName = fullName;
            this.specialization = specialization;
            this.phoneNum = phoneNum;
        }

        public int getVetId() {
            return vetId;
        }

        public String getFullName() {
            return fullName;
        }

        public String getSpecialization() {
            return specialization;
        }

        public String getPhoneNum() {
            return phoneNum;
        }

        public void setVetId(int vetId) {
            if (vetId <= 0) {
                throw new IllegalArgumentException("Id should be positive!");
            }
            this.vetId = vetId;
        }

        public void setFullName(String fullName) {
            if (fullName == null || fullName.trim().isEmpty()) {
                throw new IllegalArgumentException("Full name cannot be empty");
            }
            this.fullName = fullName;
        }

        public void setSpecialization(String specialization) {
            if (specialization == null || specialization.trim().isEmpty()) {
                throw new IllegalArgumentException("Specialization cannot be empty");
            }
            this.specialization = specialization;
        }

        public void setPhoneNum(String phoneNum) {
            if (phoneNum == null || phoneNum.trim().isEmpty()) {
                throw new IllegalArgumentException("Phone number cannot be empty");
            }
            this.phoneNum = phoneNum;
        }

        public boolean hasSpecialization(String spec) {
            return specialization.equalsIgnoreCase(spec);
        }

        @Override
        public String toString() {
            return "model.Treatment.Veterinarian{" +
                    "vetId=" + vetId +
                    ", fullName='" + fullName + '\'' +
                    ", specialization='" + specialization + '\'' +
                    ", phoneNum='" + phoneNum + '\'' +
                    '}';
        }
    }
}
