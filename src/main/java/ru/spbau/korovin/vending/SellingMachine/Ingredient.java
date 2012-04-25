package ru.spbau.korovin.vending.SellingMachine;

public class Ingredient {
    private int count;
    private final int price;
    private final String name;
    private final boolean requiresContainer;
    private final boolean mixable;


    public Ingredient(String name, int count, int price, boolean requiresContainer, boolean mixable) {
        this.count = count;
        this.price = price;
        this.name = name;
        this.requiresContainer = requiresContainer;
        this.mixable = mixable;
    }

    public void sell() {
        count--;
    }

    public int getPrice() {
        return price;
    }

    public int getCount() {
        return count;
    }

    public String getName() {
        return name;
    }

    public boolean isRequiresContainer() {
        return requiresContainer;
    }

    public boolean isMixable() {
        return mixable;
    }
}
