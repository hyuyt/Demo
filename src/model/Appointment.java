package model;

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
        if (appId <= 0)
            throw new IllegalArgumentException("Appointment ID must be positive");
        this.appId = appId;
    }

    public void setAppDate(LocalDate appDate) {
        if (appDate == null)
            throw new IllegalArgumentException("Appointment date cannot be null");
        this.appDate = appDate;
    }

    public void setAppTime(LocalTime appTime) {
        if (appTime == null)
            throw new IllegalArgumentException("Appointment time cannot be null");
        this.appTime = appTime;
    }

    public void setStatus(String status) {
        if (status == null || status.isBlank())
            throw new IllegalArgumentException("Status cannot be empty");
        this.status = status;
    }

    public void setPetId(int petId) {
        if (petId <= 0)
            throw new IllegalArgumentException("model.Pet ID must be positive");
        this.petId = petId;
    }

    public void setVetId(int vetId) {
        if (vetId <= 0)
            throw new IllegalArgumentException("Vet ID must be positive");
        this.vetId = vetId;
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
