package ru.spbau.korovin.vending.SellingMachine;

import java.util.ArrayList;
import java.util.List;

class Order {
    private final List<Ingredient> check = new ArrayList<>();

    public void add(Ingredient ingredient) {
        check.add(ingredient);
    }

    public int countSum() {
        int result = 0;
        for (Ingredient ingredient : check) {
            result += ingredient.getPrice();
        }
        return result;
    }

    public String getCheck() {
        String result = "";
        for (Ingredient ingredient : check) {
            result += ingredient.getName() + " and ";
        }
        return result.substring(0, result.length() - 5);
    }

    public boolean isUnproperMix() {
        if (check.size() <= 1) {
            return false;
        }

        // Last added good should be mixable
        return !check.get(check.size() - 1).isMixable();
    }

    public void removeLast() {
        check.remove(check.size() - 1);
    }
}
