package org.example.model;

public class BankDirector {

    //Cree un method builder
    public static BankAccount.AccountBuilder accountBuildderbuilder(){

        return new BankAccount.AccountBuilder();
    }
}
