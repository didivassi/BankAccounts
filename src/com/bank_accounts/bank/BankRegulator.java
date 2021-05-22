package com.bank_accounts.bank;

import java.util.ArrayList;
import java.util.List;

public abstract class  BankRegulator {

    final private static List<Bank> BANKS= new ArrayList<Bank>();

    public static Bank createBank(String brand){

        if(brandExists(brand)){
            System.out.println("The Brand "+brand+" already exists");
            return null;
        }
        if(brand.contains("-")){
            System.out.println("Brand can't contain '-' (hyphen) character  ");
            return null;
        }
        Bank bank=new Bank(brand);
        BANKS.add(bank);
        return bank;

    }

    private static boolean brandExists(String brand){
        for (Bank bank:BANKS) {
            if(bank.toString().equals(brand)){
                return true;
            }
        }
        return false;
    }

    protected static Bank getBank(String brand){
        for (Bank bank:BANKS) {
            if(bank.toString().equals(brand)){
                return bank;
            }
        }
        return null;
    }

}
