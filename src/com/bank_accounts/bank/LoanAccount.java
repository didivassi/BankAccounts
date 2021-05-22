package com.bank_accounts.bank;

public class LoanAccount extends CardAccount{

    public LoanAccount(float amount){
        super(amount, AccountType.LOAN);
    }

}
