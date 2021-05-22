package com.bank_accounts.bank;

public enum AccountType {

    CHECKING(0f,0f),
    SAVINGS(10f,0f),
    LOAN(100f,-200f);
    protected float minAmountToOpen;
    protected float minBalance;

    AccountType(float minAmountToOpen, float minBalance){

        this.minAmountToOpen = minAmountToOpen;
        this.minBalance=minBalance;
    }
}
