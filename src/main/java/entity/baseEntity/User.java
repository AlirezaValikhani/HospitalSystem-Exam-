package entity.baseEntity;

import entity.UserType;
import lombok.*;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "users")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "user_type",discriminatorType = DiscriminatorType.STRING)
@Entity
public class User extends BaseEntity<Integer>{
    private String fullName;
    @Column(name = "national_code",unique = true,nullable = false)
    private String nationalCode;
    private String password;
    private UserType userType;
    @Transient
    public String getDiscriminatorValue(){
        DiscriminatorValue val = this.getClass().getAnnotation( DiscriminatorValue.class );

        return val == null ? null : val.value();
    }
}
