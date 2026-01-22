package model;

public class Veterinarian {
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
            throw new IllegalArgumentException("Veterinarian ID must be positive");
        }
        this.vetId = vetId;
    }

    public void setFullName(String fullName) {
        if (fullName == null || fullName.isBlank()) {
            throw new IllegalArgumentException("Veterinarian full name cannot be empty");
        }
        this.fullName = fullName;
    }

    public void setSpecialization(String specialization) {
        if (specialization == null || specialization.isBlank()) {
            throw new IllegalArgumentException("Specialization cannot be empty");
        }
        this.specialization = specialization;
    }

    public void setPhoneNum(String phoneNum) {
        if (phoneNum == null || phoneNum.isBlank()) {
            throw new IllegalArgumentException("Phone number cannot be empty");
        }
        this.phoneNum = phoneNum;
    }

    public boolean hasSpecialization(String spec) {
        return specialization.equalsIgnoreCase(spec);
    }

    @Override
    public String toString() {
        return "model.Veterinarian{" +
                "vetId=" + vetId +
                ", fullName='" + fullName + '\'' +
                ", specialization='" + specialization + '\'' +
                ", phoneNum='" + phoneNum + '\'' +
                '}';
    }
}