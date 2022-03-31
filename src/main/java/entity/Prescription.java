package entity;

import entity.baseEntity.BaseEntity;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
public class Prescription extends BaseEntity<Integer> {
    private Date date;
    private String medication;
    private String description;
    @ManyToOne
    private Doctor doctor;
    @OneToOne
    private Patient patient;
}
