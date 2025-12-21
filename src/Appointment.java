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

    public void setAppId(int appId) {
        this.appId = appId;
    }

    public LocalDate getAppDate() {
        return appDate;
    }

    public void setAppDate(LocalDate appDate) {
        this.appDate = appDate;
    }

    public LocalTime getAppTime() {
        return appTime;
    }

    public void setAppTime(LocalTime appTime) {
        this.appTime = appTime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getPetId() {
        return petId;
    }

    public void setPetId(int petId) {
        this.petId = petId;
    }

    public int getVetId() {
        return vetId;
    }

    public void setVetId(int vetId) {
        this.vetId = vetId;
    }

    public void cancelApp() {
        status = "Cancelled";
    }

    public boolean isUpcoming() {
        if("Cancelled".equalsIgnoreCase(status)) {
            return false;
        }
        LocalDateTime appointmentDateTime =
                LocalDateTime.of(appDate, appTime);
        return appointmentDateTime.isAfter(LocalDateTime.now());
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
