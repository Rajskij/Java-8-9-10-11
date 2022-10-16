module aplication {
    uses service.Service;
    uses bank.Bank;
    requires jmp.cloud.bank.impl;
    requires jmp.cloud.service.impl;
    requires jmp.dto;
}