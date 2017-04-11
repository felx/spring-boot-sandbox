package fr.weit.sandbox;

import fr.weit.sandbox.models.User;
import fr.weit.sandbox.models.UserRole;
import fr.weit.sandbox.repositories.UserRepository;
import fr.weit.sandbox.repositories.UserRoleRepository;
import fr.weit.sandbox.services.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.IOException;
import java.util.Collection;
import java.util.Iterator;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@SpringBootApplication
@Slf4j
@RestController
public class SandboxApplication {
    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void JobCompletionNotificationListener(JdbcTemplate jdbcTemplate) {
        log.info("jdbcTemplate : {}", jdbcTemplate);
        this.jdbcTemplate = jdbcTemplate;
    }

    public static void main(String[] args) {
        SpringApplication.run(SandboxApplication.class, args);
    }


    private final Object monitor = new Object();

    @Autowired
    UserRepository userRepository;

    @RequestMapping("/one")
    public User one() throws IOException {
        return userRepository.findByName("name").get(0);
    }

    @Autowired
    UserService userService;

    @PostConstruct
    public void prepareData() {
        userService.prepareData();
    }
}
