package fr.weit.sandbox;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.File;
import java.io.IOException;

@SpringBootApplication
@Slf4j
@Controller
public class SandboxApplication {
    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void JobCompletionNotificationListener(JdbcTemplate jdbcTemplate) {
        log.info("jdbcTemplate : {}",jdbcTemplate);
        this.jdbcTemplate = jdbcTemplate;
    }
    public static void main(String[] args) {
        SpringApplication.run(SandboxApplication.class, args);
    }


    private final Object monitor = new Object();


    @RequestMapping("/dumpdb")
         public void dumpDb() throws IOException {
        synchronized (this.monitor) {
            File dump = new File("dump.sql");
            if (dump.exists()) {
                dump.delete();
            }
            //this.jdbcTemplate.execute("script '" + dump.getAbsolutePath() + "'");
            this.jdbcTemplate.execute("show create TABLE user;");
        }
    }
}
