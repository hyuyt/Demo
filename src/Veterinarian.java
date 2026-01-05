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
        if (vetId > 0) {
            this.vetId = vetId;
        } else {
            System.out.println("Warning: Vet ID must be positive. Setting to 0.");
            this.vetId = 0;
        }
    }

    public void setFullName(String fullName) {
        if (fullName != null && !fullName.trim().isEmpty()) {
            this.fullName = fullName;
        } else {
            System.out.println("Warning: Full name cannot be empty.");
            this.fullName = "Unknown";
        }
    }

    public void setSpecialization(String specialization) {
        if (specialization != null && !specialization.trim().isEmpty()) {
            this.specialization = specialization;
        } else {
            this.specialization = "General";
        }
    }

    public void setPhoneNum(String phoneNum) {
        if (phoneNum != null && !phoneNum.trim().isEmpty()) {
            this.phoneNum = phoneNum;
        } else {
            this.phoneNum = "N/A";
        }
    }

    public boolean hasSpecialization(String spec) {
        return specialization.equalsIgnoreCase(spec);
    }

    @Override
    public String toString() {
        return "Veterinarian{" +
                "vetId=" + vetId +
                ", fullName='" + fullName + '\'' +
                ", specialization='" + specialization + '\'' +
                ", phoneNum='" + phoneNum + '\'' +
                '}';
    }
}