package com.bank_accounts.bank;

public abstract class CardAccount extends Account{
    protected AccountCard card;
    protected boolean cardAttributed;

    public CardAccount(float openBalance, AccountType type) {
        super(openBalance, type);
    }

    @Override
    protected boolean canHaveCard(){
        return true;
    }

    protected AccountCard attributeCard(){
        cardAttributed=true;
        return new AccountCard(super.getAccountId());
    }

    public boolean isCardAttributed() {
        return cardAttributed;
    }

    protected AccountCard getCard(){
        return card;
    }
}
