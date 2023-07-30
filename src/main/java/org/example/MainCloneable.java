package org.example;

import org.example.model.AccountStatus;
import org.example.model.AccountType;
import org.example.model.BankAccount;
import org.example.model.Customer;

import java.io.IOException;

public class MainCloneable {

    public static void main(String[] args) throws CloneNotSupportedException {
        BankAccount bankAccount=new BankAccount();
        bankAccount.setAccountId(1L);
        bankAccount.setCurrency("MAD");
        bankAccount.setType(AccountType.CURRENT_ACCOUNT);
        bankAccount.setStatus(AccountStatus.SUSPENDED);
        bankAccount.setCustomer(new Customer(1L,"Moha"));

        BankAccount bankAccount1=new BankAccount() ;
        bankAccount1.setAccountId(bankAccount.getAccountId());
        bankAccount1.setCurrency(bankAccount.getCurrency());
        bankAccount1.setType(bankAccount.getType());
        bankAccount1.setStatus(bankAccount.getStatus());

        BankAccount bankAccount2=bankAccount.clone();

        System.out.println(bankAccount);
        System.out.println(bankAccount1);
        System.out.println(bankAccount2);
        System.out.println("===============");
        bankAccount.getCustomer().setName("reda");
        System.out.println(bankAccount);

        System.out.println(bankAccount2);

    }
}
