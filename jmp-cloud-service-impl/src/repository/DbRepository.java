package repository;

import dto.BankCard;
import dto.Subscription;
import dto.User;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

public class DbRepository {

    private static DbRepository instance;

    private final List<BankCard> bankCards;

    private final List<Subscription> subscriptions;

    private final List<User> users;

    private DbRepository() {
        this.bankCards = new ArrayList<>();
        this.subscriptions = getSubscriptions();
        this.users = getUsers();
    }

    public static synchronized DbRepository getInstance() {
        if (instance == null) {
            instance = new DbRepository();
        }

        return instance;
    }
    public void save(BankCard bankCard) {
        bankCards.add(bankCard);
    }

    public Optional<Subscription> findByByBankCardNumber(String cardNumber) {
        return subscriptions.stream()
                            .filter(subscription -> subscription.getBankcard().equals(cardNumber))
                            .findFirst();
    }

    public List<User> findAllUsers() {
        return users;
    }

    public List<BankCard> findAllBankCards() {
        return bankCards;
    }

    public List<Subscription> findAllSubscriptions() {
        return subscriptions;
    }
    private List<Subscription> getSubscriptions() {
        var subs = new ArrayList<Subscription>();

        for (int i = 0; i < 10; i++) {
            subs.add(new Subscription(String.valueOf(i), LocalDate.now()));
        }
        return subs.stream().collect(Collectors.toUnmodifiableList());
    }


    private List<User> getUsers() {
        var users = new ArrayList<User>();
        users.add(new User("Petro", "Surname1", LocalDate.of(2005, 12, 1)));
        users.add(new User("Ivan", "Surname2", LocalDate.of(2000, 10, 10)));
        users.add(new User("Stepan", "Surname3", LocalDate.of(1990, 3, 1)));
        users.add(new User("Valeria", "Surname4", LocalDate.of(2010, 12, 1)));
        users.add(new User("Petro", "Surname5", LocalDate.of(1950, 12, 1)));

        return users.stream().collect(Collectors.toUnmodifiableList());
    }
}

