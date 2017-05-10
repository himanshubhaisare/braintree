package resource;

import java.math.BigDecimal;

public class Card {

    private String number;

    private User user;

    private BigDecimal balance;

    private BigDecimal creditLimit;

    public Card(String number, User user, BigDecimal creditLimit) {
        this.number = number;
        this.user = user;
        this.creditLimit = creditLimit;
        this.balance = BigDecimal.ZERO;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public BigDecimal getCreditLimit() {
        return creditLimit;
    }

    public void setCreditLimit(BigDecimal creditLimit) {
        this.creditLimit = creditLimit;
    }
}
