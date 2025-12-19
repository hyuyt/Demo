import java.time.LocalDate;

public class Treatment {
    private int treatmentId;
    private String treatmentName;
    private String description;
    private double cost;
    private LocalDate treatmentDate;
    private int vetId;

    public Treatment(int treatmentId, String treatmentName, String description, double cost, LocalDate treatmentDate, int vetId) {
        this.treatmentId = treatmentId;
        this.treatmentName = treatmentName;
        this.description = description;
        this.cost = cost;
        this.treatmentDate = treatmentDate;
        this.vetId = vetId;
    }

    public int getTreatmentId() {
        return treatmentId;
    }

    public void setTreatmentId(int treatmentId) {
        this.treatmentId = treatmentId;
    }

    public String getTreatmentName() {
        return treatmentName;
    }

    public void setTreatmentName(String treatmentName) {
        this.treatmentName = treatmentName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public LocalDate getTreatmentDate() {
        return treatmentDate;
    }

    public void setTreatmentDate(LocalDate treatmentDate) {
        this.treatmentDate = treatmentDate;
    }

    public int getVetId() {
        return vetId;
    }

    public void setVetId(int vetId) {
        this.vetId = vetId;
    }
}