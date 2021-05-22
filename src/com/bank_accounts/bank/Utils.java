package com.bank_accounts.bank;


public class Utils {

    protected static String createAccountId(int idAccount, String brand){
        return brand+"-"+idAccount;
    }
    protected static int parseIdAccount(String idAccount){
        String[] temp=idAccount.split("-");
        return Integer.parseInt(temp[1]);
    }
    protected static String parseBrandFromIdAccount(String idAccount){
        String[] temp=idAccount.split("-");
        return temp[0];
    }
}
