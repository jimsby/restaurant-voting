package jimsby.restaurantvotingservice.web.restaurant;

import jimsby.restaurantvotingservice.model.Meal;
import jimsby.restaurantvotingservice.repository.MealRepository;
import jimsby.restaurantvotingservice.service.RestaurantService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.time.LocalDate;
import java.util.List;

import static jimsby.restaurantvotingservice.util.ValidationUtil.assureIdConsistent;

@RestController
@RequestMapping(value = AdminMenuController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
@Slf4j
@AllArgsConstructor
@CacheConfig(cacheNames = "restaurants")
public class AdminMenuController {
    static final String REST_URL = "/api/admin/restaurant/{rest_id}/menu";

    private final MealRepository repository;
    private final RestaurantService service;

    @GetMapping
    public List<Meal> get(@PathVariable int rest_id) {
        log.info("get menu by restaurant {} today", rest_id);
        return repository.findMealsByRestaurantToday(rest_id);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Meal> getById(@PathVariable int id, @PathVariable int rest_id) {
        log.info("get Meal by restaurant {}  for id {}", rest_id, id);
        return ResponseEntity.of(repository.get(id, rest_id));
    }

    @GetMapping("by-date")
    public List<Meal> getByDate(@PathVariable int rest_id, @RequestParam @Nullable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
        log.info("get menu by restaurant {} for date {}", rest_id, date);
        return repository.findMealsByRestaurantAndDate(rest_id, date);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @CacheEvict(allEntries = true)
    public void delete(@PathVariable int id, @PathVariable int rest_id) {
        log.info("delete Meal by restaurant {}  for id {}", rest_id, id);
        repository.deleteById(id);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @CacheEvict(allEntries = true)
    public ResponseEntity<Meal> create(@Valid @RequestBody Meal meal, @PathVariable int rest_id) {
        log.info("create {}", meal);
        Meal created = service.save(meal, rest_id);
        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(REST_URL + "/{id}")
                .buildAndExpand(rest_id, created.getId()).toUri();
        return ResponseEntity.created(uriOfNewResource).body(created);
    }

    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @CacheEvict(allEntries = true)
    public void update(@Valid @RequestBody Meal meal, @PathVariable int id, @PathVariable int rest_id) {
        log.info("update {} with id={}", meal, id);
        assureIdConsistent(meal, id);
        repository.checkBelong(id, rest_id);
        service.save(meal, rest_id);
    }
}
