import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class Appointment {
    private int appId;
    private LocalDate appDate;
    private LocalTime appTime;
    private String status;
    private int petId;
    private int vetId;

    public Appointment(int appId, LocalDate appDate, LocalTime appTime, String status, int petId, int vetId) {
        this.appId = appId;
        this.appDate = appDate;
        this.appTime = appTime;
        this.status = status;
        this.petId = petId;
        this.vetId = vetId;
    }

    public int getAppId() {
        return appId;
    }

    public LocalDate getAppDate() {
        return appDate;
    }

    public LocalTime getAppTime() {
        return appTime;
    }

    public String getStatus() {
        return status;
    }

    public int getPetId() {
        return petId;
    }

    public int getVetId() {
        return vetId;
    }

    public boolean isUpcoming() {
        if("Cancelled".equalsIgnoreCase(status)) {
            return false;
        }
        LocalDateTime appointmentDateTime =
                LocalDateTime.of(appDate, appTime);
        return appointmentDateTime.isAfter(LocalDateTime.now());
    }

    public void setAppId(int appId) {
        if (appId > 0) {
            this.appId = appId;
        } else {
            System.out.println("Warning: Invalid appointment ID.");
            this.appId = 0;
        }
    }

    public void setAppDate(LocalDate appDate) {
        if (appDate != null) {
            this.appDate = appDate;
        } else {
            this.appDate = LocalDate.now();
        }
    }

    public void setAppTime(LocalTime appTime) {
        if (appTime != null) {
            this.appTime = appTime;
        } else {
            this.appTime = LocalTime.now();
        }
    }

    public void setStatus(String status) {
        if (status != null && !status.trim().isEmpty()) {
            this.status = status;
        } else {
            this.status = "Scheduled";
        }
    }

    public void setPetId(int petId) {
        if (petId > 0) {
            this.petId = petId;
        } else {
            this.petId = 0;
        }
    }

    public void setVetId(int vetId) {
        if (vetId > 0) {
            this.vetId = vetId;
        } else {
            this.vetId = 0;
        }
    }

    @Override
    public String toString() {
        return "Appointment{" +
                "appId=" + appId +
                ", appDate=" + appDate +
                ", appTime=" + appTime +
                ", status='" + status + '\'' +
                ", petId=" + petId +
                ", vetId=" + vetId +
                '}';
    }
}
