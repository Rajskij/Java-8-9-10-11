module jmp.cloud.bank.impl {
    requires transitive jmp.bank.api;
    requires jmp.dto;
    exports bank.impl;
    provides bank.Bank with bank.impl.BankImpl;
}