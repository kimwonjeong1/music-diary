package ssafy.musicD;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class MusicDApplication {

	public static final String APPLICATION_LOCATIONS =
			"classpath:mongodb.yml" + ",classpath:redis.yml" + ",classpath:jwt.yml" + ",classpath:youtube.yml" + ",classpath:/application.properties";
	public static void main(String[] args) {
		System.setProperty("spring.config.location", APPLICATION_LOCATIONS);
		SpringApplication.run(MusicDApplication.class, args);
	}

}
