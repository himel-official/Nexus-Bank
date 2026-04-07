package com.nexusbank.model;

public class Account {
    private int accountId;
    private int userId;
    private String accountNumber;
    private double balance;

    // Constructor
    public Account(int userId, String accountNumber, double initialBalance) {
        this.userId = userId;
        this.accountNumber = accountNumber;
        this.balance = initialBalance;
    }

    // Getters
    public int getAccountId() { return accountId; }
    public void setAccountId(int accountId) { this.accountId = accountId; }

    public int getUserId() { return userId; }
    public String getAccountNumber() { return accountNumber; }
    public double getBalance() { return balance; }

    // Controlled methods (Basic Encapsulation + Validation)
    public boolean deposit(double amount) {
        if (amount > 0) {
            this.balance += amount;
            return true;
        }
        return false;
    }

    public boolean withdraw(double amount) {
        if (amount > 0 && this.balance >= amount) {
            this.balance -= amount;
            return true;
        }
        return false;
    }

    public boolean transfer(double amount) {
        return withdraw(amount);   // Reuse withdraw logic
    }
}