package com.bank_accounts.bank;

public class AccountCard {
    private String accountId;
    protected AccountCard(String accountId){
        this.accountId=accountId;
    }
    public String getAccountId(){
        return accountId;
    }
}
