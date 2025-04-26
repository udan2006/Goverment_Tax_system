package com.example.cwdemo.model;

import javafx.beans.property.*;

public class Transaction {
    private final SimpleStringProperty billNumber;
    private final SimpleStringProperty itemCode;
    private final SimpleDoubleProperty internalPrice;
    private final SimpleDoubleProperty discount;
    private final SimpleDoubleProperty salesPrice;
    private final SimpleIntegerProperty quantity;
    private final SimpleIntegerProperty checkSum;
    private SimpleStringProperty ValidationStatus;
    private SimpleDoubleProperty profit;

    public Transaction (String billNumber, String itemCode,Double internalPrice, Double discount, Double salesPrice, int quantity, int checkSum) {
        this.billNumber = new SimpleStringProperty(billNumber);
        this.itemCode = new SimpleStringProperty(itemCode);
        this.internalPrice = new SimpleDoubleProperty(internalPrice);
        this.discount = new SimpleDoubleProperty(discount);
        this.salesPrice = new SimpleDoubleProperty(salesPrice);
        this.quantity = new SimpleIntegerProperty(quantity);
        this.checkSum = new SimpleIntegerProperty(checkSum);
        this.ValidationStatus = new SimpleStringProperty("Not Validated");
        this.profit = new SimpleDoubleProperty(0.0);
    }

    public String getBillNumber() {
        return billNumber.get();
    }
    public SimpleStringProperty billNumberProperty() {
        return billNumber;
    }
    public void setBillNumber(String billNumber) {
        this.billNumber.set(billNumber);
    }


    public String getItemCode() {
        return itemCode.get();
    }
    public SimpleStringProperty itemCodeProperty() {
        return itemCode;
    }
    public void setItemCode(String itemCode) {
        this.itemCode.set(itemCode);
    }


    public double getInternalPrice() {
        return internalPrice.get();
    }
    public SimpleDoubleProperty internalPriceProperty() {
        return internalPrice;
    }
    public void setInternalPrice(double internalPrice) {
        this.internalPrice.set(internalPrice);
    }


    public double getDiscount() {
        return discount.get();
    }
    public SimpleDoubleProperty discountProperty() {
        return discount;
    }
    public void setDiscount(double discount) {
        this.discount.set(discount);
    }


    public double getSalesPrice() {
        return salesPrice.get();
    }
    public SimpleDoubleProperty salesPriceProperty() {
        return salesPrice;
    }
    public void setSalesPrice(double salesPrice) {
        this.salesPrice.set(salesPrice);
    }


    public int getQuantity() {
        return quantity.get();
    }
    public SimpleIntegerProperty quantityProperty() {
        return quantity;
    }
    public void setQuantity(int quantity) {
        this.quantity.set(quantity);
    }


    public int getCheckSum() {
        return checkSum.get();
    }
    public SimpleIntegerProperty checkSumProperty() {
        return checkSum;
    }
    public void setCheckSum(int checkSum) {
        this.checkSum.set(checkSum);
    }


    public String getValidationStatus() {
        return ValidationStatus.get();
    }
    public SimpleStringProperty validationStatusProperty() {
        return ValidationStatus;
    }
    public void setValidationStatus(String validationStatus) {
        this.ValidationStatus.set(validationStatus);
    }


    public double getProfit() {
        return profit.get();
    }
    public SimpleDoubleProperty profitProperty() {
        return profit;
    }
    public void setProfit(double profit) {
        this.profit.set(profit);
    }
}
