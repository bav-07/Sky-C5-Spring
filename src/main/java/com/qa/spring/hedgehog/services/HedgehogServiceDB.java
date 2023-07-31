package com.qa.spring.hedgehog.services;

import com.qa.spring.hedgehog.domain.Hedgehog;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class HedgehogServiceDB implements HedgehogService {
    @Override
    public Hedgehog create(Hedgehog hedgehog) {
        return null;
    }

    @Override
    public List<Hedgehog> create(List<Hedgehog> newHedgehogs) {
        return null;
    }

    @Override
    public List<Hedgehog> getAll() {
        return null;
    }

    @Override
    public Hedgehog getById(int id) {
        return null;
    }

    @Override
    public Hedgehog update(int id, String name, String colour, Integer age) {
        return null;
    }

    @Override
    public Hedgehog remove(int id) {
        return null;
    }
}
