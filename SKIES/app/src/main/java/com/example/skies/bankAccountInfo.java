package com.example.skies;

public class bankAccountInfo {
    private static String cardNumber;
    private String cardHolder;
    private String expDate;
    private String cvv;
    private float balance;

    public void setCardNumber(String cardNumber) {bankAccountInfo.cardNumber = cardNumber;}
    public void setCardHolder(String cardHolder) {this.cardHolder = cardHolder;}
    public void setExpDate(String expDate) {this.expDate = expDate;}
    public void setCvv(String cvv) {this.cvv = cvv;}
    public void setBalance(float balance) {this.balance = balance;}

    public String getCardNumber() {return cardNumber;}
    public String getCardHolder() {return cardHolder;}
    public String getExpDate() {return expDate;}
    public String getCvv() {return cvv;}public float getBalance() {return balance;}
}
