package ru.spbau.korovin.vending.SellingMachine;

import java.util.Map;
import java.util.Set;

public class Machine {
    private int balance = 0;
    private int containerCount;
    private final Set<Integer> acceptedCoins;
    private final Map<String, Ingredient> goods;
    private final Map<String, Ingredient> additions;
    private Order order = new Order();

    public Machine(int containerCount,
                   Set<Integer> acceptedCoins,
                   Map<String, Ingredient> goods,
                   Map<String, Ingredient> additions) {
        this.containerCount = containerCount;
        this.acceptedCoins = acceptedCoins;
        this.goods = goods;
        this.additions = additions;
    }

    public void insert(int coin) {
        if (!acceptedCoins.contains(coin)) {
            throw new NotAcceptedCoin();
        }

        balance += coin;
    }

    public String select(String good) {
        Ingredient ingredient = goods.get(good);

        if (ingredient == null) {
            throw new UnknownGood();
        }


        if (ingredient.getCount() <= 0) {
            throw new NotEnoughIngredient();
        }

        if (ingredient.isRequiresContainer() && containerCount <= 0) {
            throw new NotEnoughContainers();
        }

        order.add(ingredient);

        if (order.countSum() > balance) {
            order.removeLast();
            throw new NotEnoughMoney();
        }

        if (order.isUnproperMix()) {
            order.removeLast();
            throw new NotMixable();
        }

        ingredient.sell();
        balance -= order.countSum();
        containerCount -= ingredient.isRequiresContainer() ? 1 : 0;
        return order.getCheck();
    }

    public void addIngredient(Ingredient ingredient) {
        goods.put(ingredient.getName(), ingredient);
    }

    public void addAddition(Ingredient ingredient) {
        additions.put(ingredient.getName(), ingredient);
    }

    public void addCoin(int coin) {
        acceptedCoins.add(coin);
    }

    public void add(String addition) {
        Ingredient ingredient = additions.get(addition);

        if (!additions.keySet().contains(addition)) {
            throw new UnknownAddition();
        }

        if (ingredient.getCount() > 0) {
            order.add(ingredient);
        } else {
            throw new NotEnoughContainers();
        }
    }

    public int getChange() {
        int funds = balance;
        balance = 0;
        order = new Order();
        return funds;
    }
}
