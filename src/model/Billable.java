package model;

import java.util.List;

public interface Billable {
    double calculateBill();

    class Owner {
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
            if (ownerId <= 0)
                throw new IllegalArgumentException("model.Billable.Owner ID must be positive");
            this.ownerId = ownerId;
        }

        public void setFullName(String fullName) {
            if (fullName == null || fullName.isBlank())
                throw new IllegalArgumentException("Full name cannot be empty");
            this.fullName = fullName;
        }

        public void setPets(List<Integer> pets) {
            if (pets == null)
                throw new IllegalArgumentException("Pets list cannot be null");
            this.pets = pets;
        }

        public void setPhoneNum(String phoneNum) {
            if (phoneNum == null || phoneNum.isBlank())
                throw new IllegalArgumentException("Phone number cannot be empty");
            this.phoneNum = phoneNum;
        }

        public void setEmail(String email) {
            if (email == null || !email.contains("@"))
                throw new IllegalArgumentException("Invalid email format");
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
            return "model.Billable.Owner{" +
                    "ownerId=" + ownerId +
                    ", fullName='" + fullName + '\'' +
                    ", pets=" + pets +
                    ", phoneNum='" + phoneNum + '\'' +
                    ", email='" + email + '\'' +
                    '}';
        }
    }
}
