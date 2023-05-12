package com.liberty52.auth.service.applicationservice;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

import com.liberty52.auth.service.controller.dto.ReviewerProfileResponse;
import com.liberty52.auth.service.entity.Auth;
import jakarta.persistence.EntityManager;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@SpringBootTest
class ProfileRetrieveServiceImplTest {

    @Autowired
    ProfileRetrieveService service;

    @Autowired
    EntityManager em;
    Set<String> ids;

    @BeforeEach
    void beforeEach(){
        em.clear();

        ids = new HashSet<>();
        for (int i = 0; i < 3; i++) {
            Auth profile = Auth.createUser("uniqueEmail" + i, "12345678",
                    "KIM", null, "PROFILE");
            profile.updateUser(null, "KIM"+profile.getId());
            profile.updateUserProfile("PROFILE");
            ids.add(profile.getId());
            em.persist(profile);
        }
        ids.add("guest_id");


        em.flush();

    }

    @AfterEach
    void afterEach(){
        em.clear();;
    }

    @Test
    void retrieveReviewerProfile () throws Exception{
        //given
        Map<String, ReviewerProfileResponse> response = service.retrieveReviewerProfile(
                ids);

        //when
        assertThat(response.size()).isSameAs(4);
        for (String id : ids) {
            if ("guest_id".equals(id)) {
                assertThat(response.get(id).getAuthorName()).isEqualTo("guest_id");
                assertThat(response.get(id).getAuthorProfileUrl()).isNull();
                continue;
            }
            assertThat(response.get(id).getAuthorName()).isEqualTo("KIM"+id);
            assertThat(response.get(id).getAuthorProfileUrl()).isEqualTo("PROFILE");
        }

        //then


    }


}