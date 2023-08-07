package com.qa.spring.hedgehog.service;

import com.qa.spring.hedgehog.domain.Hedgehog;
import com.qa.spring.hedgehog.repos.HedgehogRepo;
import com.qa.spring.hedgehog.services.HedgehogService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Optional;

@SpringBootTest // boots the context (loads all the beans)
public class HedgehogServiceDBUnitTest {


    @Autowired
    private HedgehogService service;

    @MockBean // instructs spring to create a mock version of this class
    private HedgehogRepo repo;


    @Test
    void testUpdate() {
        // GIVEN
        int id = 1;
        Hedgehog existing = new Hedgehog(id, "Barry", "Blue", 8);
        String name = "Larry";
        String colour = "Red";
        int age = 12;
        Hedgehog updated = new Hedgehog(id, name, colour, age);

        // WHEN
        Mockito.when(this.repo.findById(id)).thenReturn(Optional.of(existing));
        Mockito.when(this.repo.save(updated)).thenReturn(updated);

        // THEN
        Assertions.assertEquals(updated, this.service.update(id, name, colour, age));

        Mockito.verify(this.repo, Mockito.times(1)).findById(id);
        Mockito.verify(this.repo, Mockito.times(1)).save(updated);
    }


}
