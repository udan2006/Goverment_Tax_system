package com.example.cwdemo.service;

import com.example.cwdemo.model.Transaction;

// this method used to calculate the profit for a given transaction
public class CalculateProfit {
    public double calculate(Transaction transaction) {
        return (transaction.getSalesPrice() * transaction.getQuantity() - transaction.getDiscount()) -
                (transaction.getInternalPrice() * transaction.getQuantity()) ;
    }
}
