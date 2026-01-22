package model;

import java.time.LocalDate;

public class Surgery extends Treatment {

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
