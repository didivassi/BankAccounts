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
        Person diogo=new Person("Diogo", 4000f);
        Person pedro=new Person("Pedro",2000f);

        //just checking  - to uncomment u need to make method public
        //System.out.println(diogo.getPersonIdCard());
        //System.out.println(pedro.getPersonIdCard());

        //open accounts
        String diogoCheckingAccountId=diogo.openAccount(CGD, AccountType.CHECKING,1000f);
        String diogoSavingsAccountId = diogo.openAccount(CGD,AccountType.SAVINGS,3000f);
        //you cannot open accounts if you don't have moneyOnWallet
        String diogoLoanAccountId = diogo.openAccount(CGD,AccountType.LOAN,3000f);

        //you can ask for cards
        AccountCard diogoCheckingCard=diogo.askCard(CGD,diogoCheckingAccountId);
        //pay a fee if you lost it
        AccountCard diogoCheckingCardCopy=diogo.askCard(CGD,diogoCheckingAccountId);
        //only allowed accounts can have cards
        AccountCard diogoSavingCard=diogo.askCard(CGD,diogoSavingsAccountId);
        //validate if you're asking another bank for the card
        AccountCard checkingCardBCP=diogo.askCard(BCP,diogoCheckingAccountId);
        // another person cannot ask for your cards
        AccountCard checkingWrongPerson=pedro.askCard(CGD,diogoCheckingAccountId);

        //its possible to lend your card
        diogo.makeWithdraw(CGD,diogoCheckingCard,100);
        pedro.makeWithdraw(CGD,diogoCheckingCard,200);


        //pay a fee only on loan account due to negative balance
        String pedroLoanAccountId = pedro.openAccount(BCP,AccountType.LOAN,100);
        AccountCard pedroLoanCard=pedro.askCard(BCP,pedroLoanAccountId);
        pedro.makeWithdraw(BCP,pedroLoanCard,150);

        //make transfers
        diogo.listAccounts(CGD);
        pedro.listAccounts(BCP);
        diogo.makeTransferTo(CGD, diogoSavingsAccountId, BCP, pedroLoanAccountId,200);

        diogo.listAccounts(CGD);
        pedro.listAccounts(BCP);


        pedro.getMoneyOnWallet();
        diogo.getMoneyOnWallet();



        diogo.listAccounts(CGD);
        //String Checking=diogo.getAccount(0);
        //String Savings=diogo.getAccount(1);
        diogo.makeDeposit(CGD,diogoCheckingAccountId,10);
    }
}
