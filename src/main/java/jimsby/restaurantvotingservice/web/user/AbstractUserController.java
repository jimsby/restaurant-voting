package jimsby.restaurantvotingservice.web.user;

import jimsby.restaurantvotingservice.model.User;
import jimsby.restaurantvotingservice.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import static jimsby.restaurantvotingservice.util.UserUtil.prepareToSave;

@Slf4j
public abstract class AbstractUserController {

    @Autowired
    protected UserRepository repository;

    public ResponseEntity<User> get(int id) {
        log.info("get user {}", id);
        return ResponseEntity.of(repository.findById(id));
    }

    public void delete(int id) {
        log.info("delete {}", id);
        repository.deleteById(id);
    }

    protected User prepareAndSave(User user) {
        log.info("save {}", user);
        return repository.save(prepareToSave(user));
    }
}
