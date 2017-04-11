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
public class User {

    @Id
    @GeneratedValue
    private Long id;

    private String name;
    private LocalDateTime dateOfBirth;


    private String email;

    private Boolean activated;

    @OneToMany(cascade = CascadeType.PERSIST, targetEntity = UserRole.class, mappedBy = "user")
    @JsonBackReference
    private Set<UserRole> userRoles;

}
