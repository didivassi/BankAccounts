package com.bank_accounts.bank;

public class AccountCard {
    private String accountId;
    public AccountCard(String accountId){
        this.accountId=accountId;
    }
    protected String getAccountId(){
        return accountId;
    }
}
