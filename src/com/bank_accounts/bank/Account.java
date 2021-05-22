package com.bank_accounts.bank;

public abstract class Account {

    private float balance;
    private String personId;
    private String accountId;
    private AccountType type;
    private float minBalance;


    protected Account(float openBalance, AccountType type){
        balance=openBalance;
        this.type=type;
        this.minBalance=type.minBalance;
        personId=null;

    }

    protected abstract boolean canHaveCard();

    protected float getBalance() {
        return balance;
    }

    protected float getMinBalance() {
        return minBalance;
    }

    protected void setBalance(float amount){
        balance+=amount;
    }
    protected void setPerson(String personId){
        this.personId=personId;
    }
    protected String getPerson(){
        return personId;
    }

    protected void setAccountId(String accountId){
        this.accountId=accountId;
    }
    protected String getAccountId(){
        return accountId;
    }
}
