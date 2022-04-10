package entity;

import entity.baseEntity.User;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import java.util.Set;

@Getter
@Setter
@Entity
@DiscriminatorValue("secretary")
public class Secretary extends User {
    @ManyToMany(mappedBy = "secretaries")
    private Set<Patient> patients;
    @OneToMany(mappedBy = "secretary")
    private Set<Appointment> appointments;

    public Secretary(String fullName, String nationalCode, String password, UserType userType, Set<Patient> patients, Set<Appointment> appointments) {
        super(fullName, nationalCode, password, userType);
        this.patients = patients;
        this.appointments = appointments;
    }

    public Secretary(String fullName, String nationalCode, String password, UserType userType) {
        super(fullName, nationalCode, password, userType);
    }

    public Secretary() {
    }
}
