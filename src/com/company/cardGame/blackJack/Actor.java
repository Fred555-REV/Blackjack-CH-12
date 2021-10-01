package com.company.cardGame.blackJack;
public interface Actor {
    int HIT = 1;
    int STAND = 2;
    int DOUBLE = 3;
    int SPLIT = 4;
    int QUIT = 0;

    String getName();
    int getBalance();
    int placeBet();
    int getAction(Hand hand, Hand dealer);
    void addBalance(double amt);
    //byte getAcion(Hand hand, List<Cards> cards) -> card counter version
}