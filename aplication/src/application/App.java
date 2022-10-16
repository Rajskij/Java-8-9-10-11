package application;

import bank.Bank;
import dto.*;
import exception.NoSuchSubscriptionException;
import service.Service;

import java.util.ServiceLoader;
import java.util.function.Predicate;

import static dto.BankCardType.CREDIT;
import static dto.BankCardType.DEBIT;

public class App {
    public static void main(String[] args) {
        System.out.println(" --------------- Lunching App --------------- ");

        // -------- Add a module with Service implementation. Use ServiceLoader.load() for module loading --------
        ServiceLoader<Bank> bankServiceLoader = ServiceLoader.load(Bank.class);
        var bank = bankServiceLoader.iterator().next();

        ServiceLoader<Service> services = ServiceLoader.load(Service.class);
        var service = services.iterator().next();
        // -------------------------------------------------------------------------------------------------------

        // ----------------------- Adding BankCards example -----------------------
        var creditCard = bank.createBankCard(new User(), CREDIT);
        var debitCard = bank.createBankCard(new User(), DEBIT);

        System.out.println(System.lineSeparator() + "Existing CREDIT cards list:" + service.getAllBankCards());
        service.subscribe(creditCard);
        System.out.println("Show bank list after adding CREDIT card:" + service.getAllBankCards());
        service.subscribe(debitCard);
        System.out.println("Show bank list after adding DEBIT card:" + service.getAllBankCards());
        // ------------------------------------------------------------------------

        // ----------------------- Search AllUsers example -----------------------
        System.out.println(System.lineSeparator() + "Show all users:");
        service.getAllUsers().forEach(System.out::println);
        // ------------------------------------------------------------------------

        // ----------------------- Search Subscription by BankCard example -----------------------
        System.out.println(System.lineSeparator()
                + "Show subscription with bank number '3': "
                + service.getSubscriptionByBankCardNumber("3") + System.lineSeparator());

        var bankCard = "121";

        try {
            System.out.println(System.lineSeparator()
                    + "Show subscription with bank number '" + bankCard + "':"
                    + service.getSubscriptionByBankCardNumber(bankCard).orElseThrow(
                            () -> new NoSuchSubscriptionException("There is no subscription for bank card: %s", bankCard)));
        } catch(NoSuchSubscriptionException e) {
            System.out.println(e.getMessage());
        }
        // ---------------------------------------------------------------------------------------

        // ----------------------- Search average users age example -----------------------
        System.out.println(System.lineSeparator()
                + "Show average users age: "
                + service.getAverageUsersAge());
        // ---------------------------------------------------------------------------------

        // ----------------------- Search if user over 18 years old -----------------------
        System.out.println(System.lineSeparator() + "Show if user is over 18 years old: ");
        service.getAllUsers().forEach(user -> System.out.printf("%s: %s\n", user.getName(), service.isPayableUser(user)));
        // --------------------------------------------------------------------------------

        // ----------------------- Search subscription by predicate -----------------------
        System.out.println(System.lineSeparator() + "Show all subscription with card number < '3': ");

        Predicate<Subscription> predicate = subscription -> {
            int cardNumber = Integer.parseInt(subscription.getBankcard());
            return cardNumber < 3;
        };

        service.getAllSubscriptionsByCondition(predicate).forEach(System.out::println);
        // --------------------------------------------------------------------------------
    }
}
