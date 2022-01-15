package jimsby.restaurantvotingservice.web.restaurant;

import jimsby.restaurantvotingservice.model.Restaurant;
import jimsby.restaurantvotingservice.model.Vote;
import jimsby.restaurantvotingservice.repository.VoteRepository;
import jimsby.restaurantvotingservice.util.JsonUtil;
import jimsby.restaurantvotingservice.web.AbstractControllerTest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.List;

import static jimsby.restaurantvotingservice.web.restaurant.RestaurantTestData.*;
import static jimsby.restaurantvotingservice.web.user.UserTestData.USER_MAIL;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class VoteControllerTest extends AbstractControllerTest {

    private static final String REST_URL = VoteController.REST_URL + '/';

    @Autowired
    private VoteRepository voteRepository;


    @Test
    @WithUserDetails(value = USER_MAIL)
    void getToday() throws Exception {
        perform(MockMvcRequestBuilders.get(REST_URL, RESTAURANT1_ID))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(VOTE_MATCHER.contentJson(List.of(vote1)));

    }

    @Test
    @WithUserDetails(value = USER_MAIL)
    void getTodayWithMeals() throws Exception {
        perform(MockMvcRequestBuilders.get(REST_URL + "/with-meals", RESTAURANT1_ID))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().json(JsonUtil.writeValue(restaurant1To)));

    }

    @Test
    @WithUserDetails(value = USER_MAIL)
    void getByDate() throws Exception {
        perform(MockMvcRequestBuilders.get(REST_URL + "by-date", RESTAURANT2_ID)
                .param("date", "2022-01-10"))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));
    }

    @Test
    @WithUserDetails(value = USER_MAIL)
    void vote() throws Exception {
        Vote newVote = getNewVote();
        ResultActions action = perform(MockMvcRequestBuilders.post(REST_URL, RESTAURANT2_ID)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());

        Vote created = VOTE_MATCHER.readFromJson(action);
        int newId = created.id();
        newVote.setId(newId);
        VOTE_MATCHER.assertMatch(created, newVote);
        VOTE_MATCHER.assertMatch(voteRepository.getById(newId), newVote);
    }
}