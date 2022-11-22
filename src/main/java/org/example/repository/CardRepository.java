package org.example.repository;

import org.example.entity.Card;

import java.io.IOException;

public interface CardRepository {

    public void updateConditionCards() throws IOException;

    public void readConditionCards() throws IOException;

    public void checkBalance(Card myCard);

    public void takeOffCash(Card myCard,Long cash);

    public void replenish(Card myCard,Long deposit);

    public Card getCardByNumber(String numberCard);

    public void addCard(Card card);
}
