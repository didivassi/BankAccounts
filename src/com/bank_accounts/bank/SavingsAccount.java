package com.bank_accounts.bank;

public class SavingsAccount extends Account {

    public SavingsAccount(float amount){
        super(amount, AccountType.SAVINGS);
    }

    @Override
    protected boolean canHaveCard(){
        return false;
    }


}
