package com.bank_accounts.bank;

import java.util.*;

public class Bank {



    final private List<Account> customerAccounts = new ArrayList<Account>();
    private String brand;

    protected Bank(String brand){
        this.brand=brand;
    }


    public String createAccount(String personId, AccountType accountType, float amount){
        String idAccount;
        Account accountToOpen;

        if(Utils.isNegativeAmount(amount)){
            return null;
        }
        if(amount < accountType.minAmountToOpen){
            System.out.println("You need to open this account with at least " + accountType.minAmountToOpen);
            return null;
        }
        switch (accountType){
            case CHECKING:
                accountToOpen= new CheckingAccount(amount);
                break;
            case SAVINGS:
                accountToOpen= new SavingsAccount(amount);
                break;
            case LOAN:
                accountToOpen= new LoanAccount(amount);
                break;
            default:
                System.out.println("You need to choose between Checking or Savings when open an account");
                return null;
        }

        accountToOpen.setPerson(personId);
        customerAccounts.add(accountToOpen);
        idAccount=Utils.createAccountId(customerAccounts.size()-1,brand);
        accountToOpen.setAccountId(idAccount);

        return idAccount;
    }

    public AccountCard sendCard(String accountId, String personId){
        Account account=getAccountFromId(accountId);
        if(account==null){
            return null;
        }
        if(!accountBelongsToPerson(account, personId)){
            return null;
        }
        if (!account.canHaveCard()){
            System.out.println("This account can't have a card");
            return null;
        }
        if(!((CardAccount) account).isCardAttributed()){
            System.out.println("Here is your new card. This one is free");
            return ((CardAccount) account).attributeCard();
        }

        if(!isBalanceAllowed(account, 1f)){
            System.out.println("You already have a card but you don't have engouth balance to pay a fee");
            return null;
        }

        System.out.println("You already have a card so you'll pay a fee");
        account.setBalance(-1f);
        return ((CardAccount) account).getCard();

    }

    public void withdraw(AccountCard card ,float amount){

        if(card==null){
            System.out.println("You need a Card to withdraw");
            return;
        }
        Account account=getAccountFromId(card.getAccountId());
        if(account==null){
            return;
        }
        if(!isBalanceAllowed(account, amount)){
            return;
        }

        account.setBalance(-amount);

    }

    public void deposit(String accountId, float amount){
        System.out.println("receiving  deposit "  + accountId);
        if(Utils.isNegativeAmount(amount)){
            return;
        }
        Account account=getAccountFromId(accountId);
        if(account==null){
            return;
        }
        account.setBalance(amount);
    }

    public void transferTo(Bank bankFrom, String accountIdFrom , Bank bankTo, String accountIdTo , float amount){
        if(Utils.isNegativeAmount(amount)){
            return;
        }



        Account accountFrom=getAccountFromId(accountIdFrom);
        if(accountFrom==null){
            return;
        }

        if(!isBalanceAllowed(accountFrom, amount)){
            return;
        }
        Account accountTo=bankTo.getAccountFromId(accountIdTo);
        if(accountTo==null){
            System.out.println("There is no account at destination bank");
            return;
        }
        accountFrom.setBalance(-amount);
        bankTo.deposit(accountIdTo,amount);

    }


    private Account getAccountFromId(String idAccount){
        if(!accountExists(idAccount)){
            return null;
        }
        return customerAccounts.get(Utils.parseIdAccount(idAccount));
    }

    private boolean accountExists(String idAccount){
        String tempBrand= Utils.parseBrandFromIdAccount(idAccount);
        int tempId= Utils.parseIdAccount(idAccount);
        if(!tempBrand.equals(brand)){
            System.out.println("This account is not from this Bank");
            return false;
        }
        if(tempId>customerAccounts.size()-1){
            System.out.println("This account does not exist!");
            return false;
        }
        return true;
    }

    private boolean accountBelongsToPerson(Account account, String personId){
        if(!account.getPerson().equals(personId)){
            System.out.println("This account doesn't belongs to you");
        }
        return account.getPerson().equals(personId);
    }


    private boolean isBalanceAllowed(Account account, float amount){
        if(account.getBalance() - amount < account.getMinBalance()){
            System.out.println("You can't have a balance lower than " + account.getMinBalance() );
            return false;
        }
        return true;
    }


    public float getBalance(String idAccount, String personId){
        float balance=0f;
        if(!accountExists(idAccount)){
            return balance;
        }
        Account account=getAccountFromId(idAccount);
        if(!accountBelongsToPerson(account,personId)){
            return balance;
        }
        return  getAccountFromId(idAccount).getBalance();
    }


    @Override
    public String toString(){
        return brand;
    }

}
