import java.time.LocalDate;

public class Surgery extends Treatment {

    private String surgeryType;
    private int durationMinutes;

    public Surgery(int treatmentId, String treatmentName, String description,
                   double cost, LocalDate treatmentDate, int vetId,
                   String surgeryType, int durationMinutes) {

        super(treatmentId, treatmentName, description, cost, treatmentDate, vetId);
        this.surgeryType = surgeryType;
        this.durationMinutes = durationMinutes;
    }

    public String getSurgeryType() {
        return surgeryType;
    }

    public int getDurationMinutes() {
        return durationMinutes;
    }

    @Override
    public void performTreatment() {
        System.out.println("Performing " + surgeryType +
                " surgery. Duration: " + durationMinutes + " minutes.");
    }

    @Override
    public String getType() {
        return "Surgery";
    }

    public boolean isLongSurgery() {
        return durationMinutes > 120;
    }
}
