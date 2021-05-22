package com.bank_accounts.bank;

import com.bank_accounts.Person;
import java.util.*;

public class Bank {

    final private static List<Bank> BANKS= new ArrayList<Bank>();

    final private List<Account> customerAccounts = new ArrayList<Account>();
    private String brand;

    public Bank(String brand){
        this.brand=brand;
    }

    public void createBank(String brand){
        //check if brand exist
    }

    public String createAccount(Person person, AccountType accountType, float amount){
        String idAccount;
        Account accountToOpen;

        if(Utils.isNegativeAmount(amount)){
            return null;
        }
        if(amount < accountType.minAmount){
            System.out.println("You need to open this account with at least " + accountType.minAmount);
            return null;
        }
        switch (accountType){
            case CHECKING:
                accountToOpen= new CheckingAccount(amount);
                break;
            case SAVINGS:
                accountToOpen= new SavingsAccount(amount);
                break;
            default:
                System.out.println("You need to choose between Checking or Savings when open an account");
                return null;
        }

        accountToOpen.setPerson(person);
        customerAccounts.add(accountToOpen);
        idAccount=Utils.createAccountId(customerAccounts.size()-1,brand);
        accountToOpen.setAccountId(idAccount);

        return idAccount;
    }

    public AccountCard sendCard(String accountId, Person person){
        Account account=getAccountFromId(accountId);
        if(account==null){
            return null;
        }
        if(!accountBelongsToPerson(account, person)){
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

        if(willBalanceBecomeNegative(account, 1f)){
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
        if(willBalanceBecomeNegative(account, amount)){
            return;
        }

        account.setBalance(-amount);

    }

    public void deposit(String accountId, float amount){
        if(Utils.isNegativeAmount(amount)){
            return;
        }
        Account account=getAccountFromId(accountId);
        if(account==null){
            return;
        }
        account.setBalance(amount);
    }

    public void transferTo(AccountCard card, Account accountTo, float amount){

        if(Utils.isNegativeAmount(amount)){
            return;
        }
        Account accountFrom=getAccountFromId(card.getAccountId());
        if(accountFrom==null){
            return;
        }
        if(willBalanceBecomeNegative(accountFrom, amount)){
            return;
        }
        accountFrom.setBalance(-amount);
        accountTo.setBalance(amount);
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

    private boolean accountBelongsToPerson(Account account, Person person){
        if(account.getPerson()!=person){
            System.out.println("This account doesn't belongs to you");
        }
        return account.getPerson()==person;
    }


    private boolean willBalanceBecomeNegative(Account account, float amount){
        if(account.getBalance()-amount<0){
            System.out.println("You can't have negative balances");
            return true;
        }
        return false;
    }


    public float getBalance(String idAccount, Person person){
        float balance=0f;
        if(!accountExists(idAccount)){
            return balance;
        }
        Account account=getAccountFromId(idAccount);
        if(!accountBelongsToPerson(account,person)){
            return balance;
        }
        return  getAccountFromId(idAccount).getBalance();
    }



}
