package com.superhero.superhero_v4.repository;

import com.superhero.superhero_v4.SuperInterface.ISuperheroRepository;
import com.superhero.superhero_v4.dto.CityDTO;
import com.superhero.superhero_v4.dto.SuperheroSuperpowerDTO;
import com.superhero.superhero_v4.dto.SuperpowerCountDTO;
import com.superhero.superhero_v4.model.Superhero;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository("Superhero_stub")
public class SuperheroStubRepository implements ISuperheroRepository {
    private List<Superhero> superheroList;
    private List<SuperpowerCountDTO> superpowerCountDTOS;
    private List<SuperheroSuperpowerDTO> superheroSuperpowerDTO;
    private List<CityDTO> cityDTOS;

    public SuperheroStubRepository() {
        superheroList = new ArrayList<>(List.of(
                new Superhero(100, "Superman", "Clark Kent", "1938-04-18", 10),
                new Superhero(101, "Batman", "Bruce Wayne", "1915-04-07", 11),
                new Superhero(102, "Flash", "Barry Allan", "1956-09-30", 12),
                new Superhero(103, "Batwoman", "Katherine Rebecca Kane", "1990-01-26", 13),
                new Superhero(104, "Supergirl", "Linda Lee Danvers", "1966-09-22", 14)
        ));

        superpowerCountDTOS = new ArrayList<>(List.of(
                new SuperpowerCountDTO("Superman", "Clark Kent", 3),
                new SuperpowerCountDTO("Batman", "Bruce Wayne", 3),
                new SuperpowerCountDTO("FLash", "Barry Allan", 1),
                new SuperpowerCountDTO("Batwoman", "Katherine Rebecca Kane", 3),
                new SuperpowerCountDTO("Supergirl", "Linda Lee Danvers", 3)
        ));

        superheroSuperpowerDTO = new ArrayList<>(List.of(
                new SuperheroSuperpowerDTO("Superman", "Clark Kent", new ArrayList<>(List.of(
                        "Fly", "Strong", "Heatvision"))),

                new SuperheroSuperpowerDTO("Batman", "Bruce Wayne", new ArrayList<>(List.of(
                        "Stealth", "Intelligence", "Stealth"
                ))),

                new SuperheroSuperpowerDTO("Flash", "Barry Allan", new ArrayList<>(List.of(
                        "Fast"))),

                new SuperheroSuperpowerDTO("Batwoman", "Katherine Rebecca Kane", new ArrayList<>(List.of(
                        "Stealth", "Intelligence", "Stealth"
                ))),

                new SuperheroSuperpowerDTO("Supergirl", "Linda Lee Denvers", new ArrayList<>(List.of(
                        "Fly", "Strong", "Heatvision")))
        ));

        cityDTOS = new ArrayList<>(List.of(
                new CityDTO("Metropolis", new ArrayList<>(List.of("Superman","Supergirl"))),
                new CityDTO("Gotham", new ArrayList<>(List.of("Batman","Batwoman"))),
                new CityDTO("Central City", new ArrayList<>(List.of("Flash")))
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

    @Override
    public List<SuperpowerCountDTO> getSuperpowerCount() {
        return superpowerCountDTOS;
    }

    @Override
    public SuperpowerCountDTO getSuperpowerCountHero(String name) {
        for (SuperpowerCountDTO dto : superpowerCountDTOS) {
            if (name.equalsIgnoreCase(dto.getHeroName())) {
                return dto;
            }
        }
        return null;
    }

    @Override
    public List<SuperheroSuperpowerDTO> getHeroesSuperpower() {
        return superheroSuperpowerDTO;
    }

    @Override
    public SuperheroSuperpowerDTO getHeroSuperpower(String name) {
        for (SuperheroSuperpowerDTO dto : superheroSuperpowerDTO) {
            if (name.equalsIgnoreCase(dto.getHeroName())) {
                return dto;
            }
        }
        return null;
    }

    @Override
    public CityDTO getCity(String cityName) {
        for (CityDTO dto : cityDTOS) {
            if (cityName.equalsIgnoreCase(dto.getCity())) {
                return dto;
            }
        }
        return null;
    }

    @Override
    public List<CityDTO> getAllHeroesInCity() {
        return cityDTOS;
    }
}
