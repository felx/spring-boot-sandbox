package fr.weit.sandbox.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;
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
public class User {

    @Id
    @GeneratedValue
    private Long id;

    private String name;
    private LocalDateTime dateOfBirth;


    private String email;

    private Boolean activated;

    @OneToMany(cascade = CascadeType.ALL,targetEntity = UserRole.class,mappedBy = "user")
    private Set<UserRole> userRoles;

}
