package pe.edu.upc.fromzero;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackages = "pe.edu.upc.fromzero.Repositories")
@ComponentScan(basePackages = "pe.edu.upc.fromzero")
public class FromZeroApplication {

    public static void main(String[] args) {
        SpringApplication.run(FromZeroApplication.class, args);
    }
}

