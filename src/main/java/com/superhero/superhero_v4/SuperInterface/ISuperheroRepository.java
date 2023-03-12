package com.superhero.superhero_v4.SuperInterface;

import com.superhero.superhero_v4.dto.CityDTO;
import com.superhero.superhero_v4.dto.SuperheroSuperpowerDTO;
import com.superhero.superhero_v4.dto.SuperpowerCountDTO;
import com.superhero.superhero_v4.model.Superhero;

import java.util.List;

public interface ISuperheroRepository {
    default Superhero getSuperhero(String name) {
        return null;
    }

    default List<Superhero> getSuperheroList() {
        return null;
    }

    default List<SuperpowerCountDTO> getSuperpowerCount() {
        return null;
    }

    default SuperpowerCountDTO getSuperpowerCountHero(String name) {
        return null;
    }

    default List<SuperheroSuperpowerDTO> getHeroesSuperpower() {
        return null;
    }

    default SuperheroSuperpowerDTO getHeroSuperpower(String name) {
        return null;
    }

    default List<CityDTO> getCity(String name) {
        return null;
    }

    default List<CityDTO> getAllHeroesInCity(){
        return null;
    }
}
