package jimsby.restaurantvotingservice.web.restaurant;

import jimsby.restaurantvotingservice.repository.RestaurantRepository;
import jimsby.restaurantvotingservice.web.AbstractControllerTest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static jimsby.restaurantvotingservice.web.restaurant.RestaurantTestData.*;
import static jimsby.restaurantvotingservice.web.user.UserTestData.ADMIN_MAIL;
import static jimsby.restaurantvotingservice.web.user.UserTestData.USER_MAIL;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class RestaurantControllerTest extends AbstractControllerTest {

    private static final String REST_URL = RestaurantController.REST_URL + '/';

    @Autowired
    private RestaurantRepository restaurantRepository;

    @Test
    @WithUserDetails(value = USER_MAIL)
    void get() throws Exception {
        perform(MockMvcRequestBuilders.get(REST_URL + RESTAURANT1_ID))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(RESTAURANT_WITH_MEALS_MATCHER.contentJson(restaurant1));
    }

    @Test
    @WithUserDetails(value = USER_MAIL)
    void getAll() throws Exception {
        perform(MockMvcRequestBuilders.get(REST_URL))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(RESTAURANT_WITH_MEALS_MATCHER.contentJson(restaurant2_today, restaurant1));
    }
}