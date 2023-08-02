package com.qa.spring.hedgehog.repos;

import com.qa.spring.hedgehog.domain.Hedgehog;
import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HedgehogRepo extends JpaRepository<Hedgehog, Integer> {

    List<Hedgehog> findByNameContainsIgnoreCase(String name);

    // JPQL -> Java Persistence Query Language
    @Query(value = "SELECT age FROM hedgehog WHERE name = ?1", nativeQuery = true)
//    @Query("SELECT h.age FROM Hedgehog h WHERE h.name = ?1 ")
    Integer findAgeByName(String name);

}
