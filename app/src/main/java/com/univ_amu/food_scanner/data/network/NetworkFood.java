package com.univ_amu.food_scanner.data.network;

import java.util.List;

public class NetworkFood {
    String code;
    String name;
    String brands;
    String nutriscore;
    List<NetworkQuantity> quantities;

    public static class NetworkQuantity {
        String name;
        int rank ;
        int level;
        double quantity;
        String unit;

        public  String toString(){
            return  "quantity : " +"\n name : "+name +"\n rank : "+rank + "\n level "+level+"\n quantity: "+quantity+"\n unit " +unit;
        }

    }
    public  String toString(){
        return  "Foodnetwork : " +"\n code : "+code+ "\n name : "+name +"\n brands : "+brands + "\n nutricorse "+nutriscore;
    }


}
