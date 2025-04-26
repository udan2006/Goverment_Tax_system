package com.example.cwdemo;

import com.example.cwdemo.model.Transaction;
import com.example.cwdemo.service.CalculateProfit;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CalculateProfitTest {

    @Test
    public void testCalculateProfitZeroDiscount() {
        CalculateProfit calc = new CalculateProfit();
        Transaction transaction = new Transaction(
                "1001",
                "cake01",
                100.0,
                0.0,
                200.0,
                20,
                25
        );

        double expectedValue = 2000.0;
        double actualProfit = calc.calculate(transaction);

        assertEquals(expectedValue, actualProfit);
    }

    @Test
    public void testCalculateProfitPositive() {
        CalculateProfit calc = new CalculateProfit();
        Transaction transaction = new Transaction(
                "1001",
                "cake02",
                200.0,
                10.0,
                180.0,
                50,
                24
        );
        double expectedValue = -1010.0;
        double actualProfit = calc.calculate(transaction);

        assertEquals(expectedValue, actualProfit);
    }

    @Test
    public void testCalculateProfitGetZeroProfit() {
        CalculateProfit calc = new CalculateProfit();
        Transaction transaction = new Transaction(
                "1001",
                "CupCake01",
                100.0,
                0.0,
                200.0,
                0,
                28
        );
        double expectedValue = 0.0;
        double actualProfit = calc.calculate(transaction);

        assertEquals(expectedValue, actualProfit);
    }
}
