package com.qa.spring.hedgehog.services;

import com.qa.spring.hedgehog.domain.Hedgehog;

import java.util.List;


public interface HedgehogService {

    Hedgehog create(Hedgehog hedgehog);

    List<Hedgehog> create(List<Hedgehog> newHedgehogs);


    List<Hedgehog> getAll();


    Hedgehog getById(int id);


    Hedgehog update(int id, String name, String colour, Integer age);

    Hedgehog remove(int id);

}
