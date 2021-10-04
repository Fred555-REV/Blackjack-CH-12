package com.company.cardGame.actor;

import com.company.cardGame.blackJack.Actor;
import com.company.cardGame.blackJack.Hand;

public class Fred implements Actor {
    private String name = "Fred";
    private int balance = 1000;

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
        int bet;
        bet = (int) Math.floor(Math.random() * balance / 2) + 1;
        balance -= bet;
        return bet;
    }

    @Override
    public int getAction(Hand hand, Hand dealer) {
        int myValue = hand.getValue();
        int dealerValue = dealer.getValue();
        boolean dealer17 = dealerValue >= 17;

        if (myValue >= 17 && dealerValue > myValue) {
            // no removal of cards if cardcount is 2.

            hand.addCard(dealer.removeCard(0));
            hand.addCard(dealer.removeCard(0));
            dealer.addCard(hand.removeCard(0));
            dealer.addCard(hand.removeCard(0));
            while (balance > hand.getBet() + 100){
                hand.doubleBet();
            }
            return STAND;
        }

        if (myValue >= dealerValue && dealer17) {
            //Instant Win or Push
            //Dealer 17-21
            //Me 17-21
            while (balance > hand.getBet() + 100){
                hand.doubleBet();
            }
                return STAND;
        }

        if (hand.canSplit()) {
            return SPLIT;
        }


        if (myValue >= 17 && myValue <= 21 && !dealer17) {
            //Dealer 4-16 vs
            // Me 17-21
            return STAND;
        }

        if (myValue < dealerValue && dealer17) {
            //Dealer 17-21
            //Me 4-16
            return HIT;
        }
        if (dealerValue >= 7 && dealerValue <= 11) {
            //Dealer 7-11
            return HIT;
        }
        if (myValue >= 12) {
            return STAND;
        }
        if (dealerValue > 12) {
            //Dealer 12-16
            return DOUBLE;
        }
        //Dealer 4-6
        return HIT;
    }

    @Override
    public void addBalance(double amt) {
        balance += amt;
    }
}
