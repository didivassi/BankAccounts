package com.bank_accounts;

import com.bank_accounts.bank.AccountCard;
import com.bank_accounts.bank.AccountType;
import com.bank_accounts.bank.Bank;
import com.bank_accounts.bank.BankRegulator;

public class Main {

    public static void main(String[] args) {
	// write your code here


        Bank CGD = BankRegulator.createBank("CGD");
        Bank BCP = BankRegulator.createBank("BCP");
        Bank BCP2 = BankRegulator.createBank("BCP");
        Bank BCP3 = BankRegulator.createBank("BCP-");
        Person diogo=new Person();
        String CheckingAccountId=diogo.openAccount(CGD, AccountType.CHECKING,1000f);
        String SavingsAccountId = diogo.openAccount(CGD,AccountType.SAVINGS,3000f);

        AccountCard checkingCard=diogo.askCard(CGD,CheckingAccountId);
        AccountCard checkingCardCopy=diogo.askCard(CGD,CheckingAccountId);

        AccountCard SavingCard=diogo.askCard(CGD,SavingsAccountId);

        AccountCard checkingCardBCP=diogo.askCard(BCP,CheckingAccountId);

        diogo.makeWithdraw(CGD,checkingCard,100);

        diogo.listAccounts();
        //String Checking=diogo.getAccount(0);
        //String Savings=diogo.getAccount(1);
        diogo.makeDeposit(CGD,CheckingAccountId,10);
    }
}
