package fr.weit.sandbox.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * Created by fel on 11/04/2017.
 */

@Entity
@Data
@Builder
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor

public class UserRole {

    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    private User user;
    @ManyToOne
    private Role role;



    private Boolean activated;
    private String lastModifiedBy;
    private LocalDateTime lastModified;


}
