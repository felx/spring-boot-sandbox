package fr.weit.sandbox.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Set;

/**
 * Created by fel on 03/03/2017.
 */

@Entity
@Data
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

    @OneToMany(cascade = CascadeType.ALL, targetEntity = UserRole.class,fetch = FetchType.LAZY,mappedBy = "role")
    private Set<UserRole> userRole;
}
