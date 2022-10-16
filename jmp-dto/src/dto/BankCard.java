package dto;

import java.util.Objects;

public abstract class BankCard {

    private String number;

    private User user;

    public BankCard(String number, User user) {
        this.number = number;
        this.user = user;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BankCard bankCard = (BankCard) o;
        return Objects.equals(number, bankCard.number) && Objects.equals(user, bankCard.user);
    }

    @Override
    public int hashCode() {
        return Objects.hash(number, user);
    }

    @Override
    public String toString() {
        return "BankCard type: " + this.getClass().getSimpleName();
    }
}
