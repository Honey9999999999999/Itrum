package ru.concurrentbank;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;

public class ConcurrentBank {
    public static class BankAccount {

        private final UUID accountId;
        private final AtomicInteger money;

        private BankAccount(int money) {
            accountId = UUID.randomUUID();
            this.money = new AtomicInteger(money);
        }

        public void deposit(int money) {
            this.money.addAndGet(money);
        }

        public int withdraw(int money) {

            if(this.money.get() < money){
                throw new NoMoneyException("Insufficient funds in the account : " + accountId);
            }

            this.money.addAndGet(-money);
            return money;
        }

        @Override
        public String toString(){
            return "Account : " + accountId + " | Balance : " + money;
        }

        public UUID getAccountId() {
            return accountId;
        }

        public int getBalance() {
            return money.get();
        }
    }

    private final List<BankAccount> accounts;

    public ConcurrentBank() {
        accounts = new ArrayList<>();
    }

    public BankAccount createAccount(int money) {
        BankAccount bankAccount = new BankAccount(money);
        accounts.add(bankAccount);

        return bankAccount;
    }

    public void transfer(BankAccount a, BankAccount b, int money) {
       try {
           b.deposit(a.withdraw(money));
       }
       catch (NoMoneyException ex) {
           ex.printStackTrace();
       }
    }

    public int getTotalBalance() {
        int result = 0;

        for(BankAccount account : accounts) {
            result += account.getBalance();
        }

        return result;
    }
}
