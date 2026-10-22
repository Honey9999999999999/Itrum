package ru.concurrentbank;

public class ConcurrentBankExample {
    public static void main(String[] args) {
        ConcurrentBank bank = new ConcurrentBank();

        // Создание счетов
        ConcurrentBank.BankAccount account1 = bank.createAccount(1000);
        ConcurrentBank.BankAccount account2 = bank.createAccount(500);

        // Перевод между счетами
        Thread transferThread1 = new Thread(() -> {
            while (true) {
                bank.transfer(account1, account2, 200);
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        });
        Thread transferThread2 = new Thread(() -> {
            while (true) {
                bank.transfer(account2, account1, 100);
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        });

        Thread printerThread = new Thread(() -> {
            while (true) {
                System.out.println("Total balance: " + bank.getTotalBalance());
                System.out.println(account1);
                System.out.println(account2);
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        });

        transferThread1.start();
        transferThread2.start();
        printerThread.start();
    }
}
