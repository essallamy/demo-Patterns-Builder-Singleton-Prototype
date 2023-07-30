package org.example.model;

public class BankAccount implements Cloneable{
    private Long accountId;
    private  double balance;
    private String currency;
    private AccountType type;
    private AccountStatus status;


    private Customer customer;

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }


    public Long getAccountId() {
        return accountId;
    }

    public void setAccountId(Long accountId) {
        this.accountId = accountId;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public AccountType getType() {
        return type;
    }

    public void setType(AccountType type) {
        this.type = type;
    }

    public AccountStatus getStatus() {
        return status;
    }

    public void setStatus(AccountStatus status) {
        this.status = status;
    }

    public BankAccount(Long accountId, double balance, String currency, AccountType type, AccountStatus status,Customer customer) {
        this.accountId = accountId;
        this.balance = balance;
        this.currency = currency;
        this.type = type;
        this.status = status;
        this.customer=customer;
    }

    public BankAccount() {
    }
    @Override
    public String toString() {
        return "BankAccount{" +
                "accountId=" + accountId +
                ", balance=" + balance +
                ", currency='" + currency + '\'' +
                ", type=" + type +
                ", status=" + status +
                ", customer=" + customer +
                '}';
    }


    public static AccountBuilder builder(){
        return new AccountBuilder();
    }

    public static class  AccountBuilder{
        private BankAccount bankAccount=new BankAccount();

        public AccountBuilder accountId(Long id){  //methode permet de construire un attributs id
            bankAccount.accountId=id;
           return this;         //chaque methode doit retuner le meme objet de type Account bulder
        }

       public AccountBuilder currency(String currency){
            bankAccount.currency=currency;
            return this;
       }

        public AccountBuilder balance(double balance){
            bankAccount.balance=balance;
            return this;
        }

        public AccountBuilder type(AccountType type){
            bankAccount.type=type;
            return this;
        }

        public AccountBuilder status(AccountStatus status){
         if(bankAccount.getType()==AccountType.CURRENT_ACCOUNT)
            bankAccount.status=status;
         else bankAccount.status=AccountStatus.CREATED;
            return this;
        }
  // A la fin il faut construire la methode build Qui return l'objet qui est construit
        public BankAccount build(){

            return this.bankAccount;
        }
    }

    public BankAccount clone() throws CloneNotSupportedException{
         BankAccount bank= (BankAccount) super.clone();
         bank.setCustomer(this.customer.clone());
        return bank;
    }

}
