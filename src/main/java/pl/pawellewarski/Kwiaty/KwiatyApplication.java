package pl.pawellewarski.Kwiaty;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import pl.pawellewarski.Kwiaty.controller.PostController;

import java.io.File;

@SpringBootApplication
@ComponentScan({"pl.pawellewarski.Kwiaty", "controller"})
public class KwiatyApplication {

    //	public static void main(String[] args) {
//		SpringApplication.run(KwiatyApplication.class, args);
//	}
    public static void main(String[] args) {
        new File(PostController.uploadDirectory).mkdir();
        SpringApplication.run(KwiatyApplication.class, args);
    }
}
