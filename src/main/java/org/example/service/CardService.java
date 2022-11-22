package org.example.service;

import org.example.entity.Card;

public interface CardService {
    public void addCard(Card card);

    public Card authorization (String numberCard);
}
