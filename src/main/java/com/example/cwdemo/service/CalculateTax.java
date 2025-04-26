package com.example.cwdemo.service;

import com.example.cwdemo.model.Transaction;
import javafx.collections.ObservableList;

public class CalculateTax {
    public double calculateTotalProfit(ObservableList<Transaction> transactions) {
        double totalProfit = 0.0;

        for (Transaction transaction : transactions) {
            double profit = transaction.getProfit();
            if (profit > 0) {
                totalProfit += profit;
            }
        }
        return totalProfit;
    }

    public double calculateTotalLoss(ObservableList<Transaction> transactions) {
        double totalLoss = 0.0;
        for (Transaction transaction : transactions) {
            double loss = transaction.getProfit();
            if (loss < 0) {
                totalLoss += loss;
            }
        }
        return totalLoss;
    }

    public double calculateTotalTax(ObservableList<Transaction> transactions, double taxRate) {
        double totalTax = 0.0;
        for (Transaction transaction : transactions) {
            totalTax += transaction.getProfit();

        }
        return (totalTax * taxRate) / 100;
    }
}
