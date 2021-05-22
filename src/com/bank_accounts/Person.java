package com.bank_accounts;

import com.bank_accounts.bank.AccountCard;
import com.bank_accounts.bank.AccountType;
import com.bank_accounts.bank.Bank;
import com.bank_accounts.bank.Account;
import java.util.*;

public class Person {

    private List<String> myAccounts = new ArrayList<String>();

    private int personIdCard;
    public Person(){
        personIdCard=++personIdCard;
    }

    public String openAccount(Bank bank, AccountType accountType, float amount){

        if(bank==null){
            System.out.println("Non existing bank");
            return null;
        }
        String bankAccountId = bank.createAccount(String.valueOf(this.personIdCard), accountType, amount);
        if(bankAccountId==null){
            System.out.println("Error! Opening account");
            return null;
        }
        myAccounts.add(bankAccountId);
        System.out.println("I've just opened a "+ accountType+ " account!");
        return bankAccountId;
    }

    public AccountCard askCard(Bank bank, String bankAccountId){
        if(bank==null){
            System.out.println("Non existing bank");
            return null;
        }
       return bank.sendCard(bankAccountId,String.valueOf(this.personIdCard));
    }

    public void makeDeposit(Bank bank, String bankAccountId, float amount){
        if(bank==null){
            System.out.println("Non existing bank");
            return;
        }
    }

    public void makeWithdraw(Bank bank, AccountCard card, float amount){
        if(bank==null){
            System.out.println("Non existing bank");
            return ;
        }
        bank.withdraw(card,amount);
        System.out.println("You have just withdraw " + amount + "€ from your account " + card.getAccountId());
    }

    public void makeTransferTo(Bank bank, String bankAccountIdFrom, String bankAccountIdTo, float amount){
        if(bank==null){
            System.out.println("Non existing bank");
            return;
        }
    }

    public void listAccounts(){
        System.out.println("Here's a list and balance of your accounts");
        for (String myAccount:myAccounts) {


            System.out.println(myAccount  + " has a balance");
        }

    }


    public String getAccount(int index){
        if(index<0 || index>myAccounts.size()){
            return null;
        }
        if(myAccounts.get(index) !=null){
            return myAccounts.get(index);
        }
        return null;
    }

    public int getPersonIdCard() {
        return personIdCard;
    }
}
