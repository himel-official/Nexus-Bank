package com.nexusbank.model;

import java.sql.Timestamp;

public class Transaction {
    private int transactionId;
    private int userId;
    private int accountId;
    private String transactionType;   // DEPOSIT, WITHDRAW, TRANSFER
    private double amount;
    private String description;
    private Timestamp transactionDate;

    // Constructor
    public Transaction(int userId, int accountId, String transactionType, 
                       double amount, String description) {
        this.userId = userId;
        this.accountId = accountId;
        this.transactionType = transactionType;
        this.amount = amount;
        this.description = description;
    }

    // Getters and Setters
    public int getTransactionId() { return transactionId; }
    public void setTransactionId(int transactionId) { this.transactionId = transactionId; }

    public int getUserId() { return userId; }
    public int getAccountId() { return accountId; }
    public String getTransactionType() { return transactionType; }
    public double getAmount() { return amount; }
    public String getDescription() { return description; }
    public Timestamp getTransactionDate() { return transactionDate; }
    public void setTransactionDate(Timestamp transactionDate) { this.transactionDate = transactionDate; }
}