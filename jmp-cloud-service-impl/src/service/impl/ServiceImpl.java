package service.impl;

import dto.BankCard;
import dto.Subscription;
import dto.User;
import repository.DbRepository;
import service.Service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class ServiceImpl implements Service {

    private final DbRepository dbRepository;

    public ServiceImpl() {
        this.dbRepository = DbRepository.getInstance();
    }

    @Override
    public void subscribe(BankCard bankCard) {
        dbRepository.save(bankCard);
    }

    @Override
    public Optional<Subscription> getSubscriptionByBankCardNumber(String cardNumber) {
        return dbRepository.findByByBankCardNumber(cardNumber);
    }

    @Override
    public List<User> getAllUsers() {
        return dbRepository.findAllUsers();
    }

    @Override
    public double getAverageUsersAge() {
        return getAllUsers().stream()
                     .mapToLong(user -> ChronoUnit.YEARS.between(user.getBirthday(), LocalDate.now()))
                     .summaryStatistics()
                     .getAverage();
    }

    @Override
    public boolean isPayableUser(User user) {
        return ChronoUnit.YEARS.between(user.getBirthday(), LocalDate.now()) > 18L;
    }

    @Override
    public List<Subscription> getAllSubscriptionsByCondition(Predicate<Subscription> predicate) {
        return dbRepository.findAllSubscriptions().stream()
                                                  .filter(predicate)
                                                  .collect(Collectors.toUnmodifiableList());
    }

    @Override
    public List<BankCard> getAllBankCards() {
        return dbRepository.findAllBankCards();
    }
}
