package entity;

import entity.baseEntity.User;
import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Getter
@Setter
@ToString
@Entity
@DiscriminatorValue("doctor")
public class Doctor extends User {
    private String expertise;
    @ManyToOne
    private Clinic clinic;
    @ManyToMany
    @JoinTable
            (
                    name = "Doctor_Patient",
                    joinColumns = { @JoinColumn(name = "doctor_id") },
                    inverseJoinColumns = { @JoinColumn(name = "patient_id") }
            )
    private Set<Patient> patients;
    @OneToMany(mappedBy = "doctor")
    private Set<Prescription> prescription;
    @OneToMany(mappedBy = "doctor")
    private Set<Appointment> appointments;
    @OneToOne
    private Secretary secretary;

    public Doctor(String fullName, String nationalCode, String password, UserType userType, String expertise, Clinic clinic, Set<Patient> patients, Set<Prescription> prescription, Set<Appointment> appointments, Secretary admin) {
        super(fullName, nationalCode, password, userType);
        this.expertise = expertise;
        this.clinic = clinic;
        this.patients = patients;
        this.prescription = prescription;
        this.appointments = appointments;
        this.secretary = admin;
    }

    public Doctor() {
    }
}
