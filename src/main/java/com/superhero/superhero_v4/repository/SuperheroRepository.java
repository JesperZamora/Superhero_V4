package com.superhero.superhero_v4.repository;

import com.superhero.superhero_v4.SuperInterface.ISuperheroRepository;
import com.superhero.superhero_v4.dto.CityDTO;
import com.superhero.superhero_v4.dto.SuperheroSuperpowerDTO;
import com.superhero.superhero_v4.dto.SuperpowerCountDTO;
import com.superhero.superhero_v4.model.Superhero;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository("Superhero_db")
public class SuperheroRepository implements ISuperheroRepository {
    @Value("${spring.datasource.url}")
    String db_url;
    @Value("${spring.datasource.username}")
    String u_id;
    @Value("${spring.datasource.password}")
    String pwd;

    @Override
    public Superhero getSuperhero(String name) {
        Superhero superhero = null;
        try (Connection con = DriverManager.getConnection(db_url, u_id, pwd)) {
            String SQL = "SELECT * FROM superhero WHERE hero_name = ?";
            PreparedStatement ps = con.prepareStatement(SQL);
            ps.setString(1, name);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                int superhero_id = rs.getInt("superhero_id");
                String heroName = rs.getString("hero_name");
                String realName = rs.getString("real_name");
                String creationYear = rs.getString("creation_year");
                int city_id = rs.getInt("city_id");
                superhero = new Superhero(superhero_id, heroName, realName, creationYear, city_id);
            }
            return superhero;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public List<Superhero> getSuperheroList() {
        List<Superhero> superheroes = new ArrayList<>();
        try (Connection con = DriverManager.getConnection(db_url, u_id, pwd)) {
            String SQL = "SELECT * FROM superhero";
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(SQL);

            while (rs.next()) {
                int superhero_id = rs.getInt("superhero_id");
                String heroName = rs.getString("hero_name");
                String realName = rs.getString("real_name");
                String creationYear = rs.getString("creation_year");
                int city_id = rs.getInt("city_id");
                superheroes.add(new Superhero(superhero_id, heroName, realName, creationYear, city_id));
            }
            return superheroes;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public List<SuperpowerCountDTO> getSuperpowerCount() {
        List<SuperpowerCountDTO> superpowerCount = new ArrayList<>();

        try (Connection con = DriverManager.getConnection(db_url, u_id, pwd)) {
            String SQL = "SELECT superhero.superhero_id, hero_name, real_name, COUNT(superhero_id) AS count " +
                    "FROM superhero JOIN superheropower USING(superhero_id) GROUP BY superhero_id;";
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(SQL);

            while (rs.next()) {
                String heroName = rs.getString("hero_name");
                String realName = rs.getString("real_name");
                int count = rs.getInt("count");

                superpowerCount.add(new SuperpowerCountDTO(heroName, realName, count));
            }
            return superpowerCount;

        } catch (SQLException e) {
            throw new RuntimeException(e);

        }
    }

    @Override
    public SuperpowerCountDTO getSuperpowerCountHero(String name) {
        try (Connection con = DriverManager.getConnection(db_url, u_id, pwd)) {
            SuperpowerCountDTO superpowerCount = null;
            String SQL = "SELECT superheropower.superhero_id, hero_name, real_name, COUNT(superheropower.superhero_id) AS count " +
                    "FROM superhero JOIN superheropower WHERE superheropower.superhero_id = superhero.superhero_id AND hero_name =?";
            PreparedStatement ps = con.prepareStatement(SQL);
            ps.setString(1, name);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                String heroName = rs.getString("hero_name");
                String realName = rs.getString("real_name");
                int count = rs.getInt("count");

                superpowerCount = new SuperpowerCountDTO(heroName, realName, count);
            }
            return superpowerCount;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public List<SuperheroSuperpowerDTO> getHeroesSuperpower() {
        List<SuperheroSuperpowerDTO> heroSuperpower = new ArrayList<>();

        try (Connection con = DriverManager.getConnection(db_url, u_id, pwd)) {
            String SQL = "SELECT superhero.superhero_id, hero_name, real_name, superpower FROM superhero " +
                    "JOIN superpower JOIN superheropower ON superpower.superpower_id = superheropower.superpower_id " +
                    "AND superhero.superhero_id = superheropower.superhero_id;";
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(SQL);

            String currentHeroName = "";
            SuperheroSuperpowerDTO superpowerDTO = null;

            while (rs.next()) {
                String heroName = rs.getString("hero_name");
                String realName = rs.getString("real_name");
                String superpower = rs.getString("superpower");

                if (heroName.equals(currentHeroName)) {
                    superpowerDTO.addSuperpower(superpower);
                } else {
                    superpowerDTO = new SuperheroSuperpowerDTO(heroName, realName, new ArrayList<>(List.of(superpower)));
                    heroSuperpower.add(superpowerDTO);
                    currentHeroName = heroName;
                }
            }
            return heroSuperpower;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public SuperheroSuperpowerDTO getHeroSuperpower(String name) {
        SuperheroSuperpowerDTO superheroSuperpower = null;

        try (Connection con = DriverManager.getConnection(db_url, u_id, pwd)) {
            String SQL = "SELECT superhero.superhero_id, hero_name, real_name, superpower FROM superhero " +
                    "JOIN superpower JOIN superheropower ON superpower.superpower_id = superheropower.superpower_id " +
                    "AND superhero.superhero_id = superheropower.superhero_id AND hero_name = ?;";

            PreparedStatement ps = con.prepareStatement(SQL);
            ps.setString(1, name);
            ResultSet rs = ps.executeQuery();

            String currentHeroName = "";
            while (rs.next()) {
                String heroName = rs.getString("hero_name");
                String realName = rs.getString("real_name");
                String superpower = rs.getString("superpower");

                if (heroName.equals(currentHeroName)) {
                    superheroSuperpower.addSuperpower(superpower);
                } else {
                    superheroSuperpower = new SuperheroSuperpowerDTO(heroName, realName, new ArrayList<>(List.of(superpower)));
                    currentHeroName = heroName;
                }
            }
            return superheroSuperpower;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<CityDTO> getCity(String name) {
        List<CityDTO> superheroesCity = new ArrayList<>();

        try (Connection con = DriverManager.getConnection(db_url, u_id, pwd)) {
            String SQL = "SELECT city, hero_name FROM city JOIN superhero " +
                    "WHERE superhero.city_ID = city.city_ID AND city =?";


            PreparedStatement ps = con.prepareStatement(SQL);
            ps.setString(1, name);
            ResultSet rs = ps.executeQuery();

            String currentCity = "";
            CityDTO heroCity = null;

            while (rs.next()) {
                String city = rs.getString("city");
                String heroName = rs.getString("hero_name");

                if (city.equals(currentCity)) {
                    heroCity.addSuperhero(heroName);
                } else {
                    heroCity = new CityDTO(city, new ArrayList<>(List.of(heroName)));
                    superheroesCity.add(heroCity);
                    currentCity = city;
                }
            }
            return superheroesCity;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<CityDTO> getAllHeroesInCity(){
        List<CityDTO> allHeroesInCity = new ArrayList();

        try(Connection con = DriverManager.getConnection(db_url, u_id, pwd)) {
            String SQL = "SELECT city, hero_name FROM city JOIN superhero WHERE superhero.city_ID = city.city_ID GROUP BY superhero_id";
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(SQL);

            String currentCity = "";
            CityDTO heroCity = null;
            while(rs.next()) {
                String city = rs.getString("city");
                String heroName = rs.getString("hero_name");

                if(city.equals(currentCity)){
                    heroCity.addSuperhero(heroName);
                } else {
                    heroCity = new CityDTO(city,new ArrayList<>(List.of(heroName)));
                    allHeroesInCity.add(heroCity);
                    currentCity = city;
                }

            }
            return allHeroesInCity;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }

}
