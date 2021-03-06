package fr.weit.sandbox.repositories;

import fr.weit.sandbox.models.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by fel on 05/03/2017.
 */
@Repository
public interface UserRepository extends CrudRepository<User, Long> {

    User getByName(String name);

    List<User> findByName(String name);

    List<User> findAll();

//    @Query(value = "SELECT count(u) from UserModel u where u.email is null")
//    Long errors();
//
//    @Query(value = "SELECT count(u) from UserModel u where u.email is null")
//    List<User> weird();

}
