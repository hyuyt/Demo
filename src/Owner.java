import java.util.ArrayList;
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

    public String getFullName() {
        return fullName;
    }

    public List<Integer> getPets() {
        return pets;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public String getEmail() {
        return email;
    }

    public void setOwnerId(int ownerId) {
        if (ownerId > 0) {
            this.ownerId = ownerId;
        } else {
            System.out.println("Warning: Owner ID must be positive. Setting to 0.");
            this.ownerId = 0;
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

    public void setPets(List<Integer> pets) {
        if (pets != null) {
            this.pets = pets;
        } else {
            this.pets = new ArrayList<>();
        }
    }

    public void setPhoneNum(String phoneNum) {
        if (phoneNum != null && !phoneNum.trim().isEmpty()) {
            this.phoneNum = phoneNum;
        } else {
            this.phoneNum = "N/A";
        }
    }

    public void setEmail(String email) {
        if (email != null && email.contains("@")) {
            this.email = email;
        } else {
            System.out.println("Warning: Invalid email.");
            this.email = "unknown@mail.com";
        }
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