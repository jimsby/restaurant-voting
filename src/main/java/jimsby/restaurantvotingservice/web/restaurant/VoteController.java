package jimsby.restaurantvotingservice.web.restaurant;

import jimsby.restaurantvotingservice.model.Meal;
import jimsby.restaurantvotingservice.model.Restaurant;
import jimsby.restaurantvotingservice.model.Vote;
import jimsby.restaurantvotingservice.repository.VoteRepository;
import jimsby.restaurantvotingservice.service.VoteService;
import jimsby.restaurantvotingservice.to.RestaurantTo;
import jimsby.restaurantvotingservice.web.AuthUser;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping(value = VoteController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
@Slf4j
@AllArgsConstructor
public class VoteController {

    static final String REST_URL = "/api/restaurant/{rest_id}/vote";

    protected final VoteRepository repository;
    protected final VoteService service;

    @GetMapping
    public List<Vote> getToday(@PathVariable int rest_id) {
        log.info("Menu and count voting for restaurant {} today", rest_id);
        List<Vote> votes = repository.getByRest(rest_id);
        return votes;
    }

    @GetMapping("/with-meals")
    public RestaurantTo getTodayWithMeals(@PathVariable int rest_id) {
        log.info("Menu and count voting for restaurant {} today", rest_id);
        return service.get(rest_id);
    }

    @GetMapping("by-date")
    public RestaurantTo getByDate(@PathVariable int rest_id, @RequestParam @Nullable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
        return service.getForDate(rest_id, date);
    }

    @PostMapping
    public ResponseEntity<Vote> vote(@PathVariable int rest_id, @AuthenticationPrincipal AuthUser authUser){
        log.info("Add voting for restaurant {}", rest_id);
        Vote created = service.vote(rest_id, authUser.id());
        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(REST_URL)
                .buildAndExpand(rest_id).toUri();
        return ResponseEntity.created(uriOfNewResource).body(created);
    }
}
