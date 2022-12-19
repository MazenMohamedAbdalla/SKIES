package com.example.skies;

public class bankAccountInfo {
    private String cardNumber;
    private String cardHolder;
    private String expDate;
    private String cvv;
    private int balance;

    public void setCardNumber(String cardNumber) {this.cardNumber = cardNumber;}
    public void setCardHolder(String cardHolder) {this.cardHolder = cardHolder;}
    public void setExpDate(String expDate) {this.expDate = expDate;}
    public void setCvv(String cvv) {this.cvv = cvv;}
    public void setBalance(int balance) {this.balance = balance;}

    public String getCardNumber() {return cardNumber;}
    public String getCardHolder() {return cardHolder;}
    public String getExpDate() {return expDate;}
    public String getCvv() {return cvv;}
    public int getBalance() {return balance;}
}
