package com.qa.spring.hedgehog.services;

import com.qa.spring.hedgehog.domain.Hedgehog;
import com.qa.spring.hedgehog.exceptions.HedgehogNotFoundException;
import com.qa.spring.hedgehog.repos.HedgehogRepo;
import org.springframework.context.annotation.Primary;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import java.util.List;

@Primary
@Service
public class HedgehogServiceDB implements HedgehogService {

    private HedgehogRepo repo;

    public HedgehogServiceDB(HedgehogRepo repo) {
        this.repo = repo;
    }

    @Override
    public Hedgehog create(Hedgehog hedgehog) {
        return this.repo.save(hedgehog);
    }

    @Override
    public List<Hedgehog> create(List<Hedgehog> newHedgehogs) {
        return this.repo.saveAll(newHedgehogs);
    }

    @Override
    public List<Hedgehog> getAll() {
        return this.repo.findAll();
    }

    @Override
    public Hedgehog getById(int id) {
        Hedgehog actualHedgehog = this.repo.findById(id).orElseThrow(() -> new HedgehogNotFoundException());

        return actualHedgehog;
    }

    @Override
    public Hedgehog update(int id, String name, String colour, Integer age) {
        Hedgehog toUpdate = this.getById(id);

        if (name != null) toUpdate.setName(name);
        if (colour != null) toUpdate.setColour(colour);
        if (age != null) toUpdate.setAge(age);

        return this.repo.save(toUpdate);
    }

    @Override
    public Hedgehog remove(int id) {
        Hedgehog toDelete = this.getById(id);
        this.repo.deleteById(id);
        return toDelete;
    }

    @Override
    public List<Hedgehog> findByName(String name) {
        return this.repo.findByNameContainsIgnoreCase(name);
    }

    @Override
    public Integer findAgeByName(String name) {
        return this.repo.findAgeByName(name);
    }
}
