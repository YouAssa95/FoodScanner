package com.univ_amu.food_scanner.data;

public class Quantity {
    public Quantity(String foodCode, String name, int rank, int level, double value, String unit) {
        this.foodCode = foodCode;
        this.name = name;
        this.rank = rank;
        this.level = level;
        this.value = value;
        this.unit = unit;
    }

    public String foodCode;
    public String name;
    public int rank;
    public int level;
    public double value;
    public String unit;
}