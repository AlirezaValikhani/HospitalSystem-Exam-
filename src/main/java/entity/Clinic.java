package entity;

import entity.baseEntity.BaseEntity;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
public class Clinic extends BaseEntity<Integer> {
    @Column(unique = true,nullable = false)
    private String name;
    @OneToMany(mappedBy = "clinic")
    private Set<Doctor> doctors;

    @Override
    public String toString() {
        return  "Clinic\n" +
                "id = " + getId() + "\n" +
                "name = " + name;
    }
}
