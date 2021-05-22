package com.bank_accounts.bank;

import com.bank_accounts.Person;

public abstract class Account {

    private float balance;
    private Person person;
    private String id;
    private AccountType type;
    protected boolean hasCard;

    public Account(float openBalance, AccountType type){
        balance=openBalance;
        this.type=type;
        person=null;
        hasCard=false;
    }

    protected void giveCard(){
        System.out.println("This type of account doesn't have a card");
    }

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

    protected void setId(String id){
        this.id=id;
    }
}
