package com.bank_accounts.Entities;

import com.bank_accounts.bank.AccountCard;
import com.bank_accounts.bank.AccountType;
import com.bank_accounts.bank.Bank;
import com.bank_accounts.bank.Utils;

import java.util.ArrayList;
import java.util.List;

public abstract class  Entity {

    protected static int taxPayerCreated;
    final protected List<String> bankAccounts = new ArrayList<String>();
    final protected String taxPayerIdCard;
    protected float moneyOnWallet;
    protected String name;



    public Entity(String name, float moneyOnWallet ){
        taxPayerCreated++;
        taxPayerIdCard =String.valueOf(taxPayerCreated);
        this.moneyOnWallet = moneyOnWallet;
        this.name=name;
    }

    public String openAccount(Bank bank, AccountType accountType, float amount){

        if(bank==null){
            System.out.println("Non existing bank");
            return null;
        }
        if(!hasMoneyOnWalletLeft(amount)){
            return null;
        }
        String bankAccountId = bank.createAccount(this.taxPayerIdCard, accountType, amount);
        if(bankAccountId==null){
            System.out.println("Error! Opening account");
            return null;
        }
        bankAccounts.add(bankAccountId);
        moneyOnWallet -=amount;

        System.out.println("I've just opened a "+ accountType+ " account!");
        return bankAccountId;
    }

    public AccountCard askCard(Bank bank, String bankAccountId){
        if(bank==null){
            System.out.println("Non existing bank");
            return null;
        }
        return bank.sendCard(bankAccountId,this.taxPayerIdCard);
    }

    public void makeDeposit(Bank bank, String bankAccountId, float amount){
        if(bank==null){
            System.out.println("Non existing bank");
            return;
        }
        if(!hasMoneyOnWalletLeft(amount)){
            return;
        }
        bank.deposit(bankAccountId,amount);
    }

    public void makeWithdraw(Bank bank, AccountCard card, float amount){
        if(bank==null){
            System.out.println("Non existing bank");
            return ;
        }
        bank.withdraw(card,amount);
        System.out.println("You have just withdraw " + amount + "€ from your account " + card.getAccountId());
        moneyOnWallet +=amount;
    }

    public void makeTransferTo(Bank bankFrom, String bankAccountIdFrom, Bank bankTo, String bankAccountIdTo, float amount){
        if(bankFrom==null || bankTo==null){
            System.out.println("Non existing bank");
            return;
        }
        bankFrom.transferTo(bankFrom,bankAccountIdFrom,bankTo,bankAccountIdTo,amount);
    }

    private boolean hasMoneyOnWalletLeft(float amount){
        if(moneyOnWallet - amount < 0){
            System.out.println("You don't have money under your match to perform this operation");
            return false;
        }
        return true;
    }
    public void listAccounts(Bank bank){
        System.out.println("Here's a list and balance of your accounts at "  + bank.toString());

        for (String myAccount: bankAccounts) {
            if(Utils.parseBrandFromIdAccount(myAccount).equals(bank.toString())){
                float balance = bank.getBalance(myAccount,taxPayerIdCard);
                System.out.println(myAccount  + " has a balance of " + balance);
            }
        }

    }

    public String getAccounts(int index){
        if(index<0 || index> bankAccounts.size()){
            return null;
        }
        if(bankAccounts.get(index) !=null){
            return bankAccounts.get(index);
        }
        return null;
    }

    private String getTaxPayerIdCard() {
        return taxPayerIdCard;
    }

    public float getMoneyOnWallet(){
        return moneyOnWallet;
    }

    public String getName() {
        return name;
    }
}
