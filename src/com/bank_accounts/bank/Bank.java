package com.bank_accounts.bank;

import com.bank_accounts.Person;
import java.util.*;

public class Bank {

    final private static List<String> BRANDS= new ArrayList<String>();

    final private List<Account> customerAccounts = new ArrayList<Account>();
    private String brand;
    public Bank(String brand){
        this.brand=brand;
        BRANDS.add(brand);
    }

    public String createAccount(Person person, AccountType accountType, float amount){
        String idAccount;
        Account accountToOpen;

        if(Utils.isNegativeAmount(amount)){
            return null;
        }
        switch (accountType){
            case CHECKING:
                accountToOpen= new CheckingAccount(amount);
                break;
            case SAVINGS:
                if(amount==0){
                    System.out.println("You need to open this account with at least 1 euro");
                }
                accountToOpen= new SavingsAccount(amount);
                break;
            default:
                System.out.println("You need to choose between Checking or Savings when open an account");
                return null;
        }

        accountToOpen.setPerson(person);
        customerAccounts.add(accountToOpen);
        idAccount=Utils.createAccountId(customerAccounts.size()-1,brand);
        accountToOpen.setId(idAccount);

        return idAccount;
    }

    public void sendCard(String accountId, Person person){

        //vais validar que a conta é da pessoa
        //account.giveCard();

    }

    public void withdraw(String accountId, float amount){

        Account account=getAccountFromId(accountId);
        if(account==null){
            return;
        }
        if(account.getBalance()-amount<0){
            System.out.println("You can't have negative balances");
            return;
        }
        if (!account.hasCard){
            System.out.println("You need a Card to withdraw");
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

    public void transferTo(Account accountFrom, Account accountTo, float amount){
        if(Utils.isNegativeAmount(amount)){
            return;
        }
        if(accountFrom.getBalance()-amount<0){
            System.out.println("You can't have negative balances");
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
        return account.getPerson()==person;
    }


    public float getBalance(String idAccount){
        return  getAccountFromId(idAccount).getBalance();
    }



}
