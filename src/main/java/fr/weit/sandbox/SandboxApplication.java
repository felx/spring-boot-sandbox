package fr.weit.sandbox;

import fr.weit.sandbox.models.UserRole;
import fr.weit.sandbox.repositories.UserRoleRepository;
import fr.weit.sandbox.services.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.util.List;

@SpringBootApplication
@Slf4j
@RestController
public class SandboxApplication {
    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void yow(JdbcTemplate jdbcTemplate) {
        log.info("jdbcTemplate : {}", jdbcTemplate);
        this.jdbcTemplate = jdbcTemplate;
    }

    public static void main(String[] args) {
        SpringApplication.run(SandboxApplication.class, args);
    }

    @Autowired
    UserRoleRepository userRoleRepository;

    @RequestMapping("/all")
    public List<UserRole> all() throws IOException {
        List<UserRole> users = userRoleRepository.findAll();
        log.info("users : {}", users);
        return users;

    }

    @Autowired
    UserService userService;

    @PostConstruct
    public void prepareData() {
        userService.prepareData();
    }
}
