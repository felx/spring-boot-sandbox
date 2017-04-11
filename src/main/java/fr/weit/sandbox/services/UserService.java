package fr.weit.sandbox.services;

import fr.weit.sandbox.models.Role;
import fr.weit.sandbox.models.User;
import fr.weit.sandbox.models.UserRole;
import fr.weit.sandbox.repositories.RoleRepository;
import fr.weit.sandbox.repositories.UserRepository;
import fr.weit.sandbox.repositories.UserRoleRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import java.time.LocalDateTime;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created by fel on 06/03/2017.
 */
@Slf4j
@Service
public class UserService {


    @Autowired
    RoleRepository roleRepository;
    @Autowired
    UserRepository userRepository;

    @Autowired
    UserRoleRepository userRoleRepository;


    @Transactional
    public void prepareData() {
        log.info("create role");
        Role role = roleRepository.save(
                Stream.of(Role.RoleName.values())
                        .map(
                                rn -> Role.builder()
                                        .role(rn)
                                        .build()
                        ).collect(Collectors.toList())
        ).iterator().next();
        log.info("create user");
        User user = userRepository.save(
                User.builder()
                        .dateOfBirth(LocalDateTime.parse("2017-03-07T18:00:00"))
                        .activated(true)
                        .name("name")
                        .email("name@email.org")
                        .build()
        );
        log.info("create user -> roles");
        userRoleRepository.save(
                Stream.of(
                        UserRole.builder()
                                .activated(true)
                                .role(role)
                                .user(user)
                                .lastModified(LocalDateTime.now())
                                .lastModifiedBy("init")
                                .build()
                ).collect(Collectors.toSet())
        );

    }
}
