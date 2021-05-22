package com.bank_accounts.bank;

public class CheckingAccount extends Account {


    public CheckingAccount(float amount){
      super(amount, AccountType.CHECKING);

    }

    @Override
    protected void giveCard(){
        if(super.hasCard){
            System.out.println("You already have a card");
            return;
        }
        super.hasCard=true;

    }

}
