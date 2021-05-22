package com.bank_accounts.bank;

public enum AccountType {

    CHECKING(0f),
    SAVINGS(10f);
    protected float minAmount;

    private AccountType(float minAmount){
        this.minAmount=minAmount;
    }
}
