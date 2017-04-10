package fr.weit.sandbox.repositories;

import fr.weit.sandbox.models.Role;
import fr.weit.sandbox.models.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by fel on 05/03/2017.
 */
@Repository
public interface RoleRepository extends CrudRepository<Role, Long> {

   // Role getByRoleName(Role.RoleName roleName);

//    @Query(value = "SELECT count(u) from UserModel u where u.email is null")
//    Long errors();
//
//    @Query(value = "SELECT count(u) from UserModel u where u.email is null")
//    List<User> weird();

}
