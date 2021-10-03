package com.company.cardGame.actor;

import com.company.Utils.Console;
import com.company.cardGame.blackJack.Actor;
import com.company.cardGame.blackJack.Hand;

public class Player implements Actor {
    private final String name;
    private int balance = 1000;
    private int actionsCount;

    public Player(String name) {
        this.name = name;
    }

    public Player(String name, int startingBalance) {
        this.name = name;
        balance = startingBalance;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public int getBalance() {
        return balance;
    }

    @Override
    public int placeBet() {
        int bet = Console.getInt(
                1,
                balance,
                "Enter a bet between 1 and " + balance,
                "invalid bet"
        );
        balance -= bet;
        return bet;
    }

    private String getAvailableActions(Hand hand) {
        actionsCount = 2;
        StringBuilder output = new StringBuilder();
        output.append("0. Quit\n1. Hit\n2. Stand");
        if (hand.size() == 2 && balance >= hand.getBet()) {
            output.append("\n3. Double");
            actionsCount++;
            if (hand.canSplit()) {
                output.append("\n4. Split");
                actionsCount++;
            }
        }
        return output.toString();
    }

    @Override
    public int getAction(Hand hand, Hand Dealer) {
        //display hand and value
        System.out.println(hand.displayHand());
        System.out.println(hand.getValue());
        String prompt = getAvailableActions(hand);
        return Console.getInt(0,
                actionsCount,
                prompt,
                "invalid action");
    }

    public void addBalance(double amt) {
        balance += amt;
    }

}