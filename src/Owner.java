import java.util.List;

public class Owner {
    private int ownerId;
    private String fullName;
    private List<Integer> pets;
    private String phoneNum;
    private String email;

    public Owner(int ownerId, String fullName, List<Integer> pets, String phoneNum, String email) {
        this.ownerId = ownerId;
        this.fullName = fullName;
        this.pets = pets;
        this.phoneNum = phoneNum;
        this.email = email;
    }

    public int getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(int ownerId) {
        this.ownerId = ownerId;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public List<Integer> getPets() {
        return pets;
    }

    public void setPets(List<Integer> pets) {
        this.pets = pets;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void newPet(int petId) {
        pets.add(petId);
    }

    public Integer petsSum() {
        return pets.size();
    }

    @Override
    public String toString() {
        return "Owner{" +
                "ownerId=" + ownerId +
                ", fullName='" + fullName + '\'' +
                ", pets=" + pets +
                ", phoneNum='" + phoneNum + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}