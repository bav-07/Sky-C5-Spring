package com.qa.spring.hedgehog.services;

import com.qa.spring.hedgehog.domain.Hedgehog;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

//@Primary
@Service
public class HedgehogServiceList implements HedgehogService {

    private List<Hedgehog> hedgehogs = new ArrayList<>();

    @Override
    public Hedgehog create(Hedgehog hedgehog) {
        this.hedgehogs.add(hedgehog);
        Hedgehog created = this.hedgehogs.get(this.hedgehogs.size() - 1);
        return created;
    }

    @Override
    public List<Hedgehog> create(List<Hedgehog> newHedgehogs) {
        if (this.hedgehogs.addAll(newHedgehogs)) {
            return newHedgehogs;
        } else {
            return null;
        }
    }

    @Override
    public List<Hedgehog> getAll() {
        return this.hedgehogs;
    }

    @Override
    public Hedgehog getById(int id) {
        Hedgehog found = this.hedgehogs.get(id);
        return found;
    }

    @Override
    public Hedgehog update(int id, String name, String colour, Integer age) {
        Hedgehog toUpdate = this.hedgehogs.get(id);
        if (name != null) toUpdate.setName(name);
        if (colour != null) toUpdate.setColour(colour);
        if (age != null) toUpdate.setAge(age);
        return toUpdate;
    }

    @Override
    public Hedgehog remove(int id) {
        return this.hedgehogs.remove(id);
    }

    @Override
    public List<Hedgehog> findByName(String name) {
        List<Hedgehog> found = new ArrayList<>();
        for (Hedgehog h : this.hedgehogs) {
            if (name.equals(h.getName())) found.add(h);
        }
        return found;
    }

    @Override
    public Integer findAgeByName(String name) {
        return null;
    }
}
