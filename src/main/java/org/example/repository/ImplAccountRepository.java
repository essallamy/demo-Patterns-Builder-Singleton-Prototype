package org.example.repository;

import org.example.model.AccountStatus;
import org.example.model.AccountType;
import org.example.model.BankAccount;
import org.example.model.BankDirector;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class ImplAccountRepository implements AccountRepository{
    private final static ImplAccountRepository accountRepository;   // Singleton

    static {//en va cree l'objet en moment de charge la class en memoire(bloc Static: il s'excute lorsque charge la class en memoire pour initialise l'objet)

        System.out.println("Singleton instantiation");
        accountRepository=new ImplAccountRepository();                // il m'instancier la classe en chargement de la class en memoire
    }
    private ImplAccountRepository(){}
   private Map<Long,BankAccount>bankAccountMap=new HashMap<>();
   private  long accountCount=0;
    @Override
    public BankAccount save(BankAccount bankAccount) {
        long accountid;
        synchronized (this){
            accountid=++accountCount;  //critical zone
        }
        bankAccount.setAccountId(accountid);
        synchronized (this){
            bankAccountMap.put(accountid,bankAccount);  //critical zone
        }
       return bankAccount;
    }

    @Override
    public List<BankAccount> findAll() {

        return bankAccountMap.values().stream().collect(Collectors.toList());
    }

    @Override
    public Optional<BankAccount> findById(Long id) {
       BankAccount account=bankAccountMap.get(id);
       if (account==null)
           return Optional.empty();
       else
           return Optional.of(account);
    }

    @Override
    public List<BankAccount> searchAccounts(Predicate<BankAccount> predicate) {


        return bankAccountMap.values().stream().filter(predicate).collect(Collectors.toList());
    }

    @Override
    public BankAccount update(BankAccount account) {
        bankAccountMap.put(account.getAccountId(),account);
        return account;
    }

    @Override
    public void deleteById(Long id) {

        bankAccountMap.remove(id);
    }

    public void populateData(){
        for(int i=0;i<10;i++){
            BankAccount build = BankDirector.accountBuildderbuilder()
                    .balance(1000+Math.random()*9000)
                    .type(Math.random()>0.5? AccountType.SAVING_ACCOUNT:AccountType.CURRENT_ACCOUNT)
                    .status(Math.random()>0.5? AccountStatus.CREATED:AccountStatus.ACTIVATED)
                    .currency(Math.random()>0.5?"MAD":"USD")
                    .build();
            save(build);


        }
        System.out.println("*********************");
        System.out.println(Thread.currentThread().getName());
        System.out.println("Account Count ="+ accountCount);
        System.out.println("Size: "+bankAccountMap.values().size());
        System.out.println("*********************");
    }

    public static ImplAccountRepository getInstance(){
    /*  if(accountRepository==null)
          System.out.println("Singleton instantiation");
          accountRepository=new ImplAccountRepository();
*/
        return accountRepository;
    }
}
