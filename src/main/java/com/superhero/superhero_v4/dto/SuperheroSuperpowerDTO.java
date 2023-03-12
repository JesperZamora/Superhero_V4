package com.superhero.superhero_v4.dto;

import java.util.List;

public class SuperheroSuperpowerDTO {
    private String heroName;
    private String realName;
    private List<String> superpower;

    public SuperheroSuperpowerDTO(String heroName, String realName, List<String> superpower) {
        this.heroName = heroName;
        this.realName = realName;
        this.superpower = superpower;
    }

    public String getHeroName() {
        return heroName;
    }

    public String getRealName() {
        return realName;
    }

    public List<String> getSuperpower() {
        return superpower;
    }

    public void addSuperpower(String name) {
        superpower.add(name);
    }
}
