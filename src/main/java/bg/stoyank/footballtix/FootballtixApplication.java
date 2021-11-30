package bg.stoyank.footballtix;

import bg.stoyank.footballtix.footballmatch.FootballMatch;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.util.List;

@SpringBootApplication
@EnableScheduling
public class FootballtixApplication {
    public static void main(String[] args) {
        SpringApplication.run(FootballtixApplication.class, args);
    }



}
