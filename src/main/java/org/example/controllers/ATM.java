package org.example.controllers;

import org.example.entity.Card;
import org.example.entity.StatusCard;
import org.example.repository.Scan;
import org.example.repository.impl.CardRepositoryImpl;
import org.example.service.CardServiceImpl;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ATM {

    static private final int AMOUNT_TRY = 3;

    public static void mashin() {
        CardRepositoryImpl cardRepositoryImpl = new CardRepositoryImpl();
        CardServiceImpl cardServiceIml = new CardServiceImpl();

        System.out.println("select option: 1-Add map, Any number-Work with existing card");
        int choice = Scan.getSomeInt();
        if (choice == 1) {
            cardServiceIml.addCard(fillCard());
        } else {
            cardRepositoryImpl.readConditionCards();
        }
        String numberMyCard;
        Boolean check;
        do {
            System.out.println("insert card : ");
            numberMyCard = Scan.getSomeString();
            check = checkNumber(numberMyCard);
            if (!check) {
                System.out.println("Сard entered incorrectly\n");
            }
        } while (!check);

        Card myCard = cardServiceIml.authorization(numberMyCard);
        if (!checkPinCod(myCard)) {
            cardRepositoryImpl.updateConditionCards();
            System.exit(0);
        }

        boolean notExit = true;
        do {
            System.out.println("Chose option: 1-Check balance,2-Take off cash,3-Replenish, 4-Exit\n");
            int i = Scan.getSomeInt();
            switch (i) {
                case 1:
                    cardRepositoryImpl.checkBalance(myCard);
                    break;
                case 2:
                    System.out.println("Enter the amount you want to cash out:");
                    Long cash = Scan.getSomeInt().longValue();
                    cardRepositoryImpl.takeOffCash(myCard, cash);
                    break;
                case 3:
                    System.out.println("How much money do you want to deposit:");
                    Long deposit = Scan.getSomeInt().longValue();
                    cardRepositoryImpl.replenish(myCard, deposit);
                    break;
                case 4:
                    notExit = false;
                    break;
                default:
                    System.out.println("wrong input");
            }
        } while (notExit);

        cardRepositoryImpl.updateConditionCards();
    }

    static private boolean checkNumber(String s) {
        Pattern p = Pattern.compile("([0-9]{4})+([-]\\d{4})+([-]\\d{4})+([-]\\d{4})");
        Matcher m = p.matcher(s);
        return m.matches();
    }
    static private boolean checkPinCod(Card card) {
        for (int q = 0; q < AMOUNT_TRY; q++) {
            System.out.println("insert pinCod : ");
            Integer inputCodMyCard = Scan.getSomeInt();
            if (card.getPinCod().equals(inputCodMyCard)) {
                return true;
            }
        }
        System.out.println("Card blocked");
        card.setStatus(StatusCard.BLOCKER);
        return false;
    }
    static private Card fillCard() {
        Card card = new Card();
        System.out.println("enter number card");
        card.setNumberCard(Scan.getSomeString());
        System.out.println("enter balance card");
        card.setBalance(Scan.getSomeInt().longValue());
        card.setStatus(StatusCard.ACTIVE);
        System.out.println("enter new PinCod card");
        card.setPinCod(Scan.getSomeInt());
        return card;
    }
}
