package com.univ_amu.food_scanner.data;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import  java.util.Arrays;

public class Repository {
    public Repository() {
        addRandomFoodsIfEmpty();
    }

    public int getFoodCount() {
        return foods.size();
    }

    public List<Food> getFoods() {
        return foods;
    }

    public Food getFood(String foodCode) {
        return foods.stream()
                .filter(food-> food.code.equals(foodCode))
                .findFirst()
                .get();
    }

    public List<Quantity> getQuantities(String foodCode) {
        return quantities.stream()
                .filter(quantity-> quantity.foodCode.equals(foodCode))
                .sorted(Comparator.comparingInt(quantity->quantity.rank))
                .collect(Collectors.toList());
    }

    public void insertFood(Food food, List<Quantity> quantities) {
        foods.add(0, food);
        this.quantities.addAll(quantities);
    }

    public void addRandomFoodsIfEmpty() {
        if (getFoodCount()!=0) return;
        for (Date date : dates) {
            for (int i = 0; i < 2; i++) {
                addRandomFood(date);
            }
        }
    }

    public void addRandomFood(Date date) {
        String foodCode = String.valueOf(random.nextInt(1000000));
        String name = names[random.nextInt(names.length)];
        String nutriscore = nutriscores[random.nextInt(nutriscores.length)];
        String brand = brands[random.nextInt(brands.length)];
        List<Quantity> quantities = generateRandomQuantities(foodCode);
        insertFood(new Food(foodCode, name, brand, nutriscore, date), quantities);
    }

    private List<Quantity> generateRandomQuantities(String foodCode) {
        return Arrays.asList(new Quantity(foodCode, "Énergie",0, 0,
                        random.nextInt(50000)/100.0, "kcal"),
                new Quantity(foodCode, "Glucides",1, 0,
                        random.nextInt(5000)/100.0, "g"),
                new Quantity(foodCode, "Sucre",2, 1,
                        random.nextInt(5000)/100.0, "g"),
                new Quantity(foodCode, "Matières grasses",3, 0,
                        random.nextInt(5000)/100.0, "g"),
                new Quantity(foodCode, "Acide gras saturés",4, 1,
                        random.nextInt(5000)/100.0, "g"));
    }

    private static List<Food> foods = new ArrayList<>();
    private static List<Quantity> quantities = new ArrayList<>();
    private static Random random = new Random();
    private static ZonedDateTime localDateTime = new Date().toInstant().atZone(ZoneId.systemDefault());
    private static Date[] dates = {
            Date.from(localDateTime.minusYears(2).toInstant()),
            Date.from(localDateTime.minusYears(1).toInstant()),
            Date.from(localDateTime.minusMonths(4).toInstant()),
            Date.from(localDateTime.minusDays(1).toInstant()),
            Date.from(localDateTime.toInstant())
    };
    private static String[] names = { "Jus de Fruits", "Biscuits", "Haricots verts" };
    private static String[] brands = { "TropTopFoods", "SuperFoods", "Foods++" };
    private static String[] nutriscores = { "A", "B", "C", "D", "E" };
}