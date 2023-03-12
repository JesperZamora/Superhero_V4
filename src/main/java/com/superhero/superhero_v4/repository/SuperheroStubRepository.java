package com.superhero.superhero_v4.repository;

import com.superhero.superhero_v4.SuperInterface.ISuperheroRepository;
import com.superhero.superhero_v4.model.Superhero;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
@Repository("Superhero_stub")
public class SuperheroStubRepository implements ISuperheroRepository {
    private List<Superhero> superheroList;

    public SuperheroStubRepository() {
        superheroList = new ArrayList<Superhero>(List.of(
                new Superhero(100, "Superman", "Clark Kent", "1938-04-18", 10),
                new Superhero(101, "Batman", "Bruce Wayne", "1915-04-07", 11),
                new Superhero(102, "Flash", "Barry Allan", "1956-09-30", 12),
                new Superhero(103, "Batwoman", "Katherine Rebecca Kane", "1990-01-26", 13),
                new Superhero(104, "Supergirl", "Linda Lee Danvers", "1966-09-22", 14)
        ));
    }

    @Override
    public Superhero getSuperhero(String name) {
        for (Superhero superhero : superheroList) {
            if (superhero.getHeroName().toLowerCase().equals(name)) {
                return superhero;
            }
        }
        return null;
    }

    @Override
    public List<Superhero> getSuperheroList() {
        return superheroList;
    }
}
