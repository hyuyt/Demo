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

    public void setVetId(int vetId) {
        this.vetId = vetId;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getSpecialization() {
        return specialization;
    }

    public void setSpecialization(String specialization) {
        this.specialization = specialization;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
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