package com.superhero.superhero_v4.dto;

public class SuperpowerCountDTO {
    private String heroName;
    private String realName;
    private int count;

    public SuperpowerCountDTO(String heroName, String realName, int count) {
        this.heroName = heroName;
        this.realName = realName;
        this.count = count;
    }


    public String getHeroName() {
        return heroName;
    }

    public String getRealName() {
        return realName;
    }

    public int getCount() {
        return count;
    }
}
