package bank.impl;

import bank.Bank;
import dto.*;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.function.BiFunction;

import static dto.BankCardType.CREDIT;
import static dto.BankCardType.DEBIT;

public class BankImpl implements Bank {

    // Or use Supplier if we need to use empty constructor
    private final Map<BankCardType, BiFunction<String, User, BankCard>> typeBiFunctionMap;

    public BankImpl() {
        typeBiFunctionMap = new HashMap<>();
        typeBiFunctionMap.put(CREDIT, CreditBankCard::new);
        typeBiFunctionMap.put(DEBIT, DebitBankCard::new);
    }

    @Override
    public BankCard createBankCard(User user, BankCardType bankCardType) {
        return typeBiFunctionMap.get(bankCardType).apply(UUID.randomUUID().toString(), user);
    }
}