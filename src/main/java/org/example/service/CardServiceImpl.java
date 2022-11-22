package org.example.service;

import org.example.entity.Card;
import org.example.repository.CardRepository;
import org.example.repository.Scan;
import org.example.repository.impl.CardRepositoryImpl;

import java.util.Scanner;
public class CardServiceImpl {
    CardRepository cardRepository= new CardRepositoryImpl();

    public void addCard(Card card) {
        cardRepository.addCard(card);
    }
    public Card authorization (String numberCard) {
        return cardRepository.getCardByNumber(numberCard) ;
    }
}
