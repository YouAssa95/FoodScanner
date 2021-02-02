package com.univ_amu.food_scanner.data;

import java.util.Date;

public class Food {
    public Food(String code, String name, String brands, String nutriscore, Date date) {
        this.code = code;
        this.name = name;
        this.brands = brands;
        this.nutriscore = nutriscore;
        this.date = date;
    }

    public String code;
    public String name;
    public String brands;
    public String nutriscore;
    public Date date;
}