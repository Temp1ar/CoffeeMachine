package ru.spbau.korovin.vending;

import ru.spbau.korovin.vending.SellingMachine.Ingredient;
import ru.spbau.korovin.vending.SellingMachine.Machine;

import java.util.Scanner;

public class MainVending {
    public static void main(String[] args) {
        Machine machine = MainCoffee.prepareCoffeeMachine();
        machine.addIngredient(new Ingredient("bonaqua", 5, 10, false, false));
        machine.addIngredient(new Ingredient("minerale", 5, 30, false, false));
        machine.addCoin(10);
        machine.addCoin(50);

        Scanner s = new Scanner(System.in);
        boolean quit = false;
        while (!quit) {
            String line = s.nextLine();
            String[] parts = line.split(" ");
            switch (parts[0]) {
                case "select":
                    try {
                        String coffee = machine.select(parts[1]);
                        int money = machine.getChange();
                        System.out.println("Coffee delivered: " + coffee);
                        System.out.println("Returned: " + money + " dollars.");
                    } catch (Exception e) {
                        System.err.println("Exception caught: " + e.getClass());
                    }
                    break;

                case "insert":
                    try {
                        machine.insert(Integer.parseInt(parts[1]));
                    } catch (Exception e) {
                        System.err.println("Exception caught: " + e.getClass());
                    }
                    break;

                case "add":
                    try {
                        machine.add(parts[1]);
                    } catch (Exception e) {
                        System.err.println("Exception caught: " + e.getClass());
                    }
                    break;

                case "cancel":
                    int money = machine.getChange();
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
}
