package entity;

import entity.baseEntity.User;
import lombok.Getter;
import lombok.Setter;
import javax.persistence.*;
import java.util.Set;

@Getter
@Setter
@Entity
@DiscriminatorValue("patient")
public class Patient extends User {
    private String diseaseRecords;
    @OneToOne(mappedBy = "patient")
    private Prescription previousPrescription;
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

    public Patient(String fullName, String nationalCode, String password, UserType userType, String diseaseRecords, Prescription previousPrescription, Set<Doctor> doctors, Appointment appointment, Set<Secretary> secretaries, Prescription prescription) {
        super(fullName, nationalCode, password, userType);
        this.diseaseRecords = diseaseRecords;
        this.previousPrescription = previousPrescription;
        this.doctors = doctors;
        this.appointment = appointment;
        this.secretaries = secretaries;
        this.prescription = prescription;
    }

    public Patient(String fullName, String nationalCode, String password, UserType userType, String diseaseRecords) {
        super(fullName, nationalCode, password, userType);
        this.diseaseRecords = diseaseRecords;
    }

    public Patient() {
    }

    @Override
    public String toString() {
        return super.toString() + "diseaseRecords = " + diseaseRecords;
    }
}
