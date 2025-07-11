package com.tss.model;

import com.tss.exception.InsufficientWaterException;
import com.tss.exception.OverFillException;

public class WaterDispenser {

    protected int capacity = 100;
    protected int currentliters;

    public WaterDispenser(int liters) {
        this.currentliters = liters;
    }

    public void fillWater(int liters) {
        if (currentliters + liters <= capacity) {
            currentliters += liters;
        } else {
            throw new OverFillException();
        }
    }

    public int dispenseWater(int liters) {
        if (currentliters >= liters) { 
            currentliters -= liters;
            return currentliters;
        } else {
            throw new InsufficientWaterException();
        }
    }

    public int getCurrentLiters() {
        return currentliters;
    }
}
