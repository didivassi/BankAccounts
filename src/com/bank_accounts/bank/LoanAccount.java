package com.bank_accounts.bank;

public class LoanAccount extends CardAccount{

    public LoanAccount(float amount){
        super(amount, AccountType.LOAN);
    }

    @Override
    protected void setBalance(float amount) {
        super.setBalance(amount);
        //pay a fee
        if(super.getBalance()<0 && amount < 0){
            super.setBalance(-1);
            System.out.println("u paid a fee for negative balance");
        }

    }
}
