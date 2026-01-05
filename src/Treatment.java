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

    public String getTreatmentName() {
        return treatmentName;
    }

    public String getDescription() {
        return description;
    }

    public double getCost() {
        return cost;
    }

    public LocalDate getTreatmentDate() {
        return treatmentDate;
    }

    public int getVetId() {
        return vetId;
    }

    public void setTreatmentId(int treatmentId) {
        if (treatmentId > 0) {
            this.treatmentId = treatmentId;
        } else {
            System.out.println("Warning: Treatment ID must be positive. Setting to 0.");
            this.treatmentId = 0;
        }
    }

    public void setTreatmentName(String treatmentName) {
        if (treatmentName != null && !treatmentName.trim().isEmpty()) {
            this.treatmentName = treatmentName;
        } else {
            System.out.println("Warning: Treatment name cannot be empty.");
            this.treatmentName = "Unknown";
        }
    }

    public void setDescription(String description) {
        if (description != null && !description.trim().isEmpty()) {
            this.description = description;
        } else {
            this.description = "No description";
        }
    }

    public void setCost(double cost) {
        if (cost >= 0) {
            this.cost = cost;
        } else {
            System.out.println("Warning: Cost cannot be negative! Setting to 0.");
            this.cost = 0;
        }
    }

    public void setTreatmentDate(LocalDate treatmentDate) {
        if (treatmentDate != null) {
            this.treatmentDate = treatmentDate;
        } else {
            System.out.println("Warning: Invalid treatment date. Using today.");
            this.treatmentDate = LocalDate.now();
        }
    }

    public void setVetId(int vetId) {
        if (vetId > 0) {
            this.vetId = vetId;
        } else {
            System.out.println("Warning: Vet ID must be positive. Setting to 0.");
            this.vetId = 0;
        }
    }

    public String getTreatmentSummary() {
        return treatmentName + " on " + treatmentDate + " | Cost: $" + cost;
    }

    public boolean isExpensive() {
        return cost > 100.0;   // threshold can be changed
    }

    @Override
    public String toString() {
        return "Treatment{" +
                "treatmentId=" + treatmentId +
                ", treatmentName='" + treatmentName + '\'' +
                ", description='" + description + '\'' +
                ", cost=" + cost +
                ", treatmentDate=" + treatmentDate +
                ", vetId=" + vetId +
                '}';
    }
}