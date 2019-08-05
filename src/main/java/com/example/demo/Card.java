package com.example.demo;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
public class Card {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @NotNull
    @Size(min=2)
    @Column(name="card_First_Name")
    private String cardFirstName;

    @NotNull
    @Size(min=2)
    @Column(name="card_Last_Name")
    private String cardLastName;

    @NotNull
//    @Min(1)
//    @Max(17)
    @Column(name="cardNumber")
    private long cardNumber;

    @NotNull
//    @Min(1)
    @Max(999)
    private int securityCode;

    @NotNull
//    @Min(1)
    @Max(12)
    private int cardExpMonth;

    @NotNull
//    @Min(1800)
    @Max(3000)
    private int cardExpYear;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="user_id")
    private User user;

    public Card(){

    }

    public Card(String cardFirstName, String cardLastName, long cardNumber, int securityCode, int cardExpMonth, int cardExpYear, User user) {
        this.setCardFirstName(cardFirstName);
        this.setCardLastName(cardLastName);
        this.setCardNumber(cardNumber);
        this.setSecurityCode(securityCode);
        this.setCardExpMonth(cardExpMonth);
        this.setCardExpYear(cardExpYear);
        this.setUser(user);
    }


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getCardFirstName() {
        return cardFirstName;
    }

    public void setCardFirstName(String cardFirstName) {
        this.cardFirstName = cardFirstName;
    }

    public String getCardLastName() {
        return cardLastName;
    }

    public void setCardLastName(String cardLastName) {
        this.cardLastName = cardLastName;
    }

    public long getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(long cardNumber) {
        this.cardNumber = cardNumber;
    }

    public int getSecurityCode() {
        return securityCode;
    }

    public void setSecurityCode(int securityCode) {
        this.securityCode = securityCode;
    }

    public int getCardExpMonth() {
        return cardExpMonth;
    }

    public void setCardExpMonth(int cardExpMonth) {
        this.cardExpMonth = cardExpMonth;
    }

    public int getCardExpYear() {
        return cardExpYear;
    }

    public void setCardExpYear(int cardExpYear) {
        this.cardExpYear = cardExpYear;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
