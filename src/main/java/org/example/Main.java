package org.example;

import org.example.model.AccountStatus;
import org.example.model.AccountType;
import org.example.model.BankAccount;
import org.example.model.BankDirector;
import org.example.repository.AccountRepository;
import org.example.repository.ImplAccountRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        JsonSerializer<BankAccount>bankAccountJsonSerializer=new JsonSerializer<>();
        BankAccount build = BankDirector.accountBuildderbuilder()
                .accountId(1L)
                .currency("MAD")
                .balance(7000)
                .type(AccountType.SAVING_ACCOUNT)
                .status(AccountStatus.CREATED)
                .build();

        BankAccount aaa=BankAccount.builder()
                .accountId(1L)
                .currency("MAD")
                .balance(7000)
                .type(AccountType.SAVING_ACCOUNT)
                .status(AccountStatus.CREATED)
                .build();

        System.out.println("Hello world!===="+aaa.toString());
        System.out.println("Hello world!===="+build.toString());
        System.out.println(" ==========================================Add populateData==============================================================");
        ImplAccountRepository account=ImplAccountRepository.getInstance();
        account.populateData();
        List<BankAccount> all = account.findAll();
        all.stream().map(acc->bankAccountJsonSerializer.toJson(acc))
                .forEach(acc->System.out.println(acc));
        System.out.println(" ======================================findById======================================================");
        BankAccount byId = account.findById(1L).orElse(null);
        if(byId!=null){
            System.out.println(bankAccountJsonSerializer.toJson(byId));
        }
        System.out.println(" ======================================SerchAccounts======================================================");

        List<BankAccount> serch = account.searchAccounts(bankAccount -> bankAccount.getType()==AccountType.CURRENT_ACCOUNT);
        serch.stream().map(bankAccount -> bankAccountJsonSerializer.toJson(bankAccount))
                        .forEach(acc->System.out.println(acc));

    }
}