package com.hdfc;

public class CalculateBonus implements BonusCalculator {
    @Override
    public double calculate(double salary) {
        return salary*1.10;
    }
}
