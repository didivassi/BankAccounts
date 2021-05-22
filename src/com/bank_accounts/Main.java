package com.bank_accounts;

import com.bank_accounts.bank.AccountCard;
import com.bank_accounts.bank.AccountType;
import com.bank_accounts.bank.Bank;

public class Main {

    public static void main(String[] args) {
	// write your code here
        /*
        * Assumptions
        *
        * Person have accounts
        * A person can only have an account through a bank
        * A bank stores an account
        * An account has a card
        * An account has a balance
        *
        * */

        Bank CGD = new Bank("CGD");
        Person diogo=new Person();
        String CheckingAccountId=diogo.openAccount(CGD, AccountType.CHECKING,1000f);
        String SavingsAccountId = diogo.openAccount(CGD,AccountType.SAVINGS,3000f);

        AccountCard chekingCard=diogo.askCard(CGD,CheckingAccountId);

        AccountCard chekingCardCopy=diogo.askCard(CGD,CheckingAccountId);

        diogo.makeWithdraw(CGD,chekingCard,100);

        diogo.listAccounts();
        //String Checking=diogo.getAccount(0);
        //String Savings=diogo.getAccount(1);
        diogo.makeDeposit(CGD,CheckingAccountId,10);
    }
}
