package org.example.repository.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.entity.Card;
import org.example.entity.StatusCard;
import org.example.repository.CardRepository;
import org.example.repository.Scan;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CardRepositoryImpl implements CardRepository {

    static private List<Card> cards = new ArrayList<>();
    static private final String FILE_PATH = "file1.txt";
    static private int limit = 1000000;

    @Override
    public void updateConditionCards() {
        try (FileWriter fileWriter = new FileWriter(FILE_PATH)) {
            String stringRecord;
            for (Card card : cards) {
                stringRecord = card.getNumberCard() + " ";
                stringRecord += card.getBalance().toString() + " ";
                stringRecord += card.getPinCod().toString() + " ";
                stringRecord += card.getStatus().toString() + " ";
                fileWriter.write(stringRecord);
            }
        } catch (IOException e) {
            System.out.println("ERROR updating");
            throw new RuntimeException(e);
        }

    }

    @Override
    public void readConditionCards() {
        try (FileReader fileReader = new FileReader(FILE_PATH)) {
            Scanner scan = new Scanner(fileReader);
            while (scan.hasNext()) {
                Card card = new Card();
                card.setNumberCard(String.valueOf(scan.next()));
                card.setBalance(Long.valueOf(scan.next()));
                card.setPinCod(Integer.valueOf(scan.next()));
                card.setStatus(StatusCard.valueOf(scan.next()));
                cards.add(card);
            }
        } catch (IOException e) {
            System.out.println("ERROR reading");
            throw new RuntimeException(e);
        }
    }

    @Override
    public void checkBalance(Card myCard) {
        System.out.println(myCard.getBalance());
    }

    @Override
    public void takeOffCash(Card myCard, Long cash) {
        cash = myCard.getBalance() - cash;
        if (cash >= 0L) {
            myCard.setBalance(cash);
        } else if (cash < 0L) {
            System.out.println("not enough money on the card");
        } else {
            System.out.println("wrong input");
        }
    }

    @Override
    public void replenish(Card myCard, Long deposit) {
        if (deposit > 0 && deposit < limit) {
            myCard.setBalance(myCard.getBalance() + deposit);
        } else if (deposit > 0 && deposit <= limit) {
            System.out.println("you cannot deposit more than 1 million");
        } else {
            System.out.println("wrong input");
        }
    }

    @Override
    public Card getCardByNumber(String numberCard) {
        for (Card myCard : cards) {
            if (myCard.getNumberCard().equals(numberCard)) {
                return myCard;
            }
        }
        return null;
    }

    @Override
    public void addCard(Card card) {
        if (!cards.contains(card)) {
            cards.add(card);
        } else {
            System.out.println("such a map already exists");
        }
    }
}