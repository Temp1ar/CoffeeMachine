package ru.spbau.korovin.vending;

import ru.spbau.korovin.vending.SellingMachine.Ingredient;
import ru.spbau.korovin.vending.SellingMachine.Machine;

import java.util.*;

public class MainCoffee {
    private static Machine coffeeMachine;

    public static void main(String[] args) {
        coffeeMachine = prepareCoffeeMachine();

        Scanner s = new Scanner(System.in);
        boolean quit = false;
        while (!quit) {
            String line = s.nextLine();
            String[] parts = line.split(" ");
            switch (parts[0]) {
                case "select":
                    try {
                        String coffee = coffeeMachine.select(parts[1]);
                        int money = coffeeMachine.getChange();
                        System.out.println("Order delivered: " + coffee);
                        System.out.println("Returned: " + money + " dollars.");
                    } catch (Exception e) {
                        System.err.println("Exception caught: " + e.getClass());
                    }
                    break;

                case "insert":
                    try {
                        coffeeMachine.insert(Integer.parseInt(parts[1]));
                    } catch (Exception e) {
                        System.err.println("Exception caught: " + e.getClass());
                    }
                    break;

                case "add":
                    try {
                        coffeeMachine.add(parts[1]);
                    } catch (Exception e) {
                        System.err.println("Exception caught: " + e.getClass());
                    }
                    break;

                case "cancel":
                    int money = coffeeMachine.getChange();
                    System.out.println("Returned: " + money + " dollars.");
                    break;

                case "quit":
                    quit = true;
                    break;

                default:
                    System.err.println("Unknown command");
            }
        }
    }

    static Machine prepareCoffeeMachine() {
        Map<String, Ingredient> goods = new HashMap<>();
        goods.put("capuccino", new Ingredient("capuccino", 2, 4, true, true));
        goods.put("latte", new Ingredient("latte", 3, 6, true, true));
        Map<String, Ingredient> additions = new HashMap<>();
        additions.put("sugar", new Ingredient("sugar", 2, 1, false, true));
        Set<Integer> coins = new HashSet<>();
        coins.add(1);
        coins.add(2);
        coins.add(3);
        coins.add(5);
        return new Machine(2, coins, goods, additions);
    }
}
