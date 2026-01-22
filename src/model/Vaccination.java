package model;

import java.time.LocalDate;

public class Vaccination extends Treatment {

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
