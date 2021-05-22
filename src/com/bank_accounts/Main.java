package com.bank_accounts;

import com.bank_accounts.Entities.Person;
import com.bank_accounts.bank.AccountCard;
import com.bank_accounts.bank.AccountType;
import com.bank_accounts.bank.Bank;
import com.bank_accounts.bank.BankRegulator;

public class Main {

    public static void main(String[] args) {
	// write your code here

        //you can create banks
        Bank CGD = BankRegulator.createBank("CGD");
        Bank BCP = BankRegulator.createBank("BCP");
        //but they're validated
        Bank BCP2 = BankRegulator.createBank("BCP");
        Bank BCP3 = BankRegulator.createBank("BCP-");

        //create persons
        Person diogo=new Person(4000f);
        Person pedro=new Person(2000f);

        //just checking  - to uncomment u need to make method public
        //System.out.println(diogo.getPersonIdCard());
        //System.out.println(pedro.getPersonIdCard());

        //open accounts
        String CheckingAccountId=diogo.openAccount(CGD, AccountType.CHECKING,1000f);
        String SavingsAccountId = diogo.openAccount(CGD,AccountType.SAVINGS,3000f);
        //you cannot open accounts if you don't have moneyUnderMatch
        String LoanAccountId = diogo.openAccount(CGD,AccountType.LOAN,3000f);

        //you can ask for cards
        AccountCard checkingCard=diogo.askCard(CGD,CheckingAccountId);
        //pay a fee if you lost it
        AccountCard checkingCardCopy=diogo.askCard(CGD,CheckingAccountId);
        //only allowed accounts can have cards
        AccountCard SavingCard=diogo.askCard(CGD,SavingsAccountId);
        //validate if you're asking another bank for the card
        AccountCard checkingCardBCP=diogo.askCard(BCP,CheckingAccountId);
        // another person cannot ask for your cards
        AccountCard checkingWrongPerson=pedro.askCard(CGD,CheckingAccountId);

        //its possible to lend your card
        diogo.makeWithdraw(CGD,checkingCard,100);
        pedro.makeWithdraw(CGD,checkingCard,200);



        diogo.listAccounts();
        //String Checking=diogo.getAccount(0);
        //String Savings=diogo.getAccount(1);
        diogo.makeDeposit(CGD,CheckingAccountId,10);
    }
}
