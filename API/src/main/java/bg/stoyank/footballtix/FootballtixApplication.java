package bg.stoyank.footballtix;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class FootballtixApplication {
    public static void main(String[] args) {
        SpringApplication.run(FootballtixApplication.class, args);
    }
}
