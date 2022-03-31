package entity;

import entity.baseEntity.User;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.Set;

@Getter
@Setter
@ToString
@Entity
@DiscriminatorValue("patient")
public class Patient extends User {
    private String diseaseRecords;
    private String previousPrescription;
    @ManyToMany(mappedBy = "patients")
    private Set<Doctor> doctors;
    @OneToOne
    private Appointment appointment;
    @ManyToMany
    @JoinTable
            (
                    name = "Patient_Secretary",
                    joinColumns = { @JoinColumn(name = "patient_id") },
                    inverseJoinColumns = { @JoinColumn(name = "secretary_id") }
            )
    private Set<Secretary> secretaries;
    @OneToOne(mappedBy = "patient")
    private Prescription prescription;

    public Patient(String fullName, String nationalCode, String password, UserType userType, String diseaseRecords, String previousPrescription, Set<Doctor> doctors, Appointment appointment, Set<Secretary> secretaries, Prescription prescription) {
        super(fullName, nationalCode, password, userType);
        this.diseaseRecords = diseaseRecords;
        this.previousPrescription = previousPrescription;
        this.doctors = doctors;
        this.appointment = appointment;
        this.secretaries = secretaries;
        this.prescription = prescription;
    }

    public Patient() {
    }
}
