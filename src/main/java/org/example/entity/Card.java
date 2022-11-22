package org.example.entity;

public class Card {

    private String numberCard;
    private Integer pinCod;
    private Long balance;
    private StatusCard status;

    public Integer getPinCod() {
        return pinCod;
    }

    public void setPinCod(Integer pinCod) {
        this.pinCod = pinCod;
    }

    public StatusCard getStatus() {
        return status;
    }

    public void setStatus(StatusCard status) {
        this.status = status;
    }

    public String getNumberCard() {
        return numberCard;
    }

    public void setNumberCard(String numberCard) {
        this.numberCard = numberCard;
    }

    public Long getBalance() {
        return balance;
    }

    public void setBalance(Long balance) {
        this.balance = balance;
    }

    public void Card(String numberCard, Long balance, StatusCard status, Integer pinCod) {
        this.numberCard = numberCard;
        this.balance = balance;
        this.pinCod = pinCod;
        this.status = status;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Card that = (Card) o;
        return numberCard != null && numberCard.equals(that.numberCard);
    }
}
