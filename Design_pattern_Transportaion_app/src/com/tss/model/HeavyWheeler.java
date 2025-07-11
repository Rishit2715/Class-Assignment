package com.tss.model;

import java.util.Random;

public class HeavyWheeler implements IVehicle {

    @Override
    public String generateLiscenceNumber() {
        Random random = new Random();
        int number = random.nextInt(900000) + 100000; 
        return "HW" + number;
    }

}
