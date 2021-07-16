package study.rtaskV2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class RTaskV2Application {

	public static void main(String[] args) {
		SpringApplication.run(RTaskV2Application.class, args);
	}

}
