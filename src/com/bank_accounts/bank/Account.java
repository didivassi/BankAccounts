package com.bank_accounts.bank;

import com.bank_accounts.Person;

public abstract class Account {

    private float balance;
    private Person person;
    private String accountId;
    private AccountType type;
    private float minBalance;


    protected Account(float openBalance, AccountType type){
        balance=openBalance;
        this.type=type;
        person=null;

    }

    protected abstract boolean canHaveCard();

    protected float getBalance() {
        return balance;
    }

    protected void setBalance(float amount){
        balance+=amount;
    }
    protected void setPerson(Person person){
        this.person=person;
    }
    protected Person getPerson(){
        return person;
    }

    protected void setAccountId(String accountId){
        this.accountId=accountId;
    }
    protected String getAccountId(){
        return accountId;
    }
}
