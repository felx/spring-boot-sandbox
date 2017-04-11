package fr.weit.sandbox.models;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * Created by fel on 11/04/2017.
 */

@Entity
@Getter
@Setter
@Builder
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor

public class UserRole {

    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne(optional = false,fetch = FetchType.EAGER)
    @JsonManagedReference
    private User user;

    @ManyToOne(optional = false,fetch = FetchType.EAGER)
    @JsonManagedReference
    private Role role;



    private Boolean activated;
    private String lastModifiedBy;
    private LocalDateTime lastModified;


}
