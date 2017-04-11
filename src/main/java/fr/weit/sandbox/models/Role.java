package fr.weit.sandbox.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Set;

/**
 * Created by fel on 03/03/2017.
 */

@Entity
@Getter
@Setter
@Builder
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor

public class Role {

    @Id
    @GeneratedValue
    private Long id;

    @Enumerated(EnumType.STRING)
    private RoleName role;
    public enum RoleName {
        ADMIN,USER;
    }

    @OneToMany(cascade = CascadeType.PERSIST, targetEntity = UserRole.class,mappedBy = "role")
    @JsonBackReference
    private Set<UserRole> userRole;
}
