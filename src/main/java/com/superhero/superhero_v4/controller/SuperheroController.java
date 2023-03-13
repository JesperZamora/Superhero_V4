package com.superhero.superhero_v4.controller;

import com.superhero.superhero_v4.SuperInterface.ISuperheroRepository;
import com.superhero.superhero_v4.dto.CityDTO;
import com.superhero.superhero_v4.dto.SuperheroSuperpowerDTO;
import com.superhero.superhero_v4.dto.SuperpowerCountDTO;
import com.superhero.superhero_v4.model.Superhero;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
@RequestMapping("/superhero")
@Controller
public class SuperheroController {
    ISuperheroRepository superheroRepository;

    public SuperheroController(ApplicationContext context, @Value("${superhero.repository.impl}") String impl) {
        superheroRepository = (ISuperheroRepository) context.getBean(impl);
    }

    @GetMapping("/{name}") //request 1.0 - one hero
    public ResponseEntity<Superhero> getSuperhero(@PathVariable String name) {
        Superhero superhero = superheroRepository.getSuperhero(name);
        return new ResponseEntity<>(superhero, HttpStatus.OK);
    }


    @GetMapping() //request 1.1 - all heroes
    public ResponseEntity<List<Superhero>> getSuperheros() {
        List<Superhero> superheroList = superheroRepository.getSuperheroList();
        return new ResponseEntity<>(superheroList, HttpStatus.OK);
    }

    @GetMapping("/superpower/count") //request 2.0 - all heroes with their superpower count
    public ResponseEntity<List<SuperpowerCountDTO>> getAllHeroesWithPowerCount() {
        List<SuperpowerCountDTO> superpowerCountList = superheroRepository.getSuperpowerCount();
        return new ResponseEntity<>(superpowerCountList, HttpStatus.OK);
    }

    @GetMapping("/superpower/count/{name}") //request 2.1 - one hero with superpower count
    public ResponseEntity<SuperpowerCountDTO> getAllHeroesWithPowerCount(@PathVariable String name) {
        SuperpowerCountDTO superpowerCount = superheroRepository.getSuperpowerCountHero(name);
        return new ResponseEntity<>(superpowerCount, HttpStatus.OK);
    }

    @GetMapping("/superpower") //request 3.0 - all heroes with their superpowers
    public ResponseEntity<List<SuperheroSuperpowerDTO>> getAllHeroesWithPower() {
        List<SuperheroSuperpowerDTO> superheroSuperpower = superheroRepository.getHeroesSuperpower();
        return new ResponseEntity<>(superheroSuperpower, HttpStatus.OK);
    }

    @GetMapping("/superpower/{name}") //request 3.1 - one hero with superpowers
    public ResponseEntity<SuperheroSuperpowerDTO> getOneHeroWithPower(@PathVariable String name) {
        SuperheroSuperpowerDTO superheroSuperpower = superheroRepository.getHeroSuperpower(name);
        return new ResponseEntity<>(superheroSuperpower, HttpStatus.OK);
    }


    @GetMapping("/city/{name}") // request 4 - displays city with x amount of heroes
    public ResponseEntity<CityDTO> getCity(@PathVariable String name) {
        CityDTO cities = superheroRepository.getCity(name);
        return new ResponseEntity<>(cities, HttpStatus.OK);
    }

    @GetMapping("/city") // 4.1 - displays all cities and heroes in them
    public ResponseEntity<List<CityDTO>> getAllHeroesInCity() {
        List<CityDTO> allHeroesInCity = superheroRepository.getAllHeroesInCity();
        return new ResponseEntity<>(allHeroesInCity,HttpStatus.OK);
    }

}

