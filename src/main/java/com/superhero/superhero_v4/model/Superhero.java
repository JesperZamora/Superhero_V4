package com.superhero.superhero_v4.model;

public class Superhero {
    private int id;
    private String heroName;
    private String realName;
    private String creationYear;
    private int cityId;
    public Superhero(int id, String heroName, String realName, String creationYear, int cityId) {
        this.id = id;
        this.heroName = heroName;
        this.realName = realName;
        this.creationYear = creationYear;
        this.cityId = cityId;
    }
    public Superhero(String heroName, String realName, String creationYear) {
        this.heroName = heroName;
        this.realName = realName;
        this.creationYear = creationYear;
    }

    public int getId() {
        return id;
    }


    public String getHeroName() {
        return heroName;
    }

    public String getRealName() {
        return realName;
    }

    public String getCreationYear() {
        return creationYear;
    }

    public int getCityId() {
        return cityId;
    }
}
