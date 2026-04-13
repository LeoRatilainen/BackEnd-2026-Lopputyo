package fi.haagahelia.lopputyo;

import fi.haagahelia.lopputyo.domain.Category;
import fi.haagahelia.lopputyo.domain.CategoryRepository;
import fi.haagahelia.lopputyo.domain.Training;
import fi.haagahelia.lopputyo.domain.TrainingRepository;
import fi.haagahelia.lopputyo.domain.User;
import fi.haagahelia.lopputyo.domain.UserRepository;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.HashSet;
import java.util.Set;

@SpringBootApplication
public class LopputyoApplication {

    public static void main(String[] args) {
        SpringApplication.run(LopputyoApplication.class, args);
    }

    @Bean
    public CommandLineRunner createDemoRows(TrainingRepository trainingRepository, 
                                            CategoryRepository categoryRepository,
                                            UserRepository userRepository,
                                            BCryptPasswordEncoder passwordEncoder) {
        return (args) -> {
            
            if (userRepository.findByUsername("admin") == null) {
                User admin = new User("admin", "admin@app.com", passwordEncoder.encode("admin123"), "ADMIN");
                userRepository.save(admin);
            }

            if (userRepository.findByUsername("user") == null) {
                User user = new User("user", "user@app.com", passwordEncoder.encode("user123"), "USER");
                userRepository.save(user);
            }

            if (categoryRepository.count() == 0) {
                Category chest = new Category("Chest");
                Category back = new Category("Back");
                Category legs = new Category("Legs");
                Category arms = new Category("Arms");
                Category shoulders = new Category("Shoulders");

                categoryRepository.save(chest);
                categoryRepository.save(back);
                categoryRepository.save(legs);
                categoryRepository.save(arms);
                categoryRepository.save(shoulders);

                Set<Category> pushUpMuscles = new HashSet<>();
                pushUpMuscles.add(chest);
                pushUpMuscles.add(arms);
                pushUpMuscles.add(shoulders);
                
                Set<Category> pullUpMuscles = new HashSet<>();
                pullUpMuscles.add(back);
                pullUpMuscles.add(arms);
                
                Set<Category> squatMuscles = new HashSet<>();
                squatMuscles.add(legs);

                trainingRepository.save(new Training("Push Ups", 15, 3, 45, pushUpMuscles));
                trainingRepository.save(new Training("Pull Ups", 5, 3, 60, pullUpMuscles));
                trainingRepository.save(new Training("Squats", 30, 5, 30, squatMuscles));
            }
        };
    }
}