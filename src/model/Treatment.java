package model;
import java.time.LocalDate;

public abstract class Treatment {

    protected int treatmentId;
    protected String treatmentName;
    protected String description;
    protected double cost;
    protected LocalDate treatmentDate;
    protected int vetId;

    public Treatment(int treatmentId, String treatmentName, String description,
                     double cost, LocalDate treatmentDate, int vetId) {

        setTreatmentId(treatmentId);
        setTreatmentName(treatmentName);
        setDescription(description);
        setCost(cost);
        setTreatmentDate(treatmentDate);
        setVetId(vetId);
    }


    public abstract void performTreatment();
    public abstract String getType();

    public void setTreatmentId(int treatmentId) {
        if (treatmentId <= 0)
            throw new IllegalArgumentException("Treatment ID must be positive");
        this.treatmentId = treatmentId;
    }

    public void setTreatmentName(String treatmentName) {
        if (treatmentName == null || treatmentName.isBlank())
            throw new IllegalArgumentException("Treatment name cannot be empty");
        this.treatmentName = treatmentName;
    }

    public void setDescription(String description) {
        if (description == null)
            throw new IllegalArgumentException("Description cannot be null");
        this.description = description;
    }

    public void setCost(double cost) {
        if (cost < 0)
            throw new IllegalArgumentException("Cost cannot be negative");
        this.cost = cost;
    }

    public void setTreatmentDate(LocalDate treatmentDate) {
        if (treatmentDate == null)
            throw new IllegalArgumentException("Date cannot be null");
        this.treatmentDate = treatmentDate;
    }

    public void setVetId(int vetId) {
        if (vetId < 0)
            throw new IllegalArgumentException("Vet ID cannot be negative");
        this.vetId = vetId;
    }

    @Override
    public String toString() {
        return getType() + " | " + treatmentName + " | $" + cost;
    }
}
