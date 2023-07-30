package org.example;

import org.example.model.AccountType;
import org.example.model.BankAccount;
import org.example.repository.ImplAccountRepository;

import java.io.IOException;
import java.util.List;

public class Main2 {
    public static void main(String[] args) throws IOException {
        JsonSerializer<BankAccount>bankAccountJsonSerializer=new JsonSerializer<>();
        ImplAccountRepository account2=ImplAccountRepository.getInstance();
        for(int i=0;i<10;i++){
            new Thread(() -> {
          account2.populateData();
            }).start();
        }
   System.out.print("Tape a character:");
        System.in.read();

        List<BankAccount> all = account2.findAll();
        all.stream().map(acc->bankAccountJsonSerializer.toJson(acc))
                .forEach(acc->System.out.println(acc));


    }
    }

