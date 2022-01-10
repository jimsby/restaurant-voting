package jimsby.restaurantvotingservice;

import jimsby.restaurantvotingservice.repository.VoteRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.LocalDate;

@SpringBootApplication
@AllArgsConstructor
public class RestaurantVotingApplication implements ApplicationRunner {
private final VoteRepository voteRepository;
    public static void main(String[] args) {
        SpringApplication.run(RestaurantVotingApplication.class, args);
    }


    @Override
    public void run(ApplicationArguments args) throws Exception {
        System.out.println(voteRepository.getVotesByDateEquals(LocalDate.now()));
        System.out.println(LocalDate.now());
        System.out.println(voteRepository.findAll());
    }
}
