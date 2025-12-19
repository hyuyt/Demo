public class Owner {
    private int ownerId;
    private String fullName;
    private String phoneNum;
    private String email;

    public Owner(int ownerId, String fullName, String phoneNum, String email) {
        this.ownerId = ownerId;
        this.fullName = fullName;
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

    public void contactUpdate(String Phone, String Email) {
        phoneNum = Phone;
        email = Email;
    }

    @Override
    public String toString() {
        return "Owner{" +
                "ownerId=" + ownerId +
                ", fullName='" + fullName + '\'' +
                ", phoneNum='" + phoneNum + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}